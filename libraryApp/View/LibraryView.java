package libraryApp.View;

import libraryApp.Service.LibraryServiceInterface;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LibraryView implements LibraryViewInterface {
    private final LibraryServiceInterface service;
    private final Scanner scanner = new Scanner(System.in);

    public LibraryView(LibraryServiceInterface service) {
        this.service = service;
    }

    @Override
    public void showMenu() {
        int pilihan;
        do {
            showMainMenu();
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Menghilangkan newline

            switch (pilihan) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private void showMainMenu() {
        System.out.println("\n=== Sistem Perpustakaan ===");
        System.out.println("1. Registrasi Anggota");
        System.out.println("2. Tambah Buku");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Kembalikan Buku");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private void registerMember() {
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Nomor Telepon: ");
        String nomorTelepon = scanner.nextLine();
        String id = service.registerMember(nama, alamat, nomorTelepon);
        System.out.println("Anggota berhasil didaftarkan dengan ID: " + id);
    }

    private void addBook() {
        System.out.print("Judul: ");
        String judul = scanner.nextLine();
        System.out.print("Pengarang: ");
        String pengarang = scanner.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = scanner.nextLine();
        System.out.print("Tahun Terbit: ");
        int tahunTerbit = scanner.nextInt();
        System.out.print("Jumlah Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine();  // Clear the newline
        String id = service.addBook(judul, pengarang, penerbit, tahunTerbit, stok);
        System.out.println("Buku berhasil ditambahkan dengan ID: " + id);
    }

    private void borrowBook() {
        System.out.print("ID Anggota: ");
        String idAnggota = scanner.nextLine();
        System.out.print("ID Buku: ");
        String idBuku = scanner.nextLine();
        String idPeminjaman = service.borrowBook(idAnggota, idBuku);
        if (idPeminjaman != null) {
            System.out.println("Buku berhasil dipinjam dengan ID Peminjaman: " + idPeminjaman);
        } else {
            System.out.println("Gagal meminjam buku.");
        }
    }

    private void returnBook() {
        System.out.print("ID Peminjaman: ");
        String idPeminjaman = scanner.nextLine();
        if (service.returnBook(idPeminjaman)) {
            System.out.println("Buku berhasil dikembalikan.");
        } else {
            System.out.println("ID Peminjaman tidak ditemukan.");
        }
    }
}