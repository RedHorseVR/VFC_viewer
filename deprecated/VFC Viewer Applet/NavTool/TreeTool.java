/** imports **/ 
  import  jcx.*;  
  import  java.awt.*;  
  import  java.applet.Applet;  
  import  java.net.URL;  
  import  java.net.MalformedURLException;  
  import java.awt.event.*; 

public class TreeTool extends Container implements Runnable {
  public Thread treeThread;  
  static    public TreePanel treePane = null;
  public TreeNode root;  
  public URL[] imgUrls;  
  URL CodeBase = null ;
  int x = 10;
  int y  = 10 ;
  int w = 200;
  int h  = 200;

//  Action Events
  
  
    ActionListener actionListener = null;  
  public void addActionListener(ActionListener l) {  
    actionListener = AWTEventMulticaster.add(actionListener, l);  
}  
public void removeActionListener(ActionListener l) {  
  actionListener = AWTEventMulticaster.remove(actionListener, l);  
}  

public void processEvent(AWTEvent e) {  
  
  if (actionListener != null) {  
  
    actionListener.actionPerformed(new ActionEvent( this ,  (int) AWTEvent.COMPONENT_EVENT_MASK , "xxx" ));
  }          
  } 


static  public String getSelection() {
  String  S ;;
  System.out.println("getting Tree Sel \n");
  if(  treePane != null )
  { 
    S =  treePane.getSelection().name() ;
  }else{ 
    S =  " **** " ;
  } 
return S;  }
public  TreeTool( ) {
  
} 
public  TreeTool( URL CB ) {
  CodeBase = CB;
  
} 
public  TreeTool( URL CB , int xi, int yi , int wi, int hi ) {
  CodeBase = CB;
  x = xi;
  y  = yi;
  w = wi;
  h  = hi;
  init(); 
} 
private URL _getDocumentBase() {
  System.out.println("TreeTool getDocumentBase() called ...\n");
return CodeBase; }
public void init () {  
  start(); 
  System.out.println("TreeTool init() called ...\n");
  
  
  setLayout( null );
  setBackground (Color.lightGray);  
  try {  
  
    imgUrls = new URL [10];  
    imgUrls [0] = new URL (CodeBase,  "iconpics/ylnode.gif");  
    imgUrls [1] = new URL (CodeBase,  "iconpics/grnode.gif");  
    imgUrls [2] = new URL (CodeBase,  "iconpics/phone.gif");  
    imgUrls [3] = new URL (CodeBase,  "iconpics/ppen.gif");  
    imgUrls [4] = new URL (CodeBase,  "iconpics/dblptr.gif");  
    imgUrls [5] = new URL (CodeBase,  "iconpics/file.gif");  
    imgUrls [6] = new URL (CodeBase,  "iconpics/excl.gif");  
    imgUrls [7] = new URL (CodeBase,  "iconpics/yes.gif");  
    imgUrls [8] = new URL (CodeBase,  "iconpics/no.gif");  
    imgUrls [9] = new URL (CodeBase,  "iconpics/url.gif");  
    }  
  catch (MalformedURLException e) {  
    
  }  
  
  
  
  add ( treePane = new TreePanel());
  reshape( x  ,  y , w  , h );
  treePane.reshape( x ,y, w  , h );
  treePane.showPreIcons ( false );
  
  treePane.setFont ( new Font( "Areal" , Font.PLAIN , 10 ) );
  
  
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
  t0.collapse();
  TreeNode t1 = root.addChild (new TreeNode(null, "Child with grey icon", imgUrls [1], null));  
  TreeNode t10 = t1.addChild (new TreeNode(null, "Child with yellow icon", imgUrls [0], null));  
  t10.addChild (new TreeNode(null, "Child No icon", null, null));  
  TreeNode x = t10.addChild (new TreeNode(null, "x", imgUrls [0], null));  
  t10.collapse();
  TreeNode y = x.addChild (new TreeNode(null, "Child with Double Pointer icon", imgUrls [4], null));  
  TreeNode z = y.addChild (new TreeNode(null, "Child with Double Pointer icon", imgUrls [4], null));  
  z.addChild (new TreeNode(null, "Child with a pre Yes +file icon", imgUrls [5], imgUrls [7]));  
  t1.addChild (new TreeNode(null, "Child 2 With Pre No + Exclamation icon", imgUrls [6], imgUrls [8]));  
  root.addChild (new TreeNode(null, "Child withFile icon", imgUrls [5], null));  
  root.collapse();
  
}  
public boolean action (Event ev, Object arg) {  
  System.out.println("TreeTool action() called ...\n");
  if (ev.target instanceof Button) {  
  
  }  
  repaint ();  
  treePane.repaint ();
return true;  }
public void start () {  
  System.out.println("TreeTool start() called ...\n");
  treeThread = new Thread(this);  
  treeThread.start();  
}  
public void stop () {  
  System.out.println(" TreeTool stop() called ...\n");
  
  treeThread = null;  
}  
public void run () {  
  System.out.println("TreeTool run() called ...\n");
  
}  
}  
//  FlowCode File: TreeTool.java.ins...
//  Export  File: TreeTool.java...
//  Export  Date: 06:39:53 AM - 29:Aug:1999...

