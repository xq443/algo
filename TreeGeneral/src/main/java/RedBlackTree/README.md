Step 1: 
- choose the node to be deleted like in BST


Step 2: perform the suitable cases

- case 1: if the node to be deleted is red, just delete it & exit
- case 2: 
- case 3: if the DN sibling is black, and both of DN nephew are also black:
-- remove DN
-- make sibling read
-- add black to the parent: if parent was originally rad, make parent black; else, 