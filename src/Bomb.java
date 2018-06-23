import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * Bomb炸弹类
 *
 * @author zhaiaxin
 * 2018/6/18 17:08
 */

public class Bomb {

    //地雷的横坐标
    private int x;
    //地雷的纵坐标
    private int y;
    //情况数，有0-13种情况
    private int what;
    private int hide = 0;
    //窗口的宽度
    private int w = 19;
    //窗口的高度
    private int h = 19;
    private Mine mc;
    private Toolkit tk = Toolkit.getDefaultToolkit();

    private Image bomb = tk.getImage(Bomb.class.getResource("/Image/bomb.jpg"));
    private Image bomb0 = tk.getImage(Bomb.class.getResource("/Image/bomb0.jpg"));
    private Image zeroBomb = tk.getImage(Bomb.class.getResource("/Image/0.jpg"));
    private Image oneBomb = tk.getImage(Bomb.class.getResource("/Image/1.jpg"));
    private Image twoBomb = tk.getImage(Bomb.class.getResource("/Image/2.jpg"));
    private Image threeBomb = tk.getImage(Bomb.class.getResource("/Image/3.jpg"));
    private Image fourBomb = tk.getImage(Bomb.class.getResource("/Image/4.jpg"));
    private Image fiveBomb = tk.getImage(Bomb.class.getResource("/Image/5.jpg"));
    private Image sixBomb = tk.getImage(Bomb.class.getResource("/Image/6.jpg"));
    private Image severnBomb = tk.getImage(Bomb.class.getResource("/Image/7.jpg"));
    private Image eightBomb = tk.getImage(Bomb.class.getResource("/Image/8.jpg"));
    private Image flag = tk.getImage(Bomb.class.getResource("/Image/flag.jpg"));
    private Image flag2 = tk.getImage(Bomb.class.getResource("/Image/flag2.jpg"));
    private Image bg = tk.getImage(Bomb.class.getResource("/Image/s.jpg"));

    public Bomb() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Bomb(int x, int y, int what, Mine mc) {
        super();
        this.x = x;
        this.y = y;
        this.what = what;
        this.mc = mc;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public void draw(Graphics g) {
        switch (what) {
            case 0:
                g.drawImage(zeroBomb, x, y, w, h, mc);
                break;
            case 1:
                g.drawImage(oneBomb, x, y, w, h, mc);
                break;
            case 2:
                g.drawImage(twoBomb, x, y, w, h, mc);
                break;
            case 3:
                g.drawImage(threeBomb, x, y, w, h, mc);
                break;
            case 4:
                g.drawImage(fourBomb, x, y, w, h, mc);
                break;
            case 5:
                g.drawImage(fiveBomb, x, y, w, h, mc);
                break;
            case 6:
                g.drawImage(sixBomb, x, y, w, h, mc);
                break;
            case 7:
                g.drawImage(severnBomb, x, y, w, h, mc);
                break;
            case 8:
                g.drawImage(eightBomb, x, y, w, h, mc);
                break;
            case 9:
                g.drawImage(bomb, x, y, w, h, mc);
                break;
            case 10:
                g.drawImage(bomb0, x, y, w, h, mc);
                break;
            case 11:
                g.drawImage(flag, x, y, w, h, mc);
                break;
            case 12:
                g.drawImage(flag2, x, y, w, h, mc);
                break;
            case 13:
                g.drawImage(bg, x, y, w, h, mc);
                break;
        }
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, w, h);
    }


}
