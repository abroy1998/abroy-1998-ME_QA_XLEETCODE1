package demo;
import java.net.MalformedURLException;

public class App {
    public void getGreeting() throws InterruptedException, MalformedURLException {
        
        // This is to remove unnecessary warnings from your console
        System.setProperty("java.util.logging.config.file", "logging.properties");
        
        TestCases tests = new TestCases(); // Initialize your test class

        //TODO: call your test case functions one after other here

        try {
            tests.testCase01();
            tests.testCase02();
            tests.testCase03();
            tests.testCase04();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            tests.endTest(); // End your test by clearning connections and closing browser
        }


        //END Tests


    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        new App().getGreeting();
    }
}
