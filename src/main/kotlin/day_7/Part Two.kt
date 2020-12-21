package day_7

import java.nio.file.Path

fun main(args: Array<String>) {
    var filename = "input1.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }
    val graph = parseFileWithCount(filename)
    println("We need to buy ${addBags(graph, "shiny gold") - 1} additional bags to get past security")
}

fun addBags(graph: HashMap<String, Array<Pair<String, Int>>>, node: String): Int {
    var count = 1
    for (nextNode in graph[node]!!) {
        count += addBags(graph, nextNode.first) * nextNode.second
    }
    return count
}

fun parseFileWithCount(filename: String): HashMap<String, Array<Pair<String, Int>>> {
    val output = HashMap<String, Array<Pair<String, Int>>>()

    val path = Path.of("src", "main", "kotlin", "day_7", filename).toAbsolutePath()

    path.toFile().bufferedReader().lines().forEach {
        val line = it.split(" bags contain ")
        val result = arrayListOf<Pair<String, Int>>()
        val values = line[1].split(", ")
        for (value in values) {
            var curr = value.split(" bag")[0]
            if (curr == "no other") continue
            val temp = curr.split(" ")
            curr = temp.slice(1 until temp.size).joinToString(" ")
            val amount = temp[0].toInt()
            result.add(Pair(curr, amount))
        }
        output[line[0]] = result.toTypedArray()
    }

    return output
}

