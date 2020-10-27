package data;

public class FileSystem {
  
 private FileSystemNode structure = new FileSystemNode(new Directory("Desktop"));
 
 public FileSystemNode getStructure() {
   return this.structure;
 }
  
}