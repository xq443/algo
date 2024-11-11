1. mkdir docs
2. cd docs
3. sphinx-quickstart
4. config 
```import os
import sys
sys.path.insert(0, os.path.abspath('../src'))
```
5. create doc files
```
Welcome to My Package's documentation!
=====================================

.. toctree::
   :maxdepth: 2
   :caption: Contents:

   modules

.. automodule:: my_package.example
   :members:
   :undoc-members:
   :show-inheritance:
```
6. make clean && make html
7. open build/html/index.html
