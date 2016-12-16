import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

// import filesystem;
// import cli;
// import myFile;

/*
 * Operating Systems Lab Final
 * @author: Sadi Evren Seker, Jen Zhu
 * Due Date: 12/15/16
 *
 */

public class controller {
    public static void main(String[] args) {
        filesystem fs = new filesystem();
        cli c1 = new cli(fs);
        c1.start();
        // how do we test multiple cli at once without overriding one another? 
        // And being in the same instance?
        //cli c2 = new cli(fs);
        //c2.start();
    }
}
