package problems

object CapitalizeNth {
    private const val ASCIIZERO = 48
    private const val ASCIININE = 57
    private const val ASCIIUPPERCASEA = 65
    private const val ASCIILOWERCASEZ = 122

    /**
     * Provided a string, return a version that with each every nth alphanumeric character capitalized.
     *
     * Note: alphanumeric is defined as matching the set of [a-z][A-Z][0-9] and is not internationalized
     *
     * Note: Although this could support negative values for n, or values of n that are greater than the
     * size of the string, I decided not to implement that. Such an implementation would also require a
     * decision whether we cycle through the string repeatedly, or if that's configurable. I think
     * that's out of scope.
     */
    fun String.capitalizeNthCharacter(n: Int): String {
        // A better test than `n > this.length` would be an iteration through the string to check
        // alphanumeric character counts vs n, but the loop below effectively does that as well. This
        // is just a quick check
        if (this.isBlank() || n > this.length) return this
        if (n <= 0) throw Exception("n must be greater than or equal to 1")

        var count = 0
        return this.fold("") { acc, char ->
            if (char.isNumeric() || char.isAlpha()) count += 1
            var s = char.toString()
            if (char.isAlpha()) {
                s = if (count > 0 && count % n == 0) s.uppercase() else s.lowercase()
            }
            acc + s
        }
    }

    /**
     * Kotlin extension functions that add O(1) ASCII character evaluation to the Char class.
     * A more Kotlin idiomatic way to write these is to use the IntRange type, though that introduces
     * O(m) complexity which can slow down the performance of `capitalizeNthCharacter` given very long
     * strings.
     */
    private fun Char.isNumeric(): Boolean = this.code >= ASCIIZERO && this.code <= ASCIININE
    private fun Char.isAlpha(): Boolean = this.code >= ASCIIUPPERCASEA && this.code <= ASCIILOWERCASEZ
}