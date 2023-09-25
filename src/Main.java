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


        LinkedList<BCP> ready = new LinkedList<>(); //Fila de processos prontos
        LinkedList<BCP> blocked = new LinkedList<>(); //FIla de processos bloqueados



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

            //caso a lista de prontos esteja vazia, os bloqueados serão decrementados até que algum vá para a fila de prontos
            while(ready.size() == 0){
                WaitTimeDecrementer.decrementWaitTime(blocked);
                while( WaitTimeChecker.checkWaitTime(blocked)){
                    ready.offer(blocked.remove());
                }
            }

            BCP runningProcess = ready.poll();
            assert runningProcess != null;
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
                    runningProcess.setWaitTime(3);
                    runningProcess.setPC(runningProcess.getPC()+1);
                    break;
                }
                if(instruction.equals("COM")){
                    //System.out.println("COM");
                }
                if(instruction.equals("SAIDA")){
                    runningProcess.setProcessState(Estado.BLOQUEADO);
                    runningProcess.setPC(-1);
                    programs.remove(runningProcess);
                    LogFileWriter.writeLogFile(runningProcess.getName() + (" terminado. ")
                            + "X=" + runningProcess.getX() + " Y=" + runningProcess.getY());
                    System.out.println(runningProcess.getName() + (" terminado. ")
                            + "X=" + runningProcess.getX() + " Y=" + runningProcess.getY());
                    switchesSum += runningProcess.getSwitches();
                    runningProcess.setInstructionsMean();
                    meanInstructionsMeanSum += runningProcess.getInstructionsMean();
                    break;
                }
                runningProcess.setPC(runningProcess.getPC()+1);
            }
            LogFileWriter.writeLogFile("Interrompendo " + runningProcess.getName() +
                    " após "+ instructionsExecuted + " instruções");
            System.out.println("Interrompendo " + runningProcess.getName() +
                    " após "+ instructionsExecuted + " instruções");
            runningProcess.setSwitches(runningProcess.getSwitches() + 1);
            if (runningProcess.getProcessState() == Estado.EXECUTANDO){
                ready.offer(runningProcess);
            }
            //Decrementa o tempo de espera de todos os elementos da fila de bloqueados
            WaitTimeDecrementer.decrementWaitTime(blocked);
            //verifica se acabou o tempo de espera de alguem na fila de bloqueados
            while( WaitTimeChecker.checkWaitTime(blocked)){
                ready.offer(blocked.remove());
            }
        }


//        Media de trocas = soma do numero de trocas de processo de cada processo / numero de processos
//        Media de instrucoes = Soma das medias de instrucoes executadas de cada processo a cada quantum / numero de processos
//        medias de instrucoes executadas de cada processo a cada quantum = soma das intrucoes executadas em um quantum (ou seja, total de instrucoes) / numero de quantums ate o fim do processo(trocas)

        double switchesMean = (double) switchesSum / numberProcesses;
        double generalInstructionsMean = (double) meanInstructionsMeanSum / numberProcesses;

        LogFileWriter.writeLogFile("MEDIA DE TROCAS " + switchesMean);
        LogFileWriter.writeLogFile("MEDIA DE INSTRUÇÕES " + generalInstructionsMean);
        System.out.println("MEDIA DE TROCAS " + switchesMean);
        System.out.println("MEDIA DE INSTRUÇÕES " + generalInstructionsMean);

    }
}