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
 
}