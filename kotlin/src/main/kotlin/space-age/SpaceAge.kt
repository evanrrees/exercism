package `space-age`

import kotlin.math.round
import kotlin.math.pow

class SpaceAge(private val ageInSeconds: Int) {

    private fun Double.roundTo(decimals: Int = 2) = 10.0.pow(decimals).let { round(this * it) / it }
    
    fun onEarth()      = (ageInSeconds / Planet.Earth.periodInSeconds).roundTo()
    fun onMercury()    = (ageInSeconds / Planet.Mercury.periodInSeconds).roundTo()
    fun onVenus()      = (ageInSeconds / Planet.Venus.periodInSeconds).roundTo()
    fun onMars()       = (ageInSeconds / Planet.Mars.periodInSeconds).roundTo()
    fun onJupiter()    = (ageInSeconds / Planet.Jupiter.periodInSeconds).roundTo()
    fun onSaturn()     = (ageInSeconds / Planet.Saturn.periodInSeconds).roundTo()
    fun onUranus()     = (ageInSeconds / Planet.Uranus.periodInSeconds).roundTo()
    fun onNeptune()    = (ageInSeconds / Planet.Neptune.periodInSeconds).roundTo()

    private enum class Planet(val periodInSeconds: Double) {
        Earth(31557600.0),
        Mercury(Earth.periodInSeconds * 0.2408467),
        Venus(Earth.periodInSeconds * 0.61519726),
        Mars(Earth.periodInSeconds * 1.8808158),
        Jupiter(Earth.periodInSeconds * 11.862615),
        Saturn(Earth.periodInSeconds * 29.447498),
        Uranus(Earth.periodInSeconds * 84.016846),
        Neptune(Earth.periodInSeconds * 164.79132)
    }
    
}
