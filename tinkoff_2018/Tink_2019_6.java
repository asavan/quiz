import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Tink_2019_6 {

    public static void main(String[] args) {

        Map<String, Map<String, Integer>> products = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String producer = scanner.next();
            if (producer == null || producer.isEmpty()) {
                break;
            }
            String productName = scanner.next();
            int count = scanner.nextInt();
            // scanner.nextLine();
            Map<String, Integer> ps = products.get(producer);
            if (ps == null) {
                ps = new TreeMap<>();
                ps.put(productName, count);
                products.put(producer, ps);
            } else {
                Integer prevCount = ps.get(productName);
                if (prevCount == null) {
                    ps.put(productName, count);
                } else {
                    ps.put(productName, count + prevCount);
                }
            }
        }

        for (Map.Entry<String, Map<String, Integer>> stringMapEntry : products.entrySet()) {
            System.out.println(stringMapEntry.getKey() + ":");
            for (Map.Entry<String, Integer> stringIntegerEntry : stringMapEntry.getValue().entrySet()) {
                System.out.println(stringIntegerEntry.getKey() + " " + stringIntegerEntry.getValue());
            }
        }
    }
}
