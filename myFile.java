import java.util.Date;
import java.util.concurrent.Semaphore;
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
    // readerLock should be a int semaphore - multiple readers at the same time
    // writerLock should be a binary semaphore - only one writer at a time
    private Semaphore readerLock = new Semaphore(1);
    public myFile(String name, int size, Date date) {
        this.name = name;
        this.size = size;
        this.date = date;
    }
    public String toString(){
        return name +" "+ size +" "+ date;
    }
    public void read(){
        // need some sort of locking mechanism here
        // will inquire professor about specifics later
    }
    public void write(){
        // need some sort of locking mechanism here
        // will inquire professor about specifics later
    }
}
