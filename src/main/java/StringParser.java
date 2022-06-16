class StringParser extends IntegerParser {

    protected Object parseValue(String value) {
        return String.valueOf(value);
    }
}
