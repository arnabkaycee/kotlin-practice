import java.util.*

fun <T> List<T>.tail(): List<T> = drop(1)

fun <T> Array<out T>.tail(): List<T> = drop(1)

fun <T> Iterable<T>.tail(): List<T> = drop(1)

fun <T> List<T>.permutations(): List<List<T>> {
    if (size <= 1) return listOf(this)
    val head = first()
    return tail().permutations().flatMap { tailPermutation ->
        (0..tailPermutation.size).map { i ->
            LinkedList(tailPermutation).apply { add(i, head) }
        }
    }
}

fun <T> List<T>.permutationsSeq(): Sequence<List<T>> {
    if (size <= 1) return sequenceOf(this)
    val head = first()
    return tail().permutationsSeq().flatMap { tailPermutation ->
        (0..tailPermutation.size).asSequence().map { i ->
            LinkedList(tailPermutation).apply { add(i, head) }
        }
    }
}

fun <E> List<List<E>>.transpose(): List<List<E>> {
    if (isEmpty()) return this

    val width = first().size
    require(all { it.size == width }) { "All nested lists must have the same size, but sizes were ${map { it.size }}" }
    return (0 until width).map { col ->
        (0 until size).map { row -> this[row][col] }
    }
}

