import java.util.ArrayList;
import java.util.Date;

/*
 * Operating Systems Lab Final
 * @author: Jen Zhu
 * Due Date: 12/15/16
 *
 */

/** Class for filesystem */
class filesystem{
    ArrayList<myFile> fs = new ArrayList<myFile>();
    public boolean createFile(String name,int size, Date date){
        //if()file already exists, return false
        myFile newFile = new myFile(name,size,date);
        fs.add(newFile);
        return true;
    }
    public boolean write(String fileName, String content){
        for(myFile f : fs){
            if(f.name.equals(fileName)){
                f.content = content;
                f.date = new Date();
                f.size = content.length();
                return true;
            }
        }
        System.out.println("file not found");
        return false;
    }
    public String read(String fileName){
        for(myFile f : fs){
            if(f.name.equals(fileName)){
                return f.content;
            }
        }
        System.out.println("file not found");
        return null;
    }
    public String list(){
        String returnval = "";
        for(myFile f : fs){
            returnval+= f.toString() + "\n";
        }
        return returnval;
    }
}
