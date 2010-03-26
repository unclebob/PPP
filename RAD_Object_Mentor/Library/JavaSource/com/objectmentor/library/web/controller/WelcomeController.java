package com.objectmentor.library.web.controller;

import com.objectmentor.library.web.framework.ActionResult;

public class WelcomeController extends LibraryController {

  public ActionResult index() {
    render("welcome.jsp");
    return new ActionResult();
  }
}
