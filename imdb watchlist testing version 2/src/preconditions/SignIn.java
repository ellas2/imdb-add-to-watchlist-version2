package preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.SignInWIMDbPage;

public class SignIn {
	private WebDriver driver = null;
	private String emailAddr = "ella.steinberg93@gmail.com";
	private String password = "!Qaz2wsx";
	
	public SignIn(WebDriver driver) {
		this.driver = driver;
	}
	
	//returns true iff the user is logged in 
	public boolean signedIn() {
		if (driver.findElements(By.linkText("Sign in")).size() != 0)
			return false;
		return true;
	}
	
	public void verifyAndSignInWithIMDb(){
		if (!signedIn()) {
			driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.className("imdb-logo")).click();
			SignInWIMDbPage signIn = new SignInWIMDbPage(driver);
			signIn.fullSignIn(emailAddr, password);
			
		}
	}
}