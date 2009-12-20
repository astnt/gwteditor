package ru.artlebedev.gwt.edit.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import ru.artlebedev.gwt.edit.client.ui.EditArea;
import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

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
  private TextStyle style;
  private int iter;

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
    iter = 0;
    this.style = style;
    if (first.equals(last) && end <= start) { return; } // ничего не выбрано
    formatTo(first);
  }
  
  public Node formatTo(Node current) {
    iter += 1;
    if (iter > 1000) { GWT.log("more then 1000", null); return null; }
    log("current", current);
    if (current == null) { return null; }
    if (current.getNodeType() == Document.TEXT_NODE) {
      current = formatText(current);
    }
    if (current.getNodeType() == Document.ELEMENT_NODE) {
      Element element = current.cast();
      if (!element.getTagName().equals(style.getTagName())) { // если форматирование ноды отличается
//        current.getParentElement().replaceChild(createNewChild(element.getInnerText()), current);
        if (element.hasChildNodes()) {
          current = formatTo(element.getFirstChild()); // двигаемся дальше
        }
      } else {
        log("format is equal", current);
      }
    }
    if (current == null || last.equals(current)) { log("break if null", current); return null; } // останавливаем обработку
    if (current.getNextSibling() == null && current.getParentElement() != null) {
      log("move down to parent sibling", current.getParentElement().getNextSibling());
      if (current.getNextSibling() == null) { current = findNextSibling(current); }
      current = formatTo(current.getParentElement().getNextSibling());
    } else {
      log("move next sibling", current.getNextSibling());
      if (current.getNextSibling() == null) {
        log("check for another", current.getParentNode());
        log("check for another another", current.getParentNode().getParentNode());
        current = findNextSibling(current);
      }
      current = formatTo(current.getNextSibling());
    }
    return current;
  }

  private Node findNextSibling(Node current) {
//    int i = 0;
//    while (current.getNextSibling() == null) {
//      log("trying while... ", current);
//      current = current.getParentNode();
//      if (i > 100) { break; }
//      i += 1;
//    }
    return current;
  }

  /**
   * Обработка текстовой ноды.
   * @param current нода по которой следуем
   * @return новую/ту же ноду, взависимости сменили ее или нет
   */
  private Node formatText(Node current) {
    log("text", current);
    String value = current.getNodeValue();
    if (current.equals(first) && start < current.getNodeValue().length()) { // если первая и старт меньше длины значения
      String oldFormat = value.substring(0, start); // то что остается по старому
      current.setNodeValue(oldFormat);

      if (current.equals(last) && end != value.length()) { // если послендняя и конец выделения еще не полностью ноду выделяет
        String newFormat = value.substring(start, end); // то что надо сделать по новому
        Node childWithNewFormat = createNewChild(newFormat);
        current.getParentElement().insertAfter(childWithNewFormat, current);
        String endFormat = value.substring(end, value.length());
        current.getParentElement().insertAfter(document.createTextNode(endFormat), childWithNewFormat); // вставляем что осталось по старому
      } else { // иначе формотируем по новому до конца текстовой ноды
        String newFormat = value.substring(start);
        Node replacement = createNewChild(newFormat);
        current.getParentElement().insertAfter(replacement, current);
        current = replacement;
      }
    } else if (current.equals(last) && end > 0) { // если это последняя нода и есть выделеное
      if (end == value.length()) { // если полностью дорезаем
        current = replace(current, value);
      } else {
        current.getParentElement().insertBefore(createNewChild(value.substring(0, end)), current);
        current.setNodeValue(value.substring(end));
      }
    } else { // где-то между стартом и началом
      if (!current.getParentElement().getTagName().equals(style.getTagName())) {
        current = replace(current, current.getNodeValue());
      }
    }
    return current;
  }

  private Node replace(Node current, String nodeValue) {
    Node replacement = createNewChild(nodeValue);
    current.getParentElement().replaceChild(replacement, current);
    return replacement;
  }

  private native Element log(String message, Object element) /*-{
    console.log(message, element);
  }-*/;

  private Node createNewChild(String text) {
    final Element element = document.createElement(style.getTagName());
    element.setInnerText(text);
    return element;
  }
}

