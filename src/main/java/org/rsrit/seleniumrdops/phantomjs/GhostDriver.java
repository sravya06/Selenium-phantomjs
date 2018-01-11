package org.rsrit.seleniumrdops.phantomjs;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.IOUtils;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.rsrit.seleniumrdops.models.TestCase;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GhostDriver {

	private static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

	static {
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setCapability("takesScreenshot", true);
	}

	public PhantomJSDriver phantomJsDriver() {
		return new PhantomJSDriver(desiredCapabilities);
	}

	public byte[] getScreenshotAsBytes(TestCase testCase, CaptureConfig captureConfig, String testType)
			throws IOException {
		if (testType.equals("byXpath")) {
			return performTheTestByXpath(testCase, captureConfig);
		} else {
			return null;
		}
	}

	public byte[] getScreenshotAsBufferedImage(TestCase testCase, CaptureConfig captureConfig) throws IOException {
		byte[] bytes = getScreenshotAsBytes(testCase, captureConfig, testCase.getTypeOfTest());

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

		try {
			ImageIO.write(ImageIO.read(byteArrayInputStream), "png",
					new File("D:/phantomjs-2.1.1-windows/bin/screenDimensions.png"));
			return bytes;
		} catch (IOException e) {
			// ignored
		}

		return null;
	}

	public byte[] getScreenshotAsBufferedImage(TestCase testCase) throws IOException {
		CaptureConfig captureConfig = new CaptureConfig();
		captureConfig.setWidth(1366);
		captureConfig.setHeight(768);

		return getScreenshotAsBufferedImage(testCase, captureConfig);
	}

	public byte[] performTheTestByXpath(TestCase testCase, CaptureConfig captureConfig) throws IOException {
		WebDriver webDriver = new PhantomJSDriver(desiredCapabilities);
		webDriver.get(testCase.getUrl());
		System.out.println(webDriver.getPageSource());
		if (captureConfig.getX() > 0 || captureConfig.getY() > 0) {
			webDriver.manage().window().setPosition(new Point(captureConfig.getX(), captureConfig.getY()));
		}

		if (captureConfig.getWidth() > 0 && captureConfig.getHeight() > 0) {
			webDriver.manage().window().setSize(new Dimension(captureConfig.getWidth(), captureConfig.getHeight()));
		}

		int index = 0;
		System.out.println("Opening Application URL");

		List<String> valuesList = new ArrayList<String>();
		List<String> xpathsList = new ArrayList<String>();

		// adding WebElements into list there by fetching through xpaths
		if (testCase.getTypeOfTest().equals("byXpath")) {
			for (String xpath : testCase.getListOfElements().keySet()) {
				xpathsList.add(testCase.getListOfElements().get(xpath));
			}
		}

		// adding Values of WebElements into List
		for (String values : testCase.getValueOfElements().keySet()) {
			valuesList.add(testCase.getValueOfElements().get(values));
		}

		// Sending Keys
		// Here we need to check if any xpath is a Clickables
		// Now we need to send keys according to the order i.e., if clickable
		// occurs before sending do a click then continue
		for (String xpath : xpathsList) {
			WebElement webElement = webDriver.findElement(By.xpath(xpath));
			if (valuesList.get(index).equals("click") && index == valuesList.size() - 1) {
				webDriver.findElement(By.xpath(xpath)).click();
				System.out.println("Test Passed!!");
			} else if (valuesList.get(index).equals("click")) {
				webDriver.findElement(By.xpath(xpath)).click();

				System.out.println(webDriver.getCurrentUrl());
				index++;
			} else {
				webElement.sendKeys(valuesList.get(index));
				System.out.println("Sent!!");
				index++;
			}
		}

		try {
			String cssFontRule = IOUtils.readFully(new ClassPathResource("/phantomjs/cssFontRule.js").getInputStream());
			((JavascriptExecutor) webDriver).executeScript(cssFontRule);
			Thread.sleep(1000);
		} catch (Exception e) {
			// ignored
		}

		byte[] bytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
		webDriver.quit();

		return bytes;

	}

	public byte[] performTheTestByElementId(TestCase testCase, CaptureConfig captureConfig) throws IOException {
		WebDriver webDriver = new PhantomJSDriver(desiredCapabilities);
		webDriver.get(testCase.getUrl());

		if (captureConfig.getX() > 0 || captureConfig.getY() > 0) {
			webDriver.manage().window().setPosition(new Point(captureConfig.getX(), captureConfig.getY()));
		}

		if (captureConfig.getWidth() > 0 && captureConfig.getHeight() > 0) {
			webDriver.manage().window().setSize(new Dimension(captureConfig.getWidth(), captureConfig.getHeight()));
		}

		int index = 0;
		System.out.println("Opening Application URL");

		List<String> valuesList = new ArrayList<String>();
		List<String> elementIdList = new ArrayList<String>();

		// adding WebElements into list there by fetching through element Id's
		for (String elementId : testCase.getListOfElements().keySet()) {
			elementIdList.add(testCase.getListOfElements().get(elementId));
		}

		// adding Values of WebElements into List
		for (String values : testCase.getValueOfElements().keySet()) {
			valuesList.add(testCase.getValueOfElements().get(values));
		}

		// Sending Keys
		// Here we need to check if any xpath is a Clickables
		// Now we need to send keys according to the order i.e., if clickable
		// occurs before sending do a click then continue
		for (String elementId : elementIdList) {
			WebElement webElement = webDriver.findElement(By.id(elementId));
			if (valuesList.get(index).equals("click") && index == valuesList.size() - 1) {
				webDriver.findElement(By.id(elementId)).click();

				System.out.println("Test Passed!!");
			} else if (valuesList.get(index).equals("click")) {
				webDriver.findElement(By.id(elementId)).click();

				System.out.println(webDriver.getCurrentUrl());
				index++;
			} else {
				webElement.sendKeys(valuesList.get(index));
				System.out.println("Sent!!");
				index++;
			}
		}

		try {
			String cssFontRule = IOUtils.readFully(new ClassPathResource("/phantomjs/cssFontRule.js").getInputStream());
			((JavascriptExecutor) webDriver).executeScript(cssFontRule);
			Thread.sleep(1000);
		} catch (Exception e) {
			// ignored
		}

		byte[] bytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
		webDriver.quit();

		return bytes;

	}

}
