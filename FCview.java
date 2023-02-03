/** Imports **/
	import java.awt.*;
	
	import java.util.*;
	import java.io.*;
	import java.net.*;
	
public class FCview extends Frame {
		
		static public java.awt.List  ObjList;
		
		Color SelectedObjectColor  = new Color(  255  , 0 , 0 ) ;
		Color CommentObjectColor  = new Color(  100 , 100 , 100 ) ;
		boolean first_draw = true;
		private  Point p ;
		private String VFCfile;
		private StatementObject SO1 , SO2 , SO3 , SO4 , SO5 , SO6 , SO7 ;
		public  StatementObject Head ;
		public Vector SO;
		Stack iterativeS;
		Stack logicalS;
		URL DocBase = null;
		private String LastType = null ;
		public int Zoom = 20;
		public int currentObject =1;
		public boolean DrawComments = true ;
	
	String GetLines ( InputStream  st ) throws IOException
		{
		int c;
		String  buff = new String("");
		
		while( ( c = st.read() ) != -1 ) {
			if(  c == '\n' )
			{
				System.out.println( ':' + buff );
				if(  !buff.startsWith(";") &&  !buff.startsWith("\r")  )
				{
					SO.addElement( track_stack_links(  (SO1=new StatementObject( buff , SO )) )  ) ;
					////
					if( SO1.Type.equals("input") || SO1.Type.equals("event")  )
					{
						ObjList.add( SO1.Statement  );
						}
					if( SO.size() == 1  )
					{
						Head = SO1;
						}
				}else{
					}
				buff = "";
			}else{
				buff = buff + (char)c;
				}
			}
		
		
		return null; }
	
	public void load( String fname )
		{
		InputStream in =null;
		
		
		try {
		
			
			//  in =new URL( fname).openStream();
			in = new FileInputStream( fname  );
			}
		catch(Exception e) {
			System.out.println("load() Exception <LineNumber>! " +   e.getMessage() + "\n"  );
			}
		try
		{
			
			
			GetLines( in ) ;
		}catch( Exception e ){
			System.out.println("get lines Exception <Linenumber>! " +   e.getMessage() + "\n"  );
			}
		try {
		
			if (in != null)
			
				in.close();
				
			}
		catch(Exception e) {
			}
		
		repaint();
		}
	
	FCview (  String filename ) throws IOException {
		Frame f = this;
		
		InputStream is =null;
		VFCfile = filename;
		logicalS = new Stack( ) ;
		iterativeS = new Stack( ) ;
		
		
		SO = new Vector( 200 );
		
		load( VFCfile );
		
		
		
		
		}
	
	private StatementObject track_stack_links( StatementObject SO ) {
		if( SO.Type.equals("branch")  )
		{
			try
			{
				logicalS.push( SO );
			}catch( EmptyStackException  e ){
				}
		} else if(   SO.Type.equals("path" )  ) {
			try
			{
				StatementObject S = (StatementObject) logicalS.peek( );
				if(  S.Type.equals("branch")  )
				{
					S.PLink.addElement( SO );
					SO.Link = S ;
					}
			}catch( EmptyStackException  e ){
				}
		} else if(   SO.Type.equals("loop" )  ) {
			try
			{
				iterativeS.push( SO );
			}catch( EmptyStackException  e ){
				}
		} else if( SO.Type.equals("bend") ) {
			try
			{
				StatementObject Sb = (StatementObject) logicalS.pop(  );
				SO.Link = Sb;
				Sb.Link = SO ;
			}catch( EmptyStackException  e ){
				}
		} else if(  SO.Type.equals("lend") ) {
			try
			{
				SO.Link = (StatementObject) iterativeS.pop(  );
			}catch( EmptyStackException  e ){
				}
			}
		LastType = SO.Type;
		return SO;  }
	
	public Point  drawBounds( ){
		
		Point P = new Point( 0 , 0 );
		StatementObject S ;
		for( int i =0 ; i< SO.size()  ; i++ ) {
			S  = (StatementObject)  SO.elementAt( i ) ;
			if( S.xmax - S.xmin> P.x  )
			{
				P.x = S.xmax - S.xmin ;
				}
			if( S.y2 - Head.Yo > P.y  )
			{
				P.y = S.y2 - Head.Yo ;
				}
			} // end for
		
		return P; }
	
	public Point getObjectStatementXY( String ObjectStatement ){
		Point XY = new Point( 0 , 0 );
		
		StatementObject S ;
		System.out.println( "Head at --------------------> " + Head.Xo + " - " + Head.Yo  );
		for( int i =0 ; i< SO.size()  ; i++ ) {
			S  = (StatementObject)  SO.elementAt( i ) ;
			if( S.Statement.equals( ObjectStatement)  )
			{
				XY.x = S.x1;
				XY.y= S.y1;
				return XY;
				}
			} // end for
		
		return XY;
		
		}
	public void draw( Graphics g, int x, int y, int z){
		
		
		StatementObject S ;
		if( first_draw  )
		{
			
			S  = (StatementObject)  SO.elementAt( 0 ) ;
			
			first_draw = false;
			S.calculate( g, x, y, z );
			}
		zoom( g , x, y );
		for( int i =0 ; i< SO.size()  ; i++ ) {
			if( i+1 == currentObject  )
			{
				g.setColor( SelectedObjectColor  );
			}else{
				g.setColor(  Color.black  );
				}
			S  = (StatementObject)  SO.elementAt( i ) ;
			S.show_comments = DrawComments ;
			S.transform(  x ,  y , z) ;
			if( S.xmax > 0 &&  S.xmin < 1000 && S.y2 > 0 && S.y1 < 600  )
			{
				S.draw( g, x ,  y , z) ;
			} else if( S.Type.equals("lend")  ||  S.Type.equals("bend")    ||  S.Type.equals("path")  ) {
				S.draw( g, x ,  y , z) ;
			}else{
				S.draw( g, x ,  y , z) ;
				}
			} // end for
		
		
		
		}
	
	public void zoom( Graphics g , int x , int y ){
		int MINZ = 1 , MAXZ = 50;
		if( Zoom >= MAXZ )
		{
			Zoom = MAXZ ;
		} else if( Zoom  < MINZ ) {
			Zoom  = MINZ ;
			}
		if( Zoom  == Head.font_h  )
		{
			return;
		}else{
			Head.font_h = Zoom ;
			Head.calculate( g , x , y, 0 );
			}
		}
		
		
	}

//  Export  Date: 05:51:26 PM - 02:Feb:2023...

