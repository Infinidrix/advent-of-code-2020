package day_8

import java.nio.file.Path

fun main(args: Array<String>) {
    var filename = "input1.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }
    val instructionSet = parseFile(filename)
    val visitedArray = Array(instructionSet.size) { false }
    var lineNumber = 0
    var count = 0
    while (!visitedArray[lineNumber]) {
        val currInstruction = instructionSet[lineNumber]
        visitedArray[lineNumber] = true
        when (currInstruction.first) {
            "acc" -> {
                count += currInstruction.second
                lineNumber++
            }
            "jmp" -> lineNumber += currInstruction.second
            else -> lineNumber++
        }
    }
    println("The global value is $count at the time of infinite loop")
}

fun parseFile(filename: String): Array<Pair<String, Int>> {
    val output = arrayListOf<Pair<String, Int>>()

    val path = Path.of("src", "main", "kotlin", "day_8", filename).toAbsolutePath()
    path.toFile().bufferedReader().lines().forEach {
        val line = it.split(" ").toTypedArray()
        if (line[1][0] == '+') {
            line[1] = line[1].substring(1)
        }
        output.add(Pair(line[0], line[1].toInt()))
    }

    return output.toTypedArray()
}
