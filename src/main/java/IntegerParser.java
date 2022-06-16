import java.util.List;

class IntegerParser implements OptionParser {
    @Override
    public Object parse(List<String> arguments, Option option) {
        return Integer.valueOf(arguments.get(arguments.indexOf("-" + option.value()) + 1));
    }
}
