import pytest
from flask import Flask, jsonify, request
import uuid

# Import the app, Book, and BookStore from the main file (assuming the Flask app is in a file named `app.py`)
from app import app, book_store, Book  # Corrected 'book' to 'Book'

@pytest.fixture
def client():
    # Setup the test client
    with app.test_client() as client:
        yield client

@pytest.fixture
def reset_books():
    # Reset the books list before each test
    book_store.books.clear()
    book_store.books.append(Book("To Kill a Mockingbird", "Harper Lee"))
    book_store.books.append(Book("1984", "George Orwell"))
    book_store.books.append(Book("The Great Gatsby", "F. Scott Fitzgerald"))

# Test: Get all books
def test_get_books(client, reset_books):
    response = client.get('/books')
    assert response.status_code == 200
    assert len(response.json) == 3  # There should be 3 books initially
    assert 'title' in response.json[0]  # Check if title is in the response

# Test: Get a single book by ID (Valid ID)
def test_get_book_valid_id(client, reset_books):
    book_id = book_store.books[1].id  # Get the ID of the second book
    response = client.get(f'/books/{book_id}')
    assert response.status_code == 200
    assert response.json['title'] == "1984"

# Test: Get a single book by ID (Invalid ID)
def test_get_book_invalid_id(client, reset_books):
    response = client.get('/books/invalid-id')
    assert response.status_code == 404
    assert response.json['message'] == "Book not found"

# Test: Create a new book
def test_create_book(client, reset_books):
    new_book = {
        "title": "New Book",
        "author": "New Author"
    }
    response = client.post('/books', json=new_book)
    assert response.status_code == 201
    assert 'id' in response.json  # Ensure the new book has an ID
    assert response.json['title'] == "New Book"

# Test: Update an existing book (Valid ID)
def test_update_book_valid_id(client, reset_books):
    book_id = book_store.books[0].id  # Get the ID of the first book
    updated_book = {
        "title": "Updated Title",
        "author": "Updated Author"
    }
    response = client.put(f'/books/{book_id}', json=updated_book)
    assert response.status_code == 200
    assert response.json['title'] == "Updated Title"

# Test: Update an existing book (Invalid ID)
def test_update_book_invalid_id(client, reset_books):
    updated_book = {
        "title": "Updated Title",
        "author": "Updated Author"
    }
    response = client.put('/books/invalid-id', json=updated_book)
    assert response.status_code == 404
    assert response.json['message'] == "Book not found"

# Test: Delete a book (Valid ID)
def test_delete_book_valid_id(client, reset_books):
    book_id = book_store.books[0].id  # Get the ID of the first book
    response = client.delete(f'/books/{book_id}')
    assert response.status_code == 200
    assert response.json['message'] == "Book deleted successfully"
    # Verify the book is deleted
    response = client.get(f'/books/{book_id}')
    assert response.status_code == 404

# Test: Delete a book (Invalid ID)
def test_delete_book_invalid_id(client, reset_books):
    response = client.delete('/books/invalid-id')
    assert response.status_code == 404
    assert response.json['message'] == "Book not found"
