package com.netbox.ui;

import com.netbox.entity.Box;
import com.netbox.entity.BoxDefine;
import com.netbox.control.TerisEventListener;
import com.netbox.control.TerisControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class GamePanel extends JPanel {

    GamePanel() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(580, 700));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //画边界
        g.drawRect(BoxDefine.panelTopX(), BoxDefine.panelTopY() - 15, BoxDefine.panelWidth(), BoxDefine.panelHeight());
    }
}

public class MainFrame extends JFrame {
    public static int score=0;
    public static JPanel gamePanel = new GamePanel();
    private boolean gameStartFlag = false;

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    JLabel scoreLabel = new JLabel();

    public boolean isGameStartFlag() {
        return gameStartFlag;
    }

    public void setGameStartFlag(boolean gameStartFlag) {
        this.gameStartFlag = gameStartFlag;
    }

    public Box getCurrentBox() {
        if (currentBox == null)
            currentBox = new Box(new Random().nextInt(6) + 1, BoxDefine.boxTopXDif(), BoxDefine.boxTopYDif());
        return currentBox;
    }

    public void setCurrentBox(Box currentBox) {
        this.currentBox = currentBox;
    }

    private static Box currentBox = null;
    public static MainFrame mai = null;

    public static MainFrame getInstance() {
        if (mai == null)
            mai = new MainFrame();
        return mai;
    }

    public static void init() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        tk.addAWTEventListener(new TerisEventListener(), AWTEvent.KEY_EVENT_MASK);
        TerisControl tc = new TerisControl();
        tc.boxAutoMove();
    }

    public static void displayFrame() {
        init();
        MainFrame mai = MainFrame.getInstance();
        mai.setLayout(new BorderLayout());
        JToolBar jtoolbar = new JToolBar();
        JLabel jl = new JLabel();

        jl.setText("Teris Game");
        jtoolbar.add(jl);

        Font font = new Font("黑体", Font.BOLD, 24);
        mai.getScoreLabel().setFont(font);
        mai.getScoreLabel().setText("Ready?");


        //gamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton startBtn = new JButton("Start Game");
        startBtn.setFocusable(false);
        gamePanel.add(startBtn);
        JButton pauseBtn = new JButton("Pause Game");
        pauseBtn.setFocusable(false);
        gamePanel.add(pauseBtn);

        gamePanel.setBorder(BorderFactory.createEtchedBorder());


        gamePanel.add(mai.getScoreLabel());
        JComponent td = new TerisDisplay();

        gamePanel.add(td);
        gamePanel.setLayout(null);

        gamePanel.getComponent(0).setBounds(140, 10, 150, 20);
        gamePanel.getComponent(1).setBounds(320, 10, 150, 20);
        gamePanel.getComponent(2).setBounds(400, 360, 100, 100);
        gamePanel.getComponent(3).setBounds(0, 0, 500, 700);
        mai.add(gamePanel, BorderLayout.EAST);
      //  mai.add(jtoolbar, BorderLayout.SOUTH);


        mai.setSize(600, 760);
        mai.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // gamePanel.getComponent(3).setBounds(500, 360, 100, 100);
        mai.setVisible(true);


        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mai.setGameStartFlag(true);
            }
        });

        pauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mai.setGameStartFlag(false);
            }
    });

    }
}  