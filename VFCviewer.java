/////// // HYPER LINK TO HTML_VFCviewer ---> // FILE: HTML_VFCview.ins......//////
////  AUTO ...
// AUTO 
/** Imports **///// // <--- part of framework......
	import java.awt.*;
	//import javax.swing.JOptionPane;//  import java.applet.*;
	import java.net.*;
	//////import javax.swing.JLabel; 
	//////import javax.swing.JPanel;
	/*------------*/
	// AUTO 
public class VFCviewer  extends Frame {////   public class JavaApplet  extends Applet {      ////
/** class data **///// // <--- used in framework......
	static VFCviewer  f ;///////// //   create the object application FRAME  ......////////
	static  FCview lnkView;
	URL CodeBase;
	//// // Resource Res;......
	int oldx = 0 , oldy = 0;
	int dx = 0 , dy = 0 ;
	int CrossSize =  200;
	int CrossXPos = 200 ;
	int CrossYPos = 10;
	int ZoomScale = 10;
	/**@shapeType AggregationLink*/
	/** end variables **/
	/** methods **///// // <--- used in framework......
	////  AUTO ...
	// AUTO 
boolean processPosition( Event e ){
	//System.out.println( e  );
	switch (e.id){
	case Event.MOUSE_DOWN:
		CrossXPos = e.x;//  CrossXPos+= dx;
		CrossYPos = e.y;//  CrossYPos +=  dy;
		if(  oldx != CrossXPos || oldy != CrossYPos   )
		{
			dx =   CrossXPos - oldx ;//dx =  0 ;
			dy = CrossYPos - oldy;
			oldx = CrossXPos ;//oldx = 0;
			oldy = CrossYPos ;//oldy = 0;
		}else{
			}
		return false ;
	case 403:
		if( e.key ==  1002 )//PAGE UP
		{
			System.out.println( "zoom in"  );
			lnkView.Zoom++ ;//// // Res  = new Resource( this , CodeBase );......lnkView = new FCview( getDocumentBase() , vfcname  );lnkView = new FCview( getCodeBase() , vfcname  );
			}
		if( e.key ==  1003 )//PAGE DOWN
		{
			System.out.println( "zoom out"  );
			lnkView.Zoom--  ;//// // Res  = new Resource( this , CodeBase );......lnkView = new FCview( getDocumentBase() , vfcname  );lnkView = new FCview( getCodeBase() , vfcname  );
			}
		int MINZ = 1 , MAXZ = 50;
		if( lnkView.Zoom >= MAXZ )//if( Zoom >= 25  )
		{
			lnkView.Zoom = MAXZ ;
		} else if( lnkView.Zoom < MINZ ) {
			lnkView.Zoom = MINZ ;
			}
		repaint();
		return false ;
	case Event.MOUSE_DRAG:
		{
		CrossXPos = e.x - dx;//  CrossXPos+= dx;
		CrossYPos = e.y  - dy;//  CrossYPos +=  dy;
		repaint();
		oldx = CrossXPos ;//oldx = 0;
		oldy = CrossYPos ;//oldy = 0;
		}
		return true;
		}
	return false;}
public  VFCviewer( String VFCfile ){////  public void init(){////
	//  CodeBase = getCodeBase();
	try
	{
		String  vfcname = VFCfile;//String  vfcname = "test.ins";
		lnkView = new FCview( vfcname  );//// // Res  = new Resource( this , CodeBase );......lnkView = new FCview( getDocumentBase() , vfcname  );lnkView = new FCview( getCodeBase() , vfcname  );
		//System.out.println( "---------------------DEBUG-1------------------------"  );
		lnkView.Zoom--  ;//// // Res  = new Resource( this , CodeBase );......lnkView = new FCview( getDocumentBase() , vfcname  );lnkView = new FCview( getCodeBase() , vfcname  );
		repaint();
	}catch( Exception e ){
		System.out.println("Exception <ExportFileName><LineNumber>! " +   e.getMessage() + "\n"  );
		}
	}
////  AUTO ...
// AUTO 
public static void main(String[] args){
	//////JOptionPane.showMessageDialog(null, args[0], " by RHVR (version 1.0)", JOptionPane.WARNING_MESSAGE );
	String filename = args[0].replace( "vfc://", "" );
	filename = filename.replace( "\\", "/" );
	filename = filename.replace( "%5C", "/" );
	filename = filename.replace( "//", "/" );
	//////JOptionPane.showMessageDialog(null, filename, " by RHVR (version 1.0)", JOptionPane.WARNING_MESSAGE );
	java.awt.List l1 = lnkView.ObjList = new java.awt.List( 1 );//List l1 = new List(5);    
	f = new VFCviewer(  filename );///////// //   create the object application FRAME  ......////////
	// // creating the list of 5 rows    //
	// adding the list to frame   ////lnkView.ObjList = l1;
	l1.setBounds(20, 35, 175, 500 );// ////
	//lnkView.ObjList.add("Item 1");
	f.add(lnkView.ObjList );// f.add( ObjList );
	
	f.setSize( 1000, 1000);// ////
	f.setLocation(300, 200);
	f.setTitle("VFCviewer by RedHorseVR.com (copyright 1999,2023)  - " + filename );
	f.setLayout(null);// ////
	f.setVisible(true);// ////
	//panel.setLocation(300, 200);
	
	//////f.setLayout(new FlowLayout());
	//setup frame using MAIN controls // Obj.Res.initMAINframe( Obj , "Main Window" ) ;...   // //setup frame using MAIN controls // Obj.Res.initMAINframe( Obj ,  ) ;......////setup frame using MAIN controls // Obj.Res.initMAINframe( Obj ,  ) ;
	System.out.println( "---------------------------------------------------"  );
	System.out.println( "---------------------------------------------------"  );
	System.out.println( "------------VFC Viewer by RHVR (v1.0)--------------\n"  );
	System.out.println( "NOTE: \nUse PageUP/Down to Zoom and Mouse Drag to move.\n(Copyright http://redhorsevr.com/ 1999~2023) \n" );
	System.out.println( "---------------------------------------------------"  );
	System.out.println( "---------------------------------------------------"  );
	//////JOptionPane.showMessageDialog(null, "Use PageUP/Down to Zoom and Mouse Drag to move.\n(Copyright http://redhorsevr.com/ 1999~2023) ","VFC Viewer by RHVR (v1.0)",  JOptionPane.WARNING_MESSAGE );
	}
// AUTO 
public void paint(Graphics g){
	//  drawCrossHair( g, CrossXPos, CrossYPos, CrossSize );  
	//  g.drawString( "Hello new world ..." , 20 , 20 );  
	// lnkView.zoom( g , 1 );//lnkView.zoom( g , ZoomScale );
	lnkView.draw(g , CrossXPos + 20, CrossYPos + 20 , 25);
	}
////  AUTO ...
// AUTO 
public boolean handleEvent(Event e){
	if( processPosition( e ) )
	
		{
		return true;
	}else{
		switch (e.id){
		case Event.ACTION_EVENT:
			if( "OK".equals(e.arg) || "CLOSE".equals(e.arg)  )
			
				{
				System.exit(0);//// //  THIS LOGIC CALL CAUSES A SECURITY EXCEPTION WHEN USED IN APPLET MODE......
				return true;
				}
			break;
		case Event.WINDOW_DESTROY:
			System.exit(0);//// //   THIS FAILS TO FIRE IN APPLICATION MODE......
			return true;
			}
		}
	return false; }
}       /** end of class definition **///// //  <------------ end of class definition......

//  Export  Date: 12:56:09 AM - 02:Feb:2023...

