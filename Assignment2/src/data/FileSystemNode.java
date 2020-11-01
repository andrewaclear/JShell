package data;

import java.util.*; 

public class FileSystemNode {
  
  private Directory directory;
  private List<FileSystemNode> children = new ArrayList<FileSystemNode>();
  private FileSystemNode parent = null;

  public FileSystemNode(Directory directory) {
      this.directory = directory;
  }

  public void addChild(FileSystemNode child) {
      child.setParent(this);
      this.children.add(child);
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
    FileSystemNode currentNode = this;
    
    while (currentNode != null) {
      if (currentNode.getParent() != null) {
        path = currentNode.directory.getDirectoryName() + "/" + path;
      }
      else {
        path = currentNode.directory.getDirectoryName() + path;
      }
        
      currentNode = currentNode.getParent();
    }
    
    return path;
  }
  
  public boolean isChildInside(String directoryName) {
    
    for (FileSystemNode child : this.children) {
      if (child.getDirectory().getDirectoryName() == directoryName) {
        return true;
      }
    }
    
    return false;
    
  }
  
}
