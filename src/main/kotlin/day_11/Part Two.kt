package day_11


fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else "input1.txt"
    val neighbours = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0),
            Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1))
    var seats = parseFile(filename)
    val tempSeats = seats.copy()
    var hasChange = true
    while (hasChange) {
        hasChange = false
        for (row in seats.indices) {
            for (col in seats[row].indices) {
                val seat = seats[row][col]
                val seatsOccupied = countSeatsWithSights(seats, row, col, neighbours)
                if (seat == 'L' && seatsOccupied == 0) {
                    tempSeats[row][col] = '#'
                    hasChange = true
                } else if (seat == '#' && seatsOccupied >= 5) {
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

fun countSeatsWithSights(seats: Array<Array<Char>>, row: Int, col: Int, directions: Array<Pair<Int, Int>>): Int {
    return directions.map { seatCheck(seats, row, col, it.first, it.second) }.count { it }
}

fun seatCheck(seats: Array<Array<Char>>, row: Int, col: Int, moveX: Int, moveY: Int): Boolean {
    var newRow = row + moveX
    var newCol = col + moveY
    while (newRow in seats.indices && newCol in seats[newRow].indices) {
        if (seats[newRow][newCol] == '#') {
            return true
        } else if (seats[newRow][newCol] == 'L') {
            return false
        }
        newRow += moveX
        newCol += moveY
    }
    return false
}
