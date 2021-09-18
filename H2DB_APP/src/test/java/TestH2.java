import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import com.hillel.homework_4.utils.dbUtil.H2Util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestH2 {
    private static final Logger testLogger = LogManager.getLogger("testLogger");
    private static final Logger rootLogger = LogManager.getRootLogger();

    @Test
    @Order(1)
    public void testCreateDB() {
        rootLogger.log(Level.INFO, "Positive testing of <createDB> method...");
        boolean isCreated = H2Util.createDB();
        Assertions.assertTrue(isCreated);
        testLogger.log(Level.INFO, "Positive <testCreateDB> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(6)
    public void testAddCountry() {
        rootLogger.log(Level.INFO, "Positive testing of <addCountry> method...");
        boolean isAdded = H2Util.customInsertCountries(new CountryPojo(1,"Litva"));
        Assertions.assertTrue(isAdded);
        testLogger.log(Level.INFO, "Positive <testAddCountry> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(7)
    public void testAddRegion() {
        rootLogger.log(Level.INFO, "Positive testing of <addRegion> method...");
        boolean iaAdded = H2Util.customInsertRegions(new RegionPojo(1,1, "Litva region"));
        Assertions.assertTrue(iaAdded);
        testLogger.log(Level.INFO, "<Positive testAddRegion> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(8)
    public void testAddRegionNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <addRegion> method...");
        boolean isAdded = H2Util.customInsertRegions(new RegionPojo(5,10, null));
        Assertions.assertFalse(isAdded);
        testLogger.log(Level.INFO, "Negative <testAddRegion> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(9)
    public void testAddCity() {
        rootLogger.log(Level.INFO, "Positive testing of <addCity> method...");
        boolean isAdded = H2Util.customInsertCities(new CityPojo(1,1, 1, "Litva City"));
        Assertions.assertTrue(isAdded);
        testLogger.log(Level.INFO, "Positive <testAddCity> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(10)
    public void testAddCityNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <addCity> method...");
        boolean isAdded = H2Util.customInsertCities(null);
        Assertions.assertFalse(isAdded);
        testLogger.log(Level.INFO, "Negative <testAddCity> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(11)
    public void testFindCountryById() {
        rootLogger.log(Level.INFO, "Positive testing of <findCountryById> method...");
        boolean isFound = H2Util.findCountryById(1);
        Assertions.assertTrue(isFound);
        testLogger.log(Level.INFO, "Positive <testFindCountryById> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(12)
    public void testFindCountryByIdNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <findCountryById> method...");
        boolean isFound = H2Util.findCountryById(100);
        Assertions.assertFalse(isFound);
        testLogger.log(Level.INFO, "Negative <testFindCountryById> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(13)
    public void testFindCountryByName() {
        rootLogger.log(Level.INFO, "Positive testing of <findCountryByName> method...");
        boolean isFound = H2Util.findCountryByName("Litva");
        Assertions.assertTrue(isFound);
        testLogger.log(Level.INFO, "Positive <testFindCountryByName> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(14)
    public void testFindCountryByNameNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <findCountryByName> method...");
        boolean isFound = H2Util.findCountryByName("1234656545");
        Assertions.assertFalse(isFound);
        testLogger.log(Level.INFO, "Negative <testFindCountryByName> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(15)
    public void testFindRegionById() {
        rootLogger.log(Level.INFO, "Positive testing of <findRegionById> method...");
        boolean isFound = H2Util.findRegionById(1);
        Assertions.assertTrue(isFound);
        testLogger.log(Level.INFO, "<testFindRegionById> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(16)
    public void testFindRegionByIdNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <findRegionById> method...");
        boolean isFound = H2Util.findRegionById(355);
        Assertions.assertFalse(isFound);
        testLogger.log(Level.INFO, "Negative <testFindRegionById> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(17)
    public void testFindRegionByName() {
        rootLogger.log(Level.INFO, "Positive testing of <findRegionByName> method...");
        boolean isFound = H2Util.findRegionByName("Litva region");
        Assertions.assertTrue(isFound);
        testLogger.log(Level.INFO, "<testFindRegionByName> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(18)
    public void testFindRegionByNameNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <findRegionByName> method...");
        boolean isFound = H2Util.findRegionByName("kjhjkoiuh");
        Assertions.assertFalse(isFound);
        testLogger.log(Level.INFO, "Negative <testFindRegionByName> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(19)
    public void testFindCityById() {
        rootLogger.log(Level.INFO, "Positive testing of <findCityById> method...");
        boolean isFound = H2Util.findCityById(1);
        Assertions.assertTrue(isFound);
        testLogger.log(Level.INFO, "<testFindCityById> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(20)
    public void testFindCityByIdNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <findCityById> method...");
        boolean isFound = H2Util.findCityById(100);
        Assertions.assertFalse(isFound);
        testLogger.log(Level.INFO, "Negative <testFindCityById> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }


    @Test
    @Order(21)
    public void testFindCityByName() {
        rootLogger.log(Level.INFO, "Positive testing of <findCityByName> method...");
        boolean isFound = H2Util.findCityByName("Litva City");
        Assertions.assertTrue(isFound);
        testLogger.log(Level.INFO, "<testFindCityByName> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(22)
    public void testFindCityByNameNegative() {
        rootLogger.log(Level.INFO, "Negative testing of <findCityByName> method...");
        boolean isFound = H2Util.findCityByName("sdfsdfsdf");
        Assertions.assertFalse(isFound);
        testLogger.log(Level.INFO, "Negative <testFindCityByName> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(23)
    public void testPrintCountries() {
        rootLogger.log(Level.INFO, "Positive testing of <printCountries> method...");
        boolean isPrinted = H2Util.printCountries();
        Assertions.assertTrue(isPrinted);
        testLogger.log(Level.INFO, "Positive <TestPrintCountries> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(24)
    public void testPrintRegions() {
        rootLogger.log(Level.INFO, "Positive testing of <printRegions> method...");
        boolean isPrinted = H2Util.printRegions();
        Assertions.assertTrue(isPrinted);
        testLogger.log(Level.INFO, "Positive <testPrintRegions> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(25)
    public void testPrintCities() {
        rootLogger.log(Level.INFO, "Positive testing of <printCities> method...");
        boolean isPrinted = H2Util.printCities();
        Assertions.assertTrue(isPrinted);
        testLogger.log(Level.INFO, "Positive <testPrintCities> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }

    @Test
    @Order(26)
    public void testDropDB() {
        rootLogger.log(Level.INFO, "Positive testing of <dropDB> method...");
        boolean isDropped = H2Util.dropDB();
        Assertions.assertTrue(isDropped);
        testLogger.log(Level.INFO, "Positive <testDropDB> test passed");
        rootLogger.log(Level.INFO, "Test passed!" +
                "\n---------------------------------------------------------");
    }
}
