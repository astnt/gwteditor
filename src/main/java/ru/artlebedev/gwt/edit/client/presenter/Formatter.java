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
    if (first.equals(last) && end <= start) { return; } // nothing selected
    while (true) {
      if (current.getNodeType() == Document.TEXT_NODE) {
        if (current.equals(first) && start < current.getNodeValue().length()) {
          // cut node and replace with new
          String value = current.getNodeValue();
          String oldFormat = value.substring(0, start);
          current.setNodeValue(oldFormat);

          if (current.equals(last) && end != value.length()) {
            String newFormat = value.substring(start, end);
            Node child = createNewChild(textStyle, newFormat);
            current.getParentElement().insertAfter(child, current);
            String endFormat = value.substring(end, value.length());
            current.getParentElement().insertAfter(document.createTextNode(endFormat), child);
          } else {
            String newFormat = value.substring(start);
            current.getParentElement().insertAfter(createNewChild(textStyle, newFormat), current);
          }
        }
      } else if (current.getNodeType() == Document.ELEMENT_NODE) {
        Element element = current.cast();
        if (!element.getTagName().equals(textStyle.getTagName())) { // not equal with new formatter element
          current.getParentElement().replaceChild(createNewChild(textStyle, element.getInnerText()), current);
        }
      }
      if (current.equals(last)) { break; }
      current = current.getNextSibling();
      if (current == null) { break; }
    }
  }

  private Node createNewChild(TextStyle style, String text) {
    final Element element = document.createElement(style.getTagName());
    element.setInnerText(text);
    return element;
  }
}

