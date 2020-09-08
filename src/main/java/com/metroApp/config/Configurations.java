package com.metroApp.config;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Configurations {
    String result = "";
    InputStream inputStream;
    Properties prop ;
    String propFileName = "config.properties";
    public Configurations() throws IOException {
        {

            try {
                this.prop = new Properties();
                this.inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
                if (this.inputStream != null) {
                    this.prop.load(this.inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                this.inputStream.close();
            }

        }
    }

    public String getPropValue(String name) throws IOException {
       return  this.prop.getProperty(name);
    }
}
