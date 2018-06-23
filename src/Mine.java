import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * MineClient扫雷客户端类
 *
 * @author zhaiaxin
 * 2018/6/18 19:38
 */

public class Mine extends JFrame {

    private static final long serialVersionUID = 1L;

    //屏幕宽度
    private int screenWidth;
    //屏幕高度
    private int screenHeight;
    //图片宽度
    private int imgWidth = 20;
    //图片高度
    private int imgHeight = 20;
    //地图的行数
    private int rowNum = 0;
    //地图的列数
    private int colNum = 0;
    //地雷的总数
    private int mineNum = 99;
    //计时器
    private int timer = 0;
    //游戏时间
    private int time = 0;
    //未扫雷的个数
    private int restMine;
    //不是雷的个数
    private int notMine;

    private MyPanel myPanel;
    //当前游戏状态
    private String gameState = "start";
    //第一次点击
    private boolean firstClick = true;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem lowLevel;
    private JMenuItem midLevel;
    private JMenuItem heightLevel;
    private JMenuItem restart;
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Image icon = tk.getImage("Image/icon.jpg");

    //地图集合
    private ArrayList<Bomb> bombList = new ArrayList<Bomb>();


    /**
     * mineClient构造器
     * @param screenWidth 屏幕宽度
     * @param screenHeight 屏幕高度
     * @param mineNum 地雷总数
     */
    public Mine(int screenWidth, int screenHeight, int mineNum) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.mineNum = mineNum;


        //初始化菜单栏
        initMenu();
        setTitle("扫雷");
        setIconImage(icon);
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        initList();
        myPanel = new MyPanel();
        myPanel.setBackground(new Color(192, 192, 192));
        add(myPanel);

        //鼠标事件
        myPanel.addMouseListener(new MouseListener(this));
        new updateThread().start();
    }

    /*
     * 初始化菜单栏
     */
    private void initMenu() {


        menu = new JMenu("开始");
        menuBar = new JMenuBar();
        lowLevel = new JMenuItem("初级");
        midLevel = new JMenuItem("中级");
        heightLevel = new JMenuItem("高级");
        restart = new JMenuItem("重开");

        lowLevel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                new Mine(225, 305, 10);
            }
        });

        midLevel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                new Mine(380, 460, 49);
            }
        });

        heightLevel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                new Mine(660, 460, 99);
            }
        });

        restart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
                new Mine(screenWidth, screenHeight, mineNum);
            }
        });

        menu.add(restart);
        menu.add(new JSeparator());
        menu.add(lowLevel);
        menu.add(midLevel);
        menu.add(heightLevel);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public boolean isFirstClick() {
        return firstClick;
    }

    public void setFirstClick(boolean firstClick) {
        this.firstClick = firstClick;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public ArrayList<Bomb> getBombList() {
        return bombList;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public int getMineNum() {
        return mineNum;
    }

    //创建地图
    private void initList() {
        for (int i = imgWidth; i < this.getWidth() - 2 * imgWidth; i += imgWidth) {
            for (int j = imgWidth; j < this.getHeight() - 6 * imgWidth; j += imgHeight) {
                rowNum = rowNum > i / imgWidth ? rowNum : i / imgWidth;
                colNum = colNum > j / imgWidth ? colNum : j / imgWidth;
                Bomb bomb = new Bomb(i, j, 13, this);
                bombList.add(bomb);
            }
        }
    }




    //自定义panel
    public class MyPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public void paint(Graphics g) {
            super.paintComponent(g);
            restMine = mineNum;
            notMine = 0;
            //画地雷 数字
            for (Bomb bomb : bombList) {
                bomb.draw(g);
                if (bomb.getWhat() == 11)
                    restMine--;
                if (bomb.getWhat() >= 0 && bomb.getWhat() <= 8)
                    notMine++;
            }
            //游戏失败
            if (gameState.equals("lose")) {
                for (Bomb bomb : bombList) {
                    if (bomb.getHide() == 9) {
                        bomb.setWhat(bomb.getHide());
                    }
                }
                Toolkit tk = Toolkit.getDefaultToolkit();
                Image img = tk.getImage(Mine.class.getResource("/Image/sad.jpg"));
                g.drawImage(img, this.getWidth() / 2 , 0, 20, 20, this);
            }

            //画当前游戏进行时间和未扫的地雷数目
            drawTimeAndMineNum(g);

            //取得游戏胜利
            if (!gameState.equals("lose") && notMine + mineNum == colNum * rowNum) {
                gameState = "win";
                Toolkit tk = Toolkit.getDefaultToolkit();
                Image img = tk.getImage(Mine.class.getResource("/Image/win.jpg"));
                g.drawImage(img, this.getWidth() / 2 , 0,20, 20,  this);
            }
        }

        private void drawTimeAndMineNum(Graphics g) {
            Font font = new Font("微软雅黑", Font.BOLD, 15);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("时间：" + time + " s", 0, this.getHeight() - 10);
            g.drawString("未扫雷数：" + restMine + " ", this.getWidth() - 100, this.getHeight() - 10);

        }
    }

    //屏幕每隔10ms刷新一次
    public class updateThread extends Thread {
        public void run() {

            while (true) {
                repaint();
                if (!firstClick) {
                    timer += 10;
                    if (timer == 1000) {
                        timer = 0;
                        time++;
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Mine(225, 305, 10);
    }

}
