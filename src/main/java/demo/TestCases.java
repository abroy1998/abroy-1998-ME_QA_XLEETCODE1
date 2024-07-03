package demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }


    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/");
        String url = driver.getCurrentUrl();
        boolean containsText = url.contains("leetcode");
        System.out.println("Test Case Status :- " + (containsText ? "Pass" : "Fail"));
        System.out.println("end Test case: testCase01");

    }


    public void testCase02() throws Exception {
        System.out.println("Start Test case: testCase02");
        driver.get("https://leetcode.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement viewQuestionsLink = driver.findElement(By.xpath("//a/p[text()='View Questions ']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", viewQuestionsLink);
        //js.executeScript("window.scrollBy(0,700)", "");//2nd way of scrolling
        viewQuestionsLink.click();
        String url = driver.getCurrentUrl();
        boolean containsText = url.contains("problemset");

        System.out.println("Test Case2 - Contains problemset : Status :- " + (containsText ? "Pass" : "Fail"));
        System.out.println("end Test case: testCase02");
        List<WebElement> questionRow = driver.findElements(By.xpath("//div[@role='row']"));
        js.executeScript("arguments[0].scrollIntoView();", questionRow.get(0));
        Map<String, String> urlLinks = new HashMap<>();

        int till = 7;

        for (int i = 2; i < till; i++) {
            WebElement row = questionRow.get(i);
            try {
                WebElement div = row.findElement(By.xpath(".//div[@class='truncate']"));
                WebElement clickableATag = div.findElement(By.tagName("a"));
                //Integer.parseInt(clickableATag.getText().split("\\.")[0])
                urlLinks.put(clickableATag.getText(), clickableATag.getAttribute("href"));
            } catch (Exception ignored) {
                till++;
            }

        }

        for (String key : urlLinks.keySet()) {

            driver.get(urlLinks.get(key));
            Thread.sleep(5000);

            String title = driver.getTitle().toLowerCase().trim();

            String expectedTitle = key.split("\\.")[1].toLowerCase().trim();
            boolean containsTitle = title.contains(expectedTitle);

//            System.out.println(title + " - " + expectedTitle);

            System.out.println("Test Case2 link contains " + key + ":-" + "Status " + (containsTitle ? "Pass" : "Fail"));

            driver.navigate().back();


        }

//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


//        Thread.sleep(5000000);

    }

    public void testCase03() throws Exception {
        System.out.println("Start Test case: testCase03");
        driver.get("https://leetcode.com/");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement viewQuestionsLink = driver.findElement(By.xpath("//a/p[text()='View Questions ']"));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)", "");//2nd way of scrolling

        viewQuestionsLink.click();
        Thread.sleep(3000);

        WebElement twoSumProblem = driver.findElement(By.xpath("//a[@href='/problems/two-sum']"));
        twoSumProblem.click();
        Thread.sleep(3000);


        String title = driver.getCurrentUrl().toLowerCase();
        String expectedTitle = "two-sum";
        boolean getTitle = title.contains(expectedTitle);

        System.out.println("Test Case3 Status :- " + (getTitle ? "Pass" : "Fail"));
        System.out.println("end Test case: testCase03");

    }


    public void testCase04() throws Exception {
        System.out.println("Start Test case: testCase04");
        driver.get("https://leetcode.com/");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement viewQuestionsLink = driver.findElement(By.xpath("//a/p[text()='View Questions ']"));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)", "");//2nd way of scrolling
//        js.executeScript("arguments[0].scrollIntoView();", viewQuestionsLink);

        viewQuestionsLink.click();
        Thread.sleep(3000);

//        js.executeScript("window.scrollBy(0,700)", "");//2nd way of scrolling

        WebElement twoSumProblem = driver.findElement(By.xpath("//a[@href='/problems/two-sum']"));
        js.executeScript("arguments[0].scrollIntoView();", twoSumProblem);
        twoSumProblem.click();
        Thread.sleep(3000);

        WebElement submissionTab = driver.findElement(By.xpath("//div[@data-layout-path='/ts0/tb3']"));
        submissionTab.click();
        Thread.sleep(3000);

        WebElement registerOrSignIn = driver.findElement(By.xpath("//a[text()='Register or Sign In']"));
        boolean value = registerOrSignIn.getText().contains("Register or Sign In");
        Thread.sleep(3000);


        System.out.println("Test Case4 Status :- " + (value ? "Pass" : "Fail"));
        System.out.println("end Test case: testCase04");

    }


}
