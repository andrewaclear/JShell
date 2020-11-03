package data;

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
   
   //splits the givenPath into sub paths that will be contained 
   //in the array path
   String path[] = givenPath.split("/");
   
   FileSystemNode tracker = null;
   
   if (givenPath == "/") {
     return this.root;
   }
   
   //Check if the givenPath is a full or relative path
   if (givenPath.charAt(0) == '/'){
     
     //if givenPath is a full path, the tracker starts at the root
     tracker = root;
     
   } else {
     
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
       if (child.getDirectory().getDirectoryName() == subPath) {
           
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
       return null;
       
     }
     
     //Reset the counter
     counter = 0;
     
   }
   
   //Return the FileSystemNode the tracker refers to
   return tracker;
   
 }
 
}
