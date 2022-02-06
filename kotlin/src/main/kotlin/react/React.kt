package react


internal class Reactor<T> {

    interface Subscription {
        fun cancel()
    }

    interface Cell<T> {
        var value: T
        val watchers: MutableSet<Cell<T>>

        fun notifyWatchers() {
            watchers.forEach { it.value }
        }

        fun watch(other: Cell<T>) = other.watchers.add(this)
    }

    inner class InputCell(value: T) : Cell<T> {

        override var value = value
            set(value) {
                if (field != value) {
                    field = value
                    notifyWatchers()
                }
            }

        override val watchers = mutableSetOf<Cell<T>>()
    }

    inner class ComputeCell(private vararg val watches: Cell<T>, val compute: (List<T>) -> T) : Cell<T> {

        init {
            watches.forEach(::watch)
        }

        override var value: T = compute(inputs)
            set(value) {
                if (field != value) {
                    field = value
                    callbacks.forEach { it() }
                    notifyWatchers()
                }
            }
            get() {
                value = compute(inputs)
                return field
            }

        override val watchers = mutableSetOf<Cell<T>>()

        private val inputs: List<T> get() = watches.map(Cell<T>::value)

        private val callbacks = mutableListOf<Callback>()

        fun addCallback(callback: (T) -> Unit) = Callback(callback).also(callbacks::add)

        inner class Callback(val callback: (T) -> Unit) : Subscription {

            operator fun invoke() = callback(value)

            override fun cancel() {
                callbacks.remove(this)
            }

        }
    }
}

