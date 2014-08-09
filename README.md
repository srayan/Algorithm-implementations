Algorithm-implementations
=========================

atn1.java
These programs simulate node-link based connectivity, used for studying computer networks.
In such cases it is important to find the shortest path between nodes, in order to communicate data efficiently.

atn1.java:
Dijkstra's algorithm is an important corner-stone which helps calculate the shortest path between nodes.
The first program emulates it.

• As input, it receives the number of nodes (N), the traffic demand values (bij) between pairs of nodes, and the unit cost values for the potential links (aij).
• As output, the program generates a network topology, with capac- ities assigned to the links, according to the studied model, using the shortest path based fast solution method (see at the end of the cited lecture note). The program also computes the total cost of the designed network.


project2.java
This project is an experimental study to test the reliability of a network based on individual link reliability.
We know that network reliability is determined by several factors. At any given point of time individual link reliabilities play a pivotal role in the specific situations described below:

Network topology - A complete undirected graph on n=5 nodes. Here every node is connected to the every other one. Resultantly there are m=10 edges, which are the links of the network.

Components that may fail - Although the links of the network might fail, the nodes are always up. Thus the reliability of each link is p, which is the very same for every other link. This parameter p will be taking different values during the experiments.
Reliability configuration - The system will be considered operational if and only if the network topology is connected.

It is necessary to develop an algorithm that will compute the reliability of a given network topology using the process of Exhaustive Enumeration.

The Exhaustive enumeration method involves listing all the possible states of the system and assign an up and down system condition to each state. The network reliability can be obtained by sum- ming up the probability of the up states.
Hence for every link (with link reliability = p) we will calculate the network reliability for varying values of p and graphically plot them to determine how they vary. The reliability is based on the probability of each link associated with the topology of the network. 

Secondly we fix the value of p at 0.95 and run the same experiment by randomly flipping some possible combinations of the component states and monitor how the network reliability is getting affected. In order to reduce randomness we perform the experiment several times, which averages out the results for any particular case. Thus we choose N states out of K states, the values of which are changed for each link and the reliability of the network re-determined.

Algorithm - First generate the primary solution set, then generate the next set and check if this candi- date is a solution that is appropriate to the application. The process ends when a null is re- turned when there are no more new solution sets that can be generated.!
