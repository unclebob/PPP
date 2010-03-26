<%@ page import="com.objectmentor.library.application.models.Patron" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%Set patrons = (Set) request.getAttribute("patrons");%>
<h3>Select a Patron:</h3>

<div class="input">
  <hr>
  <br>

  <form action="<library:actionPath actionName="patrons/books/selectPatron"/>" method="post">
    <%
      for (Iterator i = patrons.iterator(); i.hasNext();) {
        Patron patron = (Patron) i.next();%>
    <input type="radio" name="selectedPatron" value="<%=patron.getId()%>"> <%=patron.getFullName()%> <br/>
    <%}%>
    <input type=submit value="Select">
  </form>
  <br>
  <hr>
</div>