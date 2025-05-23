


/** Imports **/
	import java.awt.*;
	//import javax.swing.JOptionPane;
	import java.net.*;
	////
	import java.util.Scanner;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import java.io.File ;
	/*------------*/
	
public class VFCviewer  extends Frame {
/** class data **/
	static VFCviewer  f ;
	static  FCview FCV;
	URL CodeBase;
	
	int dx = 0 , dy = 0 ;
	
	int CrossSize =  200;
	
	int X = 500;
	int Y = 50;
	int CrossXPos = X ;
	int CrossYPos = Y ;
	int oldx = CrossXPos , oldy = CrossYPos ;
	
	Graphics G = null ;
	/**@shapeType AggregationLink*/
	/** end variables **/
	/** methods **/
	
	
void setPosition( int x , int y ){
	X = x ;
	Y = y ;
	CrossXPos = X ;
	CrossYPos = Y ;
	oldx = CrossXPos;
	oldy = CrossYPos ;
	}
boolean processPosition( Event e ){
	
	switch (e.id){
	case Event.MOUSE_DOWN:
		
		CrossXPos = e.x;
		CrossYPos = e.y;
		
		System.out.println( "Mouse Down at: -------------------> " + e.x + " - " +  e.y );
		System.out.println( "Head at --------------------> " + FCV.Head.Xo + " - " + FCV.Head.Yo  );
		if(  oldx != CrossXPos || oldy != CrossYPos   )
		{
			
			dx =   CrossXPos - oldx ;
			dy = CrossYPos - oldy;
			oldx = CrossXPos ;
			oldy = CrossYPos ;
			
		}else{
			}
		repaint();
		return false ;
	case  701 : {
		String L = "" + e.target ;
		
		Pattern p1 = Pattern.compile( "^.*="  );
		Pattern p2 = Pattern.compile( "]$"  );
		Matcher m1 = p1.matcher(  L );
		String result = m1.replaceFirst( "" );
		Matcher m2 = p2.matcher(  result  );
		result = m2.replaceFirst( "" );
		Point pos = FCV.getObjectStatementXY(  result  ) ;
		System.out.println(  "\n\t>>>Object:" + result +" at " + pos.x + " - " + pos.y +"\n"  );
		
		
		int dY = 50 - pos.y ;
		int dX = 450 - pos.x ;
		
		setPosition( X +dX, Y + dY ) ;
		
		
		
		repaint();
		
		return true; }
	case 403:
		if( e.key ==  1002 )
		{
			FCV.Zoom++ ;
			System.out.println( "zoom in to " + FCV.Zoom  );
			}
		if( e.key ==  1003 )
		{
			FCV.Zoom--  ;
			System.out.println( "zoom out to " + FCV.Zoom   );
			}
		int MINZ = 1 , MAXZ = 50;
		if( FCV.Zoom >= MAXZ )
		{
			FCV.Zoom = MAXZ ;
		} else if( FCV.Zoom < MINZ ) {
			FCV.Zoom = MINZ ;
			}
		StatementObject S  = (StatementObject)   FCV.SO.elementAt(  FCV.currentObject - 1  ) ;
		
		
		
		repaint();
		return true;
	case Event.MOUSE_DRAG:
		{
		CrossXPos = e.x - dx;
		CrossYPos = e.y  - dy;
		X = CrossXPos ;
		Y = CrossYPos ;
		repaint();
		oldx = CrossXPos ;
		oldy = CrossYPos ;
		
		
		}
		return true;
		}
	return false;}
public  VFCviewer( String VFCfile ){
	
	try
	{
		String  vfcname = VFCfile;
		FCV = new FCview( vfcname  );
		
		FCV.Zoom--  ;
		repaint();
	}catch( Exception e ){
		System.out.println("Exception <ExportFileName><LineNumber>! " +   e.getMessage() + "\n"  );
		}
	}


public static void main(String[] args){
	////
	String filename = args[0].replace( "vfc://", "" );
	filename = filename.replace( "\\", "/" );
	filename = filename.replace( "%5C", "/" );
	filename = filename.replace( "//", "/" );
	////
	java.awt.List l1 = FCV.ObjList = new java.awt.List( 1 );
	f = new VFCviewer(  filename );
	
	// adding the list to frame   //
	l1.setBounds(20, 35, 175, 500 );
	
	f.add(FCV.ObjList );
	
	f.setSize( 1000, 1000);
	f.setLocation(300, 200);
	f.setTitle("VFCviewer by RedHorseVR.com and MaxxImmersive.com  (copyright 1999,2023)  - " + filename );
	f.setLayout(null);
	f.setVisible(true);
	
	FCV.Zoom--  ;
	
	////
	System.out.println( "---------------------------------------------------------------------------------------------------"  );
	System.out.println( "-----VFC Viewer based on Java code base by RHVR (v1.0)- updated by MaxxImmersive.com (v1.1x) ------"  );
	System.out.println( "---------------------------------------------------------------------------------------------------"  );
	System.out.println( "NOTE: \nUse PageUP/Down to Zoom and Mouse Drag to move.\n(Copyright by redhorsevr.com 1999 and updates by maxximmersive.com 2023) \n" );
	File fil = new File( filename );
	filename = "vfc://" + fil.getAbsolutePath() ;
	System.out.println( "VIEWING FILE: "  + filename );
	System.out.println( "---------------------------------------------------------------------------------------------------"  );
	System.out.println( "---------------------------------------------------------------------------------------------------"  );
	////
	}

public void paint(Graphics g){
	G = g;
	
	
	FCV.draw(g , X , Y,  FCV.Zoom );
	}


public boolean handleEvent(Event e){
	
	
	if( processPosition( e ) )
	
		{
		return true;
	}else{
		switch (e.id){
		case Event.ACTION_EVENT:
			if( "OK".equals(e.arg) || "CLOSE".equals(e.arg)  )
			
				{
				System.exit(0);
				return true;
				}
			break;
		case Event.WINDOW_DESTROY:
			System.exit(0);
			return true;
			}
		}
	return false; }
}       /** end of class definition **/

//  Export  Date: 03:24:33 PM - 04:Mar:2023...

