import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Kotlin99EasyKtTest {

    @Test
    fun testReverse() {
        val list = listOf("first", "second", "third")
        val reversedList = reverseList(list)
        assertThat(reversedList, equalTo(listOf("third", "second", "first")) )
    }


    @Test
    fun testPalindrome() {
        val l1 = listOf(1,2,3,2,1)
        val l2 = listOf(1,2,2,1)
        val l3 = listOf(1,2,3,4)
        val l5 = listOf(1)
        val l4 = emptyList<Int>()

        assertTrue { isPalindrome(l1) }
        assertTrue { isPalindrome(l2) }
        assertTrue { isPalindrome(l5) }

        assertFalse { isPalindrome(l3) }
        assertFalse { isPalindrome(l4) }
    }

    @Test
    fun testCombination(){
        val combineLists = combinations(2, "abc".toList())
        print(combineLists)
    }
}


