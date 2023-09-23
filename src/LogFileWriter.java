import java.io.BufferedWriter;
import java.io.FileWriter;

public class LogFileWriter {
    public static void writeLogFile(String text){
        String quantumValue = String.format("%02d", QuantumReader.readQuantum());
        String file = "log" + quantumValue + ".txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(text);
            writer.newLine();
        }catch(Exception e){

        }
    }
}
