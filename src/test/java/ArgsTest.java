import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {

    @Test
    void should_parse_boolean_option_given_logging_is_true() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");

        assertTrue(option.getLogging());
    }

    @Test
    void should_parse_integer_option_logging_is_true() {
        BooleanOption option = Args.parse(BooleanOption.class, "");

        assertFalse(option.getLogging());
    }

    @Test
    void should_parse_integer_option() {
        IntegerOption option = Args.parse(IntegerOption.class, "-p", "8080");

        assertEquals(8080, option.getPort());
    }

    @Test
    void should_parse_string_option() {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr/logs");

        assertEquals("/usr/logs", option.getDirectory());
    }

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
