/**
 * A word for scrabble containing the score, word (* included) an realword
 */
public class ScrabbleWord implements Comparable {
    private String word;
    private String realWord;
    private int value;

    /**
     * Creates a ScrabbleWord
     * @param word
     * @param realWord
     */
    public ScrabbleWord(String word, String realWord) {
        this.setWord(word);
        this.setRealWord(realWord);
        this.setValue(ScrabbleValues.getWordValue(word));
    }

    /**
     * gets the word to use in scrabble (* included)
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * sets the word
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * gets the value of the word
     * @return value of the word
     */
    public int getValue() {
        return value;
    }

    /**
     * sets the value of the word
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the real word, (* excluded)
     * @param word
     */
    public void setRealWord(String word) {
        this.realWord = word;
    }

    /**
     * generates a hashCode for the ScrabbleWord (Important!!! => usage of hashSet)
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return (getWord().hashCode()*41 + value*41);
    }

    /**
     * a tostring for the ScrabbleWord
     * @return the scrabbleword string
     */
    @Override
    public String toString() {
        return word + " : " + realWord + " : " + value;
    }

    /**
     * checks if 2 objects are equal
     * @param obj
     * @return true if equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScrabbleWord) {
            ScrabbleWord test = (ScrabbleWord) obj;
            return getWord() == test.getWord();
        }
        return false;
    }

    /**
     * compares to an object
     * @param obj
     * @return the integer of the comparation
     */
    @Override
    public int compareTo(Object obj) {
        if (obj instanceof ScrabbleWord) {
            ScrabbleWord test = (ScrabbleWord) obj;
            return getValue() - test.getValue();
        }
        return 0;
    }

}
