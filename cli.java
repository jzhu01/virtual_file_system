import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Date;
/*
 * Operating Systems Lab Final
 * @author: Sadi Evren Seker, Jen Zhu
 * Due Date: 12/15/16
 *
 */
// import filesystem;
/** Class for Command Line Interface */
class cli extends Thread{
    filesystem fs;
    public cli(filesystem fs){
        this.fs = fs;
    }
    public void run(){
        System.out.println("Welcome to my command line interface");
        while(true){
            Scanner s = new Scanner(System.in);
            System.out.print(">>>");
            String commandLine = s.nextLine();

            StringTokenizer st = new StringTokenizer(commandLine);

            String command = st.nextToken();
            // System.out.println("command: "+command);
            
            // creating a new file
            if(command.equals("create")){ 
                String fileName = st.nextToken();
               // System.out.println("fileName: "+fileName);
                if(fs.createFile(fileName , 0, new Date())){
                    System.out.println("file has been created successfully");
                }
                else{
                    System.out.println("file creation failed");
                }
            }

            // listing all files in the directory
            if(command.equals("ls")){ // > ls
                System.out.println(fs.list());
            }

            // exiting out of the program
            if(command.equals("quit")){ // > quit
                System.out.println("See you again");
                return;
            }

            // writing to the file
            if(command.equals("put")){ // > put fileName content
                String fileName = st.nextToken();
                String temp = commandLine.substring(commandLine.indexOf(" ")+1); //filename
                String temp2 = temp.substring(temp.indexOf(" ")+1); // rest aka content

                if(fs.write(fileName,temp2)){
                    System.out.println(fileName + " has been overwritten.");

                }
                else{
                    System.out.println("An error occured. Please close the locks on the file and try again.");
                }
            }

            // reading from the file
            if(command.equals("get")){ // > get filename
                String fileName = st.nextToken();

                System.out.println("content of "+ fileName + " is: "+ fs.read(fileName));
            }

            // copying from a file to another
            if (command.equals("copy")){
                String originalFileName = st.nextToken();
                String fileNames= commandLine.substring(commandLine.indexOf(" ")+1); //originalFile
                String fileCopiedToName = fileNames.substring(fileNames.indexOf(" ")+1); // fileToBeChanged
                //System.out.println("Original File Name: "+ originalFileName);
                //System.out.println("File to be Copied To: "+fileCopiedToName);
                if (fs.copy(originalFileName, fileCopiedToName)){
                    System.out.println("File successfully copied.");
                } else {
                    System.out.println("An error occurred. Please try closing the locks on the file and trying again.");
                }
            }

            // remove a file from the filesystem
            if (command.equals("del")){
                String fileToDeleteName = st.nextToken();
                fs.delete(fileToDeleteName);
                System.out.println("File successfully deleted.");
            }

            // append text to a document
            if (command.equals("insert")){
                String fileName = st.nextToken();
                String temp = commandLine.substring(commandLine.indexOf(" ")+1); // file name
                String contentToAdd = temp.substring(temp.indexOf(" ")); // content to be added on
                //System.out.println("File successfully updated");
                if(fs.insert(fileName, contentToAdd)){
                    System.out.println(fileName+" has been successfully updated");
                } else{
                    System.out.println("An error occured. Please try closing the locks on the file and trying again.");
                }
            }

            // rename file 
            if (command.equals("rename")){
                String fileName = st.nextToken();
                String temp = commandLine.substring(commandLine.indexOf(" ")+1); // file name
                String newFileName = temp.substring(temp.indexOf(" ")); // new file name
                if (fs.rename(fileName, newFileName)){
                    System.out.println("File renamed successfully.");
                } else {
                    System.out.println("Unable to rename. Please try closing the locks on the file and trying again.");
                }
            }

            // close locks 
            if (command.equals("close")){
                String fileName = st.nextToken();
                //System.out.println("Trying to close the file...");
                fs.close(fileName);
                System.out.println("Succesffully closed "+fileName);
            }
        } // closes out of the while loop

    } // closes out of the run method
} // closes the thread
