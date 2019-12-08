package day7

import IntMachine

class Day7 {

    private fun readFile(fileName: String) = this::class.java.getResourceAsStream(fileName).bufferedReader().readLine()

    private val intCodes = readFile("input.txt").split(",")
        .map(String::toInt)
        .toMutableList()

    init {
        val permutations = getPermutations(arrayOf(0, 1, 2, 3, 4))
        var maxThrusterSignal = 0
        var phaseSettingSequence = mutableListOf<Int>()
        var result: Int
        for (permutation in permutations) {
            val intCodes0 = intCodes.toMutableList()
            val amplifierResult0 = IntMachine().run(intCodes0, permutation[0], 0)
            val amplifierResult1 = IntMachine().run(intCodes0, permutation[1], amplifierResult0)
            val amplifierResult2 = IntMachine().run(intCodes0, permutation[2], amplifierResult1)
            val amplifierResult3 = IntMachine().run(intCodes0, permutation[3], amplifierResult2)
            result = IntMachine().run(intCodes0, permutation[4], amplifierResult3)
            if (result > maxThrusterSignal) {
                maxThrusterSignal = result
                phaseSettingSequence = permutation.toMutableList()
            }
        }
        println("$maxThrusterSignal from phase setting sequence $phaseSettingSequence")
    }

    private fun getPermutations(word: Array<Int>): List<List<Int>> {
        val list = word.toMutableList()
        return permute(list)
    }

    private fun <Int> permute(list: List<Int>): List<List<Int>> {
        if (list.size == 1) return listOf(list)
        val perms = mutableListOf<List<Int>>()
        val sub = list[0]
        for (perm in permute(list.drop(1)))
            for (i in 0..perm.size) {
                val newPerm = perm.toMutableList()
                newPerm.add(i, sub)
                perms.add(newPerm)
            }
        return perms
    }
}