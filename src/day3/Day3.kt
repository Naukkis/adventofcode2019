package day3

import java.util.stream.Collectors

class Day3 {

    private val input = readFile("input.txt")

    init {
        val wire1 = input[0].split(",")
        val wire2 = input[1].split(",")


        val wire1Coords = getPathToEndPoint(wire1)
        val wire2Coords = getPathToEndPoint(wire2)
        val shortestSteps = wire1Coords.parallelStream()
            .filter { value -> wire2Coords.contains(value) }
            .collect(Collectors.toSet())
            .map { intersection ->
                wire1Coords[wire1Coords.indexOf(intersection)].stepCount + wire2Coords[wire2Coords.indexOf(intersection)].stepCount
            }
            .min()

        println(shortestSteps)
    }

    private fun getPathToEndPoint(wire: List<String>): List<CoordinatesWithStepCount> {
        var x = 0
        var y = 0
        val listOfCoords = mutableListOf<CoordinatesWithStepCount>()
        var stepCount = 0
        for (instructions in wire) {
            val xStart = x
            val yStart = y
            val direction = instructions[0]
            val steps = Integer.valueOf(instructions.slice(IntRange(1, instructions.length - 1)))
            when (direction) {
                'R' -> x += steps
                'L' -> x -= steps
                'U' -> y += steps
                'D' -> y -= steps
            }
            if (x != xStart) {
                if (xStart < x) {
                    for (between in xStart + 1..x) {
                        stepCount += 1
                        listOfCoords.add(CoordinatesWithStepCount(between, y, stepCount))
                    }
                } else {
                    for (between in xStart - 1 downTo x) {
                        stepCount += 1
                        listOfCoords.add(CoordinatesWithStepCount(between, y, stepCount))
                    }
                }
            } else {
                if (yStart < y) {
                    for (between in yStart + 1..y) {
                        stepCount += 1
                        listOfCoords.add(CoordinatesWithStepCount(x, between, stepCount))
                    }
                } else {
                    for (between in yStart - 1 downTo y) {
                        stepCount += 1
                        listOfCoords.add(CoordinatesWithStepCount(x, between, stepCount))
                    }
                }

            }

        }
        return listOfCoords
    }

    private data class CoordinatesWithStepCount(val x: Int, val y: Int, val stepCount: Int) {
        override fun equals(other: Any?): Boolean {
            if (other is CoordinatesWithStepCount) {
                if (this.x == other.x && this.y == other.y) {
                    return true
                }
            }
            return false
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}