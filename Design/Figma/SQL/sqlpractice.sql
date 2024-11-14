# subquery

SELECT e.name, e.salary, e.department_id
FROM employees e
WHERE e.salary > (
    SELECT AVG(salary)
    FROM employees
    WHERE department_id = e.department_id
);


# Window Function Example (ROW_NUMBER): Unique numbers, no gaps 1 2 3
SELECT
    e.name,
    e.department_id,
    e.salary,
    ROW_NUMBER() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) AS salary_rank
FROM employees e;

# Window Function Example (RANK) : Same rank for ties; gaps left 1 1 3; dense rank: 1 1 2
SELECT
    e.name,
    e.department_id,
    e.salary,
    RANK() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) AS salary_rank
FROM employees e;

SELECT
    e.name,
    e.department_id,
    e.salary,
    SUM(e.salary) OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) AS cumulative_salary
FROM employees e;

# Using EXISTS with a Subquery
## List departments that have employees with a salary greater than $100,000.
SELECT d.department_id, d.department_name
  FROM departments d
  WHERE EXISTS (
      SELECT 1
      FROM employees e
      WHERE e.department_id = d.department_id
        AND e.salary > 100000
  );

# Aggregate Function with HAVING and Subquery
SELECT e.department_id
FROM employees e
WHERE e.salary > (
    SELECT AVG(salary)
    FROM employees
    WHERE department_id = e.department_id
)
GROUP BY e.department_id
HAVING COUNT(*) > 5;

# Using CASE with Window Function
SELECT
      e.name,
      e.salary,
      e.department_id,
      CASE
          WHEN e.salary > AVG(e.salary) OVER (PARTITION BY e.department_id) THEN 'High'
          WHEN e.salary = AVG(e.salary) OVER (PARTITION BY e.department_id) THEN 'Medium'
          ELSE 'Low'
          END AS salary_class,
      RANK() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) AS salary_rank
  FROM employees e;

# ddl
CREATE TABLE Employees (
     EmployeeID INT PRIMARY KEY,
     FirstName VARCHAR(50),
     LastName VARCHAR(50),
     BirthDate DATE,
     HireDate DATE,
     Salary DECIMAL(10, 2),
     DepartmentID INT,
     FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID)
  );