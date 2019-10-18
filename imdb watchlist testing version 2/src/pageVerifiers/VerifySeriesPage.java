package pageVerifiers;
import org.openqa.selenium.WebDriver;

import pageObjects.SeriesPage;

public class VerifySeriesPage {
	private float ratingVal = 0.0f;
	private SeriesPage sPage = null;
	
	public VerifySeriesPage(WebDriver driver, float ratingVal) {
		this.ratingVal = ratingVal;
		this.sPage = new SeriesPage(driver);
	}
	
	public boolean checkRatingAndAddToWL(int i) {
		String currRating = sPage.getRating();
		String delim = "/";
		String[] ratings = currRating.split(delim);
		if (Float.parseFloat(ratings[0]) >= ratingVal) {
			sPage.waitForRibbon();
			if (sPage.noInWlElems()) {//show is not in watchlist
				sPage.clickNotInWl(); //add to watchlist
			}
		} 
		else {
			return false;
		}
		return true;
	}
	
}


