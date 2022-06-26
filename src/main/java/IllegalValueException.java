public class IllegalValueException extends RuntimeException {
    private final String option;

    public IllegalValueException(String option) {
        this.option = option;
    }

    public String getOption() {
        return this.option;
    }
}