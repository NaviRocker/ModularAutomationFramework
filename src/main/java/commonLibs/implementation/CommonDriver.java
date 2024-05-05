package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonDriver {
	
	private WebDriver driver;
	private int pageLoadTimeout;
	private int elementDetectionTimeout;
	private String pwd;
	
	public CommonDriver(String browserType) throws Exception {
		
		pageLoadTimeout = 30;
		elementDetectionTimeout = 30;
		pwd = System.getProperty("user.dir");
		
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", pwd+"/drivers/chromedriver64.exe");
			driver = new ChromeDriver();
		} else if(browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",  pwd+"/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		} else if(browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver",  pwd+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			throw new Exception("Invalid Browser Type: " + browserType);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	public void navigateToURL(String url) {
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		
		driver.get(url);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	public void closeAllBrowser() {
		driver.quit();
	}
	
	public String getTitleOfThePage() {
		return driver.getTitle();
	}

}
