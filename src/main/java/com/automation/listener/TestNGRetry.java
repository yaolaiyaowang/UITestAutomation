package com.automation.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;


/*
 * 
 * 定时失败后的重试次数，放在监听器之中
 * 
 * */
public class TestNGRetry implements IRetryAnalyzer {
	
    Log4jLogger logger = new Log4jLogger();
    
    private int maxRetryCount = 0;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            String message = "Running retry for '" + result.getName()
                    + "' on class " + this.getClass().getName() + " Retrying "
                    + retryCount + " times";
            logger.info(message);
            Reporter.setCurrentTestResult(result);
            Reporter.log("RunCount=" + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }
}
