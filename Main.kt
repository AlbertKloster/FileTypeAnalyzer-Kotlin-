package analyzer

import java.io.File

fun main(args: Array<String>) {
    val (algorithm, file, pattern, result) = args
    val byteArray = File(file).readBytes()
    val startTime = System.currentTimeMillis()

    val contains = when (algorithm) {
        "--naive" -> byteArray.containsNaive(pattern.toByteArray())
        "--KMP" -> byteArray.containsKMP(pattern.toByteArray())
        else -> false
    }
    val time = (System.currentTimeMillis() - startTime) / 1000.0

    println(if (contains) result else "Unknown file type")
    println("It took $time seconds")
}

private fun ByteArray.containsNaive(target: ByteArray): Boolean {
    if (target.isEmpty())  return true

    return this.fold(0) { startIndex, _ ->
        if (startIndex + target.size > this.size) return false

        val subArray = this.copyOfRange(startIndex, startIndex + target.size)
        if (subArray.contentEquals(target))  return true

        startIndex + 1
    } != this.size
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

private fun ByteArray.containsKMP(target: ByteArray): Boolean {
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
