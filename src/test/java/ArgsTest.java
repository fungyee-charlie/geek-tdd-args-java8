import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {

    @Test
    void should_parse_multiple_option_1() {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.getLogging());
        assertEquals(8080, options.getPort());
        assertEquals("/usr/logs", options.getDirectory());
    }


    @Test
    void should_parse_multiple_option_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.getGroup());
        assertArrayEquals(new Integer[]{1, 2, -3, 5}, options.getDecimals());
    }


    @Test
    void should_throw_illegal_option_exception_when_annotation_not_present() {
        IllegalOptionException exception = assertThrows(IllegalOptionException.class,
                () -> Args.parse(OptionWithoutAnnotation.class, "-l", "-p", "8080", "-d", "/usr/logs"));
        // the parameter arg is arg1 rather than port
        assertEquals("arg1", exception.getParameter());
    }

    private static class OptionWithoutAnnotation {
        boolean logging;

        int port;

        String directory;

        public OptionWithoutAnnotation(@Option("l") boolean logging, int port, @Option("d") String directory) {
        }
    }
}
