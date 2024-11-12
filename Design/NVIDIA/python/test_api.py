import pytest
from flask import Flask, jsonify, request
import uuid

# Import the app from the main file (assuming the Flask app is in a file named `app.py`)
from app import app, books  # Assuming app.py contains the Flask app

@pytest.fixture
def client():
    # Setup the test client
    with app.test_client() as client:
        yield client

@pytest.fixture
def reset_books():
    # Reset the books list before each test
    books.clear()
    books.append({"id": 1, "title": "To Kill a Mockingbird", "author": "Harper Lee"})
    books.append({"id": 2, "title": "1984", "author": "George Orwell"})
    books.append({"id": 3, "title": "The Great Gatsby", "author": "F. Scott Fitzgerald"})

# Test: Get all books
def test_get_books(client, reset_books):
    response = client.get('/books')
    assert response.status_code == 200
    assert len(response.json) == 3  # There should be 3 books initially
    assert 'title' in response.json[0]  # Check if title is in the response

# Test: Get a single book by ID (Valid ID)
def test_get_book_valid_id(client, reset_books):
    response = client.get('/books/2')
    assert response.status_code == 200
    assert response.json['title'] == "1984"

# Test: Get a single book by ID (Invalid ID)
def test_get_book_invalid_id(client, reset_books):
    response = client.get('/books/999')
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
    updated_book = {
        "title": "Updated Title",
        "author": "Updated Author"
    }
    response = client.put('/books/1', json=updated_book)
    assert response.status_code == 200
    assert response.json['title'] == "Updated Title"

# Test: Update an existing book (Invalid ID)
def test_update_book_invalid_id(client, reset_books):
    updated_book = {
        "title": "Updated Title",
        "author": "Updated Author"
    }
    response = client.put('/books/999', json=updated_book)
    assert response.status_code == 404
    assert response.json['message'] == "Book not found"

# Test: Delete a book (Valid ID)
def test_delete_book_valid_id(client, reset_books):
    response = client.delete('/books/1')
    assert response.status_code == 200
    assert response.json['message'] == "Book deleted successfully"
    # Verify the book is deleted
    response = client.get('/books/1')
    assert response.status_code == 404

# Test: Delete a book (Invalid ID)
def test_delete_book_invalid_id(client, reset_books):
    response = client.delete('/books/999')
    assert response.status_code == 404
    assert response.json['message'] == "Book not found"
