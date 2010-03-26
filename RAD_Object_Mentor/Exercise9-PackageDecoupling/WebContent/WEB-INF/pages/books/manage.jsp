<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%@ page import="com.objectmentor.library.models.MediaCopy" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>

<h1>Manage the Book Catalog</h1>

<form action="<library:actionPath actionName="books/manage"/>" method="post" id="list_form">
  <table class="list">
  <%
  final String evenOdd[] = new String[] { "odd", "even" };
  Map isbnTitleMap = (Map) request.getAttribute("isbnTitleMap");
  if (isbnTitleMap.size() > 0) {
  %>
  <tr class="head">
    <th class="head" valign="bottom" align="left">Copy IDs<br/>(Checked IDs will be deleted when you submit.)</th>
  	<th class="head" valign="bottom" align="center">Delete<br/>All?</th>
    <th class="head" valign="bottom" align="center">Add<br/>Copies</th>
  </tr>
  <%
  }
  int count = 0;
  for (Iterator i = isbnTitleMap.entrySet().iterator(); i.hasNext();) {
    Entry  entry = (Entry)  i.next();
    String isbn  = (String) entry.getKey();
    String title = (String) entry.getValue();
  %>
  <tr class="<%= evenOdd[count % 2] %>">
  	<td colspan="3">ISBN <%= isbn %>:&nbsp;&nbsp;"<%= title %>"</td>
  </tr>
  <tr class="<%= evenOdd[count++ % 2] %>">
    <td width="100%">
	<%
 		List copies = (List) request.getAttribute("copies_"+isbn);
  	for (Iterator i2 = copies.iterator(); i2.hasNext();) {
    	MediaCopy copy = (MediaCopy) i2.next();
  %>
			<%-- Note that the id has a format needed by the javascript, while the name has a format required by the server-side java. --%>
      <%= copy.getId() %>:&nbsp;<input type="checkbox" id='<%= "delete_"+isbn+"_"+copy.getId() %>' name='<%= "delete_"+copy.getId() %>' />&nbsp;
	<% } %>
    </td>
  	<td align="center">
  	 	<input type="checkbox" onclick='click_or_uncheck_all(this, "delete_<%=isbn%>");' />
  	</td>
  	<td align="left">
  	 	<input type="text" size="7" value="(number)" onfocus="select_value_on_first_focus(this);" name='<%= "newCopies_"+isbn %>' />
  	</td>
  </tr>
<% } %>
  <tr class="<%= evenOdd[count++ % 2] %>">
		<td colspan="3">
			New&nbsp;ISBN:&nbsp;<input type="text" size="35" name="newIsbn" id="newIsbn" value="" />
			Copies:&nbsp;<input type="text" size="3" name="newIsbnNumberOfCopies" id="newIsbnNumberOfCopies" value="1" />
		</td>
	</tr>
  <tr class="foot">
		<td colspan="3" class="foot"><input type="submit" name="Submit" value="Submit"/></td>
	</tr>
  </table>
</form>
<script>
focus_on_this_form_field("list_form", "newIsbn")
</script>
