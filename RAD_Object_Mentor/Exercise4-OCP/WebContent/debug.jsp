<!--
To see this content, invoke any JSP with "?debug" appended to it.
To turn it off again, use "?debug=0" or "?debug=false".
-->
<%
  String debug = request.getParameter("debug");
  if (debug != null) {
    if (debug.equals("0") || debug.equals("false"))
      request.getSession().removeAttribute("debug");
    else
      request.getSession().setAttribute("debug", debug);
  }
  if (request.getSession().getAttribute("debug") != null) {
%>
<table border="1">
  <tr>
    <td>HandlerResult</td>
    <td><%= request.getAttribute("result") %>
    </td>
  </tr>
  <tr>
    <td>content jsp</td>
    <td><%= (String) request.getAttribute("include_path") %>
    </td>
  </tr>
  <tr>
    <td>getAuthType()</td>
    <td><%= request.getAuthType() %>
    </td>
  </tr>
  <tr>
    <td>getContextPath()</td>
    <td><%= request.getContextPath() %>
    </td>
  </tr>
  <tr>
    <td>getCookies()</td>
    <td><%= request.getCookies().toString() %>
    </td>
  </tr>
  <%--
  <tr><td>getDateHeader()</td><td><%= request.getDateHeader().toString() %></td></tr>
  <tr><td>getHeader()</td><td><%= request.getHeader().toString() %></td></tr>
  <tr><td>getHeaderNames()</td><td><%= request.getHeaderNames().toString() %></td></tr>
  <tr><td>getHeaders()</td><td><%= request.getHeaders().toString() %></td></tr>
  <tr><td>getIntHeader()</td><td><%= request.getIntHeader().toString() %></td></tr>
  --%>
  <tr>
    <td>getMethod()</td>
    <td><%= request.getMethod() %>
    </td>
  </tr>
  <tr>
    <td>getPathInfo()</td>
    <td><%= request.getPathInfo() %>
    </td>
  </tr>
  <tr>
    <td>getPathTranslated()</td>
    <td><%= request.getPathTranslated() %>
    </td>
  </tr>
  <tr>
    <td>getQueryString()</td>
    <td><%= request.getQueryString() %>
    </td>
  </tr>
  <tr>
  	<td>Parameters:</td>
  	<td>
  		<table border="1" bgcolor="linen">
  			<tr><th>Name</th><th>Value</th></tr>
  			<% for (java.util.Enumeration e = request.getParameterNames(); e.hasMoreElements(); ) {
  					String name = (String) e.nextElement(); 
  			%>
  			<tr><td><%= name %></td><td><%= com.objectmentor.library.web.servlet.ServletHelper.arrayToString(request.getParameterValues(name))	%></td></tr>
  			<% } %>
  		</table>
  <tr>
    <td>getRemoteUser()</td>
    <td><%= request.getRemoteUser() %>
    </td>
  </tr>
  <tr>
    <td>getRequestedSessionId()</td>
    <td><%= request.getRequestedSessionId() %>
    </td>
  </tr>
  <tr>
    <td>getRequestURI()</td>
    <td><%= request.getRequestURI() %>
    </td>
  </tr>
  <tr>
    <td>getRequestURL()</td>
    <td><%= request.getRequestURL().toString() %>
    </td>
  </tr>
  <tr>
    <td>getServletPath()</td>
    <td><%= request.getServletPath() %>
    </td>
  </tr>
  <tr>
    <td>getSession()</td>
    <td><%= request.getSession().toString() %>
    </td>
  </tr>
  <tr>
    <td>isRequestedSessionIdFromURL()</td>
    <td><%= request.isRequestedSessionIdFromCookie() %>
    </td>
  </tr>
  <tr>
    <td>isRequestedSessionIdValid()</td>
    <td><%= request.isRequestedSessionIdValid() %>
    </td>
  </tr>
  <tr>
    <td>isUserInRole("admin")</td>
    <td><%= request.isUserInRole("admin") %>
    </td>
  </tr>
</table>
<% } %>