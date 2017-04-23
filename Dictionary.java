import javafx.geometry.Pos;

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
    private int size;
    private String name;

    /**
     * Constructs a Dictionary containing all english words
     */
    public Dictionary() {
        size = 1;
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
        //printTree(tree.getRoot(),0);
        //System.out.println(isEnglishWord("teston"));
        //this.getHighestValues(this.allPossibleWords("hcgkouebjt"));
        //this.TestStuff(tree.getRoot(),"","test");
        for (ScrabbleWord word :
                highestValues(this.allMatches("tryquixo",""))) {
            System.out.println(word);
        }


    }

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

    private static List<String> permutations(String s) {
        // TODO Auto-generated method stub
        List<String> combinations = new ArrayList<>();
        if (s.length() == 1) {
            combinations.add(s);
        } else {
            for (int i = 0; i < s.length(); i++) {
                List<String> temp = permutations(s.substring(0, i) + s.substring(i + 1));
                for (String string : temp) {
                    combinations.add(s.charAt(i) + string);
                }
            }
        }
        return combinations;
    }

    HashSet<ScrabbleWord> getHighestValues(HashSet<String> words) {
        int highest_value = 0;
        HashSet<ScrabbleWord> str = new HashSet<>();
        for (String s : words) {
            int current_value = ScrabbleValues.getWordValue(s);
            if (current_value > highest_value) {
                str.clear();
                str.add(new ScrabbleWord(s));
                highest_value = current_value;
            } else if (highest_value == current_value) {
                str.add(new ScrabbleWord(s));
            }
        }
        for (ScrabbleWord p : str
                ) {

            System.out.println(p.getValue() + "       " + p.getWord());
        }
        return str;
    }

    public void printTree(Position<Letter> pos, int letter) {
        System.out.println(letter + ": " + pos.get().getLetterChar());
        letter++;
        if (pos.children() != null) {
            for (Position<Letter> posi : pos.children()
                    ) {
                printTree(posi, letter);
            }

        }
    }

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

    public Position<Letter> lastPositionOfWord(String str) {

        Position<Letter> pos = tree.getRoot();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            pos = checkCharacter(pos, ch);
            if (pos == null) {
               return null;
            } else {
                buf.append(ch);
            }

            if (pos.get().isEnd() && str.length()-1 == i) {
                return pos;
            }

        }

        return null;
    }

    private Position<Letter> checkCharacter(Position<Letter> pos, char ch) {
        for (Position<Letter> posi : pos.children()) {
            if (posi.get().getLetterChar() == ch) {
                return posi;
            }
        }
        return null;
    }

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

    public HashSet<ScrabbleWord> allMatches(String s, String prefix) {
        HashSet<ScrabbleWord> pop = new HashSet<>();
        Position<Letter> wordPosition =(lastPositionOfWord(prefix) !=null) ? lastPositionOfWord(prefix) : tree.getRoot();
        recursiveWords(pop, wordPosition, prefix, s);
        return pop;
    }

    public HashSet<ScrabbleWord> recursiveWords(HashSet<ScrabbleWord> pop, Position<Letter> pos, String prefix, String s) {
        String str = removedDuplicates(s);
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Position<Letter> p = checkCharacter(pos, ch);

            if (p != null) {
                if (p.get().isEnd()) {
                    pop.add(new ScrabbleWord(prefix + ch));
                }
                StringBuilder sb = new StringBuilder(s);
                StringBuilder pr = new StringBuilder(prefix);
                sb.deleteCharAt(s.indexOf(ch));
                pr.append(ch);
                recursiveWords(pop, p, pr.toString(), sb.toString());
            } else {

            }
        }
        return pop;

    }
    void test(){
        char ch ='*';
        if (ch == '*'){

        }
    }
}


