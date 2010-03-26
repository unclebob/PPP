<%@ page isErrorPage="true" %>
<h1 class="header"><font color="red">Error</font></h1>
<div class="error">
<%= request.getAttribute("errorMessage") %><br/>
</div>
<table colspan="1">
<tr><td align="right">Request URL:</td><td><%= request.getRequestURL() %></td></tr>
<tr><td align="right">Parameters:</td>
<td>
<table border="1">
<% java.util.Enumeration names = request.getParameterNames();
   while (names.hasMoreElements()) {
   	String key   = (String) names.nextElement();
   	String value = request.getParameter(key); %>
<tr><td align="right"><%= key %></td><td><%= value %></td></tr>
<% } %>
</table>
</td></tr>
</table>