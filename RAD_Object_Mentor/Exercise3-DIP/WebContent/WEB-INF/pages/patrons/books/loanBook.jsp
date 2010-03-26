<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>

<h1>Check Out a Book</h1>

<form action="<library:actionPath actionName="patrons/books/checkout"/>" method="post">
  <table class="input">
    <tr>
      <td align="right"><b>Copy ID:</b></td>
      <td><input type="text" size="20" name="copyId"></td>
      <td><b><font color="red">*</font></b></td>
    </tr>
    <tr>
      <td align="right"><b>Borrower Name:</b></td>
      <td>
        <input type="text" size="20" name="borrowerFirstName"/>
      </td>
      <td>
        <input type="text" size="4" name="borrowerMiddleInitial"/>
      </td>
      <td>
        <input type="text" size="20" name="borrowerLastName"/>
      </td>
      <td><b><font color="red">*</font></b></td>
    </tr>
    <tr>
      <td align="right"><b>or ID:</b></td>
      <td>
        <input type="text" size="20" name="borrowerId">
      </td>
      <td><b><font color="red">*</font></b></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="Submit"/></td>
    </tr>
  </table>
</form>
<br/>
<b><font color="red">*</font></b> Indicates a required field.
