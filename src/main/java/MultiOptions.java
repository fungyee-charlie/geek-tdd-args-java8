public class MultiOptions {
    boolean logging;

    int port;

    String directory;

    public boolean getLogging() {
        return logging;
    }

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }

    public MultiOptions(@Option("l") boolean logging, @Option("p")int port, @Option("d")String directory) {
        this.logging = logging;
        this.port = port;
        this.directory = directory;
    }
}
