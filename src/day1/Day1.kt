package day1

class Day1 {

    private val input = readFile("input.txt")

    init {
        val fuelRequirement = input.map { value -> calculateFuelRequirements(value.toInt()) }
            .sum()
        println(fuelRequirement)
    }

    private fun calculateFuelRequirements(mass: Int): Int {
        val initialFuel = divideByThreeScaleDownAndSubtractTwo(mass)
        return calculateAdditionalFuelRecursive(initialFuel)
    }

    private fun divideByThreeScaleDownAndSubtractTwo(value: Int): Int {
        return value / 3 - 2
    }

    private fun calculateAdditionalFuelRecursive(initialFuel: Int): Int {
        if (initialFuel < 0) {
            return 0
        }
        return initialFuel + calculateAdditionalFuelRecursive(divideByThreeScaleDownAndSubtractTwo(initialFuel))
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()

}

