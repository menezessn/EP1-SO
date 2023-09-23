import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BCPTableCreator {
    public static List<BCP> createBCPTable(){
        List<BCP> BCPs = new ArrayList<>();
        String path = "programas/";
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] files = file.listFiles();
            if (files!=null){
                for (File file1 : files){
                    if(file1.isFile() && file1.getName().endsWith(".txt")){
                        try{
                            int numProg = Integer.parseInt(file1.getName().replace(".txt",""));
                            String[] program = programReader.readProgram(numProg);
                            BCP bcp = new BCP(program[0], Estado.PRONTO, program);
                            BCPs.add(bcp);
                        }catch (NumberFormatException e){
                        }
                    }
                }
            }
        }
        return BCPs;
    }
}
