package auto.app.page;

import java.util.List;

import com.automation.common.AppCommon;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ContactAppPage extends AppCommon{
	
	public ContactAppPage(AppiumDriver<MobileElement> appHandler) {
		super(appHandler);
	}
	
	/**
	 * add contact mobile
	 * @param name
	 * @param mobile
	 * @throws Throwable
	 * @author: mazy   
	 * @date: 2020年4月23日
	 */
	public void addNewContact(String name, String mobile) throws Throwable {
		Thread.sleep(1000);
		eleClickById("com.android.contacts:id/floating_action_button");//点击本地保存按钮
		Thread.sleep(1000);
		eleClickById("com.android.contacts:id/left_button");//点击本地保存按钮
		Thread.sleep(1000);
		List<MobileElement> editBox = eleListsGetByClassName("android.widget.EditText");//可输入编辑框获取
		txtBoxSendValue(editBox.get(0), userNameGet());//姓名
		txtBoxSendValue(editBox.get(1), phoneNumberGet());//电话
		eleClickById("com.android.contacts:id/menu_save");//保存
		Thread.sleep(200);
	}
}
