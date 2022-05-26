==DSA Assignment==

Author: Samuel Wyatt (20555535)
Date: 19/05/2022

Description:
    The purpose of this program is to fufill the assignment requirements for the 2022 S1 DSA Assignment. The requirements describe 
    creating a program with the purpose of utilising a custom graph class to determine routes on a map, and rank them in different
    ways. An interactive menu will be provided to the user which will allow them to make modifications to all aspects of the graph.


Run Program:
1. Compile with javac *.java
2. Run with "java whereNow <flag>"
    <flag> == -i || -s


Interactive Mode: 
- Interactive mode is entered by running the program using the -i flag.
- This will provide the user with an interactive menu, where they can then make operations on the graph

Silent Mode:
- Silent mode is entered by running the program using the -s flag, and providing the below information in order.
    -infile = The file containing the map information .
    -journey = The file containing the journey information.
    -saveFile = A file which contains the routes, ranked in order of distance.
- Overall, running with silent mode will be as follows: java whereNow -s infile journey saveFile.
- A .txt file named routes.txt will be created, with the routes listed from shortest (distance) to longest.


