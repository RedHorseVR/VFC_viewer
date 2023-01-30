/* Generated Resource Class - Do not edit.  You should only extend this class.  */

/* Imports */
import java.awt.*;
import java.net.*;
import java.applet.*;

class Resource {
   Frame DIALOG_FRAME_IDD_DIALOG1;

   Frame initIDD_DIALOG1( Applet c ){
	c.setLayout(null);
	c.add( IDC_CLOSEDLG=new Button(     "X") );
	IDC_CLOSEDLG.reshape(SCALE_X(0),SCALE_Y(14),SCALE_W(13),SCALE_H(10) );
	DIALOG_FRAME_IDD_DIALOG1 = new Frame ( "VFC Tree" );
	initIDR_MENU1();
	DIALOG_FRAME_IDD_DIALOG1.setMenuBar( IDR_MENU1 );
	DIALOG_FRAME_IDD_DIALOG1.add( "Center", c );
	DIALOG_FRAME_IDD_DIALOG1.reshape(SCALE_X(300),SCALE_Y(252),SCALE_W(173)+20,SCALE_H(161)+20 );
	DIALOG_FRAME_IDD_DIALOG1.show();
	return DIALOG_FRAME_IDD_DIALOG1;
   }

   Frame MAIN_FRAME;
   void initMAIN( Applet c ){
	c.setLayout(null);
	c.add( IDOK=new Button(  "Zoom In") );
	IDOK.reshape(SCALE_X(305),SCALE_Y(0),SCALE_W(39),SCALE_H(15) );
	c.add( IDC_COMMENTS=new Checkbox(        "Comments") );
	IDC_COMMENTS.reshape(SCALE_X(125),SCALE_Y(0),SCALE_W(50),SCALE_H(8) );
	c.add( IDC_COMBO2= new Choice() );
	IDC_COMBO2.addItem( " item 1 " );
	IDC_COMBO2.addItem( " item 2 " );
	IDC_COMBO2.addItem( " item 3 " );
	IDC_COMBO2.reshape(SCALE_X(5),SCALE_Y(230),SCALE_W(30),SCALE_H(40) );
	c.add( IDOK2=new Button(  "Zoom Out") );
	IDOK2.reshape(SCALE_X(345),SCALE_Y(0),SCALE_W(39),SCALE_H(15) );
	c.add( IDOK3=new Button(  "Home") );
	IDOK3.reshape(SCALE_X(265),SCALE_Y(0),SCALE_W(39),SCALE_H(15) );
	c.add( IDC_USER1=new TreeTool( c , SCALE_X(5),SCALE_Y(0),SCALE_W(115),SCALE_H(225)  ) );
	c.add( IDC_LIST1= new List() );
	IDC_LIST1.reshape(SCALE_X(5),SCALE_Y(245),SCALE_W(115),SCALE_H(57) );
	c.add( IDC_SHOW=new Checkbox(        "Show Object Browser") );
	IDC_SHOW.reshape(SCALE_X(180),SCALE_Y(0),SCALE_W(85),SCALE_H(8) );
   }

   void reshapeMAIN( Frame f ){
	f.reshape(SCALE_X(50),SCALE_Y(100),SCALE_W(459)+20,SCALE_H(304) + 20 );
   }

   Frame initMAINframe( Applet c, String title ) {
	Frame MAIN_FRAME=new Frame( title );
	initIDR_MENU1();
	MAIN_FRAME.setMenuBar( IDR_MENU1 );
	MAIN_FRAME.add( "Center", c );
	reshapeMAIN(MAIN_FRAME);
	MAIN_FRAME.show();
	initMAIN(c);
	return MAIN_FRAME; 
   }

   public MenuBar IDR_MENU1 = new MenuBar();
   void initIDR_MENU1(){
	Menu Popup1;
	Popup1 = new Menu("File");
	Popup1.add("Open...");
	Popup1.add("Close...");
	Popup1.add("Test1");
	Popup1.add("Test2");
	Popup1.add("Test3");
	Popup1.add("Test4");
	Menu Popup2;
	Popup2 = new Menu("Run ...");
	Popup2.add("test");
	Menu Popup3;
	Popup3 = new Menu("no pop");
	Popup3.add("test");
	IDR_MENU1.add( Popup1 );
	IDR_MENU1.add( Popup2 );
	IDR_MENU1.add( Popup3 );
   }

   Resource( Applet c , URL cBase  ){
	CodeBase=cBase;
	initMAIN( c);
	Initialize();
	c.repaint();
   }

   Resource( ) {
	Initialize();
   }

   void Initialize( ) {
   }

   public void paint(Graphics g){
	g.setFont( MAIN_FONT );
   }

   int SCALE_X(int x){ return (int)(x*SCALE); }
   int SCALE_Y(int x){ return (int)(x*SCALE); }
   int SCALE_W(int x){ return (int)(x*SCALE); }
   int SCALE_H(int x){ return (int)(x*SCALE); }

   /* public resource data */   //input

   URL CodeBase=null;
   double SCALE = 1.6;
   public Font  MAIN_FONT = null;
Button  IDC_CLOSEDLG;
Button  IDOK;
Checkbox  IDC_COMMENTS;
Choice IDC_COMBO2;
Button  IDOK2;
Button  IDOK3;
TreeTool  IDC_USER1;
List IDC_LIST1;
Checkbox  IDC_SHOW;

}    /* end of Resource class */

