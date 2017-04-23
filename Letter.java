/**
 * Created by Kristof on 4/21/2017.
 */
public class Letter {

    private char letterChar;
    private boolean isEnd;

    public Letter(char letterChar, boolean isEnd) {
        this.setLetterChar(letterChar);
        this.setEnd(isEnd);
    }

    public char getLetterChar() {
        return letterChar;
    }

    public void setLetterChar(char letterChar) {
        this.letterChar = letterChar;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "letterChar=" + letterChar +
                ", isEnd=" + isEnd +
                '}';
    }
}
