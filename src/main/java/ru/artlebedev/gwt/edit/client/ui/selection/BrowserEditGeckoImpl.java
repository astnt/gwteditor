package ru.artlebedev.gwt.edit.client.ui.selection;

import com.google.gwt.user.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 14:53:59
 * To change this template use File | Settings | File Templates.
 */
public class BrowserEditGeckoImpl implements BrowserEdit {

  public native Element getSelectionParent(Element element) /*-{
    console.log("cur offset", element.contentWindow.getSelection().getRangeAt(0).startOffset);
    console.log("cur end", element.contentWindow.getSelection().getRangeAt(0).endOffset);
    var selection = element.contentWindow.getSelection();
    for (var i = 0; i < selection.rangeCount; i += 1) {
      console.log("cur container parent", selection.getRangeAt(0).startContainer);
      console.log("cur container end", selection.getRangeAt(0).endContainer);
    }
    console.log("range count:", selection.rangeCount);
    return element.contentWindow.getSelection().getRangeAt(0).startContainer.parentNode;
  }-*/;

  public native int getStartOffset(Element element) /*-{
    return element.contentWindow.getSelection().getRangeAt(0).startOffset;
  }-*/;

  public native String getSelectionToString(Element element) /*-{
    return element.contentWindow.getSelection().toString();
  }-*/;
}
