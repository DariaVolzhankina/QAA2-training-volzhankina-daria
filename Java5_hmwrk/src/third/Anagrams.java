package third;

import third.exceptions.StringHaveWhitespaceException;
import third.exceptions.StringIsEmptyException;
import third.exceptions.StringIsNullException;

import java.util.*;

public class Anagrams {

    public boolean isStringsAnagrams(String str1, String str2) {

        try {
            if (str1 == null || str2 == null) {
                throw new StringIsNullException("The string cannot be null");
            }
        } catch (StringIsNullException ex) {
            ex.printStackTrace();
        }

        str1 = str1.toLowerCase().trim();
        str2 = str2.toLowerCase().trim();

        try {
            if (str1 == "" || str2 == "") {
                throw new StringIsEmptyException("The string cannot be empty");
            }
        } catch (StringIsEmptyException ex) {
            ex.printStackTrace();
        }

        try {
            if (str1.contains(" ") || str2.contains(" ")) {
                throw new StringHaveWhitespaceException("Words cannot contain a whitespace");
            }
        } catch (StringHaveWhitespaceException ex) {
            ex.printStackTrace();
        }

        if (str1.length() != str2.length()) {
            return false;
        }

        char[] array1 = str1.toCharArray();
        char[] array2 = str2.toCharArray();

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (char key : array1) {
            if (map1.containsKey(key)) {
                map1.put(key, map1.get(key) + 1);
            } else {
                map1.put(key, 1);
            }
        }

        for (char key : array2) {
            if (map2.containsKey(key)) {
                map2.put(key, map2.get(key) + 1);
            } else {
                map2.put(key, 1);
            }
        }

        return map1.equals(map2);
    }
}
