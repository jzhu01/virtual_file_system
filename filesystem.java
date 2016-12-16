import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
 * Operating Systems Lab Final
 * @author: Sadi Evren Seker, Jen Zhu
 * Due Date: 12/15/16
 *
 */

/** Class for filesystem */
class filesystem {

    ArrayList<myFile> fs;
    //ArrayList<sharedFile> currentlyAccessedFiles;
    ArrayList<myFile> currentlyReading;
    ArrayList<myFile> currentlyWriting;


    public filesystem() {
        this.fs = new ArrayList<myFile>();
        //this.currentFiles = new ArrayList<sharedFile>();
        this.currentlyReading = new ArrayList<myFile>();
        this.currentlyWriting = new ArrayList<myFile>();

    } 

    /** Helper Method to see if currentlyReading contains the file*/

    /** Method will create a new file */
    public boolean createFile(String name,int size, Date date){
        for (myFile f: fs){           // if file already in the filesystem, return false
            if (f.name.equals(name)){
                return false;
            }
        }
        myFile newFile = new myFile(name,size,date);  // otherwise, create a new file
        fs.add(newFile);
        return true;
    }

    /** Method will WRITE to a file */
    // Note: Write will overwrite all information in the file, insert will add to existing
    // content in the file, both will use writeLocks
    // Open a file for writing while another is reading  -> NO
    public boolean write(String fileName, String content){
        for(myFile f : fs){
            if(f.name.equals(fileName)){
                if (currentlyReading.contains(f) || currentlyWriting.contains(f)){
                    System.out.println("Unable to access file for writing.");
                    return false;
                }
                currentlyWriting.add(f);
                Date updatedDate = new Date();
                try {
                    f.write(content, updatedDate);
                } catch (InterruptedException e) {
                    //error handling
                }
                return true;
            }
        }
        System.out.println("file not found");
        return false;
    }

    /** Method will READ a file */
    // Open a file for reading while already reading -> OK
    // Open a file for reading while another thread is writing -> NO
    public String read(String fileName){
        for(myFile f : fs){
            if(f.name.equals(fileName)){
                // call method from myFile here
                if (currentlyWriting.contains(f)){
                    System.out.println("Unable to access file for reading.");
                    return "Null. \nAn Error occurred, please try closing the file and trying again.";
                }
                if (!currentlyReading.contains(f)){
                    currentlyReading.add(f);  
                }
                try {
                    String content = f.read();
                    return content;
                } catch (InterruptedException e){
                    //error handling
                }
            }
        }
        System.out.println("file not found");
        return null;
    }

    /** Method will LIST all the files in the directory */
    public String list(){
        String returnval = "";
        for(myFile f : fs){
            returnval+= f.toString() + "\n";
        }
        return returnval;
    }

    /** Method will COPY file contents into another file */
    // Copy from file while another thread is writing to the file -> NO
    // Copy from file while another is reading -> OK
    // Copy into a file while another is reading -> NO
    // Copy into file while another thread is writing to the file -> NO
    public boolean copy(String originalFileName, String fileCopiedToName){
        myFile originalFile = null;
        myFile fileCopiedTo = null;
        for (myFile f: fs){       // loop through all the files and check to see i they are equal to 2 files
            if (f.name.equals(originalFileName)) {
                originalFile = f;
            } else if (f.name.equals(fileCopiedToName)){
                fileCopiedTo = f;
            }
        }
        if (currentlyReading.contains(fileCopiedTo) || currentlyWriting.contains(fileCopiedTo)){
            System.out.println("Unbale to copy to file.");
            return false;
        } if (currentlyWriting.contains(originalFile)){
            System.out.println("unable to copy from file.");
            return false;
        }
        if (!currentlyReading.contains(originalFile)){
            currentlyReading.add(originalFile);
        } 
        currentlyWriting.add(fileCopiedTo);
        fileCopiedTo.content = originalFile.content;
        return true;
    }

    /** Method will delete a file */
    // Delete a file while another thread is reading/writing -> NO
    // Delete a file while another thread is writing -> NO
    public void delete(String fileToDeleteName){
        myFile fileToDelete = null;
        for (myFile f:fs){
            if (f.name.equals(fileToDeleteName)){
                fileToDelete = f;
            }
        } 
        if (currentlyWriting.contains(fileToDelete) || currentlyReading.contains(fileToDelete)){
            System.out.println("Unable to delete. Please close all locks and try again.");
            return;
        }
        fs.remove(fs.indexOf(fileToDelete));        
    }

    /** Method to insert into a file (add to the pre-existing text in the file) */
    public boolean insert(String fileToUpdate, String content){
        for (myFile f:fs){
            if (f.name.equals(fileToUpdate)){
                if (currentlyReading.contains(f) || currentlyWriting.contains(f)){
                    System.out.println("Unable to insert into the file.");
                    return false;
                }
                currentlyWriting.add(f);
                Date newDate = new Date();
                try {
                    f.insert(content, newDate);
                    return true;
                } catch (InterruptedException e){
                    // error handling
                }
            }
        }
        System.out.println("file not found");
        return false;
    }

    /** Method to rename a file */
    public boolean rename(String fileToRename, String newName) {
        for (myFile f: fs){
            if (f.name.equals(fileToRename)){
                if (currentlyReading.contains(f) || currentlyWriting.contains(f)){
                    return false;
                }
                f.rename(newName);
                return true;
            }
        }
        System.out.println("Unable to rename file.");
        return false;
    }

    /** Method to Close locks on file */
    public void close(String fileToClose){
        //System.out.println("Entered the close method!");
        for (myFile f: fs){
            if (f.name.equals(fileToClose)){
                // System.out.println("Found the file!");
                f.close();
                if (currentlyWriting.contains(f)) {
                    currentlyWriting.remove(f);
                } if (currentlyReading.contains(f)) {
                    currentlyWriting.remove(f);
                }
            }
        }
    }
    /** Additional methods to be Addressed: */
    // locking in general (? how to test?)
    //  --> create a data structure, synchronize via data structure
    //  --> array keeping track of locks on numerous files and check permissions from array before opening file

    // Locking Situations: 
    // Open a file for reading while already reading -> OK
    // Open a file for writing while another is reading  -> NO
    // Copy a file while another is reading -> OK
    // Delete a file while another thread is reading/writing -> NO
    // Insert into a file while another thread is reading -> NO
    // Open a file for reading while another thread is writing -> NO
    // Delete a file while another thread is writing -> NO
    // Copy from file while another thread is writing to the file -> NO
    // Copy into file while another thread is writing to the file -> NO
    // 

}
