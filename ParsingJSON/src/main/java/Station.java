import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Station implements Comparable<Station> {
    private String line;
    private String name;

    public Station(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public Station() {
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public int compareTo(Station station) {
        int lineComparison = line.compareTo(station.getLine());
        if (lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Station) obj) == 0;
    }
}
