Properties of the program:

Each heap type has its own class and every heap class implements an interface Heap. This interface is used in all the benchmarks and Dijkstra. Furthermore every heap uses Node class as its elements.

Node class contains the different attributes that are used by the heaps and it also contains some auxiliary methods that are used by some heaps (mainly Fibonacci heap).

All the heaps are minimum priority queues implemented as linked lists, except binary, d and iterative d-heap, which are array based.

Benchmark insert makes an array with random integers and then it inserts these integers into the heaps and measures the time that it takes to insert all the of them. Benchmark delete also makes an array with random integers and then it inserts these into the heaps. After that it take time how long it takes to delete all the integers from the heap. Array sorting benchmark takes time how long does it take to insert integers from a random array into the heap and then deleting them, thus sorting an array of integers into a ascending order. Dijkstra benchmark is not implemented in all the heaps because some heaps do not have capabilities for efficiently decreasing the keys. Also binomial heap is not implemented in Dijkstra benchmark. Reasons for this are specified in the last paragraph of this document. Dijkstra is implemented so that it finds distances to every node from a specified start node. It does not track the paths that are used.

Numerical results of the benchmarks can be found in the file BenchmarkResults.md.
These results have been compiled from ten benchmark runs and then the average was taken. So all in all the results are from a hundred benchmarks as one benchmark run goes through ten benchmarks and takes an average from them.

Things to improve: Program is not configurable (except form source code and tests are hard coded. At some point one should make a graphical user interfaxe, so the user could define which test are to be run and the user should also be able to give parameters for the tests. Binomial heap does not for correctly for Dijkstra. I think the problem lies in decrease key or it's auxiliary methods, but it is not immediately clear where the problem lies, because the JUnit tests that test the functionality of Dijkstra are passed when run, so decrease keys basic functiopnality is ok. I think that there is some problem with bigger graphs and when elements are moved around in the heap, some information is misplaced. DijkstraPriorityValue is my main candidate, but as is said. I have not been able to locate the problem as of yet.
