/**
 * Letter class to store a letter in a tree node
 */
public class Letter {

    private char letterChar;
    private boolean isEnd;

    /**
     * Letter that contains a char and checks if it is an end
     * @param letterChar
     * @param isEnd
     */
    public Letter(char letterChar, boolean isEnd) {
        this.setLetterChar(letterChar);
        this.setEnd(isEnd);
    }

    /**
     * gets the char
     * @return the char
     */
    public char getLetterChar() {
        return letterChar;
    }

    /**
     * sets the char
     * @param letterChar
     */
    public void setLetterChar(char letterChar) {
        this.letterChar = letterChar;
    }

    /**
     * checks if it is an end Letter
     * @return
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * sets if it is an end letter
     * @param end
     */
    public void setEnd(boolean end) {
        isEnd = end;
    }

    /**
     * overridden toString method
     * @return
     */
    @Override
    public String toString() {
        return "Letter{" +
                "letterChar=" + letterChar +
                ", isEnd=" + isEnd +
                '}';
    }
}
