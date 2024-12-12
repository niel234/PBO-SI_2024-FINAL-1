package libraryApp.Service;

import libraryApp.Entity.Entities;
import libraryApp.repository.RepositoryInterface;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LibraryService implements LibraryServiceInterface {
    private final RepositoryInterface repository;

    public LibraryService(RepositoryInterface repository) {
        this.repository = repository;
    }

     @Override
     public Optional<Entities.Anggota> findAnggotaById(String id) {
         return repository.findAnggotaById(id);
     }

     @Override
     public void updateBuku(Entities.Buku buku) {
         repository.updateBuku(buku);
     }

    @Override
    public String registerMember(String nama, String alamat, String nomorTelepon) {
        // Validasi input
        if (nama == null || alamat == null || nomorTelepon == null || nama.isEmpty() || alamat.isEmpty() || nomorTelepon.isEmpty()) {
            throw new IllegalArgumentException("Data anggota tidak boleh kosong.");
        }

        // Generate ID Anggota
        String idAnggota = generateId("Anggota");

        // Buat dan tambahkan anggota baru
        Entities.Anggota anggota = new Entities.Anggota(idAnggota, nama, alamat, nomorTelepon);
        repository.addAnggota(anggota);
        return anggota.getId(); // Kembalikan ID anggota yang di-generate
    }

    @Override
    public String addBook(String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
        // Validasi input
        if (judul == null || pengarang == null || penerbit == null || judul.isEmpty() || pengarang.isEmpty() || penerbit.isEmpty() || stok <= 0) {
            throw new IllegalArgumentException("Data buku tidak valid.");
        }

        // Generate ID Buku
        String idBuku = generateId("Buku");

        // Buat dan tambahkan buku baru
        Entities.Buku buku = new Entities.Buku(idBuku, judul, pengarang, penerbit, tahunTerbit, stok);
        repository.addBuku(buku);
        return buku.getId(); // Kembalikan ID buku yang di-generate
    }

    @Override
    public String borrowBook(String idAnggota, String idBuku) {
        // Cek keberadaan anggota
        if (repository.findAnggotaById(idAnggota).isEmpty()) {
            throw new IllegalArgumentException("Anggota tidak ditemukan.");
        }

        // Cek stok buku
        Optional<Entities.Buku> buku = repository.findBukuById(idBuku);
        if (buku.isPresent() && buku.get().getStok() > 0) {
            // Generate ID Peminjaman
            String idPeminjaman = generateId("Peminjaman");

            // Buat dan tambahkan peminjaman
            repository.addPeminjaman(new Entities.Peminjaman(idPeminjaman, idAnggota, idBuku, new java.util.Date()));
            buku.get().pinjam(); // Kurangi stok buku
            repository.updateBuku(buku.get()); // Update status buku di libraryApp.repository
            return idPeminjaman; // Kembalikan ID peminjaman
        }
        throw new IllegalStateException("Buku tidak tersedia untuk dipinjam.");
    }

    @Override
    public boolean returnBook(String idPeminjaman) {
        // Cek keberadaan peminjaman
        Optional<Entities.Peminjaman> peminjaman = repository.findPeminjamanById(idPeminjaman);
        if (peminjaman.isPresent()) {
            // Cari buku yang terkait dengan peminjaman
            Optional<Entities.Buku> buku = repository.findBukuById(peminjaman.get().getIdBuku());
            if (buku.isPresent()) {
                buku.get().kembalikan(); // Tambahkan stok buku
                repository.updateBuku(buku.get()); // Update status buku di libraryApp.repository
                repository.removePeminjaman(idPeminjaman); // Hapus data peminjaman
                return true; // Berhasil mengembalikan buku
            }
        }
        throw new IllegalArgumentException("Data peminjaman tidak ditemukan.");
    }

    private String generateId(String type) {
        Optional<?> lastEntity = null;

        switch (type) {
            case "Anggota":
                lastEntity = repository.getAllAnggota().stream()
                        .max((a1, a2) -> Integer.compare(Integer.parseInt(((Entities.Anggota) a1).getId()), Integer.parseInt(((Entities.Anggota) a2).getId())));
                break;
            case "Buku":
                lastEntity = repository.getAllBuku().stream()
                        .max((b1, b2) -> Integer.compare(Integer.parseInt(((Entities.Buku) b1).getId()), Integer.parseInt(((Entities.Buku) b2).getId())));
                break;
            case "Peminjaman":
                lastEntity = repository.getAllPeminjaman().stream()
                        .max((p1, p2) -> Integer.compare(Integer.parseInt(((Entities.Peminjaman) p1).getId()), Integer.parseInt(((Entities.Peminjaman) p2).getId())));
                break;
        }

        if (lastEntity.isPresent()) {
            if (type.equals("Anggota")) {
                return String.valueOf(Integer.parseInt(((Entities.Anggota) lastEntity.get()).getId()) + 1);
            } else if (type.equals("Buku")) {
                return String.valueOf(Integer.parseInt(((Entities.Buku) lastEntity.get()).getId()) + 1);
            } else if (type.equals("Peminjaman")) {
                return String.valueOf(Integer.parseInt(((Entities.Peminjaman) lastEntity.get()).getId()) + 1);
            }
        }
        return "1";
    }
}