import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// 69.70
class  Main {
    static InputReader reader;
    static long from = 0;
    static long to = 0;
    static long countAll = 0;

    // (L-2^x) / 2^(x+1) <= K <= (R-2^x) / 2^(x+1)
    public static void countOne(long num, long height) {

        long mod = num % 2;

        double left = (double) (from - Math.pow(2, height)) / Math.pow(2, height + 1);
        double right = (double) (to - Math.pow(2, height)) / Math.pow(2, height + 1);

        // if (left < 0) {
        // left = Math.ceil(left);
        // } else {
        // left = Math.floor(left);
        // }

        // if (right > 0) {
        // right = Math.ceil(left);
        // } else {
        // right = Math.floor(left);
        // }

        long countK = (long) (Math.floor(right) - Math.ceil(left)) + 1L;

        if (countK > 0) {
            if (height == 0) {
                countAll += countK;
            } else {
                countAll += countK * mod;
            }
        }

        // if (num == 451658381251L) {
        // System.out.println("height: " + height);
        // System.out.println("num: " + num);
        // System.out.println("num%2: " + num % 2);
        // System.out.println("left: " + left);
        // System.out.println("(long) Math.ceil(left): " + (long) Math.floor(left));
        // System.out.println("right: " + right);
        // System.out.println("(long) (Math.floor(right): " + (long)
        // (Math.ceil(right)));
        // System.out.println("countK: " + countK);
        // System.out.println("countAll: " + countAll);
        // }

        if (height == 0) {
            return;
        }
        countOne(num / 2, height - 1);
    }

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);

        long num = reader.nextLong();
        from = reader.nextLong();
        to = reader.nextLong();

        long maxHeight = (long) Math.floor(Math.log(num) / Math.log(2));

        if ((1L << maxHeight) > num) {
            maxHeight--;
        }

        if (num != 0) {
            countOne(num, maxHeight);
        }

        System.out.print(countAll);
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

