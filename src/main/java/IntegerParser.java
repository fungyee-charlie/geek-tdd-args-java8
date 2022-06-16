import java.util.List;
import java.util.function.Function;

class IntegerParser implements OptionParser {

    private Function<String, Object> valueParser = Integer::valueOf;

    public IntegerParser() {
    }

    public IntegerParser(Function<String, Object> valueParser) {
        this.valueParser = valueParser;
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
