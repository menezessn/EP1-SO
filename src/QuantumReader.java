import java.io.BufferedReader;
import java.io.FileReader;

public class QuantumReader {

    //Realiza a leitura do arquivo com o quantum dado em numero de comandos executados

    public static int readQuantum(String folder){
        String root = folder + "/quantum.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(root));
            String linha = br.readLine();
            int quantum = Integer.parseInt(linha);
            return quantum;
        }catch(Exception e){
            System.out.println(e);
            System.out.println("ERRO NA LEITURA");
        }
        return -1;
    }

}
