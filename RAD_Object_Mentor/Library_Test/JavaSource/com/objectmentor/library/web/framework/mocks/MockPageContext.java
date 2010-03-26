package com.objectmentor.library.web.framework.mocks;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import java.io.IOException;
import java.util.Enumeration;

public class MockPageContext extends PageContext {

  private final JspWriter out;
  private HttpServletRequest request;

  public MockPageContext(JspWriter out) {
    this.out = out;
    request = new MockHttpServletRequest();
  }

  public Object findAttribute(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public void forward(String arg0) throws ServletException, IOException {
    // TODO Auto-generated method stub

  }

  public Object getAttribute(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object getAttribute(String arg0, int arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  public Enumeration getAttributeNamesInScope(int arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  public int getAttributesScope(String arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  public Exception getException() {
    // TODO Auto-generated method stub
    return null;
  }

  public JspWriter getOut() {
    return out;
  }

  public Object getPage() {
    // TODO Auto-generated method stub
    return null;
  }

  public ServletRequest getRequest() {
    return request;
  }

  public ServletResponse getResponse() {
    // TODO Auto-generated method stub
    return null;
  }

  public ServletConfig getServletConfig() {
    // TODO Auto-generated method stub
    return null;
  }

  public ServletContext getServletContext() {
    // TODO Auto-generated method stub
    return null;
  }

  public HttpSession getSession() {
    // TODO Auto-generated method stub
    return null;
  }

  public void handlePageException(Exception arg0) throws ServletException, IOException {
    // TODO Auto-generated method stub

  }

  public void handlePageException(Throwable arg0) throws ServletException, IOException {
    // TODO Auto-generated method stub

  }

  public void include(String arg0) throws ServletException, IOException {
    // TODO Auto-generated method stub

  }

  public void initialize(Servlet arg0, ServletRequest arg1, ServletResponse arg2, String arg3, boolean arg4, int arg5, boolean arg6) throws IOException, IllegalStateException, IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  public void release() {
    // TODO Auto-generated method stub

  }

  public void removeAttribute(String arg0) {
    // TODO Auto-generated method stub

  }

  public void removeAttribute(String arg0, int arg1) {
    // TODO Auto-generated method stub

  }

  public void setAttribute(String arg0, Object arg1) {
    // TODO Auto-generated method stub

  }

  public void setAttribute(String arg0, Object arg1, int arg2) {
    // TODO Auto-generated method stub

  }

@Override
public void include(String arg0, boolean arg1) throws ServletException,
		IOException {
	// TODO Auto-generated method stub
	
}

@Override
public ExpressionEvaluator getExpressionEvaluator() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public VariableResolver getVariableResolver() {
	// TODO Auto-generated method stub
	return null;
}

}
