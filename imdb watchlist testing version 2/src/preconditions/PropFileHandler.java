package preconditions;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PropFileHandler {
	private String propFilePath;
	private float ratingValue = 0.0f;
	private String[] tvSeriesArr = null;
	
	public PropFileHandler(String propFilePath) {
		this.propFilePath = propFilePath;
	}
	
	public String[] loadAndParseFile(){
		try (InputStream input = new FileInputStream(propFilePath)) {
			Properties prop = new Properties();    
			prop.load(input);
			handleRating(prop);
			handleTvShows(prop);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return tvSeriesArr;
	}
	
	//Assumption: the rating value in properties file is valid
	public void handleRating(Properties prop) {
		String ratingStr = (prop.getProperty("ratings").replaceAll("^\"|\"$", "")).trim();
		ratingValue = Float.parseFloat(ratingStr);
	}
	
	public void handleTvShows(Properties prop) {
		String tvShows = prop.getProperty("tv"); //get a string of the format "tvshow1","tvshow2",...
        String delims = ",";
        String[] tvShowsArr = tvShows.split(delims);
        for (int i = 0; i < tvShowsArr.length; i++) {
        	tvShowsArr[i] = tvShowsArr[i].toLowerCase().replaceAll("^\"|\"$", "").trim();
        }   
        tvSeriesArr = tvShowsArr;
	}
	
	//Before calling handleRating this will return 0.0f
	public float getRatingVal() {
		return this.ratingValue;
	}
	
	public void setRatingVal(float val) {
		this.ratingValue = val;
	}
	
	//Before calling handleTvShows this will return null
	public String[] getTvArr() {
		return this.tvSeriesArr;
	}
	
	public void setTvArr(String[] tvArr) {
		this.tvSeriesArr = tvArr;
	}
}



