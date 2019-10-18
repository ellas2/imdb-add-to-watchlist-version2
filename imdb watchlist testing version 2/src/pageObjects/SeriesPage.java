package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeriesPage {
	private WebDriver driver = null;
	
	private By rating = By.className("rating");
	private By watchlistRibbon = By.className("wl-ribbon");
	private By inWl = By.className("inWL");
	private By notInWl = By.className("not-inWL");
	 
	public SeriesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getRating() {
		return driver.findElement(By.className("rating")).getText();//String of form 'x/10'
	}
	
	public void waitForRibbon() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(watchlistRibbon));
	}
	
	public boolean noInWlElems() {
		if (driver.findElements(inWl).size() == 0) {
			return true;
		}
		return false;
	}
	
	public void findNotInWl() {
		driver.findElement(notInWl);
	}
	
	//this adds the item to watchlist
	public void clickNotInWl() {
		driver.findElement(notInWl).click();
	}
}
