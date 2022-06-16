class StringParser extends IntegerParser {

    public StringParser() {
        super(String::valueOf);
    }
}
