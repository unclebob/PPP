<%@ page import="com.objectmentor.library.web.controller.ActionResult" %>
<%
ActionResult result = (ActionResult) request.getAttribute("result");
if (result != null && !result.empty()) {
	String error = result.getErrorMessage();
	if (error.length() > 0) {
%>
	  <div class="error"><%= error %></div>
<%
  }
	String warning = result.getWarningMessage();
	if (warning.length() > 0) {
%>
	  <div class="warning"><%= warning %></div>
<%
  }
  String info = result.getInfoMessage();
	if (info.length() > 0) {
%>
	  <div class="info"><%= info %></div>
<%
  }
}
%>
