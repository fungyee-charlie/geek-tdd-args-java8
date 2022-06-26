import java.util.List;

class BooleanParser implements OptionParser<Boolean> {
    private BooleanParser() {
    }

    public static OptionParser<Boolean> createBooleanParser() {
        return (arguments, option) -> SingleValueOptionParser.values(arguments, option, 0)
                .map(it -> true)
                .orElse(false);
    }

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        return SingleValueOptionParser.values(arguments, option, 0)
                .map(it -> true)
                .orElse(false);
    }
}
