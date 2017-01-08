package in.co.pujar.sudheer.finance.mf.services;

import in.co.pujar.sudheer.finance.mf.enums.NAVLineType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by sudhe on 09-07-2016.
 */
public interface NAVService {
    /**
     *
     * @param url
     * @param data
     * @return
     */
    boolean fetchTextData(String url,OutputStream data);

    /**
     *
     * @param url
     * @param fileNo
     * @param localLocation
     * @return
     */
    boolean storeTextData(String url, int fileNo, String localLocation);

    /**
     *
     * @param localLocation
     * @return
     */
    String generateNavLocalFilePath(String localLocation);

    /**
     *
     * @param fileNo
     * @return
     */
    String generateNavLocalFileName(int fileNo);

    /**
     *
     * @param localFilePath
     * @param localFileName
     * @return
     */
    Map<Long,String> readLines(String localFilePath, String localFileName);

    /**
     *
     * @param line
     * @param separator
     * @return
     */
    List splitLine (String line,String separator);

    /**
     *
     * @param currentLineIndex
     * @param nextLineIndex
     * @param currentLineSplitSize
     * @param nextLineSplitSize
     * @return
     */
    NAVLineType getNAVLineType(Long currentLineIndex, Long nextLineIndex, int currentLineSplitSize, int nextLineSplitSize);
}
