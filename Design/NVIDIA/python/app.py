from flask import Flask, jsonify, request
import uuid

class Book:
    def __init__(self, title, author):
        self.id = str(uuid.uuid4())
        self.title = title
        self.author = author

class BookStore:
    def __init__(self):
        self.books = [
            Book("To Kill a Mockingbird", "Harper Lee"),
            Book("1984", "George Orwell"),
            Book("The Great Gatsby", "F. Scott Fitzgerald")
        ]

    def add_book(self, title, author):
        new_book = Book(title, author)
        self.books.append(new_book)
        return new_book

    def get_books(self):
        return self.books

    def get_book(self, book_id):
        return next((book for book in self.books if book.id == book_id), None)

    def update_book(self, book_id, title, author):
        book = self.get_book(book_id)
        if book:
            book.title = title
            book.author = author
        return book

    def delete_book(self, book_id):
        book = self.get_book(book_id)
        if book:
            self.books.remove(book)
        return book

app = Flask(__name__)
book_store = BookStore()

@app.route('/books', methods=['POST'])
def create_book():
    data = request.get_json()
    new_book = book_store.add_book(data['title'], data['author'])
    return jsonify({"id": new_book.id, "title": new_book.title, "author": new_book.author}), 201

@app.route('/books', methods=['GET'])
def get_books():
    books = book_store.get_books()
    return jsonify([{"id": book.id, "title": book.title, "author": book.author} for book in books]), 200

@app.route('/books/<string:book_id>', methods=['GET'])
def get_book(book_id):
    book = book_store.get_book(book_id)
    if book:
        return jsonify({"id": book.id, "title": book.title, "author": book.author}), 200
    else:
        return jsonify({"message": "Book not found"}), 404

@app.route('/books/<string:book_id>', methods=['PUT'])
def update_book(book_id):
    data = request.get_json()
    book = book_store.update_book(book_id, data['title'], data['author'])
    if book:
        return jsonify({"id": book.id, "title": book.title, "author": book.author}), 200
    else:
        return jsonify({"message": "Book not found"}), 404

@app.route('/books/<string:book_id>', methods=['DELETE'])
def delete_book(book_id):
    book = book_store.delete_book(book_id)
    if book:
        return jsonify({"message": "Book deleted successfully"}), 200
    else:
        return jsonify({"message": "Book not found"}), 404

if __name__ == '__main__':
    app.run(debug=True)

