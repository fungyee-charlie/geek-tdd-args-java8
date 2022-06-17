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
            int.class, IntegerParser.createIntegerParser(),
            String.class, new IntegerParser(String::valueOf));


}
