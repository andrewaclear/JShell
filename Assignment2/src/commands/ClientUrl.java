package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import data.FileSystem;
import driver.JShell;
import io.StandardOutput;
import runtime.ErrorHandler;

public class ClientUrl extends Command {

  /**
   * Constructor for ClientUrl class. It initializes identifier,
   * maxNumOfArguments, minNumOfArguments errorTooManyArguments, missingOperand,
   * and description from its super class Commands.
   */
  public ClientUrl() {
    this.setIdentifier("curl");
    this.setDescription("Retrieve the file at that URL\r\n"
        + "and add it to the current working directory");
    this.setMaxNumOfArguments(2);
    this.setMinNumOfArguments(2);
    this.setErrorTooManyArguments("Too many arguments");
    this.setMissingOperand("Enter a URL");
  }


  private String getFileContent(String url) {
    try {
      String output = "";
      String curLine = "";
      // Make the given file url into a URL object
      URL fileUrl = new URL(url);
      // Open a url connection to the website storing the file
      URLConnection fileConnect = fileUrl.openConnection();
      // Turns input stream from fileConnect into an inputStreamReader object
      InputStreamReader reader =
          new InputStreamReader(fileConnect.getInputStream());
      // Takes in reader and turns read into a buffered reader
      BufferedReader fileReader = new BufferedReader(reader);

      curLine = fileReader.readLine();
      while (curLine != null) {
        output += curLine + "\n";
        curLine = fileReader.readLine();
      }

      fileReader.close();

      return output;
    } catch (IOException e) {
      this.setErrors(ErrorHandler.invalidUrl(this, url));
      return null;
    }
  }



  /**
   * Retrieves file at given URL and inserts at current directory.
   * 
   * @param tokens, array of string tokens holding command arguments
   * @param fSystem, an instance of FileSystem class to read and write to the
   *        file structure.
   * @param cache, stores the history and directory stack of the running
   *        terminal
   * @return returns a boolean true signal the shell to continue running
   */
  @Override
  public Command run(String[] tokens, JShell shell) {
    FileSystem fSystem = shell.getfSystem();
    String output = getFileContent(tokens[1]);

    if (output != null) {
      String fileName = fSystem.getPathLastEntry(tokens[1].replace(".", ""));

      if (fSystem.getCurrentDirectory().getDirectory()
          .getFileByFileName(fileName) == null) {
        String[] tokens2 = {"default", ">", fileName};
        StandardOutput.println(tokens2, output, shell, this);
      } else {
        this.setErrors(ErrorHandler.fileAlreadyExist(this, fileName));
      }
    }

    return this;
  }
  // Test: curl http://www.cs.cmu.edu/~spok/grimmtmp/073.txt
}
