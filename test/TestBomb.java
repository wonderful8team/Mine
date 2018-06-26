import org.junit.Test;

/**
 * Bomb单元测试类
 *
 * @author zhaiaxin
 * @time 2018/6/24 20:54
 */
public class TestBomb {

    /**
     * 测试 getWhat() 和 setWhat() 方法
     */
    @Test
    public void testBombSetAndGetWhat(){

        Bomb bomb = new Bomb();
        bomb.setWhat(1);
        System.out.println(bomb.getWhat());
    }


    
    
}
