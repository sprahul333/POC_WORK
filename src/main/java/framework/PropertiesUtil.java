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

    @SneakyThrows
    private String getProperty(String env,String key) {

        InputStreamReader isr = new InputStreamReader(new FileInputStream("Config.properties"));

        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        PropertiesConfigurationLayout propertiesConfigurationLayout = new PropertiesConfigurationLayout();

        propertiesConfigurationLayout.load(propertiesConfiguration, isr);

        return propertiesConfiguration.getString(key, key + " not found");
    }

    private String getProperty(String key) {
        return propertiesConfiguration.getString(key, key + " not found");
    }

    public String getEnvironment()
    {
        return getProperty("Environment");
    }

    public String getURL() {
        return getProperty(getEnvironment(),"URL");
    }

    public String getKillExistingBrowsers()
    {
        return getProperty("KillExistingBrowsers");
    }

    public String getBrowserClose()
    {
        return getProperty("BrowserClose");
    }

    public String getBrowser() {
        return getProperty("Browser");
    }

    public String getResolutionSize() {
        return getProperty("ScreenResolution");
    }

    public String getUserName() {
        return getProperty(getEnvironment(),"UserName");
    }

    public String getPassword() {
        return getProperty(getEnvironment(),"Password");
    }

    public String getSupervisorUserName() {
        return getProperty(getEnvironment(),"SuperVisorUserName");
    }

    public String getSuperVisorPassword() {
        return getProperty(getEnvironment(),"SuperVisorPassword");
    }

    public String getBranchUserName() {
        return getProperty(getEnvironment(),"BranchManagerUserName");
    }

    public String getBranchPassword() {
        return getProperty(getEnvironment(),"BranchManagerPassword");
    }

    public String getCashierUserName() {
        return getProperty(getEnvironment(),"CashierUserName");
    }

    public String getCashierPassword() {
        return getProperty(getEnvironment(),"CashierPassword");
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
        return getProperty(getEnvironment(),"DatabaseIP");
    }

    public String getReleaseNumber() {
        return getProperty("ReleaseNumber");
    }

    public String getDatabaseName() {
        return getProperty(getEnvironment(),"DatabaseName");
    }

    public String getDBUserName() {
        return getProperty(getEnvironment(),"DatabaseUser");
    }

    public String getDBPassword() {
        return getProperty(getEnvironment(),"DatabasePassword");
    }
}
