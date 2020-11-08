package data;
import runtime.ErrorHandler;

public class FileSystem {
  
 //A FileSystem had a root and a currentFileSystemNode 
 private FileSystemNode root = new FileSystemNode(new Directory("/"));
 private FileSystemNode currentFileSystemNode = root;
 
 //getRoot returns the root of the FileSystem
 public FileSystemNode getRoot() {
   return this.root;
 }
 
 //getCurrentDirectory returns the current FileSystemNode
 public FileSystemNode getCurrentDirectory() {
   return this.currentFileSystemNode;
 }
 
 //setCurrentDirectory sets the currentFileSystemNode to targetNode
 public void setCurrentDirectory(FileSystemNode targetNode) {
   this.currentFileSystemNode = targetNode;
 }
 
 //getFileSystemNode returns the FileSystemNode in this FileSystem 
 //that the path points to
 public FileSystemNode getFileSystemNode(String givenPath) {
   
   //path is an array of sub paths in givenPath
   String path[];
       
   FileSystemNode tracker = null;
   
   if (givenPath.equals("/")) {
     return getRoot();
   } else if (givenPath.equals(" ")){
     return getCurrentDirectory();
   } else if (inappropriatePath(givenPath)) {
     return null;
   }
   
   
   
   //Check if the givenPath is a full or relative path
   if (givenPath.charAt(0) == '/'){
     
     path = givenPath.substring(1).split("/");
     
     //if givenPath is a full path, the tracker starts at the root
     tracker = root;
     
   } else {
     
     path = givenPath.split("/");
     
     //if the givenPath is a relative path, the tracker starts 
     //at the currentFileSystemNode
     tracker = currentFileSystemNode;
   }
     
   //counter will keep track of how many children we have look for so far  
   int counter = 0;
   int totalElements = 0;
   
   //Traverse through each subPath in path
   for (String subPath : path) {
     
     //totalElements is the total amount of children in the FileSystemNode 
     //the tracker refers to
     totalElements = tracker.getChildren().size();
     
     //Traverses through each child of the tracker
     for (FileSystemNode child : tracker.getChildren()) {
       
       //Check if the child matches the subPath
       if (child.getDirectory().getDirectoryName().equals(subPath)) {
           
           
           //if the child matches the subPath, set the tracker to the child 
           //and break the loop
           tracker = child;
           break;
           
       } else {
         
         //Increase the number of children encounter by 1
         counter += 1;
         
       }
     }
     
     //Check if the counter is equal to the number of children
     if (counter == totalElements) {
       
       //if the counter is equal to the number of children, the child is not 
       //a children of tracker so return null because the path is invalid
       
       ErrorHandler.invalidPath(givenPath);
       
       return null;
       
     }
     
     //Reset the counter
     counter = 0;
     
   }
   
   //Return the FileSystemNode the tracker refers to
   return tracker;
   
 }
 
 public boolean inappropriatePath(String givenPath) {
   
   String junkCharacters = ". !@#$%^&*(){}~|<>?";
   
   if (givenPath.indexOf("//") != -1) {
     ErrorHandler.inappropriatePath(givenPath);
     return true;
   }
   
   for (int i=0; i < givenPath.length(); i = i + 1) {
     if (junkCharacters.indexOf(givenPath.charAt(i)) != -1) {
       ErrorHandler.inappropriatePath(givenPath);
       return true;
     }
   }
   
   return false;
   
 }
 
 public FileSystemNode getSemiFileSystemNode(String givenPath) {
   
   String[] splitPath;
   String targetPath = "";
   
   if (inappropriatePath(givenPath)) {
     return null;
   }
   
   if (givenPath.charAt(0) == '/') {
     
     splitPath = givenPath.substring(1).split("/");
     targetPath = "/";
     
   } else {
   
     splitPath = givenPath.split("/");
   
   }
   
   //Check if givenPath referred to the current Directory or a Directory at the root
   if (splitPath.length != 1) {
   
     for (int i = 0; i < splitPath.length - 2; i = i + 1) {
       targetPath = targetPath + splitPath[i] + "/";
     }
     
     targetPath += splitPath[splitPath.length - 2];
     
   } else {
     
     if (givenPath.charAt(0) != '/') {
       targetPath = " ";
     }
   }
   
   return getFileSystemNode(targetPath);
   
 }
 
 
}
