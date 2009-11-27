package ru.artlebedev.gwt.edit.client.ui;

import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 16:43:25
 */
public class StylePanel extends UIObject {
  interface StyleUiBinder extends UiBinder<DivElement, StylePanel> {
  }

  private static StyleUiBinder uiBinder = GWT.create(StyleUiBinder.class);
  private TextStyle[] styles;

  @UiField
  DivElement containerDiv;

  public TextStyle[] getStyles() {
    return styles;
  }

  public StylePanel() {
    setElement(uiBinder.createAndBindUi(this));
  }

  public DivElement getContainerDiv() {
    return containerDiv;
  }

  public void addTextStyles(TextStyle[] styles) {
    this.styles = styles;
    final ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        Window.alert("test");
      }
    };
    for (TextStyle style : styles) {
      containerDiv.appendChild(style.getElement());
//      style.addClickHandler(clickHandler);
    }
  }
}
