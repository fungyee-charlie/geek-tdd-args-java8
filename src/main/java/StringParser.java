class StringParser extends IntegerParser {

    private StringParser() {
        super(String::valueOf);
    }

    public static OptionParser createStringParser() {
        return new IntegerParser(String::valueOf);
    }
}
