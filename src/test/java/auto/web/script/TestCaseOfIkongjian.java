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
	@Test(alwaysRun=false, groups={"auto.demo.test.ikongjian"}, timeOut=MAX_EXCUTE_TIME)
	public void caseOfWebDriverSearchAndOpenSeleniumHq()throws FrameworkException{
		try{
			IKongJianWebPage ikongjianpage = new IKongJianWebPage(driver);
			caseName = "对魔盒系统中的预约单进行信息补充以及订单生成";
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
	

}
