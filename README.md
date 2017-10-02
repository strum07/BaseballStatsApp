# N-Way Set-Associative Cache : A Java Implementation
As per definition, A set-associative scheme is a hybrid between a fully associative NAssociativeCache, and direct mapped NAssociativeCache. It's considered a reasonable compromise between the complex hardware needed for fully associative caches (which requires parallel searches of all slots), and the simplistic direct-mapped scheme, which may cause collisions of addresses to the same slot (similar to collisions in a hash table).

This project is simple simulation of how this scheme of NAssociativeCache works as per the given requirements written completely in Java.


## Requirements
1) The NAssociativeCache itself is in the memory.(i.e. It does not communicate with a backing store)
2) The client interface should be type-safe for keys and values to be of an arbitrary type(e.g.,strings , integers, classes, etc.). For a given instance of a NAssociativeCache all keys must be the same type and  values must be the same type.
3) Design the interface as a library to be distributed to clients. Assume that the client Does not have source code to your library.
4) Provide LRU and MRU replacement algorithms.
5) Provide a way for any alternative algorithm to be implemented by the client and used by the NAssociativeCache.

Example Use Case: As an in-memory NAssociativeCache on an application server, storing data associated with user id, in order to avoid database dip for every request.


## Requirements Revisited: Understanding the Requirements(Rather, my interpretation of the requirements)
1) Cache exists independently and Does not communicate with memory in case of a request MISS: It Does not insert the value being requested from memory onto the NAssociativeCache if it already Does not exist in the NAssociativeCache. However, we can still write/set into the NAssociativeCache explicitly. Note: I did clarify this with Sadek.
2) Type safety needs to be ensured, hence use of Generics is recommended.
3) Since the library has to be distributed to clients who do not have access to the source, the entire package can be bundled into a JAR file which can be used as a project dependency on the client side to use the library. Just like an offline API.
4) Eviction strategies commonly seen on NAssociativeCache, like LRU and MRU needs to be implemented. Both these strategies can be used by the client by importing the right package. See install notes.
5) An alternate replacement algorithm to be implemented by client and used by NAssociativeCache is made possible by having abstract class implementing an abstract method. See install notes for more details.

## Path to the Solution

###Phase 1: Requirements Analysis
Apart from the requirements given, I had to do a lot of reading on understanding how a NAssociativeCache works and what each of the algorithms meant to gain enough domain knowledge to start the work.
Had to revisit many of the concepts in computer architecture course! :D This phase completely deals with the research I had to do and gaining more insight about task in hand.

#### Adressing Formats
I did a little more reading on what addressing formats meant. I clarified if I had to adhere to a specific format, but I was given the freedom to design it the way I wanted keeping the usability, readability, flexibility and efficiency in mind.

#### **Operations on a Cache**
I understood that any NAssociativeCache had to perform a set of operations such as
1) set()
2) get()
3) remove/delete()
4) print()

I was told that the first three operations are the minimum set of required methods.

####**Constraints in the system**
Constraints help us define a boundary to the system we are designing, hence I was curious to know if there were any that I should be aware of.
Like the MAX size of Cache, MAX Number of Sets, etc.
The choice was left to me to include them if I wanted, but not necessary. I decided not to **predefine** any constraints.

####**Consumption of the interface**
As I was thinking as an end user who would consume this interface, I wanted to know how to more about the specifics. I was informed that it would be consumed by any code or application
which would need to NAssociativeCache typed data.

####**Comprehending LRU and MRU algorithms**
These algorithms were the two factors(Apart from the Custom implementation of the client) which would dictate how a NAssociativeCache should behave. The more I read about it,
it became evident that they were very similar to each other and they differ by how they *set* items into the NAssociativeCache.

###Phase 2: Designing a solution
This phase deals with identifying the layout and a design of how my final implementation should look and behave.

####**Understanding the NAssociativeCache operations with respect to the MRU/LRU Algorithms**
1) The basic operations would manifest as methods that exhibit the behavior of the NAssociativeCache. Hence, these operations would have the same name
irrespective of the type of algorithm implementing it.
2) To fulfil the above point, I needed to have an interface with these methods.
3) The LRU and MRU NAssociativeCache are two different types of NAssociativeCache, hence I wanted to separate their implementations. These would
manifest as two concrete classes which would implement the interface.
4) CUSTOM//

####**Cache Structure : How to implement this?**
1) Considering the operations associated with the NAssociativeCache like set() and get() and print(), to seek maximum efficiency we would
require a data structure which can set() and get() in O(1) and print() in O(n), where n is number of total items in the NAssociativeCache.
2) For set() and get()(search only), there's are a few operations which also need to be performed to execute the methods like:

- search() operation which checks if the item exists in the NAssociativeCache
- size() operation to check whether the NAssociativeCache is full before inserting the element
- delete() operation which will help us delete an element if the NAssociativeCache is full before inserting it into the NAssociativeCache
- insert() operation which will help us insert an element in a NAssociativeCache after all the prior checks

and hence we would also want(or at least desire) all these operations to be in O(1) to make sure set() and get() remain in O(1)

The closest data structure that would help us achieve this is a Linked List considering we are always inserting at the
TAIL and removing from the HEAD. We will have to customize the Data Stucture a bit to achieve search and size operations in O(1) as
opposed to O(n). This liked list would contain NAssociativeCache elements where the TAIL would contain the newest element to be accessed or
inserted and the older elements towards the HEAD. The size() can be achieved by having another variable which increments
on insertion and decrements on deletion, this would be in O(1).   

The class description section will contain specific implementation details.


3) As observed, only the set() method differs in its implementation. Both LRU and MRU would be able to insert at the TAIL.
However, LRU would delete from the HEAD and insert at TAIL where as MRU would DELETE from TAIL and Insert at TAIL.


## Getting Started
These instructions will help you run the copy of the project on your local machine for development and testing purposes.

### Prerequisites
Since this is a Java implementation, you will need

1) Java SE Development Kit.
2) A nice IDE, like IntelliJ IDEA or Eclipse or NetBeans.
3) Optionally Maven, if you are interested in dependency management.
4) JAR file which is a deliverable of this project.


### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests
You have the option to run the tests in two ways.

Option 1 : Install Maven and as this is maven project and the pom.xml file already has the required dependency inserted in it.
Simply navigate to the project directory

```
cd $projectdirectory
```
And once you are in the directory, type mvn test
```
$projectdirectory> mvn test
```
You should be able to see the tests run and see the report

Option 2: Navigate to the test\com\sagarsudhakar\nsetassociativecache folder. You will find separate test files for each component which
makes the entire project run. You will be able to explore the tests and results for the same in your IDE.

## Built With

* [Java](https://java.com/en/) - The programming framework/language used
* [JUnit](http://junit.org) - The Testing framework used
* [Maven](https://maven.apache.org/) - For Dependency Management
* [Git](https://git-scm.com/) - For Version Control
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - As the sole IDE
* [C8H10N4O2](https://pubchem.ncbi.nlm.nih.gov/compound/caffeine#section=Top)

## Authors
* **Sagar Sudhakar** - [LinkedIn](https://www.linkedin.com/in/sagarsudhakar/)

## License
This project contains no license information. The license terms will be set by and as per The Trade Desk policies I have adhered to below:

I understand and agree that this exercise and all information related to it exchanged between The Trade Desk ("TTD") and myself is confidential and proprietary to TTD. I agree to work on this exercise solely for the purpose of being considered for employment by TTD, and expressly disclaim, waive and relinquish all rights to information (including, code, binaries, documents, know how) produced in connection with this exercise. I will not distribute or publish any portion of the exercise requirements or code produced in relation to this exercise. I agree to not copy code from any source (including websites, books, or friends and colleagues) to complete this assessment. I may, however, reference programming language documentation or use an IDE that has code completion features.

*Sagar Sudhakar*


## Acknowledgments

* Libby Boyd - For connecting with me and giving me an opportunity to interview with The Trade Desk
* Sadek Noureddine and Talib Sarif - For being very patient and answering my queries
* The Trade Desk - For making this assessment an experience to remember!
