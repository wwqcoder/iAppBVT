package com.hogwarts.base;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidUITasks {

    private static Logger logger = Logger.getLogger(AndroidUITasks.class);

    public static boolean isRecordEvenBtnExist(AppiumDriver driver) throws Exception {
        boolean result = false;
        List<WebElement> btnEles =
                findObjectsByXPath("//android.widget.Button[contains(@resource-id,'id/trackEventButton')]", driver);
        if (btnEles != null && btnEles.size() > 0) {
            result = true;
            logger.info("按钮元素可以被定位成功！");
        }

        return result;
    }


    /**
     * 找元素，固定最长等待15秒
     *
     * @param xpath
     * @param driver
     * @return
     * @throws UINotFoundException
     */
    private static List<WebElement> findObjectsByXPath(String xpath, AppiumDriver driver) throws UINotFoundException {
        return findObjectsByXPath(xpath, driver, 5);
    }

    /**
     * 找元素
     *
     * @param xpath   元素的xpath定位
     * @param driver  appium driver
     * @param waitMax 最长等待秒数
     * @return
     * @throws UINotFoundException
     */
    private static List<WebElement> findObjectsByXPath(String xpath, AppiumDriver driver, int waitMax) throws UINotFoundException {
        int size = 0;
        List<WebElement> objs = null;
        long start = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while (((now - start) < waitMax * 1000) && (size == 0)) {
            Tools.wait(1);
            objs = driver.findElementsByXPath(xpath);
            if (objs != null) {
                size = objs.size();
            }
            now = System.currentTimeMillis();
        }

        if (size == 0) {
            throw new UINotFoundException("Could not find the element which located by " + xpath);
        } else {
            return objs;
        }
    }
}
