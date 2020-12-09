package day_1


import java.nio.file.Path

fun main(args: Array<String>){

    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val input: MutableList<Int> = parseFile(filename)
    val indexMap = createNumMap(input)
    for (i in input.indices){
        val num = input[i]
        if (indexMap.containsKey(2020 - num)){
            if (indexMap[2020 - num]!!.size > 1 || !indexMap[2020-num]!!.contains(num)){
                println("$num and ${2020-num} with product ${num * (2020 - num)}")
                indexMap[num]!!.remove(i)
                if (indexMap[num]!!.size == 0){
                    indexMap.remove(num)
                }
            }
        }
    }

}

fun parseFile(filename: String): MutableList<Int> {
    val parsedOutput = mutableListOf<Int>()
    val path = Path.of("src", "main", "kotlin", "day_1", filename).toAbsolutePath() // \src\main\kotlin\
    path.toFile().bufferedReader().useLines { lines ->
        lines.forEach {parsedOutput.add(it.toInt())}
    }
    return parsedOutput
}

fun createNumMap(input: List<Int>): HashMap<Int, MutableSet<Int>> {

    val indexHolder = hashMapOf<Int, MutableSet<Int>>()
    for (i in input.indices){
        val num = input[i]
        if (!indexHolder.containsKey(num)){
            indexHolder[num] = mutableSetOf()
        }
        indexHolder[num]!!.add(i)
    }
    return indexHolder
}