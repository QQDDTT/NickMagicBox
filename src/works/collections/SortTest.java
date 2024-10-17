package works.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(2,7,3,4,9,1,0,5);

        l1.sort((a, b) -> a - b);

        // l1.forEach(a -> System.out.println(a));
        l1.forEach(System.out::println);
    }

    
}
