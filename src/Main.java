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
        int numberProcesses = programs.size();


        Queue<BCP> ready = new LinkedList<>(); //Fila de processos prontos
        Queue<BCP> blocked = new LinkedList<>(); //FIla de processos bloqueados


        //adicionando todos os processos na fila de prontos
        for(BCP program : programs){
            ready.offer(program);
            LogFileWriter.writeLogFile("Carregando " + program.getName());
            System.out.println("Carregando " + program.getName());
        }

        int switchesSum = 0;
        int meanInstructionsMeanSum = 0;


        //Rodando enquanto tiver processo na fila de prontos ou bloqueados
        while(ready.size()>0 || blocked.size()>0){

            BCP runningProcess = ready.poll();
            runningProcess.setProcessState(Estado.EXECUTANDO);
            LogFileWriter.writeLogFile("Executando " + runningProcess.getName());
            System.out.println("Executando " + runningProcess.getName());

            //Executa as instrucoes ate a quantidade de quantum ou enquanto nao houver interrupcao
            int instructionsExecuted = 0;
            for ( int i = 0; i < quantum; i++){
                runningProcess.setInstructions(runningProcess.getInstructions()+1);
                instructionsExecuted++;
                String program[] = runningProcess.getProgram();
                String instruction = program[runningProcess.getPC()];
                if (instruction.contains("X=")){
                    int assignment = Integer.parseInt(instruction.replace("X=", ""));
                    runningProcess.setX(assignment);
                }
                if (instruction.contains("Y=")){
                    int assignment = Integer.parseInt(instruction.replace("Y=", ""));
                    runningProcess.setX(assignment);
                }
                if(instruction.equals("E/S")){
                    LogFileWriter.writeLogFile("E/S iniciada em " + runningProcess.getName());
                    System.out.println("E/S iniciada em " + runningProcess.getName());
                    runningProcess.setProcessState(Estado.BLOQUEADO);
                    blocked.offer(runningProcess);
                    break;
                }
                if(instruction.equals("COM")){
                    //System.out.println("COM");
                }
                if(instruction.equals("SAIDA")){
                    runningProcess.setProcessState(Estado.BLOQUEADO);
                    //runningProcess.setPC(-1);
                    programs.remove(runningProcess);
                    LogFileWriter.writeLogFile(runningProcess.getName() + (" terminado. ")
                            + "X=" + runningProcess.getX() + " Y=" + runningProcess.getY());
                    System.out.println(runningProcess.getName() + (" terminado. ")
                            + "X=" + runningProcess.getX() + " Y=" + runningProcess.getY());
                    switchesSum += runningProcess.getSwitches();
                    meanInstructionsMeanSum += runningProcess.getInstructionsMean();
                    runningProcess.setInstructionsMean();
                    break;
                }
                runningProcess.setPC(runningProcess.getPC()+1);
            }
            LogFileWriter.writeLogFile("Interrompendo " + runningProcess.getName() + " após "+ instructionsExecuted + " instruções");
            System.out.println("Interrompendo " + runningProcess.getName() + " após "+ instructionsExecuted + " instruções");
            runningProcess.setSwitches(runningProcess.getSwitches() + 1);
            if (runningProcess.getProcessState() == Estado.EXECUTANDO){
                ready.offer(runningProcess);
            }
        }


//        Media de trocas = soma do numero de trocas de processo de cada processo / numero de processos
//        Media de instrucoes = Soma das medias de instrucoes executadas de cada processo a cada quantum / numero de processos
//        medias de instrucoes executadas de cada processo a cada quantum = soma das intrucoes executadas em um quantum (ou seja, total de instrucoes) / numero de quantums ate o fim do processo(trocas)

        double switchesMean = switchesSum / numberProcesses;
        int generalInstructionsMean = meanInstructionsMeanSum / numberProcesses;



    }
}