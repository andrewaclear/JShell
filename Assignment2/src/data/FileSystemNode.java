package data;

import java.util.*;
import runtime.ErrorHandler; 

public class FileSystemNode {
  
  //A FileSystemNode
  private Directory directory;
  private List<FileSystemNode> children = new ArrayList<FileSystemNode>();
  private FileSystemNode parent = null;
  
  //Constructor
  public FileSystemNode(Directory directory) {
      this.directory = directory;
  }
  
  //addChild adds a child to the children of this FileSystemNode
  public void addChild(FileSystemNode child) {
    
      //Set the parent of this child to be this FileSystem
      child.setParent(this);
      
      //Add the child to the children of this FIleSystemNode
      this.children.add(child);
      
  }

  //getChildren returns the children of this FileSystemNode
  public List<FileSystemNode> getChildren() {
      return this.children;
  }

  //setParent sets the parent of this FileSystemNode to the given parent
  private void setParent(FileSystemNode parent) {
      this.parent = parent;
  }

  //FileSystemNode returns the parent of this FileSystemNode
  public FileSystemNode getParent() {
      return this.parent;
  }
  
  //addFiles adds a file to the directory of this FileSystemNode
  public void addFile(File file) {
    this.directory.addFile(file);
  }
  
  //getDirectory returns the directory of this FileSystemNode
  public Directory getDirectory() {
    return this.directory;
  }
  
  //setDirectory sets the directory of this FileSystemNode 
  //to the given directory 
  public void setDirectory(Directory directory) {
    this.directory = directory;
  }
  
  //getPath returns the path of this FIleSystemNode
  public String getPath() {
    
    String path = this.directory.getDirectoryName();
    
    FileSystemNode currentFileSystemNode = this.parent;
    
    //Traverse through the generations before this node until the root
    while (currentFileSystemNode != null) {
      
      //Check if the currentFileSystemNode has a parent
      if (currentFileSystemNode.getParent() != null) {
        
        //If it has a parent, add currentFileSystemNode's directory's 
        //directoryName with a "/" to path
        path = currentFileSystemNode.directory.getDirectoryName() + "/" + path;
        
      }
      else {
        
        //If it deosn't have just add the currentFileSystemNode's 
        //directory's directoryName to path
        path = currentFileSystemNode.directory.getDirectoryName() + path;
      }
      
      //Set the curretnFileSystemNode to be the parent
      currentFileSystemNode = currentFileSystemNode.getParent();
    }
    
    return path;
  }
  
  //isChildInside returns true if one of the children of this FileSystemNode 
  //has the given directoryName, otherwise, false
  public boolean isChildInside(String directoryName) {
    
    for (FileSystemNode child : this.children) {
      if (child.getDirectory().getDirectoryName().equals(directoryName)) {
        ErrorHandler.childAlreadyExistant(directoryName, this);
        return true;
      }
    }
    
    return false;
    
  }
  
}
