import org.junit.Test;

public class MainTest extends CoreTestCase
{
     MathHelper Math = new MathHelper();

    @Test
    public void myFirstTest()
    {
         int a = Math.multyply(5);
         System.out.println(a);

         int b = Math.multyply(10, 15);
         System.out.println(b);
    }
}
