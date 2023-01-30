/** recipe log **/ // <--- part of framework...
  /**<TIME_STAMP> added Cross Hairs segments using: 'CrossHair.sg'  **/
  /** <TIME_STAMP>  added a creation call class Dialog1 using frame: 'Call Dialog.fm' and button event name Dialog **/
  /** <TIME_STAMP> LANGUAGE: Java ;   started Applet / Application  MyTestApp.class   using frame: 'Basic Applet.fm'   **/

/** Imports **/ // <--- part of framework...
  import java.awt.*;  
  import java.applet.*;  
  import java.net.*;  
  import Dialog1; 
  import  jcx.*;  
  import java.awt.event.*;  
/*------------*/
public class MyTestApp  extends Applet { //   <----- start of class definition  has drawing vars for lines...
/** class data **/ // <--- used in framework...
  FCnavigate  NavTool ; // Res.IDC_LIST1.addItem(" test " );...
  URL CodeBase;  
  int KeyHit = 'X'; // // // Resource Res;.........
  String KeyHit_Object; // // // Resource Res;.........
  int oldx = 0 , oldy = 0;
  int dx = 0 , dy = 0 ;
  int CrossSize =  200;  
  int CrossXPos = 10;  
  int CrossYPos = 10;  
  /**@shapeType AggregationLink*/  
  private FCview lnkView;
  /** end variables **/  
  /** methods **/       // // // <--- used in framework.........
  Resource Res;
/** end variables **/ 
/** events **/ // <--- used in framework...
  Command searchCmd =new Command(Command.SEARCH ); 
  Command sortCmd =new Command(Command.SORT ); 
  
  public boolean keyDown( Event e , int key ) {
  KeyHit = key;
  int  DELTA=70;
  switch( key  ){
  case 1000 : // home...
    lnkView.Zoom=15; // lnkView.zoom( g , ZoomScale );...
    CrossXPos = 0 ; // oldx = 0;...
    CrossYPos = 0 ; // oldy = 0;...
    repaint();  
     //   repaint();  ...
    break; 
  case 'Z' :
    case 1002 : // PageUP...
    lnkView.Zoom+=5; // lnkView.zoom( g , ZoomScale );...
    repaint();  
    break; 
  case 'z' :
    case 1003 : // PageDOWN...
    lnkView.Zoom-=5; // lnkView.zoom( g , ZoomScale );...
    repaint();  
    break; 
  case 1004: //  UP :...
    CrossYPos -=DELTA; // //  CrossYPos +=  dy;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  case 1005 : //  DOWN :...
    CrossYPos +=DELTA; // //  CrossYPos +=  dy;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  case 1006: //  LEFT :...
    CrossXPos -=DELTA ; // //  CrossXPos+= dx;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  case 1007: //  RIGHT :...
    CrossXPos +=DELTA ; // //  CrossXPos+= dx;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  default: 
    repaint();  
    return false; 
  } 
return true;  }
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
// init method
TreePanel TreeView;
TreeTool  Tool;
public void init(){  
  CodeBase = getCodeBase();  
  Res  = new Resource( this , CodeBase );
  add(  Tool = new TreeTool( CodeBase , 10 , 100 , 200 , 300  )  ); // add(  Tool = new TreeTool( CodeBase )  );...
  Tool.show();
  Tool.addActionListener(searchCmd); 
  try 
  {  
    String  vfcname =getParameter("flowfile"); 
    lnkView = new FCview( getDocumentBase() , vfcname  ); // // // Res  = new Resource( this , CodeBase );.........
    NavTool = new FCnavigate( Res.IDC_LIST1 , getDocumentBase() , vfcname  ); // Res.IDC_LIST1.addItem(" test " );...
    Res.IDC_COMBO2.removeAll( );
    Res.IDC_COMBO2.addItem( "input" );
    Res.IDC_COMBO2.addItem( "event" );
    Res.IDC_COMBO2.addItem( "process" );
    Res.IDC_COMBO2.addItem( "set" );
    Res.IDC_COMBO2.addItem( "output" ); 
    Res.IDC_COMBO2.addItem( "loop" ); 
    Res.IDC_COMBO2.addItem( "lend" ); 
    Res.IDC_COMBO2.addItem( "branch" ); 
    Res.IDC_COMBO2.addItem( "bend" ); 
    Res.IDC_COMBO2.addItem( "path" ); 
    Res.IDC_COMBO2.addItem( "end" ); 
    Res.IDC_COMBO2.addItem( "all" ); 
    repaint();  
  }catch( Exception e ){
    System.out.println("Exception 164! " +   e.getMessage() + "\n"  );
  } 
}  
public static void main(String args[])  {  
  MyTestApp  Obj = new MyTestApp(); //   create the object application ...
  //  load the resources ignoring MAIN // Obj.Res = new Resource( Obj , getCodeBase() );...
   // Obj.CodeBase = Obj.getCodeBase();...
  Obj.Res  = new Resource( ); // Obj.Res  = new Resource( Obj , Obj.getCodeBase()  );...
  Obj.Res.initMAINframe( Obj , "Main Window" ) ; // setup frame using MAIN controls...
  try 
  {  
    try 
    {  
      Obj.lnkView = new FCview( Obj.getCodeBase() , "test.ins" ); // // // Res  = new Resource( this , CodeBase );.........
    }catch( Exception e ){
      System.out.println("Exception 179! " +   e.getMessage() + "\n"  );
    } 
  }catch( Exception   e ){
    System.out.println("Hit e:" );
  } 
}   
public void paint(Graphics g){  
   //   drawCrossHair( g, CrossXPos, CrossYPos, CrossSize );  ...
  g.drawString( "KeyHit Code =" + KeyHit , 250 , 20 );
  g.drawString( "KeyHit Object =" + KeyHit_Object  , 250 , 40 );
  lnkView.draw(g , CrossXPos + 20, CrossYPos + 20 , 25);
}  
boolean processButtons( Event e ){
  int  DZ = 4;
  if( "Tree View".equals(e.arg) )
  {  
    Dialog1 D = new Dialog1();
    Frame DialogF  = Res.initIDD_DIALOG1(  D , new TreeTool( CodeBase , 40 , 60 , 150 , 300 ) ); // Frame DialogF  = Res.initIDD_DIALOG1(   D  );...
  } else if( "Home".equals( e.arg ) ) {
    lnkView.Zoom=10; // lnkView.zoom( g , ZoomScale );...
    CrossXPos = 200 ; // oldx = 0;...
    CrossYPos = 0 ; // oldy = 0;...
    repaint();  
    repaint();  
  } else if( "Zoom In".equals( e.arg ) ) {
    if(  lnkView.Zoom > 5 )
    { 
      lnkView.Zoom+=DZ ; // lnkView.zoom( g , ZoomScale );...
    }else{ 
      lnkView.Zoom+=1 ; // lnkView.zoom( g , ZoomScale );...
    } 
    repaint();  
  } else if( "Zoom Out".equals( e.arg ) ) {
    if(  lnkView.Zoom > 5 )
    { 
      lnkView.Zoom-=DZ; // lnkView.zoom( g , ZoomScale );...
    }else{ 
      lnkView.Zoom-=1 ; // lnkView.zoom( g , ZoomScale );...
    } 
    repaint();  
  }else{ 
    switch (e.id){  
    case Event.MOUSE_DOWN:  
      CrossXPos = e.x; //   CrossXPos+= dx;...
      CrossYPos = e.y; //   CrossYPos +=  dy;...
      if(  oldx != CrossXPos || oldy != CrossYPos   )
      { 
        dx =   CrossXPos - oldx ; // dx =  0 ;...
        dy = CrossYPos - oldy;
        oldx = CrossXPos ; // oldx = 0;...
        oldy = CrossYPos ; // oldy = 0;...
      }else{ 
      } 
    case Event.MOUSE_DRAG:  
      {
      CrossXPos = e.x - dx; //   CrossXPos+= dx;...
      CrossYPos = e.y  - dy; //   CrossYPos +=  dy;...
      repaint();  
      oldx = CrossXPos ; // oldx = 0;...
      oldy = CrossYPos ; // oldy = 0;...
      }
    default:   
      return false; 
    }  
  }  
return true ;}
public boolean handleEvent(Event e){  
  System.out.println("handleEvent() called ..."+ e.arg + "\n");
  System.out.println( "Tree  Section is ..." + Tool.getSelection() +"\n"); //   System.out.println( "Tree  Section is ..." + buff +"\n");...
  if( processButtons( e ) )
  
    {  
    return true;  
  }else{ 
    switch (e.id){  
    case Event.ACTION_EVENT:  
      if(  "X".equals(e.arg) ||  "OK".equals(e.arg) || "CLOSE".equals(e.arg)  )
      
        {  
        System.exit(0);       // // //  THIS LOGIC CALL CAUSES A SECURITY EXCEPTION WHEN USED IN APPLET MODE.........
        return true;  
      }  
      break;  
    case Event.WINDOW_DESTROY:  
      System.exit(0);       // // //   THIS FAILS TO FIRE IN APPLICATION MODE.........
      return true;  
    default:   
      if (e.id == Event.WINDOW_DESTROY) {
      
      }  
    }  
  }  
return super.handleEvent( e ) ; } //   return false; }  ...
// action method
  Toolkit T;
  String  buff; 
public boolean action( Event e , Object what ) {
  System.out.println("action() called ...\n");
  buff = TreeTool.getSelection() ;
  System.out.println( "Tree  Section is ..." + buff +"\n");
  KeyHit =  NavTool.GoTo( what.toString() ); //  KeyHit =  -10;...
  KeyHit_Object  = what.toString(); // lnkView.DrawComments =((Checkbox) what).getState();...
  if(  "true".equals(KeyHit_Object) ||  "false".equals(KeyHit_Object)  )
  {  
    lnkView.DrawComments = KeyHit_Object.equals( "true" );
    repaint();  
  } else if( KeyHit > 0 ) {
    repaint();  
  }else{ 
    if (e.target instanceof TreePanel )
    { 
      System.out.println("Hello world!");  
    } 
    return false;
  }  
return true ;  } //   return false; }  ...
}       /** end of class definition **/ //  <------------ end of class definition...
// listener method
  class Command implements ActionListener {  
  static final int SEARCH =0;  
  static final int SORT =1;  
  int id;  
  
  
  public Command(int id ) 
    {  
    this.id =id;  
}  

public void actionPerformed(ActionEvent e)  
  {  
  switch(id) {  
  case SEARCH:  
    System.out.println("Searching...");  
    break;  
  case SORT:  
    System.out.println("Sorting....");  
    break;  
  }  
  }  
}   

//  FlowCode File: MyTestApp.ins...
//  Export  File: MyTestApp.java...
//  Export  Date: 06:28:10 AM - 29:Aug:1999...

