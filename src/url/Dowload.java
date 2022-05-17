package url;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Dowload implements Runnable {

    String link;
    File out;

    public Dowload(String link, File out) {
        this.link = link;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            double fileSize = (double) httpURLConnection.getContentLengthLong();
            BufferedInputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            FileOutputStream outputStream = new FileOutputStream(this.out);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
            byte[] bytes = new byte[1024];
            double downloaded = 0.00;
            int read;
            double percentDownLoaded;
            while ((read = inputStream.read(bytes,0,1024)) >= 0){
                bufferedOutputStream.write(bytes,0,read);
                downloaded += read;
                percentDownLoaded = (downloaded*100)/fileSize;
                String percent = String.format("%.4f", percentDownLoaded);
                System.out.println("Doaloaded "+percent);
            }
            bufferedOutputStream.close();
            inputStream.close();
            System.out.println("Mission complete");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
