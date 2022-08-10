package third;
import java.util.*;

public class Anagrams {

    public boolean isStringsAnagrams(String str1, String str2){

        try {
            if (str1 != null && str2 != null) {
                System.out.println("You have entered the values");
            } else {
                throw new NullException("The string cannot be null");
            }
        } catch (NullException ex) {
            ex.printStackTrace();
        }

        str1 = str1.toLowerCase().replace(" ", "");
        str2 = str2.toLowerCase().replace(" ", "");

        if(str1.length() != str2.length()){
            return false;
        }

        char[] array1 = str1.toCharArray();
        char[] array2 = str2.toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for (int i = 0; i< array1.length;i++){
            char key = array1[i];
            if(map1.containsKey(key)){
                int counter = map1.get(key);
                counter +=1;
                map1.put(key,counter);
            }else {
                map1.put(key, 1);
            }
        }

        for (int i = 0; i<array2.length;i++){
            char key = array2[i];
            if(map2.containsKey(key)){
                int counter = map2.get(key);
                counter += 1;
                map2.put(key,counter);
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
