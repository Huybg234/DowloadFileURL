package url;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String link = "http://www.africau.edu/images/default/sample.pdf";
        File out = new File("C:\\Users\\HuyNQ\\Desktop\\huy.pdf");
        new Thread(new Dowload(link,out)).start();
    }
}
