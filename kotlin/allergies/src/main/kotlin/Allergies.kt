/** Methods for decoding an allergy score.
 * @property score Base 2 encoded allergy score between 0 and 255.
 * @constructor Creates an Allergies instance.
 */
class Allergies(private val score: Int) {
    /** Returns a list of all allergens indicated by the [score].*/
    fun getList() = Allergen.values().filter(::isAllergicTo)
    /** Returns `true` if [allergen] is encoded in the [score], and `false` otherwise. */
    fun isAllergicTo(allergen: Allergen) = score shr allergen.ordinal and 1 == 1
}
