/**
 * 
 */
package auto.app.script;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.automation.common.AppCommon;
import com.automation.exception.FrameworkException;
import com.automation.framework.AppTestNGBase;

import auto.app.page.IKongjianAppPage;


/**
 * @ClassName:     TestCaseOfIkongjian.java
 * @Description:   对ikongjianAPP进行自动化操作（重点是对动态页面的操作）
 * 
 * @author         mazhaoyang
 * @version        V1.0  
 * @Date           2020年12月30日 上午9:49:35 
 */
public class TestCaseOfIkongjian extends AppTestNGBase{
	IKongjianAppPage ikjAPP;
	
	
	@Test(alwaysRun=true, groups={"auto.demo.test.ikongjianapp"}, timeOut=MAX_EXCUTE_TIME,priority = 0)
	public void caseOfAppiumAndroidContact()throws FrameworkException{
		try{
			caseName = "浏览爱空间APP，并进行预约";
			whichCaseIsRun(caseName);
			ikjAPP = new IKongjianAppPage(appHandler);		
			//对弹框进行操作
			ikjAPP.openAPP();			
			//进入预约页面
			ikjAPP.switchToOrder();			
			boolean orderResult = ikjAPP.toastChk("请输入您的手机号");			
			Assert.assertEquals(orderResult, true);			
			
			//重复预约
			for(int i=0;i<5;i++){
				ikjAPP.doOrder();
			}
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
	
	/*
	@Test(alwaysRun=true, groups={"auto.demo.test.app"}, timeOut=MAX_EXCUTE_TIME,priority = 1)
	public void caseOfIKongJianOrder()throws FrameworkException{
		
		try {
			ikjAPP = new IKongjianAppPage(appHandler);
			caseName = "进行预约操作";
			whichCaseIsRun(caseName);
			ikjAPP.doOrder();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	*/
}
