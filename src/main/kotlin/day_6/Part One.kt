package day_6

import java.nio.file.Path

fun main(args: Array<String>) {
    var filename = "input1.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }

    val choices = parseFile(filename)
    var count = 0
    for (choiceList in choices) {
        val charCount = Array<Int>(26) { 0 }
        choiceList.forEach {
            charCount[it.toInt() - 'a'.toInt()] = 1
        }
        count += charCount.sum()
    }

    println("The sum of yes counts is $count")
}

fun parseFile(filename: String): List<String> {
    val output = mutableListOf<String>()

    val path = Path.of("src", "main", "kotlin", "day_6", filename).toAbsolutePath()
    val temp = mutableListOf<String>()
    path.toFile().bufferedReader().lines().forEach { line ->
        if (line.isEmpty() || line.isBlank()) {
            output.add(temp.joinToString(""))
            temp.clear()
        } else {
            temp.add(line)
        }
    }
    return output
}