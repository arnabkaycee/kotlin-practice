fun <T> reverseList(list: List<T>): List<T> {
    return list.reversed()
}


fun <T> isPalindrome(list: List<T>): Boolean {
    return if (list.isEmpty()) false else
        when (list.size % 2 == 0) {
            true -> list.subList(0, list.size / 2) == list.subList(list.size / 2, list.size).reversed()
            false -> list.subList(0, list.size / 2) == list.subList((list.size / 2) + 1, list.size).reversed()
        }
}


//fun <T> combineLists(
//    list: List<T>,
//    c: Int,
//    listOfSet: MutableSet<MutableSet<T>> = mutableSetOf(),
//    depth: Int = 0
//): Set<Set<T>> {
//    return if (list.isEmpty()) listOfSet
//    else {
//        list.fold(listOfSet) { listOfSet, element ->
//            if (depth < c)combineLists(list - element, c, listOfSet, depth + 1)
//            listOfSet.add(mutableSetOf())
//            listOfSet.map { it.add(element) }
//            listOfSet
//        }
//    }
//}

fun <T> combinations(n: Int, list: List<T>): List<List<T>> =
    if (n == 0) listOf(emptyList())
    else list.flatMapTails { subList ->
        println("sublist -> $subList, n = $n")
        val map = combinations(n - 1, subList.tail()).map { (it + subList.first()) }
        println("map -> $map")
        map
    }

private fun <T> List<T>.flatMapTails(f: (List<T>) -> (List<List<T>>)): List<List<T>> {

    return if (isEmpty()) emptyList()
    else {
        println("this -> $this")
        println("this -> $this")
        f(this) + this.tail().flatMapTails(f)
    }
}