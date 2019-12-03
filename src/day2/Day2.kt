package day2

class Day2 {
    private val intCodes = readFile("input.txt").split(",")
        .map(String::toInt)
        .toMutableList()

    init {
        findTarget()
    }

    private fun calculateInts(inputCodes: MutableList<Int>): Int {
        for (pointer in 0 until inputCodes.size step 4) {
            val operation = inputCodes[pointer]
            if (operation == 99) {
                break
            }
            val leftValue = inputCodes[inputCodes[pointer + 1]]
            val rightValue = inputCodes[inputCodes[pointer + 2]]
            val destination = inputCodes[pointer + 3]
            when (operation) {
                1 -> inputCodes[destination] = leftValue + rightValue
                2 -> inputCodes[destination] = leftValue * rightValue
            }
        }
        return inputCodes[0]
    }

    private fun findTarget() {
        val target = 19690720
        for (noun in 0 until 100) {
            for (verb in 0 until 100) {
                val intCodeCopy = intCodes.toMutableList()
                intCodeCopy[1] = noun
                intCodeCopy[2] = verb
                if (target == calculateInts(intCodeCopy)) {
                    val result = 100 * noun + verb
                    println(result)
                }
            }
        }
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLine()
}
