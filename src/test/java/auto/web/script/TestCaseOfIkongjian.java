/**
 * 
 */
package auto.web.script;


import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import com.automation.exception.FrameworkException;
import com.automation.framework.WebTestNGBase;

import auto.web.page.IKongJianWebPage;


/**
 * @ClassName:     TestCaseOfIkongjian.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         mazhaoyang
 * @version        V1.0  
 * @Date           2021年1月20日 上午10:58:15 
 */
public class TestCaseOfIkongjian extends WebTestNGBase{
	
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian"}, priority=5,timeOut=MAX_EXCUTE_TIME)
	public void caseOfTotal()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			String myOrderListSize = properties.getProperty("myOrderListSize");
			int myOrderListSizeInt = Integer.parseInt(myOrderListSize);
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "完成预约单到分配设计师的流程";
			whichCaseIsRun(caseName);
			ikongjianpage.loginIkongjian(uname,pword);
			
			for(int inum=0;inum<myOrderListSizeInt;inum++){
				ikongjianpage.createPreOrder();
			}
			
			ikongjianpage.openOrderPage();
			
			for(int i=0;i<phoneNumberList.size();i++){
				ikongjianpage.changeOrderMessage(i);
			}
			
			for(int j=0;j<phoneNumberList.size();j++){
				ikongjianpage.payPreMoney(j);
			}
			
			for(int k=0;k<phoneNumberList.size();k++){
				ikongjianpage.moveTheOrder(k);
			}
			
			ikongjianpage.doLogOut();
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.one"}, priority=1,timeOut=MAX_EXCUTE_TIME)
	public void caseOfWebDriverStart()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成预约单";
			whichCaseIsRun(caseName);
			properties.load(inputStream);
			String myOrderListSize = properties.getProperty("myOrderListSize");
			int myOrderListSizeInt = Integer.parseInt(myOrderListSize);
			String testEnvironment = properties.getProperty("Environment");
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
		
			
			for(int inum=0;inum<myOrderListSizeInt;inum++){
				ikongjianpage.createPreOrder();
			}
			
			ikongjianpage.doLogOut();
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.two"}, priority=2,timeOut=MAX_EXCUTE_TIME)
	public void caseOfCreateOrder()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
		    properties.load(inputStream);
		    String testEnvironment = properties.getProperty("Environment");
		    
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成订单";
			whichCaseIsRun(caseName);
			
			osorderList.clear();
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
			ikongjianpage.openOrderPage();
			
			for(int i=0;i<phoneNumberList.size();i++){
				ikongjianpage.changeOrderMessage(i);
			}
			
			ikongjianpage.doLogOut();
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.three"}, priority=3,timeOut=MAX_EXCUTE_TIME)
	public void caseOfBook()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
		    properties.load(inputStream);
		    String testEnvironment = properties.getProperty("Environment");
		    
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "缴纳定金";
			whichCaseIsRun(caseName);
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
			for(int j=0;j<phoneNumberList.size();j++){
				ikongjianpage.payPreMoney(j);
			}
			
			ikongjianpage.doLogOut();
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.four"}, priority=4,timeOut=MAX_EXCUTE_TIME)
	public void caseOfSetDate()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
		    properties.load(inputStream);
		    String testEnvironment = properties.getProperty("Environment");
		    
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "约合开工、分配设计师";
			whichCaseIsRun(caseName);
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
			for(int k=0;k<phoneNumberList.size();k++){
				ikongjianpage.moveTheOrder(k);
			}
			
			ikongjianpage.doLogOut();
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.groupTwo"}, priority=1,timeOut=MAX_EXCUTE_TIME)
	public void caseOfWebGroupTwo()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成预约单";
			whichCaseIsRun(caseName);
			properties.load(inputStream);
			String myOrderListSize = properties.getProperty("myOrderListSize");
			int myOrderListSizeInt = Integer.parseInt(myOrderListSize);
			String testEnvironment = properties.getProperty("Environment");
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
			for(int inum=0;inum<myOrderListSizeInt;inum++){
				ikongjianpage.createPreOrder();
			}
			
			osorderList.clear();
			
			ikongjianpage.openOrderPage();
			
			for(int i=0;i<phoneNumberList.size();i++){
				ikongjianpage.changeOrderMessage(i);
			}
			
			ikongjianpage.doLogOut();
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.groupThree"}, priority=1,timeOut=MAX_EXCUTE_TIME)
	public void caseOfWebGroupThree()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成预约单";
			whichCaseIsRun(caseName);
			properties.load(inputStream);
			String myOrderListSize = properties.getProperty("myOrderListSize");
			int myOrderListSizeInt = Integer.parseInt(myOrderListSize);
			String testEnvironment = properties.getProperty("Environment");
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
			for(int inum=0;inum<myOrderListSizeInt;inum++){
				ikongjianpage.createPreOrder();
			}
			
			osorderList.clear();
			
			ikongjianpage.openOrderPage();
			
			for(int i=0;i<phoneNumberList.size();i++){
				ikongjianpage.changeOrderMessage(i);
			}
			
			for(int j=0;j<phoneNumberList.size();j++){
				ikongjianpage.payPreMoney(j);
			}
			
			ikongjianpage.doLogOut();
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.groupFour"}, priority=1,timeOut=MAX_EXCUTE_TIME)
	public void caseOfWebGroupFour()throws FrameworkException{
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
		    Properties properties = new Properties();
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成预约单";
			whichCaseIsRun(caseName);
			properties.load(inputStream);
			String myOrderListSize = properties.getProperty("myOrderListSize");
			int myOrderListSizeInt = Integer.parseInt(myOrderListSize);
			String testEnvironment = properties.getProperty("Environment");
			
			String uname = properties.getProperty("loginName");
			String pword = properties.getProperty("loginPassword");
			ikongjianpage.loginIkongjian(uname,pword);
			
			for(int inum=0;inum<myOrderListSizeInt;inum++){
				ikongjianpage.createPreOrder();
			}
			
			osorderList.clear();
			
			ikongjianpage.openOrderPage();
			
			for(int i=0;i<phoneNumberList.size();i++){
				ikongjianpage.changeOrderMessage(i);
			}
			
			for(int j=0;j<phoneNumberList.size();j++){
				ikongjianpage.payPreMoney(j);
			}
			
			for(int k=0;k<phoneNumberList.size();k++){
				ikongjianpage.moveTheOrder(k);
			}
			
			ikongjianpage.doLogOut();
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}

}
