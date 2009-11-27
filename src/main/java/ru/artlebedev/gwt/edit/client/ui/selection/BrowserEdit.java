package ru.artlebedev.gwt.edit.client.ui.selection;

import com.google.gwt.dom.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 14:52:50
 * To change this template use File | Settings | File Templates.
 */
public interface BrowserEdit {
  Element getSelectionParent(Element element);
  String getSelectionToString(Element element);
  int getStartOffset(Element element);
  int getEndOffset(Element element);
  Element getStartContainer(Element element);
  Element getEndContainer(Element element);
}
