package GUI_MotaTest_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JLayeredPane {

    //定义界面内需要的组件
    private Player player;
    private StatBar statBar;
    private JLabel[][] labels;
    private JLabel background;
    private ImageIcon floorIcon;
    private ImageIcon bgIcon;
    private KeyListener move;

    //定义需要的变量
    private int[][][] floorLocation;
    private int level;
    private int count = 0;
    private int playerGetNextY ,playerGetNextX;

    //定义背景音乐播放器
    MusicPlayer bgMusic;
    Thread bgthread;
    MusicPlayer se;
    Thread seThread;

    //定义一个构造器
    GamePanel()
    {
        //初始化变量
        level = 21;
        floorLocation = new int[25][13][13];
        initLevel();
        //floorLocation = changeLevel(getLevel());

        //初始化组件
        player = new Player();
        statBar = new StatBar(player.getLV(), player.getHP(), player.getATK(),player.getDEF(), player.getGD(), player.getEXP(), getLevel(), player.getKey_yellow(), player.getKey_blue(), player.getKey_red());
        labels = new JLabel[13][13];
        background = new JLabel();
        floorIcon = null;
        bgIcon = null;

        //初始化地图
        for(int i = 0; i < 13; i++)
        {
            for(int j = 0; j < 13; j++)
            {
                labels[i][j] = new JLabel();
                labels[i][j].setBounds(j * 32, i * 32, 32, 32);
                this.add(labels[i][j], Integer.valueOf(1));
            }
        }

        //设置背景的图片
        bgIcon = new ImageIcon("motaIMG\\背景1.png");
        background.setIcon(bgIcon);
        background.setBounds(32, 32, bgIcon.getIconWidth(), bgIcon.getIconHeight());
        //最后绘制出对应的地图
        paintLevel(getLevel());


        //播放初始层背景音乐
        bgMusic = new MusicPlayer("motaMusic\\0.wav");
        bgthread = new Thread(bgMusic);
        bgthread.start();

        //对人物移动的逻辑进行设置
        move = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            //对于移动，只考虑键盘按下的情况
            @Override
            public void keyPressed(KeyEvent e) {

                //获取键盘按下的键
                int keyCode = e.getKeyCode();

                //设置一些音效
                se = new MusicPlayer("motaMusic\\Walk.wav");
                seThread = new Thread(se);

                MusicPlayer music4 = new MusicPlayer("motaMusic\\No.wav");
                Thread thread4 = new Thread(music4);


                switch(keyCode)
                {

                    //按下上键的情况，其中用对人物可能踩到的所有物块进行判断
                    case KeyEvent.VK_UP:
                        //设置人物的图像为向上图像
                        player.setIcon(player.playerIcon_up);
                        //对所有能遇到的情况进行判断

                        playerGetNextY = player.getY() / 32 - 1;
                        playerGetNextX = player.getX() / 32;

                        switch (floorLocation[getLevel()][playerGetNextY][playerGetNextX])
                        {
                            case -4:
                                //公主
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[18][11][11]) = -2;
                                labels[11][11].setIcon(new ImageIcon("motaIMG\\上楼.png"));
                                break;
                            case -3:
                                //幸运金币
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setGD(player.getGD() + 500);
                                break;
                            case -2:
                                //上楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()+1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX(),player.getY() - 32);
                                break;
                            case -1:
                                //下楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()-1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX(),player.getY() - 32);
                                break;
                            case 1:
                                //正常的路
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                player.setLocation(player.getX(),player.getY() - 32);
                                break;
                            case 11:
                                //黄门
                                if(player.getKey_yellow() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_yellow(player.getKey_yellow() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 12:
                                //蓝门
                                if(player.getKey_blue() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_blue(player.getKey_blue() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 13:
                                //红门
                                if(player.getKey_red() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_red(player.getKey_red() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 14:
                                //铁门
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 19:
                                //商店(神龛)
                                Shops shop = new Shops(getLevel(), 0, player, statBar);
                                break;
                            case 21:
                                //黄钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                break;
                            case 22:
                                //蓝钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_blue(player.getKey_blue() + 1);
                                break;
                            case 23:
                                //红钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 24:
                                //红宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 3);
                                break;
                            case 25:
                                //蓝宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 3);
                                break;
                            case 26:
                                //红药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 200);
                                break;
                            case 27:
                                //蓝药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 500);
                                break;
                            case 28:
                                //黄药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 1000);
                                break;
                            case 29:
                                //大黄门钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                player.setKey_blue(player.getKey_blue() + 1);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 30:
                                //铁剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 10);
                                break;
                            case 31:
                                //铁盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 10);
                                break;
                            case 32:
                                //银剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 70);
                                break;
                            case 33:
                                //银盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 30);
                                break;
                            case 34:
                                //骑士剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 75);
                                break;
                            case 35:
                                //骑士盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 85);
                                break;
                            case 36:
                                //圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 120);
                                break;
                            case 37:
                                //圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 120);
                                break;
                            case 38:
                                //神圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 150);
                                break;
                            case 39:
                                //神圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 190);
                                break;
                            case 40:
                                //小飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 1);
                                player.setATK(player.getATK() + 5);
                                player.setDEF(player.getDEF() + 5);
                                break;
                            case 41:
                                //大飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 3);
                                player.setATK(player.getATK() + 15);
                                player.setDEF(player.getDEF() + 15);
                                break;
                            case 42:
                                //圣水
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() * 2);
                                break;
                            case 43:
                                //十字架
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK((int)(player.getATK() * 1.3));
                                player.setDEF((int)(player.getDEF() * 1.3));
                                break;
                            case 48:
                                //小偷
                                (floorLocation[2][7][2]) = 14;
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 49:
                                //商人1
                                Shops shop2 = new Shops(getLevel(), 1, player, statBar);
                                break;
                            case 50:
                                //商人2
                                Shops shop3 = new Shops(getLevel(), 2, player, statBar);
                                break;
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                            case 60:
                            case 61:
                            case 62:
                            case 63:
                            case 64:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                            case 76:
                            case 77:
                            case 78:
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 85:
                            case 87:
                            case 88:
                            case 89:
                            case 90:
                            case 91:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    fight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 86:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    bgMusic.interrupt();
                                    JFrame end = (JFrame) SwingUtilities.getWindowAncestor(player);
                                    end.dispose();
                                    EndFrame endFrame = new EndFrame();
                                }

                                break;
                            default:
                                break;
                        }
                        break;
                    //按下下键的情况
                    case KeyEvent.VK_DOWN:
                        //设置人物的图像为向下图像
                        player.setIcon(player.playerIcon_down);

                        playerGetNextY = player.getY() / 32 + 1;
                        playerGetNextX = player.getX() / 32;

                        switch (floorLocation[getLevel()][playerGetNextY][playerGetNextX])
                        {
                            case -4:
                                //公主
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[18][12][12]) = -2;
                                labels[12][12].setIcon(new ImageIcon("motaIMG\\上楼.png"));
                                break;
                            case -3:
                                //幸运金币
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setGD(player.getGD() + 500);
                                break;
                            case -2:
                                //上楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()+1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX(),player.getY() + 32);
                                break;
                            case -1:
                                //下楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()-1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX(),player.getY() + 32);
                                break;
                            case 1:
                                //正常的路
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                player.setLocation(player.getX(),player.getY() + 32);
                                break;
                            case 11:
                                //黄门
                                if(player.getKey_yellow() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_yellow(player.getKey_yellow() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 12:
                                //蓝门
                                if(player.getKey_blue() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_blue(player.getKey_blue() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 13:
                                //红门
                                if(player.getKey_red() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_red(player.getKey_red() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 14:
                                //铁门
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 19:
                                //商店(神龛)
                                Shops shop = new Shops(getLevel(), 0, player, statBar);
                                break;
                            case 21:
                                //黄钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                break;
                            case 22:
                                //蓝钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_blue(player.getKey_blue() + 1);
                                break;
                            case 23:
                                //红钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 24:
                                //红宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 3);
                                break;
                            case 25:
                                //蓝宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 3);
                                break;
                            case 26:
                                //红药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 200);
                                break;
                            case 27:
                                //蓝药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 500);
                                break;
                            case 28:
                                //黄药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 1000);
                                break;
                            case 29:
                                //大黄门钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                player.setKey_blue(player.getKey_blue() + 1);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 30:
                                //铁剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 10);
                                break;
                            case 31:
                                //铁盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 10);
                                break;
                            case 32:
                                //银剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 70);
                                break;
                            case 33:
                                //银盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 30);
                                break;
                            case 34:
                                //骑士剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 75);
                                break;
                            case 35:
                                //骑士盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 85);
                                break;
                            case 36:
                                //圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 120);
                                break;
                            case 37:
                                //圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 120);
                                break;
                            case 38:
                                //神圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 150);
                                break;
                            case 39:
                                //神圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 190);
                                break;
                            case 40:
                                //小飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 1);
                                player.setATK(player.getATK() + 5);
                                player.setDEF(player.getDEF() + 5);
                                break;
                            case 41:
                                //大飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 3);
                                player.setATK(player.getATK() + 15);
                                player.setDEF(player.getDEF() + 15);
                                break;
                            case 42:
                                //圣水
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() * 2);
                                break;
                            case 43:
                                //十字架
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK((int)(player.getATK() * 1.3));
                                player.setDEF((int)(player.getDEF() * 1.3));
                                break;
                            case 48:
                                //小偷
                                (floorLocation[2][7][2]) = 14;
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 49:
                                //商人1
                                Shops shop2 = new Shops(getLevel(), 1, player, statBar);
                                break;
                            case 50:
                                //商人2
                                Shops shop3 = new Shops(getLevel(), 2, player, statBar);
                                break;
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                            case 60:
                            case 61:
                            case 62:
                            case 63:
                            case 64:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                            case 76:
                            case 77:
                            case 78:
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 85:
                            case 87:
                            case 88:
                            case 89:
                            case 90:
                            case 91:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    fight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 86:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    bgMusic.interrupt();
                                    JFrame end = (JFrame) SwingUtilities.getWindowAncestor(player);
                                    end.dispose();
                                    EndFrame endFrame = new EndFrame();
                                }

                                break;
                            default:
                                break;
                        }
                        break;

                    //按下左键的情况
                    case KeyEvent.VK_LEFT:
                        //设置人物的图像为向左图像
                        player.setIcon(player.playerIcon_left);


                        playerGetNextY = player.getY() / 32;
                        playerGetNextX = player.getX() / 32 - 1;

                        switch (floorLocation[getLevel()][playerGetNextY][playerGetNextX])
                        {
                            case -4:
                                //公主
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[18][12][12]) = -2;
                                labels[12][12].setIcon(new ImageIcon("motaIMG\\上楼.png"));
                                break;
                            case -3:
                                //幸运金币
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setGD(player.getGD() + 500);
                                break;
                            case -2:
                                //上楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()+1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX() - 32,player.getY());
                                break;
                            case -1:
                                //下楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()-1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX() - 32,player.getY());
                                break;
                            case 1:
                                //正常的路
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                player.setLocation(player.getX() - 32,player.getY());
                                break;
                            case 11:
                                //黄门
                                if(player.getKey_yellow() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_yellow(player.getKey_yellow() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 12:
                                //蓝门
                                if(player.getKey_blue() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_blue(player.getKey_blue() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 13:
                                //红门
                                if(player.getKey_red() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_red(player.getKey_red() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 14:
                                //铁门
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 19:
                                //商店(神龛)
                                Shops shop = new Shops(getLevel(), 0, player, statBar);
                                break;
                            case 21:
                                //黄钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                break;
                            case 22:
                                //蓝钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_blue(player.getKey_blue() + 1);
                                break;
                            case 23:
                                //红钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 24:
                                //红宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 3);
                                break;
                            case 25:
                                //蓝宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 3);
                                break;
                            case 26:
                                //红药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 200);
                                break;
                            case 27:
                                //蓝药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 500);
                                break;
                            case 28:
                                //黄药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 1000);
                                break;
                            case 29:
                                //大黄门钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                player.setKey_blue(player.getKey_blue() + 1);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 30:
                                //铁剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 10);
                                break;
                            case 31:
                                //铁盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 10);
                                break;
                            case 32:
                                //银剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 70);
                                break;
                            case 33:
                                //银盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 30);
                                break;
                            case 34:
                                //骑士剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 75);
                                break;
                            case 35:
                                //骑士盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 85);
                                break;
                            case 36:
                                //圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 120);
                                break;
                            case 37:
                                //圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 120);
                                break;
                            case 38:
                                //神圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 150);
                                break;
                            case 39:
                                //神圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 190);
                                break;
                            case 40:
                                //小飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 1);
                                player.setATK(player.getATK() + 5);
                                player.setDEF(player.getDEF() + 5);
                                break;
                            case 41:
                                //大飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 3);
                                player.setATK(player.getATK() + 15);
                                player.setDEF(player.getDEF() + 15);
                                break;
                            case 42:
                                //圣水
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() * 2);
                                break;
                            case 43:
                                //十字架
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK((int)(player.getATK() * 1.3));
                                player.setDEF((int)(player.getDEF() * 1.3));
                                break;
                            case 48:
                                //小偷
                                (floorLocation[2][7][2]) = 14;
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 49:
                                //商人1
                                Shops shop2 = new Shops(getLevel(), 1, player, statBar);
                                break;
                            case 50:
                                //商人2
                                Shops shop3 = new Shops(getLevel(), 2, player, statBar);
                                break;
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                            case 60:
                            case 61:
                            case 62:
                            case 63:
                            case 64:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                            case 76:
                            case 77:
                            case 78:
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 85:
                            case 87:
                            case 88:
                            case 89:
                            case 90:
                            case 91:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    fight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 86:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    bgMusic.interrupt();
                                    JFrame end = (JFrame) SwingUtilities.getWindowAncestor(player);
                                    end.dispose();
                                    EndFrame endFrame = new EndFrame();
                                }

                                break;
                            default:
                                break;
                        }
                        break;
                    //按下右键的情况
                    case KeyEvent.VK_RIGHT:
                        //设置人物的图像为向右图像
                        player.setIcon(player.playerIcon_right);

                        playerGetNextY = player.getY() / 32;
                        playerGetNextX = player.getX() / 32 + 1;

                        switch (floorLocation[getLevel()][playerGetNextY][playerGetNextX])
                        {
                            case -4:
                                //公主
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[18][12][12]) = -2;
                                labels[12][12].setIcon(new ImageIcon("motaIMG\\上楼.png"));
                                break;
                            case -3:
                                //幸运金币
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setGD(player.getGD() + 500);
                                break;
                            case -2:
                                //上楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()+1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX() + 32,player.getY());
                                break;
                            case -1:
                                //下楼
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                setLevel(getLevel()-1);
                                changeMusicByLevel(getLevel());
                                paintLevel(getLevel());
                                player.setLocation(player.getX() + 32,player.getY());
                                break;
                            case 1:
                                //正常的路
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                player.setLocation(player.getX() + 32,player.getY());
                                break;
                            case 11:
                                //黄门
                                if(player.getKey_yellow() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_yellow(player.getKey_yellow() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 12:
                                //蓝门
                                if(player.getKey_blue() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_blue(player.getKey_blue() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 13:
                                //红门
                                if(player.getKey_red() > 0)
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    player.setKey_red(player.getKey_red() - 1);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 14:
                                //铁门
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 19:
                                //商店(神龛)
                                Shops shop = new Shops(getLevel(), 0, player, statBar);
                                break;
                            case 21:
                                //黄钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                break;
                            case 22:
                                //蓝钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_blue(player.getKey_blue() + 1);
                                break;
                            case 23:
                                //红钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 24:
                                //红宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 3);
                                break;
                            case 25:
                                //蓝宝石
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 3);
                                break;
                            case 26:
                                //红药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 200);
                                break;
                            case 27:
                                //蓝药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 500);
                                break;
                            case 28:
                                //黄药
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() + 1000);
                                break;
                            case 29:
                                //大黄门钥匙
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setKey_yellow(player.getKey_yellow() + 1);
                                player.setKey_blue(player.getKey_blue() + 1);
                                player.setKey_red(player.getKey_red() + 1);
                                break;
                            case 30:
                                //铁剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 10);
                                break;
                            case 31:
                                //铁盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 10);
                                break;
                            case 32:
                                //银剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 70);
                                break;
                            case 33:
                                //银盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 30);
                                break;
                            case 34:
                                //骑士剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 75);
                                break;
                            case 35:
                                //骑士盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 85);
                                break;
                            case 36:
                                //圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 120);
                                break;
                            case 37:
                                //圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 120);
                                break;
                            case 38:
                                //神圣剑
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK(player.getATK() + 150);
                                break;
                            case 39:
                                //神圣盾
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setDEF(player.getDEF() + 190);
                                break;
                            case 40:
                                //小飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 1);
                                player.setATK(player.getATK() + 5);
                                player.setDEF(player.getDEF() + 5);
                                break;
                            case 41:
                                //大飞羽
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setLV(player.getLV() + 3);
                                player.setATK(player.getATK() + 15);
                                player.setDEF(player.getDEF() + 15);
                                break;
                            case 42:
                                //圣水
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setHP(player.getHP() * 2);
                                break;
                            case 43:
                                //十字架
                                playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                player.setATK((int)(player.getATK() * 1.3));
                                player.setDEF((int)(player.getDEF() * 1.3));
                                break;
                            case 48:
                                //小偷
                                (floorLocation[2][7][2]) = 14;
                                (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                labels[playerGetNextY][playerGetNextX].setIcon(null);
                                break;
                            case 49:
                                //商人1
                                Shops shop2 = new Shops(getLevel(), 1, player, statBar);
                                break;
                            case 50:
                                //商人2
                                Shops shop3 = new Shops(getLevel(), 2, player, statBar);
                                break;
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 59:
                            case 60:
                            case 61:
                            case 62:
                            case 63:
                            case 64:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                            case 76:
                            case 77:
                            case 78:
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 85:
                            case 87:
                            case 88:
                            case 89:
                            case 90:
                            case 91:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    playSoundEffectByCondition(floorLocation[getLevel()][playerGetNextY][playerGetNextX]);
                                    fight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player);
                                    (floorLocation[getLevel()][playerGetNextY][playerGetNextX]) = 1;
                                    labels[playerGetNextY][playerGetNextX].setIcon(null);
                                }
                                else
                                    thread4.start();
                                break;
                            case 86:
                                if(canFight(floorLocation[getLevel()][playerGetNextY][playerGetNextX] - 50, player))
                                {
                                    JFrame end = (JFrame) SwingUtilities.getWindowAncestor(player);
                                    end.dispose();
                                    EndFrame endFrame = new EndFrame();
                                }

                                break;
                            default:
                                break;
                        }
                        break;
                }

                //在人物每走一步后对侧边栏进行更新，并重绘人物
                statBar.update(player.getLV(), player.getHP(), player.getATK(),player.getDEF(), player.getGD(), player.getEXP(), getLevel(), player.getKey_yellow(), player.getKey_blue(), player.getKey_red());
                player.repaint();
            }

            //简单监视一下键盘的情况
            @Override
            public void keyReleased(KeyEvent e) {

//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_UP:
//                        System.out.println("松开了↑键");
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        System.out.println("松开了↓键");
//                        break;
//                    case KeyEvent.VK_LEFT:
//                        System.out.println("松开了←键");
//                        break;
//                    case KeyEvent.VK_RIGHT:
//                        System.out.println("松开了→键");
//                        break;
//                }

            }
        };



        //组装剩下的部件
        player.addKeyListener(move);
        this.add(background, Integer.valueOf(0));
        this.add(player, Integer.valueOf(2));
        this.add(statBar, Integer.valueOf(2));

        //最后进行Panel设置，完成初始化
        this.setLayout(null);
        this.setBounds(0, 0, 416 + 200, 416);
        this.setBackground(Color.BLACK);


    }














    //根据现在的层数画出当前层的地图
    public void paintLevel(int level)
    {
        //使用循环+switch更改所有图块的icon
        for(int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                switch (floorLocation[level][i][j]) {
                    case -4:
                        floorIcon = new ImageIcon("motaIMG\\公主.png");
                        break;
                    case -3:
                        floorIcon = new ImageIcon("motaIMG\\幸运金币.png");
                        break;
                    case -2:
                        floorIcon = new ImageIcon("motaIMG\\上楼.png");
                        break;
                    case -1:
                        floorIcon = new ImageIcon("motaIMG\\下楼.png");
                        break;
                    case 0:
                        floorIcon = new ImageIcon("motaIMG\\妖精.png");
                        break;
                    case 1:
                        floorIcon = new ImageIcon("motaIMG\\地板1.png");
                        break;
                    case 2:
                        floorIcon = new ImageIcon("motaIMG\\墙1.png");
                        break;
                    case 3:
                        floorIcon = new ImageIcon("motaIMG\\外墙1.png");
                        break;
                    case 4:
                        floorIcon = new ImageIcon("motaIMG\\外墙2.png");
                        break;
                    case 5:
                        floorIcon = new ImageIcon("motaIMG\\外墙3.png");
                        break;
                    case 6:
                        floorIcon = new ImageIcon("motaIMG\\外墙4.png");
                        break;
                    case 7:
                        floorIcon = new ImageIcon("motaIMG\\外墙5.png");
                        break;
                    case 8:
                        floorIcon = new ImageIcon("motaIMG\\外墙6.png");
                        break;
                    case 9:
                        floorIcon = new ImageIcon("motaIMG\\外墙7.png");
                        break;
                    case 10:
                        floorIcon = new ImageIcon("motaIMG\\外墙8.png");
                        break;
                    case 11:
                        floorIcon = new ImageIcon("motaIMG\\黄门.png");
                        break;
                    case 12:
                        floorIcon = new ImageIcon("motaIMG\\蓝门.png");
                        break;
                    case 13:
                        floorIcon = new ImageIcon("motaIMG\\红门.png");
                        break;
                    case 14:
                        floorIcon = new ImageIcon("motaIMG\\铁门.png");
                        break;
                    case 15:
                        floorIcon = new ImageIcon("motaIMG\\机关门.png");
                        break;
                    case 16:
                        floorIcon = new ImageIcon("motaIMG\\熔岩墙.png");
                        break;
                    case 17:
                        floorIcon = new ImageIcon("motaIMG\\虚空墙.png");
                        break;
                    case 18:
                        floorIcon = new ImageIcon("motaIMG\\商店左.png");
                        break;
                    case 19:
                        floorIcon = new ImageIcon("motaIMG\\商店中.png");
                        break;
                    case 20:
                        floorIcon = new ImageIcon("motaIMG\\商店右.png");
                        break;
                    case 21:
                        floorIcon = new ImageIcon("motaIMG\\黄钥匙.png");
                        break;
                    case 22:
                        floorIcon = new ImageIcon("motaIMG\\蓝钥匙.png");
                        break;
                    case 23:
                        floorIcon = new ImageIcon("motaIMG\\红钥匙.png");
                        break;
                    case 24:
                        floorIcon = new ImageIcon("motaIMG\\红宝石1.png");
                        break;
                    case 25:
                        floorIcon = new ImageIcon("motaIMG\\蓝宝石1.png");
                        break;
                    case 26:
                        floorIcon = new ImageIcon("motaIMG\\红药.png");
                        break;
                    case 27:
                        floorIcon = new ImageIcon("motaIMG\\蓝药.png");
                        break;
                    case 28:
                        floorIcon = new ImageIcon("motaIMG\\黄药.png");
                        break;
                    case 29:
                        floorIcon = new ImageIcon("motaIMG\\大黄门钥匙.png");
                        break;
                    case 30:
                        floorIcon = new ImageIcon("motaIMG\\铁剑.png");
                        break;
                    case 31:
                        floorIcon = new ImageIcon("motaIMG\\铁盾.png");
                        break;
                    case 32:
                        floorIcon = new ImageIcon("motaIMG\\银剑.png");
                        break;
                    case 33:
                        floorIcon = new ImageIcon("motaIMG\\银盾.png");
                        break;
                    case 34:
                        floorIcon = new ImageIcon("motaIMG\\骑士剑.png");
                        break;
                    case 35:
                        floorIcon = new ImageIcon("motaIMG\\骑士盾.png");
                        break;
                    case 36:
                        floorIcon = new ImageIcon("motaIMG\\圣剑.png");
                        break;
                    case 37:
                        floorIcon = new ImageIcon("motaIMG\\圣盾.png");
                        break;
                    case 38:
                        floorIcon = new ImageIcon("motaIMG\\神圣剑.png");
                        break;
                    case 39:
                        floorIcon = new ImageIcon("motaIMG\\神圣盾.png");
                        break;
                    case 40:
                        floorIcon = new ImageIcon("motaIMG\\小升级飞羽.png");
                        break;
                    case 41:
                        floorIcon = new ImageIcon("motaIMG\\大升级飞羽.png");
                        break;
                    case 42:
                        floorIcon = new ImageIcon("motaIMG\\圣水.png");
                        break;
                    case 43:
                        floorIcon = new ImageIcon("motaIMG\\十字架.png");
                        break;
                    case 44:
                        floorIcon = new ImageIcon("motaIMG\\楼层传送器.png");
                        break;
                    case 45:
                        floorIcon = new ImageIcon("motaIMG\\怪物图鉴.png");
                        break;
                    case 46:
                        floorIcon = new ImageIcon("motaIMG\\魔杖1.png");
                        break;
                    case 47:
                        floorIcon = new ImageIcon("motaIMG\\魔杖2.png");
                        break;
                    case 48:
                        floorIcon = new ImageIcon("motaIMG\\小偷.png");
                        break;
                    case 49:
                        floorIcon = new ImageIcon("motaIMG\\商人1.png");
                        break;
                    case 50:
                        floorIcon = new ImageIcon("motaIMG\\商人2.png");
                        break;
                    case 51:
                        floorIcon = new ImageIcon("motaIMG\\绿头怪.png");
                        break;
                    case 52:
                        floorIcon = new ImageIcon("motaIMG\\红头怪.png");
                        break;
                    case 53:
                        floorIcon = new ImageIcon("motaIMG\\小蝙蝠.png");
                        break;
                    case 54:
                        floorIcon = new ImageIcon("motaIMG\\骷髅人.png");
                        break;
                    case 55:
                        floorIcon = new ImageIcon("motaIMG\\青头怪.png");
                        break;
                    case 56:
                        floorIcon = new ImageIcon("motaIMG\\骷髅士兵.png");
                        break;
                    case 57:
                        floorIcon = new ImageIcon("motaIMG\\初级法师.png");
                        break;
                    case 58:
                        floorIcon = new ImageIcon("motaIMG\\大蝙蝠.png");
                        break;
                    case 59:
                        floorIcon = new ImageIcon("motaIMG\\兽面人.png");
                        break;
                    case 60:
                        floorIcon = new ImageIcon("motaIMG\\骷髅队长.png");
                        break;
                    case 61:
                        floorIcon = new ImageIcon("motaIMG\\石头怪人.png");
                        break;
                    case 62:
                        floorIcon = new ImageIcon("motaIMG\\麻衣法师.png");
                        break;
                    case 63:
                        floorIcon = new ImageIcon("motaIMG\\初级卫兵.png");
                        break;
                    case 64:
                        floorIcon = new ImageIcon("motaIMG\\红蝙蝠.png");
                        break;
                    case 65:
                        floorIcon = new ImageIcon("motaIMG\\高级法师.png");
                        break;
                    case 66:
                        floorIcon = new ImageIcon("motaIMG\\怪王.png");
                        break;
                    case 67:
                        floorIcon = new ImageIcon("motaIMG\\白衣武士.png");
                        break;
                    case 68:
                        floorIcon = new ImageIcon("motaIMG\\金卫士.png");
                        break;
                    case 69:
                        floorIcon = new ImageIcon("motaIMG\\红衣法师.png");
                        break;
                    case 70:
                        floorIcon = new ImageIcon("motaIMG\\兽面武士.png");
                        break;
                    case 71:
                        floorIcon = new ImageIcon("motaIMG\\冥卫兵.png");
                        break;
                    case 72:
                        floorIcon = new ImageIcon("motaIMG\\高级卫兵.png");
                        break;
                    case 73:
                        floorIcon = new ImageIcon("motaIMG\\双手剑士.png");
                        break;
                    case 74:
                        floorIcon = new ImageIcon("motaIMG\\冥战士.png");
                        break;
                    case 75:
                        floorIcon = new ImageIcon("motaIMG\\金队长.png");
                        break;
                    case 76:
                        floorIcon = new ImageIcon("motaIMG\\灵法师.png");
                        break;
                    case 77:
                        floorIcon = new ImageIcon("motaIMG\\冥队长.png");
                        break;
                    case 78:
                        floorIcon = new ImageIcon("motaIMG\\灵武士.png");
                        break;
                    case 79:
                        floorIcon = new ImageIcon("motaIMG\\影子战士.png");
                        break;
                    case 80:
                        floorIcon = new ImageIcon("motaIMG\\红衣魔王.png");
                        break;
                    case 81:
                        floorIcon = new ImageIcon("motaIMG\\红衣魔王.png");
                        break;
                    case 82:
                        floorIcon = new ImageIcon("motaIMG\\冥灵魔王.png");
                        break;
                    case 83:
                        floorIcon = new ImageIcon("motaIMG\\冥队长.png");
                        break;
                    case 84:
                        floorIcon = new ImageIcon("motaIMG\\灵武士.png");
                        break;
                    case 85:
                        floorIcon = new ImageIcon("motaIMG\\灵武士.png");
                        break;
                    case 86:
                        floorIcon = new ImageIcon("motaIMG\\冥灵魔王.png");
                        break;
                    case 87:
                        floorIcon = new ImageIcon("motaIMG\\灵法师.png");
                        break;
                    case 88:
                        floorIcon = new ImageIcon("motaIMG\\灵法师.png");
                        break;
                    case 89:
                        floorIcon = new ImageIcon("motaIMG\\冥灵魔王.png");
                        break;
                    case 90:
                        floorIcon = new ImageIcon("motaIMG\\血影.png");
                        break;
                    case 91:
                        floorIcon = new ImageIcon("motaIMG\\魔龙.png");
                        break;
                }
                labels[i][j].setIcon(floorIcon);
            }
        }
        //更改完后对地图进行重绘
        this.repaint();
    }

    //这是对地图的初始化，用一个三维数组记录所有层中的物块信息
    public void initLevel()
    {
            //第零层的地图
            floorLocation[0] = new int[][]
                    {
                            {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                            {3, 2,17,17,17, 2, 1, 2,17,17,17, 2, 7},
                            {3, 2,17,17,17, 2, 1, 2,17,17,17, 2, 7},
                            {3, 2,17,17,17, 2, 1, 2,17,17,17, 2, 7},
                            {3, 2,17,17,17, 2, 1, 2,17,17,17, 2, 7},
                            {3, 2,17,17,17, 2, 1, 2,17,17,17, 2, 7},
                            {3, 2,17,17,17, 2, 1, 2,17,17,17, 2, 7},
                            {3, 2, 2,17,17, 2, 1, 2,17,17, 2, 2, 7},
                            {3, 2, 2, 2, 2, 2,11, 2, 2, 2, 2, 2, 7},
                            {3,16, 2,16, 2, 0, 1, 1, 2,16, 2,16, 7},
                            {3,16,16,16,16,16, 1,16,16,16,16,16, 7},
                            {3,16,16,16,16,16,-2,16,16,16,16,16, 7},
                            {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                    };
            //第一层的地图
            floorLocation[1] = new int[][]
                    {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,-2, 1,21,51,52,51, 1, 1, 1, 1, 1, 7},
                        {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 7},
                        {3,26, 1,54,11, 1, 2,26,21,26, 2, 1, 7},
                        {3,21,54,24, 2, 1, 2,26,21,26, 2, 1, 7},
                        {3, 2,11, 2, 2, 1, 2, 2, 2,55, 2, 1, 7},
                        {3,21,56, 1, 2, 1,11,57,51,53, 2, 1, 7},
                        {3,25, 1,22, 2, 1, 2, 2, 2, 2, 2, 1, 7},
                        {3, 2,11, 2, 2, 1, 1, 1, 1, 1, 1, 1, 7},
                        {3, 1,56, 1, 2, 2,13, 2, 2, 2,11, 2, 7},
                        {3,26,28,21, 2,23, 1, 1, 2,21,59,22, 7},
                        {3,26,45,21, 2, 1,-1, 1, 2,21,21,21, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                    };

            //第二层的地图
            floorLocation[2] = new int[][]
                    {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,-1, 2, 1,75, 1, 2,24,25,21,22, 2, 7},
                        {3, 1, 2,25, 2,28, 2,24,25,21,22, 2, 7},
                        {3, 1, 2,21, 2,21, 2,24,25,21,68, 2, 7},
                        {3, 1, 2,21, 2,21, 2, 2, 2, 2,11, 2, 7},
                        {3, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 7},
                        {3, 1, 2,11, 2, 2,11, 2, 2,11, 2, 2, 7},
                        {3, 1,15, 1, 1, 1, 1, 2, 1,68, 1, 2, 7},
                        {3, 1, 2,11, 2, 2,12, 2,14, 2,14, 2, 7},
                        {3, 1, 2,21, 2,28,26, 2, 1, 2, 1, 2, 7},
                        {3, 1, 2,21, 2,28,26, 2, 1, 2, 1, 2, 7},
                        {3,-2, 2,24, 2,28,26, 2,32, 2,33, 2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                    };
        //第三层的地图
        floorLocation[3] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,30,52,21, 2,18,19,20, 2, 2, 2, 2, 7},
                        {3,52,21, 1, 2, 1, 1, 1, 2, 1,53, 1, 7},
                        {3,21,54, 1, 2, 2,11, 2, 2, 1, 2, 1, 7},
                        {3, 2,11, 2, 2, 1,54, 1, 2,21, 2,52, 7},
                        {3, 1, 1, 1, 2, 2, 2, 1, 2,21, 2,53, 7},
                        {3,51, 2, 1,53,52,53, 1, 2,21, 2,52, 7},
                        {3,51, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 7},
                        {3, 1, 1, 1, 1, 1, 2, 2, 2,11, 2, 1, 7},
                        {3, 2, 2, 2, 2,53, 2,52, 1,52, 2, 1, 7},
                        {3, 2, 1, 1, 1, 1, 2,25,53,21, 2, 1, 7},
                        {3,-1, 1, 2, 2, 2, 2,24,28,21, 2,-2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第四层的地图
        floorLocation[4] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3, 1,55, 1, 2, 1,48, 1, 2, 1,55, 1, 7},
                        {3,11, 2,11, 2, 1, 1, 1, 2,11, 2,11, 7},
                        {3, 1, 2, 1, 2, 2,14, 2, 2, 1, 2, 1, 7},
                        {3, 1, 2,54, 2,58,64,58, 2,54, 2, 1, 7},
                        {3,53, 2,26, 2,25,58,25, 2,26, 2,53, 7},
                        {3,53, 2,26, 2, 2,13, 2, 2,26, 2,53, 7},
                        {3,52, 2, 1, 2,59,63,59, 2, 1, 2,52, 7},
                        {3, 1, 2, 1, 2,24,59,24, 2, 1, 2, 1, 7},
                        {3, 1, 2, 1, 2, 2,12, 2, 2, 1, 2, 1, 7},
                        {3, 1, 2, 1, 2,21, 1,21, 2, 1, 2, 1, 7},
                        {3,-2, 2, 1,55, 1, 1, 1,55, 1, 2,-1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第五层的地图
        floorLocation[5] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,29, 2,26, 2,28,57, 1, 1,57,21,22, 7},
                        {3, 1, 2,24, 2,57, 1, 1, 1, 1,57,21, 7},
                        {3,58, 2,57, 2,56, 1, 2, 2,11, 2, 2, 7},
                        {3, 1,11, 1, 2,31,56, 2, 1,59,56,50, 7},
                        {3,58, 2, 1, 2, 2, 2, 2, 1, 1, 1,56, 7},
                        {3,24, 2, 1, 1, 1,53,54, 1, 1, 1, 1, 7},
                        {3,25, 2, 2,55, 2, 2, 2, 2, 1, 1, 1, 7},
                        {3, 1,49, 2,55, 2, 1, 1, 1,59,63, 1, 7},
                        {3, 2, 2, 2,53, 2,11, 2,12, 2,11, 2, 7},
                        {3, 1, 1, 2, 1, 2,53, 2,25,11, 1, 2, 7},
                        {3,-1, 1,53, 1, 1, 1, 2,21, 2,-2, 2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第六层的地图
        floorLocation[6] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,40,60, 2,25, 2,21,66,-3, 2,28,28, 7},
                        {3,60,21, 2,24, 2, 1,21,66, 2,61,28, 7},
                        {3,21,64,12, 1,12,64, 1,21, 2, 1,61, 7},
                        {3, 1, 1, 2,63, 2, 1, 1, 1, 2,69, 1, 7},
                        {3, 2, 2, 2,14, 2, 2, 2, 2, 2,11, 2, 7},
                        {3, 1, 1,65, 1,21,21,21, 1,65, 1, 1, 7},
                        {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7},
                        {3, 1, 2,58,11,58, 1, 1, 1, 1, 1, 2, 7},
                        {3, 1, 2,11, 2,11, 2, 2, 2, 2,12, 2, 7},
                        {3, 1, 2,58, 2, 1, 1, 2, 2, 1, 1, 2, 7},
                        {3, 1, 1, 1, 2,-2, 1,11,11, 1,-1, 2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第七层的地图
        floorLocation[7] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,-2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 7},
                        {3, 2, 2, 1,64, 2,12, 2,60, 1, 2, 2, 7},
                        {3, 2, 1,64,25, 2,67, 2,23,60, 1, 2, 7},
                        {3, 1, 1, 2, 2, 2,14, 2, 2, 2, 1, 1, 7},
                        {3, 1, 2,12,67,14,43,14,67,12, 1, 1, 7},
                        {3, 1, 2, 2, 2, 2,14, 2, 2, 2, 2, 1, 7},
                        {3, 1, 2,26,24, 2,67, 2,25,26, 2, 1, 7},
                        {3, 1, 2,21,26, 2,12, 2,26,21, 2, 1, 7},
                        {3, 1, 2, 2,22,22,28,22,22, 2, 2, 1, 7},
                        {3, 1, 1, 2, 2, 2,13, 2, 2, 2, 1, 1, 7},
                        {3, 2, 1, 1,11,-1, 1, 1,11, 1, 1, 2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第八层的地图
        floorLocation[8] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,-1, 2, 1, 1, 1, 1, 2, 1,21,60, 1, 7},
                        {3, 1, 2, 1, 2, 2,11, 2,11, 2, 2, 1, 7},
                        {3, 1, 2, 1, 2, 1, 1,12, 1, 1, 2,24, 7},
                        {3, 1, 2, 1, 2,62, 2, 2, 2,58, 2,55, 7},
                        {3,58, 2, 1, 2,26, 2,-2, 1, 1, 2,55, 7},
                        {3,64, 2,25, 2,26, 2, 2, 2, 2, 2, 1, 7},
                        {3,58, 2,55, 2, 1, 1, 1, 2, 1,64, 1, 7},
                        {3, 1, 2,55, 2, 2, 2,63, 2,11, 2, 2, 7},
                        {3, 1, 2, 1,60, 1, 2,60, 2, 1, 1, 1, 7},
                        {3, 1, 2, 2, 2,11, 2, 1, 2, 2, 2, 1, 7},
                        {3, 1, 1,62, 1, 1, 2, 1,66,67,66, 1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第九层的地图
        floorLocation[9] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,44,21, 1, 2, 2, 2, 1, 1, 1, 2, 1, 7},
                        {3,21, 1,70,11, 1, 1, 1, 2, 1,11,60, 7},
                        {3, 2,11, 2, 2, 1, 2, 2, 2, 1, 2,21, 7},
                        {3, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2,21, 7},
                        {3, 1, 1, 1,13, 1, 2,-1, 2, 1, 2,26, 7},
                        {3, 2,12, 2, 2, 1, 2, 2, 2, 1, 2, 2, 7},
                        {3,25,69,24, 2,62, 2,-2, 2, 1, 2,26, 7},
                        {3, 2,11, 2, 2, 1, 1, 1,11, 1, 2,21, 7},
                        {3,60,26,60, 2, 2,12, 2, 2, 1, 2,21, 7},
                        {3,22,60,26, 2,61,62,61, 2, 1,11,60, 7},
                        {3,34,22,60,11,27,61,27, 2, 1, 2, 1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十层的地图
        floorLocation[10] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3, 1, 2, 2,25,70, 2,70,24, 2, 2, 1, 7},
                        {3, 1, 1, 2, 2,11, 2,11, 2, 2, 1,69, 7},
                        {3, 1, 1, 1, 1, 1, 2, 1, 1, 1,69,27, 7},
                        {3, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 7},
                        {3,58, 2, 1, 1,21,21,21, 1, 1, 2,21, 7},
                        {3,64, 2, 1, 2, 2, 2, 2,11, 2, 2,21, 7},
                        {3,58, 2, 1,14, 1, 1,-1, 1,11,64, 1, 7},
                        {3, 1, 2, 2, 2, 2, 2, 2,11, 2, 2, 1, 7},
                        {3, 1, 2,26,25,24, 2, 1,64, 1, 2,21, 7},
                        {3, 1, 2,26,25,24,13,62, 2,62, 2,21, 7},
                        {3,-2, 2,26,25,24, 2,22, 2,22, 2,26, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十一层的地图
        floorLocation[11] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,26, 2,21, 2,22, 2,23, 2,27,35,27, 7},
                        {3,26, 2,21, 2,22, 2,23, 2,71,72,71, 7},
                        {3,26, 2,21, 2,22, 2,23, 2, 1,71,27, 7},
                        {3,11, 2,11, 2,11, 2,11, 2, 2,12, 2, 7},
                        {3, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 7},
                        {3,11, 2, 2,12, 2, 2, 2,12, 2, 2,11, 7},
                        {3,25, 2, 1,71,27,73,27,71, 1, 2,24, 7},
                        {3,25, 2,70, 2, 2, 2, 2, 2,70, 2,24, 7},
                        {3,25, 2,70, 2,18,19,20, 2,70, 2,24, 7},
                        {3, 2, 2,13, 2,26, 1,26, 2,13, 2, 2, 7},
                        {3,-1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十二层的地图
        floorLocation[12] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,50,25, 2, 1,68,75,68, 1, 2,27,41, 7},
                        {3,24, 1, 2, 1, 2,11, 2, 1, 2, 1,27, 7},
                        {3, 1, 1, 2, 1, 2,75, 2, 1, 2, 1, 1, 7},
                        {3, 1,73, 2, 1, 2,21, 2, 1, 2,78, 1, 7},
                        {3,73,74, 2, 1, 2,21, 2, 1, 2,76,78, 7},
                        {3, 2,12, 2, 1, 2,26, 2, 1, 2,12, 2, 7},
                        {3, 1, 1, 1, 1, 2,26, 2, 1, 1, 1, 1, 7},
                        {3, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 7},
                        {3,25,73,11,71,71,72,71,71,11,73,24, 7},
                        {3, 2, 2, 2, 2, 2,12, 2, 2, 2, 2, 2, 7},
                        {3,-2, 1, 1, 1, 1, 1, 1, 1, 1, 1,-1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十三层的地图
        floorLocation[13] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3, 1,73, 1, 1, 1, 1, 1, 2, 1,74, 1, 7},
                        {3, 1, 2, 2, 2, 2, 2,11, 2, 1, 2, 1, 7},
                        {3, 1, 2, 1, 1,71, 1, 1, 2, 1, 2, 1, 7},
                        {3,27, 2,13, 2, 2, 2, 1, 2, 1, 2, 1, 7},
                        {3,68, 2, 1, 1,74, 2,71, 2,24, 2, 1, 7},
                        {3,75, 2, 1,77,14, 2,72, 2,24, 2, 1, 7},
                        {3,68, 2,74,14,49, 2,71, 2,24, 2,25, 7},
                        {3, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2,25, 7},
                        {3, 1,68, 1, 2, 1, 1, 1,74, 1, 2,25, 7},
                        {3, 2, 2, 1, 2,27, 2, 2, 2, 2, 2, 1, 7},
                        {3,-1, 1, 1,12, 1,-2, 2,41,77,11, 1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十四层的地图
        floorLocation[14] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3, 2, 1,78,29,-2, 1, 1, 1, 1, 1, 2, 7},
                        {3, 2, 1,27, 2, 2, 2, 2, 2,27, 1, 2, 7},
                        {3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 7},
                        {3, 2, 1, 2, 2, 2,42, 2, 2, 2, 1, 2, 7},
                        {3, 2, 1, 2, 2, 2,14, 2, 2, 2, 1, 2, 7},
                        {3, 2, 1,26, 2, 2,74, 2, 2,26, 1, 2, 7},
                        {3, 2, 1,17,17, 2,77, 2,17,17, 1, 2, 7},
                        {3, 2, 1,17,17, 2,74, 2,17,17, 1, 2, 7},
                        {3, 2, 1,17,17, 2,12, 2,17,17, 1, 2, 7},
                        {3, 2,71,72,71,12, 1,12,71,72,71, 2, 7},
                        {3, 2, 2, 2, 2, 2,-1, 2, 2, 2, 2, 2, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十五层的地图
        floorLocation[15] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3, 1, 1, 1, 1,-1,17,-2, 1, 1, 1, 1, 7},
                        {3, 1,17,17,17,17,17,17,17,17,17, 1, 7},
                        {3, 1,17,17, 2, 2, 2, 2, 2,17,17, 1, 7},
                        {3, 1,17, 2, 2,49, 2,50, 2, 2,17, 1, 7},
                        {3, 1,17, 2, 2,25, 2,25, 2, 2,17, 1, 7},
                        {3, 1,17, 2, 2,24, 2,24, 2, 2,17, 1, 7},
                        {3, 1,17,17, 2, 1, 2, 1, 2,17,17, 1, 7},
                        {3, 1,17,17, 2,11, 2,11, 2,17,17, 1, 7},
                        {3, 1,17,17,17, 1, 1, 1,17,17,17, 1, 7},
                        {3, 1,17,17,17,17,13,17,17,17,17, 1, 7},
                        {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十六层的地图
        floorLocation[16] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,17,17,17,17,17, 1,-1,17,17,17,17, 7},
                        {3,17,17,17,17,17, 1,17,17,17,17,17, 7},
                        {3,17,17,17,17,17, 1,17,17,17,17,17, 7},
                        {3,17,17,17,17, 2,13, 2,17,17,17,17, 7},
                        {3,17,17,17, 2,49, 1, 2, 2,17,17,17, 7},
                        {3,17,17,17, 2, 2,80, 2, 2,17,17,17, 7},
                        {3,17,17,17, 2, 2, 1, 2, 2,17,17,17, 7},
                        {3,17,17,17, 2, 2,-2, 2, 2,17,17,17, 7},
                        {3,17,17,17,17, 2, 2, 2,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十七层的地图
        floorLocation[17] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,17,84,83, 1, 1, 1, 1, 1, 1, 1,84, 7},
                        {3,17,83,17,17,17,17,17,17,17,17, 1, 7},
                        {3,17, 1,17,84, 1, 1, 1, 1, 1, 1,84, 7},
                        {3,17, 1,17, 1,17,17,17,17,17,17,17, 7},
                        {3,17, 1,17, 1,17,84, 1, 1, 1,84,17, 7},
                        {3,17, 1,17,84, 1, 1,17,17,17, 1,17, 7},
                        {3,17, 1,17,17,17,17,17,84, 1,84,17, 7},
                        {3,17,84,17,17,17,-1,17, 1,17,17,17, 7},
                        {3,17,84,83, 1,79, 1,17,84, 1, 1,84, 7},
                        {3,17,17,17,17,17,17,17,17,17,17, 1, 7},
                        {3,-2, 1,79, 1, 1, 1, 1, 1, 1, 1,84, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十八层的地图
        floorLocation[18] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17,17,17, 2, 2, 2,17,17,17,17, 7},
                        {3,17,17,17, 2, 2,-4, 2, 2,17,17,17, 7},
                        {3,17,17,17, 2, 2,14, 2, 2,17,17,17, 7},
                        {3,17,17,17, 2, 2,13, 2, 2,17,17,17, 7},
                        {3,17,17,17,17, 2,13, 2,17,17,17,17, 7},
                        {3,17,17,17,17,17, 1,17,17,17,17,17, 7},
                        {3,17,17,17,17,17, 1,17,17,17,17,17, 7},
                        {3,-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第十九层的地图
        floorLocation[19] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {3, 1,17, 1,17,17,17,17,17, 1,17, 1, 7},
                        {3, 1,17, 1,17,17,17,17,17, 1,17, 1, 7},
                        {3, 1,17, 1,17,17,-2,17,17, 1,17, 1, 7},
                        {3, 1,17, 1,17,17, 1,17,17, 1,17, 1, 7},
                        {3, 1,17,81,17,17, 1,17,17,81,17, 1, 7},
                        {3, 1,17,14,17,17,82,17,17,14,17, 1, 7},
                        {3, 1,17,38,17,17, 1,17,17,39,17, 1, 7},
                        {3, 1,17,17,17,17, 1,17,17,17,17, 1, 7},
                        {3, 1,17,17,17,17, 1,17,17,17,17, 1, 7},
                        {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-1, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第二十层的地图
        floorLocation[20] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,79,24,67,26,83,23,83,26,67,24,79, 7},
                        {3,27,17,21,17,22,17,22,17,21,17,27, 7},
                        {3,17,25,67, 1,84, 1,84, 1,67,25,17, 7},
                        {3,26,17,21,17, 1,-1, 1,17,21,17,26, 7},
                        {3,83,22,84, 1, 1, 1, 1, 1,84,22,83, 7},
                        {3,23,17, 1,17, 1,17, 1,17, 1,17,23, 7},
                        {3,83,22,84, 1, 1, 1, 1, 1,84,22,83, 7},
                        {3,26,17,21,17, 1,-2, 1,17,21,17,26, 7},
                        {3,17,25,67, 1,84, 1,84, 1,67,25,17, 7},
                        {3,27,17,21,17,22,17,22,17,21,17,27, 7},
                        {3,79,24,67,26,83,23,83,26,67,24,79, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
        //第二十一层的地图
        floorLocation[21] = new int[][]
                {
                        {4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17, 1, 1,17,86,17, 1, 1,17,17, 7},
                        {3,17, 1, 1,17,17,87,17,17, 1, 1,17, 7},
                        {3,17, 1, 1, 1,17,87,17, 1, 1, 1,17, 7},
                        {3,17,17, 1, 1, 1, 1, 1, 1, 1,17,17, 7},
                        {3,17,17, 1, 1, 1, 1, 1, 1, 1,17,17, 7},
                        {3,17,17,17, 1, 1,17, 1, 1,17,17,17, 7},
                        {3,17,17,17,17,14, 1,14,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {3,17,17,17,17,17,17,17,17,17,17,17, 7},
                        {10,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8}
                };
    }

    //战斗前先要判断能不能战斗
    public boolean canFight(int id, Player p)
    {
        Monster m = Monster.getMonsterByID(id);
        int pHP = p.getHP(), mHP = m.getHP();

        switch (id)
        {
            case 12:
                pHP -= 100;
                break;
            case 17:
                pHP -= pHP / 4;
                break;
            case 19:
                pHP -= 300;
                break;
            case 26:
            case 37:
            case 38:
                pHP -= pHP / 3;
                break;
        }

        if(m.getDEF() >= p.getATK())
            return false;
        else if (p.getDEF() >= m.getATK())
            return true;
        else
        {
            while(true)
            {
                mHP -= (p.getATK() - m.getDEF());
                if(mHP <= 0)
                    return true;
                pHP -= (m.getATK() - p.getDEF());
                if(pHP <= 0)
                    return false;
            }
        }
    }

    //这是战斗的过程
    public void fight(int id, Player p)
    {
        Monster m = Monster.getMonsterByID(id);

        int pHP = p.getHP(), mHP = m.getHP();

        switch (id)
        {
            case 12:
                pHP -= 100;
                break;
            case 17:
                pHP -= pHP / 4;
                break;
            case 19:
                pHP -= 300;
                break;
            case 26:
            case 37:
            case 38:
                pHP -= pHP / 3;
                break;
        }


        while(true)
        {
            mHP -= (p.getATK() - m.getDEF());
//            System.out.println("玩家的回合!");
//            System.out.println(m.getName() + " 受到了 " + (p.getATK() - m.getDEF()) + " 点伤害 !\n");
//            System.out.println(m.getName() + " 还剩 " + mHP + "点血\n");

            if(mHP <= 0)
            {
                p.setHP(pHP);
                System.out.println("战胜了" + m.getName());
                p.setGD(p.getGD() + m.getGD());
                p.setEXP(p.getEXP() + m.getEXP());
                return;
            }

            pHP -= Math.max(0, (m.getATK() - p.getDEF()));
//            System.out.println(m.getName() + " 的回合!");
//            System.out.println("玩家 受到了 " + (m.getATK() - p.getDEF()) + " 点伤害 !\n");
//            System.out.println("玩家 还剩 " + pHP + "点血\n");

        }
    }
    //设置一些Getter和Setter
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    //根据物品情况播放音效
    public void playSoundEffectByCondition(int condition)
    {
        switch (condition)
        {
            case -2:
            case -1:
                se.setPath("motaMusic\\ChangeFloor.wav");
                break;
            case 1:
                se.setPath("motaMusic\\Walk.wav");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
                se.setPath("motaMusic\\Door.wav");
                break;
            case -4:
            case -3:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
                se.setPath("motaMusic\\Get.wav");
                break;
            case 42:
            case 43:
            case 48:
                se.setPath("motaMusic\\LevelUp.wav");
                break;
            case 51:
            case 52:
            case 54:
            case 55:
            case 59:
            case 61:
            case 63:
            case 66:
            case 71:
            case 72:
            case 74:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 89:
            case 90:
            case 91:
                se.setPath("motaMusic\\Fist.wav");
                break;
            case 53:
            case 58:
            case 64:
                se.setPath("motaMusic\\Blood.wav");
                break;
            case 56:
            case 60:
            case 68:
            case 70:
            case 73:
            case 75:
                se.setPath("motaMusic\\Sword.wav");
                break;
            case 57:
            case 65:
                se.setPath("motaMusic\\Magic.wav");
                break;
            case 62:
                se.setPath("motaMusic\\麻衣.wav");
                break;
            case 67:
                se.setPath("motaMusic\\白衣.wav");
                break;
            case 69:
                se.setPath("motaMusic\\红衣.wav");
                break;
            case 76:
            case 87:
            case 88:
                se.setPath("motaMusic\\灵法.wav");
                break;


        }
        seThread = new Thread(se);
        seThread.start();
    }



    //根据楼层播放/切换背景音乐
    public void changeMusicByLevel(int level)
    {
        if(level < 1)
        {
            if(!bgMusic.getPath().equals("motaMusic\\0.wav"))
            {
                bgMusic.interrupt();
                bgMusic.setPath("motaMusic\\0.wav");
                bgthread = new Thread(bgMusic);
                bgthread.start();
            }
        }
        else if(level < 8)
        {
            if(!bgMusic.getPath().equals("motaMusic\\1-7.wav"))
            {
                bgMusic.interrupt();
                bgMusic.setPath("motaMusic\\1-7.wav");;
                bgthread = new Thread(bgMusic);
                bgthread.start();
            }
        }
        else if(level < 15)
        {
            if(!bgMusic.getPath().equals("motaMusic\\8-14.wav"))
            {
                bgMusic.interrupt();
                bgMusic.setPath("motaMusic\\8-14.wav");
                bgthread = new Thread(bgMusic);
                bgthread.start();
            }
        }
        else if(level < 19)
        {
            if(!bgMusic.getPath().equals("motaMusic\\15-18.wav"))
            {
                bgMusic.interrupt();
                bgMusic.setPath("motaMusic\\15-18.wav");
                bgthread = new Thread(bgMusic);
                bgthread.start();
            }
        }
        else
        {
            if(!bgMusic.getPath().equals("motaMusic\\19-24.wav"))
            {
                bgMusic.interrupt();
                bgMusic.setPath("motaMusic\\19-24.wav");
                bgthread = new Thread(bgMusic);
                bgthread.start();
            }
        }

    }


}
