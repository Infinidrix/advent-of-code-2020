package day_3

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }
    val terrain = parseFile(filename)
    // ((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))
    val allSlopes = arrayOf(arrayOf(1, 1), arrayOf(3,1), arrayOf(5,1), arrayOf(7,1), arrayOf(1,2))
    var total = 1
    for (slope in allSlopes){
        total *= find_crashes(terrain, slope[0], slope[1])
    }
    println("Multiplication of all crashes is $total")
}

fun find_crashes(terrain: List<String>, right: Int, down: Int): Int {
    var crashes = 0
    var ind = 0
    var rightInd = 0
    while (ind < terrain.size){
        if (terrain[ind][rightInd] == '#'){
            crashes++
        }
        ind += down
        rightInd = (rightInd + right) % terrain[0].length
    }
    return crashes
}
