package sushiGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OrderPanel extends JPanel {
    private final JLabel lb_table = new JLabel("Table: ");
    private final JLabel lb_order = new JLabel("Order: ");
    private final JLabel lb_time = new JLabel("125 seconds left");
    private int timeLeft = 125;
    private int numTable;
    private String order;

    public OrderPanel() {
        this.setLayout(new GridLayout(1, 3));
        this.setPreferredSize(new Dimension(GameFrame.FRAME_WIDTH, 50));

        lb_table.setHorizontalAlignment(SwingConstants.CENTER);
        lb_table.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lb_table);

        lb_order.setFont(new Font("Arial", Font.BOLD, 20));
        lb_order.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lb_order);

        lb_time.setFont(new Font("Arial", Font.BOLD, 20));
        lb_time.setForeground(Color.RED);
        lb_time.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lb_time);

    }

    void updateTable(int table) {
        lb_table.setText("Table: " + table);
        this.numTable = table;
    }

    void updateOrder(String order) {
        lb_order.setText("Order: " + order);
        this.order = order;
    }

    void updateTimeLeft() {
        timeLeft--;
        lb_time.setText(timeLeft + " seconds left");
    }

    boolean hasTimeLeft() {
		return timeLeft != 0;
	}

    void resetPanel() {
        timeLeft = 125;
        lb_time.setText("125 seconds left");
        lb_table.setText("Table: ");
        lb_order.setText("Order: ");
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public JLabel getLb_order() {
        return lb_order;
    }

    public int getNumTable() {
        return numTable;
    }

    public String getOrder() {
        return order;
    }

}
