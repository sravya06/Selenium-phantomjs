package org.rsrit.seleniumrdops;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.rsrit.seleniumrdops.helpers.TestResultResourceHelper;
import org.rsrit.seleniumrdops.models.TestCase;
import org.rsrit.seleniumrdops.models.TestResult;
import org.rsrit.seleniumrdops.phantomjs.GhostDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private GhostDriver ghostDriver;

	@Autowired
	private TestResultResourceHelper testResultResourceHelper;

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public TestResult getImage(@RequestBody TestCase testCase) throws IOException {

		byte[] imageData = ghostDriver.getScreenshotAsBufferedImage(testCase);
		String imageBase64StringContent = Base64.encodeBase64String(imageData);
		TestResult testResult = testResultResourceHelper.getTestResultresource(testCase.getNameOfTheTest(), "Passed",
				imageBase64StringContent, new Date().getTime());
		return testResult;
	}

}
