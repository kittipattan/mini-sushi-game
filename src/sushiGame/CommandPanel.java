package sushiGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CommandPanel extends JPanel {
    private final JRadioButton rb_nigiri = new SushiTypeRadioButton("Nigiri");
    private final JRadioButton rb_sashimi = new SushiTypeRadioButton("Sashimi");
    private final JRadioButton rb_salmon = new SushiToppingRadioButton("Salmon");
    private final JRadioButton rb_maguro = new SushiToppingRadioButton("Maguro");
    private final JRadioButton rb_ebi = new SushiToppingRadioButton("Ebi");
    private final JButton bt_confirm = new JButton("Confirm");
    private final JPanel panel_sushiType = new JPanel();
    private final JPanel panel_sushi = new JPanel();
    private final JPanel panel_blank = new JPanel();
    private final JPanel panel_confirm = new JPanel();

    public CommandPanel() {
        this.setLayout(new GridLayout(4, 1));
//		this.setBorder(new LineBorder(Color.black));
        this.setPreferredSize(new Dimension(200, 500));

        panel_sushiType.setLayout(new GridLayout(2, 1));
        panel_sushiType.add(rb_nigiri);
        panel_sushiType.add(rb_sashimi);
        panel_sushiType.setBorder(new TitledBorder("Type of Sushi"));

        panel_sushi.setLayout(new GridLayout(3, 1));
        panel_sushi.add(rb_salmon);
        panel_sushi.add(rb_maguro);
        panel_sushi.add(rb_ebi);
        enableToppingButtons(false);            // disable buttons
        panel_sushi.setBorder(new TitledBorder("Topping / Type of Sashimi"));

        panel_confirm.add(bt_confirm);
        bt_confirm.setEnabled(false);    // disable confirm button

        this.add(panel_sushiType);
        this.add(panel_sushi);
        this.add(panel_blank);
        this.add(panel_confirm);

    }

    void resetPanel() {
        rb_nigiri.setSelected(false);
        rb_sashimi.setSelected(false);
    }

    void deselectToppingButtons() {
        rb_salmon.setSelected(false);
        rb_maguro.setSelected(false);
        rb_ebi.setSelected(false);
    }

    void enableTypeButtons(boolean toEnable) {
        if (toEnable) {
            rb_nigiri.setEnabled(true);
            rb_sashimi.setEnabled(true);
        } else {
            rb_nigiri.setEnabled(false);
            rb_sashimi.setEnabled(false);
        }
    }

    void enableToppingButtons(boolean toEnable) {
        if (toEnable) {
            rb_salmon.setEnabled(true);
            rb_maguro.setEnabled(true);
            rb_ebi.setEnabled(true);
        } else {
            rb_salmon.setEnabled(false);
            rb_maguro.setEnabled(false);
            rb_ebi.setEnabled(false);
        }
    }

    public JRadioButton getRb_nigiri() {
        return rb_nigiri;
    }

    public JRadioButton getRb_sashimi() {
        return rb_sashimi;
    }

    public JRadioButton getRb_salmon() {
        return rb_salmon;
    }

    public JRadioButton getRb_maguro() {
        return rb_maguro;
    }

    public JRadioButton getRb_ebi() {
        return rb_ebi;
    }

    public JButton getBt_confirm() {
        return bt_confirm;
    }


}
