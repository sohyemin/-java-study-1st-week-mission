package slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

public class NotSlidingWindow {
    Queue<Message> queue = new LinkedList<>();
    private int maxtoken;

    public NotSlidingWindow(int maxToken) {
        this.maxtoken = maxToken;
    }

    public void addMessage(Message message){
        queue.add(message);

        int currentToken = queue.stream()
                .mapToInt(Message::getToken)
                .sum();

        while (currentToken>maxtoken) {
            queue.remove();
            currentToken = queue.stream()
                    .mapToInt(Message::getToken)
                    .sum();
        }
    }
}