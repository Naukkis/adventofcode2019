package day6

class Day6 {
    private val input = readFile("input.txt")
    private val orbiterToPlanet: Map<String, String> =
        input.map { orbit -> orbit.split(")")[1] to orbit.split(")")[0] }.toMap()

    init {
        var orbiterPathCount = 0
        for (orbiter in orbiterToPlanet.keys) {
            orbiterPathCount += getOrbitPathCount(orbiter)
        }
        println(orbiterPathCount)
    }

    private fun getOrbitPathCount(planet: String?): Int {
        var count = 0
        if (orbiterToPlanet[planet] == null) {
            return 0
        } else {
            count++
            count += getOrbitPathCount(orbiterToPlanet[planet])
        }
        return count
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}