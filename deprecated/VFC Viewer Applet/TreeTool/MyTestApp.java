/** recipe log **/ // <--- part of framework...
  /**<TIME_STAMP> added Cross Hairs segments using: 'CrossHair.sg'  **/
  /** <TIME_STAMP>  added a creation call class Dialog1 using frame: 'Call Dialog.fm' and button event name Dialog **/
  /** <TIME_STAMP> LANGUAGE: Java ;   started Applet / Application  MyTestApp.class   using frame: 'Basic Applet.fm'   **/

/** Imports **/ // <--- part of framework...
  import java.awt.*;  
  import java.applet.*;  
  import java.net.*;  
  import java.awt.event.*;  
  import java.util.*; 
/*------------*/
public class MyTestApp  extends Applet { //   <----- start of class definition  has drawing vars for lines...
/** class data **/ // <--- used in framework...
  int HOMEX = 370;
  int HOMEY = 150;
  boolean last_was_branch  = false;
  TreeTool TT ; 
  FCnavigate  NavTool ; // Res.IDC_LIST1.addItem(" test " );...
  URL CodeBase;  
  int KeyHit = 'X'; // // // Resource Res;.........
  String KeyHit_Object; // // // Resource Res;.........
  int oldx = 0 , oldy = 0;
  int dx = 0 , dy = 0 ;
  int CrossSize =  200;  
  int CrossXPos = 200;
  int CrossYPos = 10;  
  /**@shapeType AggregationLink*/  
  private FCview lnkView;
  /** end variables **/  
  /** methods **/       // // // <--- used in framework.........
  Resource Res;
/** end variables **/ 
/** events **/ // <--- used in framework...
  Command searchCmd =new Command(Command.SEARCH ); 
  Command sortCmd =new Command(Command.SORT ); 

public void showStatement( int N ) {
  if( N >  lnkView.SO.size()  )
  { 
    N  =  lnkView.SO.size() ;
  }else{ 
    if( N < 1  )
    { 
      N  =  1 ;
    }else{ 
    } 
  } 
  StatementObject  S  = (StatementObject)  lnkView.SO.elementAt( N - 1 ) ;
  lnkView.currentObject = N  ;
  CrossXPos -= S.x1 - HOMEX  ; // oldx = 0;...
  CrossYPos -= S.y1 - HOMEY ; // oldy = 0;...
  oldx = CrossXPos ;
  oldy = CrossYPos ; 
  repaint();  
  } 
  public boolean keyDown( Event e , int key ) {
  KeyHit = key;
  int  DELTA=70;
  switch( key  ){
  case 1000 : // home...
    lnkView.Zoom=10; // lnkView.zoom( g , ZoomScale );...
    CrossXPos = HOMEX ; // oldx = 0;...
    CrossYPos = HOMEY ; // oldy = 0;...
    repaint();  
     //   repaint();  ...
  case 'Z' :
    case 1002 : // PageUP...
    lnkView.Zoom+=5; // lnkView.zoom( g , ZoomScale );...
    repaint();  
    break; 
  case 'z' :
    case 1003 : // PageDOWN...
    lnkView.Zoom-=5; // lnkView.zoom( g , ZoomScale );...
    repaint();  
    break; 
  case 1004: //  UP :...
    CrossYPos -=DELTA; // //  CrossYPos +=  dy;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  case 1005 : //  DOWN :...
    CrossYPos +=DELTA; // //  CrossYPos +=  dy;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  case 1006: //  LEFT :...
    CrossXPos -=DELTA ; // //  CrossXPos+= dx;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  case 1007: //  RIGHT :...
    CrossXPos +=DELTA ; // //  CrossXPos+= dx;////...
    repaint();  
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    break; 
  default: 
    repaint();  
    return false; 
  } 
return true;  }
public void init_TreeTool( Vector SO , String fname  ) 
  {  
  String  buff = new String("");
  String  path = new String("");
  String  sname = new String("");
  String  gifname= new String("set.gif");
  String  last_object = new String("generic.gif");
  int    Line = 1;
  int    Level = 0;
  StatementObject S;
  TT.addItem( fname + ";program.gif;..;code map", null, this, CodeBase , true ); // / //Root node//...
  for( int i =0 ; i< SO.size()  ; i++ ) {
    S  = (StatementObject)  SO.elementAt( i ) ;
    sname = "|"; // use the | to find begining of each code line...
    sname += (((S.Statement.replace( ';' , ':' )+"."+Line+"." ).replace('/', ' ')).replace('|','_')).replace('\\','_'); // the . (dot) is ued to find numbers ...
    Line++;
    try 
    {  
      if( S.Type.equals("input")  )
      { 
        gifname= "input.gif";
        if( true   ) // if( Level == 0  )...
        { 
          path = ""; // path = path + sname + "/";...
          TT.addItem( path + sname + ";module.gif; ."+ Line + ";"+ S.Comment.replace( ';' , ':' ) , null, this, CodeBase , false );
          path = sname + "/"; // path = path + sname + "/";...
          Level=1;
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
        }else{ 
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          path = path + sname + "/";
          Level++;
        } 
      } else if( S.Type.equals("loop")  ||  S.Type.equals("branch")    ||  S.Type.equals("path")  ) {
        gifname= S.Type + ".gif";
        if( S.Type.equals("path")  )
        { 
          if( ! last_object.equals("branch" ) )
          { 
            if( path.lastIndexOf('/') > -1  )
            { 
              path = path.substring( 0 , path.lastIndexOf('/') ) ; // buff = path.substring( 0 , path.lastIndexOf('/') ) ;...
            } 
          } 
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          path = path + sname + "/";
        }else{ 
          Level++;
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          path = path + sname + "/";
        } 
      } else  if(  S.Type.equals("lend")  ||  S.Type.equals("bend")    ) { // S.Type.equals("end")  || ...
         // /path += S.Statement.replace( ';' , ':' ) + "/";//...
        gifname= S.Type + ".gif";
        if( S.Type.equals("bend")  )
        { 
          if( path.lastIndexOf('/') > -1  )
          { 
            path = path.substring( 0 , path.lastIndexOf('/') ) ; // buff = path.substring( 0 , path.lastIndexOf('/') ) ;...
          } 
          if( path.lastIndexOf('/') > -1  )
          { 
            path = path.substring( 0 , path.lastIndexOf('/') ) ; // buff = path.substring( 0 , path.lastIndexOf('/') ) ;...
          } 
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          Level-=2;
        }else{ 
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          if( path.lastIndexOf('/') > -1  )
          { 
            path = path.substring( 0 , path.lastIndexOf('/') ) ; // buff = path.substring( 0 , path.lastIndexOf('/') ) ;...
          } 
          Level--;
        } 
        if( Level < 0 )
        { 
          Level = 0;
        } 
         // path = buff ;...
      }else{ 
        gifname= S.Type + ".gif";
        if(  S.Type.equals("end")   )
        { 
          TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          if(  Level == 0 )
          { 
            if( path.lastIndexOf('/') > -1  )
            { 
              // path = path.substring( 0 , path.lastIndexOf('/') ) ; // buff = path.substring( 0 , path.lastIndexOf('/') ) ;...
            } 
          } 
        }else{ 
          if( S.Type.equals("process" ) ||   S.Type.equals("set" ) ||  S.Type.equals("generic" )  )
          { 
            if( last_object.equals("process" ) ||  last_object.equals("set" ) ||  last_object.equals("generic" ) )
            { 
            }else{ 
              S  = (StatementObject)  SO.elementAt( i + 1 ) ;
              if(  S.Type.equals("process" ) ||   S.Type.equals("set" ) ||  S.Type.equals("generic" )  )
              { 
                gifname="multi.gif";
              } 
              TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
            } 
          }else{ 
            TT.addItem( path+sname+";"+gifname+";."+Line+";"+S.Comment, null, this, CodeBase , false );
          } 
        } 
      } 
    }catch( Exception e ){
      System.out.println("Exception at MyTestApp.ins (216) ! " +   e.getMessage() + "\n"  );
    } 
    last_object  =  S.Type ;
  } // end for 
  TT.LoadImages();
} 
public void _init_TreeTool() 
   //  // // setup base URL......
  {  
  URL m_baseURL = CodeBase;
  System.out.println("Hello world!");  
   //  // // setup base URL......
  TT.addItem( "FlowCode Module;program.gif;.500;code map", null, this, m_baseURL, true ); // / //Root node//...
  Item statusItem =new Item( "Loading items..");  
   // TT.m_control.m_folder.setTitle( m_folder.getTitle() );...
   // TT.m_control.m_folder.addElement( statusItem );...
   // TT.m_control.initalise( );...
   //  // // readin the item params item 1 to item N......
   //  addItem( param, null, this, m_baseURL, false ); ...
  TT.addItem( "Input1;module.gif; .2;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/Input1;input.gif;.2;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/Print( HELLO ).1;set.gif;.3;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/Print( HELLO ).2;set.gif;.4;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/End;end.gif;.5;Random Splines" , null, this, m_baseURL, false );
  
  TT.addItem( "Input2;module.gif;.6;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/Input1;input.gif;.7;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/loop;loop.gif;.8;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/Print( HELLO ).1;set.gif;.100;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch;branch.gif;.2;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch/path1;path.gif;.2;Random Splines" , null, this, m_baseURL, false );
      TT.addItem( "Input2/loop/branch/path1/Print( HELLO ).1;set.gif;.2;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch/path2;path.gif;.2;Random Splines" , null, this, m_baseURL, false );
      TT.addItem( "Input2/loop/branch/path2/Print( HELLO ).1;set.gif;.2;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch/bend;bend.gif;.2;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/process;set.gif;.2;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/loop/lend;lend.gif;.2;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/End;end.gif;.2;Random Splines" , null, this, m_baseURL, false );
  
  statusItem.setTitle( "Loading images..." );  
  TT.LoadImages();
} 
public void init(){  
  CodeBase = getCodeBase();  
  Res  = new Resource( this , CodeBase ); // Res  = null;...
  Res.IDC_LIST1.hide();
  Res.IDC_COMBO2.hide();
  TT = Res.IDC_USER1 ; //  TT = new TreeTool( this , 0 , 0 , 150 , 400  ) ;...
  try 
  {  
    String  vfcname =getParameter("flowfile"); 
    lnkView = new FCview( getDocumentBase() , vfcname  ); // // // Res  = new Resource( this , CodeBase );.........
    init_TreeTool( lnkView.SO ,  vfcname  ); 
    repaint();  
    NavTool = new FCnavigate( Res.IDC_LIST1 , getDocumentBase() , vfcname  ); // Res.IDC_LIST1.addItem(" test " );...
    Res.IDC_COMBO2.removeAll( );
    Res.IDC_COMBO2.addItem( "input" );
    Res.IDC_COMBO2.addItem( "event" );
    Res.IDC_COMBO2.addItem( "process" );
    Res.IDC_COMBO2.addItem( "set" );
    Res.IDC_COMBO2.addItem( "output" ); 
    Res.IDC_COMBO2.addItem( "loop" ); 
    Res.IDC_COMBO2.addItem( "lend" ); 
    Res.IDC_COMBO2.addItem( "branch" ); 
    Res.IDC_COMBO2.addItem( "bend" ); 
    Res.IDC_COMBO2.addItem( "path" ); 
    Res.IDC_COMBO2.addItem( "end" ); 
    Res.IDC_COMBO2.addItem( "generic" ); 
    Res.IDC_COMBO2.addItem( "all" ); 
  }catch( Exception e ){
    System.out.println("Exception at MyTestApp.ins (286) ! " +   e.getMessage() + "\n"  );
  } 
  lnkView.Zoom-=1 ; // lnkView.zoom( g , ZoomScale );...
  repaint(); // this repaint will recalc the vectors...
  lnkView.Zoom+=1 ; // lnkView.zoom( g , ZoomScale );...
  repaint(); // this repaint will recalc the vectors...
}  
public static void main(String args[])  {  
  MyTestApp  Obj = new MyTestApp(); //   create the object application ...
  //  load the resources ignoring MAIN // Obj.Res = new Resource( Obj , getCodeBase() );...
   // Obj.CodeBase = Obj.getCodeBase();...
  Obj.Res  = new Resource( ); // Obj.Res  = new Resource( Obj , Obj.getCodeBase()  );...
  Obj.Res.initMAINframe( Obj , "Main Window" ) ; // setup frame using MAIN controls...
  try 
  {  
    try 
    {  
      Obj.lnkView = new FCview( Obj.getCodeBase() , "test.ins" ); // // // Res  = new Resource( this , CodeBase );.........
    }catch( Exception e ){
      System.out.println("Exception 305! " +   e.getMessage() + "\n"  );
    } 
  }catch( Exception   e ){
    System.out.println("Hit e." );
  } 
}   
public void paint(Graphics g){  
   //   drawCrossHair( g, CrossXPos, CrossYPos, CrossSize );  ...
   //   g.drawString( "KeyHit Code =" + KeyHit , 250 , 20 );...
   //   g.drawString( "KeyHit Object =" + KeyHit_Object  , 250 , 40 );...
  lnkView.draw(g , CrossXPos + 20, CrossYPos + 20 , 25);
}  
boolean processButtons( Event e ){
  int  DZ = 4;
  if( "Tree View".equals(e.arg) )
  {  
  } else if( "Home".equals( e.arg ) ) {
    lnkView.Zoom=10; // lnkView.zoom( g , ZoomScale );...
    CrossXPos = HOMEX ; // oldx = 0;...
    CrossYPos = HOMEY ; // oldy = 0;...
    oldx = CrossXPos ; // oldx = 0;...
    oldy = CrossYPos ; // oldy = 0;...
    dx =   CrossXPos - oldx ; // dx =  0 ;...
    dy = CrossYPos - oldy;
    repaint();  
     //   repaint();  ...
  } else if( "Zoom In".equals( e.arg ) ) {
    if(  lnkView.Zoom > 5 )
    { 
      lnkView.Zoom+=DZ ; // lnkView.zoom( g , ZoomScale );...
    }else{ 
      lnkView.Zoom+=1 ; // lnkView.zoom( g , ZoomScale );...
    } 
    repaint(); // this repaint will recalc the vectors...
     // showStatement( lnkView.currentObject + 1 );...
  } else if( "Zoom Out".equals( e.arg ) ) {
    if(  lnkView.Zoom > 5 )
    { 
      lnkView.Zoom-=DZ; // lnkView.zoom( g , ZoomScale );...
    }else{ 
      lnkView.Zoom-=1 ; // lnkView.zoom( g , ZoomScale );...
    } 
    repaint(); // this repaint will recalc the vectors...
     // showStatement( lnkView.currentObject + 1 );...
  }else{ 
    switch (e.id){  
    case Event.MOUSE_DOWN:  
      if( e.x > 200  )
      { 
        CrossXPos = e.x; //   CrossXPos+= dx;...
        CrossYPos = e.y; //   CrossYPos +=  dy;...
        if(  oldx != CrossXPos || oldy != CrossYPos   )
        { 
          dx =   CrossXPos - oldx ; // dx =  0 ;...
          dy = CrossYPos - oldy;
          oldx = CrossXPos ; // oldx = 0;...
          oldy = CrossYPos ; // oldy = 0;...
        }else{ 
        } 
      }else{ 
      } 
    case Event.MOUSE_DRAG:  
      {
      if(   e.x > 200   )
      { 
        CrossXPos = e.x - dx; //   CrossXPos+= dx;...
        CrossYPos = e.y  - dy; //   CrossYPos +=  dy;...
        repaint();  
        oldx = CrossXPos ; // oldx = 0;...
        oldy = CrossYPos ; // oldy = 0;...
      }else{ 
      } 
      }
    default:   
      return false; 
    }  
  }  
return true ;}
public boolean handleEvent(Event e){  
  if( processButtons( e ) )
  {  
    return true;  
  }else{ 
    switch (e.id){  
    case TreeTool.TREETOOL_MOUSESELCTION :
       // System.out.println("handleEvent() ->TreeTool.TREETOOL_MOUSESELCTION  called ..."+ e.arg + "\n");...
      int LineToJumpTo =  NavTool.GoTo( e.arg.toString()  ); // returns non zero if we need to jump to a line .. -2 if the control reset the filtered type...
      if(  LineToJumpTo > 0  )
      { 
         // System.out.println(  "\t ---> Jump to line # "+ LineToJumpTo + " ... \n");...
        showStatement( LineToJumpTo-1 );
      } 
      repaint();  
      break; 
    case Event.ACTION_EVENT:  
      if(  "X".equals(e.arg) ||  "OK".equals(e.arg) || "CLOSE".equals(e.arg)  )
      {  
        System.exit(0);       // // //  THIS LOGIC CALL CAUSES A SECURITY EXCEPTION WHEN USED IN APPLET MODE.........
        return true;  
      }  
      break;  
    case Event.WINDOW_DESTROY:  
      System.exit(0);       // // //   THIS FAILS TO FIRE IN APPLICATION MODE.........
      return true;  
    default:   
    }  
  }  
return super.handleEvent( e ) ; } //   return false; }  ...
// action method
  Toolkit T;
  String  buff; 
public boolean action( Event e , Object what ) { // GUI widget actions ... drop list ... selection list...
  int LineToJumpTo =  NavTool.GoTo( e.arg.toString()  ); // returns non zero if we need to jump to a line .. -2 if the control reset the filtered type...
  if(  e.target instanceof Checkbox   ) //   if(  "true".equals(  what.toString()   ) ||  "false".equals( what.toString()  )  )...
  {  
     // System.out.println("Hello world!" + e.arg + "   " + e.target.toString()  );...
    if( (((Checkbox)(e.target)).getLabel()).equals("Comments")  )
    { 
      lnkView.DrawComments =  what.toString().equals( "true" );
      repaint();  
    } else if( (((Checkbox)(e.target)).getLabel()).equals("Show Object Browser") ) {
      System.out.println("Hello world!");  
      if(  ! ((Checkbox)(e.target)).getState()  )
      { 
        Res.IDC_LIST1.hide();
        Res.IDC_COMBO2.hide();
         // Res.IDC_USER1.hide();...
      }else{ 
        Res.IDC_LIST1.show();
        Res.IDC_COMBO2.show();
         // Res.IDC_USER1.show();...
      } 
    }else{ 
      return false;
    } 
  } else if( LineToJumpTo > 0 ) {
    showStatement( LineToJumpTo );
  }else{ 
    return false;
  }  
return true ;  } //   return false; }  ...
}       /** end of class definition **/ //  <------------ end of class definition...
// listener method
  class Command implements ActionListener {  
  static final int SEARCH =0;  
  static final int SORT =1;  
  int id;  
  
  
  public Command(int id ) 
    {  
    this.id =id;  
}  

public void actionPerformed(ActionEvent e)  
  {  
  switch(id) {  
  case SEARCH:  
    System.out.println("Searching...");  
    break;  
  case SORT:  
    System.out.println("Sorting....");  
    break;  
  }  
  }  
}   
//  FlowCode File: MyTestApp.ins...
//  Export  File: MyTestApp.java...
//  Export  Date: 10:12:52 PM - 07:Sep:1999...

