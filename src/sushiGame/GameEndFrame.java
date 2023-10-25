package sushiGame;

import sushiObject.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameEndFrame extends JFrame {
    private final JLabel lb_timesUp = new JLabel("TIME'S UP");
    private final JLabel lb_score = new JLabel("You got ");
    private final JLabel lb_sushiAmount = new JLabel();
    private final JLabel lb_sushiTypeAmount = new JLabel();
    private final JButton bt_playAgain = new JButton("Play Again");
    private final JPanel panel_playAgainBt = new JPanel();

    public GameEndFrame() {
        super("Time's up");
        this.setLayout(new GridLayout(6, 1));
        lb_timesUp.setForeground(Color.RED);
        lb_timesUp.setHorizontalAlignment(SwingConstants.CENTER);
        lb_timesUp.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lb_timesUp);

        lb_score.setHorizontalAlignment(SwingConstants.CENTER);
        lb_score.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lb_score);

        lb_sushiAmount.setHorizontalAlignment(SwingConstants.CENTER);
        lb_sushiAmount.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lb_sushiAmount);

        lb_sushiTypeAmount.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lb_sushiTypeAmount);

        panel_playAgainBt.add(bt_playAgain);
        this.add(panel_playAgainBt);

        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
    }

    public void setScore(int score) {
        lb_score.setText("You got " + score + " points !!!");
        lb_sushiAmount.setText("You have done " + Sushi.sushiCount + " sushi in total");
        lb_sushiTypeAmount.setText(NigiriSushi.nigiriCount + " nigiri and " + SashimiSushi.sashimiCount + " sashimi");
    }

    public JButton getBt_playAgain() {
        return bt_playAgain;
    }
}
