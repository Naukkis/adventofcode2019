package day4

class Day4 {

    private val start = 382345
    private val end = 843167

    init {
        var meetsCriteriaCount = 0
        for (option in start..end) {
            val charArray = option.toString()
            var hasDouble = false
            var tripleOrMoreChar = 'x'
            var nextIsGreater = true
            for ((i, char) in charArray.withIndex()) {
                if (i == charArray.length - 1) {
                    continue
                }
                if (char.toString().toInt() > charArray[i + 1].toString().toInt()) {
                    nextIsGreater = false
                    break
                }
                if (tripleOrMoreChar != char && char.toString().toInt() == charArray[i + 1].toString().toInt()) {
                    if (i + 1 <= charArray.length - 2) {
                        if (char.toString().toInt() == charArray[i + 2].toString().toInt()) {
                            tripleOrMoreChar = char
                            continue
                        }
                    }
                    hasDouble = true
                    continue
                }
            }
            if (hasDouble && nextIsGreater) {
                meetsCriteriaCount++
            }
        }

        println(meetsCriteriaCount)
    }
}

