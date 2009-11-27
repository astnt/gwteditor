package ru.artlebedev.gwt.edit.client.ui.style;

import com.google.gwt.user.client.ui.HTML;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 16:07:30
 * To change this template use File | Settings | File Templates.
 */
public class TextStyle extends HTML {
  private String tagName;
  private String className;
  private String caption;

  public TextStyle(String caption, String tagName) {
    super(caption);
    this.caption = caption;
    this.tagName = tagName;
  }

  public String getTagName() {
    return tagName;
  }

  public String getClassName() {
    return className;
  }

  public String getCaption() {
    return caption;
  }
}
