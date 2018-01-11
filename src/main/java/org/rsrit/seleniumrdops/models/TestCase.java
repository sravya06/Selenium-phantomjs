package org.rsrit.seleniumrdops.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestCase {

	@JsonProperty(value = "nameOfTheTest")
	private String nameOfTheTest;

	public String getNameOfTheTest() {
		return nameOfTheTest;
	}

	public void setNameOfTheTest(String nameOfTheTest) {
		this.nameOfTheTest = nameOfTheTest;
	}

	@JsonProperty(value = "targetUrl")
	private String url;

	@JsonProperty(value = "typeOfTest")
	private String typeOfTest;

	@JsonProperty(value = "paths")
	private Map<String, String> webElements;

	@JsonProperty(value = "keys")
	private Map<String, String> keyValues;

	public Map<String, String> getXpathOfClickablesIfAny() {
		return xpathOfClickablesIfAny;
	}

	public void setXpathOfClickablesIfAny(Map<String, String> xpathOfClickablesIfAny) {
		this.xpathOfClickablesIfAny = xpathOfClickablesIfAny;
	}

	@JsonIgnore
	private Map<String, String> xpathOfClickablesIfAny;

	public Map<String, String> getValueOfElements() {
		return keyValues;
	}

	public void setValueOfElements(Map<String, String> valueOfElements) {
		this.keyValues = valueOfElements;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTypeOfTest() {
		return typeOfTest;
	}

	public void setTypeOfTest(String typeOfTest) {
		this.typeOfTest = typeOfTest;
	}

	public Map<String, String> getListOfElements() {
		return webElements;
	}

	public void setListOfElements(Map<String, String> listOfElements) {
		this.webElements = listOfElements;
	}

	public String toString() {
		return this.getUrl() + " " + this.getTypeOfTest();
	}

	public TestCase(String nameofTheTest, String url, String typeOfTest, Map<String, String> webElements,
			Map<String, String> keyValues, Map<String, String> xpathOfClickablesIfAny) {
		super();
		this.nameOfTheTest = nameofTheTest;
		this.url = url;
		this.typeOfTest = typeOfTest;
		this.webElements = webElements;
		this.keyValues = keyValues;
		this.xpathOfClickablesIfAny = xpathOfClickablesIfAny;
	}

	public TestCase() {

	}

}
