# lab-5-maps-hashing

##Question 1
    
    i feel as though the question is a bit ambiguous when it refers to the 'state'....i dont really know what that is referring to...just an explination in the feedback would be nice
    
 ##Question 2
   
    you would want to use a smaller load factor (like 0.3) for an open hashing based map because unlike the chaining method you do not create additional space when a collision occurs so you end up adding an entry to the table instead increasing its size therfore increasing 
    its chance of colliding with more elements. To make sure the number of collisions is not very high we assign the load factor to be relativly small. Like previously stated, the chaining method does not take up additional space in the hash table when a collision occurs
    therefor its load factor does not have to be as low as linear probing and such because the table wont fill up as fast.
    
 
 ##Question 3
 
    double hashing minimizes the collisions that occur that is often associated with linear probing. It also makes it faster look up time if you have entry values with the same key
    
    
See lab document: https://uwoece-se2205b-2017.github.io/labs/05-maps-hash
