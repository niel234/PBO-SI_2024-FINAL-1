import java.util.*;

class Buku {
    String id, judul, pengarang, penerbit;
    int tahunTerbit, stok;

    public Buku(String id, String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
        this.id = id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.stok = stok;
    }
}

class Anggota {
    String id, nama, alamat, nomorTelepon;

    public Anggota(String id, String nama, String alamat, String nomorTelepon) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
    }
}

class Peminjaman {
    String idPeminjaman, idAnggota, idBuku;
    Date tanggalPinjam, tanggalKembali;
    int denda;

    public Peminjaman(String idPeminjaman, String idAnggota, String idBuku, Date tanggalPinjam) {
        this.idPeminjaman = idPeminjaman;
        this.idAnggota = idAnggota;
        this.idBuku = idBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.denda = 0;
    }
}

public class SistemPerpustakaan {
    static Scanner input = new Scanner(System.in);
    static List<Buku> daftarBuku = new ArrayList<>();
    static List<Anggota> daftarAnggota = new ArrayList<>();
    static List<Peminjaman> daftarPeminjaman = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Perpustakaan Sederhana ===");
            System.out.println("1. Registrasi Anggota");
            System.out.println("2. Tambah Data Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Cari Buku");
            System.out.println("6. Tampilkan Daftar Buku Tersedia");
            System.out.println("7. Tampilkan Riwayat Peminjaman Anggota");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // Menghilangkan newline

            switch (pilihan) {
                case 1 -> registrasiAnggota();
                case 2 -> tambahDataBuku();
                case 3 -> pinjamBuku();
                case 4 -> kembalikanBuku();
                case 5 -> cariBuku();
                case 6 -> tampilkanBukuTersedia();
                case 7 -> tampilkanRiwayatPeminjaman();
                case 8 -> System.out.println("Terima kasih telah menggunakan sistem ini!");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 8);
    }

    static void registrasiAnggota() {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Alamat: ");
        String alamat = input.nextLine();
        System.out.print("Nomor Telepon: ");
        String nomorTelepon = input.nextLine();
        String id = "A" + (daftarAnggota.size() + 1);
        daftarAnggota.add(new Anggota(id, nama, alamat, nomorTelepon));
        System.out.println("Anggota berhasil didaftarkan dengan ID: " + id);
    }

    static void tambahDataBuku() {
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Pengarang: ");
        String pengarang = input.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = input.nextLine();
        System.out.print("Tahun Terbit: ");
        int tahunTerbit = input.nextInt();
        System.out.print("Jumlah Stok: ");
        int stok = input.nextInt();
        input.nextLine();
        String id = "B" + (daftarBuku.size() + 1);
        daftarBuku.add(new Buku(id, judul, pengarang, penerbit, tahunTerbit, stok));
        System.out.println("Buku berhasil ditambahkan dengan ID: " + id);
    }

    static void pinjamBuku() {
        System.out.print("ID Anggota: ");
        String idAnggota = input.nextLine();
        System.out.print("ID Buku: ");
        String idBuku = input.nextLine();
        Optional<Buku> buku = daftarBuku.stream().filter(b -> b.id.equals(idBuku)).findFirst();
        if (buku.isPresent() && buku.get().stok > 0) {
            String idPeminjaman = "P" + (daftarPeminjaman.size() + 1);
            daftarPeminjaman.add(new Peminjaman(idPeminjaman, idAnggota, idBuku, new Date()));
            buku.get().stok--;
            System.out.println("Buku berhasil dipinjam dengan ID Peminjaman: " + idPeminjaman);
        } else {
            System.out.println("Buku tidak tersedia.");
        }
    }

    static void kembalikanBuku() {
        System.out.print("ID Peminjaman: ");
        String idPeminjaman = input.nextLine();
        Optional<Peminjaman> peminjaman = daftarPeminjaman.stream().filter(p -> p.idPeminjaman.equals(idPeminjaman)).findFirst();
        if (peminjaman.isPresent()) {
            Date sekarang = new Date();
            long selisihHari = (sekarang.getTime() - peminjaman.get().tanggalPinjam.getTime()) / (1000 * 60 * 60 * 24);
            if (selisihHari > 7) {
                peminjaman.get().denda = (int) ((selisihHari - 7) * 1000);
                System.out.println("Denda keterlambatan: Rp" + peminjaman.get().denda);
            }
            Optional<Buku> buku = daftarBuku.stream().filter(b -> b.id.equals(peminjaman.get().idBuku)).findFirst();
            buku.ifPresent(b -> b.stok++);
            System.out.println("Buku berhasil dikembalikan.");
        } else {
            System.out.println("ID Peminjaman tidak ditemukan.");
        }
    }

    static void cariBuku() {
        System.out.print("Cari berdasarkan (judul/pengarang): ");
        String kataKunci = input.nextLine();
        daftarBuku.stream()
                .filter(b -> b.judul.contains(kataKunci) || b.pengarang.contains(kataKunci))
                .forEach(b -> System.out.println(b.id + " - " + b.judul + " oleh " + b.pengarang));
    }

    static void tampilkanBukuTersedia() {
        daftarBuku.stream().filter(b -> b.stok > 0).forEach(b -> System.out.println(b.id + " - " + b.judul + " (Stok: " + b.stok + ")"));
    }

    static void tampilkanRiwayatPeminjaman() {
        System.out.print("ID Anggota: ");
        String idAnggota = input.nextLine();
        daftarPeminjaman.stream()
                .filter(p -> p.idAnggota.equals(idAnggota))
                .forEach(p -> System.out.println(p.idPeminjaman + " - Buku: " + p.idBuku + " Tanggal Pinjam: " + p.tanggalPinjam));
    }
}