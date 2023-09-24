import java.util.Queue;

public class WaitTimeChecker {
    public static boolean checkWaitTime(Queue<BCP> blockedQueue){
        if (blockedQueue.peek() != null && blockedQueue.peek().getWaitTime() == 0){
            return true;
        }
        return false;
    }

}
