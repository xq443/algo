import pytest
import requests

# Fixture example
@pytest.fixture
def sample_data():
    return [1, 2, 3]

# Test using fixture
def test_sum(sample_data):
    # This test uses the 'sample_data' fixture to test the sum of the list [1, 2, 3]
    assert sum(sample_data) == 6  # The sum should be 6


# Function to test division
def divide(a, b):
    if b == 0:
        raise ValueError("Cannot divide by zero")  # Raise an error if division by zero
    return a / b

# Test for division by zero
def test_divide_by_zero():
    # This test checks if a ValueError is raised when trying to divide by zero
    with pytest.raises(ValueError):
        divide(1, 0)  # This should raise a ValueError


# Parametrized test using @pytest.mark.parametrize
@pytest.mark.parametrize("a, b, expected", [(1, 2, 3), (2, 3, 5), (5, 5, 10)])
def test_add(a, b, expected):
    # Parametrized test to check addition of pairs of numbers
    assert a + b == expected  # This should assert that the sum matches the expected result


# Class-based test example
class TestMath:

    def test_addition(self):
        # Testing basic addition
        assert 1 + 2 == 3

    def test_subtraction(self):
        # Testing basic subtraction
        assert 2 - 1 == 1


# Test skipped using @pytest.mark.skip
@pytest.mark.skip(reason="Test is not ready yet")
def test_not_ready():
    # This test will be skipped because it's marked as 'skip'
    assert False  # This will never be executed


# Test expected to fail using @pytest.mark.xfail
@pytest.mark.xfail(reason="Known bug")
def test_known_bug():
    # This test is marked to indicate it's expected to fail (known bug)
    assert False  # This will fail, but pytest will mark it as 'expected failure'


# Function that makes an HTTP request
def get_status(url):
    response = requests.get(url)  # Send a GET request
    return response.status_code  # Return the status code of the response

# Test to mock 'requests.get' function
def test_get_status(mocker):
    # This test uses 'pytest-mock' to mock 'requests.get' behavior
    mock_response = mocker.patch('requests.get')  # Mock the 'requests.get' call
    mock_response.return_value.status_code = 200  # Mock the return value of the GET request

    result = get_status('http://example.com')  # Call the function that makes the GET request
    assert result == 200  # Assert that the mocked status code is 200

# Test capturing printed output using capfd
def test_print_output(capfd):
    print("Hello, world!")  # This will print to stdout
    captured = capfd.readouterr()  # Capture the output
    assert captured.out == "Hello, world!\n"  # Assert that the printed output is correct
