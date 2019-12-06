package day3

import java.lang.Integer.min
import java.util.stream.Collector
import java.util.stream.Collectors
import kotlin.math.abs


class Day3 {

    private val input = readFile("input.txt")

    init {
        val wire1 = input[0].split(",")
        val wire2 = input[1].split(",")

        val wire1Coords = getEndPointCoordinates(wire1)
        val wire2Coords = getEndPointCoordinates(wire2)
        val shortestManhattanDistance = wire1Coords.parallelStream()
            .filter { value -> wire2Coords.contains(value) }
            .collect(Collectors.toSet())
            .minBy { value -> abs(value.first) + abs(value.second) }


        val distance = (abs(shortestManhattanDistance?.first ?: 0)) + abs((shortestManhattanDistance?.second ?: 0))
        println(distance)
    }

    private fun getEndPointCoordinates(wire: List<String>): List<Pair<Int, Int>> {
        var x = 0
        var y = 0
        val listOfCoords = mutableListOf<Pair<Int, Int>>()
        for (direction in wire) {
            val xStart = x
            val yStart = y
            when (direction[0]) {
                'R' -> x += Integer.valueOf(direction.slice(IntRange(1, direction.length - 1)))
                'L' -> x -= Integer.valueOf(direction.slice(IntRange(1, direction.length - 1)))
                'U' -> y += Integer.valueOf(direction.slice(IntRange(1, direction.length - 1)))
                'D' -> y -= Integer.valueOf(direction.slice(IntRange(1, direction.length - 1)))
            }
            if (x != xStart) {
                if (xStart < x) {
                    for (between in xStart + 1..x) {
                        listOfCoords.add(Pair(between, y))
                    }
                } else {
                    for (between in xStart - 1 downTo x) {
                        listOfCoords.add(Pair(between, y))
                    }
                }

            } else {
                if (yStart < y) {
                    for (between in yStart + 1..y) {
                        listOfCoords.add(Pair(x, between))
                    }
                } else {
                    for (between in yStart - 1 downTo y) {
                        listOfCoords.add(Pair(x, between))
                    }
                }

            }

        }
        return listOfCoords
    }


    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}