# Page Replacement/ Caching Simulation Assignment
#### By Jinming Nian


## How to Start the Simulation
Both programs are coded in java and the source code have file names "LRU.java" and "SecondChance.java" <br/>
Here are the steps to start both programs:
- In terminal, go to the directory where the source code and test files are stored
- Type ``` javac LRU.java``` and ``` javac SecondChance.java ``` separately to create .class files that can be executed
- Here is an example to run the simulation:  ``` cat test.txt | java LRU 4 ```. Replace ``` test.txt ``` with 
the file you want to feed into the simulation. Replace ```LRU``` with ```SecondChance``` if you want to run second chance. ```4``` is the initialization of cache size, change it to any positive integer you want. <br/>

The simulation will print 3 things to your terminal: cache size, page numbers that caused page fault and a page fault summary.

### How to use the random test generator
If you would like, you can use "GenerateRandomTest.java" to generate tests. 
- Type ```javac GenerateRandomTest.java``` then hit enter.
- Type ```java GenerateRandomTest``` then hit enter.
- Follow the program to input max page number value and the test size.
## Logic behind the program
We assume the input can be dirty, so in both programs, we do a cleanup and only keep positive integers after receiving inputs.
### LRU:
The LRU program is implemented with a FIFO queue. The queue represents our cache, and the pages are elements to be added into the queue.
<br/>
We consider two scenarios: The cache contains current page, or it doesn't. When cache contains current page, 
we need to update it to be the most recently used page. We do so by pop the queue one by one and add to a buffer queue until 
we find the current page number. We then pop the queue again and store this value as most recently used. Then we continue 
put everything remaining in the queue to the buffer, and put everything in the buffer back to the queue to maintain the order.
In the end we push the most recently used page into the queue.<br/>
When cache doesn't have current page, page fault happens, and we just push the new page in, and pop the least recently used page out.

### Second Chance:
The Second Chance program is also implemented with a FIFO queue. <br/>
We first create an integer array filled with 0 to represent all the reference bits. The index of this array represent page numbers, and the values on each index can either be 0 or 1.
<br/>
We consider two scenarios: When cache contains current page, we simply update its reference bit to 1. When cache doesn't contain
current page number, we pop the queue one by one and add to a buffer queue until we find a page number with reference bit 0 or until the queue is empty.
During this process we change the reference bit of each popped number to 0. Now if the queue is empty, it means all the numbers
previously in the cache have reference bit of 1. We have turned all of them to 0, so we just put every thing in the buffer back to the queue,
and pop the queue to get rid of the least recently used page. Or if the queue is not empty which means we have found a page with
reference bit 0, we pop that element and go on putting the rest of numbers in queue to the buffer, and then put everything in the buffer back to the queue 
to maintain relative order.
Finally, we add the current page number to the cache queue.
<br/>
When cache doesn't have current page, page fault happens.

## Ways of improvement
### Optimize pop and push operations
Both programs are based on FIFO queue: based on Java's ArrayDeque implementation.
With that being said, whenever we pop or push a number to the queue, Java copies everything into a new array. This shifting
operation is very time-consuming so a major optimization would be using a linked list implementation for the queue. <br/>
Although linked lists have O(n) search time and require more overhead space,
we can achieve O(1) time in every single pop and push operations. This will decrease the time by a great deal because
searching only happens 1 time for each page request, while the pop and push operations can happen up to n times for each page request.
### Optimize searching
Linked list is faster for our problem, so we should use it if space isn't an issue. Therefore, the second possible optimization is for searching. 
To improve searching, we could either do binary search or hash. Binary search wouldn't work because the queue won't always be sorted. 
So we take a look at hashing. We can maintain a hash table that stores all the page numbers and their addresses. Whenever we see a new page, we add its value and address to the hash table, 
and whenever we want to remove a page from cache, we just set the corresponding page number's address to null. <br/>
This way, we sacrifice space for time, but we boosted searching time from O(n) to O(1). <br/><br/>
A further optimization would be storing the reference bits in the hash table for second chance algorithm.
This could be very beneficial if there are extreme values in the input page numbers. However, I don't think Java has implemented hash tables that
store three aspects, so we might need to write our own hash table for this. Changing reference bits to boolean values instead of 
integer values can also optimize space a little. 

## Sample miss-rate
All the tests used random numbers generated by Java's util library. <br/>
All three graphs in the link below are generated from a same set of test files of sizes: 10, 20, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500
<br/>
These test files are not submitted, but I do have them just in case if anyone wants to reproduce my sample results. 
<br/>
<br/>
See the plots in this link: https://docs.google.com/spreadsheets/d/1aBKfmgQQLG6aVKdBbjViQDSDAKnb2OtCli2QQ4ukbN0/edit?usp=sharing

