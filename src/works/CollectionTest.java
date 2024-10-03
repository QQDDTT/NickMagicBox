package works;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class CollectionTest {
    public static void main(String...args) {
        List<String> l1 = new ArrayList<>();
        List<?> l2 = new LinkedList<>();
        List<?> l3 = new Vector<>();
        List<List<?>> LL = new ArrayList<>();

        System.out.println(l1.size());
        l1.add("a");
        l1.add("a");
        System.out.println(l1.size());
        System.out.println("------------------------------------------");

        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new LinkedHashSet<>();
        System.out.println(s1.size());
        s1.add("a");
        s1.add("a");
        System.out.println(s1.size());
        System.out.println("------------------------------------------");

        Queue<String> q1 = new ArrayDeque<String>();
        // BlockingQueue<String> q2 = new ArrayBlockingQueue<>(0);

        System.out.println("------------------------------------------");
        Deque<String> d1 = new ArrayDeque<>();

        System.out.println("------------------------------------------");
        Map<String,String> m1 = new HashMap<>();
        Map<?,?> m2 = new LinkedHashMap<>();
        Map<?,?> m3 =new TreeMap<>();
        m1.put("1", "123");
        m1.put("1", "456");
        System.out.println(m1.get("1"));
        Entry<?,?> e = null;

    }
}
