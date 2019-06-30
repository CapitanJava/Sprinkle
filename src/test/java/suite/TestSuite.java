package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.SprinkleTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SprinkleTest.class,
})

public class TestSuite {

}
