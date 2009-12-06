package ru.artlebedev.gwt.edit.client.ui.selection;

import com.google.gwt.dom.client.Element;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 26.11.2009
 * Time: 14:53:59
 * To change this template use File | Settings | File Templates.
 */
public class BrowserEditGeckoImpl implements BrowserEdit {

  public native Element getSelectionParent(Element element) /*-{
    return element.contentWindow.getSelection().getRangeAt(0).startContainer.parentNode;
  }-*/;

  public native int getStartOffset(Element element) /*-{
    return element.contentWindow.getSelection().getRangeAt(0).startOffset;
  }-*/;

  public native int getEndOffset(Element element) /*-{
    return element.contentWindow.getSelection().getRangeAt(0).endOffset;
  }-*/;

  public native Element getStartContainer(Element element) /*-{
    console.log("start container", element.contentWindow.getSelection().getRangeAt(0).startContainer);
    return element.contentWindow.getSelection().getRangeAt(0).startContainer;
  }-*/;

  public native Element getEndContainer(Element element) /*-{
//    console.log("end container", element.contentWindow.getSelection().getRangeAt(0).endContainer);
    return element.contentWindow.getSelection().getRangeAt(0).endContainer;
  }-*/;

  public native String getSelectionToString(Element element) /*-{
    return element.contentWindow.getSelection().toString();
  }-*/;
}
