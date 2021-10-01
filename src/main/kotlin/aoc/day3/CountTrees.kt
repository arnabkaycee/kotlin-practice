package aoc.day3

import java.io.File
import kotlin.math.ceil
import kotlin.text.StringBuilder

const val FILE_PATH = "src/main/kotlin/aoc/day3/input.txt"

val terrain: List<String> = File(FILE_PATH).readLines()

fun extendTerrain(s: String, skip: Int): String {
    var str = StringBuilder(s)
    val iterableStringLength = s.length - 1
    return if (skip > iterableStringLength) {
        val times = ceil(skip.toDouble() / iterableStringLength.toDouble()).toInt()
        repeat(times-1) {
            str.append(s)
        }
        str.toString()
    } else str.toString()
}

fun replaceCharAt(string: String, char: Char, index: Int) =
    StringBuilder(string).also { it.setCharAt(index,char) }.toString()

fun countTrees(right: Int, down: Int): Int {
    var trees = 0
    var rowIndex = 0
    for (i in terrain.indices step down) {
        val rowString = terrain[i]
        val skip = if (rowIndex > 0) (rowIndex * right) else 0
        val traversingRowString = extendTerrain(rowString, skip)
        if (traversingRowString[skip] == '#') {
            ++ trees

            //println("${rowIndex + 1},${skip+1} -> ${replaceCharAt(traversingRowString,'T', skip)}")
        }
        ++ rowIndex
    }
    return trees
}

fun main() {

    println(countTrees(1, 1))
    println(countTrees(3, 1))
    println(countTrees(5, 1))
    println(countTrees(7, 1))
    println(countTrees(1, 2))

    //34897486

}