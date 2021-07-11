import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file1 = new File (args[0]);
        File file2 = new File (args[1]);
        if (file1.exists() && file2.exists()){
        CopyOfFolders copyOfFolders = new CopyOfFolders(file1, file2);
        copyOfFolders.copyFolder();
        } else{
            System.out.println("Неверно указаны пути. " +
                    "Пример: \"C:\\\\Users\\\\Роза\\\\Documents\\\\3 семестр\\\\ИМТ\\\\Ответы\" " +
                    "\"C:\\\\Users\\\\Роза\\\\Documents\\\\3 семестр\\\\ИМТ\\\\картинки\"");}

    }
}
