import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArgsTest {

    @Test
    @Disabled
    void should_parse_multiple_option_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.getLogging());
        assertEquals(8080, options.getPort());
        assertEquals("usr/logs", options.getDirectory());
    }


    @Test
    @Disabled
    void should_parse_multiple_option_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertEquals(new String[]{"this", "is", "a", "list"}, options.getGroup());
        assertEquals(new int[]{1, 2, -3, 5}, options.getDecimals());
    }
}
