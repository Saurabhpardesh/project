package flipcartpro;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipCartPRO {
	WebDriver driver;
  @BeforeClass
  public void SetUp() {
	  System.setProperty("webdriver.chrome.driver","D:\\Chrome downlode file\\Chrome driver Selenium\\chromedriver-win64\\chromedriver.exe");
	  
	  driver = new ChromeDriver();
	  
	  driver.manage().window().maximize();
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  
  }
  @Test(priority=1)
    public void openFlipkartwebsite() {
	  driver.get("https://www.flipkart.com/");
	  
	  System.out.println(driver.getTitle());
	  
	  Assert.assertTrue(driver.getTitle().contains("Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!"));
	  
  }
  
  @Test(priority=2)
  public void SearchandAddtoCart() {
	  
	  driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys("laptop");
       
      Actions act = new Actions(driver);
      act.sendKeys(Keys.ENTER).perform();
      
      driver.findElement(By.xpath("//div[text()='Acer Aspire 3 Intel Core i3 12th Gen 1215U - (8 GB/512 GB SSD/Windows 11 Home) A315-59 Thin and Light Laptop']")).click();
      
      Set<String>str=driver.getWindowHandles();
      Iterator<String> it =str.iterator();
      String parentwindow = it.next();
      String childwindow = it.next();
      driver.switchTo().window(childwindow);
      driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
      
  }
  @Test(priority=3)
  public void ProceedtoCheckout() {
	  
	  driver.findElement(By.xpath("//span[text()='Place Order']")).click();
	  
	  driver.findElement(By.xpath("//span[text()='Cart']"));
	  
      WebElement itemInCart = driver.findElement(By.xpath("//div[@class='KK-o3G']"));
      Assert.assertTrue(itemInCart.isDisplayed());
      
      
      
  }
  @Test(priority = 4)
  public void userAuthentication() {
	  WebElement emailInput = driver.findElement(By.xpath("//span[text()='Enter Email/Mobile number']"));
      emailInput.sendKeys("sp12@gmail.com");
      WebElement passwordInput = driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']"));
      passwordInput.sendKeys("Flipkart@123");
      WebElement loginButton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"));
      loginButton.click();
      
      WebElement loggedInUsername = driver.findElement(By.xpath("//div[@class='_21i8Dg']"));
      Assert.assertTrue(loggedInUsername.isDisplayed());
  }
  @Test(priority = 5)
  public void shippingInformation() {
      // Enter valid shipping information
      WebElement addressInput = driver.findElement(By.xpath("//input[@name='addressLine1']"));
      addressInput.sendKeys("Bangalaore marathalli");
      WebElement cityInput = driver.findElement(By.xpath("//input[@name='city']"));
      cityInput.sendKeys("Bangalore");
      WebElement stateInput = driver.findElement(By.xpath("//input[@name='state']"));
      stateInput.sendKeys("Karnataka");
      WebElement zipInput = driver.findElement(By.xpath("//input[@name='pincode']"));
      zipInput.sendKeys("560037");
      
      // Proceed to the next step
      WebElement continueButton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"));
      continueButton.click();
  }
  @Test(priority = 6)
  public void paymentInformation() {
      // Choose a payment method
      WebElement creditCardOption = driver.findElement(By.xpath("//input[@id='CREDIT_CARD']"));
      creditCardOption.click();
      
      // Proceed to the next step
      WebElement continueButton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"));
      continueButton.click();
  }

  @Test(priority = 7)
  public void reviewOrder() {
      // Verify the order summary
      WebElement orderSummary = driver.findElement(By.xpath("//div[@class='_1zvu5Y _3llSbO']"));
      Assert.assertTrue(orderSummary.isDisplayed());
      
      // Do not proceed to the final order placement (this is a mock scenario)
  }

  @AfterClass
  public void tearDown() {
      // Close the browser after all tests are executed
     driver.quit();
  }  
}