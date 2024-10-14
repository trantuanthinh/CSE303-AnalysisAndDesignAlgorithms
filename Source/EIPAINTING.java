import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class EIPAINTING {
    static InputReader reader;

    private static long calculateHappiness(Hashtable<Integer, Integer> numbers) {
        long happiness = 0;

        LinkedList<Integer> numbersList = new LinkedList<>(numbers.keySet());
        Collections.sort(numbersList);
        int currentIndex = 0;
        int nextIndex = 1;
        // System.out.println(numbers.size());
        // System.out.println(numbersList.size());
        while (!numbersList.isEmpty()) {
            // System.out.println("currentIndex: " + currentIndex + " nextIndex: " +
            // nextIndex);
            if (currentIndex == numbersList.size() - 1 && numbers.size() > 1) {
                nextIndex = 0;
            } else if (numbers.size() == 1) {
                break;
            }
            // System.out.println(
            // "currentValue: " + numbersList.get(currentIndex) + " nextValue: " +
            // numbersList.get(nextIndex));
            if (numbers.size() == 1) {
                break;
            }

            if (numbersList.get(nextIndex) > numbersList.get(currentIndex)) {
                happiness++;
                numbers.put(numbersList.get(currentIndex), numbers.get(numbersList.get(currentIndex)) - 1);
                if (numbers.get(numbersList.get(currentIndex)) == 0) {
                    numbers.remove(numbersList.get(currentIndex));
                    numbersList.remove(currentIndex);
                    currentIndex = nextIndex - 1;
                    nextIndex = currentIndex + 1;
                } else {
                    currentIndex = nextIndex;
                    nextIndex = currentIndex + 1;
                }
            } else {
                numbers.put(numbersList.get(currentIndex), numbers.get(numbersList.get(currentIndex)) - 1);
                if (numbers.get(numbersList.get(currentIndex)) == 0) {
                    numbers.remove(numbersList.get(currentIndex));
                    numbersList.remove(currentIndex);
                    currentIndex = nextIndex - 1;
                    nextIndex = currentIndex + 1;
                }
                currentIndex = nextIndex;
                nextIndex = currentIndex + 1;
            }

        }
        return happiness;

    }

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int n = reader.nextInt();
        Hashtable<Integer, Integer> numbers = new Hashtable<>();

        for (int i = 0; i < n; i++) {
            int num = reader.nextInt();
            numbers.put(num, numbers.getOrDefault(num, 0) + 1);
        }
        long happiness = calculateHappiness(numbers);
        System.out.println(happiness);
    }
    // private static long calculateHappiness(LinkedList<Integer> numbers) {
    // long happiness = 0;
    // int currentIndex = 0;
    // int nextIndex = 1;
    // boolean isRestart = false;

    // if (numbers.get(0) - numbers.get(numbers.size() - 1) == 0) {
    // return 0;
    // }

    // while (!numbers.isEmpty()) {
    // if (numbers.get(currentIndex) < numbers.get(nextIndex)) {
    // happiness++;
    // numbers.remove(currentIndex);
    // currentIndex = nextIndex - 1;
    // nextIndex -= 1;
    // } else if (numbers.get(currentIndex) > numbers.get(nextIndex) && isRestart) {
    // numbers.remove(currentIndex);
    // currentIndex = nextIndex;
    // isRestart = false;
    // }
    // nextIndex += 1;

    // if (numbers.size() == 1) {
    // break;
    // }
    // if (nextIndex == numbers.size() - 1 && !numbers.isEmpty()) {
    // if (numbers.get(currentIndex) < numbers.get(nextIndex)) {
    // happiness++;
    // numbers.remove(currentIndex);
    // currentIndex = nextIndex - 1;
    // nextIndex -= 1;
    // if (numbers.size() == 1) {
    // break;
    // }
    // }
    // if ((numbers.get(0) - numbers.get(numbers.size() - 1)) == 0) {
    // break;
    // }
    // nextIndex = 0;
    // isRestart = true;
    // }
    // }

    // return happiness;
    // }

    // public static void main(String[] args) throws IOException {
    // reader = new InputReader(System.in);
    // int n = reader.nextInt();
    // LinkedList<Integer> numbers = new LinkedList<>();

    // for (int i = 0; i < n; i++) {
    // int num = reader.nextInt();
    // numbers.add(num);
    // }

    // Collections.sort(numbers);

    // long happiness = calculateHappiness(numbers);
    // System.out.println(happiness);
    // }

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
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
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
