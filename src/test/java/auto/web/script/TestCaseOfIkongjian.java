/**
 * 
 */
package auto.web.script;


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
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "完成预约单到分配设计师的流程";
			whichCaseIsRun(caseName);
			ikongjianpage.loginIkongjian("wangliang1","space521");
			
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
			
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.one"}, priority=1,timeOut=MAX_EXCUTE_TIME)
	public void caseOfWebDriverStart()throws FrameworkException{
		try{
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成预约单";
			whichCaseIsRun(caseName);
			ikongjianpage.loginIkongjian("wangliang1","space521");
			
			for(int inum=0;inum<2;inum++){
				ikongjianpage.createPreOrder();
			}
			
			
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.two"}, priority=2,timeOut=MAX_EXCUTE_TIME)
	public void caseOfCreateOrder()throws FrameworkException{
		try{
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "生成订单";
			whichCaseIsRun(caseName);
			
			osorderList.clear();
			ikongjianpage.loginIkongjian("wangliang1","space521");
			
			ikongjianpage.openOrderPage();
			
			for(int i=0;i<phoneNumberList.size();i++){
				ikongjianpage.changeOrderMessage(i);
			}
			
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.three"}, priority=3,timeOut=MAX_EXCUTE_TIME)
	public void caseOfBook()throws FrameworkException{
		try{
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "缴纳定金";
			whichCaseIsRun(caseName);
			
			ikongjianpage.loginIkongjian("wangliang1","space521");
			
			for(int j=0;j<phoneNumberList.size();j++){
				ikongjianpage.payPreMoney(j);
			}
			
			
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian.four"}, priority=4,timeOut=MAX_EXCUTE_TIME)
	public void caseOfSetDate()throws FrameworkException{
		try{
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "约合开工、分配设计师";
			whichCaseIsRun(caseName);
			
			ikongjianpage.loginIkongjian("wangliang1","space521");
			
			for(int k=0;k<phoneNumberList.size();k++){
				ikongjianpage.moveTheOrder(k);
			}
			
			
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}

}
