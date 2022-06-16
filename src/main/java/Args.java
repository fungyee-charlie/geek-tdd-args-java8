import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Args {
    public static <T> T parse(Class<T> optionsClass, String... args) {
        try {
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            List<String> arguments = Arrays.asList(args);

            Object[] values = Arrays.stream(constructor.getParameters())
                    .map(it -> parseOption(arguments, it)).toArray();

            return (T) constructor.newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseOption(List<String> arguments, Parameter parameter) {
        Option option = parameter.getAnnotation(Option.class);
        Class<?> type = parameter.getType();
        return getOptionParser(type).parse(arguments, option);
    }

    private static OptionParser getOptionParser(Class<?> type) {
        return PARSERS.get(type);
    }

    private static final Map<Class<?>, OptionParser> PARSERS = ImmutableMap.of(
            boolean.class, new BooleanParser(),
            int.class, new IntegerParser(),
            String.class, new StringParser());

    interface OptionParser {
        Object parse(List<String> arguments, Option option);
    }

    private static class BooleanParser implements OptionParser {

        @Override
        public Object parse(List<String> arguments, Option option) {
            return arguments.contains("-" + option.value());
        }
    }

    private static class IntegerParser implements OptionParser {
        @Override
        public Object parse(List<String> arguments, Option option) {
            return Integer.valueOf(arguments.get(arguments.indexOf("-" + option.value()) + 1));
        }
    }

    private static class StringParser implements OptionParser {
        @Override
        public Object parse(List<String> arguments, Option option) {
            return arguments.get(arguments.indexOf("-" + option.value()) + 1);
        }
    }


}
