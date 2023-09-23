import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        System.out.println("ESCALONADOR");
        int quantum =  QuantumReader.readQuantum("programas/");
        System.out.println(quantum); //numero de quantuns

        List<BCP> programs = BCPTableCreator.createBCPTable("programas/"); // Criando lista de processos

        Queue<BCP> ready = new LinkedList<>(); //Fila de processos prontos
        Queue<BCP> blocked = new LinkedList<>(); //FIla de processos bloqueados


        //adicionando todos os processos na fila de prontos
        for(BCP program : programs){
            ready.offer(program);
            LogFileWriter.writeLogFile("Carregando " + program.getName());
            System.out.println("Carregando " + program.getName());
        }



//
//
//
//
//        for (BCP program : programs ){
//            System.out.println(program.getName());
//            System.out.println(program.getPC());
//            System.out.println(program.getProcessState());
//            System.out.println("///////////");
//
//        }
//
//        System.out.println("-----------teste alterar----------------");
//        ready.peek().setProcessState(Estado.BLOQUEADO);
//
//
//        System.out.println("-----------teste ----------------");
//
//        for (BCP program : programs ){
//            System.out.println(program.getName());
//            System.out.println(program.getPC());
//            System.out.println(program.getProcessState());
//            System.out.println("///////////");
//
//
//        }





    }
}