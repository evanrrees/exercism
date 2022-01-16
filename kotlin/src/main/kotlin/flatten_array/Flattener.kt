package flatten_array

object Flattener {

    fun flatten(source: Collection<Any?>): List<Any> = source
        .flatMap { if (it is List<*>) flatten(it) else listOf(it) }.filterNotNull()

}
