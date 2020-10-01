import org.junit.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestMain {

    private static StringChecker checker;

    @BeforeClass
    public static void setUp(){
        String file = "pushkin.txt";
        checker = new StringChecker(file);
    }
    @Before
    public void reset(){
        checker.clearWords();
    }

    @Test
    public void beautifyStringWith32(){
        String start = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
        String b = checker.beautify(start);
        assertThat(b, is("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"));
    }
    @Test
    public void wordsWithDelimiters(){
        checker.setFile("short1.txt");
        checker.read();
        String[] act = checker.arrWords().toArray(String[]::new);
        assertThat(act, not(hasItemInArray("abc,")));
    }
    @Test
    public void correctWord(){
        String string = "AbcdEfg";
        boolean actual = checker.correctString(string);
        Assert.assertTrue(actual);
    }
    @Test
    public void incorrectWord(){
        String string = "Abbcd";
        boolean actual = checker.correctString(string);
        Assert.assertNotEquals(true, actual);
    }
    @Test
    public void addDuplicateWord(){
        String string = "abc";
        checker.addWord(string);
        boolean actual = checker.correctString(string);
        Assert.assertFalse(actual);
    }
    @Test(timeout = 4000)
    public void readFile(){
        checker.read();
    }
    @Test
    public void readAndCompareResult(){
        checker.setFile("short.txt");
        checker.read();
        String[] exp = {"abc", "bca"};
        String[] act = checker.arrWords().toArray(String[]::new);
        Assert.assertArrayEquals(exp, act);
    }
    @Test
    public void incorrectFile(){
        checker.setFile("");
        checker.read();
        String[] arr = checker.arrWords().toArray(String[]::new);
        assertThat(arr, emptyArray());
    }
}
