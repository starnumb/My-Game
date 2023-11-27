package GUI_MotaTest_5;

import GUI_MotaTest_4.MusicPlayer;

import javax.swing.*;

public class GameFrame extends JFrame {


    //定义一个游戏操作区域
    GamePanel gamePanel;
    GameFrame()
    {
        //初始化游戏操作界面
        gamePanel = new GamePanel();

        //组装
        this.add(gamePanel);

        //设置frame的初始属性
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(616, 455);
        this.setVisible(true);

    }

}
