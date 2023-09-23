import java.io.BufferedReader;
import java.io.FileReader;

public class programReader {
    //realiza a leitura de um programa e retorna um array com no maximo 22 posicoes
    public static String[] readProgram(int numProg, String folder){
        String path = folder;
        String nameProg = String.format("%02d", numProg);
        String root = path + nameProg + ".txt";
        String program[] = new String[22];


        try{

            BufferedReader br = new BufferedReader(new FileReader(root));
            String linha;
            int i = 0;
            while( (linha = br.readLine()) != null || i > 22){
                program[i] = linha;
                i++;
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return program;


    }
}
