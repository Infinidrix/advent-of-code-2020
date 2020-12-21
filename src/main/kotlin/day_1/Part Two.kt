package day_1

fun main(args: Array<String>) {
    var filename = "input2.txt"
    if (args.isNotEmpty()) {
        filename = args[0]
    }
    val input: List<Int> = parseFile(filename)
    val indexMap = createTwoNumMap(input)
    for (i in input.indices) {
        val num = input[i]
        if (indexMap.containsKey(2020 - num)) {
            indexMap[2020 - num]!!.forEach {
                if (!it.contains(i)) {
                    println("Found $num, ${input[it[1]]} and ${input[it[0]]} " +
                            "with product ${num * input[it[1]] * input[it[0]]}")
                }
            }
        }
    }
}

fun createTwoNumMap(input: List<Int>): HashMap<Int, MutableList<Array<Int>>> {
    val indexHolder = hashMapOf<Int, MutableList<Array<Int>>>()
    for (i in input.indices) {
        val num = input[i]
        for (j in i until input.size) {
            val num2 = input[j]
            val sum = num + num2
            if (!indexHolder.containsKey(sum)) {
                indexHolder[sum] = mutableListOf()
            }
            indexHolder[sum]!!.add(intArrayOf(i, j).toTypedArray())
        }
    }
    return indexHolder
}