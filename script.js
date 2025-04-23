// Ambil data dari localStorage atau inisialisasi array kosong
let items = JSON.parse(localStorage.getItem('items')) || [];

// Menampilkan item saat halaman dimuat
displayItems();

// Menangani pengiriman form
document.getElementById('itemForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const itemName = document.getElementById('itemName').value;

    // Tambahkan item ke array
    items.push(itemName);
    localStorage.setItem('items', JSON.stringify(items)); // Simpan ke localStorage
    displayItems(); // Tampilkan item
    this.reset(); // Reset form
});

// Fungsi untuk menampilkan item
function displayItems() {
    const itemList = document.getElementById('itemList');
    itemList.innerHTML = ''; // Kosongkan daftar item

    items.forEach((item, index) => {
        const li = document.createElement('li');
        li.textContent = item;

        // Tombol hapus
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Hapus';
        deleteButton.onclick = function() {
            deleteItem(index);
        };

        li.appendChild(deleteButton);
        itemList.appendChild(li);
    });
}

// Fungsi untuk menghapus item
function deleteItem(index) {
    items.splice(index, 1); // Hapus item dari array
    localStorage.setItem('items', JSON.stringify(items)); // Simpan perubahan ke localStorage
    displayItems(); // Tampilkan item
}