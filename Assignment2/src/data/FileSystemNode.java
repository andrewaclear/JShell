package data;

import java.util.*; 

public class FileSystemNode {
  
  private String name;
  private Directory directory = null;
  private List<FileSystemNode> children = new ArrayList<>();
  private FileSystemNode parent = null;

  public FileSystemNode(String name) {
      this.name = name;
  }

  public void addChild(FileSystemNode child) {
      child.setParent(this);
      this.children.add(child);
  }

  public void addChildren(List<FileSystemNode> children) {
      for(FileSystemNode t : children) {
          t.setParent(this);
      }
      this.children.addAll(children);
  }

  public List<FileSystemNode> getChildren() {
      return this.children;
  }

  private void setParent(FileSystemNode parent) {
      this.parent = parent;
  }

  public FileSystemNode getParent() {
      return this.parent;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void addFile(File file) {
    this.directory.addFile(file);
  }
  
  public Directory getDirectory() {
    return this.directory;
  }
  
  public void setDirectory(Directory directory) {
    this.directory = directory;
  }
  
  public String getPath() {
    String path = "";
    FileSystemNode current_node = this;
    
    while (current_node != null) {
      if (current_node.getParent() != null) {
        path = current_node.getName() + "\\" + path;
      }
      else {
        path = current_node.getName() + path;
      }
        
      current_node = current_node.getParent();
    }
    
    return path;
  }
}
