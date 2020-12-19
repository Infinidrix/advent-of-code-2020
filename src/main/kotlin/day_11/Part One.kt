package day_11

import java.nio.file.Path

fun main(args: Array<String>){
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"
    val neighbours = arrayOf(Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1, 0),
                             Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1))
    var seats = parseFile(filename)
    val tempSeats = seats.copy()
    var hasChange = true
    while (hasChange){
        hasChange = false
        for (row in seats.indices){
            for (col in seats[row].indices){
                val seat = seats[row][col]
                val seatsOccupied = countSeatsOccupied(seats, row, col, neighbours)
                if (seat == 'L' && seatsOccupied == 0){
                    tempSeats[row][col] = '#'
                    hasChange = true
                }else if (seat == '#' && seatsOccupied >= 4){
                    tempSeats[row][col] = 'L'
                    hasChange = true
                }
            }
        }
        seats = tempSeats.copy()
    }
    val occupied = seats.map { it.count { it == '#' } }.sum()
    println("The number of occupied seats at the end is $occupied")
}
fun Array<Array<Char>>.copy() = Array(size) { get(it).clone() }

fun countSeatsOccupied(seats: Array<Array<Char>>, row: Int, col: Int, neighbours: Array<Pair<Int, Int>>): Int {
    var count = 0
    for (neighbour in neighbours){
        val newRow = row + neighbour.first
        val newCol = col + neighbour.second
        if (newRow in seats.indices && newCol in seats[newRow].indices && seats[newRow][newCol] == '#'){
            count++
        }
    }
    return count
}

fun parseFile(filename: String): Array<Array<Char>> {
    val output = arrayListOf<Array<Char>>()

    val path = Path.of("src", "main", "kotlin", "day_11", filename).toAbsolutePath()
    path.toFile().bufferedReader().lines().forEach {output.add(it.toCharArray().toTypedArray())}

    return output.toTypedArray()
}
