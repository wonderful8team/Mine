import org.junit.Test;

/**
 * MouseListener单元测试类
 *
 * @author zhaiaxin
 * @time 2018/6/25 8:22
 */
public class TestMouseListener {

    /**
     * 测试 MouseListener(Mine mine) 构造方法
     */
    @Test
    public void testMouseListener(){
        Mine mine = new Mine(225, 305, 10);
        MouseListener mouseListener = new MouseListener(mine);
        System.out.println(mouseListener);
    }
}
