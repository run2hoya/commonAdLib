package com.castis.adlib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Date;
@Slf4j
public class HttpConnectorUtil {
	

	final public static String HTTP_GET_METHOD	= "GET";
	final public static String HTTP_POST_METHOD	= "POST";
	final public static String HTTP_PUT_METHOD	= "PUT";
	final public static String HTTP_DELETE_METHOD	= "DELETE";
	final public static String HTTP_POST_BODY_X_WWW_FORM_URLENCODE_CONTENT_TYPE	= "X-WWW-FORM-URLENCODE";
	
	public static String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader( new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				} 
			} finally {
					is.close(); 
			}
			return writer.toString(); 
		} else {
			return ""; 
		} 
	}

	public static Object getObjFromJson(InputStream iStream, Class<?> outputClass)
	{	
		JsonObject jsonObject = null;
		Object resultObject = null;
		if(iStream != null) {				
			GsonBuilder gb = new GsonBuilder().registerTypeAdapter(Date.class, new WebServiceDateDeserializer());
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(iStream, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage(),e);
			}

			jsonObject = (JsonObject) new JsonParser().parse(reader);
//			resultObject = gson.fromJson(reader, outputClass);
			resultObject = gson.fromJson(jsonObject.toString(), outputClass);
		}
		
		return resultObject;
	}
	public static Object getObjFromJson(InputStream iStream, Type typeOfT)
    {   
        Object resultObject = null;
        if(iStream != null) {               
            GsonBuilder gb = new GsonBuilder().registerTypeAdapter(Date.class, new WebServiceDateDeserializer());
            gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Gson gson = gb.create();
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(iStream, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(),e);
            }
            resultObject = gson.fromJson(reader, typeOfT);
        }
        
        return resultObject;
    }
	public static Object getObjFromJsonObject(InputStream iStream, Class<?> outputClass)
	{   
		Object resultObject = null;
		if(iStream != null) {               
			GsonBuilder gb = new GsonBuilder();
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(iStream, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage(),e);
			}
			resultObject = gson.fromJson(reader, outputClass);
		}
		
		return resultObject;
	}
	public static Object getObjFromKeyFromJson(InputStream iStream, Class<?> outputClass, String key)
	{	
		JsonObject jsonObject = null;
		Object resultObject = null;
		if(iStream != null) {				
			GsonBuilder gb = new GsonBuilder().registerTypeAdapter(Date.class, new WebServiceDateDeserializer());
			
			gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Gson gson = gb.create();
			InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(iStream, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage(),e);
			}
			jsonObject = (JsonObject) new JsonParser().parse(reader);
			resultObject = jsonObject.get(key);
			if(resultObject!=null)
				resultObject = gson.fromJson(resultObject.toString(), outputClass);
		}
		
		return resultObject;
	}
	public static Object getObjFromKeyFromJson(InputStream iStream, Type typeOfT, String key)
    {   
        JsonObject jsonObject = null;
        Object resultObject = null;
        if(iStream != null) {               
            GsonBuilder gb = new GsonBuilder()
            .registerTypeAdapter(Date.class, new WebServiceDateDeserializer())
            ;
            gb = gb.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Gson gson = gb.create();
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(iStream, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(),e);
            }
            jsonObject = (JsonObject) new JsonParser().parse(reader);
            resultObject = jsonObject.get(key);
            resultObject = gson.fromJson(resultObject.toString(), typeOfT);
        }
        
        return resultObject;
    }
	
	public static InputStream getResponse(URL url) throws Exception {
		return getResponse(url, HTTP_GET_METHOD, null, -1);	//default timeout
	}
	
	public static InputStream getResponse(URL url, int timeout) throws Exception {
		return getResponse(url, HTTP_GET_METHOD, null, timeout);
	}

	public static InputStream getResponse(URL url, String requestBody) throws Exception {
		return getResponse(url, HTTP_POST_METHOD, requestBody, -1);	//default timeout
	}
	
	public static InputStream getResponse(URL url, String requestBody, int timeout) throws Exception {
		return getResponse(url, HTTP_POST_METHOD, requestBody, timeout);
	}

	public static void disableSslVerification(){
		try
		{
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType){
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType){
				}
			}
			};

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session){
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

	public static InputStream getResponseHttps(URL url, String requestMethod, String requestBody, int timeout) throws Exception {


		disableSslVerification();

		HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
		urlConn.setRequestMethod( requestMethod );
		urlConn.setDoInput( true );
		urlConn.setDoOutput( true );
		urlConn.setUseCaches( false );

		if(timeout > 0)
		{
			urlConn.setConnectTimeout(timeout);		//tomcat서버에 접속할때까지의 timeout
			urlConn.setReadTimeout(timeout);			//tomcat에서 response 받을때까지의 timeout
		}


		// Send Get Method output.
		if((requestMethod.equals(HTTP_POST_METHOD) || requestMethod.equals(HTTP_PUT_METHOD)) && requestBody != null)
		{
			urlConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			writer.write(requestBody);
			writer.close();
			wr.close();
		}

		if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
			return urlConn.getInputStream();
		} else {
			log.error(String.format("External System(%s) tomcat error : response code[%s], response message[%s]",
					url.getPath(), urlConn.getResponseCode(), urlConn.getResponseMessage()));

			return null;
		}
	}
	public static InputStream getResponse(URL url, String requestMethod, String requestBody, int timeout) throws Exception {
		
		HttpURLConnection	urlConn = null;

		urlConn = (HttpURLConnection)url.openConnection();
		
		urlConn.setRequestMethod( requestMethod );
		urlConn.setDoInput( true );
		urlConn.setDoOutput( true );
		urlConn.setUseCaches( false );
		
		if(timeout > 0)
		{
			urlConn.setConnectTimeout(timeout);		//tomcat서버에 접속할때까지의 timeout
			urlConn.setReadTimeout(timeout);			//tomcat에서 response 받을때까지의 timeout
		}
			

		// Send Get Method output.
		if((requestMethod.equals(HTTP_POST_METHOD) || requestMethod.equals(HTTP_PUT_METHOD)) && requestBody != null)
		{
			urlConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");					
			DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			writer.write(requestBody);
			writer.close();
			wr.close();
		}
	
		if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
			return urlConn.getInputStream();
		} else {
			log.error(String.format("External System(%s) tomcat error : response code[%s], response message[%s]", 
					url.getPath(), urlConn.getResponseCode(), urlConn.getResponseMessage()));
			return null;
		}
	}
	
	public static InputStream getResponse(URL url, String requestMethod, String requestBody, int timeout, String postBodyContentType) {
		
		HttpURLConnection	urlConn = null;
		try {
			urlConn = (HttpURLConnection)url.openConnection();
			
			urlConn.setRequestMethod( requestMethod );
			urlConn.setDoInput( true );
			urlConn.setDoOutput( true );
			urlConn.setUseCaches( false );
			if(timeout > 0)
			{
				urlConn.setConnectTimeout(timeout);		//tomcat서버에 접속할때까지의 timeout
				urlConn.setReadTimeout(timeout);			//tomcat에서 response 받을때까지의 timeout
			}
				
	
			// Send Get Method output.
			if(requestMethod.equals(HTTP_POST_METHOD) && requestBody != null)
			{
				if(HTTP_POST_BODY_X_WWW_FORM_URLENCODE_CONTENT_TYPE.equalsIgnoreCase(postBodyContentType))
					urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
				else
					urlConn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
				DataOutputStream 	cgiInput = new DataOutputStream( urlConn.getOutputStream() );
				
				cgiInput.writeBytes(requestBody);
				cgiInput.flush();
				cgiInput.close();
			}
		
			if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
				return urlConn.getInputStream();
			} else {
				log.error(String.format("External System(%s) tomcat error : response code[%s], response message[%s]", 
						url.getPath(), urlConn.getResponseCode(), urlConn.getResponseMessage()));
								
				return null;
			}
		} catch (Exception e) {
			log.error("getResponse fail(url-" + url + ") ErrorMessage : " + e.getMessage());
			return null;
		}
	}

}
