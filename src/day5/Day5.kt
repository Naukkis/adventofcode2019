package day5

import day5.Operation.*

class Day5 {
    private val intCodes = readFile("input.txt").split(",")
        .map(String::toInt)
        .toMutableList()

    init {
        runIntMachine(intCodes)
    }

    private fun runIntMachine(inputCodes: MutableList<Int>): Int {
        val input = 5
        var pointer = 0
        while (pointer < inputCodes.size) {
            val instruction = inputCodes[pointer]
            val paramMode1: Int = instruction / 100 % 10
            val paramMode2: Int = instruction / 1000 % 10

            val operation = Operation.getByOpCode(instruction % 100)

            if (operation == STOP) {
                break
            }

            when (operation) {
                ADD -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    inputCodes[inputCodes[pointer + 3]] = param1 + param2
                    pointer += 4
                }
                MULTIPLY -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    inputCodes[inputCodes[pointer + 3]] = param1 * param2
                    pointer += 4
                }
                SAVE -> {
                    inputCodes[intCodes[pointer + 1]] = input
                    pointer += 2
                }
                OUTPUT -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    println(param1)
                    pointer += 2
                }
                JUMP_ON_TRUE -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    if (param1 != 0) {
                        pointer = getParamValue(pointer, paramMode2, 2, inputCodes)
                    } else {
                        pointer += 3
                    }
                }
                JUMP_ON_FALSE -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    if (param1 == 0) {
                        pointer = getParamValue(pointer, paramMode2, 2, inputCodes)
                    } else {
                        pointer += 3
                    }
                }
                LESS_THAN -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    if (param1 < param2) {
                        inputCodes[getParamValue(pointer, 1, 3, inputCodes)] = 1
                    } else {
                        inputCodes[getParamValue(pointer, 1, 3, inputCodes)] = 0
                    }
                    pointer += 4
                }
                EQUALS -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    if (param1 == param2) {
                        inputCodes[getParamValue(pointer, 1, 3, inputCodes)] = 1
                    } else {
                        inputCodes[getParamValue(pointer, 1, 3, inputCodes)] = 0
                    }
                    pointer += 4
                }

                else -> throw IllegalArgumentException()
            }

        }
        return inputCodes[0]
    }

    private fun getParamValue(pointer: Int, paramMode: Int, paramNumber: Int, inputCodes: MutableList<Int>): Int {
        return when (paramMode) {
            0 -> inputCodes[inputCodes[pointer + paramNumber]]
            1 -> inputCodes[pointer + paramNumber]
            else -> throw IllegalArgumentException()
        }
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLine()

}

enum class Operation(val opCode: Int) {
    ADD(1),
    MULTIPLY(2),
    SAVE(3),
    OUTPUT(4),
    JUMP_ON_TRUE(5),
    JUMP_ON_FALSE(6),
    LESS_THAN(7),
    EQUALS(8),
    STOP(99);

    companion object {
        fun getByOpCode(value: Int): Operation {
            for (operation in values()) {
                if (operation.opCode == value) {
                    return operation
                }
            }
            throw IllegalArgumentException()
        }
    }
}