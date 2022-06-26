import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class OptionParsersTest {
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

    @Nested
    class UnaryOptionParser {
        @Test
        void should_return_value_when_parse_single_value_option_given_option_present() {
            Object parsed = new Object();
            Function<String, Object> parse = (it) -> parsed;
            Object whatever = new Object();
            assertEquals(parsed, OptionParsers.unary(whatever, parse)
                    .parse(asList("-p", "8080"), option("p")));
        }

        @Test
        void should_not_accept_extra_argument_when_parse_single_value_option() {
            TooManyArgumentException e = assertThrows(TooManyArgumentException.class,
                    () -> OptionParsers.unary(0, Integer::valueOf).parse(asList("-p", "8080", "8081"), option("p")));
            assertEquals("p", e.getOption());
        }


        @ParameterizedTest
        @ValueSource(strings = {"-p -l", "-p"})
        void should_not_accept_insufficient_argument_when_parse_single_value_option(String arguments) {
            InsufficientArgumentException e = assertThrows(InsufficientArgumentException.class,
                    () -> OptionParsers.unary(0, Integer::valueOf)
                            .parse(asList(arguments.split(" ")), option("p")));
            assertEquals("p", e.getOption());
        }

        @Test
        void should_set_default_value_when_parse_single_value_option() {
            Function<String, Object> parse = (it) -> null;
            Object defaultValue = new Object();
            assertEquals(defaultValue, OptionParsers.unary(defaultValue, parse)
                    .parse(asList(), option("p")));
        }

        @Test
        void should_throw_illegal_value_exception() {
            Function<String, Object> parse = (it) -> {
                throw new RuntimeException();
            };
            Object defaultValue = new Object();
            IllegalValueException exception = assertThrows(IllegalValueException.class,
                    () -> OptionParsers.unary(defaultValue, parse).parse(asList("-p", "list"), option("p")));
            assertEquals("p", exception.getOption());
        }
    }


   }