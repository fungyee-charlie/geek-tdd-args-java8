import java.util.List;

class IntegerParser implements OptionParser {
    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value()) + 1;
        String value = arguments.get(index);
        return parseValue(value);
    }

    protected Object parseValue(String value) {
        return Integer.valueOf(value);
    }
}
