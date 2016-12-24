package in.co.pujar.sudheer.finance.mf.services.unit.specs

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import groovy.util.logging.Slf4j
import in.co.pujar.sudheer.finance.mf.services.NAVService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource


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

    def "Fetch nav text data for a given URL"(){
        log.debug("Fetch Net Asset Value text data")
        given : "For a given URL "

        when : " we fetch the data "
            OutputStream data = new ByteOutputStream();
            boolean fetched=navService.fetchTextData(url,data)
        then : "data size should not be zero"
            fetch==(data.size()!=0);
            data.close()
        and : "return flag should be appropriate"
            fetch==fetched
        where: "possible URLS are : "
            url | fetch
            this.getClass().getResource( '/data-files/NAV0.txt' ).getPath()| true
            this.getClass().getResource( '/data-files/NAV1.txt' ).getPath()| true
            this.getClass().getResource( '/data-files/NAV2.txt' ).getPath()| true
            this.getClass().getResource( '/data-files/NAV3.txt' ).getPath()| true

           /* null| false
            ""| false
            "http"| false
            "http://portal.amfiindia.com/spages/NAV0.txt"| true
            "http://portal.amfiindia.com/spages/NAV4.txt"| false
            "https://portal.amfiindia.com/spages/NAV0.txt"| false
            */
    }


    def "Store nav text data into temporary location"(){
        given : "For a given URL and local location"
        def localLocation = applicationProperty.getProperty(FILE_LOCATION_PROPERTY_KEY)
        when : " we fetch and store the data locally "
        def stored=navService.storeTextData(url,fileNo,localLocation)
        then : "file size should not be zero "
        def localFilePath= navService.generateNavLocalFilePath(localLocation)
        def localFileName= navService.generateNavLocalFileName(fileNo)
        def file = new File(localFilePath,localFileName);
        store==(file.exists() && file.size()!=0)
        and : "return flag should be appropriate"
        store==stored
        where: "possible URLS are : "
        url | fileNo| store
        this.getClass().getResource( '/data-files/NAV0.txt' ).getPath()| 0 | true
        this.getClass().getResource( '/data-files/NAV1.txt' ).getPath()| 1 | true
        this.getClass().getResource( '/data-files/NAV2.txt' ).getPath()| 2 | true
        this.getClass().getResource( '/data-files/NAV3.txt' ).getPath()| 3 |true

        /*null| 0 | false
        ""| 0 | false
        "http"| 0 | false
        "http://portal.amfiindia.com/spages/NAV0.txt"| 0 | true
        "http://portal.amfiindia.com/spages/NAV4.txt"| 4 | false
        "https://portal.amfiindia.com/spages/NAV0.txt"| 5 | false
        */
    }

    @Unroll
    def "Read non empty line #lineIndex of  #url "(){
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




}
