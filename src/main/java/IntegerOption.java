public class IntegerOption {
    private int port;

    public int getPort() {
        return port;
    }

    public IntegerOption(@Option("p") int port) {
        this.port = port;
    }
}
