Algorithm-implementations
=========================

These programs simulate node-link based connectivity, used for studying computer networks.
In such cases it is important to find the shortest path between nodes, in order to communicate data efficiently.

atn1.java:
Dijkstra's algorithm is an important corner-stone which helps calculate the shortest path between nodes.
The first program emulates it.

• As input, it receives the number of nodes (N), the traffic demand values (bij) between pairs of nodes, and the unit cost values for the potential links (aij).
• As output, the program generates a network topology, with capac- ities assigned to the links, according to the studied model, using the shortest path based fast solution method (see at the end of the cited lecture note). The program also computes the total cost of the designed network.
