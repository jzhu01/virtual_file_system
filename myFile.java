import java.util.Date;
/*
 * Operating Systems Lab Final
 * @author: Jen Zhu
 * Due Date: 12/15/16
 *
 */

/** Class for File */
class myFile{
    String name;
    int size;
    Date date;
    String content;
    public myFile(String name, int size, Date date) {
        this.name = name;
        this.size = size;
        this.date = date;
    }
    public String toString(){
        return name +" "+ size +" "+ date;
    }
}
