import day5.Operation

/**
 * IntMachine from day 5 adjusted with second input parameter and returning output instead of printing.
 */
class IntMachine {
    fun run(inputCodes: MutableList<Int>, startInput: Int, secondInput: Int): Int {
        var manualInput = startInput
        var pointer = 0
        while (pointer < inputCodes.size) {
            val instruction = inputCodes[pointer]
            val paramMode1: Int = instruction / 100 % 10
            val paramMode2: Int = instruction / 1000 % 10

            val operation = Operation.getByOpCode(instruction % 100)

            if (operation == Operation.STOP) {
                break
            }

            when (operation) {
                Operation.ADD -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    inputCodes[inputCodes[pointer + 3]] = param1 + param2
                    pointer += 4
                }
                Operation.MULTIPLY -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    inputCodes[inputCodes[pointer + 3]] = param1 * param2
                    pointer += 4
                }
                Operation.SAVE -> {
                    inputCodes[inputCodes[pointer + 1]] = manualInput
                    manualInput = secondInput
                    pointer += 2
                }
                Operation.OUTPUT -> {
                    return getParamValue(pointer, paramMode1, 1, inputCodes)
                }
                Operation.JUMP_ON_TRUE -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    if (param1 != 0) {
                        pointer = getParamValue(pointer, paramMode2, 2, inputCodes)
                    } else {
                        pointer += 3
                    }
                }
                Operation.JUMP_ON_FALSE -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    if (param1 == 0) {
                        pointer = getParamValue(pointer, paramMode2, 2, inputCodes)
                    } else {
                        pointer += 3
                    }
                }
                Operation.LESS_THAN -> {
                    val param1 = getParamValue(pointer, paramMode1, 1, inputCodes)
                    val param2 = getParamValue(pointer, paramMode2, 2, inputCodes)
                    if (param1 < param2) {
                        inputCodes[getParamValue(pointer, 1, 3, inputCodes)] = 1
                    } else {
                        inputCodes[getParamValue(pointer, 1, 3, inputCodes)] = 0
                    }
                    pointer += 4
                }
                Operation.EQUALS -> {
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
}