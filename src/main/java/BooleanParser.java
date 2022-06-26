import java.util.List;

class BooleanParser implements OptionParser<Boolean> {
    private BooleanParser() {
    }

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        return SingleValueOptionParser.values(arguments, option, 0)
                .map(it -> true)
                .orElse(false);
    }
}
