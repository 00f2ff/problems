package problems

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals


class DoubleSetTest {

    @Test
    fun `Member constructor requires counts of one or two`() {
        Member(1, 1)
        Member(2,2)
        assertThrows<Exception> {
            Member(3,0)
        }
        assertThrows<Exception> {
            Member(4,-1)
        }
        assertThrows<Exception> {
            Member(5,-3)
        }
    }

    @Test
    fun `can add elements to DoubleSets`() {
        var set = DoubleSet()
        val element1 = Member(0,1)
        val element2 = Member(0,2)
        val element3 = Member(1,1)
        val element4 = Member(2,2)

        set += element1
        assertEquals(listOf(element1), set.members)
        set += element2
        assertEquals(listOf(element2), set.members)
        set += element3
        assertEquals(listOf(element2, element3), set.members)
        set += element4
        assertEquals(listOf(element2, element3, element4), set.members)
    }

    @Test
    fun `can add DoubleSets together`() {
        var set1 = DoubleSet(mutableListOf(
            Member(1, 2), Member(2,1)
        ))
        val set2 = DoubleSet(mutableListOf(
            Member(1, 1), Member(2,1), Member(-3,1)
        ))
        set1 += set2
        val expected = DoubleSet(mutableListOf(
            Member(1, 2), Member(2,2), Member(-3,1)
        ))
        assertEquals(expected, set1)
    }

    @Test
    fun `can remove elements from DoubleSets`() {
        val element1 = Member(0,1)
        val element2 = Member(0,2)
        val element3 = Member(1,1)
        val element4 = Member(2,2)
        var set = DoubleSet(mutableListOf(element2, element3, element4))
        set -= element1
        assertEquals(mutableListOf(element1, element3, element4), set.members)
        set -= element3
        assertEquals(mutableListOf(element1, element4), set.members)
        set -= element4
        assertEquals(mutableListOf(element1), set.members)
    }

    @Test
    fun `can add subtract DoubleSets`() {
        var set1 = DoubleSet(mutableListOf(
            Member(1, 2), Member(2,1)
        ))
        val set2 = DoubleSet(mutableListOf(
            Member(1, 1), Member(2,1), Member(-3,1)
        ))
        set1 -= set2
        val expected = DoubleSet(mutableListOf(
            Member(1, 1)
        ))
        assertEquals(expected, set1)
    }


}