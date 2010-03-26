<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.objectmentor.library.models.CompactDisc" %>
<%@ page import="com.objectmentor.library.models.MediaCopy" %>

<!--
NOTE - this bit is a little goofy.
See patrons/books/manage.jsp for a more thorough explanation

<c:set var="cdsExist" value="${false}"/>
<c:forEach items="${compactDiscs}" var="cd">
	<c:set var="cdsExist" value="${true}"/>
</c:forEach>
-->

<h1>Manage the CD Catalog</h1>

<form action="<library:actionPath actionName="compactDiscs/manage"/>" method="post">
<table class="list">
	<c:if test="${cdsExist}">
	  <tr class="head">
	    <th class="head" valign="bottom" align="left">Copy IDs<br/>(Checked IDs will be deleted when you submit.)</th>
	  	<th class="head" valign="bottom" align="center">Delete<br/>All?</th>
	    <th class="head" valign="bottom" align="center">Add<br/>Copies</th>
	  </tr>
 	</c:if>
	</tr>
	<c:set var="evenodd" value="even" />
	<c:if test="${cdsExist}">
	<%-- We mix JSTL and embedded scripts because JSTL is tedious at handling lists of list.... --%>
	<%
	List compactDiscs = (List) request.getAttribute("compactDiscs");
  for (Iterator i = compactDiscs.iterator(); i.hasNext();) {
    List cdList  = (List) i.next();
    if (cdList != null && cdList.size() > 0) {
	    MediaCopy   copy0 = (MediaCopy) cdList.get(0);
	    CompactDisc disc0 = (CompactDisc) copy0.getMedia();
  %>
		<tr class="${evenodd}">
			<td colspan="3">
				Bar&nbsp;Code&nbsp;<%= disc0.getId() %>:
				Title:&nbsp;&quot;<%= disc0.getTitle() %>&quot;, 
				Artist:&nbsp;&quot;<%= disc0.getAuthor() %>&quot; 
			</td>
		</tr>
		<tr class="${evenodd}">
		  <td width="100%">
		  <%
		  for (Iterator i2 = cdList.iterator(); i2.hasNext(); ) {
	    	MediaCopy copy = (MediaCopy) i2.next();
		    String copyId = copy.getId();
		  %>
				<%-- Note that the id has a format needed by the javascript, while the name has a format required by the server-side java. --%>
      	<%= copyId %>:&nbsp;<input type="checkbox" id='<%= "delete_"+disc0.getId()+"_"+copyId %>' name='<%= "delete_"+copyId %>' />&nbsp;
			<% } %>
    	</td>
	  	<td align="center">
	  	 	<input type="checkbox" onclick='click_or_uncheck_all(this, "delete_<%=disc0.getId()%>");' />
	  	</td>
	  	<td align="left">
	  	 	<input type="text" size="7" value="(number)" onfocus="select_value_on_first_focus(this);" name='<%= "newCopies_"+disc0.getId() %>' />
	  	</td>
	  </tr>
		<%-- put this at the end so we can set the value for the follow on row for new entries --%>
		<c:choose> 
			<c:when test="${status.count % 2 == 1}">  <%-- use == 1 to set for NEXT iteration --%>
				<c:set var="evenodd" value="even" />
			</c:when>
			<c:otherwise>
				<c:set var="evenodd" value="odd" />
			</c:otherwise>
		</c:choose>
	<% 
		} 
	}
	%>
	</c:if>
		<tr class="${evenodd}">
			<td colspan="3">
				<b>New:</b>&nbsp;<input type="text" name="id" value="(Bar code/other ID)" onfocus="select_value_on_first_focus(this);" />&nbsp;&dagger;&nbsp;<font color="red">*</font>
				<input type="text" name="title" value="(Title)" onfocus="select_value_on_first_focus(this);" />
				<input type="text" name="artist" value="(Author/Artist)" onfocus="select_value_on_first_focus(this);" />
				Copies:&nbsp;<input type="text" size="3" name="newCDsNumberOfCopies" id="newCDsNumberOfCopies" value="1" />
			</td>
		</tr>
		<tr class="foot">
			<td colspan="3" class="foot"><input type="submit" value="Submit"></td>
		</tr>
	</table>
</form>
&dagger; or other appropriate ID, <i>e.g.,</i> ISBN for Audio Books, catalog number for other CDs.<br/>
<b><font color="red">*</font></b> Indicates a required field.
