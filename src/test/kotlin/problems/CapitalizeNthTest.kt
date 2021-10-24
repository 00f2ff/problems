package problems

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import problems.CapitalizeNth.capitalizeNthCharacter

class CapitalizeNthTest {

    @Test
    fun `blank strings are required as-is`() {
        assertEquals("", "".capitalizeNthCharacter(1))
    }

    @Test
    fun `when n is greater than the length of the string, we return the string`() {
        val string = "hello"
        assertEquals(string, string.capitalizeNthCharacter(string.length + 1))
    }

    @Test
    fun `does not support 0-value n or less`() {
        assertThrows<Exception> {
            "hello".capitalizeNthCharacter(0)
        }
        assertThrows<Exception> {
            "hello".capitalizeNthCharacter(-1)
        }
    }

    @Test
    fun `should handle strings with mix of alphanumeric and non- characters`() {
        val string = "Aspiration.com"

        assertEquals("ASPIRATION.COM", string.capitalizeNthCharacter(1))
        assertEquals("aSpIrAtIoN.cOm", string.capitalizeNthCharacter(2))
        assertEquals("asPirAtiOn.cOm", string.capitalizeNthCharacter(3))
        assertEquals("aspIratIon.cOm", string.capitalizeNthCharacter(4))
        assertEquals("asPir0atIon.Com", "Aspir0ation.com".capitalizeNthCharacter(3))
    }

    @Test
    fun `should lowercase uppercase characters`() {
        assertEquals("aSpIrAtIoN", "ASPIRATION".capitalizeNthCharacter(2))
    }

    @Test
    fun `should do nothing to non-alphanumeric strings`() {
        val string = "!@#$%"
        assertEquals(string, string.capitalizeNthCharacter(3))
    }

    @Test
    fun `should do nothing to numeric strings`() {
        val string = "1234567890"
        assertEquals(string, string.capitalizeNthCharacter(2))
    }
}