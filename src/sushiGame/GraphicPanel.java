package sushiGame;

import sushiObject.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GraphicPanel extends JPanel {
    private final int plateRadius = 40;
    private int plateX = 0;
    private final int plateY = 255;
    private final int customerRadius = 50;
    private final int customerX = 220;
    private final int customerY = 100;
    private boolean isConfirmed = false;
    private Sushi sushi;

    public GraphicPanel() {
//		this.setBorder(new LineBorder(Color.black));
        this.setBackground(new Color(255, 216, 148));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background
        g.setColor(new Color(142, 150, 153));
        g.fillRect(0, 50, 525, 200);
        g.fillRect(575, 50, this.getWidth(), 200);
        g.setColor(new Color(240, 250, 255));
        g.fillRect(1, 50 + 1, 525 - 2, 200 - 1);
        g.fillRect(575 + 1, 50 + 1, this.getWidth() - 1, 200 - 1);

        // customers
        g.setColor(Color.BLACK);
        for (int i = 1; i < 5; i++) {
            g.fillOval(customerX * i - customerRadius, customerY, 2 * customerRadius, 2 * customerRadius);
            g.fillRoundRect(customerX * i - customerRadius, customerY + 2 * customerRadius - 10, 2 * customerRadius, 4 * customerRadius, 90, 90);
        }

        // table
        g.setColor(new Color(70, 70, 70));
        g.fillRect(0, 260, this.getWidth(), this.getHeight());

        // sushi plate
        g.setColor(new Color(128, 0, 0));
        g.fillOval(plateX, plateY, (2 * plateRadius), 10);
        g.fillOval(plateX, plateY + 5, (2 * plateRadius), 10);
        g.fillRect(plateX, plateY + 5, (2 * plateRadius), 5);

        // table number
        if (isConfirmed) {
            g.setColor(Color.WHITE);
            for (int j = 1; j < 5; j++) {
                g.fillRect(customerX * j - 30, 280, 65, 30);
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            for (int k = 1; k < 5; k++) {
                g.drawString("Table " + k, customerX * k - 25, 300);
            }
        }

        if (sushi instanceof NigiriSushi) {
            String topping = ((NigiriSushi) sushi).getTopping();
            g.setColor(Color.WHITE);
            g.fillRoundRect(plateX + customerRadius - 25, plateY - 5, 30, 10, 15, 15);
            if (topping == "Salmon") {
                g.setColor(new Color(255, 126, 33));
                g.fillRect(plateX + customerRadius - 27, plateY - 7, 35, 8);
            } else if (topping == "Maguro") {
                g.setColor(new Color(255, 66, 79));
                g.fillRect(plateX + customerRadius - 27, plateY - 7, 35, 8);
            } else if (topping == "Ebi") {
                int[] x = {plateX + customerRadius, plateX + customerRadius + 10, plateX + customerRadius + 10};
                int[] y = {plateY - 5, plateY - 1, plateY - 9};
                g.setColor(new Color(255, 151, 99));
                g.fillRect(plateX + customerRadius - 27, plateY - 7, 30, 6);
                g.setColor(new Color(255, 136, 77));
                g.fillPolygon(x, y, 3);
            }
        } else if (sushi instanceof SashimiSushi) {
            String typeOfSashimi = ((SashimiSushi) sushi).getTypeOfSashimi();

            if (typeOfSashimi == "Salmon") {
                g.setColor(new Color(255, 126, 33));
                g.fillRect(plateX + customerRadius - 29, plateY - 1, 35, 8);
                g.fillRect(plateX + customerRadius - 19, plateY - 4, 35, 8);
            } else if (typeOfSashimi == "Maguro") {
                g.setColor(new Color(255, 66, 79));
                g.fillRect(plateX + customerRadius - 29, plateY - 1, 35, 8);
                g.fillRect(plateX + customerRadius - 19, plateY - 4, 35, 8);
            } else if (typeOfSashimi == "Ebi") {
                int[] x1 = {plateX + customerRadius, plateX + customerRadius + 10, plateX + customerRadius + 10};
                int[] y1 = {plateY - 1, plateY + 3, plateY - 5};
                int[] x2 = {plateX + customerRadius - 3, plateX + customerRadius + 7, plateX + customerRadius + 7};
                int[] y2 = {plateY - 4, plateY, plateY - 8};
                g.setColor(new Color(255, 151, 99));
                g.fillRect(plateX + customerRadius - 27, plateY - 1, 30, 6);
                g.setColor(new Color(255, 136, 77));
                g.fillPolygon(x1, y1, 3);
                g.setColor(new Color(255, 151, 99));
                g.fillRect(plateX + customerRadius - 30, plateY - 4, 30, 6);
                g.setColor(new Color(255, 136, 77));
                g.fillPolygon(x2, y2, 3);
            }
        }
    }

    void setConfirmation(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        repaint();
    }

    void resetPanel() {
        this.sushi = null;
        this.isConfirmed = false;
        setPlateX(0);
    }

    public void setSushi(Sushi sushi) {
        this.sushi = sushi;
    }

    public int getPlateRadius() {
        return plateRadius;
    }

    public int getPlateX() {
        return plateX;
    }

    public void setPlateX(int plateX) {
        this.plateX = plateX;
    }

    public int getPlateY() {
        return plateY;
    }

    public int getCustomerRadius() {
        return customerRadius;
    }

    public int getCustomerX() {
        return customerX;
    }

    public int getCustomerY() {
        return customerY;
    }

}
