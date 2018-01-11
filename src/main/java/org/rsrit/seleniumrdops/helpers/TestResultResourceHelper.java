package org.rsrit.seleniumrdops.helpers;

import org.rsrit.seleniumrdops.models.TestResult;
import org.springframework.stereotype.Component;

@Component
public class TestResultResourceHelper {

	public TestResult getTestResultresource(String nameOfTheTest, String statusOfTest,
			String base64EquivalentOfLastScreenShot, long timeTakenToRunTheTest) {

		return new TestResult(nameOfTheTest, statusOfTest, base64EquivalentOfLastScreenShot, timeTakenToRunTheTest);
	}

}
