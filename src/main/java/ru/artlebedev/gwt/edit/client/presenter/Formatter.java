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

  public void formatTo(TextStyle style) {
    Node current = first;
    if (first.equals(last) && end <= start) { return; } // nothing selected
    while (true) {
      log("current", current);
      if (current == null) { break; } 
      if (current.getNodeType() == Document.TEXT_NODE) {
        String value = current.getNodeValue();
        if (current.equals(first) && start < current.getNodeValue().length()) {
          String oldFormat = value.substring(0, start);
          current.setNodeValue(oldFormat);

          if (current.equals(last) && end != value.length()) {
            String newFormat = value.substring(start, end);
            Node child = createNewChild(style, newFormat);
            current.getParentElement().insertAfter(child, current);
            String endFormat = value.substring(end, value.length());
            current.getParentElement().insertAfter(document.createTextNode(endFormat), child);
          } else {
            String newFormat = value.substring(start);
            current.getParentElement().insertAfter(createNewChild(style, newFormat), current);
          }
        } else if (current.equals(last) && end < 0) {
          GWT.log("not work in first case!", null);
          if (end == value.length()) {
            current.getParentElement().replaceChild(createNewChild(style, value), current);
          } else {
            current.insertBefore(createNewChild(style, value.substring(0, end)), current);
            current.setNodeValue(value.substring(end));
          }
        }
        // TODO end logic
      } else if (current.getNodeType() == Document.ELEMENT_NODE) {
        Element element = current.cast();
        if (!element.getTagName().equals(style.getTagName())) { // not equal with new formatter element
          current.getParentElement().replaceChild(createNewChild(style, element.getInnerText()), current);

          if (element.hasChildNodes()) {

          }
        }
      }
      if (current.equals(last)) { break; }
      if (current.getNextSibling() == null) {
        log("move down to parent", current.getParentElement());
        current = current.getParentElement();
      }
      else { 
        log("move next sibling", current.getNextSibling());
        current = current.getNextSibling();
      }
    }
  }

  private native Element log(String message, Node element) /*-{
    console.log(message, element)
  }-*/;

  private Node createNewChild(TextStyle style, String text) {
    final Element element = document.createElement(style.getTagName());
    element.setInnerText(text);
    return element;
  }
}

