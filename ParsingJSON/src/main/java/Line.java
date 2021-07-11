import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Line implements Comparable<Line> {
    private String number;
    private String name;


    public Line(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public Line() {
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber() {
        this.number = number;
    }

    public void setName() {
        this.name = name;
    }

    @Override
    public int compareTo(Line line) {
        String line1 = number.replace("А", "").trim();
        String line2 = line.getNumber().replace("А", "").trim();
        int num1 = Integer.parseInt(line1);
        int num2 = Integer.parseInt(line2);
        return Integer.compare(num1, num2);
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Line) obj) == 0;
    }
}
