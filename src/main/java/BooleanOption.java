public class BooleanOption {
    private boolean logging;

    public BooleanOption(@Option("l") boolean logging) {
        this.logging = logging;
    }

    public boolean getLogging() {
        return logging;
    }

}
