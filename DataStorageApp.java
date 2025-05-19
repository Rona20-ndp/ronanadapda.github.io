import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorageApp {

    // Item class representing data
    static class Item {
        private final int id;
        private String name;
        private int quantity;

        public Item(int id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("ID: %d | Nama: %s | Jumlah: %d", id, name, quantity);
        }
    }

    private final List<Item> items = new ArrayList<>();
    private int nextId = 1;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DataStorageApp app = new DataStorageApp();
        app.run();
    }

    private void run() {
        System.out.println("=== Aplikasi Penyimpanan Data ===");
        boolean exit = false;

        while (!exit) {
            showMenu();
            int choice = getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Tambah data");
        System.out.println("2. Lihat data");
        System.out.println("3. Ubah data");
        System.out.println("4. Hapus data");
        System.out.println("5. Keluar");
    }

    private void addItem() {
        System.out.println("\nTambah Data Baru");
        System.out.print("Masukkan nama: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Nama tidak boleh kosong.");
            return;
        }
        int quantity = getIntInput("Masukkan jumlah: ");
        if (quantity < 1) {
            System.out.println("Jumlah harus lebih dari 0.");
            return;
        }

        Item newItem = new Item(nextId++, name, quantity);
        items.add(newItem);
        System.out.println("Data berhasil ditambahkan: " + newItem);
    }

    private void viewItems() {
        System.out.println("\nData Barang:");
        if (items.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private void updateItem() {
        System.out.println("\nUbah Data");
        if (items.isEmpty()) {
            System.out.println("Belum ada data untuk diubah.");
            return;
        }
        int id = getIntInput("Masukkan ID data yang ingin diubah: ");
        Item item = findItemById(id);
        if (item == null) {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
            return;
        }
        System.out.println("Data saat ini: " + item);
        System.out.print("Masukkan nama baru (kosongkan jika tidak diubah): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            item.setName(newName);
        }
        String qtyInput;
        do {
            System.out.print("Masukkan jumlah baru (atau kosongkan jika tidak diubah): ");
            qtyInput = scanner.nextLine().trim();
            if (qtyInput.isEmpty()) {
                break;
            }
            try {
                int newQty = Integer.parseInt(qtyInput);
                if (newQty > 0) {
                    item.setQuantity(newQty);
                    break;
                } else {
                    System.out.println("Jumlah harus lebih dari 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        } while (true);

        System.out.println("Data berhasil diperbarui: " + item);
    }

    private void deleteItem() {
        System.out.println("\nHapus Data");
        if (items.isEmpty()) {
            System.out.println("Belum ada data untuk dihapus.");
            return;
        }
        int id = getIntInput("Masukkan ID data yang ingin dihapus: ");
        Item item = findItemById(id);
        if (item == null) {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
            return;
        }
        System.out.print("Yakin ingin menghapus data ini? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            items.remove(item);
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    private Item findItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private int getIntInput(String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                input = Integer.parseInt(line);
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }
}

