import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        System.out.println("ESCALONADOR");

        int quantum =  QuantumReader.readQuantum("programas/");

        //Excluindo o arquivo de log, caso ele exista, para que nao haja conflito
        DeleteLogFile.LogFileDelete(quantum);


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

        //while(ready.size()>0){
            BCP runningProcess = ready.poll();
            runningProcess.setProcessState(Estado.EXECUTANDO);

            for (int i = 0; i < quantum; i++){
                String program[] = runningProcess.getProgram();
                String instruction = program[runningProcess.getPC()];
                if (instruction.contains("X=")){
                    int assignment = Integer.parseInt(instruction.replace("X=", ""));
                    System.out.println(assignment);
                    runningProcess.setX(assignment);
                }
                if (instruction.contains("Y=")){
                    int assignment = Integer.parseInt(instruction.replace("Y=", ""));
                    runningProcess.setX(assignment);
                }
                if(instruction.equals("E/S")){
                    runningProcess.setProcessState(Estado.BLOQUEADO);
                    blocked.offer(runningProcess);
                    break;
                }
                if(instruction.equals("COM")){
                    System.out.println("COM");
                }
                if(instruction.equals("SAIDA")){
                    runningProcess.setProcessState(Estado.BLOQUEADO);
                    runningProcess.setPC(-1);
                    programs.remove(runningProcess);
                    break;
                }
                runningProcess.setPC(runningProcess.getPC()+1);

            }

        //}



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