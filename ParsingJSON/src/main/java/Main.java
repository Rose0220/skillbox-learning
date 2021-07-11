import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Metro metro = new Metro();

    public static void main(String[] args) throws IOException {
        String url = "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";
        String file = "data/test.json";
        ArrayList<Row> list = ParsingHTML.parseHtml(url);
        researchLines(list);
        researchStations(list);
        researchConnections(list);
        WriterJSON.createJsonFile(file, metro);
        ObjectMapper mapper = new ObjectMapper();
        Metro metroInJsonFile = mapper.readValue(new FileReader(file), Metro.class);
        System.out.println("Количество станций на линии*************************************************");
        for (Map.Entry<String, List<String>> m : metroInJsonFile.stations.entrySet()) {
            Line line = metroInJsonFile.getLine(m.getKey());
            int count = m.getValue().size();
            System.out.println(line.getName() + ": " + count);
        }
    }

    public static void researchLines(ArrayList<Row> list) {
        Map<String, String> map = new HashMap<>();
        for (Row row : list) {
            map.putIfAbsent(row.getNumberLine(), row.getNameLine());
        }
        for (Map.Entry<String, String> m : map.entrySet()) {
            metro.addLine(new Line(m.getKey(), m.getValue()));
        }
    }

    public static void researchStations(ArrayList<Row> list) {
        for (Row row : list) {
            String numberLine = row.getNumberLine();
            String nameStation = row.getNameStation();
            Station station = new Station(nameStation, numberLine);
            if (!metro.containsStation(station)) {
                metro.addStation(station);
            }
        }
    }

    public static void researchConnections(ArrayList<Row> list) {
        List<Row> list1 = new ArrayList<>();
        for (Row row : list) {
            if (row.getNumberConnection() != null)
                list1.add(row);
        }
        for (Row row : list1) {
            String numberLine = row.getNumberLine();
            String nameStation = row.getNameStation();
            String numberConnection = row.getNumberConnection();
            for (Row row1 : list1) {
                Pattern pattern = Pattern.compile(nameStation);
                Matcher matcher = pattern.matcher(row1.getNameConnection());
                String numberLine1 = row1.getNumberLine();
                String nameStation1 = row1.getNameStation();
                String numberConnection1 = row1.getNumberConnection();
                if (matcher.find() && numberLine.equals(numberConnection1) && numberConnection.equals(numberLine1)) {
                    Station connection1 = new Station(nameStation1, numberConnection);
                    Station connection2 = new Station(nameStation, numberConnection1);
                    List<Station> stations = Arrays.asList(connection1, connection2);
                    List<Station> check = Arrays.asList(connection2, connection1);
                    if (!metro.connections.contains(check))
                        metro.addConnection(stations);
                }
            }
        }
    }
}
