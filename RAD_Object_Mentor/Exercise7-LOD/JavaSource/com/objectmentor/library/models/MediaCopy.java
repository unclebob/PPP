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

  public void setMedia(Media media) {
    this.media = media;
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

  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj.getClass() == getClass()))
      return false;
    MediaCopy that = (MediaCopy) obj;
    if (!this.id.equals(that.id))
      return false;
    if (!this.media.equals(that.media))
      return false;
    return true;
  }

  public boolean isCdCopy() {
    return (getMedia() instanceof CompactDisc);
  }

  public boolean isBookCopy() {
    return (getMedia() instanceof Book);
  }
  
  public String getTitle() {
    return getMedia().getTitle();
  }
  
  public String getAuthor() {
    return getMedia().getAuthor();
  }
}
