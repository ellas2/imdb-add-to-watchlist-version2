package pageVerifiers;
import org.openqa.selenium.WebDriver;

import pageObjects.WatchlistPage;

public class VerifyWatchListPage {
	private WebDriver driver = null;
	private WatchlistPage wlPage = null;
	private WatchlistAux wlAux = null;
	private String[] tvSeriesArr = null;
	
	public VerifyWatchListPage(WebDriver driver, String[] tvSeriesArr) {
		this.driver = driver;
		this.wlPage = new WatchlistPage(driver);
		this.wlAux = new WatchlistAux(wlPage);
		this.tvSeriesArr = tvSeriesArr;
	}
	
	public boolean verifyWatchList() {
		driver.get("https://www.imdb.com/user/ur80563201/watchlist");
		float watchlistCount = wlAux.initializeLoadMoreArr();
		wlAux.sortByAddedDate();

		//for every tv show in our array - go over the watchlist and look for it. stop when found
		for(int i = 0; i < tvSeriesArr.length; i++) {			
			if (tvSeriesArr[i].isEmpty()) { //this means the show rating was lower or the string is not of a tv show
				continue;
			}
			//look for the tv show in the watchlist
			boolean found = false;
			int index = 1;
			while (!found && index <= watchlistCount) {
				wlAux.handleLoadArr(index);
				String strInd = Integer.toString(index);
				String linkText = wlPage.itemInList(strInd);			
				if (linkText.contains(tvSeriesArr[i]) || tvSeriesArr[i].contains(linkText)) {
					found = true;
				}
				index++;
			}
			if (!found) {
				System.out.println("TV Series: " + tvSeriesArr[i] + " Couldn't be found");
				return false;
			}
		}
		return true;
	}
	
}


