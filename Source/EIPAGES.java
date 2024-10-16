import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

// 100 done
public class EIPAGES {
    static InputReader reader;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int quantityOfPages = reader.nextInt();
        int[] pages = createArray(quantityOfPages);
        Arrays.sort(pages);
        for (int i = 0; i < quantityOfPages; i++) {
            int countConsecutive = 1;
            int nextIndex = 0;
            if (checkLastNumber(pages[i], pages, quantityOfPages)) {
                stringBuilder.append(pages[i]);
            } else {
                int firstValue = pages[i];
                int lastValue = 0;
                for (int j = i + 1; j < quantityOfPages; j++) {
                    nextIndex++;
                    if (checkConsecutive(pages[i], nextIndex, pages[j])) {
                        lastValue = pages[j];
                        countConsecutive++;
                    } else {
                        break;
                    }
                }
                if (countConsecutive >= 3) {
                    stringBuilder.append(firstValue).append("-").append(lastValue).append(" ");
                    i = countConsecutive + i - 1;
                } else if (countConsecutive == 2) {
                    stringBuilder.append(firstValue).append(" ").append(lastValue).append(" ");
                    i++;
                } else {
                    stringBuilder.append(firstValue).append(" ");
                }
                if (checkLastNumber(lastValue, pages, quantityOfPages)) {
                    break;
                }
            }
        }
        System.out.println(stringBuilder);
    }

    static boolean checkLastNumber(int pageNumber, int[] pages, int quantityOfPages) {
        if (pageNumber == pages[quantityOfPages - 1]) {
            return true;
        }
        return false;
    }

    static boolean checkConsecutive(int pageNumber, int nextIndex, int nextPage) {
        if (pageNumber + nextIndex == nextPage) {
            return true;
        }
        return false;
    }

    static int[] createArray(int quantity) {
        int[] numbers = new int[quantity];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = reader.nextInt();
        }
        return numbers;
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