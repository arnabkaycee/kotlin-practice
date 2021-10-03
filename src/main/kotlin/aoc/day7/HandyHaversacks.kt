package aoc.day7

import java.io.File
import java.lang.IllegalArgumentException


const val FILE_PATH = "src/main/kotlin/aoc/day7/input.txt"

val answers: List<String> = File(FILE_PATH).bufferedReader().readLines()


fun mapToList(s: String) =
    s.replace("bags*\\.*".toRegex(), "") // split the text by bags
        .split("contain|,".toRegex()) // then split it by command or 'contains' word
        .map { it.trim() } // trim
        .map {
            "^([0-9]*)([a-z\\s]*)\$" // destructure the number and the color into lists
                .toRegex()
                .find(it)?.destructured?.toList()
                ?: throw IllegalArgumentException("")
        }


val map = mutableMapOf<String, MutableList<Pair<String, Int>>>()
fun transformToBagRule(aRule: List<List<String>>) {
    val bagColor = aRule.first()[1]
    aRule
        .drop(1)
        .forEach { (qty, color) ->
            map.putIfAbsent(bagColor, mutableListOf())
            if (qty.isNotBlank() && color.trim() != "no other") {
                map[bagColor]?.add(color.trim() to qty.trim().toInt())
            }
        }
}


// PART 1
fun findOuterBagColors(subjectColor: String, allowedList: MutableList<String>): MutableList<String> {
    map.forEach { entry ->
        entry.value.find { it.first == subjectColor }?.first?.let {
            allowedList.add(entry.key)
            findOuterBagColors(entry.key, allowedList)
        }
    }
    return allowedList
}

// PART 2

fun findNumberOfInnerBags(subjectColor: String, initial: Int): Int {
    val filtered = map.filter { entry ->
        entry.key == subjectColor
    }

    var found = initial
    return if (filtered.size == 1 && filtered[subjectColor]?.size == 0)
        1
    else {
        val flatMap = filtered.values.flatMap { filteredEntries ->
            filteredEntries.map { colorQuantityPair ->
                colorQuantityPair.second * findNumberOfInnerBags(colorQuantityPair.first, found)
            }
        }
        found + flatMap.sum() + filtered.size
    }
}


fun main() {

    val repeatTimes = 1
    var subjectColor = "shiny gold"

    answers.map {
        mapToList(it)
    }.map {
        transformToBagRule(it)
    }
    val findOuterBagColors = findOuterBagColors(subjectColor, mutableListOf())

    //println(findOuterBagColors.toSet())
    println(findOuterBagColors.toSet().size)

    println(findNumberOfInnerBags(subjectColor, 0) - 1) // -1 because exclude the outer shiny bag

//
//    val allowedColors = map[subjectColor]?.map {
//        it.first
//    }
//
//    println(allowedColors)
//    println(allowedColors?.size)

//    map.forEach {
//        println("${it.key}, ${it.value}")
//    }


}
