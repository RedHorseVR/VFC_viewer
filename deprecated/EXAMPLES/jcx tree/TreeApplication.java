/*  
* Avin's jcx.TreePanel Demo Application.  
* Copyright  1995, 1996, 1997 Raja Sundarigari. All Rights Reserved.  
* Raja Sundarigari (2/9/97)  
*/  
import  jcx.*;  
import  java.awt.*;  
import  java.applet.Applet;  
import  java.net.URL;  
import  java.io.*;  
import  java.net.MalformedURLException;  
/**  
 //   * TestTree Class  ...
 //   * Demonstrates the usage of jcx.TreePanel  and jcx.TreeNode classes  ...
*/  
 //  AUTO ...
public class TreeApplication extends Frame {  
  public Thread treeThread;  
  public FMTreePanel treePane;  
  public TreeNode root;  
  public static URL[] imgUrls;  
 //  AUTO ...
public TreeApplication () {  
  super ("TestTree");  
  int w = 400;  
  int h = 500;  
  resize (w,h);  
  Font f  = new Font ("Arial", Font.PLAIN, 12);  
  setLayout(new BorderLayout());  
  setBackground (Color.lightGray);  
  try {  
  imgUrls = new URL [3];  
  imgUrls [0] = new URL ("file:///C:/javabase/tt/TreeApplication/file.gif");  
  imgUrls [1] = new URL ("file:///C:/javabase/tt/TreeApplication/open.gif");  
  imgUrls [2] = new URL ("file:///C:/javabase/tt/TreeApplication/close.gif");  
}  
catch (MalformedURLException e) {  
System.out.println (e.toString());  
}  
add ("Center", treePane = new FMTreePanel ());  
treePane.showPreIcons (true);  
treePane.setFont (f);  
root = treePane.getRoot ();  
root.setIconURL (imgUrls [1]);  
root.select();  
try {  
File rt = new File ("C:\\");  
root.setName ("C:\\");  
root.setCloseIconURL (imgUrls [2]);  
treePane.setDir (root, rt);  
}  
catch (Exception e) {  
System.out.println (e.toString());  
}  
}  
 //  AUTO ...
public boolean handleEvent (Event event) {  
  if (event.id == Event.WINDOW_DESTROY) {  
  
    System.exit(0);  
  }  
return super.handleEvent(event);  
}  
 //  AUTO ...
public static void main (String args[]) {  
  TreeApplication f = new TreeApplication ();  
  f.show();  
}  
}  
 //  AUTO ...
class FMTreePanel  extends TreePanel {  
 //  AUTO ...
public void expand (TreeNode node) {  
  String name = node.name();  
  TreeNode n = new TreeNode (null, "");  
  n = node;  
  while (true) {  
  n = n.parent();  
  if (n==null) break;  
  
    name = n.name()+"\\"+name;  
  }  
  File file = new File (name);  
  if (file.isDirectory()) setDir (node, file);  
  
  }  
 //  AUTO ...
static void setDir (TreeNode nd, File rt) {  
  for (int i=0; i<rt.list().length; i++) {  
    TreeNode node = new TreeNode (null, rt.list()[i]);  
    String path = new String (rt.getPath()+"\\"+rt.list()[i]);  
     // //System.out.println (path);...
    File file = new File (path);  
    if (file.isDirectory()) {  
    
      node.setIconURL (TreeApplication.imgUrls [1]);  
      node.setDirectory();  
      node.setCloseIconURL (TreeApplication.imgUrls [2]);  
      node.collapse();  
      nd.addChild (node);  
    }  
  }    // // add all directories...
  for (int i=0; i<rt.list().length; i++) {  
    TreeNode node = new TreeNode (null, rt.list()[i]);  
    String path = new String (rt.getPath()+"\\"+rt.list()[i]);  
     // //System.out.println (path);...
    File file = new File (path);  
    if (!file.isDirectory()){  
    
      node.setIconURL (TreeApplication.imgUrls [0]);  
      nd.addChild (node);  
    }  
  }    // // add all files...
}  
};  

//  FlowCode File: TreeApplication.ins...
//  Export  File: TreeApplication.java...
//  Export  Date: 08:54:33 AM - 27:Aug:1999...

