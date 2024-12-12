package Entity;

import java.util.Date;

public class Entities {

    public static class Buku {
        private String id, judul, pengarang, penerbit;
        private int tahunTerbit, stok;

        public Buku(String id, String judul, String pengarang, String penerbit, int tahunTerbit, int stok) {
            this.id = id;
            this.judul = judul;
            this.pengarang = pengarang;
            this.penerbit = penerbit;
            this.tahunTerbit = tahunTerbit;
            this.stok = stok;
        }

        public String getId() { return id; }
        public String getJudul() { return judul; }
        public String getPengarang() { return pengarang; }
        public int getStok() { return stok; }

        public void pinjam() { if (stok > 0) stok--; }
        public void kembalikan() { stok++; }
    }

    public static class Anggota {
        private String id, nama, alamat, nomorTelepon;

        public Anggota(String id, String nama, String alamat, String nomorTelepon) {
            this.id = id;
            this.nama = nama;
            this.alamat = alamat;
            this.nomorTelepon = nomorTelepon;
        }

        public String getId() { return id; }
        public String getNama() { return nama; }
    }

    public static class Peminjaman {
        private String idPeminjaman, idAnggota, idBuku;
        private Date tanggalPinjam;
        private int denda;

        public Peminjaman(String idPeminjaman, String idAnggota, String idBuku, Date tanggalPinjam) {
            this.idPeminjaman = idPeminjaman;
            this.idAnggota = idAnggota;
            this.idBuku = idBuku;
            this.tanggalPinjam = tanggalPinjam;
            this.denda = 0;
        }

        public String getIdPeminjaman() { return idPeminjaman; }
        public String getIdAnggota() { return idAnggota; }
        public String getIdBuku() { return idBuku; }
    }
}
