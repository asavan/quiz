import java.util.Scanner;

public class T3_PolynomMultiply {

    static public class Polynomial {
        private final long[] coeff;

        public Polynomial(long... coeff) {
            this.coeff = coeff;
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            boolean isFirst = true;
            for (int i = coeff.length-1; i >=0; --i) {
                long c = coeff[i];
                if (c == 0) continue;
                if (c >= 1) {
                    if (i > 1) {
                        if (!isFirst) {
                            b.append("+");
                        }
                        if (c > 1) {
                            b.append(c);
                        }
                        b.append("x^");
                        b.append(i);
                    } else if (i == 1) {
                        if (!isFirst) {
                            b.append("+");
                        }
                        if (c > 1) {
                            b.append(c);
                        }
                        b.append("x");
                    } else {
                        if (!isFirst) {
                            b.append("+");
                        }
                        b.append(c);
                    }
                } else if (c == -1) {
                    if (i > 1) {
                        b.append("-");
                        b.append("x^");
                        b.append(i);
                    } else if (i == 1) {
                        b.append("-");
                        b.append("x");
                    } else {
                        b.append(c);
                    }
                } else {
                        b.append(c);
                    if (i > 1) {
                        b.append("x^");
                        b.append(i);
                    } else if (i == 1) {
                        b.append("x");
                    }
                }

                isFirst = false;
            }
            return b.toString();
        }

        public Polynomial(String str) {
            coeff = new long[21];
            String str2 = str.replace("-", "+-");
            String[] arr = str2.split("\\+");
            for (String s : arr) {
                if (s.length() == 0) continue;
                final long c;
                final int power;
                if (s.contains("x^")) {
                    String[] res = s.split("x\\^");
                    if (res[0].isEmpty()) {
                        c = 1;
                        power = Integer.parseInt(res[1]);
                    } else {
                        if (res[0].equals("-")) {
                            c = -1;
                        } else {
                            c = Long.parseLong(res[0], 10);
                        }
                        power = Integer.parseInt(res[1]);
                    }
                    coeff[power] = c;
                } else if (s.contains("x")) {
                    String[] res = s.split("x");
                    if (res.length == 0 || res[0].isEmpty()) {
                        c = 1;
                    } else {
                        if (res[0].equals("-")) {
                            c = -1;
                        } else {
                            c = Long.parseLong(res[0], 10);
                        }
                    }
                    power = 1;
                    coeff[power] = c;
                } else {
                    power = 0;
                    c = Long.parseLong(s, 10);
                    coeff[power] = c;
                }
            }
        }

        public Polynomial multiply(Polynomial polynomial) {
            int totalLength = coeff.length + polynomial.coeff.length - 1;
            long[] result = new long[totalLength];
            for (int i = 0; i < coeff.length; i++)
                for (int j = 0; j < polynomial.coeff.length; j++) {
                    result[i + j] += coeff[i] * polynomial.coeff[j];
                }
            return new Polynomial(result);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        Polynomial p1 = new Polynomial(s1);
        Polynomial p2 = new Polynomial(s2);
        Polynomial p3 = p1.multiply(p2);
        System.out.println(p3.toString());
    }
}
