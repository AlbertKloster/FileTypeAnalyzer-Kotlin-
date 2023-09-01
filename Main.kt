package analyzer

import java.io.File

fun main(args: Array<String>) {
    val (file, pattern, result) = args
    val byteArray = File(file).readBytes()

    println(if (byteArray.contains(pattern.toByteArray())) result else "Unknown file type")
}

private fun ByteArray.contains(target: ByteArray): Boolean {
    if (target.isEmpty())  return true

    return this.fold(0) { startIndex, _ ->
        if (startIndex + target.size >= this.size) return false

        val subArray = this.copyOfRange(startIndex, startIndex + target.size)
        if (subArray.contentEquals(target))  return true

        startIndex + 1
    } != this.size
}