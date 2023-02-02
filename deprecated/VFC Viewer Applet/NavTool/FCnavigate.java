/** Imports **/       // // // <--- part of framework.........
  import java.awt.*;  
  import java.awt.List;
  import java.applet.*;  
  import java.util.*;
  import java.io.*;
  import java.net.*; 
 //  AUTO ...
public class FCnavigate  {
  
  
    List L = null;
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
  
  public int  GoTo( String line_code ) { //  returns the line to jump to or -2  ... ...
    int rtnVal = -2 ;
    int  ctok = line_code.lastIndexOf( '.') ;
    if(  ctok >= 0 )
    { 
      String  St =  line_code.substring( ctok + 1 ) ;
      try 
      {  
        rtnVal = (new Integer( St )).intValue() ;
      }catch(  Exception e ){ 
      } 
    }else{ // reload the filtered list...
       // /* ...
      if( !line_code.equals("true") && !line_code.equals("false")  )
      { 
        type_filter  = line_code;
        L.clear();
        load( VFCfile );
      } 
       // */ ...
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
       // System.out.println( buff );...
      if(  !buff.startsWith(";")  && buff.length() > 5   )
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
          System.out.println("FCnavigate.java.ins(80)");
        } 
        line_number++;
        if( Statement.length() == 0  )
        { 
          Statement = "<empty>";
        }else{ 
        } 
        if( Type.trim().equals( type_filter) || type_filter.equals("all")  )
        { 
          L.addItem( Statement+"."+ line_number  );
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
      System.out.println("Exception 116! " +   e.getMessage() + "\n"  );
      L.addItem("Exception 117! " +   e.getMessage() + "\n"  );
    } 
    }  
  catch(Exception e) {  
    System.out.println("Exception 121! " +   e.getMessage() + "\n"  );
    L.addItem("Exception 122! " +   e.getMessage() + "\n"  );
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
  

}  

//  FlowCode File: FCnavigate.java.ins...
//  Export  File: FCnavigate.java...
//  Export  Date: 02:55:29 PM - 03:Sep:1999...

