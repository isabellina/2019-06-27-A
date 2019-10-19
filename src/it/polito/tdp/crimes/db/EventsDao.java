package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.crimes.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<String> getCategorieReati(){
		String sql = "select distinct offense_category_id " +
				"from events " ;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			List<String> categorieReati = new LinkedList<String>();
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				categorieReati.add(rs.getString("offense_category_id"));
			}
			
			conn.close();
			return categorieReati;
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
    public List<Year> getAnno(){
    	String sql= "select distinct  Year(reported_date) as anno " +
    			 "from events " +
    			"order by anno ASC " ;
    			
    	try {
    		Connection conn = DBConnect.getConnection();
    		PreparedStatement st = conn.prepareStatement(sql);
    		List<Year> anni = new LinkedList<Year>();
    		ResultSet rs = st.executeQuery();
    		while(rs.next()) {
    			anni.add(Year.of(rs.getInt("anno")));
    		}
    		
    		conn.close();
    		return anni;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    public List<String> getTipoReati(Year y, String s){
    	String sql = "select offense_type_id " +
    			"from events " +
    			"where Year(reported_date)=? and  offense_category_id = ? " ;
    	
    	try {
    		
    		Connection conn = DBConnect.getConnection();
    		PreparedStatement st = conn.prepareStatement(sql);
    		List<String> listaTipi = new LinkedList<String>();
    		st.setInt(1, y.getValue());
    		st.setString(2, s);
    		ResultSet rs = st.executeQuery();
    		
    		
    		while(rs.next()) {
    			listaTipi.add(rs.getString("offense_type_id"));
    		}
    		conn.close();
    		return listaTipi;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    public int getPeso(String type_id1) {
    	return 0;
    }
   
}
