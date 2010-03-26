<%@ taglib uri="/WEB-INF/tld/LibraryTags.tld" prefix="library" %>

<h1>Identify Patron</h1>

<div class="input">
  <hr>
  <br>

  <form action="<library:actionPath actionName="patrons/books/matchPatron"/>" method="post">
    <label for="patronPattern">Name or ID</label> <input name="patronPattern" type="text">
    <input type="submit" value="Find">
  </form>
  <br>
  <hr>
</div>