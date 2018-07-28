package beans;

import java.util.ArrayList;


public class FileReader {

    private ArrayList<String> fileName;

    public FileReader() {
    }

    private String fileSize;
    private String fileExtension;


    public ArrayList<String> getFileName() {
        return fileName;
    }

    public void setFileName(ArrayList<String> fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
