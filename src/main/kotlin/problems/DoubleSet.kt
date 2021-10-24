package problems

data class Member(val value: Int, var count: Int) {
    init {
        if (count <= 0 || count > 2) throw Exception("A member must have 0 < count <= 2")
    }
}

// todo: make toString overrides that use correct kinds of brackets
// todo: does it make sense to implement this as a map internally? that way we can get O(1) on plus/minus...
// nah, b/c means read operations will be O(n), or it'll take 2x space
// todo: should we take a vararg instead of members?
data class DoubleSet(val members: MutableList<Member> = mutableListOf()) {
    operator fun plus(member: Member): DoubleSet {
        var exists = false
        members.forEachIndexed { i, m ->
            if (m.value == member.value) {
                try {
                    members[i] = m.copy(count = m.count + member.count)
                } catch (e: Exception) {
                    members[i] = m.copy(count = 2)
                }
                exists = true
            }
        }
        if (!exists) {
            members += member
        }
        return this
    }

    operator fun plus(doubleSet: DoubleSet): DoubleSet {
        doubleSet.members.forEach {this.plus(it)}
        return this
    }

    operator fun minus(member: Member): DoubleSet {
        var memberToDelete: Member? = null
        members.forEachIndexed { i, m ->
            if (m.value == member.value) {
                try {
                    members[i] = m.copy(count = m.count - member.count)
                } catch (e: Exception) {
                    memberToDelete = m
                }
            }
        }
        memberToDelete?.let {
            members -= it
        }
        return this
    }

    operator fun minus(doubleSet: DoubleSet): DoubleSet {
        doubleSet.members.forEach {this.minus(it)}
        return this
    }
}