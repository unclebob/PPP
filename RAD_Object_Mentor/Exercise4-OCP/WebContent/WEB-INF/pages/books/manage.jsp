<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%@ page import="com.objectmentor.library.models.MediaCopy" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>

<h1>Manage the Book Catalog</h1>

<form action="<library:actionPath actionName="books/manage"/>" method="post">
  <table class="list">
  <%
  final String evenOdd[] = new String[] { "odd", "even" };
  List isbns = (List) request.getAttribute("isbns"); 
  if (isbns != null && isbns.size() > 0) {
  %>
  <%--
  <tr class="head">
  	<th align="center"><input type="checkbox" name="all" value="all" onclick="click_or_uncheck_all(this);" alt="delete all"/> all</th>
    <td colspan="2" align="left">&nbsp;</td>
  </tr>
  --%>
  <%
  }
  for (Iterator i = isbns.iterator(); i.hasNext();) {
    String isbn = (String) i.next();
  %>
  <tr class="head">
  	<th colspan="3">ISBN: <%= isbn %></th>
  </tr>
  <tr>
  	<th>Delete?</th>
    <th>Copy ID</th>
    <th>Title</th>
  </tr>
	<%
	  int count = 0;
 		List copies = (List) request.getAttribute("copies_"+isbn);
  	for (Iterator i2 = copies.iterator(); i2.hasNext();) {
    	MediaCopy copy = (MediaCopy) i2.next();
  %>
  <tr class="<%= evenOdd[count++ % 2] %>">
  	<td align="center">
  	 	<input type="checkbox" name='<%= "delete_"+copy.getId() %>' />
  	</td>
    <td>
      <%= copy.getId() %>
    </td>
    <td>
    	<%= copy.getMedia().getTitle() %>
    </td>
  </tr>
	<% } %>
  <tr class="<%= evenOdd[count++ % 2] %>">
  	<td>&nbsp;</td>
  	<td>Add New Copies: </td>
  	<td>
  	 	<input type="text" size="15" value="(number of copies)" onfocus="clear_value_on_first_focus(this);" name='<%= "newCopies_"+isbn %>' />
  	</td>
  </tr>
  <tr><td colspan="3">&nbsp;</td></tr>
<% } %>
  <tr>
  	<td colspan="2" align="right">New ISBN:</td>
  	<td>
    	<input type="text" size="35" name="newIsbn" value="" />
  	</td>
  </tr>
  <tr class="head">
    <td>&nbsp;</td>
    <td colspan="2"><input type="submit" name="Submit" value="Submit"/></td>
  </tr>
  </table>
</form>
