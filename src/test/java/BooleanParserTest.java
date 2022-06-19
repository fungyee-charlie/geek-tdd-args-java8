import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class BooleanParserTest {

    static  Option option(String value) {
        return new Option(){
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
    void should_not_accept_extra_argument_for_boolean_option() {
        TooManyArgumentException e = assertThrows(TooManyArgumentException.class,
                () -> new BooleanParser().parse(asList("-l", "t"), option("l")));

        assertEquals("l", e.getOption());
    }

    @Test
    void should_not_accept_extra_argument_for_boolean_option_1() {
        TooManyArgumentException e = assertThrows(TooManyArgumentException.class,
                () -> new BooleanParser().parse(asList("-l", "t", "f"), option("l")));

        assertEquals("l", e.getOption());
    }

}