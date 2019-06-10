package Lesson_2.exception;

/**
 * Created by Alexandr Zheleznyakov on 2019-06-07.
 */
public class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j, String data) {
        super("Ошибочные данные в ячейке [" + i + "][" + j + "]{\"" + data + "\"}");
    }
}
