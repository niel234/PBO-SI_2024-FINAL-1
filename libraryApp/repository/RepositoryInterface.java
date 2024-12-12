package libraryApp.repository;

import libraryApp.Entity.Entities;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface {
    List<Entities.Buku> getAllBuku();
    List<Entities.Anggota> getAllAnggota();
    List<Entities.Peminjaman> getAllPeminjaman();

    void addBuku(Entities.Buku buku);
    void addAnggota(Entities.Anggota anggota);
    void addPeminjaman(Entities.Peminjaman peminjaman);

    Optional<Entities.Buku> findBukuById(String id);
    Optional<Entities.Peminjaman> findPeminjamanById(String id);
    void removePeminjaman(String idPeminjaman);

    void updateBuku(Entities.Buku buku);

    Optional<Entities.Anggota> findAnggotaById(String id);
}