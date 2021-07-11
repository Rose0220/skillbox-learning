import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

public class CopyOfFolders {
    private File folderSource;
    private File folderDestination;


    public CopyOfFolders(File folderSource, File folderDestination) {
        this.folderSource = folderSource;
        this.folderDestination = folderDestination;
    }

    public void copyFolder() {
        ArrayList<File> folders = new ArrayList<>();
        ArrayList<File> newFolders = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        if (folderSource.isDirectory() && folderDestination.isDirectory()) {
            unpacked(folderSource, folders, files);
            while (folders.size() != 0) {
                researchFiles(folders, newFolders, files);
                folders.clear();
                folders.addAll(newFolders);
                newFolders.clear();
            }
            files.stream().forEach(f -> copyOfContent(f));
        } else if (!folderSource.isDirectory() && folderDestination.isDirectory()) {
            copyOfContent(folderSource);
        } else {
            System.out.println("Путь копирования папки указан неверно");
        }
    }

    public void researchFiles(ArrayList<File> folders, ArrayList<File> newFolders, ArrayList<File> files) {
        folders.stream()
                .flatMap(f ->
                        Arrays.stream(f.listFiles()))
                .forEach(file -> {
                    if (file.isDirectory()){
                        newFolders.add(file);
                        Path relationalPath = researchPath(folderSource, file);
                        File createDirectory = new File(folderDestination.getAbsolutePath() + "\\" + relationalPath);
                        createDirectory.mkdirs();
                        }
                    else {files.add(file);}
                });

    }

    public void unpacked(File file, ArrayList<File> folders, ArrayList<File> files) {
        Arrays.stream(file.listFiles()).forEach(f -> {
            if (f.isDirectory()){
                folders.add(f);
            Path relationalPath = researchPath(folderSource, f);
            File createDirectory = new File(folderDestination.getAbsolutePath() + "\\" + relationalPath);
            createDirectory.mkdirs();}
            else {files.add(f);}
        });
    }
    public Path researchPath(File fileMain, File fileSource) {
        Path mainPath = Paths.get(fileMain.getAbsolutePath());
        Path currentPath = Paths.get(fileSource.getAbsolutePath());
        return mainPath.relativize(currentPath).normalize();
    }

    public void copyOfContent(File fileSource) {
        Path copiedFile = Path.of(fileSource.getAbsolutePath());
        Path createdFile;
        try {
            if (fileSource.equals(folderSource)){
                createdFile = Path.of(folderDestination.getAbsolutePath() + "\\" + fileSource.getName());
            } else {
                Path relationalPath = researchPath(folderSource, fileSource);
                createdFile = Path.of(folderDestination.getAbsolutePath() + "\\" + relationalPath);}
            Files.copy(copiedFile, createdFile, StandardCopyOption.REPLACE_EXISTING);
            if (Files.exists(createdFile)) {
                System.out.println("Файл " + copiedFile.getFileName()
                        + " скопирован из  папки" + copiedFile.getParent() + " в папку " + createdFile.getParent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
