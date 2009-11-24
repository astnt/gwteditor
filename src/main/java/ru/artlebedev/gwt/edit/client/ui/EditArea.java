package ru.artlebedev.gwt.edit.client.ui;

import com.google.gwt.user.client.ui.RichTextArea;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 24.11.2009
 * Time: 14:03:31
 */
public class EditArea extends RichTextArea {
  public native void test() /*-{
    console.log("this");
    console.log("gg", 132);
//    console.log(typeof(this));
  }-*/;
}
