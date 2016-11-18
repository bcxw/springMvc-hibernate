package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Itsuxue {

	/**
	 * 修改某个文件的后缀
	 * 
	 * @param file
	 * @param suffix
	 */
	public static void modifyFileSuffix(File file, String fromSuffix,
			String toSuffix) {
		String fileName = file.getPath().substring(0,
				file.getPath().lastIndexOf(".") + 1);
		String fileSuffix = file.getPath().substring(
				file.getPath().lastIndexOf(".") + 1, file.getPath().length());
		if (fileSuffix.equals(fromSuffix)) {
			file.renameTo(new File(fileName + toSuffix));
		}
	}

	/**
	 * 修改某个文件夹及子文件夹中文件的后缀
	 * 
	 * @param path
	 * @param suffix
	 */
	public static void modifyDirsSuffix(String path, String fromSuffix,
			String toSuffix) {
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; tempList != null && i < tempList.length; i++) {
			File oneFile = tempList[i];
			if (oneFile.isFile()) {
				modifyFileSuffix(oneFile, fromSuffix, toSuffix);
			} else if (oneFile.isDirectory()) {
				modifyDirsSuffix(oneFile.getPath(), fromSuffix, toSuffix);
			}
		}
	}
	
	/**
	 * 把目录中的文件编码由GBK转为UTF8
	 * @param path
	 */
	public static void filesGBK2UTF8(String path){
		try {
			File file = new File(path);
			File[] tempList = file.listFiles();
			for (int i = 0; tempList != null && i < tempList.length; i++) {
				File oneFile = tempList[i];
				if (oneFile.isFile()) {
					String utf8FilePath=path+"utf8/";
					File newPath=new File(utf8FilePath);
					newPath.mkdirs();
					File newFile=new File(utf8FilePath+oneFile.getName());
					newFile.createNewFile();
					BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(oneFile),"gb2312"));  
					String line = null;
					String lines="";
					while ((line = br.readLine()) != null) {  
						lines=lines+line+"\r\n";
					}
					br.close();  
					
					/////////文件特殊处理///////////
//					lines=lines.replace("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">", "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
//					lines=lines+"\r\n</body></html>";
//					System.out.println(lines);
					////////////////////
					
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
					bw.write(lines);
					bw.close();
					
				} else if (oneFile.isDirectory()) {
					filesGBK2UTF8(oneFile.getPath());
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void changeALink (Document doc){
		Elements titleEls=doc.getElementsByTag("a");
		for(int m=0;titleEls!=null&&m<titleEls.size();m++){
			Element a=titleEls.get(m);
			
			String href=a.attr("href");
			if(href.contains("#")){
				
				String text=a.text();
				
				
				if(text.split("\\.").length>4){
					a.attr("href", "#"+href.split("#")[1]);
				}else{
					a.attr("href", href.split("#")[1]+".html");
				}
				
				
				
			}
			
		}
	}
	
	//mysql 处理文件内容乱的问题
	public static List<String> getALinks(String url){
		List<String> returnList=new ArrayList<String>();
		
		String oldPath="D:/mysql/MYSQL5";
		String newPath="D:/mysql/html";
		
		
		try {
			Document doc = Jsoup.parse(new File(url), "utf-8");
			List<Element> tagList=doc.getElementsByTag("a");
			
			String preFile="";
			String docStr="";
			
			for(int i=0;i<tagList.size();i++){
				Element element=tagList.get(i);
				String href=element.attr("href");
				
				String linkText=element.text();
				
				String title=element.text();
				
				String tArr[]=title.split("\\.");
				title=tArr.length>1?tArr[tArr.length-1]:title;
				
				
				
				title=title.trim();
				
				
				
				if(!title.toLowerCase().contains("mysql"))title="MySQL "+title;
				title=title+"_IT速学网";
				
				String tagName=href.replace(".html", "");
				
				String original=element.attr("original");
				
				if(!href.contains("javascript")){
					String newFilePath="";
					Document oldDoc=null;
					if(StringUtils.isNotEmpty(original)){
						oldDoc = Jsoup.parse(new File(oldPath+"/"+original), "utf-8");
						
						Elements titleEls=oldDoc.getElementsByAttributeValue("name", tagName);
						
						Element titleNameEl=titleEls.get(0);
						
						if(titleNameEl.parent().previousElementSibling()==null){
							Element contentEl=titleNameEl.parent().parent().parent().parent().parent();
							docStr=contentEl.html().toString();
						}else{
							String preAppendStr=titleNameEl.parent().parent().toString();
							
							
//							Element contentEl=titleNameEl.parent().parent().parent().parent().parent();
//							String tempContent=contentEl.toString();
//							contentEl.getElementsByAttributeValue("name", tagName).get(0).parent().parent().html(titleNameEl.parent().toString());
//							docStr=contentEl.toString();
//							
//							//头部错位，要把头部之前的内容放入上一个文件中
//							Element preE=Jsoup.parse(tempContent);
//							preE.getElementsByAttributeValue("name", tagName).get(0).remove();
//							
//							
							
							Element contentEl=titleNameEl.parent().parent().parent().parent().parent();
							
							contentEl.getElementsByAttributeValue("name", tagName).get(0).parent().parent().html(titleNameEl.parent().toString());
							
							docStr=contentEl.html().toString();
							
							//头部错位，要把头部之前的内容放入上一个文件中
							Document pDoc=Jsoup.parse(new File(preFile), "utf-8");
							Document pAppendDoc=Jsoup.parse(preAppendStr);
							pAppendDoc.getElementsByAttributeValue("name", tagName).get(0).parent().remove();
							pDoc.body().append(pAppendDoc.body().html());
							
							changeALink(pDoc);
							FileOutputStream fos = new FileOutputStream(preFile);
							fos.write(pDoc.toString().getBytes());
							fos.close();
						}
						
						
						
					}else{
						oldDoc = Jsoup.parse(new File(oldPath+"/"+href), "utf-8");
						docStr=oldDoc.html().toString();
					}
					newFilePath=newPath+"/"+href;
					File newFile=new File(newFilePath);
					if(!newFile.exists()){
						newFile.createNewFile();
					}
					
					Document endDoc=Jsoup.parse(docStr);
					Elements tempElements=endDoc.getElementsByAttributeValue("class", "section");
					
					String linkTextTArr[]=linkText.split("\\.");
					if(linkTextTArr.length<4){
						for(int j=0;tempElements!=null&&j<tempElements.size();j++){
							if(tempElements.get(j).tagName().equals("div"))
							tempElements.get(j).remove();
						}
					}
					
					changeALink(endDoc);
					
					
					endDoc.getElementsByTag("head").get(0).html("<title>"+title+"</title>");
					
					docStr=endDoc.toString();
					
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(docStr.getBytes());
					fos.close();
					preFile=newFilePath;
				}
				

				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnList;
	}

	
	//excel check title
	public static void checkTitle(){
		File file=new File("C:/ComsenzEXP/wwwroot/office/excel");
		
		File fList[]=file.listFiles();
		
		for( File tFile:fList){
			if(tFile.getName().contains("php")){
				try {
					Document endDoc=Jsoup.parse(tFile,"utf-8");
					Elements ale=endDoc.getElementsByTag("title");
					
					if(ale!=null&&ale.size()>0){
						
						if(ale.get(0).toString().contains("backtotop")){
							System.out.println(tFile.getName()+"-----------"+endDoc.getElementsByTag("title").get(0).toString());
						}
					}else{
						System.out.println("err---"+tFile.getName());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
//		checkTitle();
		
//		 getALinks("D:/mysql/list.html");
		
		modifyDirsSuffix("D:/mysql/html","html", "php");
		
//		filesGBK2UTF8("D:/example");
		
//		Document doc = Jsoup.parse("<div><a></a><span id='test'></span></div>");
//		
//		Element element= doc.getElementById("test");
//		
//		Element pe=element.previousElementSibling();
//		
//		System.out.println(pe);
		
	}

}
