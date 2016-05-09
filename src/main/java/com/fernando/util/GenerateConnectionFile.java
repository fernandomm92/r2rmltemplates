package com.fernando.util;

import com.fernando.dao.ConnectionObject;

import java.io.*;

/**
 * Created by ferna on 04/05/2016.
 */
public class GenerateConnectionFile {

    public static void composeFile(ConnectionObject connectionObject){
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("properties.r2rml.properties"), "utf-8"));
            writer.write(connection(connectionObject));
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String connection(ConnectionObject connectionObject){
        return "mappingdocument.file.path=mapping.ttl\n" +
                "output.file.path=result.nq\n" +
                "\n" +
                "output.rdflanguage=TURTLE\n" +
                "\n" +
                "no_of_database=1 \n" +
                "database.name[0]=postgres\n" +
                "database.driver[0]=org.postgresql.Driver\n" +
                "database.url[0]="+connectionObject.getUrl()+"\n" +
                "database.user[0]="+connectionObject.getUsername()+"\n" +
                "database.pwd[0]="+connectionObject.getPassword()+"\n" +
                "database.type[0]=postgresql\n" +
                "\n" +
                "uri.transform=toLowercase\n";
    }
}
