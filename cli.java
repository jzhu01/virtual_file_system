import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Date;
/*
 * Operating Systems Lab Final
 * @author: Jen Zhu
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
                System.out.println("command :"+command);
                if(command.equals("create")){ // > create fileName
                    String fileName = st.nextToken();
                   // System.out.println("fileName: "+fileName);
                    if(fs.createFile(fileName , 0, new Date())){
                        System.out.println("file is created successfully");
                    }
                    else{
                        System.out.println("file creation failed");
                    }
                }
                if(command.equals("ls")){ // > ls
                    System.out.println(fs.list());
                }
                if(command.equals("quit")){ // > quit
                    System.out.println("See you again");
                    return;
                }
                if(command.equals("put")){ // > put fileName content
                    String fileName = st.nextToken();
                    String temp = commandLine.substring(commandLine.indexOf(" ")+1); //filename
                    String temp2 = temp.substring(temp.indexOf(" ")+1); // rest aka content

                    if(fs.write(fileName,temp2)){
                        System.out.println(fileName + "is updated");

                    }
                    else{
                        System.out.println("an error occured");
                    }
                }
                if(command.equals("get")){ // > get filename
                    String fileName = st.nextToken();

                    System.out.println("content of "+ fileName + " is : "+ fs.read(fileName));
                }

        }
    }
}
