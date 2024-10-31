import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

// 45
public class EI2122Q1ADAM2 {
    static InputReader reader;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        int m = reader.nextInt();
        int pairs = reader.nextInt();

        List<Integer> men = new ArrayList<>();
        List<Integer> women = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = reader.nextInt();
            men.add(num);
        }
        for (int i = 0; i < m; i++) {
            int num = reader.nextInt();
            women.add(num);
        }
        men.sort((s1, s2) -> {
            return s1 - s2;
        });
        women.sort((s1, s2) -> {
            return s1 - s2;
        });

        int maxDiff = binarySearch(men, women, pairs);
        System.out.println(maxDiff);
    }

    static int binarySearch(List<Integer> men, List<Integer> women, int pairs) {
        int left = 0;
        int diff1 = men.get(men.size() - 1) - women.get(0);
        int diff2 = women.get(women.size() - 1) - men.get(0);
        int right = Math.max(diff1, diff2);

        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canFormPairs(men, women, pairs, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    static boolean canFormPairs(List<Integer> men, List<Integer> women, int pairs, int maxDiff) {
        int menIndex = 0;
        int womenIndex = 0;
        int pairsFormed = 0;
        while (menIndex < men.size() && womenIndex < women.size()) {
            if (Math.abs(men.get(menIndex) - women.get(womenIndex)) <= maxDiff) {
                pairsFormed++;
                menIndex++;
                womenIndex++;
                if (pairsFormed == pairs) {
                    return true;
                }
            } else if (men.get(menIndex) > women.get(womenIndex)) {
                menIndex++;
            } else {
                womenIndex++;
            }
        }
        return false;
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
