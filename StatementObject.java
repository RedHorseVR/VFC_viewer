/** Imports **/
	import java.awt.*;
	
	import java.util.*;
	
public class StatementObject   {
		
		static Font F = null  ;
		Vector V;
		public String Statement;
		public String Comment;
		private Color CommentColor  ;
		private  int  w = 37 ;
		private  int  h = 15 ;
		private  int  Separation  ;
		private  int  margin = h/5 ;
		private Point P;
		
		private int x1bound = 0;
		private int x2bound = 0;
		int  xmin2=0;
		int  xmax2 =0;
		boolean first_draw = true;
		private  Vector LPoints;
		int  ymid1 = 0;
		int  ymid2 = 0 ;
		int  Xo = 0;
		int  Yo = 0 ;
		public String Type ;
		static    public int font_h = 10 ;
		public boolean show_comments =false;
		public  StatementObject Link;
		public  Vector  PLink;
				
			int  x1 ;
			int  x2;
		
			int  xmin ;
			int  xmax ;
		
			int  y1;
			int  y2 ;
		
			int  ymid ;
			int  xmid ;
			
	
	StatementObject(  String flowcode  , Vector Vec ){
		V = Vec ;
		P = new Point( 300 , 150 ) ;
		PLink  = new Vector() ;
		Type = "generic" ;
		Statement = flowcode ;
		Comment = " ... ";
		Link = null ;
		CommentColor  = new Color( 150, 200, 150  );
		StringTokenizer st = new StringTokenizer( flowcode , "(<> \t" );
		try
		{
			Type = st.nextToken( "( \t" );
			String  S  = st.nextToken( "" );
			Statement =  S.substring( S.indexOf("(")+1 , S.indexOf(");/")  ) ;
			int  ctok = S.indexOf( ");/") ;
			if(  ctok > 0 )
			{
				Comment = S.substring( ctok + 4 ).trim();
				}
		}catch( NoSuchElementException e ){
			}
		}
		
	
	public void calculate( Graphics g, int x, int y, int z){
		
		
		F = new Font(  "SansSerif",  Font.PLAIN , font_h ) ;
		g.setFont( F  );
		if( V != null )
		{
			StatementObject S = null ;
			Point xyp = new Point( x , y );
			Xo = x;
			Yo = y;
			for( int p = 0; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				xyp = S.calcobject( g, xyp.x, xyp.y, z) ;
				} // end for
			
			xyp.x = x ; xyp.y = y ;
			for( int p = 0; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				xyp = S.calcobject( g, xyp.x, xyp.y, z) ;
				} // end for
			
		}else{
			
			}
		}
		
	
	public void transform(  int x, int y, int z ){
		int dx = x-Xo;  int dy = y-Yo;   int dz;
		
		
		
		
		P.x += dx  ;   P.y += dy;
		x1bound += dx;
		x2bound += dx;
		xmin2 += dx;
		xmax2 +=dx;
		ymid1  += dy;
		ymid2 += dy;
		Xo  += dx;
		Yo  += dy;
				
			x1 += dx;
			x2+= dx;
		
			xmin+= dx;
			xmax+= dx;
		
			y1  += dy;
			y2  += dy;
		
			ymid  += dy;
			xmid += dx;
			
		if(  LPoints != null )
		{
			Point P;
			for( int i=0 ; i< LPoints.size() ; i++ ) {
				P = (Point) LPoints.elementAt( i );
				P.x += dx  ;   P.y += dy;
				} // end for
			
			}
		}
		
	
	private Point calcobject( Graphics g, int x, int y, int z){
		FontMetrics FM;
		if( F != null  )
		{
			g.setFont( F  );
			FM = g.getFontMetrics( F );
		}else{
			FM = g.getFontMetrics();
			}
		w = FM.stringWidth( Statement );
		h = font_h ;
		if( Type.equals( "input" ) || Type.equals( "event" )  )
		{
			h *=2;
		} else  {
			}
		Separation = h;
				
			margin = h/5 ;
			x = x - w/2 ;
		
			x1 = x-margin ;
			x2 = x + w+ margin;
		
			y1 = y - (h+margin) ;
			y2 = y+margin;
		
			ymid = y - h/2 ;
			xmid = x + w/2;
			
		if( Type.equals( "input" )  )
		{
			calc_input( g, x, y, z) ;
		} else if( Type.equals( "event" )  ) {
			calc_event( g, x, y , z) ;
		} else if( Type.equals( "set" )  ) {
			calc_set( g, x, y , z) ;
		} else if( Type.equals( "loop" )  ) {
			calc_loop( g, x, y , z) ;
		} else if( Type.equals( "branch" )  ) {
			calc_branch( g, x, y , z) ;
			
				} else if( Type.equals( "lend" )  ) {
			calc_lend( g, x, y , z) ;
		} else if( Type.equals( "end" )  ) {
			calc_end( g, x, y , z) ;
		} else if( Type.equals( "bend" )  ) {
			calc_bend( g, x, y , z) ;
		} else if( Type.equals( "path" )  ) {
			calc_path( g, x, y , z) ;
		}else{
			calc_set( g, x, y , z) ;
			}
		return P; }
	public Point draw( Graphics g, int x, int y, int z){
		transform( x   , y  , 0 ) ;
		if( F != null  )
		{
			g.setFont( F  );
			}
		if( Type.equals( "input" )  )
		{
			draw_input( g ) ;
		} else if( Type.equals( "event" )  ) {
			draw_event( g ) ;
		} else if( Type.equals( "set" )  ) {
			draw_set( g ) ;
		} else if( Type.equals( "loop" )  ) {
			draw_loop( g ) ;
		} else if( Type.equals( "branch" )  ) {
			draw_branch( g ) ;
		
			
				} else if( Type.equals( "lend" )  ) {
			draw_lend( g ) ;
		} else if( Type.equals( "end" )  ) {
			draw_end( g ) ;
		} else if( Type.equals( "bend" )  ) {
			draw_bend( g ) ;
		} else if( Type.equals( "path" )  ) {
			draw_path( g ) ;
		}else{
			draw_set( g ) ;
			}
		if( show_comments  )
		{
			Color startColor = g.getColor();
			g.setColor( CommentColor  );
			g.drawString( Comment, xmax + 2*margin  , y2  );
			g.setColor( startColor );
		}else{
			}
		return P; }
	
	private int GetPathXmax( StatementObject PS ) {
		Point P = new Point( 0 , 0 );
		int Xlimit = PS.xmin;
		if( V != null )
		{
			StatementObject S = null ;
			int depth = 0;
			int start_seg = V.lastIndexOf( PS );
			for( int p = start_seg ; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				if( S.Type.equals("branch") )
				{
					depth++;
				} else if( S.Type.equals("bend")  ) {
					depth--;
				} else if( S.Type.equals("path")  ) {
					if( depth == 0 && p> start_seg  )
					{
						return Xlimit ;
						}
				} else if( S.Type.equals("lend")  ) {
					if( Xlimit < S.x1bound   )
					{
						Xlimit = S.x1bound ;
						}
					}
				if( Xlimit < S.xmax  )
				{
					Xlimit = S.xmax;
				}else{
					}
				} // end for
			
		}else{
			
			}
		return Xlimit;  }
	private Point GetPathBounds( StatementObject PS ) {
		Point B = new Point( PS.xmin - h , PS.xmax + h );
		if( V != null )
		{
			StatementObject S = null ;
			int depth = 0;
			int start_seg = V.lastIndexOf( PS );
			for( int p = start_seg ; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				if( S.Type.equals("branch") )
				{
					depth++;
				} else if( S.Type.equals("bend")  ) {
					depth--;
				} else if( S.Type.equals("path")  ) {
					if( depth == 0 && p> start_seg  )
					{
						return B ;
						}
				} else if( S.Type.equals("lend")  ) {
					if( B.x < S.x1bound   )
					{
						B.x = S.x1bound   ;
						}
					if( B.y > S.x2bound   )
					{
						B.y = S.x2bound   ;
						}
					}
				if( B.x < S.xmax  )
				{
					B.x = S.xmax;
					}
				if( B.y > S.xmin )
				{
					B.y = S.xmin ;
					}
				} // end for
			
		}else{
			
			}
		return B ;  }
	
	private Point GetLoopBounds( StatementObject PS ) {
		int width = 0;
		Point B = new Point( PS.xmin - h , PS.xmax + h );
		if( V != null )
		{
			StatementObject S = null ;
			int depth = 0;
			int start_seg = V.lastIndexOf( PS );
			for( int p = start_seg ; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				if( S.Type.equals("loop") )
				{
					depth++;
				} else if( S.Type.equals("lend")  ) {
					depth--;
					if( depth == 0  )
					{
						return B ;
					}else{
						if( B.x < S.x1bound   )
						{
							B.x = S.x1bound   ;
							}
						if( B.y > S.x2bound   )
						{
							B.y = S.x2bound   ;
							}
						}
					}
				if( B.x < S.xmax  )
				{
					B.x = S.xmax;
					}
				if( B.y > S.xmin )
				{
					B.y = S.xmin ;
					}
				} // end for
			
		}else{
			
			}
		return B ; }
	
	private int GetLoopWidth( StatementObject PS ) {
		int width = 0;
		int  Xmin = PS.x1;
		int  Xmax = PS.x2;
		width = PS.w  + 2*h ;
		if( V != null )
		{
			StatementObject S = null ;
			int depth = 0;
			int start_seg = V.lastIndexOf( PS );
			for( int p = start_seg ; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				if( S.Type.equals("loop") )
				{
					depth++;
				} else if( S.Type.equals("lend")  ) {
					depth--;
					if( depth == 0  )
					{
						return width;
						}
					}
				if( Xmax < S.xmax  )
				{
					Xmax = S.xmax;
					}
				if( Xmin > S.xmin )
				{
					Xmin = S.xmin ;
					}
				if( width < S.w   )
				{
					width = S.w  ;
					}
				width = Xmax - Xmin;
				} // end for
			
		}else{
			
			}
		return width ; }
	
	private int GetPathWidth( StatementObject PS ) {
		int width = PS.w + 4*h ;
		if( V != null )
		{
			StatementObject S = null ;
			int depth = 0;
			int start_seg = V.lastIndexOf( PS );
			for( int p = start_seg ; p < V.size(); p++ ) {
				S = (StatementObject)V.elementAt( p );
				if( S.Type.equals("branch") )
				{
					depth++;
				} else if( S.Type.equals("bend")  ) {
					if( depth == 0 && p> start_seg  )
					{
						return width;
						}
					depth--;
				} else if( S.Type.equals("path")  ) {
					if( depth == 0 && p> start_seg  )
					{
						return width;
						}
				} else if( S.Type.equals("lend")  ) {
					if( width < S.x1bound - S.x2bound  )
					{
						width = S.x1bound - S.x2bound ;
						}
					}
				if( width < S.w  )
				{
					width = S.w ;
					}
				
				} // end for
			
		}else{
			
			}
		return width ; }
	
	private void calc_event( Graphics g, int x, int y, int z){
		
				
			y1 = y  ;
			y2 = y+margin + (h+margin);
		
			ymid = y2 - h/2 - margin ;
			
		int  texth = 4 ;
		int  p = h/2 ;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		xmin2 = xmin + p/2 ;
		xmax2 = xmax - p/2;
		
		P.x = xmid;
		P.y = y2 + Separation ;
		}
	private void draw_event( Graphics g ){
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x1, y1 , xmin , ymid  );
		g.drawLine( x1, y2 , xmin , ymid  );
		g.drawLine( x2 , y1 , xmax , ymid  );
		g.drawLine( x2 , y2 , xmax , ymid  );
		
		
		
		
		
		g.drawString( Statement , x1+margin  , y1 + h - margin );
		g.drawLine( xmid , y2 , xmid , P.y - h/2  -  margin/2  );
		
		g.drawLine( xmid - h/4 ,  P.y - h*3/4  -  margin/2 , xmid , P.y - h/2  -  margin/2  );
		g.drawLine( xmid ,  P.y - h/2  -  margin/2 , xmid + h/4, P.y - h*3/4  -  margin/2  );
		}
	
	private void calc_input( Graphics g, int x, int y, int z){
		
				
			y1 = y  ;
			y2 = y+margin + (h+margin);
		
			ymid = y2 - h/2 - margin ;
			
		int  texth = 4 ;
		int  p = h/2 ;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		xmin2 = xmin + p/2 ;
		xmax2 = xmax - p/2;
		
		P.x = xmid;
		P.y = y2 + Separation ;
		}
	private void draw_input( Graphics g ){
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x1, y1 , xmin , ymid  );
		g.drawLine( x1, y2 , xmin , ymid  );
		g.drawLine( x2 , y1 , xmax , ymid  );
		g.drawLine( x2 , y2 , xmax , ymid  );
		g.drawLine( x1, y1 , xmin2 , ymid  );
		g.drawLine( x1, y2 , xmin2 , ymid  );
		g.drawLine( x2 , y1 , xmax2 , ymid  );
		g.drawLine( x2 , y2 , xmax2 , ymid  );
		
		g.drawString( Statement , x1+margin  , y1 + h - margin );
		g.drawLine( xmid , y2 , xmid , P.y - h/2  -  margin/2  );
		
		g.drawLine( xmid - h/4 ,  P.y - h*3/4  -  margin/2 , xmid , P.y - h/2  -  margin/2  );
		g.drawLine( xmid ,  P.y - h/2  -  margin/2 , xmid + h/4, P.y - h*3/4  -  margin/2  );
		}
	
	private void calc_loop( Graphics g, int x, int y, int z){
		int  p = h*2;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		
		int  Bottom_y = y2 + Separation/2 ;
		P.y = Bottom_y + Separation + margin;
		P.x = xmid;
		}
	private void draw_loop( Graphics g ){
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x1, y1 , xmin , ymid  );
		g.drawLine( x1, y2 , xmin , ymid  );
		g.drawLine( x2 , y1 , x2 , y2  );
		
		g.drawString( Statement , x1+margin  , y2-margin  );
		g.drawLine( xmid , y2 , xmid , P.y  - h - margin );
		}
	
	private void calc_branch( Graphics g, int x, int y, int z){
		int  p = h*2;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		int  Bottom_y = y2 + Separation/2 ;
		P.y = Bottom_y + Separation + margin;
		P.x = xmin;
		
		}
		
	private void draw_branch( Graphics g ){
		g.drawLine( xmax , y2 , xmin , y2 );
		g.drawLine( xmin , y2 , x1 , y1  );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x2, y1 , xmax , y2  );
		
		g.drawString( Statement , x1 + margin  , y2-margin  );
		}
		
	
	private void calc_path( Graphics g, int x, int y, int z){
		int  p = h;
		xmin = x1 - p ;
		xmax = x2 + p ;
		if( Link != null && Link.Link != null  )
		{
			if(  Link.PLink != null )
			{
				if(  Link.PLink.lastIndexOf( this ) != 0 )
				{
					try
					{
						StatementObject S0  = (StatementObject)  Link.PLink.elementAt( 0 ) ;
						StatementObject S1  = (StatementObject)  Link.PLink.elementAt(  Link.PLink.lastIndexOf( this ) -1 ) ;
						y1 = S0.y1;
						y2 = S0.y2;
						Point B = GetPathBounds(  this )  ;
						int W  = GetPathWidth(  this )  ;
						xmid =   GetPathXmax( S1 ) +  W ;
						
						if( xmid < Link.xmax  )
						{
							xmid = Link.xmax ;
							}
						x1 = xmid - w/2 - margin ;
						x2 = xmid + w/2 + margin;
						
						
					}catch( EmptyStackException  e ){
						}
					int dx = 2*(Link.xmid - xmid);
					if( dx > 0  )
					{
						dx = 0;
						xmid+=dx ;
						x1 +=dx ;
						x2 +=dx;
						}
				}else{
					StatementObject S1  = (StatementObject)  Link.PLink.elementAt( 0 ) ;
					y1 = S1.y1;
					y2 = S1.y2;
					xmid =   Link.xmin - w/2;
					x1 = xmid - margin - w/2;
					x2 = xmid + w/2 + margin;
					}
			}else{
				}
			xmin = x1 - p ;
			xmax = x2 + p ;
			
		}else{
			}
		P.y = y2 + 2*Separation + 2* margin;
		P.x = xmid;
		}
	private void draw_path( Graphics g ){
		int  p = h;
		if( Link != null  )
		{
			g.drawLine( Link.xmax , Link.y2 , xmid , Link.y2  );
			g.drawLine( xmid , Link.y2 , xmid , y1  );
			
		}else{
			}
		g.drawLine( xmin , y1 , x2 , y1 );
		g.drawLine( x2, y1 , xmax , y2  );
		g.drawLine( xmax, y2 , x1 , y2  );
		g.drawLine( x1 , y2 , xmin , y1 );
		
		g.drawString( Statement , x1+margin  , y1+margin+ h  );
		int     py = y2 + Separation + margin ;
		g.drawLine( P.x  , y2 , P.x  ,  py  );
		
		g.drawLine( P.x - h/4 ,  py - h*3/4 , P.x , py );
		g.drawLine( P.x ,  py , P.x + h/4,  py - h*3/4 );
		
		}
	
	private void calc_bend( Graphics g, int x, int y, int z){
		Vector LinkPoints = new Vector(20);
		LPoints = LinkPoints ;
		if( Link != null  )
		{
			if( Link.PLink != null  )
			{
				if( V != null  )
				{
					StatementObject S = null ;
					StatementObject End ;
					int num_paths = Link.PLink.size();
					int Xlimit = 0;
					for( int i = 0; i < num_paths ; i++ ) {
						StatementObject Start = (StatementObject)Link.PLink.elementAt( i );
						if( i < num_paths - 1  )
						{
							End = (StatementObject)Link.PLink.elementAt( i + 1 );
						}else{
							End = this;
							}
						int start_seg = V.lastIndexOf( Start );
						int end_seg = V.lastIndexOf( End );
						for( int p = start_seg ; p < end_seg ; p++ ) {
							S = (StatementObject)V.elementAt( p );
							if( y1 < S.y2  )
							{
								y1 = S.y2 + Separation  ;
								y2 = y1 + h + 2*margin ;
							}else{
								}
							if( Xlimit < S.xmax  )
							{
								Xlimit = S.xmax;
							}else{
								}
							} // end for
						
						if( S != null  )
						{
							Point P = new Point();
							P.x = S.xmid;
							P.y = S.y2 + Separation/2 ;
							LinkPoints.addElement( P );
							}
						} // end for
					
				}else{
					}
			}else{
				}
			x = Link.xmid - w/2 ;
			x1 = Link.xmid - margin - w/2 ;
			x2 = Link.xmid  + w/2+ margin;
			xmid = Link.xmid ;
			
			Link.Link = this.Link;
		}else{
			}
		int  p = h*2;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		P.y = y2 + Separation*2 + margin;
		P.x = xmid;
		}
	private void draw_bend( Graphics g ){
		g.drawLine( xmax , y1 , xmin , y1 );
		g.drawLine( xmin , y1 , x1 , y2  );
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x2, y2 , xmax , y1  );
		if( Link != null )
		{
			g.drawLine( Link.xmin, y1 , xmid , y1 );
			if( Link.PLink != null  )
			{
				StatementObject S  = (StatementObject)  Link.PLink.elementAt( Link.PLink.size() - 1 ) ;
				g.drawLine( xmax , y1 , S.xmid , y1  );
				S  = (StatementObject)  Link.PLink.elementAt( 0 ) ;
				g.drawLine( xmin , y1 , S.xmid , y1  );
				
			}else{
				}
			}
		if(  LPoints != null )
		{
			Point P;
			for( int i=0 ; i< LPoints.size() ; i++ ) {
				P = (Point) LPoints.elementAt( i );
				g.drawLine( P.x , y1 , P.x , P.y );
				} // end for
			
			}
		
		g.drawString( Statement , x1+ margin  , y2 - margin  );
		g.drawLine( P.x  , y2 , P.x  , P.y - h - margin);
		}
	
	private void calc_lend( Graphics g, int x, int y, int z){
		int  p = h*2;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		if( Link != null  )
		{
			int lw = GetLoopWidth( Link );
			Point B  = GetLoopBounds( Link );
			x1bound = B.x +  h ;
			x2bound = B.y - h ;
		}else{
			}
		int  Bottom_y = y2 + Separation/2 ;
		
		P.y = Bottom_y + Separation + margin;
		P.x = xmid;
		}
	private void draw_lend( Graphics g ){
		int  p = h*2;
		xmin = x1 - p*2 ;
		xmax = x2 + p*2 ;
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x2 , y1 , xmax , ymid  );
		g.drawLine( x2, y2 , xmax , ymid  );
		g.drawLine( x1 , y1 , x1 , y2  );
		
		g.drawString( Statement , x1+margin  , y2-margin  );
		if( Link != null  )
		{
			g.drawLine( xmax , ymid , x1bound  , ymid );
			g.drawLine( x1bound  , ymid , x1bound  , Link.ymid );
			g.drawLine( x1bound  , Link.ymid , Link.x2, Link.ymid );
			g.drawLine( x1 , ymid , x2bound  , ymid );
			g.drawLine( x2bound  , ymid , x2bound  , Link.ymid );
			g.drawLine( x2bound  , Link.ymid , Link.xmin , Link.ymid );
		}else{
			}
		
		g.drawLine( xmid , y2 , xmid , P.y - h - margin );
		}
	
	private void calc_set( Graphics g, int x, int y, int z){
		int  p = h/2;
		xmin = x1 ;
		xmax = x2 ;
		P.y = y2 + Separation/2+ Separation + margin;
		P.x = xmid;
		P.y = y2 + Separation/2+ Separation + margin;
		P.x = xmid;
		}
		
	private void draw_set( Graphics g ){
		g.drawLine( xmid , y2 , xmid , y2 + Separation/2);
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x1, y1 , x1, y2  );
		g.drawLine( x2 , y1 , x2 , y2  );
		
		g.drawString( Statement , x1+ margin  , y2-2*margin  );
		g.drawLine( xmid , y2 , xmid , y2 + Separation/2 );
		}
		
	
	private void calc_end( Graphics g, int x, int y, int z ){
		int  p = h/3;
		xmin = x1 - p;
		xmax = x2 + p;
		ymid1 = y1 + h/3 ;
		ymid2 = y2 - h/3 ;
		int  Bottom_y = y2 + Separation/2 ;
		P.y = Bottom_y + Separation*3;
		P.x = xmid;
		
		}
		
	private void draw_end( Graphics g  ){
		
		g.drawLine( x1 , y2 , x2 , y2 );
		g.drawLine( x1 , y1 , x2 , y1 );
		g.drawLine( x1, y1 , xmin , ymid1  );
		g.drawLine( xmax  ,  ymid1, xmax , ymid2  );
		g.drawLine( x1, y2 , xmin, ymid2  );
		g.drawLine( x2, y1 , xmax , ymid1  );
		g.drawLine( xmin  ,  ymid1, xmin , ymid2  );
		g.drawLine( x2, y2 , xmax, ymid2  );
		
		g.drawString( Statement , x1+margin  ,  y2-2*margin );
		}
		
		
	}

//  Export  Date: 04:07:46 PM - 31:Jan:2023...

