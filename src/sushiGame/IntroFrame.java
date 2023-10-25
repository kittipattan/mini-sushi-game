package sushiGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IntroFrame extends JFrame {
    private final JPanel panel_title = new JPanel();
    private final JPanel panel_instruction = new JPanel();
    private final JPanel panel_play = new JPanel();
    private final JButton bt_play = new JButton("Play");
    private final JLabel lb_gameTitle = new JLabel("Sushi Restaurant Game");
    private final JLabel lb_instruction = new JLabel("<html><center>INSTRUCTION<br><br>"
            + "You have to serve the <br> corrrect sushi to the <br> correct table<br>"
            + "with in 125 seconds.<br><br>"
            + "Correct: +100 points , Wrong: -50 points<br><br>"
            + "1. Wait for the order at the top panel<br>"
            + "2. Select the type at the left panel<br>"
            + "3. Select the topping at the left panel<br>"
            + "4. Press confirm at the left panel<br>"
            + "5. Select the table by pressing on the table number</center></html>");

    public IntroFrame(String title) {
        super(title);

        // panel_title
        panel_title.setPreferredSize(new Dimension(this.getWidth(), 120));
        panel_title.setLayout(new GridLayout(1, 1));
        lb_gameTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lb_gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panel_title.add(lb_gameTitle);

        // panel_instruction
        panel_instruction.add(lb_instruction);

        // panel_play
        panel_play.setPreferredSize(new Dimension(this.getWidth(), 70));
        panel_play.add(bt_play);

        // IntroFrame
        this.add(panel_title, BorderLayout.NORTH);
        this.add(panel_instruction, BorderLayout.CENTER);
        this.add(panel_play, BorderLayout.SOUTH);

        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JButton getBt_play() {
        return bt_play;
    }


}
