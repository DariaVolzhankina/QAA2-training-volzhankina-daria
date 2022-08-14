package second;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Duplicates {
    public void duplicates(String str) {
        String[] words = str.toLowerCase().split("[^\\p{L}\\p{N}]+");
        List<String> withDuplicates = Arrays.stream(words).collect(Collectors.toList());
        List<String> withoutDuplicates = Arrays.stream(words).distinct().toList();

        for (String o : withoutDuplicates) {
            withDuplicates.remove(o);
        }

        System.out.println(Arrays.stream(withDuplicates.toArray()).distinct().toList());
    }
}
