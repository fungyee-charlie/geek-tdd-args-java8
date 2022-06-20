import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
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
    void should_return_true_given_boolean_option_present() {
        assertTrue(new BooleanParser().parse(singletonList("-l"), option("l")));
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

    @Test
    void should_set_default_value_to_false_for_boolean_option() {
        assertFalse(new BooleanParser().parse(asList(), option("l")));
    }

}