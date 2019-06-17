package Lesson_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Main {
    public static final int SIZE_ARRAY_WORD = 20;

    public static void main(String[] args) {
        // 1
        final String[] array = generateArray(SIZE_ARRAY_WORD);
        printArray(array);

        final Map<String, Integer> duplicate = new HashMap<>();

        for (String key : array) {
            final int value = duplicate.getOrDefault(key, 0);
            duplicate.put(key, value + 1);
        }

        System.out.println("\n" + duplicate);
        System.out.println();

        // 2
        final PhoneList<String, String> phoneList = new PhoneList<>();

        phoneList.add("John", "+1 222 333 44 55");
        phoneList.add("John", "+1 222 333 77 88");
        phoneList.add("John", "+1 222 333 99 99");
        phoneList.add("David", "+1 234 444 12 43");
        phoneList.add("Jim", "+1 444 234 52 32");
        phoneList.add("Jim", "+1 432 555 12 87");

        final String searchItem = "John";

        final Set<String> phones = phoneList.get(searchItem);
        System.out.println("\n" + searchItem + " = " + phones);
    }


    private static String[] generateArray(int size) {
        String[] origWord = new String[]{
            "One", "Two", "Many", "Big", "Small"
        };

        String[] out = new String[size];
        final Random random = new Random();
        for (int i = 0; i < size; ++i) {
            final int position = random.nextInt(origWord.length);
            out[i] = origWord[position];
        }
        return out;
    }

    private static void printArray(String[] arr) {
        System.out.print("\n[ ");
        for (String str: arr) {
            System.out.print(str + " ");
        }
        System.out.print("]\n");
    }
}
