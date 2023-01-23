package com.ua.glebkorobov.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static final String FILE_NAME_PROPERTIES = "/myProp.properties";
    private static final String FILE_SETTINGS_PROPERTIES = "/settings.properties";

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            InputStream is = App.class.getResourceAsStream(FILE_NAME_PROPERTIES);
            properties.load(is);
            logger.info("Getting properties");
        } catch (IOException e) {
            logger.error("Properties hasn't found");
            throw new RuntimeException(e);
        }

        UserBean userBean = new UserBean("Привіт " + properties.getProperty("username") + "!");
        logger.info("User message created");


        try {
            InputStream is = App.class.getResourceAsStream(FILE_SETTINGS_PROPERTIES);
            properties.load(is);
            logger.info("Getting settings properties");
        } catch (IOException e) {
            logger.error("Settings properties hasn't found");
            throw new RuntimeException(e);
        }

        if (properties.getProperty("file").toLowerCase(Locale.ROOT).equals("json")) {
            createJsonFile(userBean);
        }else if (properties.getProperty("file").toLowerCase(Locale.ROOT).equals("xml")) {
            createXmlFile(userBean);
        }
    }

    private static void createJsonFile(UserBean userBean) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            System.out.println(mapper.writeValueAsString(userBean));
//            mapper.writeValue(Paths.get("userJson.json").toFile(), userBean);
            logger.info("Json file created");
        } catch (IOException e) {
            logger.error("Json file hasn't found ");
            throw new RuntimeException(e);
        }
    }

    private static void createXmlFile(UserBean userBean) {
        XmlMapper xmlMapper = new XmlMapper();

        try {
            System.out.println(xmlMapper.writeValueAsString(userBean));
//            xmlMapper.writeValue(Paths.get("userXml.xml").toFile(), userBean);
            logger.info("Xml file created");
        } catch (IOException e) {
            logger.error("Xml file hasn't found ");
            throw new RuntimeException(e);
        }
    }
}
