package test;
import java.lang.*; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageVerifiers.VerifySearchPage;
import pageVerifiers.VerifySeriesPage;
import pageVerifiers.VerifyWatchListPage;
import preconditions.ChromeWebDriver;
import preconditions.PropFileHandler;
import preconditions.SignIn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.Assert.assertTrue;



public class WatchlistTest {
	WebDriver driver = null;
	String[] tvSeriesArr = null;
	float ratingValue = 0.0f;
	
	@BeforeEach
	public void preconditions() {
		//Handle and parse properties file
		PropFileHandler fileH = new PropFileHandler(".\\config.properties");
		tvSeriesArr = fileH.loadAndParseFile();
		ratingValue = fileH.getRatingVal();
		//Initialize the web driver and go to the IMDb home page
		ChromeWebDriver chromeWD = new ChromeWebDriver(".\\Driver\\chromedriver.exe");
		driver = chromeWD.initializeAtUrl("https://www.imdb.com");
		//Register to IMDb in order to be able to use the IMDb feature
		SignIn sI = new SignIn(driver);
		sI.verifyAndSignInWithIMDb();
	}
	
	

	@Test
	public void addToWatchlistAndVerify(){
		//Add to watchlist
		VerifySearchPage verifySearch = new VerifySearchPage(driver, tvSeriesArr, ratingValue);
		VerifySeriesPage verifySeries = new VerifySeriesPage(driver, tvSeriesArr, ratingValue);
		for(int i = 0; i < tvSeriesArr.length; i++) {
			verifySearch.findSeriesAtIndex(i);
			boolean res = verifySeries.checkRatingAndAddToWL(i);
			if (res == false) {
				tvSeriesArr[i] = "";
			}	
		}
		//Verify watchlist
		VerifyWatchListPage verfiyWP = new VerifyWatchListPage(driver, tvSeriesArr);
		boolean returnValue = verfiyWP.verifyWatchList();
		System.out.println(returnValue);
		assertTrue(returnValue);
	}
		

	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}	
}
