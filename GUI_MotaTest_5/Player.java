package GUI_MotaTest_5;

import javax.swing.*;

public class Player extends JLabel{

    //定义角色需要的属性
    private int LV;
    private int HP;
    private int ATK;
    private int DEF;
    private int GD;
    private int EXP;
    private int key_yellow;
    private int key_blue;
    private int key_red;


    //定义角色的四向图
    ImageIcon playerIcon_up;
    ImageIcon playerIcon_down;
    ImageIcon playerIcon_left;
    ImageIcon playerIcon_right;

    Player()
    {
        //初始化角色的属性
        setLV(0);
        setHP(1000);
        setATK(100000);
        setDEF(100000);
        setGD(0);
        setEXP(0);
        setKey_yellow(1);
        setKey_blue(1);
        setKey_red(1);

        //初始化四向图的路径
        playerIcon_down = new ImageIcon("motaIMG\\PLAYER_down.png");
        playerIcon_up = new ImageIcon("motaIMG\\PLAYER_up.png");
        playerIcon_left = new ImageIcon("motaIMG\\PLAYER_left.png");
        playerIcon_right = new ImageIcon("motaIMG\\PLAYER_right.png");

        //初始化人物的图像(朝下)，设定锁定和大小
        this.setIcon(playerIcon_down);
        this.setBounds(192,32,playerIcon_up.getIconWidth(), playerIcon_up.getIconHeight());
        //this.setBounds(192,352,playerIcon_up.getIconWidth(), playerIcon_up.getIconHeight());
        this.setFocusable(true);
    }

    //Getter和Setter以备使用
    //Setter
    public void setLV(int LV) {
        this.LV = LV;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public void setGD(int GD) {
        this.GD = GD;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public void setKey_yellow(int key_yellow) {
        this.key_yellow = key_yellow;
    }

    public void setKey_blue(int key_blue) {
        this.key_blue = key_blue;
    }

    public void setKey_red(int key_red) {
        this.key_red = key_red;
    }

    //Getter
    public int getLV() {
        return LV;
    }

    public int getHP() {
        return HP;
    }

    public int getATK() {
        return ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public int getGD() {
        return GD;
    }

    public int getEXP() {
        return EXP;
    }

    public int getKey_yellow() {
        return key_yellow;
    }

    public int getKey_blue() {
        return key_blue;
    }

    public int getKey_red() {
        return key_red;
    }
}