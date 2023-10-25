package sushiGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ScorePanel extends JPanel {
    private final JLabel lb_score = new JLabel("Score: 0");
    private final JButton bt_playpause = new JButton("Pause");
    private final JButton bt_restart = new JButton("Restart");
    private final JPanel panel_button = new JPanel();
    private int totalScore = 0;

    public ScorePanel() {
//		this.setBorder(new LineBorder(Color.black));
        this.setPreferredSize(new Dimension(200, 500));
        this.setLayout(new GridLayout(2, 1));

        lb_score.setHorizontalAlignment(SwingConstants.CENTER);
        lb_score.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lb_score);

        panel_button.add(bt_playpause);
        panel_button.add(bt_restart);
        this.add(panel_button);
    }

    void updateScore(int scoreAdded) {
        totalScore += scoreAdded;
        lb_score.setText("Score: " + totalScore);
    }

    void resetPanel() {
        totalScore = 0;
        lb_score.setText("Score: 0");
        bt_playpause.setText("Pause");
    }

    public int getTotalscore() {
        return totalScore;
    }

    public JButton getBt_playpause() {
        return bt_playpause;
    }

    public JButton getBt_restart() {
        return bt_restart;
    }


}