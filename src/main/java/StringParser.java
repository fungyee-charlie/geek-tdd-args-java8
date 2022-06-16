class StringParser extends IntegerParser {

    public static OptionParser createStringParser() {
        return new IntegerParser(String::valueOf);
    }
}
