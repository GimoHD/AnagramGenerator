import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;


public class GUI extends JFrame {
    private static final long serialVersionUID = 5514566716849599754L;
    private JButton btnMove = new JButton("Find all");
    private JLabel labelAnagram = new JLabel("Anagram letters:");
    private JTextField scrabbleLetters = new JTextField();

    // Model View Controller: We change contents of the JList through
    // manipulation of a MODEL, not the actual JList.


    private DefaultListModel model_1 = new DefaultListModel();
    private DefaultListModel model_2 = new DefaultListModel();

    private JList jList2 = new JList(model_2);
    private JList jList1 = new JList(model_1);

    // JLists do not scroll by default.  We need to add them to
    // an encompassing JScrollPane
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();

    Dictionary dic = new Dictionary();

    /**
     * Creates a GUI
     */
    public GUI() {
        super();
        Debugger.turnOn();
        this.getContentPane().setLayout(null);

        labelAnagram.setBounds(15,10,100,20);
        scrabbleLetters.setBounds(15,30,100,20);
        btnMove.setBounds(240,30,100,20);

        jScrollPane1.setBounds(new Rectangle(15, 60, 200, 450));
        jScrollPane2.setBounds(new Rectangle(230, 60, 200, 450));

        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        btnMove.addActionListener(e -> {
            model_1.clear();
            ArrayList<ScrabbleWord> words = new ArrayList<>();
             String s = scrabbleLetters.getText();
            words.addAll(dic.allMatches(s,""));
            Collections.sort(words,Collections.reverseOrder());
            for (ScrabbleWord w:words
                 ) {
               model_1.addElement(w);

             }
        });

        this.getContentPane().add(this.labelAnagram, null);
        this.getContentPane().add(scrabbleLetters, null);

        this.getContentPane().add(jScrollPane2, null);

        this.getContentPane().add(jScrollPane1, null); // to a JScrollPane
        this.getContentPane().add(btnMove, null);

        jScrollPane2.getViewport().add(jList2, null); // Must add JList
        jScrollPane1.getViewport().add(jList1, null); // JList to JScrollPane

        jList2.setSelectedIndex(0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addToArraylist(ArrayList<ScrabbleWord> words){
    }




}