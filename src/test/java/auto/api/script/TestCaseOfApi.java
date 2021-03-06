package auto.api.script;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.automation.exception.FrameworkException;
import com.automation.framework.WebTestNGBase;

import static io.restassured.RestAssured.given;
import io.restassured.response.*;

public class TestCaseOfApi extends WebTestNGBase{

	@Test(alwaysRun=true, groups={"auto.demo.test.api"}, timeOut=MAX_EXCUTE_TIME)
	public void caseOfAMapApi()throws FrameworkException{
		try{
			caseName = "高德接口测试";
			whichCaseIsRun(caseName);
			boolean result = false;
			
	        Response response = given()
	                .param("longitude", 121.04925573429551)
	                .param("latitude", 31.315590522490712)
	                .get("https://ditu.amap.com/service/regeo");

	        // 打印出 response 的body
	        response.prettyPrint();
	        result = response.jsonPath().get("data.districtadcode").equals("320583");
	        
			assertEquals(result, true);
		}catch (Throwable e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
	}
}