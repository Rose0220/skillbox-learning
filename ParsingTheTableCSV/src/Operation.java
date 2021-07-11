import java.text.SimpleDateFormat;
import java.util.*;

public class Operation {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    private String typeAccount;
    private String numberAccount;
    private String currency;
    private Date date;
    private String reference;
    private String description;
    private double income;
    private double expense;

    public Operation(String typeAccount, String numberAccount, String currency, Date date, String reference,
                     String description, double income, double expense){
        this.typeAccount = typeAccount;
        this.numberAccount = numberAccount;
        this.currency = currency;
        this.date = date;
        this.reference = reference;
        this.description = description;
        this.income = income;
        this.expense = expense;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

    @Override
    public String toString() {

        return "Номер счета: " + numberAccount + " дата " +  dateFormat.format(date) + " доход " + " " + income + " расход " + expense;
    }
}
