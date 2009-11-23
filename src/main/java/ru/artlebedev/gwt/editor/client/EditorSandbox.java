package ru.artlebedev.gwt.editor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 23.11.2009
 * Time: 12:44:58
 */
public class EditorSandbox implements EntryPoint {

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(new Label("editor sandbox"));
  }
}
