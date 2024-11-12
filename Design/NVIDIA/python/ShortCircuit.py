class BoolExpr:
    """Base class for a boolean expression."""

class BoolConst(BoolExpr):
    """Boolean constant expression."""
    def __init__(self, constant):
        self.constant = constant

    def evaluate(self, context=None):
        return self.constant


class BoolVar(BoolExpr):
    """Boolean variable expression."""
    def __init__(self, variable):
        self.variable = variable

    def evaluate(self, context):
        # Use the context (a dictionary) to look up the value of the variable
        return context.get(self.variable, False)


class And(BoolExpr): # BoolConst is a subclass of BoolExpr
    """Logical AND operation that supports short-circuit evaluation."""
    def __init__(self, expressions):
        self.expressions = expressions

    def evaluate(self, context):
        # Evaluate each expression, short-circuit if one is False
        for expr in self.expressions:
            if not expr.evaluate(context):
                return False
        return True


class Or(BoolExpr):
    """Logical OR operation that supports short-circuit evaluation."""
    def __init__(self, expressions):
        self.expressions = expressions

    def evaluate(self, context):
        # Evaluate each expression, short-circuit if one is True
        for expr in self.expressions:
            if expr.evaluate(context):
                return True
        return False

class one(BoolExpr):
    def __init__(self, expressions):
        self.expressions = expressions
    

# Example usage

# Define variables a, b and context where a = True, b = False
a = BoolVar("a")
b = BoolVar("b")
context = {"a": True, "b": False}

# Define (a && b) && True
expr = And([And([a, b]), BoolConst(True)])

# Evaluate the expression with short-circuiting
print(expr.evaluate(context))  # Output: False
