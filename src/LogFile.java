import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LogFile {
    
    String file;

    public LogFile(){
        String newLogFile =  "log/log" + String.format("%02d", QuantumReader.readQuantum("programas/")) + ".txt";
        
        File file = new File(newLogFile);

        if(file.exists()) {
            if(file.delete()) {
                System.out.println("Arquivo de log anterior excluido. Criando novo arquivo...");
            } else {
                System.err.println("Erro ao tentar excluir arquivo de log.");
            }
        } else {
            System.err.println("Criando arquivo de log...");
        }
        this.file = newLogFile;
        System.err.println("Arquivo de log criado.");
    }   

    public void write(String text){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true))){
            writer.write(text+"\n");
            writer.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
