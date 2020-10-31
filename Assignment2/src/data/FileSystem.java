package data;

public class FileSystem {
  
 private FileSystemNode structure = new FileSystemNode(new Directory("/"));
 
 private FileSystemNode current_node = structure;
 
 public FileSystemNode getStructure() {
   return this.structure;
 }
 
 public FileSystemNode getCurrentDirectory() {
   return this.current_node;
 }
 
 public FileSystemNode getFileSystemNode(String full_path) {
   
   String path[] = full_path.split("/");
   
   FileSystemNode tracker = null;
   
   if (full_path.charAt(0) == '/'){
     tracker = structure;
   } else {
     tracker = current_node;
   }
     
     
   int counter = 0;
   int total_elements = 0;
   
   for (String mini_path : path) {
     total_elements = tracker.getChildren().size();
     for (FileSystemNode node : tracker.getChildren()) {
       if (node.getDirectory().getDirectoryName() == mini_path) {
           tracker = node;
           break;
       }
       counter += 1;
     }
     if (counter == total_elements) {
       return null;
     }
     
   }
   
   return tracker;
   
 }
 
}
