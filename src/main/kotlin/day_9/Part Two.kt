package day_9


fun main(args: Array<String>){
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"

    val numbers = parseFile(filename)
    var goal = 731031916L // Number found from part one

    var start = 0
    var end = -1
    while (end < numbers.size - 1){
        when {
            goal == 0L && end - start > 0-> {
                val slice = numbers.slice(start..end)
                val minVal = slice.reduce {a, b -> minOf(a, b)}
                val maxVal = slice.reduce {a, b -> maxOf(a, b)}
                println("The minimum number in sequence is $minVal and max is $maxVal. \nTheir sum is ${minVal + maxVal}")
                end += 1
                goal -= numbers[end]
            }
            goal < 0L -> {
                goal += numbers[start]
                start += 1
            }
            else -> {
                end += 1
                goal -= numbers[end]
            }
        }
    }
}