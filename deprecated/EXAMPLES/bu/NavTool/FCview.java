/** Imports **/       // // // <--- part of framework.........
  import java.awt.*;  
  import java.applet.*;  
  import java.util.*;
  import java.io.*;
  import java.net.*; 
 //  AUTO ...
public class FCnav Extends Tree {
  
  
    boolean first_draw = true;
    private  Point p ; // ///...
    private String VFCfile;  
    private StatementObject SO1 , SO2 , SO3 , SO4 , SO5 , SO6 , SO7 ;
    private Vector SO;
    Stack iterativeS;
    Stack logicalS;
    URL DocBase = null;
    private String LastType = null ;
    public int Zoom = 15;
    public boolean DrawComments = false;
  
  String GetLines ( InputStream  st ) throws IOException 
    {  
    int c;
    String  buff = new String("");
    while( ( c = st.read() ) != -1 ) { // GetLines( st ) ;...
      if(  c == '\n' ) 
      { 
        System.out.println( buff ); // System.out.println(st.sval );...
        if(  !buff.startsWith(";") &&  !buff.startsWith("\r")  )
        { 
          SO.addElement( track_stack_links(  new StatementObject( buff , SO ) )  ) ;
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
  
     //  Thread.currentThread().setPriority(Thread.MIN_PRIORITY); ...
    in =new URL( DocBase ,fname).openStream(); //   in =new URL(getDocumentBase(),fname).openStream();...
    try 
    {  
       // InputStream  in  = new InputStream("test.ins");...
       //  StreamTokenizer st =new StreamTokenizer(in);...
      GetLines( in ) ; // while( in.read( buff , 0 , 64 ) != -1 ) {...
    }catch( Exception e ){
      System.out.println("Exception 56! " +   e.getMessage() + "\n"  );
    } 
    }  
  catch(Exception e) {  
    System.out.println("Exception 60! " +   e.getMessage() + "\n"  );
  }  
  try {  
  
    if (in != null) 
    
      in.close(); 
     
    }  
  catch(Exception e) {  
  }  
  repaint();  
}  

FCview ( URL docbase , String filename ) throws IOException {
  DocBase = docbase ;
  InputStream is =null;  
  VFCfile = filename;
  logicalS = new Stack( ) ; //  Use to track logical nesting ...
  iterativeS = new Stack( ) ; //  Use to track logical nesting ...
  p = new Point( 10 , 10 ) ; // /.//...
  SO = new Vector( 200 );
   // SO.addElement( track_stack_links(  new StatementObject( "input( ! );/... commentary " ) )  ) ;...
  load( VFCfile ); // load( "test.ins" );...
   // SO.addElement( track_stack_links(  new StatementObject( "end( ! );/... commentary " ) )  ) ;...
   // int     i=10;...
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

public void draw( Graphics g, int x, int y, int z){ //   public void _draw( Graphics g, int x, int y, int z){...
   // FontMetrics FM = g.getFontMetrics();...
  StatementObject S ;
  if( first_draw  ) // Must cals on each draw for now until we develop a 2D trasform...
  { // Calc only on first run to avoid bad display...
    S  = (StatementObject)  SO.elementAt( 0 ) ;
    first_draw = false;
    S.calculate( g, 0, 0, z );
  } 
  zoom( g , x, y );
  for( int i =0 ; i< SO.size()  ; i++ ) {
    S  = (StatementObject)  SO.elementAt( i ) ;
    S.show_comments = DrawComments ;
    if( S.xmax > 0 &&  S.xmin < 1000 && S.y2 > 0 && S.y1 < 600  )
    { 
      S.draw( g, x ,  y , z) ; // p = S.draw( g, p.x , p.y  , z) ;...
    } else if( S.Type.equals("lend")  ||  S.Type.equals("bend")    ||  S.Type.equals("path")  ) {
      S.draw( g, x ,  y , z) ; // p = S.draw( g, p.x , p.y  , z) ;...
    }else{ 
      S.transform(  x ,  y , z) ; // p = S.draw( g, p.x , p.y  , z) ;...
    } 
  } // end for 
   // SO1.show_comments = true;...
}   

public void zoom( Graphics g , int x , int y ){ // zoom by points...
  StatementObject S ;
  if( Zoom > 30  )
  { 
    Zoom = 30;
  } else if( Zoom  < 1 ) {
    Zoom  = 1;
  } 
  g.drawString( "Zoom Font Points =" + Zoom , 100  , 20  ); //   g.drawString( "  end()" , x  , y  );...
  S  = (StatementObject)  SO.elementAt( 0 ) ;
  if( Zoom  == S.font_h  )
  { 
    if( S.F != null  )
    { 
      g.setFont( S.F  );
    } 
    return; 
  }else{ 
    S.font_h = Zoom ;
    S.calculate( g , x , y, 0 );
  } 
  }   
  

}  

//  FlowCode File: FCnavigate.java.ins...
//  Export  File: FCview.java...
//  Export  Date: 10:51:02 AM - 26:Aug:1999...

