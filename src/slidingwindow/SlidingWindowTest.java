package slidingwindow;

public class SlidingWindowTest {
    public static void main(String[] args) {

        SlidingWindowManager manager = new SlidingWindowManager(100);

        System.out.println("=== 메시지 추가 시작 ===");

        manager.addMessage(new Message("안녕", 10));
        manager.printMessage();
        // 안녕 (10)

        manager.addMessage(new Message("오늘 날씨 어때?", 30));
        manager.printMessage();
        // 안녕 오늘 날씨 어때? (40)

        manager.addMessage(new Message("점심은 먹었어?", 20));
        manager.printMessage();
        // 안녕 오늘 날씨 어때? 점심은 먹었어? (60)

        manager.addMessage(new Message("나는 김치찌개를 먹었어.", 30));
        manager.printMessage();
        // 안녕 오늘 날씨 어때? 점심은 먹었어? 나는 김치찌개를 먹었어. (90)

        System.out.println();
        System.out.println("=== 토큰 초과 ===");

        manager.addMessage(new Message("오늘 저녁 메뉴 추천해줘.", 40));
        manager.printMessage();

        /*
        추가 후 토큰 수

        90 + 40 = 130

        가장 오래된 메시지 제거

        안녕(10) 제거 → 120

        아직 초과

        오늘 날씨 어때?(30) 제거 → 90

        최종

        점심은 먹었어?
        나는 김치찌개를 먹었어.
        오늘 저녁 메뉴 추천해줘.
        */

        System.out.println();

        System.out.println("=== 매우 큰 메시지 추가 ===");

        manager.addMessage(new Message("엄청 긴 문장", 80));
        manager.printMessage();

        /*
        기존 90 + 80 = 170

        점심은 먹었어?(20) 제거 → 150
        나는 김치찌개를 먹었어.(30) 제거 → 120
        오늘 저녁 메뉴 추천해줘.(40) 제거 → 80

        결과

        엄청 긴 문장
        */
    }
}
