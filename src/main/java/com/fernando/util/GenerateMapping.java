package com.fernando.util;

import com.fernando.dao.ConnectionObject;
import com.fernando.dao.PredicateObject;

import java.io.*;
import java.util.List;

/**
 * Created by ferna on 24/04/2016.
 */
public class GenerateMapping {

    public static void composeFile(ConnectionObject connectionObject, String[] prefixes, List<PredicateObject> predicateObjects){
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("mapping.ttl"), "utf-8"));
            writer.write(prefix(prefixes));
            writer.write(logicalTable(connectionObject));
            writer.write(subjectMap());
            writer.write(predicateObjects(predicateObjects));
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String prefix(String[] prefixes){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<prefixes.length;i++){
            stringBuilder.append(prefixes[i]);
        }
        return "@prefix rr: <http://www.w3.org/ns/r2rml#> .\n"+stringBuilder.toString()+"\n\n";
    }
    public static String logicalTable(ConnectionObject connectionObject){
        return "rr:logicalTable [\n    rr:tableName  \""+connectionObject.getSchema()+"\"\n];\n\n";
    }
    public static String subjectMap(){
        return "rr:subjectMap [\n    a rr:Subject; \n    rr:template \"http://example.com/resource/{id}\"; \n    rr:termType rr:IRI; \n    rr:class <http://example.com/vocabulary/class>;\n];\n\n";
    }
    public static String predicateObjects(List<PredicateObject>predicateObjects){
        StringBuilder stringBuilder = new StringBuilder();
        for (PredicateObject pre :predicateObjects) {
            stringBuilder.append("rr:predicateObjectMap [ \n    rr:predicate "+pre.getProperty()+"; \n    rr:objectMap [ \n      rr:column \""+pre.getColumn()+"\"; \n      rr:termType "+pre.getType()+"; ]; \n];\n\n");
        }
        return stringBuilder.toString();
    }
}
