package bank_account


internal class BankAccount {

    private var open = true

    @Volatile
    var balance = 0L
        get() {
            check(open) { "Account is closed." }
            return field
        }
        private set

    @Synchronized
    fun adjustBalance(amount: Long) {
        check(open) { "Account is closed." }
        balance += amount
    }

    fun close() {
        open = false
    }
}
