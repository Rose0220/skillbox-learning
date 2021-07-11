public class Row {
    private String numberLine;
    private String nameLine;
    private String nameStation;
    private String numberConnection;
    private String nameConnection;

    public Row(String numberLine, String nameLine, String nameStation, String numberConnection, String nameConnection) {
        this.numberLine = numberLine;
        this.nameLine = nameLine;
        this.nameStation = nameStation;
        this.numberConnection = numberConnection;
        this.nameConnection = nameConnection;
    }

    @Override
    public String toString() {
        return numberLine + "," + nameLine + "," + nameStation + "," + numberConnection + "," + nameConnection;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public String getNameLine() {
        return nameLine;
    }

    public String getNameStation() {
        return nameStation;
    }

    public String getNumberConnection() {
        return numberConnection;
    }

    public String getNameConnection() {
        return nameConnection;
    }
}
