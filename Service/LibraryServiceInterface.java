package Service;

public interface LibraryServiceInterface {
    String registerMember(String nama, String alamat, String nomorTelepon);
    String addBook(String judul, String pengarang, String penerbit, int tahunTerbit, int stok);
    boolean borrowBook(String idAnggota, String idBuku);
    boolean returnBook(String idPeminjaman);
}
