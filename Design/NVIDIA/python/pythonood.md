# Python OOP

## 1. Inheritance / Composition，并且举例

**Inheritance** allows a class (child class) to inherit attributes and methods from another class (parent class). This promotes code reuse and establishes a relationship between classes.

**Example:**

```python
class Animal:
    def speak(self):
        return "Animal sound"

class Dog(Animal):
    def speak(self):
        return "Bark"

dog = Dog()
print(dog.speak())  # Output: Bark
```
