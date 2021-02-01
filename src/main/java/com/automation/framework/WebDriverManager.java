package com.automation.framework;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.config.WebConstant;
import com.automation.listener.Log4jLogger;

/**
 * web/h5页面初始化类
 * @author mazy
 *
 */
public class WebDriverManager {
	
	public static RemoteWebDriver driver;
	Log4jLogger logger = new Log4jLogger();
	AbastractBase baseAct = new AbastractBase();
	
	/**
	 * broser setting and driver create
	 * 根据不同的浏览器类型，取lib下的对应driver
	 * @param browserType
	 * @return
	 * @author: mazy   
	 */
	private RemoteWebDriver browserSet(String browserType){
		if (browserType.equalsIgnoreCase("firefox")) {
			driver = createGeckodriver();
		} 
		if (browserType.equalsIgnoreCase("ie")) {
			driver = createIEDriver();
		} 
		if (browserType.equalsIgnoreCase("chrome")) {
			
			
			driver = createChromeDriver();
		} 
		if (browserType.equalsIgnoreCase("opera")) {
			driver = createOperaDriver();
		} 
		if(browserType.equalsIgnoreCase("H5")){
			driver = createH5ChromeDriver();
		}
		return driver;
	}
	
	/**
	 * opera driver create
	 * 取Opera浏览器的驱动，DesiredCapabilities可以不进行定义
	 * @return
	 * @author: mazy   
	 * @date: 2020年4月13日
	 */
	@SuppressWarnings("deprecation")
	private RemoteWebDriver createOperaDriver(){
		DesiredCapabilities dr = DesiredCapabilities.operaBlink();
		OperaOptions option = new OperaOptions();
		option.setBinary(System.getProperty("browserPath"));
		dr.setCapability(OperaOptions.CAPABILITY, option);
		System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "/lib/opreadriver.exe");
		dr.setPlatform(Platform.WINDOWS);
		option.merge(dr);
		driver = new OperaDriver(option);
		return driver;
	}
	
	/**
	 * h5 in chrome driver create
	 * 取内置Chrome的h5页面驱动
	 * @return
	 * @author: mazy   
	 */
	@SuppressWarnings({ "static-access" })
	private RemoteWebDriver createH5ChromeDriver(){
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/lib/chromedriver.exe");
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", baseAct.emulationName);
        Map<String, Object> chromeOptions = new HashMap<String, Object>();     
        chromeOptions.put("mobileEmulation", mobileEmulation);     
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();       
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        options.merge(capabilities);
		driver = new ChromeDriver(options);
		return driver;
	}
	
	/**
	 * chrome driver create
	 * 取Chrome的驱动
	 * @return
	 * @author: mazy   
	 */
	private RemoteWebDriver createChromeDriver(){
		ChromeOptions options = new ChromeOptions();		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.prompt_for_download", true);
		options.setExperimentalOption("prefs", prefs);
		
		//设置selenium后台运行	
		//方法一：通过setHeadless方法直接设置
		//options.setHeadless(true);
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/lib/chromedriver.exe");
		driver = new ChromeDriver(options);
		return driver;
	}
	
	/**
	 * firefox driver create
	 * 取Firefox的驱动
	 * @return
	 * @author: mazy   
	 */
	private RemoteWebDriver createGeckodriver() {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/lib/geckodriver.exe");
		driver = new FirefoxDriver();
		return driver;
	}
	
	/**
	 * ie driver create
	 * 取IE的驱动
	 * @return
	 * @author: mazy   
	 */
	@SuppressWarnings("deprecation")
	private InternetExplorerDriver createIEDriver(){
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/lib/IEDriverServer.exe");  //32 bit
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  
		//避免还需要手动取消IE浏览器的“保护模式”
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(InternetExplorerDriverService.createDefaultService(), ieCapabilities);
	}
	
	/**
	 * web driver initialize
	 * 初始化web的驱动
	 * @param browserType
	 * @throws MalformedURLException
	 * @author: mazy   
	 */
	public void initNewWebDriver(final String browserType) throws MalformedURLException {
		logger.info("Will start the web driver for the WEB case");
		driver = browserSet(browserType);
		
		driver.manage().timeouts().implicitlyWait(WebConstant.implicitlyWait, TimeUnit.SECONDS);
		if(!browserType.equalsIgnoreCase("chrome")){
			driver.manage().timeouts().pageLoadTimeout(WebConstant.pageLoadTimeout, TimeUnit.SECONDS);
		}
		driver.manage().timeouts().setScriptTimeout(WebConstant.scriptTimeout, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	/**
	 * web driver close
	 * 关闭驱动
	 * @author: mazy   
	 */
    public void closeDriver() {
    	try{
        	if(driver != null) {
				driver.close();
        		driver = null;
        		logger.info("All windows have been closed and cookies have been deleted.");
        		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    /**
     * web driver return 
     * 对webdriver驱动进行初始化
     * @param browserType
     * @return
     * @throws Throwable
     * @author: mazy   
     */
    public RemoteWebDriver getCurrentWebDriver(String browserType) throws Throwable {
    	// Get driver mode,获取到当前浏览器的类型,并设置了重试三次保证驱动启动成功机制
    	String mode = System.getProperty("HANDLE_MODE");
    	
    	if (StringUtils.isBlank(mode)) {
    		mode = WebConstant.handleMode;
    	}
    	
    	if ("Selenium".equalsIgnoreCase(mode)){
    		driver = null;
    		initNewWebDriver(browserType);
    		int tryTime=0;
    		while(driver==null && tryTime<3){
    			tryTime++;
    			AbastractBase baseAct = new AbastractBase();
    			baseAct.browserKill(browserType);
    			initNewWebDriver(browserType);
    		}
    	}
        return driver;
    }
}