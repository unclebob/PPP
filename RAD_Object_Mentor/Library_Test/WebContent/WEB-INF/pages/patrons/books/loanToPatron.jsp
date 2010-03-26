<%@ page import="com.objectmentor.library.application.models.Patron" %>
<%@ page import="com.objectmentor.library.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<%Patron patron = (Patron) request.getSession().getAttribute("patron");%>
<h1>Loan to Patron: <%=patron.getFullName()%>
</h1>
<%=DateUtil.dateToString((Date) request.getAttribute("date"))%>
<div class="input">
  <hr/>
  <br/>

  <form action="<library:actionPath actionName="patrons/books/loanCopy"/>" method="post">
    <label for="mediaId">Copy Id</label>
    <input name="copyId" type="text">
    <input type="submit" value="Loan">
  </form>
  <br/>
  <hr/>
</div>

<jsp:include page="/WEB-INF/pages/patrons/books/loanRecords.jsp"/>

