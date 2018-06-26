import org.junit.Test;

/**
 * Mine单元测试类
 *
 * @author zhaiaxin
 * @time 2018/6/25 8:22
 */
public class TestMine {

    /**
     * 测试 getGameState() 和 getGameState() 方法
     */
    @Test
    public void testSetAndSetGameState(){
        Mine mine = new Mine(225, 305, 10);
        mine.setGameState("测试状态");
        System.out.println(mine.getGameState());
    }
}
