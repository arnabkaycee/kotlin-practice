package aoc.day5

import java.io.File

const val FILE_PATH = "/Users/arnab/Documents/01_Work/11_kotlin/kotlin-aoc/src/main/kotlin/aoc/day5/input.txt"

val encodedSeats: List<String> = File(FILE_PATH).readLines()

fun convert(str: String, range: IntRange): Int {
    var seatRange = range
    str.forEach { char ->
        seatRange = when (char) {
            'F', 'L' -> IntRange(seatRange.first, (seatRange.last + seatRange.first) / 2)
            'B', 'R' -> IntRange(((seatRange.last + seatRange.first) / 2) + 1, seatRange.last)
            else -> seatRange
        }
    }
    return seatRange.first
}

fun main() {
    val seats = encodedSeats.map { encodedSeatNo ->
        val row = convert(encodedSeatNo.substring(0..6), 0..127)
        val col = convert(encodedSeatNo.substring(7..9), 0..7)
        (row * 8) + col
    }
    println((0..1023).toList() - seats)
//    print(seats.maxOrNull())
//    println(convert("FBFBBFF", 0..127))
//    println(convert("RLR", 0..7))
}
