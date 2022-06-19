import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class SingleValueOptionParserTest {
    static Option option(String value) {
        return new Option() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String value() {
                return value;
            }
        };
    }

    @Test
    void should_not_accept_extra_argument_when_parse_single_value_option() {
        TooManyArgumentException e = assertThrows(TooManyArgumentException.class,
                () -> new SingleValueOptionParser<>(Integer::valueOf).parse(asList("-p", "8080", "8081"), option("p")));
        assertEquals("p", e.getOption());
    }


    @ParameterizedTest
    @ValueSource(strings = {"-p -l", "-p"})
    void should_not_accept_insufficient_argument_when_parse_single_value_option(String arguments) {
        InsufficientArgumentException e = assertThrows(InsufficientArgumentException.class,
                () -> new SingleValueOptionParser<>(Integer::valueOf)
                        .parse(asList(arguments.split(" ")), option("p")));
        assertEquals("p", e.getOption());
    }
}