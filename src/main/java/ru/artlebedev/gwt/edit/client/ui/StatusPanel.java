package ru.artlebedev.gwt.edit.client.ui;

import java.util.List;
import java.util.ArrayList;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.dom.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 17:19:17
 */
public class StatusPanel extends VerticalPanel {
  private HorizontalPanel nodePath;
  private Label selected;

  public StatusPanel() {
    add(nodePath = new HorizontalPanel());
    add(selected = new Label());
  }

  public void updateNodesPanel(EditArea editArea) {
    Element current = editArea.getSelectionParent();
    List<String> nodesOrder = new ArrayList<String>();
    nodesOrder.add(current.getTagName());
    while (current.hasParentElement()) {
      nodesOrder.add(current.getTagName());
      current = current.getParentElement();
    }
    nodePath.clear();
    for (int i = nodesOrder.size() - 1; i > 0; i -= 1) {
      nodePath.add(new Button(nodesOrder.get(i).toLowerCase()));
    }
    selected.setText(editArea.getSelectionToString());
  }
}
