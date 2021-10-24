package problems

import java.util.*

/**
 * A DoubleSet is a collection whose members are integers, and who can have one or two of each member.
 *
 * DoubleSet is initialized with variable arguments like other Kotlin Collections, and is backed by
 * a MutableMap. It supports add and subtract operations for Int members and other DoubleSets.
 */
class DoubleSet(vararg members: Int) {
    private var memberMap: MutableMap<Int, Int> = mutableMapOf()

    /**
     * When this class is constructed, add members into the backing map
     */
    init {
        members.forEach {
            this.plus(it)
        }
    }

    /**
     * Convert the map into a list of members corresponding with the count of each member
     */
    fun flatten(): List<Int> {
        return memberMap.toList().flatMap {
            Collections.nCopies(it.second, it.first)
        }
    }

    /**
     * Add a member
     */
    operator fun plus(value: Int): DoubleSet {
        memberMap[value] = if (memberMap[value] != null) 2 else 1
        return this
    }

    /**
     * Subtract a member
     */
    operator fun minus(value: Int): DoubleSet {
        if (memberMap[value] == 1) {
            memberMap.remove(value)
        } else if (memberMap[value] == 2) {
            memberMap[value] = 1
        }
        return this
    }

    /**
     * Add a DoubleSet to this DoubleSet
     */
    operator fun plus(ds: DoubleSet): DoubleSet {
        ds.flatten().forEach {this.plus(it)}
        return this
    }

    /**
     * Subtract a DoubleSet from this DoubleSet
     */
    operator fun minus(ds: DoubleSet): DoubleSet {
        ds.flatten().forEach {this.minus(it)}
        return this
    }

    override fun toString(): String =
        memberMap.toList().map { "{${it.first}: ${it.second}}" }.toString()
}