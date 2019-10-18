package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
	private WebDriver driver = null;
	private By searchBox = By.id("navbar-query");
	private By magnifyingGlass = By.className("magnifyingglass");
	private By ResultList = By.xpath("//div/table/tbody//*[@class='result_text']");
	private String xpathStr = "";
	private String xpathStrLink = "";
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void searchForShow (String currTvShow)	{
		driver.findElement(searchBox).sendKeys(currTvShow);
		driver.findElement(magnifyingGlass).click();
	}
	
	public int numSearchResults() {
		return driver.findElements(ResultList).size();			
	}
	
	
	public String getResultStr(String strInd) {
		this.xpathStr = "(//div/table/tbody//*[@class='result_text'])[" + strInd + "]";
		return driver.findElement(By.xpath(xpathStr)).getText().toLowerCase();
	}

	public void clickOnSearchResult(String strInd) {
		this.xpathStrLink = "(//div/table/tbody//*[@class='result_text']/a)[" + strInd + "]";
		driver.findElement(By.xpath(xpathStrLink)).click();
	}
	
	public void clearSearchBox() {
		driver.findElement(searchBox).clear();
	}

}
