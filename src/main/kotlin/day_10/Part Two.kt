package day_10

fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"

    val adapters = arrayListOf(0)
    adapters.addAll(parseFile(filename).sortedArray())
    val possibleArrangements = Array(adapters.size) { 0L }
    possibleArrangements[adapters.size - 1] = 1L
    for (index in adapters.size - 2 downTo 0) {
        val currVal = adapters[index]
        var nextInd = index + 1
        var nextVal = adapters[nextInd]
        while (nextVal in currVal + 1..currVal + 3) {
            possibleArrangements[index] += possibleArrangements[nextInd]
            if (nextInd == adapters.size - 1) break
            nextVal = adapters[++nextInd]
        }
    }
    println("There are ${possibleArrangements[0]} ways to arrange adapters")

}