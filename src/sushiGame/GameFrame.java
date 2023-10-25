package sushiGame;

import sushiObject.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener, ItemListener, MouseListener {
    public static final int FRAME_WIDTH = 1500;
    public static final int FRAME_HEIGHT = 500;
    private final IntroFrame frame_intro = new IntroFrame("Sushi Restaurant Game");
    private final CommandPanel panel_command = new CommandPanel();
    private final GraphicPanel panel_graphic = new GraphicPanel();
    private final OrderPanel panel_order = new OrderPanel();
    private final ScorePanel panel_score = new ScorePanel();
    private final GameEndFrame frame_timesUp = new GameEndFrame();
    private String sushiTopping;
    private Sushi sushiOrder;
    private final Timer timer_gameTime = new Timer(1000, this);
    private final Timer timer_animation = new Timer(1, this);
    private int numTable;
    private int targetPosition;
    private boolean goForward = true;
    private boolean isPlay = false;
    private boolean isConfirmed = false;
    private boolean isServing = false;

    public GameFrame() {
        super("Sushi Restaurant Game");
        this.setLayout(new BorderLayout());
        this.add(panel_command, BorderLayout.WEST);
        this.add(panel_graphic, BorderLayout.CENTER);
        this.add(panel_order, BorderLayout.NORTH);
        this.add(panel_score, BorderLayout.EAST);

        panel_command.getRb_nigiri().addItemListener(this);
        panel_command.getRb_sashimi().addItemListener(this);
        panel_command.getRb_salmon().addItemListener(this);
        panel_command.getRb_maguro().addItemListener(this);
        panel_command.getRb_ebi().addItemListener(this);
        panel_command.getBt_confirm().addActionListener(this);
        panel_score.getBt_playpause().addActionListener(this);
        panel_score.getBt_restart().addActionListener(this);
        panel_graphic.addMouseListener(this);
        frame_intro.getBt_play().addActionListener(this);
        frame_timesUp.getBt_playAgain().addActionListener(this);

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);


    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        int stateChange = e.getStateChange();

        // make user can only press one radio button at a time
        if (source instanceof SushiTypeRadioButton) {
            if (stateChange == 1) {
                if (source == panel_command.getRb_nigiri()) {
                    panel_command.getRb_sashimi().setSelected(false);
                } else if (source == panel_command.getRb_sashimi()) {
                    panel_command.getRb_nigiri().setSelected(false);
                }
                panel_command.enableToppingButtons(true);

            } else {
                panel_command.enableToppingButtons(false);
                panel_command.deselectToppingButtons();
            }

        } else if (source instanceof SushiToppingRadioButton) {
            if (stateChange == 1) {
                if (source == panel_command.getRb_salmon()) {
                    panel_command.getRb_maguro().setSelected(false);
                    panel_command.getRb_ebi().setSelected(false);
                    sushiTopping = "Salmon";
                } else if (source == panel_command.getRb_maguro()) {
                    panel_command.getRb_salmon().setSelected(false);
                    panel_command.getRb_ebi().setSelected(false);
                    sushiTopping = "Maguro";
                } else if (source == panel_command.getRb_ebi()) {
                    panel_command.getRb_salmon().setSelected(false);
                    panel_command.getRb_maguro().setSelected(false);
                    sushiTopping = "Ebi";
                }
                panel_command.getBt_confirm().setEnabled(true);

            } else {
                panel_command.getBt_confirm().setEnabled(false);
                sushiTopping = null;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == frame_intro.getBt_play()) {
            frame_intro.setVisible(false);
            panel_command.enableTypeButtons(true);
            this.setVisible(true);
            timer_gameTime.start();
            isPlay = true;

        } else if (source == panel_command.getBt_confirm()) {
            if (panel_command.getRb_nigiri().isSelected()) {
                sushiOrder = new NigiriSushi(sushiTopping);
            } else if (panel_command.getRb_sashimi().isSelected()) {
                sushiOrder = new SashimiSushi(sushiTopping);
            }
            isConfirmed = true;
            panel_graphic.setSushi(sushiOrder);
            panel_graphic.setConfirmation(isConfirmed);
            enableCommandPanel(false);

        } else if (source == panel_score.getBt_playpause()) {
            if (isPlay) {
                panel_score.getBt_playpause().setText("Play");
                enableCommandPanel(false);
                timer_gameTime.stop();
                timer_animation.stop();
                isPlay = false;
            } else {
                panel_score.getBt_playpause().setText("Pause");
                if (!isConfirmed && panel_graphic.getPlateX() == 0) {
                    panel_command.enableTypeButtons(true);
                    panel_command.getRb_nigiri().setSelected(false);
                    panel_command.getRb_sashimi().setSelected(false);
                }

                timer_gameTime.start();
                isPlay = true;
                if (panel_graphic.getPlateX() != 0) {
                    timer_animation.start();
                }
            }

        } else if (source == panel_score.getBt_restart() || source == frame_timesUp.getBt_playAgain()) {
            resetGame();
        }

        if (source == timer_gameTime) {
            panel_order.updateTimeLeft();
            if (panel_order.getTimeLeft() == 124) {
                int randTable = (int) Math.floor(Math.random() * 4 + 1);
                panel_order.updateTable(randTable);
                randomOrder();
            }
            if (!(panel_order.hasTimeLeft())) {
                timesUp();
            }
        }

        if (source == timer_animation) {
            if (panel_graphic.getPlateX() == targetPosition) {
                String order = sushiTopping + " " + sushiOrder.getTypeOfSushi();
                int score = 0;

                // compare
                if (order.equals(panel_order.getOrder()) && numTable == panel_order.getNumTable()) {
                    score = 100;
                } else {
                    score = -50;
                }
                panel_score.updateScore(score);

                // reset objects, command panel, and graphic panel
                goForward = false;
                sushiOrder = null;
                sushiTopping = null;
                isConfirmed = false;
                isServing = false;
                panel_command.resetPanel();
                panel_graphic.setSushi(sushiOrder);
                panel_graphic.setConfirmation(isConfirmed);
            }

            movePlate(10, goForward);

            if (panel_graphic.getPlateX() == 0) {
                goForward = true;
                timer_animation.stop();
                panel_command.enableTypeButtons(true);
                randomTable();
                randomOrder();
            }

        }
    }

    public void mouseClicked(MouseEvent e) {
        int posX = e.getX();
        int posY = e.getY();
        int customerX = panel_graphic.getCustomerX();
        int customerRadius = panel_graphic.getCustomerRadius();
        boolean isValid = false;

        if (isPlay && isConfirmed && !(isServing)) {
            // check what table is clicked
            if (posX >= 220 - 30 && posX <= 220 - 30 + 65 && posY >= 280 && posY <= 310) {
                numTable = 1;
                isValid = true;
            } else if (posX >= 220 * 2 - 30 && posX <= 220 * 2 - 30 + 65 && posY >= 280 && posY <= 310) {
                numTable = 2;
                isValid = true;
            } else if (posX >= 220 * 3 - 30 && posX <= 220 * 3 - 30 + 65 && posY >= 280 && posY <= 310) {
                numTable = 3;
                isValid = true;
            } else if (posX >= 220 * 4 - 30 && posX <= 220 * 4 - 30 + 65 && posY >= 280 && posY <= 310) {
                numTable = 4;
                isValid = true;
            }

            if (isValid) {
                targetPosition = customerX * numTable - customerRadius + 10;
                timer_animation.start();
                isServing = true;
                enableCommandPanel(false);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    void movePlate(int pixel, boolean goFoward) {
        if (goForward) {
            panel_graphic.setPlateX(panel_graphic.getPlateX() + pixel);
        } else {
            panel_graphic.setPlateX(panel_graphic.getPlateX() - pixel);
        }
        panel_graphic.repaint();
    }

    int randFromOneTo(int count) {
        return (int) Math.floor(Math.random() * count + 1);
    }

    void randomTable() {
        int randTable = randFromOneTo(4);
        panel_order.updateTable(randTable);
    }

    void randomOrder() {
        int randType = randFromOneTo(2);
        int randToppping = randFromOneTo(3);
        String typeOfSushi = "";
        String topping = "";

        if (randType == 1) {
            typeOfSushi = "Nigiri";
        } else if (randType == 2) {
            typeOfSushi = "Sashimi";
        }

        if (randToppping == 1) {
            topping = "Salmon";
        } else if (randToppping == 2) {
            topping = "Maguro";
        } else if (randToppping == 3) {
            topping = "Ebi";
        }

        panel_order.updateOrder(topping + " " + typeOfSushi);
    }

    void timesUp() {
        isPlay = false;
        timer_gameTime.stop();
        timer_animation.stop();
        this.setVisible(false);
//		System.out.println("Time's Up");
        frame_timesUp.setScore(panel_score.getTotalscore());
        frame_timesUp.setVisible(true);
    }

    void resetGame() {
        Sushi.sushiCount = 0;
        NigiriSushi.nigiriCount = 0;
        SashimiSushi.sashimiCount = 0;
        goForward = true;
        isConfirmed = false;
        isServing = false;
        sushiOrder = null;
        sushiTopping = null;
        timesUp();
        frame_timesUp.setVisible(false);
        frame_intro.setVisible(true);
        panel_score.resetPanel();
        panel_order.resetPanel();
        panel_graphic.resetPanel();
        panel_command.getRb_nigiri().setSelected(false);
        panel_command.getRb_sashimi().setSelected(false);
    }

    void enableCommandPanel(boolean toEnable) {
        panel_command.enableToppingButtons(toEnable);
        panel_command.enableTypeButtons(toEnable);
        panel_command.getBt_confirm().setEnabled(toEnable);
    }

    public CommandPanel getPanel_command() {
        return panel_command;
    }

    public GraphicPanel getPanel_graphic() {
        return panel_graphic;
    }

    public OrderPanel getPanel_order() {
        return panel_order;
    }

    public ScorePanel getPanel_score() {
        return panel_score;
    }

    public GameEndFrame getFrame_timesUp() {
        return frame_timesUp;
    }


}


