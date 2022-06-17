import java.util.List;
import java.util.function.Function;

class IntegerParser implements OptionParser {

    private Function<String, Object> valueParser;

    public IntegerParser(Function<String, Object> valueParser) {
        this.valueParser = valueParser;
    }

    public static OptionParser createIntegerParser() {
        return new IntegerParser(Integer::valueOf);
    }

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value()) + 1;
        String value = arguments.get(index);
        return parseValue(value);
    }

    protected Object parseValue(String value) {
        return valueParser.apply(value);
    }
}
