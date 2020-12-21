package day_2

import java.nio.file.Path

fun main(args: Array<String>) {
    var filename = "input1.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }
    val passwordList = parseFile(filename)
    var count = 0
    passwordList.forEach {
        val charCount = it[3].count { char -> char == it[2][0] }
        val minBound = it[0].toInt()
        val maxBound = it[1].toInt()
        if (charCount in minBound..maxBound) {
            count += 1
        }
    }
    println("The number of valid passwords is $count")
}

fun parseFile(filename: String): List<Array<String>> {
    val output = mutableListOf<Array<String>>()
    val path = Path.of("src", "main", "kotlin", "day_2", filename).toAbsolutePath()
    path.toFile().bufferedReader().useLines {
        it.forEach { line ->
            val formattedLine = line.split(" ")
            val result = arrayListOf<String>()
            result.addAll(formattedLine[0].split("-"))
            result.addAll(formattedLine.slice(1 until formattedLine.size))
            output.add(result.toTypedArray())
        }
    }
    return output
}
