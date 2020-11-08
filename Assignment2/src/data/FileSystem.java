package data;
import runtime.ErrorHandler;

public class FileSystem {
  
 /*
  * FileSystem has a root and a currentFileSystemNode
  * 
  */
 private FileSystemNode root = new FileSystemNode(new Directory("/"));
 private FileSystemNode currentFileSystemNode = root;
 
 
 /*
  * getRoot returns the root of this FileSystem
  * 
  * @return The root of this FileSystem
  * 
  */
 public FileSystemNode getRoot() {
   return this.root;
 }
 
 
 /* 
  * getCurrentDirectory returns the currentFileSystemNode of this FileSystem
  *
  * @return The currentFileSystemNode of this FileSystem
  *
  */
 public FileSystemNode getCurrentDirectory() {
   return this.currentFileSystemNode;
 }
 
 
 /* 
  * setCurrentDirectory sets the currentFileSystemNode to targetNode
  * 
  * @param targetNode A FileSystemNode
  * 
  */
 public void setCurrentDirectory(FileSystemNode targetNode) {
   this.currentFileSystemNode = targetNode;
 }
 
 
 /*
  * getFileSystemNode returns the FileSystemNode the givenPath refers to if its
  * a valid/appropriate path, otherwise, return null 
  * and display an error message
  * 
  * @param givenPath A relative or full path
  * 
  * @return The FileSystemNode that givenPath points to
  * 
  */
 public FileSystemNode getFileSystemNode(String givenPath) {
   
   if (givenPath.equals("/")) {
     return getRoot();
   } else if (givenPath.equals(" ")){
     return getCurrentDirectory();
   } else if (inappropriatePath(givenPath)) {
     return null;
   }

   return traversePath(givenPath);
 }
 
 /*
  * inappropraitePath returns true and an error message when the givenPath
  * is not an appropriate path (contains illicit characters), 
  * or false otherwise
  * 
  * @param givenPath A relative or full path
  * 
  * @return true if the given path is inappropriate, otherwise, false
  * 
  */
 public boolean inappropriatePath(String givenPath) {
   
   String inappropriateCharacters = ". !@#$%^&*(){}~|<>?";
   
   if (givenPath.indexOf("//") != -1) {
     ErrorHandler.inappropriatePath(givenPath);
     return true;
   }
   
   for (int i=0; i < givenPath.length(); i = i + 1) {
     if (inappropriateCharacters.indexOf(givenPath.charAt(i)) != -1) {
       ErrorHandler.inappropriatePath(givenPath);
       return true;
     }
   }
   
   return false;
   
 }
 
 /*
  * getSemiFileSystemNode returns the FileSystemNode the givenPath refers to
  * excluding the last entry if its an valid/appropriate path, 
  * otherwise, return null and display an error message
  * 
  * @param givenPath A relative or full path
  * 
  * @return The FileSystemNode the givenPath refers to excluding the last entry
  *         which is null if the givenPath is an invalid/inaproapiate path 
  * 
  */
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
   
   //Check if givenPath referred to the current Directory or 
   //a Directory at the root
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
 
 /*
  * getPathLastEntry returns the last FileSystemNode the givenPath refers to
  * 
  * @param givenPath A relative or full path
  * 
  * @return The last FileSystemNode the givenPath refers to
  *  
  */
 public String getPathLastEntry(String givenPath) {
   
   String[] splitPath;
   
   if (givenPath.charAt(0) == '/') {
     splitPath = givenPath.substring(1).split("/");
   } else {
     splitPath = givenPath.split("/");
   }
   
   return splitPath[splitPath.length - 1];
 }
 
 
 /*
  * traversePath returns the FileSystemNode the givenPath refers to
  * 
  * @param givenPath A relative or full path
  * 
  * @return The FileSystemNode the givenPath points to
  * 
  */
 private FileSystemNode traversePath(String givenPath) {
   
   String splitPath[]; FileSystemNode nodeTracker = null;

   //Check if the givenPath is a full or relative path,
   //thats provides where the tracker should start
   if (givenPath.charAt(0) == '/'){
     splitPath = givenPath.substring(1).split("/");
     nodeTracker = root;
   } else {
     splitPath = givenPath.split("/");
     nodeTracker = currentFileSystemNode;
   }
   
   int childrenCounter = 0, totalChildren = 0;

   for (String singlePath : splitPath) {
     
     totalChildren = nodeTracker.getChildren().size();
     
     for (FileSystemNode child : nodeTracker.getChildren()) {
       if (child.getDirectory().getDirectoryName().equals(singlePath)) {
           nodeTracker = child;
           break;
       }
       childrenCounter += 1;
     }
     if (childrenCounter == totalChildren) {
       ErrorHandler.invalidPath(givenPath);
       return null;
     }
     childrenCounter = 0;
   }
   return nodeTracker;
 }
 
 
}
