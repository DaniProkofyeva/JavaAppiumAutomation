import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest extends CoreTestCase
{
    @Before
    public void textStartTest()
    {
        System.out.println("Start test");
    }

    @After
    public void testFinishTest()
    {
        System.out.println("Finish test");
    }

    @Test
    public void firstTest()
    {
        System.out.println("First test");
    }

    @Test
    public void secondTest()
    {
        System.out.println("Second test");
    }

}
