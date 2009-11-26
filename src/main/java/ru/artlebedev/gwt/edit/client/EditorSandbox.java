package ru.artlebedev.gwt.edit.client;

import ru.artlebedev.gwt.edit.client.ui.EditArea;

import org.apache.commons.lang.ArrayUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 23.11.2009
 * Time: 12:44:58
 */
public class EditorSandbox implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final EditArea editArea = new EditArea();
    final VerticalPanel panel = new VerticalPanel();
    editArea.setHTML("test <strong>bold</strong> and <em>italic</em>");
    final Button getSelectionBtn = new Button("get selection");
    final Button getCurPosBtn = new Button("get cur position");
    final Label label = new Label("test layout");
    final HorizontalPanel nodes = new HorizontalPanel();
    RootPanel.get("EditorLayout").add(editArea);
    final RootPanel styleLayout = RootPanel.get("StyleLayout");
    final RootPanel statusLayout = RootPanel.get("StatusLayout");
    panel.add(getSelectionBtn);
    panel.add(getCurPosBtn);
    styleLayout.add(panel);
    styleLayout.add(label);
    statusLayout.add(nodes);
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
        updateNodesPanel(editArea, nodes);
      }
    });
    editArea.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        updateNodesPanel(editArea, nodes);
      }
    });
  }

  private void updateNodesPanel(EditArea editArea, HorizontalPanel nodes) {
    Element current = editArea.getSelectionCurrent();
    List<String> nodesOrder = new ArrayList<String>();
    nodesOrder.add(current.getTagName());
    while(current.hasParentElement()) {
      nodesOrder.add(current.getTagName());
      current = current.getParentElement();
    }
    nodes.clear();
    for (int i = nodesOrder.size() - 1; i > 0; i-=1) {
      nodes.add(new Button(nodesOrder.get(i).toLowerCase()));
    }
  }
}
