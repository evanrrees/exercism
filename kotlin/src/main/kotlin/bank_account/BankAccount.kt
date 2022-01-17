package bank_account

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class BankAccount {

    private var open = true
    val balance get() = _balance
    private var _balance = 0L
        get() {
            check(open) { "Account is closed." }
            while (transactionQueue.isNotEmpty()) {
                field += transactionQueue.take()
            }
            return field
        }

    private val transactionQueue: BlockingQueue<Long> = LinkedBlockingQueue()

    fun adjustBalance(amount: Long) {
        check(open) { "Account is closed." }
        transactionQueue += amount
    }

    fun close() {
        open = false
    }
}
