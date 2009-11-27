package ru.artlebedev.gwt.edit.client;

import ru.artlebedev.gwt.edit.client.ui.EditArea;
import ru.artlebedev.gwt.edit.client.ui.StylePanel;
import ru.artlebedev.gwt.edit.client.ui.StatusPanel;
import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

import java.util.List;
import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.dom.client.Element;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 23.11.2009
 * Time: 12:44:58
 */
@SuppressWarnings({"GwtToHtmlReferences"})
public class EditorSandbox implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final EditArea editArea = new EditArea();
    final VerticalPanel panel = new VerticalPanel();
    editArea.setHTML("test <strong>bold</strong> and <em>italic</em>");
    final Button getSelectionBtn = new Button("get selection");
    final Button getCurPosBtn = new Button("get cur position");
    final Label label = new Label("test layout");
    final StatusPanel status = new StatusPanel();
    RootPanel.get("EditorLayout").add(editArea);
    final RootPanel styleLayout = RootPanel.get("StyleLayout");
    final RootPanel statusLayout = RootPanel.get("StatusLayout");
    panel.add(getSelectionBtn);
    panel.add(getCurPosBtn);
    styleLayout.getElement().appendChild(createStyles());
    styleLayout.add(panel);
    styleLayout.add(label);
    statusLayout.add(status);
    getSelectionBtn.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        final String result = editArea.getSelectionToString();
        label.setText(result);
      }
    });
    getCurPosBtn.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        final Element current = editArea.getSelectionCurrent();
        label.setText(current.getTagName());
      }
    });
    editArea.addKeyUpHandler(new KeyUpHandler() {
      @Override
      public void onKeyUp(KeyUpEvent keyUpEvent) {
        status.updateNodesPanel(editArea);
      }
    });
    editArea.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        status.updateNodesPanel(editArea);
      }
    });
    editArea.addMouseUpHandler(new MouseUpHandler() {
      @Override
      public void onMouseUp(MouseUpEvent mouseUpEvent) {
        status.updateNodesPanel(editArea);
      }
    });
  }

  private Element createStyles() {
    StylePanel panel = new StylePanel();
    TextStyle[] styles = new TextStyle[] {
        new TextStyle("<strong>bold</strong>", "STRONG"),
        new TextStyle("<em>italic</em>", "EM"),
    };
    panel.addTextStyles(styles);
    return panel.getElement();
  }

}


