package third;
import third.exceptions.StringIsNullException;

import java.util.*;

public class Anagrams {

    public boolean isStringsAnagrams(String str1, String str2){

        try {
            if (str1 != null && str2 != null) {
                System.out.println("You have entered the values");
            } else {
                throw new StringIsNullException("The string cannot be null");
            }
        } catch (StringIsNullException ex) {
            ex.printStackTrace();
        }

        str1 = str1.toLowerCase().replace(" ", "");
        str2 = str2.toLowerCase().replace(" ", "");

        if(str1.length() != str2.length()){
            return false;
        }

        char[] array1 = str1.toCharArray();
        char[] array2 = str2.toCharArray();

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for (int i = 0; i< array1.length;i++){
            char key = array1[i];
            if(map1.containsKey(key)){
                map1.put(key, map1.get(key) + 1);
            }else {
                map1.put(key, 1);
            }
        }

        for (int i = 0; i<array2.length;i++){
            char key = array2[i];
            if(map2.containsKey(key)){
                map2.put(key, map2.get(key) + 1);
            }else {
            map2.put(key,1);}
        }

        if (map1.equals(map2)){
            return true;
        }
        else{
            return false;
        }
    }
}
