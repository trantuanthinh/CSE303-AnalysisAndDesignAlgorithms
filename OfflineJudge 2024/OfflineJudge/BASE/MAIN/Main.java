import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;

// 85
class  Main {
    static InputReader reader;

    public static boolean canPermutate(Hashtable<Integer, Integer> tableA, Hashtable<Integer, Integer> tableB, int k) {
        List<Integer> listA = new ArrayList<>(tableA.keySet());
        List<Integer> listB = new ArrayList<>(tableB.keySet());
        if (listA.isEmpty() && listB.isEmpty()) {
            return true;
        }
        for (int i = 0; i < listA.size(); i++) {
            for (int j = 0; j < listB.size(); j++) {
                int each = listA.get(i);
                int key = listB.get(j);
                int different = Math.abs(each - key);
                if (different <= k) {
                    k -= different;
                    int countA = tableA.get(each);
                    int countB = tableB.get(key);
                    tableA.put(each, countA - 1);
                    tableB.put(key, countB - 1);
                    if (tableA.get(each) == 0) {
                        tableA.remove(each);
                    }
                    if (tableB.get(key) == 0) {
                        tableB.remove(key);
                    }
                    return tableA.isEmpty() && tableB.isEmpty();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        int k = reader.nextInt();
        Hashtable<Integer, Integer> tableA = new Hashtable<>();
        Hashtable<Integer, Integer> tableB = new Hashtable<>();
        for (int i = 0; i < n; i++) {
            int num = reader.nextInt();
            tableA.put(num, tableA.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            int num = reader.nextInt();
            if (tableA.containsKey(num)) {
                tableA.put(num, tableA.get(num) - 1);
                if (tableA.get(num) == 0) {
                    tableA.remove(num);
                }
            } else {
                tableB.put(num, tableB.getOrDefault(num, 0) + 1);
            }
        }

        if (canPermutate(tableA, tableB, k)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static public class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        @SuppressWarnings("unused")
        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }
}

