import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

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
        Object value = null;
        Option option = parameter.getAnnotation(Option.class);

        if (parameter.getType() == int.class) {
            value = parseInteger(arguments, option);
        }

        if (parameter.getType() == boolean.class) {
            value = parseBoolean(arguments, option);
        }

        if (parameter.getType() == String.class) {
            value = parseString(arguments, option);
        }
        return value;
    }

    private static Object parseString(List<String> arguments, Option option) {
        return arguments.get(arguments.indexOf("-" + option.value()) + 1);
    }

    private static Object parseBoolean(List<String> arguments, Option option) {
        return arguments.contains("-" + option.value());
    }

    private static Object parseInteger(List<String> arguments, Option option) {
        return Integer.valueOf(arguments.get(arguments.indexOf("-" + option.value()) + 1));
    }

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
