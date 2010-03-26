package com.objectmentor.library.web.tags;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class ActionPathTag implements Tag, Serializable {

  private static final long serialVersionUID = 3729310680219714575L;

  private PageContext pageContext = null;
  private Tag parent = null;
  private String actionName = null;

  public void setPageContext(PageContext pageContext) {
    this.pageContext = pageContext;
  }

  public void setParent(Tag tag) {
    parent = tag;
  }

  public Tag getParent() {
    return parent;
  }

  public void setActionName(String name) {
    this.actionName = name;
  }

  public String getActionName() {
    return actionName;
  }

  public int doStartTag() throws JspException {
    try {
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest(); 
      pageContext.getOut().write(request.getContextPath() + "/" + actionName + ".do");
    } catch (IOException e) {
      throw new JspTagException("An IOException occurred.");
    }
    return SKIP_BODY;
  }

  public int doEndTag() throws JspException {
    return EVAL_PAGE;
  }

  public void release() {
    pageContext = null;
    parent = null;
    actionName = null;
  }
}