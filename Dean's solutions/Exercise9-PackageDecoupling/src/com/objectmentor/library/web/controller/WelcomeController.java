package com.objectmentor.library.web.controller;

public class WelcomeController extends LibraryController {

  public ActionResult index() {
    render("welcome.jsp");
    return new ActionResult();
  }
}
