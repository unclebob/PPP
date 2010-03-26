<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>

<h1>Set Date</h1>

<div class="input">
  <hr>
  <br>

  <form action="<library:actionPath actionName="debug/setDate"/>" method="post">
    <label for="date">Date</label> <input name="date" type="text">
    <input type="submit" value="Set Date">
  </form>
  <br>
  <hr>
</div>