import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

class SingleValueOptionParser<T> implements OptionParser<T> {

    private Function<String, T> valueParser;
    private T defaultValue;

    public SingleValueOptionParser(T defaultValue, Function<String, T> valueParser) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }


    @Override
    public T parse(List<String> arguments, Option option) {
        Optional<List<String>> argumentList;
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) {
            argumentList = Optional.empty();
        } else {
            List<String> values = values(arguments, index);

            if (values.size() < 1) {
                throw new InsufficientArgumentException(option.value());
            }

            if (values.size() > 1) {
                throw new TooManyArgumentException(option.value());
            }
            argumentList = Optional.of(values);
        }

        return argumentList
                .map(it -> parseValue(option, it.get(0)))
                .orElse(defaultValue);

    }

    private T parseValue(Option option, String value) {
        try {
            return valueParser.apply(value);
        } catch (Exception e) {
            throw new IllegalValueException(option.value());
        }
    }


    static List<String> values(List<String> arguments, int index) {
        int followingFlag = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst()
                .orElse(arguments.size());
        return arguments.subList(index + 1, followingFlag);
    }

}
