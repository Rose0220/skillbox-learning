import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");

    public static void main(String[] args) {
        ArrayList<Operation> operations = parserCsv("data/movementList.csv");
        ArrayList<Operation> currentAccount = new ArrayList<>();
        Map<String, List<Operation>> numbersAccounts = operations.stream().collect(Collectors.groupingBy(Operation :: getNumberAccount));
        for (Map.Entry<String, List<Operation>> number : numbersAccounts.entrySet()) {
            for (Operation operation : operations) {
                if (number.getKey().equals(operation.getNumberAccount()))
                    currentAccount.add(operation);
            }
            if (!currentAccount.isEmpty()) {
                double generalIncome = currentAccount.stream().mapToDouble(Operation::getIncome).sum();
                double generalExpense = currentAccount.stream().mapToDouble(Operation::getExpense).sum();
                System.out.println("Номер счета: " + currentAccount.get(0).getNumberAccount() +
                        ", общий доход: " + generalIncome +  ", общий расход: " + generalExpense);
                System.out.println("Список расходов: ");
                for (Operation o : currentAccount){
                    if (o.getExpense() > 0)
                        System.out.println(o.getDescription());
                }
            }
        }


    }
    public static String replaceComma(String s){
        Pattern pattern = Pattern.compile("\".+.\"");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String s1 = s.substring(matcher.start(), matcher.end());
            String s2 = s1;
            s2 = s2.replace("\"", "");
            s2 = s2.replace(",", ".");
            s = s.replaceAll(s1,s2);
        }
        return s;
    }
    public static ArrayList<Operation> parserCsv(String path){
        ArrayList<Operation> operations = null;
        try {
            operations = (ArrayList<Operation>) Files.readAllLines(Paths.get(path)).stream()
                        .map(s -> replaceComma(s).split(","))
                        .filter(f -> f.length == 8)
                        .map(Main::toOperation)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operations;
    }
    private static Operation toOperation(String[] fragments) {
        try {
            return new Operation(
                    fragments[0],
                    fragments[1],
                    fragments[2],
                    dateFormat.parse(fragments[3]),
                    fragments[4],
                    fragments[5],
                    Double.parseDouble(fragments[6]),
                    Double.parseDouble(fragments[7])
            );
        } catch (ParseException e) {
            return null;
        }
    }

}
