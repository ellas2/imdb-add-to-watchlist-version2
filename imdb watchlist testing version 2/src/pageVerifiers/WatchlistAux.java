package pageVerifiers;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.WatchlistPage;

public class WatchlistAux {
	private WatchlistPage wlPage;
	private int loadMoreInd = 0;
	private boolean[] loadMore = null;
	
	public WatchlistAux(WatchlistPage wlPage) {
		this.wlPage = wlPage;
	}
	
	//this will be used to know how many times to click 'load more' during the test
	public float initializeLoadMoreArr() {
		String titleCount = wlPage.getWatchlistCount();	
		String delims = " Titles";
		String[] countStrArr = titleCount.split(delims);
		float watchlistCount = Float.parseFloat(countStrArr[0]);
		int numElementsInArr = (int) Math.ceil(watchlistCount/60);
		boolean[] loadMore = new boolean[numElementsInArr];
		Arrays.fill(loadMore, Boolean.FALSE);
		return watchlistCount;
	}
	
	
	//sort by added date - for efficiency
	public void sortByAddedDate() {
		Select sortBy = new Select(wlPage.sortBy());
		sortBy.selectByVisibleText("Date Added");
		//we wait for the page to be completely loaded with the "Date Added" sorting
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void handleLoadArr(int index) {
		//every 60 entries we need to click 'load more'
		if (index % 60 == 1 && index >= 61) {
			int indexInLoadArr = index/60 - 1;
			for (int j = 0; j <= indexInLoadArr; j++) {
				if (!loadMore[j]) {
					wlPage.loadMore();
					loadMore[j] = true;
				}
			}
			loadMoreInd = indexInLoadArr;
		}
	}
	
	
}