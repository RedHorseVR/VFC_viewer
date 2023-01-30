/** recipe log **/
  /**<TIME_STAMP> added Cross Hairs segments using: 'CrossHair.sg'  **/
  /** <TIME_STAMP>  added a creation call class Dialog1 using frame: 'Call Dialog.fm' and button event name Dialog **/
  /** <TIME_STAMP> LANGUAGE: Java ;   started Applet / Application  MyTestApp.class   using frame: 'Basic Applet.fm'   **/

/** Imports **/
  import java.awt.*;
  import java.applet.*;
  import java.net.*;  
  import Dialog1; 
/*------------*/
public class MyTestApp  extends Applet {
/** class data **/
  FCnavigate  NavTool ;
  URL CodeBase;  
  int KeyHit = 'X';
  String KeyHit_Object;
  int oldx = 0 , oldy = 0;
  int dx = 0 , dy = 0 ;
  int CrossSize =  200;  
  int CrossXPos = 10;  
  int CrossYPos = 10;  
  /**@shapeType AggregationLink*/  
  private FCview lnkView;
  /** end variables **/  
  /** methods **/      
  Resource Res;
/** end variables **/ 
/** events **/
  
  public boolean keyDown( Event e , int key ) {
  KeyHit = key;
  int  DELTA=70;
  switch( key  ){
  case 1000 :
    lnkView.Zoom=15;
    CrossXPos = 0 ;
    CrossYPos = 0 ;
    repaint();  
    
    break; 
  case 'Z' :
    case 1002 :
    lnkView.Zoom+=5;
    repaint();  
    break; 
  case 'z' :
    case 1003 :
    lnkView.Zoom-=5;
    repaint();  
    break; 
  case 1004:
    CrossYPos -=DELTA;
    repaint();  
    oldx = CrossXPos ;
    oldy = CrossYPos ;
    break; 
  case 1005 :
    CrossYPos +=DELTA;
    repaint();  
    oldx = CrossXPos ;
    oldy = CrossYPos ;
    break; 
  case 1006:
    CrossXPos -=DELTA ;
    repaint();  
    oldx = CrossXPos ;
    oldy = CrossYPos ;
    break; 
  case 1007:
    CrossXPos +=DELTA ;
    repaint();  
    oldx = CrossXPos ;
    oldy = CrossYPos ;
    break; 
  default: 
    repaint();  
    return false; 
  } 
  return true;  }
  boolean process_IDD_DIALOG1( Event ev){
  if( "Dialog".equals(ev.arg) )
  { 
    Dialog1  DlgObj = new Dialog1();
    DlgObj.frame = Res.initIDD_DIALOG1( DlgObj );
    return true; 
  } 
return false;}

// init method
public void init(){
  CodeBase = getCodeBase();  
  Res  = new Resource( this , CodeBase );
  try 
  {  
    String  vfcname =getParameter("flowfile");
    lnkView = new FCview( getDocumentBase() , vfcname  );
    NavTool = new FCnavigate( Res.IDC_LIST1 , getDocumentBase() , vfcname  );
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
    System.out.println("Exception 113! " +   e.getMessage() + "\n"  );
  } 
}  
public static void main(String args[])  {
  MyTestApp  Obj = new MyTestApp();
  //  load the resources ignoring MAIN
  
  Obj.Res  = new Resource( );
  Obj.Res.initMAINframe( Obj , "Main Window" ) ;
  try 
  {  
    try 
    {  
      Obj.lnkView = new FCview( Obj.getCodeBase() , "test.ins" );
    }catch( Exception e ){
      System.out.println("Exception 128! " +   e.getMessage() + "\n"  );
    } 
  }catch( Exception   e ){
    System.out.println("Hit e:" );
  } 
} 
public void paint(Graphics g){  
  
  g.drawString( "KeyHit Code =" + KeyHit , 250 , 20 );
  g.drawString( "KeyHit Object =" + KeyHit_Object  , 250 , 40 );
  lnkView.draw(g , CrossXPos + 20, CrossYPos + 20 , 25);
}  
boolean processPosition( Event e ){
  if( "Tree View".equals(e.arg) )
  { 
    Dialog1  DlgObj = new Dialog1();
    DlgObj.frame = Res.initIDD_DIALOG1( DlgObj );
  } else if( "Home".equals( e.arg ) ) {
    lnkView.Zoom=15;
    CrossXPos = 200 ;
    CrossYPos = 0 ;
    repaint();  
    repaint();  
  } else if( "Zoom In".equals( e.arg ) ) {
    lnkView.Zoom+=5;
    repaint();  
  } else if( "Zoom Out".equals( e.arg ) ) {
    lnkView.Zoom-=5;
    repaint();  
  }else{ 
    switch (e.id){  
    case Event.MOUSE_DOWN:  
      CrossXPos = e.x;
      CrossYPos = e.y;
      if(  oldx != CrossXPos || oldy != CrossYPos   )
      { 
        dx =   CrossXPos - oldx ;
        dy = CrossYPos - oldy;
        oldx = CrossXPos ;
        oldy = CrossYPos ;
      }else{ 
      } 
    case Event.MOUSE_DRAG:  
      {
      CrossXPos = e.x - dx;
      CrossYPos = e.y  - dy;
      repaint();  
      oldx = CrossXPos ;
      oldy = CrossYPos ;
      }
    default:   
      return false; 
    }  
  } 
return true ;}
public boolean handleEvent(Event e){  
  if( processPosition( e ) )
  
    {  
    return true;  
  }else{ 
    switch (e.id){  
    case Event.ACTION_EVENT:  
      if(  "X".equals(e.arg) ||  "OK".equals(e.arg) || "CLOSE".equals(e.arg)  )
      
        {  
        System.exit(0);      
        return true;  
      }  
      break;  
    case Event.WINDOW_DESTROY:  
      System.exit(0);      
      return true;  
    default:   
    }  
  }  
return super.handleEvent( e ) ; }
// action method
public boolean action( Event e , Object what ) {
  KeyHit =  NavTool.GoTo( what.toString() );
  KeyHit_Object  = what.toString();
  if(  "true".equals(KeyHit_Object) ||  "false".equals(KeyHit_Object)  )
  {  
    lnkView.DrawComments = KeyHit_Object.equals( "true" );
    repaint();  
  } else if( KeyHit > 0 ) {
    repaint();  
  }else{ 
    repaint();  
    return false;
  }  
return true ;  }
}       /** end of class definition **/
//  FlowCode File: MyTestApp.ins...
//  Export  File: MyTestApp.java...
//  Export  Date: 10:06:16 PM - 26:Aug:1999...

