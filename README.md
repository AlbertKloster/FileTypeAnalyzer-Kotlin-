# Stage 4/5: A question of priorities
## Description
It is almost useless to have a pattern base with only one pattern. There should be more. We can take patterns one by one from some storage and match them until we find a successful match. But what should we do if several patterns match successfully?

Extend your program to make it match several patterns against each file. Implement some prioritizing scheme for patterns to prevent ambiguity in case of multiple matches.

The `patterns.db` consist of patterns with their priorities. You can download it <a href="https://stepik.org/media/attachments/lesson/210127/patterns.db">here</a>. For example:
```
4;"PK";"Zip archive"
7;"word/_rels";"MS Office Word 2007+"
7;"ppt/_rels";"MS Office PowerPoint 2007+"
7;"xl/_rels";"MS Office Excel 2007+"
```

A higher value means higher priority. In this example, the `"Zip archive"` is a container for multiple files. However, Microsoft Office files are also stored as Zip archives, you can clearly see this if you rename file `"file.docx"` to `"file.zip"`. If you unzip it, you'll see that it contains a bunch of folders and a bunch of XMLs. So, a Word file contains both `"PK"` indicating that this is a Zip archive and `"word/_rels"` indicating that this is a Word document. In this situation, you should choose a pattern with higher priority, which is `"MS Office Word 2007+"`.

While developing your program you can use the attached file. It contains some patterns with different priorities sorted by their "accuracy" (for example, MS Office 2007 documents store their data inside a zip archive, so the pattern for Office documents should be "stronger" than that for zip archive).

## Example
<b>Example:</b> <i>Program execution with arguments `test_files patterns.db`</i>
```
test_filesdoc_0.doc: MS Office Word 2003
doc_1.ppt: MS Office PowerPoint 2003
file.zip: Zip archive
```
