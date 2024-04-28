package com.controller;
//package com.controller;
import java.sql.Connection;
//import java.sql.PrepareStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Flight;
import com.view.*;


public class Flight_controller {

	
		
	
			
			public int  saveFlight(Flight S, Connection  con)
			{
				int n =0;
				try
				{
					Class.forName("org.postgresql.Driver");
				
				     PreparedStatement psmt = con.prepareStatement("insert into flight values(?,?,?,?,?)");
				
				psmt.setLong(1, S.getId());
				psmt.setString(2, S.getName());
				psmt.setString(3, S.getNumber());
				psmt.setString(4, S.getSource());
				psmt.setString(5, S.getDestination());
				
		 	 n =	psmt.executeUpdate();
				
				
				}
				catch (SQLException | ClassNotFoundException e )
				{
					e.printStackTrace();
				}
				return n;
				
			
			}
			public ResultSet findFlightById(int id, Connection con)
			{
				ResultSet rs = null;
				
				try
				{
					
					PreparedStatement psmt = con.prepareStatement("select * from flight where id = ?");
					psmt.setInt(1, id);

					rs = psmt.executeQuery();

					
				}
				catch (SQLException e )
				{
					e.printStackTrace();
				}
			   return rs;
			}
			   public  int update_FlightById(int id, String name,Connection con)
			   {
				   int n=0;
				   try
				   {
					 
				   
					   PreparedStatement psmt = con.prepareStatement("update flight set name = ? where id =?");
					   psmt.setString(1,name);
						psmt.setInt(2, id);
						
						n = psmt.executeUpdate();
						

				   }
				   catch(SQLException e)
				   {
					   e.printStackTrace();
				   }

		         return n;
				   
			   }
			   public int deleteFlightById(int id,Connection con)
				  {
				   int b=0;
				   try
				   {
					   
					   PreparedStatement psmt = con.prepareStatement("delete from flight where id =?");
					   psmt.setInt(1, id);
					   b = psmt.executeUpdate();
				   }
				   catch(SQLException e)
				   {
					   e.printStackTrace();
				   }
		  
		         return b;
				  }
			  
				   
			public static void main(String[] args) {
				
				
				
				
				
				

			}

		


	}


