package aoc.day4

import java.io.File

const val FILE_PATH = "src/main/kotlin/aoc/day4/input.txt"

val MANDATORY_PROPERTIES = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

val rawBuffer: List<String> = File(FILE_PATH).bufferedReader().readLines()

data class Document(val props: MutableList<Pair<String, String>> = mutableListOf())

fun processLine(line: String): List<Pair<String, String>> {
    val props = mutableListOf<Pair<String, String>>()
    line.split(" ").forEach { pair ->
        val (prop, value) = pair.split(":")
        props.add(prop to value)
    }
    return props
}

val documents =
    rawBuffer.fold(mutableListOf<Document>()) { documentCollection, line ->
        if (line.isNotBlank()) {
            // case for the first line
            if (documentCollection.isEmpty()) documentCollection.add(Document())
            val currentDocument = documentCollection.last()
            currentDocument.props.addAll(processLine(line))
        } else {
            // found a blank line, indicates a new doc
            documentCollection.add(Document())
        }
        documentCollection
    }


// property validations [START]

fun isYear(yearString: String, minValue: Int, maxValue: Int) =
    yearString.matches("[0-9]{4}".toRegex()) && yearString.toInt() in minValue..maxValue

fun isHeight(value: String): Boolean {
    return "([0-9]{2,3})(in|cm)".toRegex().find(value)?.destructured?.let { (heightString, unit) ->
        when (unit) {
            "cm" -> heightString.toInt() in 150..193
            "in" -> heightString.toInt() in 59..76
            else -> false
        }
    } ?: false
}

// property validations [END]

fun isValidDocumentProperty(propPair: Pair<String, String>): Boolean {
    val (prop, value) = propPair
    return when (prop) {
        "byr" -> isYear(value, 1920, 2002)
        "iyr" -> isYear(value, 2010, 2020)
        "eyr" -> isYear(value, 2020, 2030)
        "hgt" -> isHeight(value)
        "hcl" -> "#[0-9a-f]{6}".toRegex().matches(value)
        "ecl" -> "amb|blu|brn|gry|grn|hzl|oth".toRegex().matches(value)
        "pid" -> "[0-9]{9}".toRegex().matches(value)
        else -> true    //ignore other properties
    }
}


fun main() {
    val validDocsCount = documents.count { doc ->
        doc.props.map { it.first }.containsAll(MANDATORY_PROPERTIES) &&
                doc.props.all { docProp -> isValidDocumentProperty(docProp) }
    }
    print("No of valid Docs: $validDocsCount")
}

