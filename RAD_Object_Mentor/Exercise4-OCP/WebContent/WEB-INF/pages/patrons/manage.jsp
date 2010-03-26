<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%@ page import="com.objectmentor.library.models.Patron" %>
<%@ page import="java.util.*" %>
<h1>Patron List</h1>
<form action="<library:actionPath actionName="patrons/manage"/>" method="post">
<table class="list">
  <%
  Collection patrons = (Collection) request.getAttribute("patrons"); 
  %>
  <tr class="head">
  	<th><%= (patrons != null && patrons.size() > 0) ? "Delete?" : "&nbsp;" %></th>
    <th>ID</th>
    <th>First <font color="red">*</font></th>
    <th>M.I.</th>
    <th>Last <font color="red">*</font></th>
  </tr>
	<% 
  if (patrons != null && patrons.size() > 0) { 
	%>
  <tr class="head">
  	<td align="center"><input type="checkbox" name="all" value="all" onclick="click_or_uncheck_all(this);" alt="delete all"/> all</td>
    <td colspan="4" align="left">&nbsp;</td>
  </tr>
  <%
  }
  final String evenOdd[] = new String[] { "odd", "even" };
  int count = 0;
  for (Iterator i = patrons.iterator(); i.hasNext();) {
    Patron patron = (Patron) i.next();
  %>
  <tr class="<%= evenOdd[count++ % 2] %>">
  	<td align="center">
  	 	<input type="checkbox" name='<%= "delete_"+patron.getId() %>' />
  	</td>
    <td>
      <%= patron.getId() %>
    </td>
    <td>
    	<input type="text" size="35" name='<%= "firstName_"+patron.getId() %>' value="<%=patron.getFirstName()%>" />
    </td>
    <td>
    	<input type="text" size="1" name='<%= "middleInitial_"+patron.getId() %>' value="<%=patron.getMiddleInitial()%>" />
    </td>
    <td>
    	<input type="text" size="35" name='<%= "lastName_"+patron.getId() %>' value="<%=patron.getLastName()%>" />
    </td>
  </tr>
<% } %>
  <tr>
  	<td colspan="2">
  		&nbsp;
    </td>
    <td>
    	<input type="text" size="35" name="newFirstName" value="(new)" onfocus="clear_value_on_first_focus(this);" />
    </td>
    <td>
    	<input type="text" size="1" name="newMiddleInitial" />
    </td>
    <td>
    	<input type="text" size="35" name="newLastName" />
    </td>
  </tr>
  <tr class="head">
    <td colspan="2">&nbsp;</td>
    <td colspan="3"><input type="submit" name="Submit" value="Submit"/></td>
  </tr>
</table>
<br/>
<b><font color="red">*</font></b> Indicates a required field.
</form>
