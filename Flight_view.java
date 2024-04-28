package com.view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.controller.*;
import com.model.*;


public class Flight_view {

	
		

			private static Scanner sc = new Scanner(System.in);

			public static Flight SaveObject() {

				Flight s = new Flight();

				System.out.println("Enter the Id: ");
				int id = sc.nextInt();

				System.out.println("Enter the name: ");
				sc.nextLine();
				String name = sc.nextLine();

				System.out.println("Enter the number: ");
				String num = sc.nextLine();

				System.out.println("Enter Source: ");
				sc.nextLine();
				String source = sc.nextLine();

				System.out.println("Enter Destination: ");
				String des = sc.nextLine();

				
				
				s.setId(id);
				s.setName(name);
				s.setNumber(num);
				s.setSource(source);
				s.setDestination(des);
				
				

				return s;

			}

			public static void main(String[] args) {

				Connection con = null;

				try {
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_database", "postgres", "root");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();

				} catch (SQLException e) {
					e.printStackTrace();
				}

				Flight_controller controller = new Flight_controller();

				System.out.println("... Flight info...");

				while (true) {

					System.out.println("\n---Choose What to Perform---\n");
					System.out.println("1. Save Flight.");
					System.out.println("2. Find Flight by id.");
					System.out.println("3. Update Flight By Id.");
					System.out.println("4. Delete Flight By Id.");
					System.out.println("5. Exit.");
					
					System.out.println("\n Enter Your choice... ");
					int choice = sc.nextInt();

					switch (choice) {

					case 1: {
						System.out.println("Enter the No. of Records to Enter: ");
						int no = sc.nextInt();

						int count = 1;
						while (count <= no) {
							System.out.println("Enter Data for flight " + count);
							Flight f = SaveObject() ;
							int s = controller.saveFlight(f, con);

							if (s != 0) {
								System.out.println("Data saved Successfully!\n");
							} else {
								System.out.println("Data not saved!...");
							}
							count++;
						}

						break;
					}

					case 2: {
						System.out.println("Enter Flight Id: ");
						int id = sc.nextInt();
						ResultSet rs = controller.findFlightById(id, con);

						try {
							if (rs.next() != false) {
								System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | "
										+ rs.getString(4) + " | " + rs.getString(5) );
							} else {
								System.out.println("Record Not Found....");
							}
						} catch (SQLException e) {

							e.printStackTrace();
						}
						break;

					}

					case 3: {
						System.out.println("Enter Flight ID: ");
						int id2 = sc.nextInt();

						System.out.println("Enter name: ");
						sc.nextLine();
						String num = sc.nextLine();

						int n = controller.update_FlightById(id2, num, con);

						if (n != 0) {
							System.out.println("Flight details updated!");
						} else {
							System.out.println("Record not found!\n");
						}
						break;

					}
					
					case 4: {
						System.out.println("Enter Flight ID");
						int id3 = sc.nextInt();

						int b = controller.deleteFlightById(id3, con);
						if (b != 0) {
							System.out.println("Record deleted!..\n");
						} else {
							System.out.println("Record not Found!..\n");
						}
						break;

					}
					
					
					
					case 5: {
						System.out.println("Thank You!..");
						try {
							con.close();
						} catch (SQLException e) {

							e.printStackTrace();
						}
						return;

					}
					default:
						System.out.println("Invalid choice..\n");
						break;
					}
				}

			}
		

	}


