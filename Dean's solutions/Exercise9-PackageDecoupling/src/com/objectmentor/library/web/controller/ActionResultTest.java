package com.objectmentor.library.web.controller;

import junit.framework.TestCase;

public class ActionResultTest extends TestCase {

	public void test3ArgHandlerResultConstructorSetsAllMessages() {
		ActionResult result = new ActionResult("info", "warning", "error");
		assertEquals("info", result.getInfoMessage());
		assertEquals("warning", result.getWarningMessage());
		assertEquals("error", result.getErrorMessage());
	}

	public void test2ArgHandlerResultConstructorSetsInfoAndWarningMessages() {
		ActionResult result = new ActionResult("info", "warning");
		assertEquals("info", result.getInfoMessage());
		assertEquals("warning", result.getWarningMessage());
		assertEquals("", result.getErrorMessage());
	}

	public void test1ArgHandlerResultConstructorSetsInfoMessageOnly() {
		ActionResult result = new ActionResult("info");
		assertEquals("info", result.getInfoMessage());
		assertEquals("", result.getWarningMessage());
		assertEquals("", result.getErrorMessage());
	}

	public void testDefaultHandlerResultConstructorSetsNoMessages() {
		ActionResult result = new ActionResult();
		assertTrue(result.empty());
		assertEquals("", result.getInfoMessage());
		assertEquals("", result.getWarningMessage());
		assertEquals("", result.getErrorMessage());
	}

	public void testAppendToInfoMessage() {
		ActionResult result = new ActionResult("info");
		result.appendToInfoMessage("rmation");
		assertEquals("information<br/>", result.getInfoMessage());
		assertEquals("", result.getWarningMessage());
		assertEquals("", result.getErrorMessage());
	}

	public void testAppendToWarningMessage() {
		ActionResult result = new ActionResult("info", "warn");
		result.appendToWarningMessage("ing");
		assertEquals("warning<br/>", result.getWarningMessage());
		assertEquals("info", result.getInfoMessage());
		assertEquals("", result.getErrorMessage());
	}

	public void testAppendToErrorMessage() {
		ActionResult result = new ActionResult("info", "warn", "err");
		result.appendToErrorMessage("or");
		assertEquals("error<br/>", result.getErrorMessage());
		assertEquals("info", result.getInfoMessage());
		assertEquals("warn", result.getWarningMessage());
	}

	public void testEmpty() {
		assertTrue(new ActionResult().empty());
	}

	public void testMakeErrorHandlerResult() {
		ActionResult result = ActionResult.makeErrorHandlerResult("error");
		assertEquals("", result.getInfoMessage());
		assertEquals("", result.getWarningMessage());
		assertEquals("error", result.getErrorMessage());
	}

	public void testMakeWarningHandlerResult() {
		ActionResult result = ActionResult.makeWarningHandlerResult("warning");
		assertEquals("", result.getInfoMessage());
		assertEquals("warning", result.getWarningMessage());
		assertEquals("", result.getErrorMessage());
	}

	public void testMakeInfoHandlerResult() {
		ActionResult result = ActionResult.makeInfoHandlerResult("info");
		assertEquals("info", result.getInfoMessage());
		assertEquals("", result.getWarningMessage());
		assertEquals("", result.getErrorMessage());
	}

}
