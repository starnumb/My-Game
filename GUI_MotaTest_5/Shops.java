package GUI_MotaTest_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Shops extends JFrame{
    //设置界面需要的控件
    private JPanel shopPanel;
    private JLabel description;
    private JButton choice1;
    private JButton choice2;
    private JButton choice3;
    ActionListener listener1;
    ActionListener listener2;
    ActionListener listener3;
    MusicPlayer shopMusic;
    Thread shopThread;

    //定义一个构造器
    Shops(int level, int shopper, Player p, StatBar s)
    {
        shopPanel = new JPanel();
        shopMusic = new MusicPlayer("motaMusic\\Confirm.wav");

        //判断是哪个npc
        switch (shopper)
        {
            //神龛的情况
            case 0:
                switch (level)
                {
                    case 3:
                        //三楼金币商店的情况
                        //初始化四个部分
                        description = new JLabel("想要增加你的能力吗？如果你有25个金币，你可以任意选择一项：");
                        choice1 = new JButton("生命+800");
                        choice2 = new JButton("攻击+4");
                        choice3 = new JButton("防御+4");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getGD() >= 25)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();

                                p.setGD(p.getGD() - 25);
                                p.setHP(p.getHP() + 800);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());

                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getGD() >= 25)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 25);
                                p.setATK(p.getATK() + 4);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener3 = e -> {
                            if(p.getGD() >= 25)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 25);
                                p.setDEF(p.getDEF() + 4);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        break;
                    case 11:
                        //11楼金币商店的情况
                        //初始化四个部分
                        description = new JLabel("想要增加你的能力吗？如果你有100个金币，你可以任意选择一项：");
                        choice1 = new JButton("生命+4000");
                        choice2 = new JButton("攻击+20");
                        choice3 = new JButton("防御+20");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getGD() >= 100)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 100);
                                p.setHP(p.getHP() + 4000);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getGD() >= 100)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 100);
                                p.setATK(p.getATK() + 20);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener3 = e -> {
                            if(p.getGD() >= 100)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 100);
                                p.setDEF(p.getDEF() + 20);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        break;
                }
                break;
            case 1:
                //蓝色老人的情况
                switch (level)
                {

                    case 5:
                        //5层经验商人的情况
                        //初始化四个部分
                        description = new JLabel("你好，英雄的人类，只要你有足够的经验，我就可以让你变得更强大：");
                        choice1 = new JButton("提升一级(100)");
                        choice2 = new JButton("增加攻击5(30)");
                        choice3 = new JButton("增加防御5(30)");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getEXP() >= 100)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setEXP(p.getEXP() - 100);
                                p.setHP(p.getHP() + 1000);
                                p.setLV(p.getLV() + 1);
                                p.setATK(p.getATK() + 5);
                                p.setDEF(p.getDEF() + 5);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());

                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getEXP() >= 30)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setEXP(p.getEXP() - 30);
                                p.setATK(p.getATK() + 5);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener3 = e -> {
                            if(p.getGD() >= 30)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setEXP(p.getEXP() - 30);
                                p.setDEF(p.getDEF() + 5);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        break;
                    case 13:
                        //13层经验商人的情况
                        //初始化四个部分
                        description = new JLabel("你好，英雄的人类，只要你有足够的经验，我就可以让你变得更强大：");
                        choice1 = new JButton("提升三级(270)");
                        choice2 = new JButton("增加攻击17(95)");
                        choice3 = new JButton("增加防御17(95)");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getEXP() >= 270)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setEXP(p.getEXP() - 270);
                                p.setLV(p.getLV() + 3);
                                p.setHP(p.getHP() + 3000);
                                p.setATK(p.getATK() + 15);
                                p.setDEF(p.getDEF() + 15);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());

                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getEXP() >= 95)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setEXP(p.getEXP() - 95);
                                p.setATK(p.getATK() + 17);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener3 = e -> {
                            if(p.getGD() >= 95)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setEXP(p.getEXP() - 95);
                                p.setDEF(p.getDEF() + 17);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        break;
                    case 15:
                        //15层救经验商人的情况
                        //初始化四个部分
                        description = new JLabel("只要你有足够的经验，我就可以给你一把神剑，提升120攻击：");
                        choice1 = new JButton("购买神剑(500)");
                        choice2 = new JButton("什么？ 这么贵，不买！");
                        choice3 = new JButton("再考虑考虑");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getEXP() >= 500)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setATK(p.getATK() + 120);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());

                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getEXP() >= 500)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("啥b，有钱还不买？？？？？？");
                                description.repaint();
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("没钱你来这干嘛？？");
                                description.repaint();
                            }
                        };
                        listener3 = e -> {
                            if(p.getEXP() >= 500)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("啥b，有钱还不买？？？？？？");
                                description.repaint();
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("没钱你来这干嘛？？");
                                description.repaint();
                            }
                        };
                        break;
                }
                break;
            case 2:
                //红色老人的情况
                switch (level)
                {
                    case 5:
                        //5层钥匙商人的情况
                        //初始化四个部分
                        description = new JLabel("相信你一定有特殊的需要，只要你有金币，我就可以帮你：");
                        choice1 = new JButton("购买一把黄钥匙(10)");
                        choice2 = new JButton("购买一把蓝钥匙(50)");
                        choice3 = new JButton("购买一把红钥匙(100)");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getGD() >= 10)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 10);
                                p.setKey_yellow(p.getKey_yellow() + 1);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getGD() >= 50)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 50);
                                p.setKey_blue(p.getKey_blue() + 1);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener3 = e -> {
                            if(p.getGD() >= 100)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setGD(p.getGD() - 100);
                                p.setKey_red(p.getKey_red() + 1);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        break;

                    case 12:
                        //12层钥匙商人的情况
                        //初始化四个部分
                        description = new JLabel("哦，欢迎你的到来，如果你手里缺少金币，我可以帮你：");
                        choice1 = new JButton("卖出一把黄钥匙(7)");
                        choice2 = new JButton("卖出一把蓝钥匙(35)");
                        choice3 = new JButton("卖出一把红钥匙(70)");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getKey_yellow() >= 1)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setKey_yellow(p.getKey_yellow() - 1);
                                p.setGD(p.getGD() + 7);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getKey_blue() >= 1)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setKey_blue(p.getKey_blue() - 1);
                                p.setGD(p.getGD() + 35);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener3 = e -> {
                            if(p.getKey_red() >= 1)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setKey_red(p.getKey_red() - 1);
                                p.setGD(p.getGD() + 70);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        break;
                    case 15:
                        //15层救金币商人的情况
                        //初始化四个部分
                        description = new JLabel("只要你有足够的经验，我就可以给你一把神盾，提升120防御：");
                        choice1 = new JButton("购买神盾(500)");
                        choice2 = new JButton("什么？ 这么贵，不买！");
                        choice3 = new JButton("再考虑考虑");

                        //设置三个按钮的监视器
                        listener1 = e -> {
                            if(p.getGD() >= 500)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                p.setATK(p.getATK() + 120);
                                s.update(p.getLV(), p.getHP(), p.getATK(),p.getDEF(), p.getGD(), p.getEXP(), level, p.getKey_yellow(), p.getKey_blue(), p.getKey_red());

                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                            }
                        };
                        listener2 = e -> {
                            if(p.getGD() >= 500)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("啥b，有钱还不买？？？？？？");
                                description.repaint();
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("没钱你来这干嘛？？");
                                description.repaint();
                            }
                        };
                        listener3 = e -> {
                            if(p.getGD() >= 500)
                            {
                                shopMusic.setPath("motaMusic\\Confirm.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("啥b，有钱还不买？？？？？？");
                                description.repaint();
                            }
                            else
                            {
                                shopMusic.setPath("motaMusic\\No.wav");
                                shopThread = new Thread(shopMusic);
                                shopThread.start();
                                description.setText("没钱你来这干嘛？？");
                                description.repaint();
                            }
                        };
                        break;
                }
            default:
                break;

        }

        //调整文字对齐
        description.setHorizontalAlignment(JLabel.CENTER);

        //调整文本和按钮属性
        description.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        choice1.setFont(new Font("黑体", Font.PLAIN, 15));
        choice2.setFont(new Font("黑体", Font.PLAIN, 15));
        choice3.setFont(new Font("黑体", Font.PLAIN, 15));
        description.setForeground(Color.BLACK);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);

        //装载监视器
        choice1.addActionListener(listener1);
        choice2.addActionListener(listener2);
        choice3.addActionListener(listener3);

        //组装
        shopPanel.add(description);
        shopPanel.add(choice1);
        shopPanel.add(choice2);
        shopPanel.add(choice3);

        //设置选项框的属性
        shopPanel.setBounds(0, 0, 400, 250);
        shopPanel.setLayout(new GridLayout(4, 1));

        //进行最后的组装并设置frame的属性，初始化成功
        this.add(shopPanel);
        this.setSize(400, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }



}
