package main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.dao.EventDAO;
import main.java.model.Event;
import main.java.utilities.ConnectionManager;

public class EventDAOImpl implements EventDAO {

	Connection connection = null;
	PreparedStatement prepedStmt = null;

	public boolean addEvent(Event event) {
		try {
			connection = ConnectionManager.getConnection();
			String sql = "INSERT INTO Event VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			// in the future we can use google api to check if real address
			// and an address checker api to find similar and auto format address
			// this will be done before coming to this dao function and opening a connection

			prepedStmt.setString(1, event.getTitle());
			prepedStmt.setString(2, event.getInfo_url());
			prepedStmt.setInt(3, event.getAddress_number());
			prepedStmt.setString(4, event.getStreet_name());
			prepedStmt.setString(5, event.getCity());
			prepedStmt.setString(6, event.getState());
			prepedStmt.setInt(7, event.getZip());
			prepedStmt.setInt(8, event.getNumber_helpers_needed());
			prepedStmt.setString(9, event.getEvent_image_url());

			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			// if exception is already exists then display a message (should this be in
			// exception?
			// better in exception so I don't have to do a separate query for title
			return false;
		} finally {
			ConnectionManager.closeResources(prepedStmt, connection);
		}
	}
	
	public boolean updateEvent(Event event, String original_title) {
		try {
			// in controller validator check the values comply before opening connection..
			// more abstraction.
			connection = ConnectionManager.getConnection();
			String sql = "UPDATE Event SET title=?, info_url=?, address_number=?, street_name=?, city=?, state=?, zip=?, number_helpers_needed=?, event_image_url=? WHERE title=?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, event.getTitle());
			prepedStmt.setString(2, event.getInfo_url());
			prepedStmt.setInt(3, event.getAddress_number());
			prepedStmt.setString(4, event.getStreet_name());
			prepedStmt.setString(5, event.getCity());
			prepedStmt.setString(6, event.getState());
			prepedStmt.setInt(7, event.getZip());
			prepedStmt.setInt(8, event.getNumber_helpers_needed());
			prepedStmt.setString(9, event.getEvent_image_url());
			prepedStmt.setString(10, original_title);

			if (prepedStmt.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionManager.closeResources(prepedStmt, connection);
		}
	}

	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();

		try {
			connection = ConnectionManager.getConnection();
			String sql = "SELECT * FROM Event";
			prepedStmt = connection.prepareStatement(sql);

			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				Event event = new Event(rs.getString("title"), rs.getString("info_url"), rs.getInt("address_number"),
						rs.getString("street_name"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"),
						rs.getInt("number_helpers_needed"), rs.getString("event_image_url"));
				events.add(event);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			ConnectionManager.closeResources(prepedStmt, connection);
		}
		// return the list of Event objects populated by the DB.
		return events;
	}

	public boolean deleteEventByTitle(String title) {
		try {
			connection = ConnectionManager.getConnection();
			String sql = "DELETE from Event WHERE title=?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, title);
			System.out.println(title);
			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionManager.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public Event getEventByTitle(String title) {
		Event event = null;
		try {
			connection = ConnectionManager.getConnection();
			String sql = "SELECT * FROM Event WHERE title = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, title);
			ResultSet rs = prepedStmt.executeQuery();

			if (rs.next()) {
				event = new Event(rs.getString("title"), rs.getString("info_url"), rs.getInt("address_number"),
						rs.getString("street_name"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"),
						rs.getInt("number_helpers_needed"), rs.getString("event_image_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeResources(prepedStmt, connection);
		}
		return event;
	}

	@Override
	public boolean addPersonToEvent(int auto_id, String title) {
		try {
			connection = ConnectionManager.getConnection();
			String sql = "INSERT INTO event_person(person_auto_id_fkey, event_title_fkey) values(?, ?)"; 
			prepedStmt = connection.prepareStatement(sql);
			
			prepedStmt.setInt(1, auto_id);
			prepedStmt.setString(2, title);
			
			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionManager.closeResources(prepedStmt, connection);
		}		
	}
}
