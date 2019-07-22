package com.crud.framework.base;

import com.crud.framework.config.Settings;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;



public class FrameworkInitialize extends Base {

    public static final String USERNAME = "ibi3";
    public static final String AUTOMATE_KEY = "YpkLXQHsc2JRDt6FquP5";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public final static boolean HEADLESS = Boolean.getBoolean("headless");


    public static void initializeBrowser(String environmentType, String Os,BrowserTypes browserType ) throws MalformedURLException {
        RemoteWebDriver driver = null;
        System.out.println("\nTests are running in : " + environmentType + "\n" +
                "The Browser running is : " + browserType + "\n" +
                "The Operating system is : " + Os +"\n");
        if (environmentType.equalsIgnoreCase("local") || environmentType.equalsIgnoreCase("grid")) {

            switch (browserType) {
                case Chrome: {
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/browser/chromedriver.exe");
                    DesiredCapabilities caps = new DesiredCapabilities().chrome();
                    HashMap<String, Object> chromePreferences = new HashMap<>();
                    chromePreferences.put("profile.password_manager_enabled", false);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--test-type");
                    options.addArguments("chrome.switches", "--disable-extensions");
                    options.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling");
                    options.addArguments("--no-default-browser-check");
                    options.setExperimentalOption("prefs", chromePreferences);

                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new ChromeDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);//cap
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Chrome browser");
                    break;
                }
                case Firefox: {
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\browser\\geckodriver.exe");
                    DesiredCapabilities caps = new DesiredCapabilities().firefox();
                    caps.setBrowserName("firefox");

                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new FirefoxDriver(caps);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Firefox browser");
                    break;
                }
                case IE: {
                    DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                    caps.setCapability(CapabilityType.VERSION, "10");
                    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    caps.setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
                    InternetExplorerOptions options = new InternetExplorerOptions();
                    options.merge(caps);
                    options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                    options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
                    options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

                    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/browser/IEDriverServer.exe");
                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new InternetExplorerDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), options);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting IE browser");
                    break;
                }
                case Safari: {
                    DesiredCapabilities caps = DesiredCapabilities.safari();
                    SafariOptions options = new SafariOptions();
                    options.merge(caps);
                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new SafariDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Safari browser");
                    break;
                }
                case Edge: {
                    DesiredCapabilities caps = DesiredCapabilities.edge();
                    EdgeOptions options = new EdgeOptions();
                    options.merge(caps);
                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new EdgeDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Edge browser");
                    break;
                }
                case Opera: {
                    DesiredCapabilities caps = DesiredCapabilities.opera();
                    OperaOptions options = new OperaOptions();
                    options.merge(caps);
                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new OperaDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Opera browser");
                    break;
                }
                case Htmlunit: {
                    DesiredCapabilities caps = DesiredCapabilities.htmlUnit();
                    caps.setBrowserName("Firefox");
                    caps.setJavascriptEnabled(true);
                    caps.setPlatform(Platform.WIN10);
                    if (environmentType.equalsIgnoreCase("local")) {
                        //driver = new HtmlUnitDriver(caps);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting HtmlUnit browser");
                    break;
                }
                case Headless: {
                    DesiredCapabilities caps = DesiredCapabilities.edge();

                    if (environmentType.equalsIgnoreCase("local")) {
                        //driver = getHeadlessRemoteWebDriverInstance();
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), caps);
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting HtmlUnit browser");
                    break;
                }
            }

        } else if (environmentType.equalsIgnoreCase("BrowserStack")) {
            driver = new RemoteWebDriver(new URL(Settings.BrowserStackHub), bsDesiredCaps(browserType, Os));

            LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
            Settings.logs.Write("Starting BrowserStack environment & browsers");
        }
    }

    public static DesiredCapabilities bsDesiredCaps(BrowserTypes browserTypes, String Os) {
        final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
        final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
        final String proxyHostname = System.getProperty("proxyHost");
        final Integer proxyPort = Integer.getInteger("proxyPort");
        final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
        DesiredCapabilities caps = new DesiredCapabilities();

        if (proxyEnabled) {
            Proxy proxy = new Proxy();
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(proxyDetails);
            proxy.setSslProxy(proxyDetails);
            caps.setCapability(PROXY, proxy);
        }

        if (useRemoteWebDriver) {
//                DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.selenium_version", "3.5.2");
            switch (Os) {
                case "Windows":

                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    switch (browserTypes) {
                        case Chrome:
                            caps.setCapability("browser", browserTypes);
                            caps.setCapability("browser_version", "71.0 beta");
                            break;
                        case IE:
                            caps.setCapability("browser", "IE");
                            caps.setCapability("browser_version", "11.0");
                            break;
                        case Edge:
                            caps.setCapability("browser", "Edge");
                            caps.setCapability("browser_version", "17.0");
                            break;
                        case Firefox:
                            caps.setCapability("browser", "Firefox");
                            caps.setCapability("browser_version", "64.0 beta");
                            break;
                        default:
                            System.out.println("There is no such Browser selected: " + browserTypes);
                            break;
                    }
                    break;

                case "OS X":
                    caps.setCapability("os", "OS X");
                    caps.setCapability("os_version", "High Sierra");
                    switch (browserTypes) {
                        case Chrome:
                            caps.setCapability("browser", "Chrome");
                            caps.setCapability("browser_version", "71.0 beta");
                            break;
                        case Safari:
                            caps.setCapability("browser", "Safari");
                            caps.setCapability("browser_version", "11.0");
                            break;
                        case Firefox:
                            caps.setCapability("browser", "Firefox");
                            caps.setCapability("browser_version", "64.0 beta");
                            break;
                        default:
                            System.out.println("There is no such Browser selected: " + browserTypes);
                            break;
                    }
                    break;

                case "IOS":
                    caps.setCapability("os", "iOS");
                    caps.setCapability("os_version", "11.0");
                    caps.setCapability("real_mobile", "true");
                    switch (browserTypes) {
                        case IPhoneX:
                            caps.setCapability("device", "iPhone X");
                            break;
                        case IPhone8:
                            caps.setCapability("device", "iPhone 8");
                            break;
                        default:
                            System.out.println("There is no such Browser selected: " + browserTypes);
                            break;
                    }
                    break;

                case "Android":
                    caps.setCapability("os", "Android");
                    caps.setCapability("real_mobile", "true");
                    switch (browserTypes) {
                        case SamsungS9:
                            caps.setCapability("os_version", "8.0");
                            caps.setCapability("device", "Samsung Galaxy S9");
                            break;
                        case SamsungS8:
                            caps.setCapability("os_version", "7.0");
                            caps.setCapability("device", "Samsung Galaxy S8 Plus");
                            break;
                        default:
                            System.out.println("There is no such Browser selected: " + browserTypes);
                            break;
                    }
                    break;

                default:
                    System.out.println("There is no such operating selected: " + Os);
                    break;
            }
        }
        return caps;
    }

}