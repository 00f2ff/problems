package problems

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class DoubleSetTest {

    @Test
    fun `DoubleSets round member counts down to 2`() {
        val ds = DoubleSet(1,1,1,2,2,3,-1,0)
        assertEquals(listOf(-1,0,1,1,2,2,3), ds.flatten().sorted())
    }

    @Test
    fun `can add elements to DoubleSets`() {
        var ds = DoubleSet()
        ds += 1
        assertEquals(listOf(1), ds.flatten())
        ds += 1
        assertEquals(listOf(1,1), ds.flatten())
        ds += 1
        assertEquals(listOf(1,1), ds.flatten())
        ds += -1
        assertEquals(listOf(-1,1,1), ds.flatten().sorted())
    }

    @Test
    fun `can add DoubleSets together`() {
        val ds1 = DoubleSet(1,1,2)
        val ds2 = DoubleSet(1,2,-3)
        assertEquals(listOf(-3,1,1,2,2), (ds1 + ds2).flatten().sorted())
    }

    @Test
    fun `can remove elements from DoubleSets`() {
        var ds = DoubleSet(0,0,1,2,2)
        ds -= 0
        assertEquals(listOf(0,1,2,2), ds.flatten().sorted())
        ds -= 1
        assertEquals(listOf(0,2,2), ds.flatten().sorted())
        ds -= 1
        assertEquals(listOf(0,2,2), ds.flatten().sorted())
        ds -= 2
        ds -= 2
        assertEquals(listOf(0), ds.flatten().sorted())
    }

    @Test
    fun `can add subtract DoubleSets`() {
        val ds1 = DoubleSet(1,1,2)
        val ds2 = DoubleSet(1,2,-3)
        assertEquals(listOf(1), (ds1 - ds2).flatten().sorted())
    }
}