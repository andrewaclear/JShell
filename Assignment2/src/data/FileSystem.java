package data;

public class FileSystem {
  
 private FileSystemNode structure = new FileSystemNode(new Directory("Desktop"));
 
 private FileSystemNode current_node = structure;
 
 public FileSystemNode getStructure() {
   return this.structure;
 }
 
 public FileSystemNode getCurrentDirectory() {
   return this.current_node;
 }
 
}