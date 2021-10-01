package aoc.day6

import java.io.File

const val FILE_PATH = "src/main/kotlin/aoc/day6/input.txt"

val answers: List<String> = File(FILE_PATH).bufferedReader().readLines()


val processed = answers.fold(mutableListOf<MutableSet<Char>>()) { acc, el ->
    if (el.isNotBlank()) {
        if (acc.isEmpty()) acc.add(mutableSetOf<Char>())
        acc.last().addAll(el.toSet())
    } else {
        acc.add(mutableSetOf())
    }
    acc
}

val consensus = answers.foldIndexed(mutableListOf<MutableSet<Char>>()) { index, acc, el ->

    if (el.isNotBlank()) {
        if (acc.isEmpty()) {
            acc.add(mutableSetOf<Char>())
        }


        if ((index - 1 !in answers.indices || answers[index - 1].isBlank()) && acc.last().isEmpty()){
            acc.last().addAll(el.toSet())
        }
        val set = acc.last() intersect el.toSet()
        acc.last().clear()
        acc.last().addAll(set)
    } else {
        acc.add(mutableSetOf())
    }
    acc
}

fun main() {
    println(processed.sumOf { it.size })
    println(consensus.sumOf { it.size })
}