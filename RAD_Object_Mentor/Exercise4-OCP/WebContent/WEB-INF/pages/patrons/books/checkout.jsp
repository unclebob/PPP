<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>

<h1>Check Out a Book</h1>

<form action="<library:actionPath actionName="patrons/books/checkout"/>" method="post">
  <table class="input">
    <tr>
      <td align="right"><b>Copy ID:</b></td>
      <td><input type="text" size="35" name="copyId"></td>
    <tr>
      <td align="right"><b>Borrower ID:</b></td>
      <td><input type="text" size="35" name="borrowerId"></td>
      <tr>
        <td>&nbsp;</td>
        <td><input type="submit" name="Submit" value="Submit"/></td>
      </tr>
  </table>
</form>
