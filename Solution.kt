import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Solution {

    fun getWords(phoneNumber: String): List<String> {
        return emptyList()
    }

    @Test
    fun `should return an empty list if the phoneNumber is blank`() {
        val words = Solution().getWords(phoneNumber = " ")
        assertTrue(words.isEmpty())
    }
}
