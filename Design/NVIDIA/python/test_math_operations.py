import unittest

def add(a, b):
    return a + b

class TestMathOperations(unittest.TestCase):
    def test_add(self):
        result = add(2, 3)
        self.assertEqual(result, 5)

if __name__ == '__main__':
    unittest.main()
