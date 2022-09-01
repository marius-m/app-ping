package lt.markmerkk.ping

import kotlin.random.Random

/**
 * Provides random numbers
 */
class RNDProvider {

    private val random = Random.Default

    fun numInclusive(min: Int, max: Int): Int {
        return random.nextInt(min, max + 1)
    }
}