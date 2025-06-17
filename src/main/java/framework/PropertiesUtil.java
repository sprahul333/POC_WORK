package framework;

import lombok.SneakyThrows;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class PropertiesUtil {

    private InputStreamReader isr;
    private PropertiesConfiguration propertiesConfiguration;
    private PropertiesConfigurationLayout propertiesConfigurationLayout;

    @SneakyThrows
    public PropertiesUtil() {
        //InputStreamReader will read the complete data from the properties file
        isr = new InputStreamReader(new FileInputStream("Config.properties"));

        propertiesConfiguration = new PropertiesConfiguration();
        propertiesConfigurationLayout = new PropertiesConfigurationLayout();

        //It facilitates the reading of the properties file
        //By transferring the data in InputStreamReader to propertiesConfiguration
        //In the PropertiesConfiguration Object, data will be stored in the form of key-value pairs
        propertiesConfigurationLayout.load(propertiesConfiguration, isr);
    }

    private String getProperty(String key) {
        return propertiesConfiguration.getString(key, key + " not found");
    }

    public String getURL() {
        return getProperty("URL");
    }

    public String getBrowser() {
        return getProperty("Browser");
    }

    public String getResolutionSize() {
        return getProperty("ScreenResolution");
    }

    public String getUserName() {
        return getProperty("UserName");
    }

    public String getPassword() {
        return getProperty("Password");
    }

    public String getSupervisorUserName() {
        return getProperty("SuperVisorUserName");
    }

    public String getSuperVisorPassword() {
        return getProperty("SuperVisorPassword");
    }

    public String getHeadlessTest() {
        return getProperty("Headless");
    }

    public String getIncognitoMode() {
        return getProperty("Incognito");
    }

    public String getConsolidatedOrIndividualReport() {
        return getProperty("ConsolidatedOrIndividualReport");
    }

    public String getDatabaseIP() {
        return getProperty("DatabaseIP");
    }

    public String getReleaseNumber() {
        return getProperty("ReleaseNumber");
    }

    public String getDatabaseName() {
        return getProperty("DatabaseName");
    }

    public String getDBUserName() {
        return getProperty("DatabaseUser");
    }

    public String getDBPassword() {
        return getProperty("DatabasePassword");
    }

//    public static void main(String[] args) {
//        PropertiesUtil propertiesUtil=new PropertiesUtil();
//        System.out.println(propertiesUtil.getBrowser());
//        System.out.println(propertiesUtil.getUserName());
//    }
}
