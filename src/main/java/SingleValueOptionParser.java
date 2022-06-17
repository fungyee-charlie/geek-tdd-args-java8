import java.util.List;
import java.util.function.Function;

class SingleValueOptionParser implements OptionParser {

    private Function<String, Object> valueParser;

    public SingleValueOptionParser(Function<String, Object> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value()) + 1;
        String value = arguments.get(index);
        return valueParser.apply(value);
    }

}
