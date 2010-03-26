<%@ page import="com.objectmentor.library.models.Patron" %>
<%@ page import="com.objectmentor.library.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%Patron patron = (Patron) request.getSession().getAttribute("patron");%>
<h1><%=patron.getFullName()%> is returning:
</h1>
<%=DateUtil.dateToString((Date) request.getAttribute("date"))%>
<div class="input">
  <hr/>
  <br/>

  <form action="<library:actionPath actionName="patrons/books/returnCopy"/>" method="post">
    <label for="mediaId">Media Id</label>
    <input name="copyId" type="text">
    <input type="submit" value="Return">
  </form>
  <br/>
  <hr/>
</div>

<jsp:include page="/WEB-INF/pages/patrons/books/loanRecords.jsp"/>

