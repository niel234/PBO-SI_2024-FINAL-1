package Service;

import Entity.Entities;
import repository.RepositoryInterface;
import java.util.Date;
import java.util.Optional;

public class LibraryService implements LibraryServiceInterface {
    private final RepositoryInterface repository;

    public LibraryService(RepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public String registerMember(String nama, String alamat, String nomorTelepon) {
        String id = "A" + (repository.getAnggota().size() + 1);
        repository.addAnggota(new Entities.Anggota(id, nama, alamat, nomorTelepon));
        return id;
    }

    @Override
    public String addBook(String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
        String id = "B" + (repository.getBuku().size() + 1);
        repository.addBuku(new Entities.Buku(id, judul, pengarang, penerbit, tahunTerbit, stok));
        return id;
    }

    @Override
    public boolean borrowBook(String idAnggota, String idBuku) {
        Optional<Entities.Buku> buku = repository.findBukuById(idBuku);
        if (buku.isPresent() && buku.get().getStok() > 0) {
            String idPeminjaman = "P" + (repository.getPeminjaman().size() + 1);
            repository.addPeminjaman(new Entities.Peminjaman(idPeminjaman, idAnggota, idBuku, new Date()));
            buku.get().pinjam();
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(String idPeminjaman) {
        Optional<Entities.Peminjaman> peminjaman = repository.findPeminjamanById(idPeminjaman);
        if (peminjaman.isPresent()) {
            Optional<Entities.Buku> buku = repository.findBukuById(peminjaman.get().getIdBuku());
            buku.ifPresent(Entities.Buku::kembalikan);
            repository.removePeminjaman(idPeminjaman);
            return true;
        }
        return false;
    }
}

