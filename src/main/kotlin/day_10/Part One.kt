package day_10

import java.nio.file.Path


fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"

    val adapters = parseFile(filename).toSet()
    val countArray = Array(3) { 0 }
    var countConnected = 0
    var jolts = 0
    while (countConnected < adapters.size) {
        for (i in 1..3) {
            if (jolts + i in adapters) {
                countArray[i - 1]++
                jolts += i
                countConnected++
                break
            }
        }
    }
    countArray[2]++ // plus one because the device joltage is always 3 above the highest joltage
    println("The number of 1 jolt diffs is ${countArray[0]} and 3 jolt diffs is ${countArray[2]} \n" +
            "making the product ${countArray[0] * countArray[2]}")

}

fun parseFile(filename: String): Array<Int> {
    val output = arrayListOf<Int>()

    val path = Path.of("src", "main", "kotlin", "day_10", filename).toAbsolutePath()
    path.toFile().bufferedReader().lines().forEach { output.add(it.toInt()) }

    return output.toTypedArray()
}