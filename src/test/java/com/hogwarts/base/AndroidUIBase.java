package com.hogwarts.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JiZhi.Qian on 2019/3/11.
 */
public abstract class AndroidUIBase {
    private Logger logger = Logger.getLogger(AndroidUIBase.class);
    private String appiumURL = "http://127.0.0.1:4723/wd/hub"; //appium 服务URL地址

    private String platformName = "Android"; //平台名称
    private String deviceName = "Android Emulator"; //设备名称(可以是假的)
    private String appPackage = "com.appsflyer.androidsampleapp";//安卓应用包名
    private String appActivity = ".MainActivity"; //安卓activity类
    private String androidUid = Tools.getAndroidDeviceId(); //安卓设备Uid(不能是假的)
    private String platformVersion = Tools.getDeviceRelease(androidUid); //安卓设备平台版本

    protected AppiumDriver driver;
    protected String testcaseName = "";

    @Before
    public void begin() throws MalformedURLException {
        //设置Desired Capabilities相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);

        //设置安卓系统uid
        capabilities.setCapability("deviceName", androidUid);
        capabilities.setCapability("udid", androidUid);

        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        logger.info("创建drvier实例 ...");
    }

    @After
    public void tearDown() {
        logger.info("自动化测试" + testcaseName + "结束。");

        if (driver == null) {
            return;
        }

        driver.quit();
    }


}
