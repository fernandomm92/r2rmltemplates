package com.fernando.controller;

import com.fernando.dao.ConnectionObject;
import com.fernando.dao.PredicateObject;
import com.fernando.util.GenerateConnectionFile;
import com.fernando.util.GenerateMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferna on 11/04/2016.
 */
@Controller
public class MainController {

    private JdbcTemplate jdbcTemplate;

    private List<String> columns = new ArrayList();

    private ConnectionObject connectionObject = new ConnectionObject();

    @RequestMapping(value = "/")
    public ModelAndView form(ModelAndView model){
        model.setViewName("db_form");
        return model;
    }

    @RequestMapping(value = "/savedbdata", method = RequestMethod.POST)
    public ModelAndView saveDBData(HttpServletRequest request) {

        connectionObject.setUrl(request.getParameter("db_url"));
        connectionObject.setUsername(request.getParameter("username"));
        connectionObject.setPassword(request.getParameter("password"));
        connectionObject.setSchema(request.getParameter("schema"));

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(connectionObject.getUrl());
        dataSource.setUsername(connectionObject.getUsername());
        dataSource.setPassword(connectionObject.getPassword());

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.query("SELECT * FROM "+connectionObject.getSchema(),new ResultSetExtractor() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                columns.clear();
                for (int i = 1; i < columnCount+1; i++) {
                    String columnName = rsmd.getColumnName(i);
                    columns.add(columnName);
                    System.out.println(columnName);
                }
                return columnCount;
            }
        });
        ModelAndView model = new ModelAndView("redirect:/columns");
        model.addObject("co",columns);
        return model;
    }

    @RequestMapping(value = "/columns")
    public ModelAndView editR2RML(ModelAndView model){
        model.addObject("co",columns);
        model.setViewName("editR2RML");
        return model;
    }

    @RequestMapping(value = "/saveR2RMLFields", method = RequestMethod.POST)
    public ModelAndView saveR2RMLFields(HttpServletRequest request) {
        List<PredicateObject> propertyObjects = new ArrayList<>();

        for(int i=0;i<columns.size();i++){
            String property = request.getParameter("property"+String.valueOf(i));
            String type = request.getParameter("radio"+String.valueOf(i));

            PredicateObject propertyObject = new PredicateObject();
            propertyObject.setColumn(columns.get(i));
            propertyObject.setProperty(property);
            propertyObject.setType(type);
            propertyObjects.add(propertyObject);
        }
        String [] prefixes = request.getParameter("prefixTextarea").split("\n");

        GenerateMapping.composeFile(connectionObject,prefixes,propertyObjects);
        GenerateConnectionFile.composeFile(connectionObject);

        return new ModelAndView("redirect:/show_file");
    }

    @RequestMapping(value = "/show_file")
    public ModelAndView showMappingFile(ModelAndView model){
        model.setViewName("show_file");
        return model;
    }
}
