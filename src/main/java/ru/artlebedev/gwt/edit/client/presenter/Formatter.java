package ru.artlebedev.gwt.edit.client.presenter;

import ru.artlebedev.gwt.edit.client.ui.EditArea;
import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;

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

  public void setEdit(EditArea edit) {
    this.edit = edit;
  }

  public void updateSelection() {
    start = edit.getStartOffset();
    end = edit.getEndOffset();
    first = edit.getStartContainer();
    last = edit.getEndContainer();
  }

  public void formatTo(TextStyle textStyle) {
    boolean selected = isSelected(textStyle);
    GWT.log("format to: " + textStyle + "; selected " + selected, null);

    Node newChild = createNewChild(textStyle.getTagName(), selected);
    final Element parent;
    final Element element;
    if (selected) {
      element = first.getParentElement();
      parent = first.getParentElement().getParentElement();
    } else {
      element = first;
      parent = first.getParentElement();
    }
    GWT.log("parent: " + parent, null);
//    if (start == 0) {
      parent.replaceChild(newChild, element);
//    }
  }

  private boolean isSelected(TextStyle textStyle) {
    return first.hasParentElement() && textStyle.getTagName().equals(first.getParentElement().getTagName());
  }

  private Node createNewChild(String tagName, boolean selected) {
    if (selected) {
      return first.getOwnerDocument().createTextNode(first.getInnerText());
    } else {
      final Element element = first.getOwnerDocument().createElement(tagName);
      element.setInnerText(first.getInnerText());
      return element;
    }
  }
}
