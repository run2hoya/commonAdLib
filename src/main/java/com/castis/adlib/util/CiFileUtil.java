package com.castis.adlib.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
@Slf4j
public class CiFileUtil {

	public static final String		EXTENSION_XML	=	"xml";		
	public static final String		EXTENSION_LOG	=	"log";
	/**
	 * return - Parsed Directory Name
	 * */
	public static String createDirecotryOfFilePath(String filePath) {
		String directory = getReplaceFullPath(filePath);
		if(!directory.isEmpty())
			createDirectory(directory);
		return directory;
	}
	public static String getReplaceFullPath(String filePath) {
		String result = filePath.replace("\\", "/");
		String lastCh = result.substring(result.length()-1);
		if(!lastCh.equals("/")){
			result = result + "/";
		}
		return result;
	}
	
	public static String getDirectoryName(String filePath) {
		try {
			int pos = filePath.lastIndexOf("/");
			String directory = "";
			if(pos > 0) {
				directory = filePath.substring(0, pos);
			} else {
				pos = filePath.lastIndexOf("\\");
				if(pos > 0) {
					directory = filePath.substring(0, pos);
				}
			}
			return directory;
		} catch (Exception e) {
			log.error("[" + filePath + "] GetDirectoryName fail", e);
			return "";
		}
	}
	
	public static List<File> getFileList(String directory, boolean bRecursive) {
		List<File> fileList = new ArrayList<File>();
		
		try {
			File dir = new File(directory);
			if(!dir.exists())	{
				log.info(directory + " - not exists"); 
			}
			loadFileList(dir, fileList, bRecursive);
			
		} catch(Exception e) {
			log.error("[" + directory + "] get file list - fail", e);
			return null;	
		}
		
		return fileList;
	}
	
	static void loadFileList(File dir, List<File> fileList, boolean bRecursive) {
		
		if(dir == null)	return;
		
		try {
			File fileNDirList[] = dir.listFiles();
			if(fileNDirList == null)	return;
			for(File file : fileNDirList) {
				if( file.isDirectory() ) {
					if(bRecursive) {
						loadFileList(file, fileList, bRecursive);
					}
				} else {
					fileList.add(file);
				}
			}
			
		} catch(Exception e) {
			log.error("[" + dir.getPath() + "] load file list - fail", e);
			return;
		}
	}
	
/*	public static String getIntegerSubstring(String src, int beginIndex, int endIndex) {
		
		String substr = "";
		if(src.length() >= endIndex) {
			char c = '0';
			while(c == '0')	{
				substr = src.substring(beginIndex++, endIndex);
				c = substr.charAt(0);	
			}
		}
		return substr;
	}
	
	public static String getDateDirectory(String date) {
		String dateDir = CiFileUtil.getIntegerSubstring(date, 0, 4);			//year
		dateDir = dateDir + "/" + CiFileUtil.getIntegerSubstring(date, 4, 6);	//month
		dateDir = dateDir + "/" + CiFileUtil.getIntegerSubstring(date, 6, 8);	//date
		dateDir = dateDir + "/" + CiFileUtil.getIntegerSubstring(date, 8, 10);	//hour
		//dateDir = dateDir + "/" + CiFileUtil.getIntegerSubstring(date, 10, 12);	//min
		//dateDir = dateDir + "/" + CiFileUtil.getIntegerSubstring(date, 12, 14);	//sec
		return dateDir;
	}
	*/
	
	public static int getIntegerSubstring(String src, int beginIndex, int endIndex) {
		
		int result = -1;
		
		String substr = "";
		try {
			if(src.length() >= endIndex) {
				substr = src.substring(beginIndex, endIndex);
				result = Integer.parseInt(substr);
			}
		} catch (NumberFormatException e) {
			log.error( "[" + src + ":" + beginIndex + "~" + endIndex + "] " + substr + " is NOT Number Format");
			throw e;
		}
		return result;
	}
	
	public static List<String> getTimeDirectoryList(String baseDir,String startTime, String endTime) {
		
		List<String> dirList = new ArrayList<String>();
		
		try {
			int iStartTime[] = {-1,-1,-1,-1, -1};
			iStartTime[0] = CiFileUtil.getIntegerSubstring(startTime, 0, 4);	//year
			iStartTime[1] = CiFileUtil.getIntegerSubstring(startTime, 4, 6);	//month
			iStartTime[2] = CiFileUtil.getIntegerSubstring(startTime, 6, 8);	//date
			iStartTime[3] = CiFileUtil.getIntegerSubstring(startTime, 8, 10);	//hour
			iStartTime[4] = CiFileUtil.getIntegerSubstring(startTime, 10, 12);	//min
			
			int iEndTime[] = {-1,-1,-1,-1,-1};
			iEndTime[0] = CiFileUtil.getIntegerSubstring(endTime, 0, 4);
			iEndTime[1] = CiFileUtil.getIntegerSubstring(endTime, 4, 6);
			iEndTime[2] = CiFileUtil.getIntegerSubstring(endTime, 6, 8);
			iEndTime[3] = CiFileUtil.getIntegerSubstring(endTime, 8, 10);
			iEndTime[4] = CiFileUtil.getIntegerSubstring(endTime, 10, 12);
			
			int step;
			for(step = 0; step < 5; step++) {
				if(iStartTime[step] == -1)	break;				
			}
			
			Calendar startCal = Calendar.getInstance();
			
			Calendar endCal = Calendar.getInstance();
			endCal.set(iEndTime[0], iEndTime[1]-1, iEndTime[2], iEndTime[3], iEndTime[4]);
			
			int unitFieldOfCalendar = -1;
			switch(--step) {
			case 0 : unitFieldOfCalendar = Calendar.YEAR;
				startCal.set(iStartTime[0],0);
				endCal.set(iEndTime[0],0);
				break;
			case 1 : unitFieldOfCalendar = Calendar.MONTH;
				startCal.set(iStartTime[0], iStartTime[1]-1);
				endCal.set(iEndTime[0], iEndTime[1]-1);
				break;
			case 2 : unitFieldOfCalendar = Calendar.DATE;
				startCal.set(iStartTime[0], iStartTime[1]-1, iStartTime[2]);
				endCal.set(iEndTime[0], iEndTime[1]-1, iEndTime[2]);
				break;
			case 3 : unitFieldOfCalendar = Calendar.HOUR_OF_DAY;
				startCal.set(iStartTime[0], iStartTime[1]-1, iStartTime[2], iStartTime[3],0);
				endCal.set(iEndTime[0], iEndTime[1]-1, iEndTime[2], iEndTime[3], 0);
				break;
			case 4 : unitFieldOfCalendar = Calendar.MINUTE;
				startCal.set(iStartTime[0], iStartTime[1]-1, iStartTime[2], iStartTime[3], iStartTime[4]);
				endCal.set(iEndTime[0], iEndTime[1]-1, iEndTime[2], iEndTime[3], iEndTime[4]);
				break;
			}
			
			if(unitFieldOfCalendar != -1 && iEndTime[step] != -1) {
				for(Calendar pos = startCal; pos.compareTo(endCal) < 0; pos.add(unitFieldOfCalendar, 1) )
					dirList.add(getDateDir(baseDir, startCal, unitFieldOfCalendar));
			}
			
		} catch (NumberFormatException e) {
			return null;
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
		
		
		return dirList;
	}
	
	public static String createDirectoryWithName(String directoryName) {
		String directory = makeDirectoryName(directoryName);
		if(!directory.isEmpty())
			createDirectory(directory);
		return directory;
	}
	
	public static boolean createDirectory(String directory) {
		if(directory.isEmpty())	return true;
		
		try {
			FileUtils.forceMkdir(new File(directory));
			
			return true;
		} catch (Exception e) {
			log.error("[" + directory + "] create directory fail", e);
			return false;
		}
	}
	
	public static String createCurrentDirectory(String parentDir, int fieldNumberOfCalendar) {
		String currentDir = getCurrentDir(parentDir, fieldNumberOfCalendar);
		boolean bOK = createDirectory(currentDir);
		if(bOK)	return currentDir;
		else	return "";
	}
	
	/*public static boolean writeFileLog(String fileLogName, String logContent, boolean append) {
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try{
			fileWriter = new FileWriter(new File(fileLogName), append);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(logContent.getBytes("UTF-8").toString());
			bufferedWriter.newLine();
			return true;
		}catch(Exception e){
			log.error("[file:" + fileLogName + ", log:" + logContent + ", append:" + append + "] write file log - fail", e);
			return false;
		}finally{
			try{
				bufferedWriter.close();
				fileWriter.close();
			} catch(Exception e){}
		}
	}*/
	public static boolean writeFileLog(String fileLogName, String logContent, boolean append) {
		
		FileOutputStream fileWriter = null;
		OutputStreamWriter outputWriter = null;
		try{
			fileWriter = new FileOutputStream(new File(fileLogName), append);
			outputWriter = new OutputStreamWriter(fileWriter);
			outputWriter.write(logContent + "\n");
			return true;
		}catch(Exception e){
			log.error("[file:" + fileLogName + ", log:" + logContent + ", append:" + append + "] write file log - fail", e);
			return false;
		}finally{
			try{
				outputWriter.close();	outputWriter = null;
				fileWriter.close();		fileWriter = null;
			} catch(Exception e){}
		}
	}
	
	public static void appendFileLog(String fileLogName, String logContent) {
		writeFileLog(fileLogName, logContent, true);
	}
	
	public static String getCurrentDayFileName(String prefix, String extension) {
		return prefix + getCurrentTimeFileName(Calendar.DATE) + "." + extension;
	}
	public static String getCurrentMilliSecFileName(String postfix, String extension) {
		return getCurrentTimeFileName(Calendar.MILLISECOND) + postfix + "." + extension;
	}
	
	public static String convertTimeValueToString(int value) {
		return (value<10)?"0"+Integer.toString(value):Integer.toString(value);
	}
	public static String getCurrentTimeFileName(int unitFieldOfCalendar) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		String fileName = year + convertTimeValueToString(month) + convertTimeValueToString(date);
		
		if(Calendar.MILLISECOND == unitFieldOfCalendar) {
			fileName = fileName + convertTimeValueToString(cal.get(Calendar.HOUR_OF_DAY)) 
								+ convertTimeValueToString(cal.get(Calendar.MINUTE))
								+ convertTimeValueToString(cal.get(Calendar.SECOND))
								+ convertTimeValueToString(cal.get(Calendar.MILLISECOND));
		}
		else if(Calendar.SECOND == unitFieldOfCalendar) {
			fileName = fileName + convertTimeValueToString(cal.get(Calendar.HOUR_OF_DAY)) 
								+ convertTimeValueToString(cal.get(Calendar.MINUTE))
								+ convertTimeValueToString(cal.get(Calendar.SECOND));

		}
		
		return fileName;
	}
	
	public static String getCurrentDir(String parentDir, int unitFieldOfCalendar) {
		if(parentDir.isEmpty())	return "";
		
		Calendar cal = Calendar.getInstance();
		return getDateDir(parentDir, cal, unitFieldOfCalendar);
	}
	
	public static void renameFile(File uploadFile, String backupFolder, String newFileName) throws Exception
	{
		File dest = new File(getReplaceFullPath(backupFolder), newFileName);
		if(!uploadFile.renameTo(dest)){			
			throw new Exception("rename fails: " + uploadFile.getName() + " to " + dest);
		}
		else
		{
			log.info(newFileName + " moved '"+ dest+ "'");
		}
	}
	
	
	public static String getDateDir(String parentDir, Calendar cal, int unitFieldOfCalendar) {
		if(parentDir.isEmpty())	return "";
		
		int pos = parentDir.lastIndexOf("/");
		if( pos != (parentDir.length()-1) )
			parentDir.concat("/");
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		String currentDir = parentDir;
		
		//자주 사용하는 단위를 if문 조건 앞쪽으로 두었음...
		if(Calendar.HOUR_OF_DAY == unitFieldOfCalendar)		//tracking, requestDescription 내용  파일 저장		
			currentDir = parentDir + year + "/" + month + "/" + date + "/" + hour + "/";
		else if(Calendar.DATE == unitFieldOfCalendar)
			currentDir = parentDir + year + "/" + month + "/" + date + "/";
		else if(Calendar.MONTH == unitFieldOfCalendar)	
			currentDir = parentDir + year + "/" + month + "/";
		else if(Calendar.SECOND == unitFieldOfCalendar)
			currentDir = parentDir + year + "/" + month + "/" + date + "/" + hour + "/" + minute + "/" + second + "/";
		else if(Calendar.MINUTE == unitFieldOfCalendar)
			currentDir = parentDir + year + "/" + month + "/" + date + "/" + hour + "/" + minute + "/";
		else if(Calendar.YEAR == unitFieldOfCalendar)
			currentDir = parentDir + year + "/";
		
		return currentDir;
	}
	
	//private
	
	synchronized static void makeFolder(File file)
	{
		//폴더가 만들어 지지 않은 상태에서 해당 메소드를 기다리고 있을수 있기때문에 안에서 한번 더 체크해줌
		if(!file.exists())
		{
			file.mkdirs();
		}
	}
	
	public static String makeDirectoryName(String directoryName) {
		int pos = directoryName.lastIndexOf("/");
		if( pos != (directoryName.length()-1) )
			directoryName = directoryName.concat("/");
		
		return directoryName;
	}
	
	public static void deleteAllFile(String directoryPath) {
		File path = new File(directoryPath);
		
        if(!path.exists()) {
            return;
        }
         
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isFile())                 
                file.delete();            
        }        
    }
	
	public static List<File> getFileList(String path) throws Exception{
		if(path==null || path.isEmpty())
			return null;
		
		File dir = null;
		try {
			dir = openDirectory(path); 
			
			File[] files = dir.listFiles();
			if(files==null || files.length <= 0)
				return null;
			
			List<File> list = Arrays.asList(files);
			Collections.sort(list, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
			
			return list;
		}finally {
			if(dir!=null)
				dir = null;
		}
		
	}
	
	public static List<File> getFileListWithoutFolder(String path) throws Exception{
		if(path==null || path.isEmpty())
			return null;
		
		File dir = null;
		try {
			dir = openDirectory(path); 
			
			File[] files = dir.listFiles();
			if(files==null || files.length <= 0)
				return null;
			
			List<File> list = new ArrayList<File>();
			for (File file : files) {
				if(!file.isDirectory())
					list.add(file);
			}
			
//			List<File> list = Arrays.asList(files);
			Collections.sort(list, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
			
			return list;
		}finally {
			if(dir!=null)
				dir = null;
		}
		
	}

	public static File openDirectory(String path) {

		if(path==null || path.isEmpty())
			return new File("");
		
		File dir = new File(path); 
		if(dir.isDirectory()==false && dir.isFile()==false){
			dir.mkdirs();
		}
		
		return dir;
	}
	
	public static boolean moveFileToDirectory(String sourceDir,	String destDir, String fileName) throws Exception{
		
		return moveFileToDirectory(sourceDir, destDir, fileName, true);
	}

	public static boolean moveFileToDirectory(String strSrcDir,	String strDestDir, String fileName, boolean createDestDir) throws Exception{
		 
		if(strSrcDir==null || strSrcDir.isEmpty() || strDestDir==null || strDestDir.isEmpty() ||fileName==null || fileName.isEmpty() ){
		     return false;
		}
		
		 File destFile = new File(strDestDir, fileName);
	     if(destFile.exists()) {
	          destFile.delete();
	      }
	     
	     FileUtils.moveFileToDirectory(
	    		 new File(strSrcDir, fileName)
	    		 , new File(strDestDir)
	    		 , createDestDir
	    		 );
		
		return true;
	}

	public static boolean checkDirectory(String path, boolean force) {

		if(path==null || path.isEmpty())
			return false;
		
		File dir = new File(path); 
		
		try{
			
			boolean bDir = dir.isDirectory();
			if(bDir==false && dir.isFile()==false && force==true){
				dir.mkdirs();
				return false;
			}
			
			return bDir;
			
		} finally{
			if(dir!=null)
				dir=null;
		}
	}

	public static boolean renameFile(String strSrcDir, String fileName,
			String rename) throws Exception{
		
		if(strSrcDir==null || strSrcDir.isEmpty())	return false;
		if(rename==null || rename.isEmpty())	return false;
		if(fileName==null || fileName.isEmpty())	return false;

		 File destFile = new File(strSrcDir, rename);
	     if(destFile.exists()) {
	          destFile.delete();
	      }
	     
		FileUtils.moveFile(
				new File(strSrcDir, fileName)
				, destFile
				);
		
		return true;
	}

	public static boolean copyFileToDirectory(String strSrcDir, String strDestDir,
			String fileName) throws Exception{
		
		if(strSrcDir==null || strSrcDir.isEmpty())	return false;
		if(strDestDir==null || strDestDir.isEmpty())	return false;
		if(fileName==null || fileName.isEmpty())	return false;
		
		 File destFile = new File(strDestDir, fileName);
	     if(destFile.exists()) {
	          destFile.delete();
	      }
			
	     File destDir = openDirectory(strDestDir);
	     
	     FileUtils.copyFileToDirectory(
				new File(strSrcDir, fileName)
				, destDir
				, true
				);
		
		return true;
		
	}
	
	public static boolean copyFile(File srcFile, String strDestDir,
			String fileName) throws Exception{
		
		if(srcFile==null)	return false;
		if(strDestDir==null || strDestDir.isEmpty())	return false;
		if(fileName==null || fileName.isEmpty())	return false;
		
		File destFile = new File(strDestDir, fileName);
		if(destFile.exists()) {
			destFile.delete();
		}
			
		openDirectory(strDestDir);
		 
		FileUtils.copyFile(srcFile, destFile);
		
		return true;	
	}

	public static String getUploaded_file_name(String file_name,
			String content_type, String tailFix) {
		
			String baseName = FilenameUtils.getBaseName(file_name);
			
		if(tailFix!=null && tailFix.isEmpty()==false)
			return baseName + "_" +content_type + tailFix;
		
		return baseName + "_" +content_type + FilenameUtils.getExtension(file_name);
	}


	public static boolean deleteFile(String filePath, String fileName) {
		
		if(filePath==null || filePath.isEmpty())	return false;
		if(fileName==null || fileName.isEmpty())	return false;
		
		 File file = new File(filePath, fileName);
	     if(file.exists() && file.isFile()) {
		     return FileUtils.deleteQuietly(file);
	      }
			
	     return false;
	}
	
	public static boolean deleteFile(File file) {
		
		if(file==null)	return false;
		
		if(file.exists() && file.isFile()) {
			return FileUtils.deleteQuietly(file);
		}
		
		return false;
	}
	
}
