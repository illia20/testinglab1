import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class TestMainParameters {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Abc", "abc"}, {" abc", "abc"}, {" ABcd ", "abcd"}
        });
    }
    private String par;
    private String exp;
    public TestMainParameters(String par, String exp){
        this.par = par;
        this.exp = exp;
    }
    private static StringChecker checker;
    @BeforeClass
    public static void setUp(){
        String file = "C:\\Users\\knopa\\Desktop\\pushkin.txt";
        checker = new StringChecker(file);
    }
    @Test
    public void beautifyPar(){
        assertThat(checker.beautify(par), is(exp));
    }
}
