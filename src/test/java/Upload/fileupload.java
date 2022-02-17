package Upload;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Test
public class fileupload {
    ExcelConfig excel = new ExcelConfig("/home/knoldus/Desktop/S_Demo/src/test/resources/data.xlsx");
    static WebDriver driver;

//for chrome
    public void CBrowser(){
        try{
            System.setProperty("webdriver.chrome.driver","/home/knoldus/Desktop/S_Demo/Driver/chromedriver");
        }
        catch(WebDriverException e){
            System.out.print(e.getMessage());
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demos.devexpress.com/aspxfilemanageranduploaddemos/uploadcontrol/UploadAndSubmit.aspx");
        driver.findElement(By.cssSelector("#ContentHolder_FormLayout_DescriptionTextBox_I")).sendKeys("uploading files");
        try {
            driver.findElement(By.cssSelector("#ContentHolder_FormLayout_DocumentsUploadControl_TextBox0_Input")).sendKeys(excel.getData(0,0,0));
        }
        catch(NoSuchElementException e){
            System.out.print(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try{
        driver.findElement(By.cssSelector("div[id='ContentHolder_FormLayout_SubmitButton_CD'] span[class='dx-vam']")).click();}
        catch(ElementNotVisibleException e)
        {
            System.out.println(e.getMessage());
        }
        WebElement message = driver.findElement(By.cssSelector("#ContentHolder_FormLayout_RoundPanel_DescriptionLabel"));
        String expectedErrorMessage= "uploading files";
        String actualErrorMessage = message.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual message is not matched with expected message. \nActual: "
                        + actualErrorMessage + "\nExpected: "
                        + expectedErrorMessage);
        System.out.print("Success");
        driver.close();
    }

//for firefox
    @Test
    public void fbrowser(){
        System.setProperty("webdriver.gecko.driver","/home/knoldus/Desktop/S_Demo/Driver/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demos.devexpress.com/aspxfilemanageranduploaddemos/uploadcontrol/UploadAndSubmit.aspx");
        driver.findElement(By.cssSelector("#ContentHolder_FormLayout_DescriptionTextBox_I")).sendKeys("uploading files");
        try {
            driver.findElement(By.cssSelector("#ContentHolder_FormLayout_DocumentsUploadControl_TextBox0_Input")).sendKeys(excel.getData(0, 0, 0));
        }
        catch(NoSuchElementException e){
            System.out.print(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try {
            driver.findElement(By.cssSelector("#ContentHolder_FormLayout_SubmitButton_CD")).click();
        }
        catch(ElementNotVisibleException e)
        {
            System.out.println(e.getMessage());
        }
        WebElement message = driver.findElement(By.cssSelector("#ContentHolder_FormLayout_RoundPanel_DescriptionLabel"));
        String expectedErrorMessage = "uploading files";
        String actualErrorMessage = message.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual message is not matched with expected message. \nActual: "
                        + actualErrorMessage + "\nExpected: "
                        + expectedErrorMessage);
        System.out.print("Success");
        driver.quit();
    }

    //For Headless
    @Test
    public void headless(){
    System.setProperty("webdriver.chrome.driver","/home/knoldus/Desktop/S_Demo/Driver/chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    driver= new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("https://demos.devexpress.com/aspxfilemanageranduploaddemos/uploadcontrol/UploadAndSubmit.aspx");
    driver.findElement(By.cssSelector("#ContentHolder_FormLayout_DescriptionTextBox_I")).sendKeys("uploading files");
    try {
        driver.findElement(By.cssSelector("#ContentHolder_FormLayout_DocumentsUploadControl_TextBox0_Input")).sendKeys(excel.getData(0,0,0));
    }
    catch(NoSuchElementException e){
        System.out.print(e.getMessage());
    }
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    try{
        driver.findElement(By.cssSelector("div[id='ContentHolder_FormLayout_SubmitButton_CD'] span[class='dx-vam']")).click();}
    catch(ElementNotVisibleException e)
    {
        System.out.println(e.getMessage());
    }
    WebElement message = driver.findElement(By.cssSelector("#ContentHolder_FormLayout_RoundPanel_DescriptionLabel"));
    String expectedErrorMessage = "uploading files";
    String actualErrorMessage = message.getText();
    Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
            "Actual message is not matched with expected message. \nActual: "
                    + actualErrorMessage + "\nExpected: "
                    + expectedErrorMessage);
    System.out.print("Success");
    driver.close();

    }

//For Screenshot
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                try{
                    FileHandler.copy(source, new File("Driver"+result.getName()+".png"));
                    System.out.println("Screenshot taken");
                }
                catch (Exception e)
                {
                    System.out.println("Exception while taking screenshot "+e.getMessage());
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }
}
