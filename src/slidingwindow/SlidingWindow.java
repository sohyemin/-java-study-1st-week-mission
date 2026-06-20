package slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindow {
    public static void main(String[] args){
        /*특정 범위 내의 합이 가장 큰 조합 찾기*/
        int[] arr1 = {1, 15, 1, 2, 6, 12, 5, 7};
        int windowsize = 3;

        Queue<Integer> queue = new LinkedList<>();

        int currentSum =0;

        for(int i=0; i<windowsize; i++){
            queue.add(arr1[i]);
            currentSum+=arr1[i];
        }

        int maxsum = currentSum;

        for (int i = windowsize; i < arr1.length; i++) {
            currentSum -= queue.remove();

            queue.add(arr1[i]);
            currentSum += arr1[i];

            if(currentSum > maxsum)
                maxsum=currentSum;
        }

        System.out.println(queue);
    }
}
