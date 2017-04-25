import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Dictionary contains directories and files
 */
public class Dictionary {

    Tree<Letter> tree = new Tree<>(new Letter('*', false));
    File file = new File("./words.txt");

    /**
     * Constructs a Dictionary containing all english words
     */
    public Dictionary() {
        Position<Letter> pos = tree.getRoot();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                for (int i = 0; i < line.length(); i++) {
                    boolean end = false;
                    char ch = line.toLowerCase().charAt(i);
                    if (i == (line.length() - 1)) {
                        end = true;
                    }
                    pos = posInTree(pos, ch, end);
                }
                pos = tree.getRoot();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string where the duplicate characters are removed
     * @param s
     * @return a string without duplicate chars
     */
    String removedDuplicates(String s) {
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    /**
     * gets or creates a new Positition in the Tree according to the position in the tree
     * @param pos
     * @param ch
     * @param end
     * @return the position of the char ch
     */
    public Position<Letter> posInTree(Position<Letter> pos, char ch, boolean end) {
        for (Position<Letter> posi : pos.children()) {
            if (ch == posi.get().getLetterChar()) {
                if (!posi.get().isEnd()) {
                    posi.get().setEnd(end);
                }
                return posi;
            }
        }
        return tree.addChild(pos, new Letter(ch, end));
    }


    /**
     * gets the position of ch that have parent pos
     * @param pos
     * @param ch
     * @return the child with char ch (null if no child)
     */
    private Position<Letter> checkCharacter(Position<Letter> pos, char ch) {
        for (Position<Letter> posi : pos.children()) {
            if (posi.get().getLetterChar() == ch) {
                return posi;
            }
        }
        return null;
    }

    /**
     * Gets the highest values from a given Hashset
     * @param words
     * @return
     */
    HashSet<ScrabbleWord> highestValues(HashSet<ScrabbleWord> words) {
        int highest_value = 0;
        HashSet<ScrabbleWord> set = new HashSet<>();
        for (ScrabbleWord s : words) {
            int current_value = s.getValue();
            if (current_value > highest_value) {
                set.clear();
                set.add(s);
                highest_value = current_value;
            } else if (highest_value == current_value) {
                set.add(s);
            }
        }
        return set;
    }

    /**
     *
     * @param pop the set of matched words
     * @param pos the current position of the character
     * @param prefix the current prefix (currently generated string) contains wildcards
     * @param realPrefix the prefix that does not contain wildcards
     * @param s the string (all remaining chars that have to be permutated)
     * @return
     */
    public HashSet<ScrabbleWord> recursiveWords(HashSet<ScrabbleWord> pop, Position<Letter> pos, String prefix, String realPrefix, String s) {
        String str = removedDuplicates(s);
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '*') {
                for (Position<Letter> p : pos.children()
                        ) {
                    getNextLetter(pop, prefix, realPrefix, s, ch, p);
                }
            } else {
                Position<Letter> p = checkCharacter(pos, ch);
                getNextLetter(pop, prefix, realPrefix, s, ch, p);
            }
        }
        return pop;

    }

    /**
     * gets the next letter
     * @param pop the set of matched words
     * @param prefix the current prefix (currently generated string) contains wildcards
     * @param realPrefix the prefix that does not contain wildcards
     * @param s the string (all remaining chars that have to be permutated)
     * @param ch the character to use for the next recursion
     * @param p the current Position of the letter
     */
    private void getNextLetter(HashSet<ScrabbleWord> pop, String prefix, String realPrefix, String s, char ch, Position<Letter> p) {

        if (p != null) {
            char realChar = p.get().getLetterChar();
            if (p.get().isEnd()) {
                pop.add(new ScrabbleWord(prefix + ch, realPrefix + realChar));
            }
            StringBuilder sb = new StringBuilder(s);
            StringBuilder pr = new StringBuilder(prefix);
            StringBuilder rpr = new StringBuilder(realPrefix);
            sb.deleteCharAt(s.indexOf(ch));
            pr.append(ch);
            rpr.append(realChar);
            recursiveWords(pop, p, pr.toString(), rpr.toString(), sb.toString());
        } else {

        }
    }

    /**
     * gets all matches with a string s, with or without a given prefix
     * @param s
     * @param prefix
     * @return a set of matched words
     */
    public HashSet<ScrabbleWord> allMatches(String s, String prefix) {
        HashSet<ScrabbleWord> pop = new HashSet<>();
        Position<Letter> wordPosition = (lastPositionOfWord(prefix) != null) ? lastPositionOfWord(prefix) : tree.getRoot();
        recursiveWords(pop, wordPosition, prefix, prefix, s);
        return pop;
    }
    /**
     * gets the last position in the tree of a string
     * @param str
     * @return the postion of the last char of the string str
     */
    public Position<Letter> lastPositionOfWord(String str) {

        Position<Letter> pos = tree.getRoot();
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            pos = checkCharacter(pos, ch);
            if (pos == null) {
                return null;
            } else {
                buf.append(ch);
            }
            if (pos.get().isEnd() && str.length() - 1 == i) {
                return pos;
            }
        }
        return null;
    }
}


