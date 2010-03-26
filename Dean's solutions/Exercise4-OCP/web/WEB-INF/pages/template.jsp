<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Library</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="<%= request.getContextPath() %>/styles.css" media="all" rel="Stylesheet" type="text/css"/>
  <script src="<%= request.getContextPath() %>/library.js"></script>
</head>
<body onload="focus_first_form_field();">
<div class="header">
  <img src="<%= request.getContextPath() %>/images/OMI.gif" align="left">

  <h1>Object Mentor Library</h1>
</div>
<div class="palette">
  <b>Patron Activities</b><br/>
  <ul>
    <li><a href="<%= request.getContextPath() %>">Home</a></li>
    <li>Patron Membership:
      <ul>
        <li><a href="<library:actionPath actionName="patrons/manage"/>">Manage the Patrons</a></li>
        <li><a href="<library:actionPath actionName="patrons/history"/>">Borrowing History</a></li>
      </ul>
    </li>
    <li>Books:
      <ul>
        <li><a href="<library:actionPath actionName="patrons/books/checkout"/>">Check Out</a></li>
        <li><a href="<library:actionPath actionName="patrons/books/checkin"/>">Check In</a></li>
      </ul>
    </li>
  </ul>
  <b>Manage Resources</b><br/>
  <ul>
    <li>Books:
      <ul>
        <li><a href="<library:actionPath actionName="books/manage"/>">Manage the Book Catalog</a></li>
      </ul>
    </li>
  </ul>
</div>
<div class="canvas">
  <jsp:include page="/WEB-INF/pages/handler_result.jsp" />
  <jsp:include page="<%= (String)request.getAttribute("include_path") %>"/>
</div>
<div class="footer">
  Copyright &copy; 2006-2007 Object Mentor, Inc. All Rights Reserved.
</div>
<jsp:include page="/debug.jsp"/>
</body>
</html>
