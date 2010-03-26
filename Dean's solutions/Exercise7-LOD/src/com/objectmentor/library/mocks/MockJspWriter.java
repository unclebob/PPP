package com.objectmentor.library.mocks;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class MockJspWriter extends JspWriter {

  public String submittedContent;

  public MockJspWriter(int bufferSize, boolean autoFlush) {
    super(bufferSize, autoFlush);
    // TODO Auto-generated constructor stub
  }

  public void clear() throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void clearBuffer() throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void close() throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void flush() throws IOException {
    // TODO Auto-generated method stub
    
  }

  public int getRemaining() {
    // TODO Auto-generated method stub
    return 0;
  }

  public void newLine() throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(boolean arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(char arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(int arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(long arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(float arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(double arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(char[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(String arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void print(Object arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println() throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(boolean arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(char arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(int arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(long arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(float arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(double arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(char[] arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(String arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void println(Object arg0) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void write(char[] arg0, int arg1, int arg2) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public void write(String content) throws IOException {
    this.submittedContent = content;
    
  }

}
