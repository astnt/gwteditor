package ru.artlebedev.gwt.edit.client;

import ru.artlebedev.gwt.edit.client.presenter.NodeProcessor;
import ru.artlebedev.gwt.edit.client.ui.EditArea;
import ru.artlebedev.gwt.edit.client.ui.StatusPanel;
import ru.artlebedev.gwt.edit.client.ui.style.TextStyle;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 23.11.2009
 * Time: 12:44:58
 */
@SuppressWarnings({"GwtToHtmlReferences", "GWTStyleCheck"})
public class EditorSandbox implements EntryPoint {
  private EditArea editArea;
  private StatusPanel status;
  private NodeProcessor processor;

  @Override
  public void onModuleLoad() {
    processor = new NodeProcessor();
    editArea = new EditArea();
    final VerticalPanel panel = new VerticalPanel();
    editArea.setHTML("test <strong>bold</strong> and <em>italic</em>");
    final Button getSelectionBtn = new Button("get selection");
    final Button getCurPosBtn = new Button("get cur position");
    final Label label = new Label("test layout");
    status = new StatusPanel();
    RootPanel.get("EditorLayout").add(editArea);
    final RootPanel styleLayout = RootPanel.get("StyleLayout");
    final RootPanel statusLayout = RootPanel.get("StatusLayout");
    panel.add(getSelectionBtn);
    panel.add(getCurPosBtn);
    styleLayout.add(createStyles());
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
        final Element current = editArea.getSelectionParent();
        label.setText(current.getTagName());
      }
    });
    final ChangeHandler changeHandler = new ChangeHandler();
    editArea.addKeyUpHandler(changeHandler);
    editArea.addClickHandler(changeHandler);
    editArea.addMouseUpHandler(changeHandler);
  }

  private VerticalPanel createStyles() {
    final ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        GWT.log("source: " + clickEvent.getSource(), null);
      }
    };
    TextStyle[] styles = new TextStyle[] {
        new TextStyle("<strong>bold</strong>", "STRONG"),
        new TextStyle("<em>italic</em>", "EM"),
        new TextStyle("<p>para</p>", "P"),
    };
    VerticalPanel panel = new VerticalPanel();
    panel.addStyleName("StyleList");
    for (TextStyle textStyle : styles) {
      panel.add(textStyle);
      textStyle.addClickHandler(clickHandler);
    }
    return panel;
  }

  private class ChangeHandler implements KeyUpHandler, ClickHandler, MouseUpHandler {
    private void update() {
      status.updateNodesPanel(editArea);
      processor.update(editArea);
    }

    @Override
    public void onKeyUp(KeyUpEvent keyUpEvent) { update(); }

    @Override
    public void onClick(ClickEvent clickEvent) { update(); }

    @Override
    public void onMouseUp(MouseUpEvent mouseUpEvent) { update(); }
  }

}


  