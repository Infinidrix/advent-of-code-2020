package day_6

import java.nio.file.Path

fun main(args: Array<String>) {
    var filename = "input1.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }

    println("The sum of all yes counts is ${parseFileWithSet(filename)}")
}

fun parseFileWithSet(filename: String): Int {
    var output = 0

    val path = Path.of("src", "main", "kotlin", "day_6", filename).toAbsolutePath()
    var temp = mutableSetOf<Char>()
    var first = true
    path.toFile().bufferedReader().lines().forEach { line ->
        if (line.isEmpty() || line.isBlank()) {
            output += temp.size
            first = true; temp = mutableSetOf()
        } else if (first) {
            temp.addAll(line.toSet())
            first = false
        } else {
            val tempSet = line.toSet()
            temp.removeIf { !tempSet.contains(it) }
        }
    }
    return output
}