package aoc.day2

import java.io.File

const val FILE_PATH = "src/main/kotlin/aoc/day2/input.txt"

data class Policy(val minCharCount: Int, val maxCharCount: Int, val char: Char)

data class PolicyWithExample(val policy: Policy, val example: String)

val REGEX = """([0-9]{1,})-([0-9]{1,})\s([a-z]):\s([a-z]*)""".toRegex()

fun parseLine(line: String) = REGEX.find(line)?.destructured

val violationCount =
    File(FILE_PATH)
        .readLines()
        .mapNotNull {
        parseLine(it)?.let { (minCharCount, maxCharCount, char, example) ->
            PolicyWithExample(
                Policy(minCharCount.toInt(), maxCharCount.toInt(), char[0]),
                example
            )
        }
    }.filter { policyWithExample ->
        val range = policyWithExample.policy.minCharCount..policyWithExample.policy.maxCharCount
        val charOccurrences = policyWithExample.example.count { it == policyWithExample.policy.char }
        charOccurrences in range
    }.size


fun main() {
    // listOfViolations.forEach{ println(it)}
    print(violationCount)
}
