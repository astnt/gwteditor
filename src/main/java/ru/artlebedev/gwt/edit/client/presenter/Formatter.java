package ru.artlebedev.gwt.edit.client.presenter;

import ru.artlebedev.gwt.edit.client.ui.EditArea;
import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Document;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 27.11.2009
 * Time: 14:54:39
 * Обрабатывает наборы нод.
 */
public class Formatter {
  private int start;
  private int end;
  private Element first;
  private Element last;
  private EditArea edit;
  private Document document;

  public void setEdit(EditArea edit) {
    this.edit = edit;
  }

  public void updateSelection() {
    start = edit.getStartOffset();
    end = edit.getEndOffset();
    first = edit.getStartContainer();
    last = edit.getEndContainer();
    document = first.getOwnerDocument();
  }

  public void formatTo(TextStyle textStyle) {
    Node current = first;
    while (true) {
      if (current.getNodeType() == Document.TEXT_NODE) {
        if (current.equals(first) && start < current.getNodeValue().length()) {
          // cut node and replace with new
          String value = current.getNodeValue();
          String oldFormat = value.substring(0, value.indexOf(start));
          String newFormat = value.substring(value.indexOf(start));
          current.setNodeValue(oldFormat);
          current.getParentElement().insertAfter(createNewChild(textStyle.getTagName(), newFormat), current);
        }
      } else if (current.getNodeType() == Document.ELEMENT_NODE) {
        Element element = current.cast();
        if (!element.getTagName().equals(textStyle.getTagName())) { // not equal with new formatter element
          current.getParentElement().replaceChild(createNewChild(textStyle.getTagName(), element.getInnerText()), current);
        }
      }
      if (current.equals(last)) { break; }
      current = current.getNextSibling();
      if (current == null) { break; }
    }
  }

  private Node createNewChild(String tagName, String text) {
    final Element element = document.createElement(tagName);
    element.setInnerText(text);
    return element;
  }
}
