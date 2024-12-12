package View;

import Service.LibraryServiceInterface;

import java.util.Scanner;

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
            System.out.println("\n=== Sistem Perpustakaan ===");
            System.out.println("1. Registrasi Anggota");
            System.out.println("2. Tambah Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Menghilangkan newline

            switch (pilihan) {
                case 1 -> registerMember();
                case 2 -> addBook();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> System.out.println("Terima kasih!");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
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
        scanner.nextLine();
        String id = service.addBook(judul, pengarang, penerbit, tahunTerbit, stok);
        System.out.println("Buku berhasil ditambahkan dengan ID: " + id);
    }

    private void borrowBook() {
        System.out.print("ID Anggota: ");
        String idAnggota = scanner.nextLine();
        System.out.print("ID Buku: ");
        String idBuku = scanner.nextLine();
        if (service.borrowBook(idAnggota, idBuku)) {
            System.out.println("Buku berhasil dipinjam.");
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