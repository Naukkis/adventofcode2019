package day6

class Day6 {
    private val input = readFile("input.txt")
    private val orbiterToPlanet: Map<String, String> =
        input.map { orbit -> orbit.split(")")[1] to orbit.split(")")[0] }.toMap()
    private var youPath = mutableListOf<String?>()
    private var sanPath = mutableListOf<String?>()

    init {
        // part 1
        var orbiterPathCount = 0
        for (orbiter in orbiterToPlanet.keys) {
            orbiterPathCount += getOrbitPathCount(orbiter)
        }
        println(orbiterPathCount)

        // part 2
        getOrbiters(orbiterToPlanet["YOU"], youPath)
        getOrbiters(orbiterToPlanet["SAN"], sanPath)
        val firstCommon = sanPath.last { value -> youPath.contains(value) }

        println(sanPath.size - 1 - sanPath.indexOf(firstCommon) + youPath.size - 1 - youPath.indexOf(firstCommon))
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

    private fun getOrbiters(planet: String?, orbiters: MutableList<String?>) {
        if (orbiterToPlanet[planet] == null) {
            return
        } else {
            getOrbiters(orbiterToPlanet[planet], orbiters)
            orbiters.add(planet)
        }
    }

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}