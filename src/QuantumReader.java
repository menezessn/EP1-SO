import java.io.BufferedReader;
import java.io.FileReader;

public class QuantumReader {

    //Realiza a leitura do arquivo com o quantum dado em numero de comandos executados
    static String root = "src/quantum.txt";
    public static int readQuantum(){
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
