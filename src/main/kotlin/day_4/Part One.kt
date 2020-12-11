package day_4

import java.nio.file.Path
import java.util.*

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }

    val passportSheet: List<Array<String>> = parseFile(filename)
    val fieldsCount = 8
    var count = 0
    for (passports in passportSheet){
        var fields = passports.size
        if (!passports.contains("cid")){
            fields++
        }
        if (fields >= fieldsCount){
            count++
        }
    }
    println("The number of valid passwords is $count")
}

fun parseFile(filename: String): List<Array<String>>{
    val output = mutableListOf<Array<String>>()
    val path = Path.of("src", "main", "kotlin", "day_4", filename).toAbsolutePath()
    path.toFile().bufferedReader().useLines {
        var passportInfo = mutableListOf<String>()
        it.forEach {
            if (it.isBlank()){
                output.add(passportInfo.toTypedArray())
                passportInfo = mutableListOf<String>()
            }else{
                val formattedLine = it.split(" ").toMutableList()
                formattedLine.forEachIndexed { index, s ->
                    formattedLine[index] = s.split(":")[0]
                }
                passportInfo.addAll(formattedLine)
            }
        }
    }
    return output
}