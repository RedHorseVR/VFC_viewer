import  jcx.*;  
import  java.awt.*;  
import  java.applet.Applet;  
import  java.net.URL;  
import  java.net.MalformedURLException;  

public class TreeTool extends java.applet.Applet implements Runnable{
  public Thread treeThread;  
  public TreePanel treePane;  
  public TreeNode root;  
  public URL[] imgUrls;  

public void init () {  
  String width  = getParameter("width");  
  int w = (width == null) ? 400 : Integer.valueOf(width).intValue();  
  String height  = getParameter("height");  
  int h = (height == null) ? 500 : Integer.valueOf(height).intValue();  
  
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
    
  }  
  
  
  setLayout( null );
  add ( treePane = new TreePanel());
  // reshape( 0 , 0, w , h );treePane.reshape( w*3/4 , 0, w/4  , h );
  treePane.reshape( 0 , 0, w  , h );
  treePane.showPreIcons ( false );
  
  
  
  
  root = treePane.getRoot ();  
  root.setIconURL (imgUrls [0]);  
  root.setName  ("Inputs");
  root.select();  
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
  
}  
public boolean action (Event ev, Object arg) {  
  if (ev.target instanceof Button) {  
  
  }  
  repaint ();  
  treePane.repaint ();
return true;  }
public void start () {  
  treeThread = new Thread(this);  
  treeThread.start();  
}  
public void stop () {  
  showStatus ("Stop called");  
  treeThread = null;  
}  
public void run () {  
  showStatus ("Run called");  
}  
}  
//  FlowCode File: TreeTool.java.ins...
//  Export  File: TreeTool.java...
//  Export  Date: 03:40:25 PM - 27:Aug:1999...

