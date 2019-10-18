package pageVerifiers;
import org.openqa.selenium.WebDriver;

import pageObjects.SearchPage;

public class VerifySearchPage {
	private SearchPage search = null;
	private String[] tvSeriesArr = null;
		
	public VerifySearchPage(WebDriver driver, String[] tvSeries) {
		this.search = new SearchPage(driver);
		this.tvSeriesArr = tvSeries;
	}
	
	public void findSeriesAtIndex(int i) {
		//enter the name from the array in the main search bar
		search.searchForShow (tvSeriesArr[i]);
		//find the number of expected results - so we'll know when to stop
		int iCount = search.numSearchResults();
		//we only want to click on a TV Series - sometimes there are movies/tv episodes with the same name
		//we look for the listing of the relevant name with the text "TV Series" or "TV Mini-Series" in brackets
		String str = "";
		boolean found = false;
		int index = 1;
		while (!found && index <= iCount){
			str = search.getResultStr(Integer.toString(index));
			if (str.contains((tvSeriesArr[i])) && (str.contains(("TV Series").toLowerCase()) ||
					str.contains(("TV Mini-Series").toLowerCase()))) {
				found = true;
				search.clickOnSearchResult(Integer.toString(index));

			}
			index++;
		}
	}
	
	
}
