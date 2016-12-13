import java.util.ArrayList;
import java.util.Date;

/*
 * Operating Systems Lab Final
 * @author: Jen Zhu
 * Due Date: 12/15/16
 *
 */

/** Class for filesystem */
class filesystem {

    ArrayList<myFile> fs;

    public filesystem() {
        this.fs = new ArrayList<myFile>();

    } 

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

    /** Method will write to a file */
    public boolean write(String fileName, String content){
        for(myFile f : fs){
            if(f.name.equals(fileName)){
                // call method from myFile here
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

    /** Method will read a file */
    public String read(String fileName){
        for(myFile f : fs){
            if(f.name.equals(fileName)){
                // call method from myFile here
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

    /** Method will list all the files in the directory */
    public String list(){
        String returnval = "";
        for(myFile f : fs){
            returnval+= f.toString() + "\n";
        }
        return returnval;
    }

    /** Method will copy file contents into another file */
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
        // question: are these instancees of the original files, or actually dealing with those objects?
        fileCopiedTo.content = originalFile.content;
        return true;
    }

    /** Method will delete a file */
    public void delete(String fileToDeleteName){
        myFile fileToDelete = null;
        for (myFile f:fs){
            if (f.name.equals(fileToDeleteName)){
                fileToDelete = f;
            }
        }
        fs.remove(fs.indexOf(fileToDelete));        
    }

    /** Method to insert into a file (add to the pre-existing text in the file) */
    public boolean insert(String fileToUpdate, String content){
        for (myFile f:fs){
            if (f.name.equals(fileToUpdate)){
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
    /** Additional methods to be Addressed: */
    // locking in general (? how to test?)
    // Move a file
    // --> Need to find a way to store both arrayLists of files and files in filesystem
    // --> assume both files exist, 
    // Insert into a file (? adding to content in the file?)
    // Close a file - close and remove all locks (? releasing the locks?)

}
