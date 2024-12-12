package libraryApp.Service;

import libraryApp.Entity.Entities;

import java.util.Optional;

public interface LibraryServiceInterface {

    // Finds Anggota by ID (returns Optional of Anggota)
    Optional<Entities.Anggota> findAnggotaById(String id);

    // Updates Buku (book) information
    void updateBuku(Entities.Buku buku);

    // Registers a member without the need for an ID parameter (ID is auto-generated)
    String registerMember(String nama, String alamat, String nomorTelepon);

    // Adds a new Buku (book) to the library system without requiring an ID parameter
    String addBook(String judul, String pengarang, String penerbit, int tahunTerbit, int stok);

    // Allows an Anggota to borrow a Buku (book)
    String borrowBook(String idAnggota, String idBuku);

    // Allows an Anggota to return a borrowed Buku (book)
    boolean returnBook(String idPeminjaman);
}