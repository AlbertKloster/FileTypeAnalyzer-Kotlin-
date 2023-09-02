# Stage 3/5: Wanted!
## Description
Let’s make another improvement. The pattern search engine is pretty fast now. However, if we want to check multiple files, we should pass them to the checker one by one and match them consecutively. Parallelize your checker to handle multiple files.

Now your search engine must deal with multiple files. Hint: your program can be organized as several workers. Each worker is equivalent (logically) to the single-threaded pattern matcher: it takes several files and matches them consecutively using the searching algorithm. The answers of each worker will be aggregated as the total execution result.

Your program should accept two strings: P and R as the first two arguments. P and R represent the pattern to check. Then it should take a folder’s path which contains all of the files to be checked by your program.

For this stage, you should use only the KMP algorithm.

## Examples
<b>Example 1:</b> <i>Program execution with arguments `test_files "-----BEGIN\ CERTIFICATE-----" "PEM certificate"`</i>
```
file.pem: PEM certificate
doc_1.docx: Unknown file type
doc_2.pdf: Unknown file type
```

<b>Example 2:</b> <i>Program execution with arguments `test_files "%PDF-" "PDF document"`</i>
```
file.pem: Unknown file type
doc_1.docx: Unknown file type
doc_2.pdf: PDF document
```
