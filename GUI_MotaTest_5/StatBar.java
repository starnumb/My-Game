package GUI_MotaTest_5;

import javax.swing.*;
import java.awt.*;

public class StatBar extends JPanel {

    //定义所有侧边栏需要的属性
    public int LV;
    public int HP;
    public int ATK;
    public int DEF;
    public int GD;
    public int EXP;
    public int LEVEL;
    public int key_yellow;
    public int key_blue;
    public int key_red;


    //为每个属性创建一个文本标签
    JLabel lLV;
    JLabel lHP;
    JLabel lATK;
    JLabel lDEF;
    JLabel lGD;
    JLabel lEXP;
    JLabel lLEVEL;
    JLabel lkey_yellow;
    JLabel lkey_blue;
    JLabel lkey_red;

    //定义一个图像工具类
    ImageIcon playerIcon;

    StatBar(int LV, int HP, int ATK , int DEF, int GD, int EXP, int LEVEL, int key_yellow, int key_blue, int key_red)
    {
        //初始化，为侧边栏赋值
        this.LV = LV;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.GD = GD;
        this.EXP = EXP;
        this.LEVEL = LEVEL;
        this.key_yellow = key_yellow;
        this.key_blue = key_blue;
        this.key_red = key_red;

        //设置侧边栏的内容
        lLV = new JLabel("   " + this.LV + "  级  ");
        lHP = new JLabel("   生命  " + this.HP + "");
        lATK = new JLabel("   攻击  " + this.ATK + "");
        lDEF = new JLabel("   防御  " + this.DEF + "");
        lGD = new JLabel("   金币  " + this.GD + "");
        lEXP = new JLabel("   经验  " + this.EXP + "");
        lLEVEL = new JLabel("   第  " + this.LEVEL + "  层");
        lkey_yellow = new JLabel("   黄钥匙  " + this.key_yellow + "  ");
        lkey_blue = new JLabel("   蓝钥匙  " + this.key_blue + "  ");
        lkey_red = new JLabel("   红钥匙  " + this.key_red + "  ");

        //为等级栏，红蓝黄钥匙栏设置图片
        playerIcon = new ImageIcon("IMAGE\\PLAYER_3.png");
        lLV.setIcon(playerIcon);
        lLV.setHorizontalTextPosition(JLabel.LEFT);
        lkey_yellow.setIcon(new ImageIcon("IMAGE\\KEY_1.png"));
        lkey_yellow.setHorizontalTextPosition(JLabel.LEFT);
        lkey_blue.setIcon(new ImageIcon("IMAGE\\KEY_2.png"));
        lkey_blue.setHorizontalTextPosition(JLabel.LEFT);
        lkey_red.setIcon(new ImageIcon("IMAGE\\KEY_3.png"));
        lkey_red.setHorizontalTextPosition(JLabel.LEFT);

        //设置字体
        lLV.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lHP.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lATK.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lDEF.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lGD.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lEXP.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lLEVEL.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lkey_yellow.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lkey_blue.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lkey_red.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        //设置背景色
        lLV.setForeground(Color.white);
        lHP.setForeground(Color.white);
        lATK.setForeground(Color.white);
        lDEF.setForeground(Color.white);
        lGD.setForeground(Color.white);
        lEXP.setForeground(Color.white);
        lLEVEL.setForeground(Color.orange);
        lkey_yellow.setForeground(Color.yellow);
        lkey_blue.setForeground(Color.blue);
        lkey_red.setForeground(Color.red);

        //设置文字居中
//        lLV.setHorizontalAlignment(JLabel.CENTER);
//        lHP.setHorizontalAlignment(JLabel.CENTER);
//        lATK.setHorizontalAlignment(JLabel.CENTER);
//        lDEF.setHorizontalAlignment(JLabel.CENTER);
//        lGD.setHorizontalAlignment(JLabel.CENTER);
//        lEXP.setHorizontalAlignment(JLabel.CENTER);
//        lLEVEL.setHorizontalAlignment(JLabel.CENTER);
//        lkey_yellow.setHorizontalAlignment(JLabel.CENTER);
//        lkey_blue.setHorizontalAlignment(JLabel.CENTER);
//        lkey_red.setHorizontalAlignment(JLabel.CENTER);

        //组装
        this.add(lLV);
        this.add(lHP);
        this.add(lATK);
        this.add(lDEF);
        this.add(lGD);
        this.add(lEXP);
        this.add(lLEVEL);
        this.add(lkey_yellow);
        this.add(lkey_blue);
        this.add(lkey_red);


        //最后对Panel进行设置，完成初始化
        this.setBounds(416, 0, 200, 416);
        this.setLayout(new GridLayout(10, 1, 0, 10));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);

    }

    //更新函数，用来更新侧边栏，内容与初始化类似
    public void update(int LV, int HP, int ATK , int DEF, int GD, int EXP, int LEVEL, int key_yellow, int key_blue, int key_red)
    {
        this.LV = LV;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.GD = GD;
        this.EXP = EXP;
        this.LEVEL = LEVEL;
        this.key_yellow = key_yellow;
        this.key_blue = key_blue;
        this.key_red = key_red;

//        lLV.setText("  " + this.LV + "  级");
//        lHP.setText("   生命  " + this.HP + "");
//        lATK.setText("   攻击  " + this.ATK + "");
//        lDEF.setText("   防御  " + this.DEF + "");
//        lGD.setText("   金币  " + this.GD + "");
//        lEXP.setText("   经验  " + this.EXP + "");
//        lLEVEL.setText("   第  " + this.LEVEL + "  层");
//        lkey_yellow.setText("   黄钥匙  " + this.key_yellow + "");
//        lkey_blue.setText("   蓝钥匙  " + this.key_blue + "");
//        lkey_red.setText("   红钥匙  " + this.key_red + "");
        lLV.setText("   " + this.LV + "  级  ");
        lHP.setText("   生命  " + this.HP + "");
        lATK.setText("   攻击  " + this.ATK + "");
        lDEF.setText("   防御  " + this.DEF + "");
        lGD.setText("   金币  " + this.GD + "");
        lEXP.setText("   经验  " + this.EXP + "");
        lLEVEL.setText("   第  " + this.LEVEL + "  层");
        lkey_yellow.setText("   黄钥匙  " + this.key_yellow + "  ");
        lkey_blue.setText("   蓝钥匙  " + this.key_blue + "  ");
        lkey_red.setText("   红钥匙  " + this.key_red + "  ");

    }

}
