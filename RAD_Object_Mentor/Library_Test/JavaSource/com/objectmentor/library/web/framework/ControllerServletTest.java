package com.objectmentor.library.web.framework;

import com.objectmentor.library.web.framework.mocks.*;
import junit.framework.TestCase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ControllerServletTest extends TestCase {
	private ControllerServlet servlet;

	private MockHttpServletRequest request;

	private MockHttpServletResponse response;

	private boolean servletException;

	protected void setUp() throws Exception {
		super.setUp();
		servlet = new ControllerServlet() {
			private static final long serialVersionUID = 8507980800911525485L;

			protected ControllerFinder getControllerFinder() {
				return new ControllerFinder(
						"com.objectmentor.library.web.framework");
			}

			protected void initializeServlet(HttpServletRequest request) {
			}
		};
		response = new MockHttpServletResponse();
		servletException = false;
	}

	public void testDoGet() throws Exception {
		request = new MockHttpServletRequest("GET", "/Library",
				"/Library/test/action.do");
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			servletException = true;
		}
		assertTrue(servletException);
		assertTrue(TestController.getWasCalled);
		assertSame(TestController.theRequest, request);
		assertEquals("action", request.getAttribute("action_name"));
	}

	public void testDoPost() throws Exception {
		request = new MockHttpServletRequest("POST", "/Library",
				"/Library/test/action.do");
		try {
			servlet.doPost(request, response);
		} catch (ServletException e) {
			servletException = true;
		}
		assertTrue(servletException);
		assertTrue(TestController.postWasCalled);
		assertSame(TestController.theRequest, request);
		assertEquals("action", request.getAttribute("action_name"));
	}

}
