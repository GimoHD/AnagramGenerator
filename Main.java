import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setPreferredSize(new Dimension(1000,800));
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        });
    }
}