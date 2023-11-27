package GUI_MotaTest_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    完美精致的COPY了开始页面
    Made by me
 */
public class EndFrame extends JFrame implements ActionListener {
    JButton startButton;
    JLabel startFont;
    MusicPlayer music;
    Thread endThread;
    EndFrame()
    {

        startButton = new JButton("庆祝！!");
        startFont = new JLabel("恭喜你通关");
        music = new MusicPlayer("motaMusic\\Cheer.wav");


        startFont.setBounds(180, 100, 400, 100);
        startFont.setHorizontalTextPosition(JLabel.CENTER);
        startFont.setForeground(Color.orange);
        startFont.setFont(new Font("微软雅黑", Font.PLAIN, 50));

        startButton.setBounds(190, 200, 200, 100);
        startButton.setFont(new Font("微软雅黑", Font.BOLD, 40));
        startButton.setForeground(Color.pink);
        startButton.setFocusable(false);
        startButton.addActionListener(this);


        this.add(startButton);
        this.add(startFont);
        music.start();

        //设置frame的初始属性
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(616, 455);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startButton)
        {
            music.setPath("motaMusic\\Hi.wav");
            endThread = new Thread(music);
            endThread.start();
        }

    }
}