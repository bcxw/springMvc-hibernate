package common.itsuxue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadFile {
  /**
   * 下载远程文件并保存到本地
   * 
   * @param remoteFilePath
   *          远程文件路径
   * @param localFilePath
   *          本地文件路径
   */
  public static void downloadFile(String remoteFilePath, String localFilePath) {
    URL urlfile = null;
    HttpURLConnection httpUrl = null;
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    File f = new File(localFilePath);
    try {
      urlfile = new URL(remoteFilePath);
      httpUrl = (HttpURLConnection) urlfile.openConnection();
      httpUrl.connect();
      bis = new BufferedInputStream(httpUrl.getInputStream());
      bos = new BufferedOutputStream(new FileOutputStream(f));
      int len = 2048;
      byte[] b = new byte[len];
      while ((len = bis.read(b)) != -1) {
        bos.write(b, 0, len);
      }
      bos.flush();
      bis.close();
      httpUrl.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        bis.close();
        bos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  
  
  
  public static void downloadFileHttps(String remoteHttpsFilePath, String localFilePath) {
    URL urlfile = null;
    HttpsURLConnection httpUrl = null;
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    File f = new File(localFilePath);
    try {
      urlfile = new URL(remoteHttpsFilePath);
      httpUrl = (HttpsURLConnection) urlfile.openConnection();
      httpUrl.setRequestProperty("User-Agent",
          "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//      httpUrl.connect();
      bis = new BufferedInputStream(httpUrl.getInputStream());
      bos = new BufferedOutputStream(new FileOutputStream(f));
      int len = 2048;
      byte[] b = new byte[len];
      while ((len = bis.read(b)) != -1) {
        bos.write(b, 0, len);
      }
      bos.flush();
      bis.close();
      httpUrl.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        bis.close();
        bos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  
  public static void main(String args[]){
    
    downloadFileHttps("https://tortoisesvn.net/docs/release/TortoiseSVN_zh_CN/images/TMerge_TwoPane.png","d:/itsuxue/svn_move.png");
    
    
  }
}
