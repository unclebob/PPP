package com.objectmentor.library.web.controller;

public class ActionResult {
	private StringBuffer infoMessage    = new StringBuffer();
	private StringBuffer warningMessage = new StringBuffer();
	private StringBuffer errorMessage   = new StringBuffer();
  
	public ActionResult (String infoMessage, String warningMessage, String errorMessage) {
		this.infoMessage.append(infoMessage);
		this.warningMessage.append(warningMessage);
		this.errorMessage.append(errorMessage);
	}
	
	public ActionResult (String infoMessage, String warningMessage) {
		this(infoMessage, warningMessage, "");
	}
	
	public ActionResult (String infoMessage) {
		this(infoMessage, "", "");
	}
  
  public ActionResult() {}

	public ActionResult appendToInfoMessage(String message) { 
		infoMessage.append(message).append("<br/>"); 
		return this;
	}
	public ActionResult appendToWarningMessage(String message) { 
		warningMessage.append(message).append("<br/>");
		return this;
	}
	public ActionResult appendToErrorMessage(String message) { 
		errorMessage.append(message).append("<br/>");
		return this;
	}
	
	public String getInfoMessage()    { return infoMessage.toString(); }
	public String getWarningMessage() { return warningMessage.toString(); }
	public String getErrorMessage()   { return errorMessage.toString(); }

	public boolean empty() {
		return infoMessage.length() == 0 && warningMessage.length() == 0 && errorMessage.length() == 0;
	}

	/**
	 * Convenience factory method to make a result with just an error message.
	 */
	static public ActionResult makeErrorHandlerResult(String errorMessage) {
		return new ActionResult("", "", errorMessage);
	}
	
	/**
	 * Convenience factory method to make a result with just a warning message.
	 */
	static public ActionResult makeWarningHandlerResult(String warningMessage) {
		return new ActionResult("", warningMessage);
	}
	
	/**
	 * Convenience factory method to make a result with just an info message. This
	 * is somewhat redundant with one of the constructors, but it is provided for 
	 * "symmetry" with the other factory methods.
	 */
	static public ActionResult makeInfoHandlerResult(String infoMessage) {
		return new ActionResult(infoMessage);
	}

	public static final ActionResult POST_NOT_SUPPORTED = 
		ActionResult.makeErrorHandlerResult("POST method not supported for this request.");
	public static final ActionResult GET_NOT_SUPPORTED = 
		ActionResult.makeErrorHandlerResult("GET method not supported for this request.");

	public String toString() {
		if (empty()) 
			return "ActionResult: <empty>";
		StringBuffer buff = new StringBuffer();
		buff.append("ActionResult: ");
		if (getErrorMessage().length() > 0)
			buff.append("error = \"").append(getErrorMessage()).append("\", ");
		if (getWarningMessage().length() > 0)
			buff.append("warning = \"").append(getWarningMessage()).append("\", ");
		if (getInfoMessage().length() > 0)
			buff.append("info = \"").append(getInfoMessage()).append("\"");
		buff.append(".");
		return buff.toString();
	}
}
