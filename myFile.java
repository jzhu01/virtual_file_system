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
    private Semaphore readerLock = new Semaphore(10);
    private Semaphore writerLock = new Semaphore(1);
    public myFile(String name, int size, Date date) {
        this.name = name;
        this.size = size;
        this.date = date;
    }
    public String toString(){
        return name +" "+ size +" "+ date;
    }
    public String read() throws InterruptedException {
        // need some sort of locking mechanism here
        // assume the contents of the file are a string, with no breaks
        readerLock.acquire();
        String content = this.content;
        readerLock.release();
        return content;
    }
    public void write(String content, Date date) throws InterruptedException {
        // need some sort of locking mechanism here
        // will inquire professor about specifics later
        writerLock.acquire();
        this.content = content;
        this.date = date;
        this.size = content.length();
        writerLock.release();

    }

    public void insert(String content, Date date) throws InterruptedException {
        writerLock.acquire();
        this.content += content;
        this.date = date;
        this.size = this.content.length();
        writerLock.release();
    }
}
