let books = [];

document.getElementById('bookForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const title = document.getElementById('bookTitle').value;
    const author = document.getElementById('bookAuthor').value;

    const book = {
        title: title,
        author: author
    };

    books.push(book);
    displayBooks();
    this.reset();
});

function displayBooks() {
    const bookList = document.getElementById('bookList');
    bookList.innerHTML = '';

    books.forEach((book, index) => {
        const li = document.createElement('li');
        li.textContent = `${book.title} oleh ${book.author}`;
        
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Hapus';
        deleteButton.onclick = function() {
            deleteBook(index);
        };

        li.appendChild(deleteButton);
        bookList.appendChild(li);
    });
}

function deleteBook(index) {
    books.splice(index, 1);
    displayBooks();
}
