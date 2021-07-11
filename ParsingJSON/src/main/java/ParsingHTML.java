import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ParsingHTML {
    public static ArrayList<Row> parseHtml(String url) throws IOException {
        ArrayList<Row> list = new ArrayList<>();
        Document doc = Jsoup.connect(url)
                .maxBodySize(0).get();
        Elements element = doc.select("table.standard");
        Elements trs = new Elements();
        for (int i = 0; i < element.size(); i++) {
            Elements tr = element.get(i).select("tr");
            tr.remove(0);
            if (i != 0) {
                tr.remove(0);
            }
            trs.addAll(tr);
        }
        parsingData(trs, list);
        String path = "C:\\Users\\Роза\\Documents\\3 семестр\\ИМТ\\Ответы\\2.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Row s : list) {
            writer.write(s.toString() + "\n");
        }
        writer.close();
        return list;
    }

    private static void parsingData(Elements trs, ArrayList<Row> list) {
        trs.forEach(el -> {
            Elements elements = el.select("td");

            String numbers = elements.get(0).select("span").text().trim();
            Elements names = elements.get(0).select("a");
            numbers = numbers.substring(0, numbers.length() - 3).trim();
            String[] numberLine = numbers.trim().split("\\s+\\s");
            String[] nameLine = new String[numberLine.length];
            for (int l = 0; l < numberLine.length; l++) {
                nameLine[l] = names.get(l).attr("title");
                String currentNumber = numberLine[l];
                if (currentNumber.indexOf("0") == 0)
                    numberLine[l] = currentNumber.substring(1);
            }
            String nameStation = elements.get(1).select("a").attr("title").replaceAll("\\(.*?\\)", "").trim();
            String connection = elements.get(3).attr("data-sort-value");
            if (!connection.equals("Infinity")) {
                Elements spans = elements.get(3).select("span");
                for (int i = 0; i < spans.size(); i++) {
                    connection = spans.get(i).text();
                    if (connection.indexOf("0") == 0)
                        connection = connection.substring(1);
                    String nameConnection = spans.get(i + 1).attr("title");
                    for (int k = 0; k < nameLine.length; k++) {
                        list.add(new Row(numberLine[k], nameLine[k], nameStation, connection, nameConnection));
                    }
                    ++i;
                }
            } else {
                for (int k = 0; k < nameLine.length; k++) {
                    list.add(new Row(numberLine[k], nameLine[k], nameStation, null, null));
                }
            }
        });
    }
}
