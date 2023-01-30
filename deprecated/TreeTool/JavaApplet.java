// HYPER LINK TO HTML_JavaApplet ---> // FILE: HTML_JavaApplet.ins...
/** Imports **/ // <--- part of framework...
  import java.awt.*;  
  import java.applet.*;  
  import java.net.*;  
  import java.net.URL;  
  import java.net.MalformedURLException;  
  import java.util.StringTokenizer;  
  import java.util.Hashtable;  
  import java.util.Enumeration;  
  import TreeApp;  
   // import Resource;...
/*------------*/
public class JavaApplet  extends Applet { //   <----- start of class definition  has drawing vars for lines...
/** class data **/ // <--- used in framework...
  URL CodeBase;
   // Resource Res;...
/** end variables **/ 
/** methods **/ // <--- used in framework...

public void init(){  
  CodeBase = getCodeBase();
   // Res  = new Resource( this , CodeBase );...
  TreeControl  m_control =new TreeControl(); 
  add(m_control ); //  new TreeApp(CodeBase) ;...
  m_control.initalise( );  
  m_control.setBackground( Color.black ); 
  LoadTree();
}   
public static void main(String args[]){  
  JavaApplet  Obj = new JavaApplet(); //   create the object application ...
  //setup frame using MAIN controls // Obj.Res.initMAINframe( Obj , "Main Window" ) ;...
}   
public void paint(Graphics g){  
  g.drawString( "Hello new world ..." , 20 , 20 );
}   
public boolean handleEvent(Event e){  
  switch (e.id){  
  case Event.ACTION_EVENT:
    if( "OK".equals(e.arg) || "CLOSE".equals(e.arg)  )
    {  
      System.exit(0); //  THIS LOGIC CALL CAUSES A SECURITY EXCEPTION WHEN USED IN APPLET MODE...
      return true; 
    }  
    break; 
  case Event.WINDOW_DESTROY:
    System.exit(0); //   THIS FAILS TO FIRE IN APPLICATION MODE...
    return true;
    
  }   
return false; }
// ************************ TREE WORK
  private String m_rootTitle ="Root Directory";  
  private boolean m_expanded =false;  
  private String m_baseURLString;  
  private URL m_baseURL = null; 
  private TreeControl m_control;  
  FolderItem m_folder;  
  private Hashtable m_imageList;  
  private Thread loadThread =null;  
   //  // // Parameter names.  To change a name of a parameter, you need only make......
   //  // // a single change.  Simply modify the value of the parameter string below.......
   //  // //--------------------------------------------------------------------------......
  private final String PARAM_rootTitle ="rootTitle";  
  private final String PARAM_expanded ="expanded";  
  private final String PARAM_baseURL ="baseURL";  
  private final String PARAM_bgImage ="bgImage";  
  private final String PARAM_bgColor ="bgColor";  
  private final String PARAM_fgColor ="fgColor";  
  private final String PARAM_bgHighlight ="bgHighlight";  
  private final String PARAM_textColor ="textColor";  
  private final String PARAM_textHighlight ="textHighlight";  
  private final String PARAM_font ="font";  
  private final String PARAM_offset ="offset";  
  private final String PARAM_dotted ="dotted";  
  private final String PARAM_showURL ="showURL";  
public void LoadTree() 
   //  // // setup base URL......
  {  
  System.out.println("Hello world!");  
  String param =getParameter("baseURL");  
   //  // // setup base URL......
  if(   m_baseURL == null  )
  { 
    try{  
    
       //  // /auto path//......
      if( param != null )  
      
         //  // /auto path//......
        {  
        m_baseURL =new URL( param );  
         //  // // default base url......
        }  
      else  
        m_baseURL =getDocumentBase();  
       
      }  
    catch( MalformedURLException e ) {  
      m_baseURL =getDocumentBase();  
       //  // // set root item title......
    }  
  }else{ 
  } 
   //  param =getParameter("rootTitle"); ...
  addItem( "FlowCode Module;program.gif;;code map", null, this, m_baseURL, true ); //  //Root node...
  Item statusItem =new Item( "Loading items..");  
  m_control.m_folder.setTitle( m_folder.getTitle() );  
  m_control.m_folder.addElement( statusItem );  
  m_control.initalise( );  
   //  // // readin the item params item 1 to item N......
   //  addItem( param, null, this, m_baseURL, false ); ...
  addItem( "Input1;module.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input1/Input1;input.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input1/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input1/Print( HELLO ):2;set.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input1/End;end.gif;;Random Splines" , null, this, m_baseURL, false );
  
  addItem( "Input2;module.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input2/Input1;input.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input2/loop;loop.gif;;Random Splines" , null, this, m_baseURL, false );
    addItem( "Input2/loop/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
    addItem( "Input2/loop/branch;branch.gif;;Random Splines" , null, this, m_baseURL, false );
    addItem( "Input2/loop/branch/path1;path.gif;;Random Splines" , null, this, m_baseURL, false );
      addItem( "Input2/loop/branch/path1/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
    addItem( "Input2/loop/branch/path2;path.gif;;Random Splines" , null, this, m_baseURL, false );
      addItem( "Input2/loop/branch/path2/Print( HELLO ):1;set.gif;;Random Splines" , null, this, m_baseURL, false );
    addItem( "Input2/loop/branch/bend;bend.gif;;Random Splines" , null, this, m_baseURL, false );
    addItem( "Input2/loop/process;set.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input2/loop/lend;lend.gif;;Random Splines" , null, this, m_baseURL, false );
  addItem( "Input2/End;end.gif;;Random Splines" , null, this, m_baseURL, false );
  
  statusItem.setTitle( "Loading images..." );  
  LoadImages();
} 
 //  // // add an item to the control on the fly......
public void add( String string )  
  {  
  FolderItem old_folder =m_folder;  
  m_folder =m_control.getFolder();  
  addItem( string, null, this, m_baseURL, false );  
   //  // // load images......
  MediaTracker tracker =new MediaTracker( this );  
   //  // // pull all the images our of the hashtable......
   //  // // and add them to the tracker......
  Enumeration en =m_imageList.elements();  
  while( en.hasMoreElements() ) {  
    tracker.addImage( (Image)en.nextElement(), 0 );  
  }  
   //  // // load all the images......
  try {  
  
    tracker.waitForAll();  
    }  
  catch( InterruptedException e ) {  
     //  // // do nothing as compontents will handle image problems......
  }  
  m_control.setFolder( m_folder );  
  m_folder =old_folder;  
}  
void LoadImages() {
  m_control.initalise( );  
   //  // // load images......
  MediaTracker tracker =new MediaTracker( this );  
   //  // // pull all the images our of the hashtable......
   //  // // and add them to the tracker......
  Enumeration en =m_imageList.elements();  
  while( en.hasMoreElements() ) {  
    tracker.addImage( (Image)en.nextElement(), 0 );  
  }  
   //  // // load all the images......
  try {  
  
    tracker.waitForAll();  
    }  
  catch( InterruptedException e ) {  
     //  // // do nothing as compontents will handle image problems......
  }  
  m_folder =m_control.setFolder( m_folder );  
}  
public void _run() 
   //  // // setup base URL......
  {  
  String param =getParameter("baseURL");  
   //  // // setup base URL......
  try{  
  
     //  // /auto path//......
    if( param != null )  
    
       //  // /auto path//......
      {  
      m_baseURL =new URL( param );  
       //  // // default base url......
      }  
    else  
      m_baseURL =getDocumentBase();  
     
    }  
  catch( MalformedURLException e ) {  
    m_baseURL =getDocumentBase();  
     //  // // set root item title......
  }  
  param =getParameter("rootTitle");  
  if (param != null)  
  
     //  // /auto path//......
    addItem( param, null, this, m_baseURL, true );  
   
  Item statusItem =new Item( "Loading items..");  
  m_control.m_folder.setTitle( m_folder.getTitle() );  
  m_control.m_folder.addElement( statusItem );  
  m_control.initalise( );  
   //  // // readin the item params item 1 to item N......
  int x =1;  
  while(( param = getParameter( "item" + x++)) != null ) {  
    if( param.startsWith("**") )  
    
      continue;  
       //  // /auto path//......
     
    addItem( param, null, this, m_baseURL, false );  
  }  
  statusItem.setTitle( "Loading images..." );  
  m_control.initalise( );  
   //  // // load images......
  MediaTracker tracker =new MediaTracker( this );  
   //  // // pull all the images our of the hashtable......
   //  // // and add them to the tracker......
  Enumeration en =m_imageList.elements();  
  while( en.hasMoreElements() ) {  
    tracker.addImage( (Image)en.nextElement(), 0 );  
  }  
   //  // // load all the images......
  try {  
  
    tracker.waitForAll();  
    }  
  catch( InterruptedException e ) {  
     //  // // do nothing as compontents will handle image problems......
  }  
  m_folder =m_control.setFolder( m_folder );  
}  
 //  // // test if image is already to be loaded......
 //  // // or add to load list......
private final Image addImage( String imageURL, Applet applet )  
   //  // // dont bother with 0 length strings......
  {  
  if( imageURL.length() == 0 )  
  
    return null;  
     
     //  // // check if we already have this loaded......
   
  if( m_imageList.containsKey( imageURL ) )  
  
     //  // /auto path//......
    {  
    return (Image) m_imageList.get( imageURL );  
     
    }  
  else  
     //  // // create image and add it to the list......
    {  
    try {  
    
      URL link =new URL( applet.getCodeBase(), imageURL );  
       //  // //Image image = applet.getImage( applet.getDocumentBase(), imageURL );......
      Image image =applet.getImage( link );  
      m_imageList.put( imageURL, image );  
      return image;  
       
      }  
    catch( MalformedURLException e ) {  
       //  // // do nothting......
    }  
     
   
} return null;  
}  
 //  // // parse image arguments and add images to the item......
private final Item addImage( Item item, String imageArgs, Applet applet )  
  {  
  if( imageArgs.length() == 0 )  
  
    return null;  
     
   
  StringTokenizer stoke =new StringTokenizer(imageArgs, ",", false );  
  if( stoke.hasMoreElements() )  
  
     //  // /auto path//......
    {  
    item.m_icon =( addImage( stoke.nextToken(), applet ) );  
     //  // // add second image if its a folder......
    if( stoke.hasMoreElements() )  
    
       //  // /auto path//......
      {  
      item.m_highlightIcon =( addImage( stoke.nextToken(), applet ) );  
     
   
} } return item;  
}  
private final String addItem( String itemTitle, String none, Applet applet, URL baseURL, boolean rootItem )  
  {  
  Item item=null;  
   //  // // first item == item text and depth......
  if( rootItem )  
  
     //  // // if root directory item......
     //  // /auto path//......
    {  
    item =m_folder;  
     //  // // item equals root......
    item.setTitle( getArgument(itemTitle,0) );  
    }  
  else  
     //  // // normal item......
    {  
    if(( item = addItemString( getArgument(itemTitle,0) )) == null )  
    
       //  // /auto path//......
      return null;  
       
     
   
   //  // // second item == image......
  } addImage( item, getArgument(itemTitle,1) , applet );  
  ItemAction action =new ItemAction( this );  
  item.setAction( action );  
   //  // // forth argument is the status string......
  action.setStatus( getArgument(itemTitle,3) );  
   //  // // third item == link url......
  String linkArgs =getArgument(itemTitle,2);  
  if( linkArgs.length() != 0 )  
  
     //  // /auto path//......
    {  
    StringTokenizer stoke =new StringTokenizer(linkArgs, ",", false );  
    String link =stoke.nextToken();  
    String targetFrame ="_top";  
     //  // // strip frame from string......
    if( stoke.hasMoreElements() )  
    
       //  // /auto path//......
      targetFrame =stoke.nextToken();  
     
    try {  
    
      URL linkURL =new URL( baseURL, link );  
      action.setDocument( linkURL, targetFrame );  
      }  
    catch( MalformedURLException e ) {  
      action.setStatus( "Error: Malformed URL Exception" );  
    }  
     
   
} return item.getTitle();  
}  
 //  // // strip an argument from an argument string broken up by ;......
private final String getArgument( String args, int arg_number )  
  {  
  int position=0;  
  int arg_position =0;  
  try {  
  
     //  // // count across argument breakers ';'......
    while( arg_position < arg_number && position < args.length() ) {  
      if( args.charAt( position++ ) == ';' )  
      
         //  // /auto path//......
        arg_position++;  
       
    }  
     
     //  // // eat and white leading white space......
    while( args.charAt( position ) == ' ' && position < args.length() ) position++;  
      if( arg_position != arg_number )  
      
        return "";  
         
       
      int arg_start =position;  
       //  // // cut out our argument......
      while( position < args.length() && args.charAt( position ) != ';' ) position++;  
        return args.substring( arg_start, position );  
         
        }  
      catch( StringIndexOutOfBoundsException e ) {  
        return "";  
         
      }  
}  
private final Item addItemString( String itemTitle )  
  {  
  if( itemTitle.length() == 0 )  
  
    return null;  
     
     //  // // break string down into elements......
   
  StringTokenizer stoke =new StringTokenizer(itemTitle, "\\/", false);  
  FolderItem parent =m_folder;  
  Item currentItem =null;  
  while( stoke.hasMoreElements() ) {  
    String token =stoke.nextToken();  
     //  // // find item in the list......
    currentItem =parent.findItem( token );  
     //  // // no item create one......
    if( currentItem == null )  
    
       //  // /auto path//......
      {  
      if( stoke.hasMoreElements() )  
      
         //  // /auto path//......
        {  
        currentItem =(Item)new FolderItem( token );  
        }  
      else  
        {  
        currentItem =new Item( token );  
       
      } parent.addElement( currentItem );  
      }  
    else  
       //  // // if found item......
       //  // // if more elements to come......
      {  
      if( stoke.hasMoreElements() )  
      
         //  // /auto path//......
         //  // // if we are not a folder convert item to a folder......
        {  
        if( !(currentItem instanceof FolderItem) )  
        
           //  // /auto path//......
          {  
          Item converted =(Item) new FolderItem(currentItem );  
          parent.replaceItem( currentItem, converted );  
          currentItem =converted;  
         
       
     
     //  // // end else found......
     //  // // if more elements make parent currentItem......
    }  
    } } if( stoke.hasMoreElements() )  
    
       //  // /auto path//......
      parent =(FolderItem)currentItem;  
     
  }  
   //  // // end while tokens......
return currentItem;  
}  
 //  //  FlowCode File: TreeApp.java.ins......
 //  //  Export  File: TreeApp.java......
 //  //  Export  Date: 06:15:57 PM - 29:Aug:1999......
}       /** end of class definition **/ //  <------------ end of class definition...
//  FlowCode File: JavaApplet.ins...
//  Export  File: JavaApplet.java...
//  Export  Date: 10:27:44 PM - 29:Aug:1999...

