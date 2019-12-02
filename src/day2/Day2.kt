package day2

class Day2 {
    private val input = readFile("input.txt")

    init {
        val intCodes = input.split(",")
            .map(String::toInt)
            .toMutableList()

        for (pointer in 0 until intCodes.size step 4) {
            val operation = intCodes[pointer]
            if (operation == 99) {
                break
            }
            val leftValue = intCodes[intCodes[pointer + 1]]
            val rightValue = intCodes[intCodes[pointer + 2]]
            val destination = intCodes[pointer + 3]
            when (operation) {
                1 -> intCodes[destination] = leftValue + rightValue
                2 -> intCodes[destination] = leftValue * rightValue
            }
        }
        println(intCodes[0])
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLine()
}
