package framework;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLActions {

    private PropertiesUtil propertiesUtil = new PropertiesUtil();

    @SneakyThrows
    public List<Map<String,Object>> performQueryExecutions(String query)
    {
        //Map<String,Object> --> Contains the data for an individual row of data
        //List<Map<String,Object>> --> Contains the data for all the rows of data
        List<Map<String,Object>> data=new ArrayList<>();

        //Statement is the library that is used to execute the SQL Queries
        Statement stmt=connectToDB().createStatement();
        ResultSet rs; //Stores the data that is returned by the database after executing the query

        if(stmt!=null)
        {
            if(query.toLowerCase().startsWith("select")) //Checks if the query is a Select Query or not
            {
                rs=stmt.executeQuery(query); //Used for execution of Select Queries
                while (rs.next()) //Iterating through the Result Set and checks if there are any records
                {
                    Map<String,Object> record=new HashMap<>(); //Creating a new Hashtable Object

                    //.getMetaData() will help us in fetching the structure of the table
                    int noOfColumns=rs.getMetaData().getColumnCount(); //Getting the number of Columns in the table

                    for (int i = 1; i <= noOfColumns; i++) //Iterating through the Columns
                    {
                        String columnName=rs.getMetaData().getColumnName(i); //Getting the Column Name
                        Object columnValue=rs.getObject(columnName); //Getting the Column Value

                        if(columnValue!=null)
                        {
                            record.put(columnName,columnValue); //Putting the Column Name and Column Value in the Hashtable Object
                        }
                        else
                        {
                            record.put(columnName,""); //Putting the Column Name and Column Value in the Hashtable Object
                        }
                    }

                    data.add(record);//Adding the Hashtable Object to the List Object
                }

                System.out.println(data);

            }

            else
            {
                stmt.execute(query); //used for execution of Insert, Update, Delete Queries

                //stmt.execute() --> returns a boolean value indicating whether the SQL statement was processed successfully or not
                //If the value is true, it means that the query was processed successfully
                //If the value is false, it means that the query was not processed successfully
                //This is because the query may not be valid or the query may not be executed successfully
            }
        }

        return data;
    }

    //Connect to the MS SQL DB
    @SneakyThrows
    private Connection connectToDB() {

        //Loading the Driver and creating a new Instance/object for JDBC Driver for MSSQL Server Database
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        //Creating a new Instance for JDBC Driver for MySQL Database
//            Class.forName("com.mysql.jdbc.Driver").newInstance();

        //Creating a new Instance for JDBC Driver for Oracle Database
//            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

        Connection con=null;

        String jdbcURL="jdbc:sqlserver://"+propertiesUtil.getDatabaseIP()+":1433;"+";databaseName="+propertiesUtil.getDatabaseName()+";encrypt=false;trustServerCertificate=false";

        //DriverManager.getConnection() method is used to establish a connection to the database.
        con= DriverManager.getConnection(jdbcURL,propertiesUtil.getDBUserName(),propertiesUtil.getDBPassword());

        return con;
    }

}
