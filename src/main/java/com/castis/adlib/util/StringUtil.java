package com.castis.adlib.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

 
/**
 * Appl. 전체에 사용 되는 String Util Class.</br>
 * 용도에 따라 변수명을 재정의하거나 추가하여 사용.</br>
 * Static 멤버 변수들로 구성되어 직접 사용이 가능하다.
 */
public class StringUtil {
	public static String integerToStringWithCipher(int num, int cipher) {
		String str = Integer.toString(num);
		
		while(cipher > str.length()) {
			str = "0" + str;
		}
		
		return str;
	}
	
	public static String stringToStringWithCipher(String num, int cipher) {
		while(cipher > num.length()) {
			num = "0" + num;
		}
		
		return num;
	}
	public static String getCurrentTimeString(String pattern) {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String result = formatter.format(date);
		return result;
	}

	public static int stringToMinimizedInterger(String str, int minimum){
		int result = minimum;
		try {
	    	result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return minimum;
		}
    	
    	if(result < minimum)
    		result = minimum;
    	return result;
    }
	
	/**
     * 문자열을 IntegerList 로 변환한다.
     * @param list 대상  문자열
     * @return IntegerList
     */
    public static int[] stringListToIntegerList(String[] list){
    	if(list == null || list.length == 0)
    		return null;
    	
    	int[] result = new int[list.length];
    	
    	for(int i = 0; i < list.length; i++) {
    		if(ableToConvertInteger(list[i]))
    			result[i] = Integer.parseInt(list[i]);
    		else
    			return null;
    	}        
      
        return result;
    }
    
	 /**
     * 문자열을 대상 토큰으로 arraySize 만큼 자른다.
     * @param str 대상 문자열
     * @param str 토큰
     * @param str 자르는 개수
     * @return 잘린 문자열
     */
    public static String[] tokenizer(String str, String token, int arraySize){
    	if(str == null || str.equals(""))
    		return null;
    	
        String[] result = new String[arraySize];
        StringTokenizer st = new StringTokenizer(str, token);
        
		for(int i = 0; st.hasMoreElements(); i++) {
			if(i == arraySize)
				return null;
			result[i] = st.nextToken();
		}
      
        return result;
    }

    public static boolean ableToConvertInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
    
    public static boolean ableToConvertToLong(String str) {
		try {
			Long.parseLong(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
    
    /**
     * 널문자를 ""로 변환
     * @param str 변환대상 문자열
     * @return 변환된 문자열
     */
    public static String nullToBlank(String str){
        String result = str;
        
        if(result == null){
            result = "";
        }
        return result;
    }
    
    public static String nullToDash(String str){
        String result = str;
        
        if(result == null){
            result = "-";
        }
        return result;
    }
    
    public static String nullToBlank(Integer inteter){
        String result = "";
        
        if(inteter != null){
            result = inteter.toString();
        }		
        return result;
    }
    
    /**
     * 널문자이면 해당 문자열로 변환
     * @param str 문자열
     * @param conv 변환문자열
     * @return 변환문자열
     */
	public static String nullToString(String str, String conv){
        String result = str;
        
        if(result == null || result.equals("")){
            result = conv;
        }
        return result;
	}
	
	/**
     * 널문자이면 해당  정수형으로 변환
     * @param str 문자열
     * @param conv 변환정수형
     * @return 변환정수형
     */
	public static int nullToInteger(String str, int conv){
		if(str == null || str.equals(""))
			return conv;
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			
		}
		
		return conv;
	}
	
	public static Integer nullToInteger(String str, Integer conv){
		if(str == null || str.equals(""))
			return conv;
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			
		}
		
		return conv;
	}

	public static Boolean nullToBoolean(String parameter, Boolean conv){
		if(parameter == null || parameter.equals(""))
			return conv;

		try {
			if(Integer.parseInt(parameter) != 0)
				return true;
			else			
				return false;
		} catch (Exception e) {}
		
        return Boolean.parseBoolean(parameter);
	}
	
	
	/**
	 * 문자열을 원하는 크기로 잘라낸다.
	 * @param str 문자열
	 * @param len 자르기시작할 위치
	 * @param attachment 잘라낸이후 붙일 문자 열(예 ...)
	 * @return 잘라낸 문자열
	 */	
	public static String sliceString(String str, int len, String attachment) {

		if(str == null || str.length() < 1) return "";

		if(str.length() > len) {
			return str.substring(0,len)+attachment;
		} 
		return str;
	}

	/**
	 * 문자열을 원하는 크기(byte 단위)로 잘라낸다.
	 * String str :문자열
	 * int len : 자르기시작할 위치
	 * int attachment : 잘라낸이후 붙일 문자 열(예 ...)
	 */
	public static String trimString(String str, int len, String attachment) {
		StringBuffer temp = new StringBuffer();
		temp.delete(0,temp.length());
		int j,k;
		if(str == null || str.length() < 1) return "";

		if(str.getBytes().length > len+1) {
			for(j=0, k=0;j<len;j++,k++) {
				if(str.substring(j,j+1).getBytes().length == 1) {
					temp.append(str.substring(j,j+1));
				} else if(str.substring(j,j+1).getBytes().length == 2) {
					temp.append(str.substring(j,j+1));
					k++;
				} else if(str.substring(j,j+1).getBytes().length == 3) {
					temp.append(str.substring(j,j+1));
					k++;
					k++;
				}
				if(k >len) break;
			}
			str = temp.append(attachment).toString();	
		}

		//null처리
		temp = null;
		return str;
	}

	/**
	 * 2byte 문자의 갯수를 가져온다.
	 * String str :문자열
	 * int len : 자르기시작할 위치
	 * int attachment : 잘라낸이후 붙일 문자 열(예 ...)
	 */
	public static int get2ByteStringCount(String str) {
		int j,k;
		if(str == null || str.length() < 1) return 0;

		for(j=0, k=0;j<str.length();j++,k++) {
			if(str.substring(j,j+1).getBytes().length == 1) {
				
			} else if(str.substring(j,j+1).getBytes().length == 2) {
				k++;
			}
		}
		return k;
	}

	
	/**
	 * 문자열을 원하는 크기로 잘라내어 String 배열에 담아준다.
	 * String str :문자열
	 * int arraySize : 배열사이즈
	 * int splitSize : 잘라낼 사이즈
	 */	
	public static String[] trimString(String str, int arraySize, int splitSize) {
		String[] result = new String[arraySize];
		
		if(str == null || str.length() < 1) return result;
		int j=0,k=0;
		
		for(int i = 0; i< result.length; i++) {
			k = (i+1)*splitSize;
			if(str.length() > k && str.length() > j) {
				result[i] = str.substring(j, k);
			} else if(str.length() < k && str.length() > j){
				result[i] = str.substring(j);
			} else {
				result[i] = "";
			}
			j = k;
		}
		
		return result;
	}

	
	
	/**
	 * 문자열을 원하는 크기부터 원하는 크기로 (byte 단위)로 잘라낸다.
	 * String str : 문자열
	 * int start : 자르기시작할 위치
	 * int end : 자르기시작할 위치
	 * int attachment : 잘라낸이후 붙일 문자 열(예 ...)
	 */
	public static String trimString(String str, int start, int end, String attachment) {
		StringBuffer temp = new StringBuffer();
		temp.delete(0,temp.length());
		int j,k;
		if(str == null || str.length() < 1) return "";
		
		if(str.getBytes().length > end+1) {
			for(j=0, k=0;k<end;j++,k++) {
				if(str.substring(j,j+1).getBytes().length ==1) {
					if(k > start)
					temp.append(str.substring(j,j+1));
				} else if(str.substring(j,j+1).getBytes().length ==2) {
					if(k > start)
					temp.append(str.substring(j,j+1));
					k++;
				} else if(str.substring(j,j+1).getBytes().length ==3) {
					if(k > start)
					temp.append(str.substring(j,j+1));
					k++;
					k++;
				}
				if(k > end) break;
				
			}
			str = temp.append(attachment).toString();	
		
		} 
		else if(str.getBytes().length > start+1) {
			for(j=0, k=0;k< str.getBytes().length ;j++,k++) {
				if(str.substring(j,j+1).getBytes().length == 1) {
					if(k > start)
					temp.append(str.substring(j,j+1));
				} else if(str.substring(j,j+1).getBytes().length == 2) {
					if(k > start)
					temp.append(str.substring(j,j+1));
					k++;
				} else if(str.substring(j,j+1).getBytes().length == 3) {
					if(k > start)
					temp.append(str.substring(j,j+1));
					k++;
					k++;
				}
				if(k > end) break;
			}
			str = temp.append(attachment).toString();
			
		} else {
			str = "";
		}
		//null처리
		temp = null;
		return str;
	}
	
	
	
	/**
	 * 넘겨진 배열형태의 데이터를 주어진문자로 연결한 스트링 리턴
	 * @param sep 구분자
	 * @param obj  배열형태의 데이터
	 * @return String 
	 */
	public static String implode(String sep, String[] obj){
		StringBuffer temp = new StringBuffer(100);
		int i = 0;
		while(i < obj.length){
			if(temp.length()>0)	temp.append(sep);
			temp.append(obj[i]);
			i++;
		}
		return temp.toString();
	}
	
	/**
	 * 넘겨진 배열형태의 데이터를 주어진문자로 연결한 스트링 리턴
	 * @param sep 구분자
	 * @param obj 배열형태의 데이터
	 * @return String 
	 */
	public static String implode(String sep, Collection<?> obj){
		StringBuffer temp = new StringBuffer(100);
		Iterator<?> itr = obj.iterator();
		while(itr.hasNext()) {
			String objstr = (String)itr.next();
			if(temp.length()>0)	temp.append(sep);
			temp.append(objstr);
		}
		return temp.toString();
	}
	/**
	 * 해당문자열을 주어진문자를 기준으로 나누어 배열로 리턴
	 * @param sep 구분자 
	 * @param str 해당문자열
	 * @return Collection 
	 */
	public static Collection<String> explode(String sep,String str){
		Collection<String> retCol = null;
		if(str != null){
			
			StringTokenizer strtoken = new StringTokenizer((String)str,(String)sep);
			if(strtoken.countTokens() > 0) {
				retCol = new ArrayList<String>();
				while (strtoken.hasMoreTokens()) {
					retCol.add(strtoken.nextToken());
				}
			}
		}
		return retCol;
	}
	
	/**
	 * Input String을 주어진문자를 기준으로 나누어 배열로 리턴
	 * @param sep 구분자
	 * @param str Input String
	 * @return 변환 String[] 
	 */
	public static String [] split(String str, String sep){
		String [] tempString = null;
		if(str != null){
			StringTokenizer strToken = new StringTokenizer(str, sep);
			tempString = new String[strToken.countTokens()];
			for(int i =0; i < tempString.length; i++)
				tempString[i] = strToken.nextToken();
		}
		return tempString;
	}
	
	/**
	 * Input String을 주어진문자를 기준으로 나누어 배열로 리턴
	 * @param regex 구분자
	 * @param str Input String
	 * @param limit 반환 사이즈
	 * @return 변환 String[] 
	 */
	public static String [] split(String str, String regex, int limit){
		String [] tempString = null;
		if(str != null){
			tempString = str.split(regex, limit);
		}
		return tempString;
	}

	/**
	 * 문자열에서 해당문자를 원하는 문자로 변경한다.
	 * @param org :	문자열
	 * @param var :	변환할 문자열
	 * @param tgt :	변환될 문자열
	 * @return : 변환된 문자열
	 */
	public static String replaceString(String org, String var, String tgt){
		StringBuffer temp = new StringBuffer(1024);
		int	end	= 0;
		int	begin =	0;

		while (true) {
			end	= org.indexOf(var, begin);
			if (end	== -1) {
				end	= org.length();
				temp.append(org.substring(begin,end));
				break;
			}
			temp.append(org.substring(begin,end)).append(tgt);
			begin =	end	+ var.length();
		}
		return temp.toString();
	}

	/**
	 * ASKII 문자셋을 KSC 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String ascii2ksc(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			// JS String Object를 전달할 때 Unicode로 전달함.
			// 그러나 문자열을 8859_1로 해석하여 Unicode로 변환하므로 
			// java에서 String으로 처리할 때는 그 반대 과정을 거쳐 char set를
			// 변환해야 한다. 즉, 8859_1 byte array로 읽어 KSC5601로 변환한다.
			byte[] b = str.getBytes("8859_1");
			rtnStr = new String(b, "KSC5601");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}
	
	/**
	 * KSC 문자셋을ASKII 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String ksc2ascii(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			byte[] b = str.getBytes("KSC5601");
			rtnStr = new String(b, "8859_1");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}
	public static boolean isUTF8(String str, String encoding) {
		try {
			if(encoding==null || "".equals(encoding)){
				byte[] bytes = str.getBytes();
				return isUTF8(bytes,0,bytes.length);
			}else{
				byte[] bytes = str.getBytes(encoding);
				return isUTF8(bytes,0,bytes.length);
			}
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		}
		
		return false;
	}	   
	public static boolean isUTF8(byte[] buf, int offset, int length) {

	       boolean yesItIs = false;
	       for (int i=offset; i<offset+length; i++) {
	          if ((buf[i] & 0xC0) == 0xC0) { // 11xxxxxx 패턴 인지 체크 
	             int nBytes;
	             for (nBytes=2; nBytes<8; nBytes++) {
	                int mask = 1 << (7-nBytes);
	                if ((buf[i] & mask) == 0) break;
	             }
	                                  //CJK영역이나 아스키 영역의 경우 110xxxxx 10xxxxxx 패턴으로 올수 없다.
	             if(nBytes==2) return false;
	             
	             // Check that the following bytes begin with 0b10xxxxxx
	             for (int j=1; j<nBytes; j++) {
	                if (i+j >= length || (buf[i+j] & 0xC0) != 0x80) return false;
	             }
             
             if(nBytes==3){
             	// 유니코드 형태로 역치환 해서 0x0800 ~ 0xFFFF 사이의 영역인지 체크한다. 
                 char c = (char) (((buf[i] & 0x0f) << 12) + ((buf[i+1] & 0x3F) << 6) + (buf[i+2] & 0x3F));
                 if(!(c >= 0x0800 && c <= 0xFFFF)){
                     return false;
                 }	                	
             }
	                
	             yesItIs = true;
	          }
	       }
	       return yesItIs;
	 }


	/**
	 * ASKII 문자셋을 utf-8 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String ascii2utf8(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			byte[] b = str.getBytes("8859_1");
			rtnStr = new String(b, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}
	
	/**
	 * utf-8 문자셋을ASKII 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String utf82ascii(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			byte[] b = str.getBytes("UTF-8");
			rtnStr = new String(b, "8859_1");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}

	/**
	 * ASKII 문자셋을 EUC_KR 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String ascii2euckr(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			// Communicator는 JS String Object를 전달할 때 Unicode로 전달함.
			// 그러나 문자열을 8859_1로 해석하여 Unicode로 변환하므로 
			// java에서 String으로 처리할 때는 그 반대 과정을 거쳐 char set를
			// 변환해야 한다. 즉, 8859_1 byte array로 읽어 KSC5601로 변환한다.
			byte[] b = str.getBytes("8859_1");
			rtnStr = new String(b, "EUC_KR");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}
	
	/**
	 * EUC_KR 문자셋을 ASKII 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String euckr2ascii(String str) {
		String rtnStr = null;
		if(str==null) return ""; 
		try {
			byte[] b = str.getBytes("EUC_KR");
			rtnStr = new String(b, "8859_1");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}	

	/**
	 * ASKII 문자셋을 EUC_JP 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String ascii2eucjp(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			// Communicator는 JS String Object를 전달할 때 Unicode로 전달함.
			// 그러나 문자열을 8859_1로 해석하여 Unicode로 변환하므로 
			// java에서 String으로 처리할 때는 그 반대 과정을 거쳐 char set를
			// 변환해야 한다. 즉, 8859_1 byte array로 읽어 KSC5601로 변환한다.
			byte[] b = str.getBytes("8859_1");
			rtnStr = new String(b, "EUC_JP");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}
	
	/**
	 * EUC_JP 문자셋을 ASKII 문자셋으로 변환
	 * @param str 원본 스트링
	 * @return 변환 스트링
	 */
	public static String eucjp2ascii(String str) {
		String rtnStr = null;
		if(str==null) return "";
		try {
			byte[] b = str.getBytes("EUC_JP");
			rtnStr = new String(b, "8859_1");
		} catch(UnsupportedEncodingException e) {
			rtnStr = str;
		}
		
		return rtnStr;
	}	
	
	/**
	 * Input String이 한글인지를 판단한다.
	 * @param str Input String
	 * @return true 한글,</br>
	 *         false 한글이 아님
	 */
	public static boolean isHangul(String str) {
		char ch;
		for(int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if(ch == '\r' || ch == '\n' || ch == '\t') {
				continue;
			}

			if((int)ch < 0x20 || (int)ch > 0x7f) {
				return true;
			}
		}
		return false;
	}
	
	public static String trimWhitespace(String str){

        if(isNull(str))
        	return null;
        
		int start = 0;
        int last = str.length() - 1;
        int end = last;
        
        while (start <= end && isWhitespace(str.charAt(start))) {
            start++;
        }
        while (end >= start && isWhitespace(str.charAt(end))) {
            end--;
        }
        if (start == 0 && end == last) {
            return str;
        }
        return str.substring(start, end + 1);

	}
	
	public static boolean isWhitespace(char c) {
        switch(c) {
        case 0x00:
        case ' ':
        case '\t':
        case '\f':
        case '\n':
        case '\r':
        case 11: /* VT */
            return true;
        default:
            return false;
        }
    }

	public static boolean isNull(String str) {
		if(str == null || str.equals(""))
			return true;
		return false;
	}
	
	public static boolean isNotNull(String str) {
		return (str == null || str.equals("")) ? false : true; 
	}

	public static boolean isNull(Object str) {
		if(str == null)
			return true;
		return false;
	}

	public static boolean isMinRange(String str, int min) {
		try {
			if(Integer.parseInt(str) < min)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}

	public static boolean isMinLong(String str, long min) {
		try {
			if(Long.parseLong(str) < min)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}

	public static boolean isRange(String str, int min, int max) {
		try {
			if( (Integer.parseInt(str) < min) || (max < Integer.parseInt(str)))
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean isBoolean(String str){
		if(isNull(str))
			return false;
		
		if("true".equalsIgnoreCase(str)
				|| "false".equalsIgnoreCase(str)
				|| "1".equals(str)
				|| "0".equals(str))
			return true;
		
		return false;
	}

	
	public static Boolean isInList(String str, String[] paramList){
		if(isNull(str) || paramList==null || paramList.length < 1)
			return false;
		
		for(String tempStr:paramList){
			if(str.equalsIgnoreCase(tempStr))
				return true;
		}
		
		return false;
	}

	public static int nullToInteger(Object object, int conv) {
		if(object==null)
			return conv;
		return nullToInteger((String)object, conv);
	}
	
	public static String nullToString(Object object, String conv){
		if(object==null)
			return conv;
		
        return nullToString((String)object, conv);
	}
	
	public static String[] nullToStringList(String[] list, String[] conv){
		if(StringUtil.isEmptyList(list))
			return conv;
		
        return list;
	}

	public static Boolean nullToBoolean(Object object, Boolean conv){
		if(object==null)
			return conv;
		
        return nullToBoolean((String)object, conv);
	}
	
	public static String getFullPath(String folder, String fileName) {
		if ( fileName == null )
			return null;
		
		if ( fileName.isEmpty() ) 
			return fileName;
		
		String fileFullPath = ""; 
		if ( folder != null && !folder.isEmpty() ) {
			fileFullPath += folder;
			if ( fileFullPath.endsWith("/") == false )
				fileFullPath += "/";
		}
		fileFullPath += fileName;

		return fileFullPath;
	}

	public static String trim(String str) {
		return str != null ? str.trim() : null; 
	}
	
	public static String nullToStringTrim(String str, String conv) {
        if(str == null || str.equals("")){
            return conv.trim();
        }
        
        return str.trim();
	}

	public static int nullToIntegerTrim(String str, int conv) {
		if(str == null || str.equals(""))
			return conv;
        return Integer.parseInt(str.trim());
	}

	
	public static Boolean isList(String str, String sep, int size){
		if(isNull(str))
			return false;
		
		StringTokenizer strToken = new StringTokenizer(str, sep);
			
		if(size>0)
			return 	(strToken.countTokens()==size)? true : false;
		else
			return (strToken.countTokens() > 0)? true : false;
	}
	
	public static boolean isEmptyList(String[] list) {
		if(list == null || list.length <= 0)
			return true;
		
		for(String element : list) {
			if(!isNull(element))
				return false;
		}
		
		return true;
	}
	
	public static String[] removeNullElementInList(String[] list) {
		if(list == null || list.length <= 0)
			return null;
		
		List<String> newList = new ArrayList<String>();
		
		for(String str : list) {
			if(!isNull(str))
				newList.add(str);
		}
		
		if(newList.isEmpty())
			return null;
		
		list = new String[newList.size()];
		for(int i = 0; i < newList.size(); i++)
			list[i] = newList.get(i);
		
		return list;
	}

	public static int notNullArrayLength(String[] arr) {
		if(arr == null)
			return -1;
		
		int notNullArraylength = 0;
		for(int i = 0; i < arr.length; i++) {
			if(!isNull(arr[i]))
				notNullArraylength++;
		}
		
		return notNullArraylength;
	}

	public static boolean isInList(Object object, String[] paramList) {
		if(isNull(object) || paramList==null || paramList.length < 1)
			return false;
		
		for(String tempStr:paramList){
			if(((String)object).equalsIgnoreCase(tempStr))
				return true;
		}
		
		return false;
	}

	
	public static Boolean isInList(String strArray, String sep, int index, String[] paramList){
		if(isNull(strArray) || isNull(sep) || paramList==null || paramList.length < 1)
			return false;
		
		Collection<String> strCollection = explode(sep, strArray);
		
		if(strCollection==null || strCollection.isEmpty() || strCollection.size() < index)
			return false;

		ArrayList<String> strArrayTemp = (ArrayList<String>) strCollection;
		
		return isInList(strArrayTemp.get(index), paramList);
	}

	public static boolean nullToBoolean_ZeroOne(String parameter, boolean b) {
		if(isNull(parameter))
			return b;
		try {
			int i = Integer.parseInt(parameter);
			if(i != 0)
				return true;
		} catch (Exception e) {}
		
		return false;
	}
	
	public static String convertTargetSubscriberId(String targetId) {
		if(targetId != null && targetId.contains("|")) {
			StringTokenizer strToken = new StringTokenizer(targetId, "|");
			targetId = strToken.nextToken();
		}
		
		return targetId;
	}

	
	public static String getStringFromList(String strArray, String sep, int index, int total){
		if(isNull(strArray) || isNull(sep))
			return null;
		
		Collection<String> strCollection = explode(sep, strArray);
		
		if(strCollection==null || strCollection.size() < index)
			return null;
		
		if(total > -1 && strCollection.size()!=total)
			return null;
		
		if(strCollection!=null && strCollection.size() >= index){

			ArrayList<String> strArrayTemp = (ArrayList<String>) strCollection;
			
			return strArrayTemp.get(index);
		}

		
		return null;
	}

	public static boolean isRange(String parameter, Long min, Long max) {
		try {
			if( (Long.parseLong(parameter) < min) || (max < Long.parseLong(parameter)))
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public static String removeMilliSecond(String time) {
		if(time == null || "".equals(time) || time.length() < 19)
			return "";
		
		return time.substring(0, 19);
	}

	public static Long nullToLong(String str, Long conv) {
		if(str == null || str.equals(""))
			return conv;
        return Long.parseLong(str);
	}
	
	/**
	 * 문자열 배열을 입력 받고 문자열을 리턴한다.
	 * 중복을 허용 하지 않고 문자열과 문자열을 ',' 로 연결한다.
	 * 빈 문자열은 제거한다.
	 * 
	 * @param list : 변환할 문자열
	 * @return : 변환된 문자열
	 */
	public static String listToString(String[] list) {
		Set<String> conv = new HashSet<String>();
		
		if(list != null && list.length > 0) {
			for(String e : list) {
				if(!StringUtil.isNull(e))
					conv.add(e);
			}
		}

		String result = conv.toString().replace(" ", "");
		return result.substring(1, result.length() - 1);
	}

	public static boolean YnToBool(char yn) {
		return (yn == 'Y' || yn == 'y') ? true : false;
	}
	
	public static boolean YnToBool(String yn) {
		return ("Y".equalsIgnoreCase(yn)) ? true : false;
	}
	
	public static String YnFromBool(Boolean yn) {
		if(yn == null || yn == false)
			return "N";
		else
			return "Y";		
	}

	public static String integerToString(Integer num) {
		return num == null ? null : Integer.toString(num);
	}
	
	// URL Encoding 된 파라미터를 디코딩하는 하는 함수.
	// 이미 Decoding 된 파라미터는 입력 값을 그대로 반환함.
	public static String decodeUTF8(String param, String method) {
		String decodedParam = null;

		if(param==null || "".equals(param))
			return param;
		
		if(method.equalsIgnoreCase("GET")){
			try {
				if(StringUtil.isUTF8(param, "ISO-8859-1")){
					decodedParam = new String(param.getBytes("8859_1"), "UTF-8");
				} else {
					decodedParam = param;
				}
			} catch (Exception e) {
				decodedParam = null;
			}
		} else
			return param;
		
		return decodedParam;
		
	}
	public static  String convertEUCKR(String param, String method) {
		String convertParam = null;

		if(param==null || "".equals(param))
			return "";
		
		if(method.equalsIgnoreCase("GET")){

			try {
				if(isUTF8(param, "ISO-8859-1")){
					convertParam = new String(param.getBytes("8859_1"), "UTF-8");
					convertParam = new String(convertParam.getBytes(), "KSC5601");
				}else {
					convertParam = new String(param.getBytes("8859_1"), "KSC5601");
				}
				
			} catch (Exception e) {
				
				convertParam = "";
			}
			
			
		}else{
			try {
				// UTF-8 로 들어온다는 가정이 있음
				convertParam = new String(param.getBytes(), "KSC5601");
					
			} catch (Exception e) {
				
				convertParam = "";
			}
		}
		
		return convertParam;
		
	}
	public static String removeBrackets(List<String> idList) {
		return idList.toString().substring(1, idList.toString().length()-1).replaceAll("\\p{Space}", "");
	}
}

