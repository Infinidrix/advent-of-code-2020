package day_7

import java.nio.file.Path
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val graph = parseFile(filename)
    val dp = mutableMapOf<String, Boolean>()
    for (node in graph.keys){
        val queue = ArrayDeque<String>()
        queue.add(node)
        while (queue.isNotEmpty()){
            val currNode = queue.removeFirst()
            if ((currNode in dp.keys && dp[currNode] == true) || currNode == "shiny gold"){
                dp[node] = true
                break
            }
            queue.addAll(graph[currNode]!!)
        }
    }
    println("The number of reachable to shiny gold is ${dp.entries.count { it.value } - 1}")
}

fun parseFile(filename: String): HashMap<String, Array<String>> {
    val output = HashMap<String, Array<String>>()

    val path = Path.of("src", "main", "kotlin", "day_7", filename).toAbsolutePath()

    path.toFile().bufferedReader().lines().forEach {
        val line = it.split(" bags contain ")
        val result = arrayListOf<String>()
        val values = line[1].split(", ")
        for (value in values){
            var curr = value.split(" bag")[0]
            if (curr == "no other") continue
            val temp = curr.split(" ")
            if (temp[0].toIntOrNull() != null){
                curr = temp.slice(1 until temp.size).joinToString(" ")
            }
            result.add(curr)
        }
        output[line[0]] = result.toTypedArray()
    }
    return output
}
