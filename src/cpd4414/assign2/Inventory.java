/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.assign2;

import java.sql.*;

/**
 * This class is a wrapper around the Inventory table.
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class Inventory {

    /**
     * Utility method for acquiring a valid Database Connection
     * @return the database Connection object
     */
    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("JDBC Driver Not Found: " + ex.getMessage());
        }

        try {
            String jdbc = "jdbc:mysql://ipro.lambton.on.ca/inventory";
            conn = DriverManager.getConnection(jdbc, "products", "products");
        } catch (SQLException ex) {
            System.err.println("Failed to Connect: " + ex.getMessage());
        }
        return conn;
    }

    /**
     * Finds out how many of an item are available in the Inventory based on 
     * its product ID. Returns -1 if there is an error.
     * @param id - the product ID
     * @return - the quantity
     */
    public static int getQuantityForId(int id) {
        int quantity = -1;
        try {
            Connection conn = getConnection();        
            String query = "SELECT quantity FROM inventory WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);            
            ResultSet rs = pstmt.executeQuery();            
            rs.first();
            quantity = rs.getInt("quantity");
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Failed to Get Quantity for ID " + id + ": " + ex.getMessage());
        }
        return quantity;
    }
}
