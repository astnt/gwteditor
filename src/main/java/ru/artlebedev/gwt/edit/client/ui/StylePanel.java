package ru.artlebedev.gwt.edit.client.ui;

import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiBinder;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 16:43:25
 * To change this template use File | Settings | File Templates.
 */
public class StylePanel extends UIObject {
  interface StyleUiBinder extends UiBinder<DivElement, StylePanel> {
  }

  private static StyleUiBinder uiBinder = GWT.create(StyleUiBinder.class);

  @UiField
  DivElement containerDiv;

  public StylePanel() {
    setElement(uiBinder.createAndBindUi(this));
  }

  public DivElement getContainerDiv() {
    return containerDiv;
  }

  public void addTextStyles(TextStyle[] styles) {
    for (TextStyle style : styles) {
      getContainerDiv().appendChild(style.getElement());
    }
  }
}
