import java.util.List;
import java.util.function.Function;

class SingleValueOptionParser<T> implements OptionParser<T> {

    private Function<String, T> valueParser;

    public SingleValueOptionParser(Function<String, T> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());

        if (index + 1 == arguments.size()) {
            throw new InsufficientArgumentException(option.value());
        }

        if (arguments.get(index + 1).startsWith("-")) {
            throw new InsufficientArgumentException(option.value());
        }

        if (index + 2 < arguments.size() &&
                !arguments.get(index + 2).startsWith("-")) {
            throw new TooManyArgumentException(option.value());
        }
        String value = arguments.get(index + 1);
        return valueParser.apply(value);
    }

}
