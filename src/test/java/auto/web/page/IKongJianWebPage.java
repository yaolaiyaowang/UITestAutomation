/**
 * 
 */
package auto.web.page;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.common.WebCommon;
import com.automation.entities.OneShopOrder;

/**
 * @ClassName:     IKongJianWebPage.java
 * @Description:   对ikongjian页面进行配置操作
 * 
 * @author         mazhaoyang
 * @version        V1.0  
 * @Date           2021年1月20日 上午10:46:51 
 */
public class IKongJianWebPage extends WebCommon{
	OneShopOrder osorder;
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("OrderUsefulInfo.properties");
    Properties properties = new Properties();
    
   
	
	public IKongJianWebPage(RemoteWebDriver driver) {		
		super(driver);
	}
	
	By usernameBy = By.name("username");
	By passwordBy = By.name("password");
	By login = By.xpath("//input[@value='登录']");
	By swipBy = By.id("nc_1_n1z");
	
	
	/*
	 * 登录魔盒
	 * */
	public void loginIkongjian(String name,String password){
		
		try {
			 properties.load(inputStream);
			 String envirment = properties.getProperty("Environment");
			toURL("https://uc."+envirment+".ikongjian.com/login");
			txtBoxSendValue(usernameBy, name);
			txtBoxSendValue(passwordBy, password);
			Actions action = new Actions(driver);
			action.dragAndDropBy(driver.findElement(swipBy), 287, 0).perform();
			eleClickBy(login);
			Thread.sleep(3000);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	By PhoneNumBy = By.name("mobile");
	By UserNameBy = By.name("userName");
	By provincepreBy = By.id("province");
	By cityPreBy = By.id("city");
	By regionPreBy = By.id("regionId");
	By planDecorateDateBy = By.name("planDecorateDate");
	By areaCodePreBy = By.name("areaCode");
	By shopNoPreBy = By.name("shopNo");
	By subBtnBy = By.id("subBtn");
	
	/*
	 * 建立预约单
	 * */
	
	public void createPreOrder() throws Throwable{
		properties.load(inputStream);
		 String envirment = properties.getProperty("Environment");
		osorder = new OneShopOrder();
		toURL("https://ncrm."+envirment+".ikongjian.com/order/addOrder");
		
		String phoneNum = phoneNumberGet();
		phoneNumberList.add(phoneNum);
		
		osorder.setPhoneNumber(phoneNum);
		osorder.setName(phoneNum);
		osorderList.add(osorder);
		//输入手机号和姓名
		txtBoxSendValue(PhoneNumBy, phoneNum);
		txtBoxSendValue(UserNameBy, phoneNum);
		System.out.println(phoneNum);
		
		//选择地区
		doOptionSelect(provinceBy,"0001");
		doOptionSelect(cityBy,"00010001");
		doOptionSelect(regionIdBy,"6");	
		
		
		//地图选址
		eleClickBy(communityBy);
		driver.switchTo().frame(driver.findElement(mapFrameBy));
		txtBoxSendValue(tipinputBy, "育慧里");
		eleClickBy(quitBtnBy);
		eleClickBy(queryAddressBy);
		eleClickBy(sureMyButtonBy);
		
		
		//选择店		
		doOptionSelect(areaCodePreBy,"101");
		doOptionSelect(shopNoPreBy,"SS043");
		
		//设置预约日期
		txtBoxSendValue(planDecorateDateBy, "2021-08-01");
		
		
		
		eleClickBy(subBtnBy);
		
	}
	
	By crmBy = By.xpath("//a[contains(text(),'crm系统')]");
	By preorderManageBy = By.xpath("//span[contains(text(),'预约单管理')]");
	

	
	By preorderListBy = By.id("menu_son_2303672875244060673");
	By userphoneBy = By.id("userPhone");
	By searchBy = By.xpath("//input[@value='查询']");
	
	By preorderPageBy = By.xpath("//a[contains(text(),'YY2021')]");
	
	
	
	
	
	/*
	 * 打开预约单页面
	 * */
	public void openOrderPage(){
		try {
		//	eleClickBy(crmBy);
			properties.load(inputStream);
			 String envirment = properties.getProperty("Environment");
			 
			toURL("https://ncrm."+envirment+".ikongjian.com/sysMain");
			eleClickBy(preorderManageBy);
			Thread.sleep(2000);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	By preOrderBy = By.xpath("//input[@value='预约单修改']");
	By usernameChangeBy = By.name("userName");
	
	By provinceBy = By.id("province");
	By cityBy = By.id("city");
	By regionIdBy = By.id("regionId");
	
	By areaCodeBy = By.id("areaCode");
	By shopNoEditBy = By.id("shopNoEdit");
	
	By saveBtnBy = By.id("saveBtn");
	
	By preOrderManageTitleBy = By.xpath("//a[@value='预约管理']");
	
	By creatOrderBy = By.xpath("//input[@value='生成订单']");
	
	By creatOrderSureBy = By.xpath("//a[contains(text(),'确定')]");
	
	By DDOrderNumBy = By.xpath("//a[contains(text(),'DD2021')]");
	By HTOrderNumBy = By.xpath("//em[contains(text(),'HT2021')]");
	
	/*
	 * 修改预约单信息(接下来预约单进行修改)
	 * */
	public void changeOrderMessage(int phoneindex){
		
		try {
			osorder = new OneShopOrder();
			
			eleClickBy(preorderListBy);
			String name = userNameGet();
			nameList.add(name);
			txtBoxSendValue(userphoneBy, phoneNumberList.get(phoneindex));
			osorder.setName(name);
			osorder.setPhoneNumber(phoneNumberList.get(phoneindex));
			eleClickBy(searchBy);
			Thread.sleep(3000);
			eleClickBy(preorderPageBy);
			eleClickBy(preOrderBy);
			txtBoxSendValue(usernameChangeBy,name);
			doOptionSelect(provinceBy,"0001");
			doOptionSelect(cityBy,"00010001");
			doOptionSelect(regionIdBy,"6");			
			doOptionSelect(areaCodeBy,"101");
			doOptionSelect(shopNoEditBy,"SS043");			
			eleClickBy(saveBtnBy);
			
			driver.navigate().back();
			driver.navigate().back();
			eleClickBy(creatOrderBy);
			eleClickBy(creatOrderSureBy);
			
			osorder.setDDOrder(eleTxtGet(DDOrderNumBy));
			osorder.setHTOrder(eleTxtGet(HTOrderNumBy));
		
			osorderList.add(osorder);
			
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	By addNewOrderBy = By.id("addButton");	//value="4"
	By mobileBy = By.id("mobile");
	By searchPayOrderBy = By.xpath("//input[@value='查询']");
	By checkBoxBy = By.name("checkBox");
	By surePayOrderBy = By.xpath("//input[@value='确认']");
	By addNewLineBy = By.xpath("//a[contains(text(),'增行')]");
	
	By paymentCategoryBy = By.id("paymentCategory");   //value="1"
	By paymentAmountBy = By.id("paymentAmount");   //1000
	By paymentTypeBy = By.id("paymentType");   //value="1"
	By subAddPayBtnBy = By.id("subAddPayBtn"); 
	By savePayOrderBy = By.xpath("//a[contains(text(),'保存')]");
	
	
	/*
	 * 订单交付定金
	 * 
	 * */
	
	public void payPreMoney(int phoneindex) throws Throwable{
		properties.load(inputStream);
		 String envirment = properties.getProperty("Environment");
		 
		toURL("https://fms."+envirment+".ikongjian.com/orderPayController/listPage");
		doOptionSelect(addNewOrderBy,"4");
		txtBoxSendValue(mobileBy, phoneNumberList.get(phoneindex));
		eleClickBy(searchPayOrderBy);
		eleClickBy(checkBoxBy);
		eleClickBy(surePayOrderBy);
		eleClickBy(addNewLineBy);
		doOptionSelect(paymentCategoryBy,"1");
		txtBoxSendValue(paymentAmountBy, "1000");
		doOptionSelect(paymentTypeBy,"1");
		eleClickBy(subAddPayBtnBy);
		eleClickBy(savePayOrderBy);
		
		
	}
	
	
	By orderMobileBy = By.id("mobile");
	By HTOrderPayBy = By.xpath("//input[@value='查询']");
	By DDOrderMesBy = By.xpath("//a[contains(text(),'DD2021')]");
	By preOnWorkBy = By.xpath("//a[contains(text(),'约合开工日期')]");
	By dateMoonBy = By.id("yuehePqYuefen"); //2021-08
	By dateMoonSureBy = By.xpath("//label[contains(text(),'排期旬')]");
	By dateSeBy = By.id("yuehePqXun"); //value="1"
//	By updateCouponBy = By.id("updateCoupon");
	By updateCouponBy = By.xpath("//form[@id='yueheForm']/descendant::div[2]/input");
	//Alert  
	By editOrderBy = By.xpath("//a[contains(text(),'订单修改')]");
	By communityBy = By.id("community"); //点击进入地图页面
	
	By mapFrameBy = By.id("mapIframe");
	By myPageTopBy = By.id("myPageTop");
	
	By tipinputBy = By.id("tipinput"); //育慧里
	By quitBtnBy = By.xpath("//a[contains(text(),'搜索')]");
	By queryAddressBy = By.xpath("//div[@class='amap-simple-marker-label' and contains(text(),'2')]");
	By sureMyButtonBy = By.xpath("//button[contains(text(),'确定')]");
	By buildingBy = By.id("building"); //5
	By unitBy = By.id("unit"); //4
	By roomBy = By.id("room"); //1101
	By houseCategoryBy = By.name("houseCategory");//value="1"
	By houseTypeBy = By.name("houseType");//value="HX004"
	By areaBy = By.name("area"); //90
	By housePropBy = By.name("houseProp"); //value="1"
	By changeHouseBy = By.xpath("//a[contains(text(),'调整房屋信息')]");
	By canBtnBy = By.id("canBtn");
	
	By allotDesignerBtnBy = By.id("allotDesignerBtn");
	By designChBy = By.xpath("//input[@placeholder='--请选择--']");
	By designerBy = By.xpath("//li[contains(text(),'张宁宁')]");
	By updateCouponButBy = By.xpath("//form[@id='allotDesignerForm']/div[2]/input"); //分配设计师
	By designersureBy = By.xpath("//a[contains(text(),'确定')]");
	
	/*
	 * 订单进行到待排雷
	 * 
	 * */
	
	public void moveTheOrder(int phoneindex){
		try {
			properties.load(inputStream);
			 String envirment = properties.getProperty("Environment");
			 
				toURL("https://ncrm."+envirment+".ikongjian.com/decorateOrder/list");
				txtBoxSendValue(orderMobileBy, phoneNumberList.get(phoneindex));
				//txtBoxSendValue(orderMobileBy,"18774691106");
				eleClickBy(HTOrderPayBy);
				eleClickBy(DDOrderMesBy);
				eleClickBy(preOnWorkBy);
				txtBoxSendValue(dateMoonBy, "2021-08");
				eleClickBy(dateMoonSureBy);
				doOptionSelect(dateSeBy,"1");
				driver.switchTo().defaultContent();
				eleClickBy(updateCouponBy);
				driver.switchTo().alert().accept();		
				eleClickBy(editOrderBy);
				eleClickBy(communityBy);
				driver.switchTo().frame(driver.findElement(mapFrameBy));
				txtBoxSendValue(tipinputBy, "育慧里");
				eleClickBy(quitBtnBy);
				eleClickBy(queryAddressBy);
				eleClickBy(sureMyButtonBy);
				txtBoxSendValue(buildingBy, "5");
				txtBoxSendValue(unitBy, "4");
				txtBoxSendValue(roomBy, "1101");
				doOptionSelect(houseCategoryBy,"1");
				doOptionSelect(houseTypeBy,"HX004");
				txtBoxSendValue(areaBy, "90");
				doOptionSelect(housePropBy,"1");
				eleClickBy(changeHouseBy);
				eleClickBy(canBtnBy);
				
				eleClickBy(allotDesignerBtnBy);
				eleClickBy(designChBy);
				eleClickBy(designerBy);
			//	System.out.println(eleAttrValueGet(updateCouponButBy,"innerHTML"));
				eleClickBy(updateCouponButBy);
				eleClickBy(designersureBy);
				Thread.sleep(1000);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	Thread.currentThread().interrupt();
		}		
		
	}
	
	
	By logoutBy = By.xpath("//a[contains(text(),'退出')]");	
	public void doLogOut() throws Throwable{
		properties.load(inputStream);
		String envirment = properties.getProperty("Environment");
		toURL("https://ncrm."+envirment+".ikongjian.com/decorateOrder/list");
		eleClickBy(logoutBy);
	}
}
