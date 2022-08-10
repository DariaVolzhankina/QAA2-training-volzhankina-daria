package third;

public class Anagram {
    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        boolean isStringsAnagrams = anagrams.isStringsAnagrams("abc","bac ");
        System.out.println(isStringsAnagrams);
    }
}
