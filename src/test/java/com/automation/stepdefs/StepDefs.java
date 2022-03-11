package com.automation.stepdefs;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.automation.stepdefs.StepDefs;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefs {

	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	WebDriver driver;

	String base_url = "http://automationpractice.com";	
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn;



	@Before
	public void setUp(Scenario scn){
		this.scn = scn;


		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
		scn.log("Browser Invoked");
		logger.info("Browser Invoked");
	}

	@After(order=1)
	public void cleanUp(){
		driver.close();
		scn.log("Browser Closed");

	}

	@After(order=2) // this will execute first, higher the number, sooner it executes
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}else{
			scn.log("Test case is passed, no screen shot captured");
		}
	}


	//	@Given("User opened browser")
	//	public void user_opened_browser() {
	//		driver = new ChromeDriver();
	//		driver.manage().window().maximize();;
	//		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);

	//	}

	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {


		driver.get(base_url);

		scn.log("Browser navigated to URL: " + base_url);
		logger.info("Browser navigated to URL: " + base_url);

		String expected="My Store";
		String actual=driver.getTitle();
		Assert.assertEquals("Page Title validation",expected,actual);

		scn.log("Page title validation successfull. Actual title: " + actual );
		logger.info("Page title validation successfull. Actual title: " + actual);



	}

	@When("user viewed for application logo")
	public void user_viewed_for_application_logo() {

		WebElement LogoImage = driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", LogoImage);
	}

	@Then("application logo is displayed")
	public void application_logo_is_displayed() {
		WebElement LogoImage = driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img"));

		//Verify the Application Logo is Displyed or Not

		if(LogoImage.isDisplayed())
		{
			System.out.println("Application logo is Displayed");


			//Verify the image Text Value 
			System.out.println("The width of logo is-" + LogoImage.getAttribute("width"));

			System.out.println("The height of logo is -" + LogoImage.getAttribute("height"));

		}

		else 
		{
			System.out.println("Application logo is not Displayed");
		}

		scn.log("Logo is Displyed " +LogoImage.isDisplayed());
		logger.info("Logo is Displyed " +LogoImage.isDisplayed());


	}

	//	@Then("user close the browser")
	//	public void user_close_the_browser() {
	//		driver.quit();
	//
	//	}


	@Given("User search for product categories")
	public void user_search_for_product_categories() throws InterruptedException {

		WebElement WomenSection = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
		Thread.sleep(3000);
		JavascriptExecutor js1 = ((JavascriptExecutor) driver);
		js1.executeScript("arguments[0].style.border='3px solid red'", WomenSection);

		Thread.sleep(6000);

		WebElement DressesSection =driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a"));
		Thread.sleep(3000);
		JavascriptExecutor js2 = ((JavascriptExecutor) driver);
		js2.executeScript("arguments[0].style.border='3px solid red'",DressesSection);
		Thread.sleep(6000);

		WebElement TShirtSection =driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a"));
		Thread.sleep(3000);
		JavascriptExecutor js3 = ((JavascriptExecutor) driver);
		js3.executeScript("arguments[0].style.border='3px solid red'",TShirtSection);
		Thread.sleep(6000);

	}

	@When("User Click on Any Product")
	public void user_click_on_any_product() throws InterruptedException {
		WebElement WomenSection = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
		WomenSection.click();

		System.out.println("Title Page for WomenSection - " + driver.getTitle());

		scn.log("The Womean Section is Checked -");
		logger.info("The Womean Section is Checked -");
		Thread.sleep(6000);

		WebElement DressesSection =driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a"));
		DressesSection.click();

		System.out.println("Title Page  for DressesSection  - " + driver.getTitle());

		scn.log("The Dresses Section is Checked -");
		logger.info("The Dresses Section is Checked -");
		Thread.sleep(6000);

		WebElement TShirtSection =driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a"));
		TShirtSection.click();

		System.out.println("Title Page  for TShirtSection - " + driver.getTitle());

		scn.log("The T- Shirt Section is Checked -" );
		logger.info("The T- Shirt Section is Checked -" );
		Thread.sleep(6000);


	}
	//	@Then("user closed the browser")
	//	public void user_closed_the_browser() {
	//		driver.quit();
	//
	//	}


	@When("User Search for product {string}")
	public void user_search_for_product(String  productName) {
		WebElement SerchBox = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));

		SerchBox.sendKeys("T-Shirts");

		scn.log("Search for T-shirt ");
		logger.info("Search for T-shirt ");

		JavascriptExecutor jS = ((JavascriptExecutor) driver);
		jS.executeScript("arguments[0].style.border='3px solid red'",SerchBox);


	}

	@Then("User can see an result contains T-shirt as Text")
	public void user_can_see_an_result_contains_t_shirt_as_text() throws InterruptedException {
		WebElement listOfProducts = driver.findElement(By.xpath("/html/body/div[2]/ul/li/strong"));
		//	listOfProducts .click();

		JavascriptExecutor jS = ((JavascriptExecutor) driver);
		jS.executeScript("arguments[0].style.border='3px solid red'",listOfProducts);
		Thread.sleep(6000);

	}

	//	@Then("User Click on the closed browser")
	//	public void user_click_on_the_closed_browser() {
	//		driver.quit();
	//	}


	@Given("User Click on Social media Application twitter logo image")
	public void user_click_on_social_media_application_twitter_logo_image() throws InterruptedException {
		WebElement LogoImage = driver.findElement(By.xpath("/html/body/div/div[3]/footer/div/section[1]/ul/li[2]/a"));


		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollBy(0,4000)");

		JavascriptExecutor jS = ((JavascriptExecutor) driver);
		jS.executeScript("arguments[0].style.border='3px solid red'",LogoImage);

		Thread.sleep(6000);



	}

	@When("Validate Url is displayed in new tab")
	public void validate_url_is_displayed_in_new_tab() {

		WebElement LogoImage = driver.findElement(By.xpath("/html/body/div/div[3]/footer/div/section[1]/ul/li[2]/a"));

		LogoImage.click();

		String Title = driver.getCurrentUrl();

		System.out.println("The Current Page Title is-" + Title);

		scn.log("Title page is Displyed -"+Title);
		logger.info("Title page is Displyed -"+Title);

		//		 JavascriptExecutor jS = ((JavascriptExecutor) driver);
		//		 jS.executeScript("arguments[0].style.border='3px solid red'",Title );

	}
	@Then("User can able to see an twitter account name as Selenium Framework")
	public void user_can_able_to_see_an_twitter_account_name_as_selenium_framework() throws InterruptedException {

		WebElement NameOfFrameWork =driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div[1]/div/span[1]/span"));
		NameOfFrameWork.click();

		String FrameWorkName =driver.getTitle();

		System.out.println("The twitter Name is Shown as "+FrameWorkName);

		JavascriptExecutor jS = ((JavascriptExecutor) driver);
		jS.executeScript("arguments[0].style.border='3px solid red'",NameOfFrameWork );

		Thread.sleep(6000);

		scn.log("Checked the FrameWork  Name ");
		logger.info("Checked the FrameWork  Name ");

	}
}