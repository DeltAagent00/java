package Lesson_5;

public class MyThread extends Thread {
    final float[] arr;

    public MyThread(float[] arr) {
        this.arr = arr;
    }

    public float[] getArr() {
        return arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
