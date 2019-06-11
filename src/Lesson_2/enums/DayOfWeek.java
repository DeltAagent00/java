package Lesson_2.enums;

/**
 * Created by Alexandr Zheleznyakov on 2019-06-07.
 */
public enum DayOfWeek {
    Monday(0),
    Tuesday(1),
    Wednesday(2),
    Thursday(3),
    Friday(4),
    Saturday(5),
    Sunday(6);

    private int numOfDay;

    DayOfWeek(int numOfDay) {
        this.numOfDay = numOfDay;
    }

    public int getNumOfDay() {
        return numOfDay;
    }}
