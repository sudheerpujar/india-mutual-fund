package in.co.pujar.sudheer.finance.mf.services.unit.specs

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import groovy.util.logging.Slf4j
import in.co.pujar.sudheer.finance.mf.bo.Scheme
import in.co.pujar.sudheer.finance.mf.builder.impl.SchemeBuilder
import in.co.pujar.sudheer.finance.mf.builder.impl.SchemeVoBuilder
import in.co.pujar.sudheer.finance.mf.services.NAVService
import in.co.pujar.sudheer.finance.mf.enums.NAVLineType
import in.co.pujar.sudheer.finance.mf.vo.SchemeVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource
import java.text.SimpleDateFormat


/**
 * Created by sudhe on 09-07-2016.
 */
@ContextConfiguration(locations ="classpath:config/india-mf-config.xml")
@Slf4j
class NAVServiceSpec extends Specification {
    def FILE_LOCATION_PROPERTY_KEY = "nav.data.file.location.local"
    @Autowired
    NAVService navService

    @Resource(name="applicationProperty")
    private Properties applicationProperty;


    def setupSpec(){

        log.debug("setupSpec() - Runs once per Specification");
    }

    def setup(){
        log.debug("setup() Runs before every feature method");
    }

    def cleanup(){
        log.debug ("Cleanup method - Runs  after every feature method.");
        def localLocation = applicationProperty.getProperty(FILE_LOCATION_PROPERTY_KEY)
        def localFilePath= navService.generateNavLocalFilePath(localLocation);
        def file = new File(localFilePath);
        if(file!=null && file.exists()){
            file.deleteDir();
        }
    }
    def cleanupSpec(){
        log.debug ("cleanupSpec() - Runs only once per specification");

    }

    @Unroll
    def "Fetch nav text data for a given #url"(){
        log.debug("Fetch Net Asset Value text data")
        given : "For a given #url "

        when : " I fetch the data "
            OutputStream data = new ByteOutputStream();
            boolean fetched=navService.fetchTextData(url,data)
        then : " the data size should not be zero"
            def dataSize=(data.size()!=0);
            data.close()
        and : "return flag should be appropriate"
            dataSize && fetched
        where: "#url "
            url <<[
            this.getClass().getResource( '/data-files/NAV0.txt' ).getPath(),
            this.getClass().getResource( '/data-files/NAV1.txt' ).getPath() ,
            this.getClass().getResource( '/data-files/NAV2.txt' ).getPath() ,
            this.getClass().getResource( '/data-files/NAV3.txt' ).getPath() ]
    }

    @Unroll
    def "Store nav text data of #url into temporary location"(){
        given : "For a given URL '#url' and local location"
        def localLocation = applicationProperty.getProperty(FILE_LOCATION_PROPERTY_KEY)
        when : " I fetch and store the data locally "
        def stored=navService.storeTextData(url,fileNo,localLocation)
        then : "file size should not be zero "
        def localFilePath= navService.generateNavLocalFilePath(localLocation)
        def localFileName= navService.generateNavLocalFileName(fileNo)
        def file = new File(localFilePath,localFileName);
        def fileExists=(file.exists() && file.size()!=0)
        and : "return flag should be appropriate"
        stored && fileExists
        where: " #url #file "
        url | fileNo
        this.getClass().getResource( '/data-files/NAV0.txt' ).getPath()| 0
        this.getClass().getResource( '/data-files/NAV1.txt' ).getPath()| 1
        this.getClass().getResource( '/data-files/NAV2.txt' ).getPath()| 2
        this.getClass().getResource( '/data-files/NAV3.txt' ).getPath()| 3

    }

    @Unroll
    def "Read contents of non-empty line '#lineIndex'"(){
        given : "A file @ local location"
        def localLocation = applicationProperty.getProperty(FILE_LOCATION_PROPERTY_KEY)
        navService.storeTextData(url,fileNo,localLocation)
        when : "I read line #lineIndex of the file #url"
        def localFilePath = navService.generateNavLocalFilePath(localLocation)
        def localFileName = navService.generateNavLocalFileName(fileNo)
        def lines = navService.readLines(localFilePath,localFileName)
        def line =lines.get(Long.valueOf(lineIndex-1))
        and : "I spilt the line #lineIndex with  separator '#separator' "
        def List columns=navService.splitLine(line,separator)
        then : "I get a column list of size #size "
        columns.size()==size
        where : "('#url' #fileNo  '#separator'  #size #lineIndex)"
        url | fileNo| separator|size | lineIndex
        this.getClass().getResource( '/data-files/NAV0.txt' ).getPath()| 0 | ";" | 8 | 1
        this.getClass().getResource( '/data-files/NAV1.txt' ).getPath()| 1 | ";" | 1 | 2
        this.getClass().getResource( '/data-files/NAV2.txt' ).getPath()| 2 | ";" | 1 | 3
        this.getClass().getResource( '/data-files/NAV3.txt' ).getPath()| 3 | ";" | 8 | 4
    }

    @Unroll
    def "Identify Line Type of current '#lineIndex' as '#lineType'" (){
        given : "A file @ local location"
        def localLocation = applicationProperty.getProperty(FILE_LOCATION_PROPERTY_KEY)
        navService.storeTextData(url,fileNo,localLocation)
        when : "I read the consecutive lines of file of #url"
        def localFilePath = navService.generateNavLocalFilePath(localLocation)
        def localFileName = navService.generateNavLocalFileName(fileNo)
        def lines = navService.readLines(localFilePath,localFileName)
        def Long currentLineIndex=Long.valueOf(lineIndex-1)
        def Long nextLineIndex=Long.valueOf(lineIndex)
        def currentLine =lines.get(currentLineIndex)
        def nextLine =lines.get(nextLineIndex)
        and : "I calculate the current line type of line number  '#lineIndex' after splitting the line with '#separator' "
        def int currentLineSplitSize=navService.splitLine(currentLine,separator).size();
        def int nextLineSplitSize=navService.splitLine(nextLine,separator).size();
        def NAVLineType actualLineType=navService.getNAVLineType(currentLineIndex, nextLineIndex, currentLineSplitSize, nextLineSplitSize);
        then : "I get the Line Type  '#lineType'"
        actualLineType == lineType

        where : "('#url' #fileNo  '#separator'  #size  #lineIndex #lineType)"
        url | fileNo| separator|size | lineIndex | lineType
        this.getClass().getResource( '/data-files/NAV0.txt' ).getPath()| 0 | ";" | NAVLineType.HEADER.columnSize | 1 | NAVLineType.HEADER
        this.getClass().getResource( '/data-files/NAV1.txt' ).getPath()| 1 | ";" | NAVLineType.FUND_TYPE.columnSize | 2 | NAVLineType.FUND_TYPE
        this.getClass().getResource( '/data-files/NAV2.txt' ).getPath()| 2 | ";" | NAVLineType.FUND.columnSize | 3 | NAVLineType.FUND
        this.getClass().getResource( '/data-files/NAV3.txt' ).getPath()| 3 | ";" | NAVLineType.SCHEME.columnSize | 4 | NAVLineType.SCHEME
        this.getClass().getResource( '/data-files/NAV0.txt' ).getPath()| 0 | ";" | NAVLineType.EOS.columnSize| 15 | NAVLineType.EOS
        this.getClass().getResource( '/data-files/NAV3.txt' ).getPath()| 3 | ";" | NAVLineType.EOF.columnSize | 31 | NAVLineType.EOF
    }

    @Unroll
    def "Build Scheme"(){
        given : "a scheme values"
        def SchemeVoBuilder schemeVoBuilder=new SchemeVoBuilder().code(code).name(name).gISIN(gISIN).rISIN(rISIN).nav(nav).rPrice(rPrice).sPrice(sPrice).date(date)
        def SchemeVo schemeVo = schemeVoBuilder.build()
        when : "I build a scheme using scheme builder"
        def SchemeBuilder schemeBuilder = new SchemeBuilder()
        schemeBuilder.schemeVo(schemeVo)
        def Scheme scheme = schemeBuilder.build()
        then : "I verify the data from scheme bean with scheme data"
        code == String.valueOf(scheme.code) && gISIN == scheme.gISIN && rISIN == scheme.rISIN && name == scheme.name && (nav == String.valueOf(scheme.nav) || scheme.nav==-1.0) && date == new SimpleDateFormat(SchemeBuilder.SCHEME_DATE_FORMAT).format(scheme.date) && (rPrice == String.valueOf(scheme.rPrice) || scheme.rPrice == -1.0) && (sPrice == String.valueOf(scheme.sPrice) || scheme.sPrice == -1.0)
        where :
        code|gISIN|rISIN|name|nav|rPrice|sPrice|date
        '101913'|'INF955L01682'|'INF955L01690'|'BARODA PIONEER BALANCE FUND - Plan A - Dividend Option'|'17.21'|'17.04'|'17.21'|'08-Jul-2016'
        '101912'|'INF955L01708'|'-'|'BARODA PIONEER BALANCE FUND - Plan A - Growth Option'|'44.78'|'44.33'|'44.78'|'08-Jul-2016'
        '108145'|'INF200K01IJ0'|'-'|'SBI TAX ADVANTAGE FUND - SERIES I - GROWTH'|'24.2706'|'N.A.'|'N.A.'|'08-Jul-2016'
        '133322'|'-'|'-'|'Sundaram Long Term Tax Advantage Fund Regular Plan Growth'|'10.1743'|'10.1743'|'10.1743'|'08-Jul-2016'
        '135964'|'-'|'-'|'UTI Long Term Advantage Fund Series III - Regular Plan - Growth Option'|'10.5687'|'0.0'|'0.0'|'08-Jul-2016'
    }
}
