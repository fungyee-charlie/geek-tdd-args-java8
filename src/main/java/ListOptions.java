public class ListOptions {
    String[] group;

    Integer[] decimals;

    public String[] getGroup() {
        return group;
    }

    public Integer[] getDecimals() {
        return decimals;
    }

    public ListOptions(@Option("g")String[] group, @Option("d") Integer[] decimals) {
        this.group = group;
        this.decimals = decimals;
    }
}
