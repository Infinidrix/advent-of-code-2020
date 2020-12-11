package day_5

import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val allIds = mutableListOf<Int>()
    val inputStream: Stream<String> = parseFile(filename)
    inputStream.forEach {
        if (it.isNullOrEmpty())
            return@forEach
        val row = binSearch(127, 0, it, 'F')
        val col = binSearch(7, 7, it, 'L')
        val seatId = row * 8 + col
        allIds.add(seatId)
    }
    allIds.sort()
    for (ind in 1 until allIds.size){
        if (allIds[ind] - allIds[ind - 1] == 2){
            println("It seems like my ID is ${allIds[ind] - 1}")
        }
    }
}