# Stage 1/5: Is this a PDF?
## Description
In this project, you will learn how to make a tool for determining file type. It's not like determining file type based on the extension of the file; the filename can be random. Actually, many file types contain special byte sequences that make it easy to determine them. You’ll start from a simple program that can only search for an occurrence of the specific pattern inside a single file and gradually turn it into a more practical solution that can check hundreds and thousands of files against a huge pattern set. Each pattern has information about a file type which will be reported to the user when there is a successful match.

This approach is widely used in many different applications. For example, the <a href="https://en.wikipedia.org/wiki/File_(command)">Unix “file” tool</a> relies on a sophisticated “magic” database (it consists of a pattern set written in a specific language), antivirus and malware-detection tools search the malicious signatures inside user’s files, and firewalls do the same with a system’s network traffic (as well as DPI systems).

Let’s begin with an elementary prototype of our file type checker. Write a program that accepts a pattern and its file type and matches the pattern against some file.

Here we deﬁne pattern as a pair of two strings: {P, R}, where P is a pattern itself and R is a resulting ﬁle type which corresponds to pattern P. If the string P is found in the ﬁle then your program should return R as file type. For example, for the following pattern
```kotlin
{"%PDF-", "PDF document"}
```
the program will search for a `%PDF-` in a file’s binary data and if found successfully will determine file type as `PDF document`. Note that the pattern can be anywhere in the file, not just at the start of the file. You can see here in the row with "Magic number" there is the `%PDF` pattern, but actually after this pattern, the `-` symbol always appears, so the pattern described above is stricter.

You can use <a href="https://docs.w3cub.com/kotlin/docs/tutorials/kotlin-for-py/file-io">this link</a> to see how to open a file in binary (or <a href="https://www.codejava.net/java-se/file-io/how-to-read-and-write-binary-files-in-java">this detailed link</a> for Java, but it is similar with Kotlin).

Write a program that accepts a ﬁle name and pattern and searches for an occurrence of the pattern in this ﬁle. If the pattern matched at least once the program should report its type. If there were no matches, print `Unknown file type`.

Your program should accept three arguments: the file to check (relative path to the project folder), the pattern string (P), and the result string (R).

## Examples
The examples below show how your output might look.

<b>Example 1:</b> <i>Program execution with arguments `doc.pdf "%PDF-" "PDF document"`</i>
```
PDF document
```
<b>Example 2:</b> <i>Program execution with arguments `picture.jpg "%PDF-" "PDF document"`</i>
```
Unknown file type
```
