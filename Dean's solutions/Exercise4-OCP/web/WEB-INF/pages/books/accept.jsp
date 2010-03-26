<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>
<h1>Enter a New Book Copy</h1>

<form action="<library:actionPath actionName="books/accept"/>" method="post">
  <table class="input">
    <tr>
      <td align="right"><b>ISBN:</b></td>
      <td><input type="text" name="isbn" maxlength="13" size="13"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="Submit"/></td>
    </tr>
  </table>
</form>
