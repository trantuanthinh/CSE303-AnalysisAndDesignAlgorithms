import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

// 100 done
public class EIFLIP {
    static InputReader reader;
    static StringBuilder sb = new StringBuilder();

    static int countStep(boolean[][] board) {
        int minCount = Integer.MAX_VALUE;
        for (int i = 1; i <= 512; i++) {
            boolean[][] tempBoard = new boolean[3][3];
            int count = 0;
            for (int j = 0; j < 9; j++) {
                if ((i & (1 << j)) > 0) {
                    tempBoard = click(tempBoard, j);
                    count++;
                }
            }
            if (compareMatrix(board, tempBoard)) {
                minCount = Math.min(minCount, count);
            }
        }
        return minCount;
    }

    static boolean compareMatrix(boolean[][] board1, boolean[][] board2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1[i][j] != board2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean[][] click(boolean[][] clickBoard, int position) { // board 3x3, position 0->8
        int row = position / 3;
        int col = position % 3;
        if (col == 0 || col == 2) {
            col = Math.abs(col - 2);
        }

        clickBoard[row][col] = !clickBoard[row][col];

        if (row == 0 || row == 2) {
            clickBoard[1][col] = !clickBoard[1][col];
        } else {
            clickBoard[0][col] = !clickBoard[0][col];
            clickBoard[2][col] = !clickBoard[2][col];
        }

        if (col == 0 || col == 2) {
            clickBoard[row][1] = !clickBoard[row][1];
        } else {
            clickBoard[row][0] = !clickBoard[row][0];
            clickBoard[row][2] = !clickBoard[row][2];
        }
        return clickBoard;
    }

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        int numberOfTestcases = reader.nextInt();
        for (int i = 0; i < numberOfTestcases; i++) {
            boolean[][] board = new boolean[3][3];
            for (int j = 0; j < 3; j++) {
                String each = reader.nextLine();
                for (int k = 0; k < 3; k++) {
                    char eachChar = each.charAt(k);
                    if (eachChar == '*') {
                        board[j][k] = true;
                    }
                }
            }
            int count = countStep(board);
            sb.append(count).append("\n");
        }
        System.out.println(sb);
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
