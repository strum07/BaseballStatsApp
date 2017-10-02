# TLDR : Build, Setup, and Install Notes for N-Set Associative Cache
Alright, Gentlemen! Let's dive in, shall we? :D


## Project Deliverable Items
<ol>
<li>README.md which is also the design documentation</li>
<li>TLDR-README.md - The one you are reading! :)</li>
<li>Zipped Project Source Code</li>
<li>NAssociativeCache.jar - (The JAR file which can be distributed and used without having the source code)</li>
</ol>


## Things to know
<p>Apart from conforming to the requirements expectations set for this assessment(found in the README.md: The "too long, please read me" version :p)</p>

<ol>
   <li> The cache exists as an isolated package </li>
   <li> The replacement algorithms reside in a separate package which implement the eviction strategies(LRU/MRU etc) </li>
   <li> The cache makes use of the eviction strategies and you'll need (2) to insert things into the cache </li>
   <li> The same cache object can be used to with various eviction strategies </li>     
</ol> 


## Prerequisite Installations
<ol>
<li>Standard Edition Java SDK </li>
<li>An IDE</li>
<li>Maven(If you plan to build the project)</li>
</ol>


## Build
<p>If you'd like to build the project and use the JAR generated to plug it into a new project to test it, read on. You could
skip to **SETUP** if you already have the JAR from this step or choose to use the JAR bundled with the project deliverable.</p>


**Step 1:** Navigate to the working directory on the terminal/command prompt

```
cd $workingDirectory
```

After the command, you will be able to see a prompt like this
```
$workingDirectory>
```
**Step 2:** Use Maven commands to see some magic to generate the JAR
```
$workingDirectory>mvn clean install
```
[Maven build resource](https://maven.apache.org/run-maven/)

**Step 3:** Navigate to the Target directory where the JAR is generated and have its location handy



## Project Setup 
- **JAR SETUP**
The JAR file, import that as a dependency to your new JAVA project

Resources on Importing:
1) [IntelliJ Idea](https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse)
2) [Eclipse](https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse) 
3) [Netbeans](https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project)


- **Package Setup**
After successfully importing the JAR file, you will now be able to use the classes in the JAR file in your own project!

For the entire scope of the project resides in the below package. 

```java
 import sagarsudhakar.nsetassociativecache.*;
```
 
 Let's dig a little deeper!
 
#### The following package contains everything related to the Cache
```java
  import sagarsudhakar.nsetassociativecache.cache.*;
```
  
  - The cache which we will make use of(set associative cache) can be found as the following
```java
    import sagarsudhakar.nsetassociativecache.cache.SetAssociativeCache;
```
 
 - If you'd like to build your own custom cache, you could implement the following interface
```java
     import sagarsudhakar.nsetassociativecache.cache.CacheInterface;
``` 
  - This is all we'll make use of in this package(As imports, internally we make use of DLLNode and Set as well)
  
  
#### The following package contains everything related to the Replacement Algorithms
```java
  import sagarsudhakar.nsetassociativecache.evictionstrategy.*;
```
 - To make sure that the strategies can be plugged in at runtime, we need the following context import
```java
   import sagarsudhakar.nsetassociativecache.evictionstrategy.EvictionStrategyContext;
```
 
#### The Replacement Policies available
 - The LEAST RECENTLY USED Algorithm can be implemented using this import 
```java
    import sagarsudhakar.nsetassociativecache.evictionstrategy.LruStrategy;
``` 

 - The MOST RECENTLY USED Algorithm can be implemented using this import 
```java
    import sagarsudhakar.nsetassociativecache.evictionstrategy.MruStrategy;
``` 

 - The CUSTOM Algorithms can be implemented using this import which can be extended during object instantiation
```java
    import sagarsudhakar.nsetassociativecache.evictionstrategy.CustomStrategy;
``` 

- The RANDOM Eviction Algorithm can be implemented using this import 
```java
    import sagarsudhakar.nsetassociativecache.evictionstrategy.RandomStrategy;
``` 

- If you'd like to build your own replacement policy(apart from CUSTOM option), you could implement the following interface
```java
     import sagarsudhakar.nsetassociativecache.evictionstrategy.EvictionStrategy;
``` 

## Execution and Usage

##### **Step 1**: Initialize a cache following an eviction policy(following example is an implementation of LRU)
- You'll need to import as described previously
- Setup Cache object
- Setup Strategy Context object

```java
     import com.sagarsudhakar.nsetassociativecache.cache.CacheInterface;
     import com.sagarsudhakar.nsetassociativecache.cache.SetAssociativeCache;
     import com.sagarsudhakar.nsetassociativecache.evictionstrategy.EvictionStrategyContext;
     import com.sagarsudhakar.nsetassociativecache.evictionstrategy.LruStrategy;
     
     public class ClientExample {
     
         public static void main(String[] args) {
             //UNIVERSAL CACHE OBJECT : CAN BE PLUGGED IN WITH ANY EVICTION STRATEGY
             //SetAssociativeCache takes in two parameters: 1) Number of Sets 2) Set Capacity
             //By defining the data types in CacheInterface instantiation, we ensure type safety(Generics let us define any type)
             
             CacheInterface<Integer, Integer> cache = new SetAssociativeCache(3, 4);
     
             //LRU IMPLEMENTATION with ALL THE FEATURES OF LRU CACHE
             
             EvictionStrategyContext strategy = new EvictionStrategyContext(new LruStrategy(cache));     
``` 


##### **Step 2**: Insert data into the Cache

```java
    //POPULATING THE CACHE
    for (int counter = 0; counter < 12; counter++) {
    
       //Use the strategy context object to insert into cache with eviction policy
       strategy.set(counter, counter + 1);
    }
```

##### **Step 3**: Change the policy for the same cache(Changing from LRU to MRU/Random)
```java
           //Changing eviction policy to MRU (pass cache object)
          strategy.changeStrategy(new MruStrategy(cache));
           
            //Changing eviction policy to Random (pass cache object)
          strategy.changeStrategy(new RandomStrategy(cache));

```
**Note that imports needed for the policies are included as described above


##### **Step 4**: Options available for Cache


Find their usage below :)

```java
        //CACHE OPTIONS
        
        //If the key is not found, get() will return undefined else the value and will update the recently used nature of the key
        cache.get(key);

        //Returns the key value (or undefined if not found) without updating the recently used nature of the key.
        cache.peek(key);
        
        //Deletes a key out of the Cache.
        cache.deleteKey(key);
        
        //Check if a key is in the Cache, without updating the recent used nature returns Boolean TRUE or FALSE
        cache.search(key);

        //Clear the Cache entirely, throwing away all values.
        cache.reset();

        //Return a list of the keys in the Cache.
        cache.keys();

        //Return a list of the values in the Cache.
        cache.values();

        //Return total quantity of objects currently in Cache
        cache.itemCount();

        //prints the current contents of the Cache to the console : Printing order: Left to right--> Most recently used to Least recently used
        cache.printCache();
``` 



#####**Bonus Step**: CUSTOM Cache usage
<p> By using the Custom cache, you have the ability to plug in your own algorithm complying to cache/eviction strategy
interface methods and make it work the way you want it. Below is a sample code on how to use this feature.</p>

Implementation of CustomStrategy to perform like LruStrategy


```java
 strategy.changeStrategy(new CustomStrategy<Integer, Integer>(cache) {
            public void set(Integer key, Integer value) {
                if (cache.search(key)) {
                    cache.removeFromCache(key);
                    cache.insertInCache(key, value);
                } else {
                    Set<Integer, Integer> set = cache.getMapOfSets()
                            .get(key.hashCode() % cache.getNumOfSets());
                    if (set.itemCount() >= cache.getSetCapacity()) {
                        set.removeFromSet(set.getHead().getKey());
                    }
                    set.insertInSet(key, value);
                }
            }
 });
``` 


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
What can I do better? Let me know at
[sagarsudhakar@live.com](mailto:sagarsudhakar@live.com)

