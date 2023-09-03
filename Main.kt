package analyzer

import java.io.File
import java.math.BigInteger


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
            if (rabinKarpContainsPattern(file.readBytes(), pattern.pattern.toByteArray())) {
                println("${file.name}: ${pattern.result}")
                return
            }
        }
        println("${file.name}: $UNKNOWN_FILE_TYPE")
    }
}

fun rabinKarpContainsPattern(source: ByteArray, pattern: ByteArray): Boolean {
    val sourceLength = source.size
    val patternLength = pattern.size

    if (sourceLength < patternLength) {
        return false
    }

    val prime = BigInteger("101") // Prime number for hashing
    val patternHash = calculateHash(pattern, 0, patternLength, prime)
    var sourceHash = calculateHash(source, 0, patternLength, prime)

    for (i in 0 until sourceLength - patternLength + 1) {
        if (sourceHash == patternHash) {
            // If the hash matches, perform a character-by-character comparison
            var match = true
            for (j in 0 until patternLength) {
                if (source[i + j] != pattern[j]) {
                    match = false
                    break
                }
            }
            if (match) {
                return true
            }
        }

        // Update the sourceHash for the next window
        if (i < sourceLength - patternLength) {
            sourceHash = sourceHash.subtract(BigInteger.valueOf(source[i].toLong())
                .multiply(prime.pow(patternLength - 1)))
                .multiply(prime)
                .add(BigInteger.valueOf(source[i + patternLength].toLong()))
        }
    }

    return false
}

private fun calculateHash(input: ByteArray, start: Int, end: Int, prime: BigInteger): BigInteger {
    var hash = BigInteger.ZERO
    for (i in start until end) {
        hash = hash.multiply(prime).add(BigInteger.valueOf(input[i].toLong()))
    }
    return hash
}