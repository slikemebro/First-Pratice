package com.ua.glebkorobov.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ua.glebkorobov.practice.exceptions.WrongFormatFile;
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

    private static final String FILE_FORMAT = "format";

    public static void main(String[] args) throws WrongFormatFile {

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

        createFile(userBean);
    }


    private static void createFile(UserBean userBean) throws WrongFormatFile {
        if (System.getProperty(FILE_FORMAT) == null) {
            throw new WrongFormatFile("Wrong format of file: " + System.getProperty(FILE_FORMAT));
        }

        ObjectMapper mapper;

        if (System.getProperty(FILE_FORMAT).equalsIgnoreCase("json")) {
            mapper = new ObjectMapper();

            try {
                logger.info(mapper.writeValueAsString(userBean));
                logger.info("Json file created");
            } catch (IOException e) {
                logger.error("Json file hasn't found ");
                throw new RuntimeException(e);
            }
        } else if (System.getProperty(FILE_FORMAT).equalsIgnoreCase("xml")) {
            mapper = new XmlMapper();

            try {
                logger.info(mapper.writeValueAsString(userBean));
                logger.info("Xml file created");
            } catch (IOException e) {
                logger.error("Xml file hasn't found ");
                throw new RuntimeException(e);
            }
        } else {
            logger.error("Wrong format of file");
            throw new WrongFormatFile("Wrong format of file: " + System.getProperty(FILE_FORMAT));
        }
    }
}
