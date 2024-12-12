package repository;

import Entity.Entities;
import java.util.*;

public interface RepositoryInterface {
    List<Entities.Buku> getBuku();
    List<Entities.Anggota> getAnggota();
    List<Entities.Peminjaman> getPeminjaman();

    void addBuku(Entities.Buku buku);
    void addAnggota(Entities.Anggota anggota);
    void addPeminjaman(Entities.Peminjaman peminjaman);
    void removePeminjaman(String idPeminjaman);

    Optional<Entities.Buku> findBukuById(String id);
    Optional<Entities.Peminjaman> findPeminjamanById(String id);
}
