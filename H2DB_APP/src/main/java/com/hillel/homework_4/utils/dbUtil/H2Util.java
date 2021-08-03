package com.hillel.homework_4.utils.dbUtil;

import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.sql.*;

public class H2Util {
    private static final Logger appLogger = LogManager.getLogger("appLogger");

    private static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./DB/names", "root", "hillel");
            appLogger.log(Level.INFO, "Connection successfully established...");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <connect> METHOD...");
        }
        return connection;
    }

    public static boolean createDB() {
        try {
            Connection connection = connect();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            ClassLoader classLoader = H2Util.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("createTables.sql");
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream));
                scriptRunner.runScript(reader);
            }
            appLogger.log(Level.INFO, "Database successfully created...");
            connection.close();
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <createDB> METHOD...");
        }
        return false;
    }

    public static boolean dropDB() {
        try {
            Connection connection = connect();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            ClassLoader classLoader = H2Util.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("dropTables.sql");
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream));
                scriptRunner.runScript(reader);
            }
            connection.close();
            appLogger.log(Level.INFO, "Database successfully dropped...");
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <dropDB> METHOD...");
        }
        return false;
    }

    public static boolean insertCountries() {
        try {
            String sql = "INSERT INTO PUBLIC.COUNTRIES (country_name) VALUES (?)";
            CountryPojo bean;
            String[] header = {"country_name"};
            int count = 0;
            int batchSize = 20;
            String csvPath = "./unzip/countries.csv";
            Connection connection = connect();
            connection.setAutoCommit(false);
            CellProcessor[] cellProcessor = new CellProcessor[]{
                    new NotNull(),
            };
            PreparedStatement statement = connection.prepareStatement(sql);
            ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvPath),
                    CsvPreference.STANDARD_PREFERENCE);
            beanReader.getHeader(true);

            while ((bean = beanReader.read(CountryPojo.class, header, cellProcessor)) != null) {
                String country_name = bean.getCountry_name();

                statement.setString(1, country_name);

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            beanReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            appLogger.log(Level.INFO, "Values were successfully inserted from <countries.scv>...");
            return true;
        } catch (IOException | SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <insertCountries> METHOD...");
        }
        return false;
    }

    public static boolean insertRegions() {
        try {
            String sql = "INSERT INTO PUBLIC.REGIONS (ID_CON, REGION_NAME) VALUES (?, ?)";
            RegionPojo bean;
            String[] header = {"id_con", "region_name"};
            int count = 0;
            int batchSize = 20;
            String csvPath = "./unzip/regions.csv";
            Connection connection = connect();
            connection.setAutoCommit(false);
            CellProcessor[] cellProcessor = new CellProcessor[]{
                    new ParseInt(),
                    new NotNull()
            };
            PreparedStatement statement = connection.prepareStatement(sql);
            ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvPath),
                    CsvPreference.STANDARD_PREFERENCE);
            beanReader.getHeader(true);

            while ((bean = beanReader.read(RegionPojo.class, header, cellProcessor)) != null) {
                int id_con = bean.getId_con();
                String region_name = bean.getRegion_name();

                statement.setInt(1, id_con);
                statement.setString(2, region_name);

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            beanReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            appLogger.log(Level.INFO, "Values were successfully inserted from <regions.scv>...");
            return true;
        } catch (IOException | SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <insertRegions> METHOD...");
        }
        return false;
    }

    public static boolean insertCities() {
        try {
            String sql = "INSERT INTO PUBLIC.CITIES (ID_CON, ID_REG, CITY_NAME) VALUES (?, ?, ?)";
            CityPojo bean;
            String[] header = {"id_con", "id_reg", "city_name"};
            int count = 0;
            int batchSize = 20;
            String csvPath = "./unzip/cities.csv";
            Connection connection = connect();
            connection.setAutoCommit(false);
            CellProcessor[] cellProcessor = new CellProcessor[]{
                    new ParseInt(),
                    new ParseInt(),
                    new NotNull()
            };
            PreparedStatement statement = connection.prepareStatement(sql);
            ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvPath),
                    CsvPreference.STANDARD_PREFERENCE);
            beanReader.getHeader(true);

            while ((bean = beanReader.read(CityPojo.class, header, cellProcessor)) != null) {
                int id_con = bean.getId_con();
                int id_reg = bean.getId_reg();
                String city_name = bean.getCity_name();

                statement.setInt(1, id_con);
                statement.setInt(2, id_reg);
                statement.setString(3, city_name);

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            beanReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            appLogger.log(Level.INFO, "Values were successfully inserted from <cities.csv>...");
            return true;
        } catch (IOException | SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <insertCities> METHOD...");
        }
        return false;
    }

    public static boolean customInsertCountries(CountryPojo countryPojo) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "INSERT INTO PUBLIC.COUNTRIES (country_name) " +
                            "VALUES ('%s')",
                    countryPojo.getCountry_name());
            statement.executeUpdate(sql);
            appLogger.log(Level.INFO, "Custom country value was successfully added...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <customInsertCountries> METHOD...");
        }
        return false;
    }

    public static boolean customInsertRegions(RegionPojo regionPojo) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "INSERT INTO PUBLIC.REGIONS (id_con, region_name) " +
                            "VALUES (%d, '%s')",
                    regionPojo.getId_con(),
                    regionPojo.getRegion_name());
            statement.executeUpdate(sql);
            appLogger.log(Level.INFO, "Custom region value was successfully added...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <customInsertRegions> METHOD...");
        }
        return false;
    }

    public static boolean customInsertCities(CityPojo cityPojo) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "INSERT INTO PUBLIC.CITIES (id_con, id_reg, city_name) " +
                            "VALUES (%d, %d, '%s')",
                    cityPojo.getId_con(),
                    cityPojo.getId_reg(),
                    cityPojo.getCity_name());
            statement.executeUpdate(sql);
            appLogger.log(Level.INFO, "Custom city value was successfully added...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <customInsertCities> METHOD...");
        }
        return false;
    }

    public static boolean findCountryById(int id) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "SELECT * FROM PUBLIC.COUNTRIES " +
                            "WHERE PUBLIC.COUNTRIES.ID_COUNTRY = %d", id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                appLogger.log(Level.WARN, "COUNTRY ID IS MISSING...");
                connection.close();
                return false;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            appLogger.log(Level.INFO, "Country value was successfully found by id...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findElementById> METHOD...");
        }
        return false;
    }

    public static boolean findCountryByName(String name) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "SELECT * FROM PUBLIC.COUNTRIES " +
                            "WHERE PUBLIC.COUNTRIES.COUNTRY_NAME = '%s'", name);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                appLogger.log(Level.WARN, "COUNTRY NAME IS MISSING...");
                return false;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            appLogger.log(Level.INFO, "Country value was successfully found by name...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findCountryByName> METHOD...");
        }
        return false;
    }

    public static boolean findRegionById(int id) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "SELECT * FROM PUBLIC.REGIONS " +
                            "WHERE PUBLIC.REGIONS.ID_REGION = %d", id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                appLogger.log(Level.WARN, "REGION ID IS MISSING...");
                return false;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
            appLogger.log(Level.INFO, "Region value was successfully found by id...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findRegionById> METHOD...");
        }
        return false;
    }

    public static boolean findRegionByName(String name) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "SELECT * FROM PUBLIC.REGIONS " +
                            "WHERE PUBLIC.REGIONS.REGION_NAME = '%s'", name);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                appLogger.log(Level.WARN, "REGION NAME IS MISSING...");
                return false;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
            appLogger.log(Level.INFO, "Region value was successfully found by name...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findRegionByName> METHOD...");
        }
        return false;
    }

    public static boolean findCityById(int id) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "SELECT * FROM PUBLIC.CITIES " +
                            "WHERE PUBLIC.CITIES.ID_CITY = %d", id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                appLogger.log(Level.WARN, "CITY ID IS MISSING...");
                return false;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(4));
            }
            appLogger.log(Level.INFO, "City value was successfully found by id...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findCityById> METHOD...");
        }
        return false;
    }

    public static boolean findCityByName(String name) {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = String.format(
                    "SELECT * FROM PUBLIC.CITIES " +
                            "WHERE PUBLIC.CITIES.CITY_NAME = '%s'", name);
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                appLogger.log(Level.WARN, "CITY NAME IS MISSING...");
                return false;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(4));
            }
            appLogger.log(Level.INFO, "City value was successfully found by name...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findCityByName> METHOD...");
        }
        return false;
    }

    public static boolean printCountries() {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM PUBLIC.COUNTRIES";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            appLogger.log(Level.INFO, "Countries values were successfully printed...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <printCountries> METHOD...");
        }
        return false;
    }

    public static boolean printRegions() {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM PUBLIC.REGIONS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
            appLogger.log(Level.INFO, "Regions values were successfully printed...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <printRegions> METHOD...");
        }
        return false;
    }

    public static boolean printCities() {
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM PUBLIC.CITIES";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(4));
            }
            appLogger.log(Level.INFO, "Cities values were successfully printed...");
            connection.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <printCities> METHOD...");
        }
        return false;
    }
}
