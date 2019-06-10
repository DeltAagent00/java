package Lesson_2;

import Lesson_2.enums.DayOfWeek;
import Lesson_2.exception.MyArrayDataException;
import Lesson_2.exception.MyArraySizeException;

/**
 * Created by Alexandr Zheleznyakov on 2019-06-07.
 */
public class Main {
    private static final int CORRECT_SIZE_ARRAY = 4;

    private static final int COUNT_WORK_HOUR_OF_WEEK = 40;
    private static final int COUNT_WORK_HOUR_OF_DAY = 8;


    public static void main(String[] args) {
        /** 1 **/
        final String[][] stringArray = {
                                            {"1", "2", "3", "4"},
                                            {"5", "6", "7", "8"},
                                            {"9", "10", "11", "12"},
                                            {"13", "14", "15", "16"}
                                        };
        try {
            checkArray(stringArray);
            final int summ = getSumm(stringArray);
            System.out.println("Сумма массива = " + summ);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 2 **/
        System.out.println(getWorkingHourse(DayOfWeek.Monday));
    }

    private static void checkArray(String[][] array) throws MyArraySizeException {
        if (array.length != CORRECT_SIZE_ARRAY || array[0].length != CORRECT_SIZE_ARRAY) {
            throw new MyArraySizeException(CORRECT_SIZE_ARRAY);
        }
    }

    private static int getSumm(String[][] array) throws MyArrayDataException {
        int summ = 0;
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; ++i) {
                for (int j = 0; j < array[i].length; ++j) {
                    try {
                        summ += Integer.valueOf(array[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(i, j, array[i][j]);
                    }
                }
            }
        }
        return summ;
    }

    private static String getWorkingHourse(DayOfWeek dayOfWeek) {
        final int leftHours;
        switch (dayOfWeek) {
            case Saturday:
            case Sunday:
                return "Сегодня выходной";
            default:
                leftHours = dayOfWeek.getNumOfDay() * COUNT_WORK_HOUR_OF_DAY;
        }
        final int countWorkHours = COUNT_WORK_HOUR_OF_WEEK - leftHours;
        return "Рабочих часов осталось " + countWorkHours;
    }
}
