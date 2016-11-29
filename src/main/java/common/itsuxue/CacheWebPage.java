package common.itsuxue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CacheWebPage {
  
  
  public static void cachePageByHtmlLink(Document linkDoc, String cachePath) {
    try {
      File pathFile = new File(cachePath);
      if (!pathFile.exists()) {
        pathFile.mkdirs();
      }

      List<Element> aList = linkDoc.getElementsByTag("a");
      for (int i = 0; i < aList.size(); i++) {
        Element el = aList.get(i);
        String href = el.absUrl("href");
        if (href.toLowerCase().contains("http") && href.toLowerCase().contains("TortoiseSVN_zh_CN")
            && !href.toLowerCase().contains("#")) {
          Document articleDoc = Jsoup.connect(href).get();

          String namesArr[] = href.split("/");

          String name = namesArr[namesArr.length - 1];
          name = pathFile.getPath() + "/" + name;

          // Element contentEl = articleDoc.getElementById("content");

          if (articleDoc != null) {

            // articleDoc.body().html(contentEl.toString());

            // 保存图片
            Elements imgEls = articleDoc.getElementsByTag("img");
            for (int imgIndex = 0; imgEls != null && imgIndex < imgEls.size(); imgIndex++) {
              Element imgEl = imgEls.get(imgIndex);
              System.out.println("img---" + imgEl.absUrl("src"));
              String imgSrc = imgEl.absUrl("src");

              String tmpNameArr[] = imgSrc.split("/");
              String imgName = tmpNameArr[tmpNameArr.length - 1];

              File imgPathFile = new File(pathFile.getPath() + "/images/");
              if (!imgPathFile.exists()) {
                imgPathFile.mkdirs();
              }

              // ///////////////////////////////////
              // downloadFile(imgSrc, imgPathFile.getPath() + "/" + imgName);

              imgEl.attr("src", "images/" + imgName);
            }

            // 保存视频
            Elements embedEls = articleDoc.getElementsByTag("embed");
            for (int imgIndex = 0; embedEls != null && imgIndex < embedEls.size(); imgIndex++) {
              Element embedEl = embedEls.get(imgIndex);
              String embedSrc = embedEl.absUrl("src");

              String tmpNameArr[] = embedSrc.split("/");
              String embedName = tmpNameArr[tmpNameArr.length - 1];

              File embedPathFile = new File(pathFile.getPath() + "/images/");
              if (!embedPathFile.exists()) {
                embedPathFile.mkdirs();
              }
              // ///////////////////////////////////////////
              // downloadFile(embedSrc, embedPathFile.getPath() + "/" +
              // embedName);

              embedEl.attr("src", "images/" + embedName);
            }

            // name = name.replace("python3-", "");

            if (new File(name).exists()) {
              System.out.println("------------------------------------------");
              System.out.println("文件重复：" + name);
              System.out.println("------------------------------------------");
            } else {

              // 再次取页面中的链接
              /*
               * Elements childAEls = articleDoc.getElementsByTag("a"); for (int
               * childIndex = 0; childAEls != null && childIndex <
               * childAEls.size(); childIndex++) { Element tmpEl =
               * childAEls.get(childIndex); String childAHref =
               * tmpEl.absUrl("href"); if
               * (childAHref.contains("/python3/python3-")) {
               * System.out.println("B" + childAHref); Document childDoc =
               * Jsoup.connect(childAHref).get();
               * 
               * //cachePageByHtmlLink(childDoc, cachePath);
               * 
               * } }
               */

              FileOutputStream fos = new FileOutputStream(name);
              fos.write(articleDoc.toString().getBytes());
              fos.close();

              // cachePageByHtmlLink(articleDoc, cachePath);

            }

          }
        }
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  
  
  public static void cacheWebHttps(String linkFile,String baseUrlPath) {
      String cachePath = "d:/itsuxue";
      
      Document linkDoc = Jsoup.parse(GetPageHttps.getPageHttps(linkFile));

      cachePageHttps(linkDoc, cachePath,baseUrlPath);

  }
  
  public static void cachePageHttps(Document linkDoc, String cachePath,String baseUrlPath) {
    try {
      File pathFile = new File(cachePath);
      if (!pathFile.exists()) {
        pathFile.mkdirs();
      }

      List<Element> aList = linkDoc.getElementsByTag("a");
      for (int i = 0; i < aList.size(); i++) {
        Element el = aList.get(i);
        String href = baseUrlPath+el.attr("href");
        if (href.toLowerCase().contains("http") && href.contains("TortoiseSVN_zh_CN")
            && !href.toLowerCase().contains("#")) {
          
          
          Document articleDoc = Jsoup.parse(GetPageHttps.getPageHttps(href));

          String namesArr[] = href.split("/");

          String name = namesArr[namesArr.length - 1];
          name = pathFile.getPath() + "/" + name;

          if (articleDoc != null) {
            // 保存图片
            Elements imgEls = articleDoc.getElementsByTag("img");
            for (int imgIndex = 0; imgEls != null && imgIndex < imgEls.size(); imgIndex++) {
              Element imgEl = imgEls.get(imgIndex);
              String imgSrc = baseUrlPath+imgEl.attr("src");
              
              System.out.println(imgSrc);

              String tmpNameArr[] = imgSrc.split("/");
              String imgName = tmpNameArr[tmpNameArr.length - 1];

              File imgPathFile = new File(pathFile.getPath() + "/images/");
              if (!imgPathFile.exists()) {
                imgPathFile.mkdirs();
              }

              DownloadFile.downloadFileHttps(imgSrc, imgPathFile.getPath() + "/" + imgName);

              imgEl.attr("src", "images/" + imgName);
            }

            // 保存视频
            Elements embedEls = articleDoc.getElementsByTag("embed");
            for (int imgIndex = 0; embedEls != null && imgIndex < embedEls.size(); imgIndex++) {
              Element embedEl = embedEls.get(imgIndex);
              String embedSrc = baseUrlPath+embedEl.attr("src");

              String tmpNameArr[] = embedSrc.split("/");
              String embedName = tmpNameArr[tmpNameArr.length - 1];

              File embedPathFile = new File(pathFile.getPath() + "/images/");
              if (!embedPathFile.exists()) {
                embedPathFile.mkdirs();
              }
              
              DownloadFile.downloadFileHttps(embedSrc, embedPathFile.getPath() + "/" +  embedName);
              

              embedEl.attr("src", "images/" + embedName);
            }

            if (new File(name).exists()) {
              System.out.println("------------------------------------------");
              System.out.println("文件重复：" + name);
              System.out.println("------------------------------------------");
            } else {
              FileOutputStream fos = new FileOutputStream(name);
              fos.write(articleDoc.toString().getBytes());
              fos.close();

              // cachePageByHtmlLink(articleDoc, cachePath);

            }

          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  
  
  
  
}
