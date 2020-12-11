package day_4

import java.nio.file.Path
import kotlin.collections.HashMap

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val passportSheet = parseFileWithInfo(filename)
    var count = 0
    for (passport in passportSheet){
        var isValid = true
        isValid = isValid && checkBirthday(passport)
        isValid = isValid && checkIssueDate(passport)
        isValid = isValid && checkExpYear(passport)
        isValid = isValid && checkHeight(passport)
        isValid = isValid && checkHair(passport)
        isValid = isValid && checkEye(passport)
        isValid = isValid && checkId(passport)
        if (isValid) count++
    }
    println("The number of valid passports is $count")
}

fun checkId(passport: java.util.HashMap<String, String>): Boolean {
    val id = passport["pid"] ?: return false
    if (id.length != 9) return false
    for (char in id){
        if (!char.isDigit()) return false
    }
    return true
}

fun checkEye(passport: java.util.HashMap<String, String>): Boolean {
    val eye = passport["ecl"] ?: return false
    val validColors = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    if (eye !in validColors) return false
    return true
}

fun checkHair(passport: HashMap<String, String>): Boolean {
    val hair = passport["hcl"] ?: return false
    if (hair.isEmpty() || hair[0] != '#') return false
    var count = 0
    val validChars = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

    for (char in hair.substring(1)){
        if (char !in validChars) return false
        count++
    }
    if (count != 6){
        return false
    }
    return true
}

fun checkHeight(passport: HashMap<String, String>): Boolean{
    val height = passport["hgt"] ?: return false
    val length = height.length
    if (length < 3){
        return false
    }
    val acceptableSuffix = HashMap<String, IntRange>()
    acceptableSuffix["in"] = 59..76
    acceptableSuffix["cm"] = 150..193
    val units = height.substring(length - 2)
    val value = height.substring(0, length - 2).toIntOrNull() ?: return false
    val acceptableRange = acceptableSuffix[units] ?: return false
    if (value !in acceptableRange){
        return false
    }
    return true
}

fun checkExpYear(passport: HashMap<String, String>): Boolean{
    if (!passport.containsKey("eyr")){
        return false
    }
    val expYear = passport["eyr"]
    if (expYear!!.length != 4){
        return false
    }
    val expYearInt = expYear.toIntOrNull() ?: return false
    if (expYearInt !in 2020..2030){
        return false
    }
    return true
}

fun checkIssueDate(passport: HashMap<String, String>): Boolean {
    if (!passport.containsKey("iyr")){
        return false
    }
    val issueYear = passport["iyr"]
    if (issueYear!!.length != 4){
        return false
    }
    val issueYearInt = issueYear.toIntOrNull() ?: return false
    if (issueYearInt !in 2010..2020){
        return false
    }
    return true
}

fun checkBirthday(passport: HashMap<String, String>): Boolean{
    if (!passport.containsKey("byr")){
        return false
    }
    val birthYear = passport["byr"]
    if (birthYear!!.length != 4){
        return false
    }
    val birthYearInt = birthYear.toIntOrNull() ?: return false
    if (birthYearInt !in 1920 until 2003){
        return false
    }
    return true
}

fun parseFileWithInfo(filename: String): List<HashMap<String, String>>{
    val output = mutableListOf<HashMap<String, String>>()
    val path = Path.of("src", "main", "kotlin", "day_4", filename).toAbsolutePath()
    path.toFile().bufferedReader().useLines {
        var passportInfo = HashMap<String, String>()
        it.forEach { line ->
            if (line.isBlank()){
                output.add(passportInfo)
                passportInfo = HashMap()
            }
            else {
                line.split(" ").forEach { property ->
                    val pair = property.split(":")
                    passportInfo[pair[0]] = pair[1]
                }
            }
        }
    }
    return output
}