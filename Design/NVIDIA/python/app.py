from flask import Flask, jsonify, request
import uuid

app = Flask(__name__)

# Sample data
books = [
    {"id": 1, "title": "To Kill a Mockingbird", "author": "Harper Lee"},
    {"id": 2, "title": "1984", "author": "George Orwell"},
    {"id": 3, "title": "The Great Gatsby", "author": "F. Scott Fitzgerald"}
]

# CREATE: Add a new book
@app.route('/books', methods=['POST'])
def create_book():
    new_book = request.get_json()  # Get the JSON data from the request body
    new_book['id'] = str(uuid.uuid4())  # Generate a random UUID
    books.append(new_book)  # Add the new book to the list
    return jsonify(new_book), 201  # Return the new book and HTTP status code 201

# READ: Get all books
@app.route('/books', methods=['GET'])
def get_books():
    return jsonify(books), 200  # Return the list of books with HTTP status code 200

# READ: Get a single book by ID
@app.route('/books/<int:id>', methods=['GET'])
def get_book(id):
    book = next((book for book in books if book["id"] == id), None)  # Find book by id
    if book:
        return jsonify(book), 200  # Return the book if found
    else:
        return jsonify({"message": "Book not found"}), 404  # Return 404 if book not found

# UPDATE: Update an existing book by ID
@app.route('/books/<int:id>', methods=['PUT'])
def update_book(id):
    book = next((book for book in books if book["id"] == id), None)
    if book:
        updated_data = request.get_json()  # Get the updated data from the request body
        book.update(updated_data)  # Update the book's data
        return jsonify(book), 200  # Return the updated book
    else:
        return jsonify({"message": "Book not found"}), 404  # Return 404 if book not found

# DELETE: Delete a book by ID
@app.route('/books/<int:id>', methods=['DELETE'])
def delete_book(id):
    book = next((book for book in books if book["id"] == id), None)
    if book:
        books.remove(book)  # Remove the book from the list
        return jsonify({"message": "Book deleted successfully"}), 200  # Return success message
    else:
        return jsonify({"message": "Book not found"}), 404  # Return 404 if book not found

# Run the app
if __name__ == '__main__':
    app.run(debug=True)
