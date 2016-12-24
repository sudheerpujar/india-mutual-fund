package in.co.pujar.sudheer.finance.mf.services.impl;


import in.co.pujar.sudheer.finance.mf.services.NAVService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;


import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * Created by sudhe on 09-07-2016.
 */


public class NAVServiceImpl implements NAVService {


    CloseableHttpClient httpClient;
    public NAVServiceImpl(CloseableHttpClient httpClient){
        this.httpClient=httpClient;
    }


    /**
     * @param url
     * @param data
     * @return
     */
    private boolean fetchTextDataUsingFile(String url,OutputStream data){
        InputStream inputStream = null;
        File file = new File(url);
        boolean isFetched=false;
        if(file.exists()){
            try {
                inputStream=new FileInputStream(file);
                IOUtils.copyLarge(inputStream,data);
                isFetched=true;
            } catch (IOException e) {
                isFetched=false;
            }
        }
        return isFetched;
    }


    /**
     * @param url
     * @param data
     * @return
     */
    private boolean fetchTextDataUsingHttp(String url,OutputStream data) {
        HttpGet httpGet = new HttpGet(url);
        InputStream inputStream=null;
        boolean isFetched=false;
        try {
            CloseableHttpResponse response =httpClient.execute(httpGet);
            int status=response.getStatusLine().getStatusCode();
            if(status== HttpStatus.SC_OK){
                inputStream =response.getEntity().getContent();
                IOUtils.copyLarge(inputStream,data);
                isFetched=true;
            }
        } catch (IOException e) {
            isFetched=false;
        }finally {
            httpGet.releaseConnection();
            try {
                if(inputStream!=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                isFetched=false;
            }
        }
        return isFetched;
    }


    /**
     * @param url
     * @param data
     * @return
     */
    @Override
    public boolean fetchTextData(String url, OutputStream data) {
        if(url==null || url.length()==0) {
            return false;
        }else if (url.toUpperCase().startsWith("HTTP")){
            return fetchTextDataUsingHttp(url,data);
        } else{
            return fetchTextDataUsingFile(url,data);
        }
    }


    /**
     * @param url
     * @param fileNo
     * @return
     */
    @Override
    public boolean storeTextData(String url,int fileNo,String localLocation) {
        boolean isDataStored=false;
        String storeLocation = generateNavLocalFilePath(localLocation);
        File storeLocationPath=new File(storeLocation);
        if(!storeLocationPath.exists()){
            storeLocationPath.mkdirs();
        }
        String storeLocationFileName = generateNavLocalFileName(fileNo);
        File storeLocationFile = new File(storeLocationPath,storeLocationFileName);
        if(storeLocationFile.exists()){
            storeLocationFile.delete();
        }

        try( FileOutputStream outputStream = new FileOutputStream(storeLocationFile)){

            isDataStored=fetchTextData(url,outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(!isDataStored && storeLocationFile!=null && storeLocationFile.exists()){
                storeLocationFile.delete();
            }
        }
        return isDataStored;
    }

    @Override
    public String generateNavLocalFilePath(String localLocation) {
        StringBuffer localFilePath = new StringBuffer(System.getProperty("user.home").toString());
        localFilePath.append(File.separator).append(localLocation);
        return localFilePath.toString();
    }

    @Override
    public String generateNavLocalFileName(int fileNo) {
        int day= GregorianCalendar.getInstance().get(GregorianCalendar.DATE);
        int month=GregorianCalendar.getInstance().get(GregorianCalendar.MONTH);
        int year=GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
        StringBuffer storeLocationFileName = new StringBuffer() ;
        storeLocationFileName.append(year).append(month).append(day).append(fileNo).append(".txt");
        return storeLocationFileName.toString();
    }

    @Override
    public Map<Long, String> readLines(String localFilePath, String localFileName) {
        Map<Long, String>lines=new HashMap<Long, String>();
        File localFile = new File(localFilePath,localFileName);
        if(localFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(localFile))){
                String line=null;
                Long lineIndex= Long.valueOf(0);
                while((line=reader.readLine())!=null){
                    if(line.trim().length()>0) {
                        lines.put(lineIndex++, line);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }



    @Override
    public List<String> splitLine(String line, String separator) {
        return Arrays.asList(line.split(separator));
    }
}
