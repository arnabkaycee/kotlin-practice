package aoc.day1

import java.io.File

const val FILE_PATH = "src/main/kotlin/aoc/day1/input.txt"
val numbers = File(FILE_PATH).readLines().map { it.toInt() }
//
//fun find2020PairProduct() : Pair<Int, Int?>? {
//    val numbers = getNumbers()
//    val complementMap = numbers.associateBy { 2020 - it }
//    return numbers.mapNotNull { number ->
//        if (complementMap[number] != null) Pair(number, complementMap[number])
//        else null
//    }.firstOrNull()
//}

fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? {
    // Map: sum - x -> x
    val complements = associateBy { sum - it }
    return firstNotNullOfOrNull { number ->
        val complement = complements[number]
        if (complement != null) Pair(number, complement) else null
    }
}

val complementPairs: Map<Int, Pair<Int, Int>?> =
    numbers.associateWith { numbers.findPairOfSum(2020 - it) }.filter { (key,value) -> value != null }


fun main() {
    print(complementPairs)
}



