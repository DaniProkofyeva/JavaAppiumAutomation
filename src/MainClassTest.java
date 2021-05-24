import org.junit.Test;
import org.junit.Assert;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {
        String phrase = this.getClassString();
        if (phrase.contains("Hello")) {
            return;
        }
        if (phrase.contains("hello")) {
            return;
        } else {
            Assert.fail("I have not Hello or hello");
        }

    }
}