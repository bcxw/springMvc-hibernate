package common.itsuxue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetPageHttps {

  public static String getPageHttps(String pagePath) {
    StringBuffer pageContent = new StringBuffer();
    try {
      URL url = new URL(pagePath);
      HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
      httpsConn.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
      InputStream ins = httpsConn.getInputStream();
      BufferedReader breader = new BufferedReader(new InputStreamReader(ins));

      String tempString = null;
      while ((tempString = breader.readLine()) != null) {
        pageContent.append(tempString);
        pageContent.append("\r\n");
      }

    } catch (Exception e) {
      e.printStackTrace();

    }
    return pageContent.toString();
  }
}