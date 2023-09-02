package analyzer

import java.io.File

data class Pattern(val priority: Int, val pattern: String, val result: String)

const val UNKNOWN_FILE_TYPE = "Unknown file type"

fun main(args: Array<String>) {
    val (pathFiles, pathPatterns) = args
    val patterns = getPatterns(pathPatterns)
    val workers = mutableListOf<Worker>()
    File(pathFiles).listFiles()?.forEach { file ->
        workers.add(Worker(file, patterns))
    }
    workers.forEach { it.start() }
    workers.forEach { it.join() }

}

private fun getPatterns(pathPatterns: String): List<Pattern> {
    return File(pathPatterns).readLines()
        .map { it.split(";") }
        .map { Pattern(it[0].toInt(), it[1].removeSurrounding("\""), it[2].removeSurrounding("\"")) }
        .sortedByDescending { it.priority }
}

class Worker(private val file: File, private val patterns: List<Pattern>) : Thread() {
    override fun run() {
        for (pattern in patterns) {
            if (file.readBytes().contains(pattern.pattern.toByteArray())) {
                println("${file.name}: ${pattern.result}")
                return
            }
        }
        println("${file.name}: $UNKNOWN_FILE_TYPE")
    }
}

private fun ByteArray.buildPrefixFunction(): IntArray {
    val table = IntArray(size)
    var j = 0
    for (i in 1 until size) {
        while (j > 0 && this[i] != this[j]) {
            j = table[j - 1]
        }
        if (this[i] == this[j]) {
            j++
        }
        table[i] = j
    }
    return table
}

private fun ByteArray.contains(target: ByteArray): Boolean {
    if (target.isEmpty()) return true

    val kmpTable = target.buildPrefixFunction()
    var i = 0
    var j = 0

    while (i < size) {
        if (target[j] == this[i]) {
            i++
            j++
            if (j == target.size) {
                return true
            }
        } else {
            if (j != 0) {
                j = kmpTable[j - 1]
            } else {
                i++
            }
        }
    }
    return false
}
