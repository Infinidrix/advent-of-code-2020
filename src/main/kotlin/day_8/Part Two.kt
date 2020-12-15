package day_8

fun main(args: Array<String>){
    var filename = "input1.txt"
    if (args.isNotEmpty()){
        filename = args[0]
    }

    val instructionSet = parseFile(filename)
    val visitedArray = Array(instructionSet.size) { false }

    println("The final global value when end is reached is ${fixLoop(instructionSet, 0, true, visitedArray).first}")
}

fun fixLoop(instructionSet: Array<Pair<String, Int>>, currInd: Int, hasFlip: Boolean, visited: Array<Boolean>): Pair<Int, Boolean> {
    var count = 0
    var found = false

    if (currInd == instructionSet.size){
        return Pair(0, true)
    }else if (visited[currInd]){
        return Pair(0, false)
    }

    val currInst = instructionSet[currInd]
    visited[currInd] = true
    when (currInst.first){
        "acc" -> {
            val returned = fixLoop(instructionSet, currInd + 1, hasFlip, visited)
            if (returned.second){
                count = currInst.second + returned.first
                found = true
            }
        }
        "jmp" -> {
            val returned = fixLoop(instructionSet, currInd + currInst.second, hasFlip, visited)
            if (returned.second){
                count = returned.first
                found = true
            }else if (hasFlip){
                val secondChance = fixLoop(instructionSet, currInd + 1, false, visited)
                if (secondChance.second){
                    count = secondChance.first
                    found = true
                }
            }
        }
        "nop" -> {
            val returned = fixLoop(instructionSet, currInd + 1, hasFlip, visited)
            if (returned.second){
                count = returned.first
                found = true
            }else if (hasFlip){
                val newReturned = fixLoop(instructionSet, currInd + currInst.second, false, visited)
                if (newReturned.second){
                    count = newReturned.first
                    found = true
                }
            }
        }
    }
    visited[currInd] = false
    return Pair(count, found)
}

