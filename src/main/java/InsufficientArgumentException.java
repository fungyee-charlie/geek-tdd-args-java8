public class InsufficientArgumentException extends RuntimeException{
    private final String option;

    public InsufficientArgumentException(String option) {
        this.option = option;
    }

    public String getOption() {
        return this.option;
    }
}
