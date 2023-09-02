# Stage 2/5: KMP algorithm
## Description
Improve your file type checker’s pattern match logic by rewriting the pattern search algorithm. Use some of the advanced algorithms you have learned so far like the Knuth-Morris-Pratt algorithm. Check your program’s search performance with some huge file. Compare your improved search engine with a naive implementation. You can use `nanoTime()` from the `java.lang.System` class, for measuring execution time. Check the <a href="https://docs.oracle.com/javase/10/docs/api/java/lang/System.html#nanoTime()">official documentation</a> and the Kotlin tutorial <a href="https://www.baeldung.com/kotlin/measure-elapsed-time#classic-java">Measuring Elapsed Time in Kotlin, #6</a>.

Your program should accept another argument, that represents an algorithm, a naive implementation that you've implemented in the previous step marked as `--naive` and KMP algorithm marked as `--KMP`. Other arguments (file name, pattern and file type) should be parsed after this one.

## Examples
First, we've checked the naive implementation, and it took roughly 5 seconds. Then KMP showed 5 times better performance. You should expect similar behavior from your program.

<b>Example 1:</b> <i>Program execution with arguments `--naive huge_doc.pdf "%PDF-" "PDF document"`</i>
```
PDF document
It took 5.011 seconds
```

<b>Example 2:</b> <i>Program execution with arguments `--KMP huge_doc.pdf "%PDF-" "PDF document"`</i>
```
PDF document
It took 1.037 seconds
```

<b>Example 3:</b> <i>Program execution with arguments `--naive pic.png "%PDF-" "PDF document"`</i>
```
Unknown file type
It took 3.641 seconds
```

<b>Example 4:</b> <i>Program execution with arguments `--KMP pic.png "%PDF-" "PDF document"`</i>
```
Unknown file type
It took 0.469 seconds
```
