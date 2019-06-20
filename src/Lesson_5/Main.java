package Lesson_5;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;

    public static void main(String[] args) {
        flow1();
        flow2(5);
    }

    private static void flow1() {
        final float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);

        final long timerStart = System.currentTimeMillis();

        for (int i = 0; i< SIZE; ++i) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        final long nowTime = System.currentTimeMillis();
        System.out.println("calc time flow1 = " + (nowTime - timerStart) + "ms");
    }

    private static void flow2(int countThread) {
        final MyThread[] threads = new MyThread[countThread];
        final int countItemInEveryThread = SIZE / countThread;

        final float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);

        final long timerStart = System.currentTimeMillis();

        for (int i = 0; i < countThread; ++i) {
            final boolean isLast = (i == countThread - 1);

            final float[] subArray;

            if (!isLast) {
                subArray = new float[countItemInEveryThread];
            } else {
                subArray = new float[SIZE - (countItemInEveryThread * (countThread - 1))];
            }
            System.arraycopy(arr, i * countItemInEveryThread, subArray, 0, subArray.length);

            final MyThread thread = new MyThread(subArray);
            threads[i] = thread;

            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < countThread; ++i) {
            final float[] subArray = threads[i].getArr();
            System.arraycopy(subArray, 0, arr, i * countItemInEveryThread, subArray.length);
        }

        final long nowTime = System.currentTimeMillis();
        System.out.println("calc time flow2 = " + (nowTime - timerStart) + "ms");
    }
}
