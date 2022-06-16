import java.util.List;

class StringParser implements OptionParser {
    @Override
    public Object parse(List<String> arguments, Option option) {
        return arguments.get(arguments.indexOf("-" + option.value()) + 1);
    }
}
