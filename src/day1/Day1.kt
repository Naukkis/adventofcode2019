package day1

import java.math.BigDecimal
import java.math.RoundingMode

class Day1 {

    private val input = readFile("input.txt")

    init {
        val fuelRequirement = input.map { value -> value.toBigDecimal() }
            .map { value ->
                value.divide(BigDecimal(3), 0, RoundingMode.DOWN)
                    .subtract(BigDecimal(2))
            }
            .fold(BigDecimal.ZERO, BigDecimal::add)
        println(fuelRequirement)
    }

private fun readFile(fileName: String)
        = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}