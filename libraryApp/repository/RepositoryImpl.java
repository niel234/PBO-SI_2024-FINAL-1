package libraryApp.repository;

import libraryApp.Entity.Entities;
import libraryApp.congfig.Database;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RepositoryImpl implements RepositoryInterface {

    private Database database; // Koneksi ke database

    public RepositoryImpl(Database database) {
        this.database = database;
    }


    @Override
    public List<Entities.Buku> getAllBuku() {
        List<Entities.Buku> bukuList = new ArrayList<>();
        String sqlStatement = "SELECT * FROM Buku";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                bukuList.add(new Entities.Buku(
                        resultSet.getString("id_buku"),
                        resultSet.getString("judul"),
                        resultSet.getString("pengarang"),
                        resultSet.getString("penerbit"),
                        resultSet.getInt("tahunTerbit"),
                        resultSet.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching books: " + e.getMessage());
        }
        return bukuList;
    }

    @Override
    public List<Entities.Anggota> getAllAnggota() {
        List<Entities.Anggota> anggotaList = new ArrayList<>();
        String sqlStatement = "SELECT * FROM Anggota";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                anggotaList.add(new Entities.Anggota(
                        resultSet.getString("id_anggota"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("nomorTelepon")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching members: " + e.getMessage());
        }
        return anggotaList;
    }

    @Override
    public List<Entities.Peminjaman> getAllPeminjaman() {
        List<Entities.Peminjaman> peminjamanList = new ArrayList<>();
        String sqlStatement = "SELECT * FROM Peminjaman";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                peminjamanList.add(new Entities.Peminjaman(
                        resultSet.getString("id_peminjaman"),
                        resultSet.getString("id_anggota"),
                        resultSet.getString("id_buku"),
                        resultSet.getDate("tanggal_peminjaman")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching borrowings: " + e.getMessage());
        }
        return peminjamanList;
    }

    @Override
    public void addBuku(Entities.Buku buku) {
        String sqlStatement = "INSERT INTO Buku (judul, pengarang, penerbit, tahunTerbit, stok) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, buku.getJudul());
            preparedStatement.setString(2, buku.getPengarang());
            preparedStatement.setString(3, buku.getPenerbit());
            preparedStatement.setInt(4, buku.getTahunTerbit());
            preparedStatement.setInt(5, buku.getStok());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    @Override
    public void addAnggota(Entities.Anggota anggota) {
        String sqlStatement = "INSERT INTO Anggota (nama, alamat, nomorTelepon) VALUES (?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, anggota.getNama());
            preparedStatement.setString(2, anggota.getAlamat());
            preparedStatement.setString(3, anggota.getNomorTelepon());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding member: " + e.getMessage());
        }
    }

    @Override
    public void addPeminjaman(Entities.Peminjaman peminjaman) {
        String sqlStatement = "INSERT INTO Peminjaman (id_anggota, id_buku, tanggal_peminjaman) VALUES (?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, peminjaman.getIdAnggota());
            preparedStatement.setString(2, peminjaman.getIdBuku());
            preparedStatement.setDate(3, new java.sql.Date(peminjaman.getTanggalPeminjaman().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding borrowing record: " + e.getMessage());
        }
    }

    @Override
    public Optional<Entities.Buku> findBukuById(String id) {
        String sqlStatement = "SELECT * FROM Buku WHERE id_buku = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Entities.Buku(
                            resultSet.getString("id_buku"),
                            resultSet.getString("judul"),
                            resultSet.getString("pengarang"),
                            resultSet.getString("penerbit"),
                            resultSet.getInt("tahunTerbit"),
                            resultSet.getInt("stok")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching book by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Entities.Peminjaman> findPeminjamanById(String id) {
        String sqlStatement = "SELECT * FROM Peminjaman WHERE id_peminjaman = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Entities.Peminjaman(
                            resultSet.getString("id_peminjaman"),
                            resultSet.getString("id_anggota"),
                            resultSet.getString("id_buku"),
                            resultSet.getDate("tanggal_peminjaman")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching borrowing record by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void removePeminjaman(String idPeminjaman) {
        String sqlStatement = "DELETE FROM Peminjaman WHERE id_peminjaman = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, idPeminjaman);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing borrowing record: " + e.getMessage());
        }
    }

    @Override
    public void updateBuku(Entities.Buku buku) {
        String sqlStatement = "UPDATE Buku SET judul = ?, pengarang = ?, penerbit = ?, tahunTerbit = ?, stok = ? WHERE id_buku = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, buku.getJudul());
            preparedStatement.setString(2, buku.getPengarang());
            preparedStatement.setString(3, buku.getPenerbit());
            preparedStatement.setInt(4, buku.getTahunTerbit());
            preparedStatement.setInt(5, buku.getStok());
            preparedStatement.setString(6, buku.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public Optional<Entities.Anggota> findAnggotaById(String id) {
        String sqlStatement = "SELECT * FROM Anggota WHERE id_anggota = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Entities.Anggota(
                            resultSet.getString("id_anggota"),
                            resultSet.getString("nama"),
                            resultSet.getString("alamat"),
                            resultSet.getString("nomorTelepon")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching member by ID: " + e.getMessage());
        }
        return Optional.empty();
    }
}