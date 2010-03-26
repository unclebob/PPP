<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<!--
NOTE - this bit is a little goofy. Apparently, the JSTL tags won't support
a request object directly as a List interface, so we can't ask patrons.size.
To discover whether patrons is empty or not, we default it to empty, and then
iterate through the patrons, setting it to non-empty

<c:set var="patronsExist" value="${false}"/>
<c:forEach items="${patrons}" var="patron">
	<c:set var="patronsExist" value="${true}"/>
</c:forEach>
-->

<h1>Manage the Patrons</h1>
<form action="<library:actionPath actionName="patrons/manage"/>" method="post" id="list_form">
<table class="list">
  <tr class="head">
  	<c:choose>
  		<c:when test="${patronsExist}">
	  		<th class="head">
	  			Delete?
   				<br/><input type="checkbox" name="all" value="all" onclick="click_or_uncheck_all(this);" alt="delete all"/> all
	  		</th>
		    <th class="head">ID</th>
	  	</c:when>
	  	<c:otherwise>
	  		<th colspan="2" class="head"/>
	  	</c:otherwise>
		</c:choose>
    <th class="head">First <font color="red">*</font></th>
    <th class="head">M.I.</th>
    <th class="head">Last <font color="red">*</font></th>
  </tr>
  
<c:if test="${patronsExist}">
	<c:forEach items="${patrons}" var="patron" varStatus="status">
		<c:choose>
			<c:when test="${status.count % 2 == 0}">
				<tr class="even">
			</c:when>
			<c:otherwise>
				<tr class="odd">
			</c:otherwise>
		</c:choose>
	  	<td>
	  	 	<input type="checkbox" name="delete_<c:out value="${patron.id}"/>" />
	  	</td>
	    <td>
	      <c:out value="${patron.id}"/>
	    </td>
	    <td>
	    	<input type="text" size="35" name="firstName_<c:out value="${patron.id}"/>" value="<c:out value="${patron.firstName}"/>" />
	    </td>
	    <td>
	    	<input type="text" size="1" name="middleInitial_<c:out value="${patron.id}"/>" value="<c:out value="${patron.middleInitial}"/>" />
	    </td>
	    <td>
	    	<input type="text" size="35" name="lastName_<c:out value="${patron.id}"/>" value="<c:out value="${patron.lastName}"/>" />
	    </td>
	  </tr>
	</c:forEach>
</c:if>

  <tr>
  	<td colspan="2">
  		New:
    </td>
    <td>
    	<input type="text" size="35" name="newFirstName" id="newFirstName" value="(first name)" onfocus="select_value_on_first_focus(this);" />
    </td>
    <td>
    	<input type="text" size="1" name="newMiddleInitial" />
    </td>
    <td>
    	<input type="text" size="35" name="newLastName" id="newLastName" value="(last name)" onfocus="select_value_on_first_focus(this);" />
    </td>
  </tr>
  <tr class="foot">
    <td colspan="5" class="foot"><input type="submit" name="Submit" value="Submit"/></td>
  </tr>
</table>
<br/>
<b><font color="red">*</font></b> Indicates a required field.
</form>
<script>
focus_on_this_form_field("list_form", "newFirstName")
</script>
