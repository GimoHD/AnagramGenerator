import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * creates a GUI to generate and control the Anagram generator
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 5514566716849599754L;
    Dictionary dic = new Dictionary();
    private JButton btnFindAll = new JButton("Find");
    private JButton btnAddToBoard = new JButton("Add to board");
    private JLabel labelAnagram = new JLabel("Anagram letters:");
    private JLabel labelBoard = new JLabel("Words on the board:");
    private JLabel labelResults = new JLabel("Search results:");
    private JTextField scrabbleLetters = new JTextField();
    private JCheckBox chkboxSorted = new JCheckBox("Sorted");
    private JCheckBox chkboxHighestOnly = new JCheckBox("Highest only");
    private DefaultListModel<ScrabbleWord> model_1 = new DefaultListModel<>();
    private DefaultListModel<ScrabbleWord> model_2 = new DefaultListModel<>();
    private JList<ScrabbleWord> jList2 = new JList<>(model_2);
    private JList<ScrabbleWord> jList1 = new JList<>(model_1);
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private HashSet<ScrabbleWord> wordsOnBoard = new HashSet<>();

    /**
     * Creates a GUI
     */
    public GUI() {
        super();
        Debugger.turnOn();
        this.getContentPane().setLayout(null);

        labelAnagram.setBounds(15, 10, 100, 20);
        scrabbleLetters.setBounds(15, 30, 100, 20);
        btnFindAll.setBounds(240, 10, 170, 20);
        btnAddToBoard.setBounds(240, 30, 170, 20);
        chkboxHighestOnly.setBounds(420, 10, 150, 20);
        chkboxSorted.setBounds(420, 30, 150, 20);
        chkboxSorted.setSelected(true);

        labelResults.setBounds(new Rectangle(15, 60, 200, 20));
        jScrollPane1.setBounds(new Rectangle(15, 80, 200, 450));
        jScrollPane2.setBounds(new Rectangle(240, 80, 200, 450));
        labelBoard.setBounds(new Rectangle(240, 60, 200, 20));

        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnFindAll.addActionListener(e -> {
            model_1.clear();
            HashSet<ScrabbleWord> words = new HashSet<>();
            String s = scrabbleLetters.getText();
            words.addAll(dic.allMatches(s, ""));
            for (ScrabbleWord word : wordsOnBoard) {
                words.addAll(dic.allMatches(s, word.getRealWord()));
            }
            if (chkboxHighestOnly.isSelected()) {
                words = dic.highestValues(words);
            }
            ArrayList<ScrabbleWord> sorted = new ArrayList<>(words);
            if (chkboxSorted.isSelected()) {
                sorted.sort(Collections.reverseOrder());
            }
            for (ScrabbleWord w : sorted
                    ) {
                model_1.addElement(w);

            }
        });
        btnAddToBoard.addActionListener(e -> {

            if (!jList1.isSelectionEmpty()) {
                model_2.clear();
                wordsOnBoard.add(model_1.get(jList1.getSelectedIndex()));
                for (ScrabbleWord w : wordsOnBoard
                        ) {
                    model_2.addElement(w);

                }
                model_1.clear();
            }
        });

        this.getContentPane().add(chkboxHighestOnly, null);
        this.getContentPane().add(chkboxSorted, null);
        this.getContentPane().add(this.labelAnagram, null);
        this.getContentPane().add(scrabbleLetters, null);
        this.getContentPane().add(labelBoard, null);
        this.getContentPane().add(labelResults, null);
        this.getContentPane().add(jScrollPane2, null);

        this.getContentPane().add(jScrollPane1, null); // to a JScrollPane
        this.getContentPane().add(btnFindAll, null);
        this.getContentPane().add(btnAddToBoard, null);

        jScrollPane2.getViewport().add(jList2, null); // Must add JList
        jScrollPane1.getViewport().add(jList1, null); // JList to JScrollPane

        jList2.setSelectedIndex(0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}