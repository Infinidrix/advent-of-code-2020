package day_3

import java.nio.file.Path

fun main(args: Array<String>) {
    var filename = "input1.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }

    val terrain = parseFile(filename)
    var rightLocation = 0
    var crashes = 0
    for (row in terrain.indices) {
        if (terrain[row][rightLocation] == '#') {
            crashes++
        }
        rightLocation = (rightLocation + 3) % terrain[row].length
    }
    println("The toboggan will encounter $crashes trees")
}

fun parseFile(filename: String): List<String> {
    val output = mutableListOf<String>()
    val path = Path.of("src", "main", "kotlin", "day_3", filename).toAbsolutePath()
    path.toFile().bufferedReader().useLines {
        it.forEach { line ->
            output.add(line)
        }
    }
    return output
}
