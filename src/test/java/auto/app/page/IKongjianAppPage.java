/**
 * 
 */
package auto.app.page;

import com.automation.common.AppCommon;
import com.automation.listener.Log4jLogger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @ClassName:     IKongjianAppPage.java
 * @Description:   对爱空间装修APP进行自动化调试
 * 
 * @author         mazhaoyang
 * @version        V1.0  
 * @Date           2020年12月30日 上午9:27:52 
 */
public class IKongjianAppPage extends AppCommon{
	
	Log4jLogger logger = new Log4jLogger();
	
	public IKongjianAppPage(AppiumDriver<MobileElement> appHandler) {
		super(appHandler);
	}
	
	
	/*
	 * 打开APP，进入主页面
	 * */
	public void openAPP(){
		
		try {
			eleClickById("com.ikongjian.decoration:id/tv_agree");
			Thread.sleep(1000);
			eleClickById("com.ikongjian.decoration:id/tv_operate");
			Thread.sleep(1000);
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 进入装修页面，进行预约
	 * */
	public void switchToOrder() {
		try {
			tapClick(680,1835);
			Thread.sleep(1000);
			swipeDown();
			Thread.sleep(1000);
			swipeDown();
			Thread.sleep(1000);
			tapClick(524,1539);
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 进行预约操作
	 * */
	
	public void doOrder(){
		try {
			eleClickById("com.ikongjian.decoration:id/tv_location");
			Thread.sleep(1000);
			eleClickById("com.ikongjian.decoration:id/city");
			Thread.sleep(1000);
			String phoneNum = phoneNumberGet();
			phoneNumberList.add(phoneNum);
			logger.info(phoneNum);
			txtBoxSendValue(eleGetById("com.ikongjian.decoration:id/et_phone"),phoneNum);
			Thread.sleep(1000);
			eleClickById("com.ikongjian.decoration:id/iv_order");
			Thread.sleep(1000);
			tapClick(524,1539);
			Thread.sleep(1000);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
