package com.castis.adlib.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
@Slf4j
public class FileWriteChecker {

	public static boolean canRead(File file) {
		RandomAccessFile ran = null;

		try {
			ran = new RandomAccessFile(file, "rw");
			return true;
		} catch (Exception ex) {
			log.info(file.getName() + " 읽을 수 없는 상태 입니다.");
			return false;
		} finally {
			if(ran != null) try {
				ran.close();
			} catch (IOException ex) {
				return false;
			}
			ran = null;
		}
	}

	public static boolean isSizeChanged(File file, long waitingTimeToWrite) {

		Long beforeFileSize = file.length();

		if(waitingTimeToWrite > 0)
			try{Thread.sleep(waitingTimeToWrite);}catch(Exception e){ return false; };

        Long afterFileSize = file.length();
		if(beforeFileSize.equals(afterFileSize) == false)
		{
			log.info(file.getName() + " file is writing. checking file complete.");
			return true;
		}

		RandomAccessFile ran = null;

        try {
            ran = new RandomAccessFile(file, "rw");
            return false;
        } catch (Exception ex) {
        	log.info(file.getName() + " 읽을 수 없는 상태 입니다.");
        	return true;
        } finally {
            if(ran != null) try {
                ran.close();
            } catch (IOException ex) {
            	return true;
            }
            ran = null;
        }
	}
}
