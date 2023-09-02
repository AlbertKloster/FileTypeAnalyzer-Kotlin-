package analyzer

import java.io.File

fun main(args: Array<String>) {
    val (files, pattern, result) = args
    val workers = mutableListOf<Worker>()
    File(files).listFiles()?.forEach { file ->
        workers.add(Worker(file, pattern, result))
    }
    workers.forEach { it.start() }
    workers.forEach { it.join() }
}

class Worker(private val file: File, private val pattern: String, private val result: String) : Thread() {
    override fun run() {
        println(
            "${file.name}: ${
                if (file.readBytes().contains(pattern.toByteArray())) result else "Unknown file type"
            }"
        )
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
