import java.io.File;

public class DeleteLogFile {
    public static void LogFileDelete(int quantum){
        String fileName = "log" + String.format("%02d", quantum) + ".txt";

        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.err.println("Failed to delete the file.");
            }
        } else {
            System.err.println("File not found.");
        }

    }
}
