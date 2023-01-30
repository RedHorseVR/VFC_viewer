/**  C:\WINDOWS\PROFILES\LUIS\DESKTOP\FCVIEW~1\RESOURCE\RESOUR~1.JAV  **/   //   FILE:C:\WINDOWS\PROFILES\LUIS\DESKTOP\FCVIEW~1\RESOURCE\RESOUR~1.JAV...
   //  /* Generated Resource Class - Do not edit.  You should only extend this class.  */...
   //  /* Imports */...
  import java.awt.*;  
  import java.net.*;  
  import java.applet.*;  
class Resource extends Applet  { 
  Frame DIALOG_FRAME_IDD_DIALOG1;  
Frame initIDD_DIALOG1( Container D , Container c ) 
  {  
  Frame F = initIDD_DIALOG1( D ) ; //  Frame F  initIDD_DIALOG1( c ) ;...
  DIALOG_FRAME_IDD_DIALOG1.add( c ); // D.add( DIALOG_FRAME_IDD_DIALOG1 );...
return F; }  // return null; }...
Frame initIDD_DIALOG1( Container c ) 
  {  
  DIALOG_FRAME_IDD_DIALOG1 =new Frame ( "VFC Tree" );  
  if( c != null   ) // if( c != null   )...
  { //  { ...
    DIALOG_FRAME_IDD_DIALOG1.add( c ); //  DIALOG_FRAME_IDD_DIALOG1.add( "Center", c ); ...
    c.setLayout(null);  //     DIALOG_FRAME_IDD_DIALOG1.setLayout(null);...
    c.add( IDC_CLOSEDLG=new Button( "X") ); //   DIALOG_FRAME_IDD_DIALOG1.add( IDC_CLOSEDLG=new Button( "X") );...
    IDC_CLOSEDLG.reshape( SCALE_X(10), SCALE_Y(30), SCALE_W(13),SCALE_H(10) ); //   IDC_CLOSEDLG.reshape(SCALE_X(0),SCALE_Y(0),SCALE_W(13),SCALE_H(10) ); ...
  } //  } ...
  initIDR_MENU1();  
  DIALOG_FRAME_IDD_DIALOG1.setMenuBar( IDR_MENU1 ); //   DIALOG_FRAME_IDD_DIALOG1.setMenuBar( IDR_MENU1 ); ...
  DIALOG_FRAME_IDD_DIALOG1.reshape(SCALE_X(300),SCALE_Y(252),SCALE_W(173)+20,SCALE_H(161)+20 );  
  DIALOG_FRAME_IDD_DIALOG1.show();  
return DIALOG_FRAME_IDD_DIALOG1;  
}  
public boolean action( Event e , Object what ) {
  System.out.println("Resource action\n"); // System.out.println("Resource action");...
return true ;  } //   return false; }  ...
public boolean handleEvent(Event e){  
  System.out.println("Resource handleEvent\n"); // System.out.println("Resource handleEvent\n");...
  switch (e.id){  
  case Event.ACTION_EVENT:
    if( "OK".equals(e.arg) || "Cancel".equals(e.arg) ||  "X".equals(e.arg)  )
    {  
      return true;
    }  
  case Event.WINDOW_DESTROY:
    return true;
    
  }   
return super.handleEvent( e ) ; } //   return false; }  ...
Frame MAIN_FRAME;  
void initMAIN( Container c )  
  {  
  c.setLayout(null);  
  c.add( IDOK=new Button( "Zoom In") );  
  IDOK.reshape(SCALE_X(225),SCALE_Y(0),SCALE_W(39),SCALE_H(15) );  
  c.add( IDC_CHECK2=new Checkbox( "Comments") );  
  IDC_CHECK2.reshape(SCALE_X(130),SCALE_Y(0),SCALE_W(55),SCALE_H(15) );  
  c.add( IDC_BUTTON1=new Button( "Tree View") );  
  IDC_BUTTON1.reshape(SCALE_X(70),SCALE_Y(0),SCALE_W(42),SCALE_H(14) );  
  c.add( IDC_COMBO2= new Choice() );  
  IDC_COMBO2.addItem( " item 1 " );  
  IDC_COMBO2.addItem( " item 2 " );  
  IDC_COMBO2.addItem( " item 3 " );  
  IDC_COMBO2.reshape(SCALE_X(0),SCALE_Y(1),SCALE_W(54),SCALE_H(30) );  
  c.add( IDOK2=new Button( "Zoom Out") );  
  IDOK2.reshape(SCALE_X(265),SCALE_Y(0),SCALE_W(39),SCALE_H(15) );  
  c.add( IDC_BUTTON3=new Button( "X") );  
  IDC_BUTTON3.reshape(SCALE_X(115),SCALE_Y(0),SCALE_W(13),SCALE_H(14) );  
  c.add( IDC_LIST1= new List() );  
  IDC_LIST1.reshape(SCALE_X(0),SCALE_Y(15),SCALE_W(54),SCALE_H(275) );  
  c.add( IDC_VERT= new Scrollbar( Scrollbar.VERTICAL ) );  
  IDC_VERT.reshape(SCALE_X(54),SCALE_Y(0),SCALE_W(11),SCALE_H(290) );  
  c.add( IDC_HORZ= new Scrollbar( Scrollbar.HORIZONTAL ) );  
  IDC_HORZ.reshape(SCALE_X(55),SCALE_Y(290),SCALE_W(335),SCALE_H(9) );  
  c.add( IDOK3=new Button( "Home") );  
  IDOK3.reshape(SCALE_X(185),SCALE_Y(0),SCALE_W(39),SCALE_H(15) );  
}  
void reshapeMAIN( Frame f )  
  {  
  f.reshape(SCALE_X(50),SCALE_Y(100),SCALE_W(403)+20,SCALE_H(304) + 20 );  
}  
Frame initMAINframe( Container c, String title )  
  {  
  Frame MAIN_FRAME=new Frame( title );  
  initIDR_MENU1();  
  MAIN_FRAME.setMenuBar( IDR_MENU1 );  
  MAIN_FRAME.add( "Center", c );  
  reshapeMAIN(MAIN_FRAME);  
  MAIN_FRAME.show();  
  initMAIN(c);  
return MAIN_FRAME;  
}  
public MenuBar IDR_MENU1 =new MenuBar();  
void initIDR_MENU1()  
  {  
  Menu Popup1;  
  Popup1 =new Menu("File");  
  Popup1.add("Open...");  
  Popup1.add("Close...");  
  Popup1.add("Test1");  
  Popup1.add("Test2");  
  Popup1.add("Test3");  
  Popup1.add("Test4");  
  Menu Popup2;  
  Popup2 =new Menu("Run ...");  
  Popup2.add("test");  
  Menu Popup3;  
  Popup3 =new Menu("no pop");  
  Popup3.add("test");  
  IDR_MENU1.add( Popup1 );  
  IDR_MENU1.add( Popup2 );  
  IDR_MENU1.add( Popup3 );  
}  
Resource( Container c , URL cBase )  
  {  
  CodeBase=cBase;  
  initMAIN( c);  
  Initialize();  
  c.repaint();  
}  
Resource( )  
  {  
  Initialize();  
}  
void Initialize( )  
  {  
}  
public void paint(Graphics g)  
  {  
  g.setFont( MAIN_FONT );  
}  
int SCALE_X(int x)  
  {  
return (int)(x*SCALE);  
}  
int SCALE_Y(int x)  
  {  
return (int)(x*SCALE);  
}  
int SCALE_W(int x)  
  {  
return (int)(x*SCALE);  
}  
int SCALE_H(int x)  
  {  
return (int)(x*SCALE);  
}  
 //  /* public resource data */...
 //  //input...
URL CodeBase=null;  
double SCALE =1.6;  
public Font MAIN_FONT =null;  
Button IDC_CLOSEDLG;  
Button IDOK;  
Checkbox IDC_CHECK2;  
Button IDC_BUTTON1;  
Choice IDC_COMBO2;  
Button IDOK2;  
Button IDC_BUTTON3;  
List IDC_LIST1;  
Scrollbar IDC_VERT;  
Scrollbar IDC_HORZ;  
Button IDOK3;  
 //  /* end of Resource class */...
}  

//  FlowCode File: RESOUR~1.JAV.ins...
//  Export  File: Resource.java...
//  Export  Date: 04:11:15 AM - 29:Aug:1999...

