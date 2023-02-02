/*  
* Avin's jcx.TreePanel Demo Applet  
* Copyright  1995, 1996, 1997 Raja Sundarigari. All Rights Reserved.  
* Raja Sundarigari  
*/  
import  jcx.*;  
import  java.awt.*;  
import  java.applet.Applet;  
import  java.net.URL;  
import  java.net.MalformedURLException;  
/**  
* TreeApplet Class  
 // //  AUTO ......
 //  AUTO ...
* Demonstrates the usage of jcx.TreePanel  and jcx.TreeNode classes  
  */  
   // //  AUTO ......
 //  AUTO ...
public class TreeApplet extends java.applet.Applet implements Runnable{  
  public Thread treeThread;  
  public TreePanel treePane;  
  public TreeControls controls;  
  public TreeNode root;  
  public URL[] imgUrls;  
   // //  AUTO ......
 //  AUTO ...
public void paint(Graphics g){  
   //   drawCrossHair( g, CrossXPos, CrossYPos, CrossSize );  ...
  g.drawString( "Hello new world ..." , 20 , 20 );  
}  
public void init () {  
  String width  = getParameter("width");  
  int w = (width == null) ? 400 : Integer.valueOf(width).intValue();  
  String height  = getParameter("height");  
  int h = (height == null) ? 500 : Integer.valueOf(height).intValue();  
  resize (w,h);  
  String fontsize  = getParameter("fontsize");  
  int fs = (fontsize == null) ? 15 : Integer.valueOf(fontsize).intValue();  
   // // // face......
  String fontface  = getParameter("fontface");  
  if (fontface==null) fontface = "Areal";  
  
     // // /auto path//......
    String urlfontface  = getParameter("urlfontface");  
    if (urlfontface==null) urlfontface = "Areal";  
    
       // // /auto path//......
       // // // style......
      String fontstyle  = getParameter("fontstyle");  
      int fstyle;  
      if (fontstyle==null)  fstyle = Font.PLAIN;  
      else if (fontstyle.equals("plain")) fstyle = Font.PLAIN;  
      else if (fontstyle.equals("bold")) fstyle = Font.BOLD;  
      else if (fontstyle.equals("italic")) fstyle = Font.ITALIC;  
      else  fstyle = Font.PLAIN;  
        String urlfontstyle  = getParameter("urlfontstyle");  
        int urlfstyle;  
        if (urlfontstyle==null)  urlfstyle = Font.ITALIC;  
        else if (urlfontstyle.equals("plain")) urlfstyle = Font.PLAIN;  
        else if (urlfontstyle.equals("bold")) urlfstyle = Font.BOLD;  
        else if (urlfontstyle.equals("italic")) urlfstyle = Font.ITALIC;  
        else urlfstyle = Font.ITALIC;  
          Font f  = new Font (fontface, fstyle, fs);  
          Font uf = new Font (urlfontface, urlfstyle, fs);  
          setLayout(new BorderLayout());  
          setBackground (Color.lightGray);  
          try {  
          
            imgUrls = new URL [10];  
            imgUrls [0] = new URL (getDocumentBase(),  "iconpics/ylnode.gif");  
            imgUrls [1] = new URL (getDocumentBase(),  "iconpics/grnode.gif");  
            imgUrls [2] = new URL (getDocumentBase(),  "iconpics/phone.gif");  
            imgUrls [3] = new URL (getDocumentBase(),  "iconpics/ppen.gif");  
            imgUrls [4] = new URL (getDocumentBase(),  "iconpics/dblptr.gif");  
            imgUrls [5] = new URL (getDocumentBase(),  "iconpics/file.gif");  
            imgUrls [6] = new URL (getDocumentBase(),  "iconpics/excl.gif");  
            imgUrls [7] = new URL (getDocumentBase(),  "iconpics/yes.gif");  
            imgUrls [8] = new URL (getDocumentBase(),  "iconpics/no.gif");  
            imgUrls [9] = new URL (getDocumentBase(),  "iconpics/url.gif");  
          }  
        catch (MalformedURLException e) {  
           // // // do error recovery .........
        }  
        add ("Center", treePane = new TreePanel());  
        treePane.showPreIcons (true);  
        treePane.setFont (f);  
        treePane.setUrlFont (uf);  
        root = treePane.getRoot ();  
        root.setIconURL (imgUrls [0]);  
        root.setName  ("Inputs");
        root.select();  
        try {  
        
          root.addChild (  
          new TreeNode(null, "Ibbotson Home Page", new URL("http://www.ibbotson.com"), null, imgUrls [9]));  
          root.addChild (  
          new TreeNode(null, "Netscape Home Page", new URL("http://www.netscape.com"), null, imgUrls [9]));  
          root.addChild (  
          new TreeNode(null,  "Software Home Page", null , null, imgUrls [9]));
        }  
      catch (MalformedURLException e) {  
         // // // do error recovery .........
      }  
      TreeNode t0 = root.addChild (new TreeNode(null, "Child with two Icon states", imgUrls [7], imgUrls [6]));  
      t0.setCloseIconURL (imgUrls [8]);  
      TreeNode t00 = t0.addChild (new TreeNode(null, "Expand me to See the scroll bars", null, null));  
      for (int i=0; i<50; i++)  
      t00.addChild (new TreeNode(null, "Child with file icon "+Integer.toString(i), imgUrls [5], imgUrls [5]));  
      t00.collapse();  
      t0.addChild (new TreeNode(null, "Child with Pre Yes + Pen icon", imgUrls [3], imgUrls [7]));  
      t0.addChild (new TreeNode(null, "Child with Pre No + Phone icon", imgUrls [2], imgUrls [8]));  
      TreeNode t1 = root.addChild (new TreeNode(null, "Child with grey icon", imgUrls [1], null));  
      TreeNode t10 = t1.addChild (new TreeNode(null, "Child with yellow icon", imgUrls [0], null));  
      t10.addChild (new TreeNode(null, "Child No icon", null, null));  
      TreeNode x = t10.addChild (new TreeNode(null, "x", imgUrls [0], null));  
      TreeNode y = x.addChild (new TreeNode(null, "Child with Double Pointer icon", imgUrls [4], null));  
      TreeNode z = y.addChild (new TreeNode(null, "Child with Double Pointer icon", imgUrls [4], null));  
      z.addChild (new TreeNode(null, "Child with a pre Yes +file icon", imgUrls [5], imgUrls [7]));  
      t1.addChild (new TreeNode(null, "Child 2 With Pre No + Exclamation icon", imgUrls [6], imgUrls [8]));  
      root.addChild (new TreeNode(null, "Child withFile icon", imgUrls [5], null));  
      add ("South", controls = new TreeControls(this));  
      }  
       // //  AUTO ......
 //  AUTO ...
public void start () {  
  treeThread = new Thread(this);  
  treeThread.start();  
}  
 // //  AUTO ......
 //  AUTO ...
public void stop () {  
  showStatus ("Stop called");  
  treeThread = null;  
}  
 // //  AUTO ......
 //  AUTO ...
public void run () {  
  showStatus ("Run called");  
}  
 // //  AUTO ......
 //  AUTO ...
public static void main (String args[]) {  
  TreeFrame f = new TreeFrame("Tree Test");  
  f.inApplet = false;  
  }  
  public String[][] getParameterInfo()  
  {       // // // name, type, desc, default......
  String[][] info = {  
{"fontstyle",     "stringset Plain,Italic,Bold","Style of the font for regular nodes", "Italic"},  
{"urlfontstyle", "stringset Plain,Italic,Bold","Style of the font for URL nodes",     "Plain"},  
{"fontface",    "string","Face of the font for regular nodes",  "Arial"},  
{"urlfontface", "string","Face of the font for URL nodes",      "Arial"},  
{"fontsize",    "string","Size of both Regular and URL Fonts",  "15"},  
{"width",    "number","applet width",                        "400"},  
{"height",       "number","applet height",                       "500"},  
};  
return info;  
}  
public String getAppletInfo()  
{  
String info = new String("TreeApplet\nVersion 1.0\nCopyright (C) 1996 Raja Sundarigari");  
return(info);  
}  
 // // //{{DECLARE_CONTROLS......
 // // //}}......
}  
 // //  AUTO ......
 //  AUTO ...
class TreeControls extends Panel{  
  Button addb;  
  Button delb;  
  Button siblb;  
  Button showdoc;  
  Choice iconChoice;  
  Choice preIconChoice;  
  TextField namet;  
  TextField urlnamet;  
  public TreeApplet app;  
  Label  l1;
   // //  AUTO ......
 //  AUTO ...
public TreeControls (TreeApplet a) {  
  app = a;  
  setLayout(new GridLayout(0, 1));  
  l1 = new Label("Avin Software (http://homepage.interaccess.com/~avin/)", Label.CENTER);
  l1.setFont (new Font("Arial", Font.BOLD, 13));  
  Panel p0 = new ThreedPanel();  
  p0.add (l1);  
  add (p0);  
  Panel p1 = new ThreedPanel();  
  p1.setLayout(new GridLayout(0,4));  
  p1.add (new Label("PreIcon>>"));  
  p1.add (preIconChoice = new Choice());  
  p1.add (new Label("Icon>>"));  
  p1.add (iconChoice = new Choice());  
  add (p1);  
  Panel p2 = new ThreedPanel();  
  p2.setLayout(new GridLayout(0,4));  
  p2.add (new Label("NodeName>>"));  
  p2.add (namet    = new TextField (15));  
  p2.add (new Label("Type a URL>>"));  
  p2.add (urlnamet = new TextField (15));  
  urlnamet.setText("http://");  
  add (p2);  
  Panel p3 = new ThreedPanel();  
  p3.setLayout(new GridLayout(0,4));  
  p3.add (addb    = new Button("addChild()"));  
  p3.add (siblb   = new Button("insertSibling()"));  
  p3.add (delb    = new Button("remove()"));  
  p3.add (showdoc = new Button("showDocument()"));  
  add (p3);  
  namet.setText("name goes here!");  
  iconChoice.addItem("none");  
  iconChoice.addItem("Yellow Node");  
  iconChoice.addItem("GreyNode");  
  iconChoice.addItem("Phone");  
  iconChoice.addItem("Pen");  
  iconChoice.addItem("Pointer");  
  iconChoice.addItem("File");  
  iconChoice.addItem("Exclamation");  
  iconChoice.addItem("Yes");  
  iconChoice.addItem("No");  
  iconChoice.addItem("Url");  
  preIconChoice.addItem("none");  
  preIconChoice.addItem("Yellow Node");  
  preIconChoice.addItem("GreyNode");  
  preIconChoice.addItem("Phone");  
  preIconChoice.addItem("Pen");  
  preIconChoice.addItem("Pointer");  
  preIconChoice.addItem("File");  
  preIconChoice.addItem("Exclamation");  
  preIconChoice.addItem("Yes");  
  preIconChoice.addItem("No");  
  preIconChoice.addItem("Url");  
}  
 // //  AUTO ......
 //  AUTO ...
public boolean action (Event ev, Object arg) {  
  if (ev.target instanceof Button) {  
  
     // // /auto path//......
    String nodename = namet.getText();  
    String nodeurlname = urlnamet.getText();  
    URL nodeurl;  
    try {  
    
      nodeurl = new URL(nodeurlname);  
      }  
    catch (MalformedURLException e) {  
      nodeurl = null;  
    }  
    int index = iconChoice.getSelectedIndex();  
    int preindex = preIconChoice.getSelectedIndex();  
    if ((String)arg =="addChild()") {  
    
       // // /auto path//......
      if (nodename.length() == 0) return true;  
      app.treePane.getSelection().addChild (new TreeNode(null, nodename, nodeurl,  
      index>0 ? app.imgUrls[index-1] : null,  
      preindex>0 ? app.imgUrls[preindex-1] : null));  
      }  
    else if ((String)arg =="insertSibling()") {  
      if (nodename.length() == 0) return true;  
      app.treePane.getSelection().insertSibling (  
      new TreeNode (null, nodename, nodeurl, index>0 ? app.imgUrls[index-1] : null,  
      preindex>0 ? app.imgUrls[preindex-1] : null));  
      }
    else if ((String)arg =="remove()") {  
      System.out.println("Remove called +++");
      System.out.println( "-->>> name : " + app.treePane.getSelection().name() );
      l1.setText( app.treePane.getSelection().name() );
      app.treePane.getSelection().remove ();  
      }  
    else if ((String)arg =="showDocument()") {  
      System.out.println("showDocument() called"+app.treePane.getSelection().getUrl());  
      app.getAppletContext().showDocument(app.treePane.getSelection().getUrl());  
    }  
  }  
  repaint ();  
  app.treePane.repaint ();  
return true;  
}  
}  
/*  
* TreeFrame Class  
* --- only if you need to run this as a standalone application  
* --- (as opposed to Applet)  
*/  
 // //  AUTO ......
 //  AUTO ...
class TreeFrame extends Frame{  
  public boolean inApplet;  
   // //  AUTO ......
 //  AUTO ...
public TreeFrame (String name) {  
  super (name);  
  inApplet = true;  
  TreeApplet app = new TreeApplet();  
  app.init();  
  app.start();  
  add("Center", app);  
   // // //{{INIT_CONTROLS......
  setLayout(null);  
  addNotify();  
  resize( 100,   300); //   resize(insets().left + insets().right + 430,insets().top + insets().bottom + 270);  ...
  setTitle("Untitled");  
   // // //}}......
   // // //{{INIT_MENUS......
   // // //}}......
}  
 // //  AUTO ......
 //  AUTO ...
public boolean handleEvent (Event event) {  
  if (event.id == Event.WINDOW_DESTROY) {  
  
     // // /auto path//......
    if (inApplet) dispose();  
    else  System.exit(0);  
    
  }  
return super.handleEvent(event);  
}  
 // // //{{DECLARE_CONTROLS......
 // // //}}......
 // // //{{DECLARE_MENUS......
 // // //}}......
}  
 // //  FlowCode File: TreeApplet.java.ins......
 // //  Export  File: TreeApplet.java......
 // //  Export  Date: 09:39:28 AM - 27:Aug:1999......

//  FlowCode File: TreeApplet.java.ins...
//  Export  File: TreeApplet.java...
//  Export  Date: 04:25:27 AM - 29:Aug:1999...

