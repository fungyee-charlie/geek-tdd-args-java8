public class StringOption {
    private String directory;

    public String getDirectory() {
        return directory;
    }

    public StringOption(@Option("d") String directory) {
        this.directory = directory;
    }
}
