package day_12

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"
    // x,y - state
    val dirs = hashMapOf('N' to Pair(0, 1), 'S' to Pair(0, -1), 'E' to Pair(1, 0), 'W' to Pair(-1, 0))
    val instructions = parseFile(filename)!!
    var wayPointHorizontal = 10
    var wayPointVert = 1
    var shipHorizontal = 0
    var shipVert = 0
    for (inst in instructions) {
        when (inst.first) {
            in dirs -> {
                wayPointHorizontal += dirs[inst.first]!!.first * inst.second
                wayPointVert += dirs[inst.first]!!.second * inst.second
            }
            'F' -> {
                shipHorizontal += wayPointHorizontal * inst.second
                shipVert += wayPointVert * inst.second
            }
            else -> {
                // rotate waypoint
                val mult = if (inst.first == 'R') -1 else 1
                val angle = (inst.second.toDouble() / 180.0) * Math.PI * mult
                val newHori = wayPointHorizontal * cos(angle) - wayPointVert * sin(angle)
                val newVert = wayPointVert * cos(angle) + wayPointHorizontal * sin(angle)
                wayPointHorizontal = round(newHori).toInt()
                wayPointVert = round(newVert).toInt()
            }
        }
    }
    println("Horizontal loc is $shipHorizontal and vertical loc $shipVert \nThe Manhattan distance between start and end is ${abs(shipHorizontal) + abs(shipVert)}")
}