package ru.artlebedev.gwt.edit.client.presenter;

import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 27.11.2009
 * Time: 14:47:33
 * Хранится инфотмация о выбранном тексте, нодах и т.п.
 */
public class Selection {
  private Element parentNode;

  public void setParentNode(Element parentNode) {
    this.parentNode = parentNode;
  }
}
