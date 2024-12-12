package libraryApp.Entity;

import java.util.Date;

public class Entities {

    // Kelas Anggota (Member)
    public static class Anggota {
        private String id; // Kolom id anggota
        private String nama;
        private String alamat;
        private String nomorTelepon;

        // Konstruktor tanpa generate ID
        public Anggota(String nama, String alamat, String nomorTelepon) {
            this.nama = nama;
            this.alamat = alamat;
            this.nomorTelepon = nomorTelepon;
        }

        // Konstruktor dengan ID yang sudah ada
        public Anggota(String id, String nama, String alamat, String nomorTelepon) {
            this.id = id;
            this.nama = nama;
            this.alamat = alamat;
            this.nomorTelepon = nomorTelepon;
        }

        // Getter dan Setter
        public String getId() {
            return id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(final String nama) {
            this.nama = nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(final String alamat) {
            this.alamat = alamat;
        }

        public String getNomorTelepon() {
            return nomorTelepon;
        }

        public void setNomorTelepon(final String nomorTelepon) {
            this.nomorTelepon = nomorTelepon;
        }
    }

    // Kelas Buku (Book)
    public static class Buku {
        private String id; // Kolom id buku
        private String judul;
        private String pengarang;
        private String penerbit;
        private int tahunTerbit;
        private int stok;

        // Konstruktor tanpa generate ID
        public Buku(String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
            this.judul = judul;
            this.pengarang = pengarang;
            this.penerbit = penerbit;
            this.tahunTerbit = tahunTerbit;
            this.stok = stok;
        }

        // Konstruktor dengan ID yang sudah ada
        public Buku(String id, String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
            this.id = id;
            this.judul = judul;
            this.pengarang = pengarang;
            this.penerbit = penerbit;
            this.tahunTerbit = tahunTerbit;
            this.stok = stok;
        }

        // Getter dan Setter
        public String getId() {
            return id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(final String judul) {
            this.judul = judul;
        }

        public String getPengarang() {
            return pengarang;
        }

        public void setPengarang(final String pengarang) {
            this.pengarang = pengarang;
        }

        public String getPenerbit() {
            return penerbit;
        }

        public void setPenerbit(final String penerbit) {
            this.penerbit = penerbit;
        }

        public int getTahunTerbit() {
            return tahunTerbit;
        }

        public void setTahunTerbit(final int tahunTerbit) {
            this.tahunTerbit = tahunTerbit;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(final int stok) {
            this.stok = stok;
        }

        public void pinjam() {
            if (stok > 0) {
                stok--;
            } else {
                System.out.println("Stok buku tidak tersedia!");
            }
        }

        public void kembalikan() {
            stok++;
        }
    }

    // Kelas Peminjaman (Borrowing)
    public static class Peminjaman {
        private String id; // Kolom id peminjaman
        private String idAnggota;
        private String idBuku;
        private Date tanggalPeminjaman;

        // Konstruktor tanpa generate ID
        public Peminjaman(String idAnggota, String idBuku, Date tanggalPeminjaman) {
            this.idAnggota = idAnggota;
            this.idBuku = idBuku;
            this.tanggalPeminjaman = tanggalPeminjaman;
        }

        // Konstruktor dengan ID yang sudah ada
        public Peminjaman(String id, String idAnggota, String idBuku, Date tanggalPeminjaman) {
            this.id = id;
            this.idAnggota = idAnggota;
            this.idBuku = idBuku;
            this.tanggalPeminjaman = tanggalPeminjaman;
        }

        // Getter dan Setter
        public String getId() {
            return id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public String getIdAnggota() {
            return idAnggota;
        }

        public void setIdAnggota(final String idAnggota) {
            this.idAnggota = idAnggota;
        }

        public String getIdBuku() {
            return idBuku;
        }

        public void setIdBuku(final String idBuku) {
            this.idBuku = idBuku;
        }

        public Date getTanggalPeminjaman() {
            return tanggalPeminjaman;
        }

        public void setTanggalPeminjaman(final Date tanggalPeminjaman) {
            this.tanggalPeminjaman = tanggalPeminjaman;
        }
    }
}