package com.academy.tracuunganhkt;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.academy.tracuunganhkt.Economics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionHelper {
    Connection con;
    String username, password, ip, port, database;
    @SuppressLint("NewApi")

    public Connection connectionclass() {
        ip = "192.168.0.153";
//        ip = "192.168.110.230";
//        ip="192.168.10.106";
        database = "NganhKinhTe";
        username = "pgt";
        password = "pgt";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+username+";password="+password+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }

        return connection;
    }
}
