import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution {

    companion object {
        @JvmStatic
        fun getWords1digit() = Stream.of(
            Arguments.of("1", listOf<String>("1")),
            Arguments.of("2", listOf<String>("A", "B", "C")),
            Arguments.of("3", listOf<String>("D", "E", "F")),
            Arguments.of("4", listOf<String>("G", "H", "I")),
            Arguments.of("5", listOf<String>("J", "K", "L")),
            Arguments.of("6", listOf<String>("M", "N", "O")),
            Arguments.of("7", listOf<String>("P", "Q", "R", "S")),
            Arguments.of("8", listOf<String>("T", "U", "V")),
            Arguments.of("9", listOf<String>("W", "X", "Y", "Z")),
            Arguments.of("0", listOf<String>("0")),
        )

        @JvmStatic
        fun getWords2digit() = Stream.of(
            Arguments.of("12", listOf<String>("1A",  "1B", "1C")),
            Arguments.of("23", listOf<String>("AD", "AE", "AF", "BD", "BE", "BF", "CD", "CE", "CF")),
        )
    }

    val numberToLetters: Map<String, List<String>> = mapOf(
        "2" to listOf("A", "B", "C"),
        "3" to listOf("D", "E", "F"),
        "4" to listOf("G", "H", "I"),
        "5" to listOf("J", "K", "L"),
        "6" to listOf("M", "N", "O"),
        "7" to listOf("P", "Q", "R", "S"),
        "8" to listOf("T", "U", "V"),
        "9" to listOf("W", "X", "Y", "Z")
    )

    fun getWords(phoneNumber: String): List<String> {
        val words = mutableListOf<String>()
        return recursiveLetters(words, phoneNumber)
    }

    private fun recursiveLetters(words: List<String>, phoneNumber: String): List<String> {
        if  (phoneNumber.isBlank()) return words

        val digit = phoneNumber[0].toString()
        val digitLetters = numberToLetters.getOrDefault(digit, listOf(digit))

        val newWords = mutableListOf<String>()
        digitLetters.forEach { letter ->
            if (words.isEmpty()) {
                newWords.add(letter)
            } else {
                newWords.addAll(words.map { w -> w + letter })
            }
        }
        return recursiveLetters(newWords, phoneNumber.drop(1))
    }

    @Test
    fun `should return an empty list if the phoneNumber is blank`() {
        val words = Solution().getWords(phoneNumber = " ")
        assertTrue(words.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("getWords1digit")
    fun `should return a list if the phoneNumber has only one digit`(
        phoneNumber: String, expected: List<String>
    ) {
        val words = Solution().getWords(phoneNumber = phoneNumber)
        assertTrue(words.containsAll(expected))
        assertTrue(expected.containsAll(words))
    }

    @ParameterizedTest
    @MethodSource("getWords2digit")
    fun `should return a list if the phoneNumber has only two digits`(
        phoneNumber: String, expected: List<String>
    ) {
        val words = Solution().getWords(phoneNumber = phoneNumber)
        assertTrue(words.containsAll(expected))
        assertTrue(expected.containsAll(words))
    }

    @Test
    fun `should return FLOWERS for 3569377`() {
        val words = Solution().getWords(phoneNumber = "3569377")
        assertTrue(words.contains("FLOWERS"))
    }

    @Test
    fun `should return MY1BANK for 3569377`() {
        val words = Solution().getWords(phoneNumber = "6912265")
        assertTrue(words.contains("MY1BANK"))
    }
}
