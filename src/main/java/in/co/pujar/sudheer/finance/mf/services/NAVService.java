package in.co.pujar.sudheer.finance.mf.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by sudhe on 09-07-2016.
 */
public interface NAVService {
    boolean fetchTextData(String url,OutputStream data);
    boolean storeTextData(String url, int fileNo, String localLocation);
    String generateNavLocalFilePath(String localLocation);
    String generateNavLocalFileName(int fileNo);
    String readFirstLine(String localFilePath,String localFileName);
    List splitLine (String line,String separator);
}
