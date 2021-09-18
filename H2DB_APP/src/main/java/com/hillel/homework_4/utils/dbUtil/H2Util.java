package com.hillel.homework_4.utils.dbUtil;

import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import com.hillel.homework_4.utils.hibernateUtil.HibernateUtil;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import javax.persistence.Query;
import java.io.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class H2Util {
    private static final Logger appLogger = LogManager.getLogger("appLogger");
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    private static Connection connect() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./DB/names", "root", "hillel");
            appLogger.log(Level.INFO, "Connection was successfully established...");
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <connect> METHOD...");
        }
        return connection;
    }

    public static boolean createDB() {
        ClassLoader classLoader = H2Util.class.getClassLoader();

        try (Connection connection = connect();
             InputStream inputStream = classLoader.getResourceAsStream("createTables.sql")) {

            ScriptRunner scriptRunner = new ScriptRunner(connection);
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream));
                scriptRunner.runScript(reader);
            }
            appLogger.log(Level.INFO, "Database successfully created...");
            return true;
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <createDB> METHOD...");
        }
        return false;
    }

    public static boolean dropDB() {
        ClassLoader classLoader = H2Util.class.getClassLoader();

        try (Connection connection = connect();
             InputStream inputStream = classLoader.getResourceAsStream("dropTables.sql")) {

            ScriptRunner scriptRunner = new ScriptRunner(connection);
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream));
                scriptRunner.runScript(reader);
            }
            appLogger.log(Level.INFO, "Database successfully dropped...");
            return true;
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <dropDB> METHOD...");
        }
        return false;
    }

    public static boolean insertCountries() {
        CountryPojo bean;
        Path csvPath = Path.of(".", "unzip", "countries.csv");
        String[] header = {"countryName"};
        String csvPathString = csvPath.toString();
        String sql = "INSERT INTO PUBLIC.COUNTRIES (country_name) VALUES (?)";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvPathString),
                     CsvPreference.STANDARD_PREFERENCE)) {

            CellProcessor[] cellProcessor = new CellProcessor[]{
                    new NotNull(),
            };
            beanReader.getHeader(true);
            while ((bean = beanReader.read(CountryPojo.class, header, cellProcessor)) != null) {
                String countryName = bean.getCountryName();
                statement.setString(1, countryName);
                statement.addBatch();
                statement.executeBatch();
            }
            statement.executeBatch();
            appLogger.log(Level.INFO, "Values were successfully inserted from <countries.scv>...");
            return true;
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <insertCountries> METHOD...");
        }
        return false;
    }

    public static boolean insertRegions() {
        RegionPojo bean;
        Path csvPath = Path.of(".", "unzip", "regions.csv");
        String sql = "INSERT INTO PUBLIC.REGIONS (ID_CON, REGION_NAME) VALUES (?, ?)";
        String[] header = {"idCon", "regionName"};
        String csvPathString = csvPath.toString();

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvPathString),
                     CsvPreference.STANDARD_PREFERENCE)) {

            CellProcessor[] cellProcessor = new CellProcessor[]{
                    new ParseInt(),
                    new NotNull()
            };
            beanReader.getHeader(true);
            while ((bean = beanReader.read(RegionPojo.class, header, cellProcessor)) != null) {
                int id_con = bean.getIdCon();
                String region_name = bean.getRegionName();
                statement.setInt(1, id_con);
                statement.setString(2, region_name);
                statement.addBatch();
                statement.executeBatch();
            }
            statement.executeBatch();
            appLogger.log(Level.INFO, "Values were successfully inserted from <regions.scv>...");
            return true;
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <insertRegions> METHOD...");
        }
        return false;
    }

    public static boolean insertCities() {
        CityPojo bean;
        Path csvPath = Path.of(".", "unzip", "cities.csv");
        String sql = "INSERT INTO PUBLIC.CITIES (ID_CON, ID_REG, CITY_NAME) VALUES (?, ?, ?)";
        String[] header = {"idCon", "idReg", "cityName"};
        String csvPathString = csvPath.toString();

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(csvPathString),
                     CsvPreference.STANDARD_PREFERENCE)) {

            CellProcessor[] cellProcessor = new CellProcessor[]{
                    new ParseInt(),
                    new ParseInt(),
                    new NotNull()
            };
            beanReader.getHeader(true);
            while ((bean = beanReader.read(CityPojo.class, header, cellProcessor)) != null) {
                int id_con = bean.getIdCon();
                int id_reg = bean.getIdReg();
                String city_name = bean.getCityName();
                statement.setInt(1, id_con);
                statement.setInt(2, id_reg);
                statement.setString(3, city_name);
                statement.addBatch();
                statement.executeBatch();
            }
            statement.executeBatch();
            appLogger.log(Level.INFO, "Values were successfully inserted from <cities.csv>...");
            return true;
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <insertCities> METHOD...");
        }
        return false;
    }

    public static boolean customInsertCountries(CountryPojo countryPojo) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            session.save(countryPojo);
            session.getTransaction().commit();
            appLogger.log(Level.INFO, "Custom country value was successfully added...");
            return true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public static boolean customInsertRegions(RegionPojo regionPojo) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            if (regionPojo.getRegionName() != null) {
                session.save(regionPojo);
                session.getTransaction().commit();
                appLogger.log(Level.INFO, "Custom region value was successfully added...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <customInsertRegions> METHOD...");
        }
        return false;
    }

    public static boolean customInsertCities(CityPojo cityPojo) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            session.save(cityPojo);
            session.getTransaction().commit();
            appLogger.log(Level.INFO, "Custom city value was successfully added...");
            return true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public static boolean findCountryById(int id) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM CountryPojo WHERE idCountry = :paramId");
            query.setParameter("paramId", id);
            session.getTransaction().commit();
            List<?> list = query.getResultStream().toList();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findElementById> METHOD...");
            throwable.printStackTrace();
        }
        return false;
    }

    public static boolean findCountryByName(String name) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM CountryPojo WHERE countryName = :paramName");
            query.setParameter("paramName", name);
            session.getTransaction().commit();
            List<?> list = query.getResultStream().toList();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findCountryByName> METHOD...");
        }
        return false;
    }

    public static boolean findRegionById(int id) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM RegionPojo WHERE idRegion = :paramId");
            query.setParameter("paramId", id);
            session.getTransaction().commit();
            List<?> list = query.getResultStream().toList();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findRegionById> METHOD...");
        }
        return false;
    }

    public static boolean findRegionByName(String name) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM RegionPojo WHERE regionName = :paramName");
            query.setParameter("paramName", name);
            session.getTransaction().commit();
            List<?> list = query.getResultStream().toList();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findRegionByName> METHOD...");
        }
        return false;
    }

    public static boolean findCityById(int id) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM CityPojo WHERE idCity = :paramId");
            query.setParameter("paramId", id);
            session.getTransaction().commit();
            List<?> list = query.getResultStream().toList();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findCityById> METHOD...");
        }
        return false;
    }

    public static boolean findCityByName(String name) {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM CityPojo WHERE cityName = :paramName");
            query.setParameter("paramName", name);
            session.getTransaction().commit();
            List<?> list = query.getResultStream().toList();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <findCityByName> METHOD...");
        }
        return false;
    }

    public static boolean printCountries() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM CountryPojo");
            List<?> list = query.getResultStream().toList();
            session.getTransaction().commit();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <printCountries> METHOD...");
        }
        return false;
    }

    public static boolean printRegions() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM RegionPojo");
            List<?> list = query.getResultStream().toList();
            session.getTransaction().commit();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <printRegions> METHOD...");
        }
        return false;
    }

    public static boolean printCities() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM CityPojo");
            List<?> list = query.getResultStream().toList();
            session.getTransaction().commit();
            if (!(list.isEmpty())) {
                list.forEach(System.out::println);
                appLogger.log(Level.INFO, "Country value was successfully found by id...");
                return true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            appLogger.log(Level.WARN, "CAUGHT EXCEPTION IN <printCities> METHOD...");
        }
        return false;
    }
}
