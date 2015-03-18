package com.nicolasodano.cassandra;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Hello world!
 *
 */
public class Main 
{
	
	
    public static void main( String[] args )
    {
    	Cluster cluster;
    	Session session;
    	
    	Collection <InetAddress> nodes = new ArrayList<InetAddress>();
    	
    	try
    	{
    		nodes.add(InetAddress.getByName("127.0.0.1"));
        	nodes.add(InetAddress.getByName("127.0.0.2"));
        	nodes.add(InetAddress.getByName("127.0.0.3"));
    	} catch(UnknownHostException ex) 
    	{
    		ex.printStackTrace();
    		return;
    	}
    	
    	
        // cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        cluster = Cluster.builder().addContactPoints(nodes).build();
        session = cluster.connect("playlist");
        
        // Insert one record into the users table
        session.execute("INSERT INTO users (lastname, age, email, firstname) VALUES ('Jones', 35, 'bob@example.com', 'Bob')");
   
        // Use select to get the user we just entered
        ResultSet results = session.execute("SELECT * FROM users");
        for (Row row : results) {
        	System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
        }
    
        
        cluster.close();
    }
}
