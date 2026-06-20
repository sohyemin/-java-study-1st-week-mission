package slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowManager {

    private Queue<Message> queue = new LinkedList<>();
    private int currentToken;
    private int maxToken;

    public SlidingWindowManager(int maxToken) {
        this.maxToken = maxToken;
        currentToken=0;
    }

    public void addMessage(Message message){

        queue.add(message);
        currentToken+=message.getToken();


        while (currentToken > maxToken && !queue.isEmpty())
        {
            currentToken-=queue.remove().getToken();
        }

    }

    public void printMessage(){
        System.out.print("저장된 메시지: ");
        queue.forEach(message -> {
            System.out.print(message.getMessage() + " ");
        });
        System.out.println();
        System.out.println("현재 저장된 토큰수 : " + currentToken);
        System.out.println("-----------------------------");
    }
}
