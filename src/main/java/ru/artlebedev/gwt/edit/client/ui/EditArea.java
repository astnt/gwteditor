package ru.artlebedev.gwt.edit.client.ui;

import ru.artlebedev.gwt.edit.client.ui.selection.BrowserEditGeckoImpl;
import ru.artlebedev.gwt.edit.client.ui.selection.BrowserEdit;

import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 24.11.2009
 * Time: 14:03:31
 */
public class EditArea extends RichTextArea implements BrowserEditable {
  BrowserEdit impl;

  public EditArea() {
    impl = new BrowserEditGeckoImpl();
  }

  public Element getSelectionParent() {
    return impl.getSelectionParent(getElement());
  }

  public int getStartOffset() {
    return impl.getStartOffset(getElement());
  }

  public String getSelectionToString() {
    return impl.getSelectionToString(getElement());
  }
}
