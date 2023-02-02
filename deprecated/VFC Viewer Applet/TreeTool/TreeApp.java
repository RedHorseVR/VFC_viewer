/** Imports **/       // // // <--- part of framework.........
  import java.awt.*;  
  import java.applet.*;  
  import java.net.*;  
  /*------------*/  
 //  AUTO ...
public class TreeApp extends Applet implements Runnable {  
  TreeTool TT = null ; 
  private Thread loadThread =null;  
  private String m_rootTitle ="Root Directory";  
  private boolean m_expanded =false;  
  private URL m_baseURL = null; 
  private TreeControl m_control;  
   //  // // Parameter names.  To change a name of a parameter, you need only make......
   //  // // a single change.  Simply modify the value of the parameter string below.......
   //  // //--------------------------------------------------------------------------......
public TreeApp()  
  {  
}  
public String getAppletInfo()  
  {  //  <--- WARNING: BROKEN LINE ...
  return "Name: TreeApp\r\n" + "Author: Conrad Dare-Edwards\r\n" + "Re-engineerd using VFC\r\n" + "WWW:http://www.mcsoftware.com.au\r\n" + "E-Mail: cdare-edwards@mcsoftware.com.au";
}  
public void init() {  
  
  TreeTool TT = new TreeTool( this ) ; 
  m_control = TT.m_control; 
  init_TreeTool(); 
  loadThread =new Thread(this);  
  loadThread.start();  
} 
public void run() {
} 
public void init_TreeTool() 
   //  // // setup base URL......
  {  
  System.out.println("Hello world!");  
   //  // // setup base URL......
  TT.addItem( "FlowCode Module;program.gif;;code map", null, this, m_baseURL, true ); // / //Root node//...
  Item statusItem =new Item( "Loading items..");  
   //  m_control.m_folder.setTitle( m_folder.getTitle() ); ...
  m_control.m_folder.addElement( statusItem );  
  m_control.initalise( );  
   //  // // readin the item params item 1 to item N......
   //  addItem( param, null, this, m_baseURL, false ); ...
  TT.addItem( "Input1;module.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/Input1;input.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/Print( HELLO ):2;set.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input1/End;end.gif;;Random Splines" , null, this, m_baseURL, false );
  
  TT.addItem( "Input2;module.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/Input1;input.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/loop;loop.gif;;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch;branch.gif;;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch/path1;path.gif;;Random Splines" , null, this, m_baseURL, false );
      TT.addItem( "Input2/loop/branch/path1/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch/path2;path.gif;;Random Splines" , null, this, m_baseURL, false );
      TT.addItem( "Input2/loop/branch/path2/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/branch/bend;bend.gif;;Random Splines" , null, this, m_baseURL, false );
    TT.addItem( "Input2/loop/process;set.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/loop/lend;lend.gif;;Random Splines" , null, this, m_baseURL, false );
  TT.addItem( "Input2/End;end.gif;;Random Splines" , null, this, m_baseURL, false );
  
  statusItem.setTitle( "Loading images..." );  
  TT.LoadImages();
} 
} 
//  FlowCode File: TreeApp.java.ins...
//  Export  File: TreeApp.java...
//  Export  Date: 12:59:34 AM - 30:Aug:1999...

