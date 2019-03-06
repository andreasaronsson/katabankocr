package nu.aron.katabankocr;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.vavr.collection.List;

class MainTest {

    @Test
    void testMain() {
        String pathToTestFiles = "./src/test/resources/";
        var values = List.of("123456789", "111111111", "222222222", "333333333", "444444444", "555555555",
                "666666666", "777777777", "888888888", "999999999");
        assertTrue(values.forAll(s -> s.equals(Main.readResultFor(get(pathToTestFiles + s)))));
    }
}
