package aron.nu.katabankocr;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import io.vavr.collection.List;

public class MainTest {

    @Test
    public final void testMain() {
        String pathToTestFiles = "./src/test/resources/";
        List<String> values = List.of("123456789", "111111111", "222222222", "333333333", "444444444", "555555555",
                "666666666", "777777777", "888888888", "999999999");
        assertThat(values.forAll(s -> s.equals(Main.readResultFor(get(pathToTestFiles + s)))), is(true));
    }
}
