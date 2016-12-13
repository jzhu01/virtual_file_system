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

    ArrayList<myFile> fs = new ArrayList<myFile>();
    
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
                f.content = content;
                f.date = new Date();
                f.size = content.length();
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
                return f.content;
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
    /** Additional methods to be Addressed: */
    // Move a file
    // --> assume both files exist, 
    // Insert into a file
    // Close a file - close and remove all locks

}
