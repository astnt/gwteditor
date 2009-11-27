package ru.artlebedev.gwt.edit.client.presenter;

import ru.artlebedev.gwt.edit.client.ui.EditArea;

import com.google.gwt.core.client.GWT;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 27.11.2009
 * Time: 14:54:39
 * Обрабатывает наборы нод.
 */
public class NodeProcessor {
  private Selection selection;

  public void update(EditArea editArea) {
    selection = new Selection();
    selection.setParentNode(editArea.getSelectionParent());
    GWT.log("start offset: " + editArea.getStartOffset(), null);
  }
}
