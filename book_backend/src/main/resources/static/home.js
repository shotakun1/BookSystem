Vue.createApp({
    data() {
        return {
            books: null,
            updatePopup: false,
            addPopup: false,
            updatedBook: {
                bookId: '',
                title: '',
                author: '',
                publisher: '',
                price: ''
            },
            addedBook: {
                bookId: '',
                title: '',
                author: '',
                publisher: '',
                price: ''
            }
        }
    },
    created() {
        axios.get('/allBooks').then(response => {
            this.books = response.data;
        }).catch(error => {
            console.error('Error fetching books', error);
        });
    },
    methods: {
        deleteBook(bookId) {
            if (confirm("是否删除")) {
                axios.delete('/deleteBook/' + bookId).then(() => {
                    alert("已删除");
                    window.location.reload();
                    console.log('Book deleted successfully');
                }).catch(error => {
                    console.error('Error deleting book', error);
                });
            }
        },
        openUpdatePopup(book) {
            this.updatedBook = {...book}; // Copy the book object to updateBook
            this.updatePopup = true; // Show the popup window
        },
        submitUpdate() {
            axios.put('/updateBook/' + this.updatedBook.bookId, this.updatedBook)
                .then(() => {
                    alert("已更新");

                    window.location.reload();
                    console.log('Book updated successfully');
                })
                .catch(error => {
                    console.error('Error updating book', error);
                });
        },
        openAddPopup() {
            this.addPopup = true;
        },
        submitAdd() {
            axios.post('/addBook', this.addedBook).then(() => {
                alert("已添加");

                window.location.reload();
                console.log('Book added successfully');
            }).catch(error => {
                console.error('Error adding book', error);
            });
        },
        closePopup() {
            this.updatePopup = false; // Close the popup window
            this.addPopup = false;
        }
    }
}).mount('#app')