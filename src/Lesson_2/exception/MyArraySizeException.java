package Lesson_2.exception;

/**
 * Created by Alexandr Zheleznyakov on 2019-06-07.
 */
public class MyArraySizeException extends Exception {
    public MyArraySizeException(int quadroSize) {
        super("Массив должен быть размером " + quadroSize + "x" + quadroSize);
    }
}
