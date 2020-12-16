package day_9

import java.nio.file.Path

fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"

    val numbers = parseFile(filename)
    val hashedNumbers = hashMapOf<Long, Int>()

    for (i in 0 until 25){
        val value = numbers[i]
        hashedNumbers[value] = hashedNumbers[value]?.plus(1) ?: 1
    }

    for (index in 25 until numbers.size){
        val value = numbers[index]
        var fulfillsCondition = false
        for (secondIndex in index - 25 until index){
            val diff = value - numbers[secondIndex]
            if ((diff in hashedNumbers.keys && diff != numbers[secondIndex]) ||
                    (diff in hashedNumbers.keys && hashedNumbers[diff]!! > 1)){
                fulfillsCondition = true
            }
        }
        if (!fulfillsCondition){
            println("$value doesn't comply with the sum rule")
        }
        hashedNumbers[numbers[index - 25]] = hashedNumbers[numbers[index - 25]]!!.minus(1)
        if (hashedNumbers[numbers[index - 25]] == 0) hashedNumbers.remove(numbers[index - 25])
        hashedNumbers[value] = hashedNumbers[value]?.plus(1) ?: 1
    }
}

fun parseFile(filename: String): Array<Long> {
    val output = arrayListOf<Long>()

    val path = Path.of("src", "main", "kotlin", "day_9", filename).toAbsolutePath()
    path.toFile().bufferedReader().lines().forEach { output.add(it.toLong()) }

    return output.toTypedArray()
}
