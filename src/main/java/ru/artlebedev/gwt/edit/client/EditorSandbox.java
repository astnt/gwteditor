package ru.artlebedev.gwt.edit.client;

import ru.artlebedev.gwt.edit.client.ui.EditArea;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

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
    editArea.setHTML("test <strong>bold</strong> and <em>italic</em>");
    RootPanel.get().add(editArea);
    editArea.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        editArea.test();
      }
    });

  }
}
