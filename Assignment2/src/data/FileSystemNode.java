package data;

import java.util.*; 

public class FileSystemNode {
  
  private String name;
  private LinkedList<Directory> directories  = new LinkedList<Directory>();
  private List<FileSystemNode> children = new ArrayList<>();
  private FileSystemNode parent = null;

  public FileSystemNode(String name) {
      this.name = name;
  }
  
  public void addDirectory(Directory directory) {
    this.directories.add(directory);
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

  public LinkedList<Directory> getData() {
      return this.directories;
  }

  public void setData(LinkedList<Directory> directories) {
      this.directories = directories;
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
  
}


