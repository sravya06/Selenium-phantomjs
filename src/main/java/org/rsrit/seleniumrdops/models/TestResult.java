package org.rsrit.seleniumrdops.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestResult {

	@JsonProperty(value = "nameOfTheTest")
	private String nameOfTheTest;

	@JsonProperty(value = "status")
	private String statusOfTest;

	@JsonProperty(value = "encodeImage")
	private String base64EquivalentOfLastScreenShot;

	@JsonProperty(value = "run_time")
	private long timeTakenToRunTheTest;

	public String getNameOfTheTest() {
		return nameOfTheTest;
	}

	public void setNameOfTheTest(String nameOfTheTest) {
		this.nameOfTheTest = nameOfTheTest;
	}

	public String getStatusOfTest() {
		return statusOfTest;
	}

	public void setStatusOfTest(String statusOfTest) {
		this.statusOfTest = statusOfTest;
	}

	public String getBase64EquivalentOfLastScreenShot() {
		return base64EquivalentOfLastScreenShot;
	}

	public void setBase64EquivalentOfLastScreenShot(String base64EquivalentOfLastScreenShot) {
		this.base64EquivalentOfLastScreenShot = base64EquivalentOfLastScreenShot;
	}

	public long getTimeTakenToRunTheTest() {
		return timeTakenToRunTheTest;
	}

	public void setTimeTakenToRunTheTest(long timeTakenToRunTheTest) {
		this.timeTakenToRunTheTest = timeTakenToRunTheTest;
	}

	public TestResult(String nameOfTheTest, String statusOfTest, String base64EquivalentOfLastScreenShot,
			long timeTakenToRunTheTest) {
		super();
		this.nameOfTheTest = nameOfTheTest;
		this.statusOfTest = statusOfTest;
		this.base64EquivalentOfLastScreenShot = base64EquivalentOfLastScreenShot;
		this.timeTakenToRunTheTest = timeTakenToRunTheTest;
	}

}
