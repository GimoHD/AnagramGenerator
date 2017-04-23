/**
 * Created by Kristof on 4/21/2017.
 */
public class ScrabbleWord implements Comparable {
    private String word;
    private int value;

    public ScrabbleWord(String word, int value) {
        this.setWord(word);
        this.setValue(value);
    }

    public ScrabbleWord(String word) {
        this.setWord(word);
        this.setValue(ScrabbleValues.getWordValue(word));
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return getValue() + Integer.valueOf(getWord().charAt(0));
    }

    @Override
    public String toString() {
        return word + " : " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScrabbleWord) {
            ScrabbleWord test = (ScrabbleWord) obj;
            return getWord() == test.getWord();
        }
        return false;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof ScrabbleWord) {
            ScrabbleWord test = (ScrabbleWord) obj;
            return getValue()- test.getValue();
        }
        return 0;
    }
}
