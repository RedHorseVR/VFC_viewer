/** recipe log **/ // <--- part of framework...
  /** created Dialog1.class using frame: 'Dialog Applet.fm'   **/

/** Imports **/
  import java.awt.*;  
  import java.applet.*;  
  import java.net.*;  
  import jcx.*;  
/*------------*/
public class Dialog1  extends Applet {
/** class event procs **/ // <--- used in framework...
/** class data **/ // <--- used in framework...
  Frame frame=null; //    <----- frame is set when the dialog is created via the resources class object and this framework...
  TreePanel TreeView;
  URL CodeBase;  
/* end static variables */ 
public TreePanel  initTree() {
  TreeNode root;
  TreePanel treePane;
  treePane = new TreePanel(); // Scontainer.add ( treePane = new TreePanel());...
  treePane.showPreIcons ( false ); //   treePane.showPreIcons (true);  ...
  treePane.setFont ( new Font( "Areal" , Font.PLAIN , 10) );
  treePane.setUrlFont ( new Font( "Areal" , Font.PLAIN , 10) );
   //   treePane.setFont (f);  ...
   //   treePane.setUrlFont (uf);  ...
   //   treePane.addNotify();...
  URL[] imgUrls = null;
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
     // // // do error recovery .........
  }  
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
return treePane ;    }
public void init(){  
   // frame.add( TreeView  );...
  System.out.println("init Dialog\n");
}   
public boolean action( Event e , Object what ) {
  System.out.println("Hello world!");  
  if (e.target instanceof TreePanel )
  { 
  } 
return true ;  } //   return false; }  ...
public boolean handleEvent(Event e){  
  switch (e.id){  
  case Event.ACTION_EVENT:
    if( "OK".equals(e.arg) || "Cancel".equals(e.arg) ||  "X".equals(e.arg)  )
    {  
      if( frame!=null  )
      {  
        frame.hide();
        frame.dispose();
      }  
      return true;
    }  
  case Event.WINDOW_DESTROY:
    if( frame!=null  )
    {  
      frame.hide();
      frame.dispose();
    }  
    return true;
    
  }   
return super.handleEvent( e ) ; } //   return false; }  ...
}       /* end of class definition */
  
//  FlowCode File: Dialog1.ins...
//  Export  File: Dialog1.java...
//  Export  Date: 04:10:30 AM - 29:Aug:1999...

