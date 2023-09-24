import java.util.LinkedList;
import java.util.List;

public class WaitTimeDecrementer {
    //Decrementa o tempo de espera de todos os elementos da pilha
    public static void decrementWaitTime(LinkedList<BCP> blockedQueue){
        for(int i = 0; i < blockedQueue.size(); i++){
            BCP process = blockedQueue.get(i);
            process.setWaitTime(process.getWaitTime()-1);
        }
    }
}
