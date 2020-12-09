package day_2

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val passwordList = parseFile(filename)
    var count = 0
    passwordList.forEach {
        var status = false
        val firstIndex = it[0].toInt() - 1
        val secondIndex = it[1].toInt() - 1
        val char = it[2][0]
        if (it[3][firstIndex] == char){
            status = !status
        }
        if (it[3][secondIndex] == char){
            status = !status
        }
        if (status){
            count++
        }
    }
    println("Count of valid passwords is $count")
}