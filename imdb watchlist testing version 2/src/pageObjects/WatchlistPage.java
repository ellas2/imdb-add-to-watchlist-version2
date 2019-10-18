package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WatchlistPage {
	WebDriver driver;
	
	private By titleCount = By.className("lister-details");//need get text on this
	private By sortOptions = By.id("lister-sort-by-options");
	private By loadMore = By.className("load-more");
	private String xpathStr = "";//gets updated every time
	
	public WatchlistPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getWatchlistCount() {
		return driver.findElement(titleCount).getText();
	}
	
	public WebElement sortBy() {
		return driver.findElement(sortOptions);
	}
	
	public void loadMore() {
		driver.findElement(loadMore).click();
	}
	
	public void updateXpathString(String strInd) {
		this.xpathStr = "(//div//*[@class='lister-item-header']/a)[" + strInd + "]";
	}
	
	public String itemInList(String stringInd) {
		this.updateXpathString(stringInd);
		WebDriverWait waity = new WebDriverWait(driver, 20);
		waity.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathStr)));
		String linkText = driver.findElement(By.xpath(xpathStr)).getText().toLowerCase();
		return linkText;
	}
	
}
