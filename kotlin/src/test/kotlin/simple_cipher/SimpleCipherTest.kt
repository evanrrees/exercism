package simple_cipher

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        RandomKeyCipherTest::class,
        IncorrectKeyCipherTest::class,
        SubstitutionCipherTest::class
)
internal class SimpleCipherTest {
}
