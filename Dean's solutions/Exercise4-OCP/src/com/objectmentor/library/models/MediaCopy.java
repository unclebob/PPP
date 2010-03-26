package com.objectmentor.library.models;

public class MediaCopy {
  private Media media;
  private String id;
  private LoanReceipt loanReceipt;

  public MediaCopy(Media media, String id) {
    this.media = media;
    this.id = id;
  }

  public Media getMedia() {
    return media;
  }

  public boolean isLoaned() {
    return loanReceipt != null;
  }

  public String getId() {
    return id;
  }

  public LoanReceipt getLoanReceipt() {
    return loanReceipt;
  }

  public void setLoaned(LoanReceipt loanReceipt) {
    this.loanReceipt = loanReceipt;
  }
}
