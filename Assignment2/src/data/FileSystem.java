package data;

public class FileSystem {
  
 private FileSystemNode root = new FileSystemNode(new Directory("/"));
 
 private FileSystemNode currentNode = root;
 
 public FileSystemNode getRoot() {
   return this.root;
 }
 
 public FileSystemNode getCurrentDirectory() {
   return this.currentNode;
 }
 
 public void setCurrentDirectory(FileSystemNode node) {
   this.currentNode = node;
 }
 
 public FileSystemNode getFileSystemNode(String full_path) {
   
   String path[] = full_path.split("/");
   
   FileSystemNode tracker = null;
   
   if (full_path.charAt(0) == '/'){
     tracker = root;
   } else {
     tracker = currentNode;
   }
     
     
   int counter = 0;
   int totalElements = 0;
   
   for (String subPath : path) {
     totalElements = tracker.getChildren().size();
     for (FileSystemNode node : tracker.getChildren()) {
       if (node.getDirectory().getDirectoryName() == subPath) {
           tracker = node;
           break;
       }
       counter += 1;
     }
     if (counter == totalElements) {
       return null;
     }
     
   }
   
   return tracker;
   
 }
 
}
