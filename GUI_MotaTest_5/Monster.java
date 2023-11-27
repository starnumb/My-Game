package GUI_MotaTest_5;

public class Monster {
    //名字
    private String name;
    //血量
    private int HP;
    //攻击力
    private int ATK;
    //防御力
    private int DEF;
    //金钱
    private int GD;
    //经验
    private int EXP;

    //定义一个构造器
    private Monster(String name, int HP, int ATK, int DEF, int GD, int EXP) {
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.GD = GD;
        this.EXP = EXP;
    }

    //定义一个简单怪物工厂，根据输入的id返回对应的怪物对象
    public static Monster getMonsterByID(int id)
    {
        switch (id)
        {
            case 1:
                return new Monster("绿头怪", 50, 20, 1, 1, 1);
            case 2:
                return new Monster("红头怪", 70, 15, 2, 2, 2);
            case 3:
                return new Monster("小蝙蝠", 100, 20, 5, 3, 3);
            case 4:
                return new Monster("骷髅人", 110, 25, 5, 5, 4);
            case 5:
                return new Monster("青头怪", 200, 35, 10, 5, 5);
            case 6:
                return new Monster("骷髅士兵", 150, 40, 20, 8, 6);
            case 7:
                return new Monster("初级法师", 125, 50, 25, 10, 7);
            case 8:
                return new Monster("大蝙蝠", 150, 65, 30, 10, 8);
            case 9:
                return new Monster("兽面人", 300, 75, 45, 13, 10);
            case 10:
                return new Monster("骷髅队长", 400, 90, 50, 15, 12);
            case 11:
                return new Monster("石头怪人", 500, 115, 65, 15, 15);
            case 12:
                return new Monster("麻衣法师", 250, 120, 70, 20, 17);
            case 13:
                return new Monster("初级卫兵", 450, 150, 90, 22, 19);
            case 14:
                return new Monster("红蝙蝠", 550, 160, 90, 25, 20);
            case 15:
                return new Monster("高级法师", 100, 200, 110, 30, 25);
            case 16:
                return new Monster("怪王", 700, 250, 125, 32, 30);
            case 17:
                return new Monster("白衣武士", 1300, 300, 150, 40, 35);
            case 18:
                return new Monster("金卫士", 850, 350, 200, 45, 40);
            case 19:
                return new Monster("红衣法师", 500, 400, 260, 47, 45);
            case 20:
                return new Monster("兽面武士", 900, 450, 330, 50, 50);
            case 21:
                return new Monster("冥卫兵", 1250, 500, 400, 55, 55);
            case 22:
                return new Monster("高级卫兵", 1500, 560, 460, 60, 60);
            case 23:
                return new Monster("双手剑士", 1200, 620, 520, 65, 75);
            case 24:
                return new Monster("冥战士", 2000, 680, 590, 70, 65);
            case 25:
                return new Monster("金队长", 900, 750, 650, 77, 70);
            case 26:
                return new Monster("灵法师", 1500, 830, 730, 80, 70);
            case 27:
                return new Monster("冥队长", 2500, 900, 850, 84, 75);
            case 28:
                return new Monster("灵武士", 1200, 980, 900, 88, 75);
            case 29:
                return new Monster("影子战士", 3100, 1150, 1050, 92, 80);
            case 30:
                return new Monster("红衣魔王", 15000, 1000, 1000, 100, 100);
            case 31:
                Monster 红衣魔王 = new Monster("红衣魔王", 20000, 1333, 1333, 133, 133);
                return new Monster("红衣魔王", 20000, 1333, 1333, 133, 133);
            case 32:
                return new Monster("冥灵魔王", 33333, 2000, 1000, 375, 330);
            case 33:
                return new Monster("冥队长", 3333, 1200, 1133, 112, 100);
            case 34:
                return new Monster("灵武士", 1600, 1306, 1200, 117, 100);
            case 35:
                return new Monster("灵武士", 2400, 2612, 2400, 146, 125);
            case 36:
                return new Monster("冥灵魔王", 45000, 2550, 2250, 312, 275);
            case 37:
                return new Monster("灵法师", 2000, 1106, 973, 106, 93);
            case 38:
                return new Monster("灵法师", 3000, 2212, 1946, 132, 116);
            case 39:
                return new Monster("冥灵魔王", 60000, 3400, 3000, 390, 343);
            case 40:
                return new Monster("血影", 99999, 5000, 4000, 0, 0);
            case 41:
                return new Monster("魔龙", 99999, 9999, 5000, 0, 0);
            default:
                return new Monster("周斌琦", 1, 1, 1, 1, 1);
        }
    }

    //设置一些Getter和Setter
    //Getter
    public String getName() {
        return name;
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

    //Setter

    public void setName(String name) {
        this.name = name;
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
}
