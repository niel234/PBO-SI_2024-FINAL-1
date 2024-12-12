package repository;

import Entity.Entities;
import repository.RepositoryInterface;

import java.util.*;

public class RepositoryImpl implements RepositoryInterface {
    private final List<Entities.Buku> daftarBuku = new ArrayList<>();
    private final List<Entities.Anggota> daftarAnggota = new ArrayList<>();
    private final List<Entities.Peminjaman> daftarPeminjaman = new ArrayList<>();

    @Override
    public List<Entities.Buku> getBuku() {
        return daftarBuku;
    }

    @Override
    public List<Entities.Anggota> getAnggota() {
        return daftarAnggota;
    }

    @Override
    public List<Entities.Peminjaman> getPeminjaman() {
        return daftarPeminjaman;
    }

    @Override
    public void addBuku(Entities.Buku buku) {
        daftarBuku.add(buku);
    }

    @Override
    public void addAnggota(Entities.Anggota anggota) {
        daftarAnggota.add(anggota);
    }

    @Override
    public void addPeminjaman(Entities.Peminjaman peminjaman) {
        daftarPeminjaman.add(peminjaman);
    }

    @Override
    public void removePeminjaman(String idPeminjaman) {
        daftarPeminjaman.removeIf(p -> p.getIdPeminjaman().equals(idPeminjaman));
    }

    @Override
    public Optional<Entities.Buku> findBukuById(String id) {
        return daftarBuku.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Entities.Peminjaman> findPeminjamanById(String id) {
        return daftarPeminjaman.stream().filter(p -> p.getIdPeminjaman().equals(id)).findFirst();
    }
}
