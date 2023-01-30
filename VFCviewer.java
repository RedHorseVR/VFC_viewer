


/** Imports **/
	import java.awt.*;
	import javax.swing.JOptionPane;
	import java.net.*;
	/*------------*/
	
public class VFCviewer  extends Frame {
/** class data **/
	URL CodeBase;
	
	int oldx = 0 , oldy = 0;
	int dx = 0 , dy = 0 ;
	int CrossSize =  200;
	int CrossXPos = 10;
	int CrossYPos = 10;
	int ZoomScale = 10;
	/**@shapeType AggregationLink*/
	private FCview lnkView;
	/** end variables **/
	/** methods **/
	
	
boolean processPosition( Event e ){
	
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
		return false ;
	case 403:
		if( e.key ==  1002 )
		{
			System.out.println( "zoom in"  );
			lnkView.Zoom++ ;
			}
		if( e.key ==  1003 )
		{
			System.out.println( "zoom out"  );
			lnkView.Zoom--  ;
			}
		int MINZ = 1 , MAXZ = 50;
		if( lnkView.Zoom >= MAXZ )
		{
			lnkView.Zoom = MAXZ ;
		} else if( lnkView.Zoom < MINZ ) {
			lnkView.Zoom = MINZ ;
			}
		repaint();
		return false ;
	case Event.MOUSE_DRAG:
		{
		CrossXPos = e.x - dx;
		CrossYPos = e.y  - dy;
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
		lnkView = new FCview( vfcname  );
		System.out.println( "---------------------DEBUG-1------------------------"  );
		lnkView.Zoom--  ;
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
	JOptionPane.showMessageDialog(null, filename, " by RHVR (version 1.0)", JOptionPane.WARNING_MESSAGE );
	VFCviewer  f = new VFCviewer(  filename );
	f.setSize( 1000, 1000);
	f.setVisible(true);
	f.setLayout(new FlowLayout());
	//setup frame using MAIN controls // Obj.Res.initMAINframe( Obj , "Main Window" ) ;...   // //setup frame using MAIN controls // Obj.Res.initMAINframe( Obj ,  ) ;......
	////
	}


public void paint(Graphics g){
	
	
	// lnkView.zoom( g , 1 );
	lnkView.draw(g , CrossXPos + 20, CrossYPos + 20 , 25);
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

//  Export  Date: 04:45:17 PM - 30:Jan:2023...

