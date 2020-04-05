import java.util.*;

public class Main7 {

    static class Emp {

        private Emp patron;
        private String name;

        public Emp(String name) {
            this.name = name;
        }

        public int getLevel() {
            if (patron == null) {
                return 0;
            }
            return patron.getLevel() + 1;
        }

        public void setPatron(Emp p) {
            patron = p;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Map<String, Emp> map = new HashMap<>();
        map.put("X", new Emp("X"));
        for (int i = 0; i < n - 1; ++i) {
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            String empName = split[0];
            String patronName = split[1];
            Emp emp = map.get(empName);
            if (emp == null) {
                emp = new Emp(empName);
                map.put(empName, emp);
            }
            Emp patron = map.get(patronName);
            if (patron == null) {
                patron = new Emp(patronName);
                map.put(patronName, patron);
            }
            emp.setPatron(patron);
        }
        List<Emp> employees = new ArrayList<>(map.values());
        employees.sort(Comparator.comparing(Emp::getName));
        for (Emp employee : employees) {
            System.out.println(employee.getName() + " " + employee.getLevel());
        }
    }

}