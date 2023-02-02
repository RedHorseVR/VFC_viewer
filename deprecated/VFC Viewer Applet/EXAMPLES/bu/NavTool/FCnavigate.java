/** Imports **/       // // // <--- part of framework.........
  import java.awt.*;  
  import java.applet.*;  
  import java.util.*;
  import java.io.*;
  import java.net.*; 
 //  AUTO ...
public class FCnavigate  {
  
  
    List L;
    boolean first_draw = true;
    private  Point p ; // ///...
    private String VFCfile;  
    private StatementObject SO1 , SO2 , SO3 , SO4 , SO5 , SO6 , SO7 ;
    private Vector SO;
    Stack iterativeS;
    Stack logicalS;
    URL DocBase = null;
    private String LastType = null ;
    public String KeyHit = "***" ;
    public int Zoom = 15;
    public boolean DrawComments = false;
    String type_filter  = "input";
  
  public int  GoTo( String line_code ) { //  returns the line we jumped to or -2  ... if not found if not found sets the filter ......
    int rtnVal = -2 ;
    int  ctok = line_code.lastIndexOf( ':') ;
    if(  ctok >= 0 )
    { 
      String  St =  line_code.substring( ctok + 1 ) ;
      rtnVal = (new Integer( St )).intValue() ;
    }else{
      type_filter  = line_code;
      L.clear();
      load( VFCfile );
    } 
return rtnVal ; } // return r; }...

String LoadLines ( InputStream  input_s , String type_filter ) throws IOException 
  {  
  int c;
  String  buff = new String("");
  String  Type = new String("");
  String  Statement;
  String  Comment;
  String  S;
  int line_number=0;
  while( ( c = input_s .read() ) != -1 ) { // GetLines( st ) ;...
    if(  c == '\n' ) 
    { 
      System.out.println( buff ); // System.out.println(st.sval );...
      if(  !buff.startsWith(";")   )
      { 
         // SO.addElement( track_stack_links(  new StatementObject( buff , SO ) )  ) ;...
        Statement = buff;
        Comment = " ... ";
        StringTokenizer st = new StringTokenizer( buff , "(<> \t" );
        try
        {  
          Type = st.nextToken( "( \t" );
          S  = st.nextToken( "" );
          Statement =  S.substring( S.indexOf("(")+1 , S.indexOf(");/")  ).trim() ; // Get the main statement...
          int  ctok = S.indexOf( ");/") ;
          if(  ctok > 0 )
          { 
            Comment = S.substring( ctok + 4 ).trim();
          } 
        }catch( NoSuchElementException e ){
          System.out.println("FCnavigate.java.ins(70)");
        } 
        line_number++;
        if( Statement.length() == 0  )
        { 
          Statement = "*empty*";
        }else{ 
        } 
        if( Type.trim().equals( type_filter) || type_filter.equals("all")  )
        { 
          L.addItem( Statement+":"+ line_number  );
        }else{ 
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
  
     //  Thread.currentThread().setPriority(Thread.MIN_PRIORITY); ...
    in =new URL( DocBase ,fname).openStream(); //   in =new URL(getDocumentBase(),fname).openStream();...
    try 
    {  
       // InputStream  in  = new InputStream("test.ins");...
       //  StreamTokenizer st =new StreamTokenizer(in);...
      LoadLines( in , type_filter  ) ; // while( in.read( buff , 0 , 64 ) != -1 ) {...
      in.close(); 
    }catch( Exception e ){
      System.out.println("Exception 106! " +   e.getMessage() + "\n"  );
      L.addItem("Exception 107! " +   e.getMessage() + "\n"  );
    } 
    }  
  catch(Exception e) {  
    System.out.println("Exception 111! " +   e.getMessage() + "\n"  );
    L.addItem("Exception 112! " +   e.getMessage() + "\n"  );
  }  
}  

FCnavigate( List list ,  URL docbase , String filename ) throws IOException {
  L = list;
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
//  Export  File: FCnavigate.java...
//  Export  Date: 10:06:01 PM - 26:Aug:1999...

