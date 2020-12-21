package day_12

import java.nio.file.Path
import java.util.stream.Stream
import kotlin.math.abs

fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"
    // x,y - state
    val dirs = hashMapOf('N' to Pair(0, 1), 'S' to Pair(0, -1), 'E' to Pair(1, 0), 'W' to Pair(-1, 0))
    val instructions = parseFile(filename)!!
    val rotations = arrayOf(Pair(1, 0), Pair(0, -1), Pair(-1, 0), Pair(0, 1))
    var facing = 0
    var horizontalDist = 0
    var vertDist = 0
    for (inst in instructions) {
        when (inst.first) {
            in dirs -> {
                horizontalDist += dirs[inst.first]!!.first * inst.second
                vertDist += dirs[inst.first]!!.second * inst.second
            }
            'F' -> {
                horizontalDist += rotations[facing].first * inst.second
                vertDist += rotations[facing].second * inst.second
            }
            else -> {
                val mult = if (inst.first == 'R') 1 else -1
                facing = Math.floorMod(facing + (mult * (inst.second / 90)), 4)
            }
        }
    }
    println("Horizontal loc is $horizontalDist and vertical loc $vertDist \nThe Manhattan distance between start and end is ${abs(vertDist) + abs(horizontalDist)}")
}

fun parseFile(filename: String): Stream<Pair<Char, Int>>? {
    val path = Path.of("src", "main", "kotlin", "day_12", filename).toAbsolutePath()
    return path.toFile().bufferedReader().lines().map { Pair(it[0], it.slice(1 until it.length).toInt()) }
}
