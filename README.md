# nateRepo

***How to run?

class PIAssignmets.java is including the main task for running this task.There are two .txt files with events: the base given .txt file and another one with a different word number.
The algorithm is straightforward.for every match between lines we found, we put in a patternMtach Map.the sequence go like this:look for matched lines in the map, and try to match them by the pattern.if success - merge line to map and continue to the next line.otherwise - look for a candidates (lines that weren't matched yet) and try to match with each one of them(-the approach is that if we found match in the map, there is no need to keep looking, since if we found match chain in the map we guarantee that all proper previous lines were already merged to to them).




***1. What can you say about the complexity of your code?

This assignments quick approach brings time complexity to be quadratic of O(n^2) as we "brute force" by comparing each new line to previous lines, In the worst case scenario for example, where all lines (from all words length) are different from each other, the N element will necessarily do N-1 match attempts with all previous lines, the next node will do the same +1, and son on and son on.
In the average case and assuming we are reading from 'consistent phrasing' generator, we likely not to iterate all previous lines, so the TimeComplexity will reduce as a factor of some mathematical expression that involving the number of possibilities to choose, but we can't rely on that, so overall  time complexity is quadratic.



***2. How will your algoritem scale?

In this task we are not processing data part by part, but as a single unit as we read all data to memory one by one, a single log at a time.

From a scale perspective, if our log file gets bigger and bigger till it gets huge, the memory capacity will exceed his limit and will collapse due to Memory. 
If we will process tons of logs with light data instead of a Huge single file, we will be able to process all logs (assuming every new file loaded is resetting the memory) without memory issues, thus, the time complexity to process all log files will be long. 
So from a scale perspective my solution is not good for scale, as there are no parts that happen in parallel, and the program runs in single thread and inMemory.



***3. If you had two weeks to do this task, what would you have done differently? What would be better?

I would process a file in chunks so I'll read an amount of rows at a time and wouldn't read it all at once.  Also,I can't hold all the data all the time in memory so I will have to move to some database so I'll be able to use more memory than the physical we have. 

Also, I would have changed the approach for this solution, instead of holding all lines in memory and doing iterative comparison between them, I would save every word as a node in a graph, and hold the connections (represents matches)  of each node to another for input and output connection.Each word can be identified by 2 parameters which are timeStamp(column) and index (the location of the word in the sentence starting from 0 to <size of sentence>). After building that that will bring the time complexity to O(K) where K=N+E, which is the number of words(N) and the number of edges (E). 

*A whiteBoard draw picture is attached for graph approach in the resource folder inside the zip*
in scaling, since every line now will be not unique in a multithreaded environment, we will suffer from collision between lines.in general for two weeks work i should create a mechanism where every time memory is about to end we output out data to some storage and keep managing this incoming log.
Natanel

  
