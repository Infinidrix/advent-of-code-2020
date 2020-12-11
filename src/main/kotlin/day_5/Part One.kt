package day_5

import java.nio.file.Path
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream
import kotlin.math.log2

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val maxID = AtomicInteger(0)
    val inputStream: Stream<String> = parseFile(filename)
    inputStream.forEach {
        if (it.isNullOrEmpty())
            return@forEach
        val row = binSearch(127, 0, it, 'F')
        val col = binSearch(7, 7, it, 'L')
        val seatId = row * 8 + col
        if (maxID.get() < seatId){
            maxID.set(seatId)
        }
    }
    println("The maximum seat ID is $maxID")
}

fun binSearch(last: Int, iterStart: Int, string: String, backChar: Char):Int {
    var end = last + 1
    var start = 0
    var mid: Int
    val iterations = log2(end - start.toFloat()).toInt()
    for (i in 0 until iterations){
        mid = start + (end - start) / 2
        if (string[i + iterStart] == backChar){
            end = mid
        }else {
            start = mid
        }
    }
    return end - 1
}

fun parseFile(filename: String): Stream<String>{
    val path = Path.of("src", "main", "kotlin", "day_5", filename).toAbsolutePath()
    return path.toFile().bufferedReader().lines()
}