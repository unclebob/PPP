<%@ page import="com.objectmentor.library.utils.DateUtil" %>
<%@ page import="com.objectmentor.library.web.controller.patrons.LoanRecord" %>
<%@ page import="java.util.List" %>
<%
  List loanRecords = (List) request.getAttribute("loanRecords");
  if (loanRecords.size() > 0) {
%>
<table class="list">
  <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Due date</th>
    <th>Fine</th>
  </tr>
  <%
    for (int i = 0; i < loanRecords.size(); i++) {
      LoanRecord loanRecord = (LoanRecord) loanRecords.get(i);
  %>
  <tr class="<%=i%2==0?"even":"odd"%>">
    <td><%=loanRecord.id%>
    </td>
    <td><%=loanRecord.title%>
    </td>
    <td><%=DateUtil.dateToString(loanRecord.dueDate)%>
    </td>
    <td><%=loanRecord.fine.toString()%>
    </td>
  </tr>
  <%
    }
  %>
</table>
<%
  }
%>