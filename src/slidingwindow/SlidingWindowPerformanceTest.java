package slidingwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlidingWindowPerformanceTest {

    public static void main(String[] args) {
        int messageCount = 1_000_000;
        int maxToken = 10_000;

        List<Message> messages = createMessages(messageCount);

        long start1 = System.nanoTime();

        NotSlidingWindow notSlidingWindow = new NotSlidingWindow(maxToken);

        for (Message message : messages) {
            notSlidingWindow.addMessage(message);
        }

        long end1 = System.nanoTime();

        long start2 = System.nanoTime();

        SlidingWindowManager slidingWindowManager = new SlidingWindowManager(maxToken);

        for (Message message : messages) {
            slidingWindowManager.addMessage(message);
        }

        long end2 = System.nanoTime();

        System.out.println("Not Sliding Window: " + (end1 - start1) / 1_000_000 + " ms");
        System.out.println("Sliding Window: " + (end2 - start2) / 1_000_000 + " ms");
    }

    private static List<Message> createMessages(int count) {
        List<Message> messages = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 0; i < count; i++) {
            int token = random.nextInt(50) + 1;
            messages.add(new Message("message-" + i, token));
        }

        return messages;
    }
}
