package WrapperPrimitive;

public class WrapperPrimitive {

    private static final int SIZE = 10_000_000;

    public static void main(String[] args) throws Exception {

        System.out.println("=== Primitive double[] 테스트 ===");

        long primitiveStart = System.nanoTime();

        double[] primitiveArray = new double[SIZE];

        for (int i = 0; i < SIZE; i++) {
            primitiveArray[i] = i * 0.1;
        }

        long primitiveEnd = System.nanoTime();

        System.out.println("double[] 생성 시간: " + (primitiveEnd - primitiveStart) / 1_000_000 + " ms");

        System.out.println("VisualVM에서 double[] 메모리 확인 후 Enter를 누르세요.");
        System.in.read();

        System.out.println("=== Wrapper Double[] 테스트 ===");

        long wrapperStart = System.nanoTime();

        Double[] wrapperArray = new Double[SIZE];

        for (int i = 0; i < SIZE; i++) {
            wrapperArray[i] = i * 0.1;
        }

        long wrapperEnd = System.nanoTime();

        System.out.println("Double[] 생성 시간: " + (wrapperEnd - wrapperStart) / 1_000_000 + " ms");

        System.out.println("VisualVM에서 Double[] 메모리 확인 후 Enter를 누르세요.");
        System.in.read();

        // GC로 사라지지 않게 참조 유지
        System.out.println("primitiveArray[0] = " + primitiveArray[0]);
        System.out.println("wrapperArray[0] = " + wrapperArray[0]);
    }
}
