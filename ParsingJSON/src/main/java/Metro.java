import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.*;

@JsonAutoDetect
@JsonPropertyOrder({"stations", "connections", "lines"})
public class Metro {
    @JsonUnwrapped
    List<Line> lines;
    @JsonUnwrapped
    Map<String, List<String>> stations;
    @JsonUnwrapped
    List<List<Station>> connections;

    public Metro() {
        lines = new ArrayList<>();
        stations = new TreeMap<>();
        connections = new ArrayList<>();
    }


    public void addStation(Station station) {
        String line = station.getLine();
        String name = station.getName();
        if (stations.containsKey(line)) {
            stations.get(line).add(name);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(name);
            stations.put(line, list);
        }
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void addConnection(List<Station> stations) {
        connections.add(stations);
    }

    public Line getLine(String number) {
        Line line = null;
        for (Line l : lines) {
            if (l.getNumber().equals(number))
                line = l;
        }
        return line;
    }

    public boolean containsStation(Station station) {
        boolean check = false;
        String line = station.getLine();
        String name = station.getName();
        for (Map.Entry<String, List<String>> m : stations.entrySet()) {
            if (m.getKey().equals(line)) {
                for (String s : m.getValue()) {
                    if (name.equals(s)) {
                        check = true;
                        break;
                    }
                }
            }
        }
        return check;
    }
}

