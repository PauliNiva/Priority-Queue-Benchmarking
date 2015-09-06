Instructions For Use:

The program is developed for a computer that runs at least with the following specifications:
    - Intel(R) Core(TM) i-53570K CPU @ 3.40GHz
    - 16 GB of RAM
    
Program may crash due to the recursive processes that are used in some of the methods if the computers memory, cpu and other specifications such as java heap size are not sufficiently high enough.
In this case, user should lower the values that give problems and run the program again. Most likely the recursive unary heap is the first one to give problems due to stack overflow. Also treap is reported to give heap space problems on machines with lesser capacities.

Usage: Run the program from sources or download the latest shaded jar from the repository's releases tab ( https://github.com/PauliNiva/Priority-Queue-Benchmarking/releases ) and run the jar. Then wait _awhile_ till the program runs through its benchmarks. This takes about 15 minutes with the computer that has specifications as stated above. While the program is running it prints out results as each benchmark completes.