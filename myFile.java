import java.util.Date;
//import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/*
 * Operating Systems Lab Final
 * @author: Sadi Evren Seker, Jen Zhu
 * Due Date: 12/15/16
 *
 */

/** Class for File */
class myFile{
    String name;
    int size;
    Date date;
    String content;
    boolean notClosed; 
    private final Object lock = new Object();

    public myFile(String name, int size, Date date) {
        this.name = name;
        this.size = size;
        this.date = date;
        this.notClosed = true;
    }

    public String toString(){
        return name +" "+ size +" "+ date;
    }

    public String read() throws InterruptedException {
        // need some sort of locking mechanism here
        // assume the contents of the file are a string, with no breaks
        synchronized(lock){
            String content = this.content;
            while(notClosed){
                System.out.println("File has not been closed from reading. Will try again in 20 seconds.");
                TimeUnit.SECONDS.sleep(5);
            }
        }
        return content;
    }

    public void write(String content, Date date) throws InterruptedException {
        // need some sort of locking mechanism here
        // will inquire professor about specifics later
        synchronized(lock){
            this.content = content;
            this.date = date;
            this.size = content.length();
        }
        // System.out.println("File has not been closed from writing. Will try again in 20 seconds.");
        // TimeUnit.SECONDS.sleep(10);

    }

    public void insert(String content, Date date) throws InterruptedException {
        synchronized(lock){
            this.content += content;
            this.date = date;
            this.size = this.content.length();
        }
    }

    public void rename(String updatedName){
        synchronized(lock){
            this.name = updatedName;
        }
    }

    public void close(){
        System.out.println("Entered the close method of the file!");
        this.notClosed = false;
    }

}
