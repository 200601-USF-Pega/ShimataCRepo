package main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.dao.EventDAO;
import main.java.model.Event;
import main.java.model.Message;
import main.java.model.MessageThread;
import main.java.model.Person;
import main.java.model.Schedule;
import main.java.model.Supply;
import main.java.web.ConnectionService;

public class EventDAOImpl implements EventDAO {

	Connection connection = null;
	PreparedStatement prepedStmt = null;

	@Override
	public boolean createPostEvent(Event event) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO event(\n"
					+ "	title, info_url, address_number, street_name, city, state, zip, number_helpers_needed, event_image_url)\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean createPostPersonToEvent(String title, int person_auto_id) {
		try {
			connection = ConnectionService.getConnection();
			// if check schedules don't cross use a stored procedure?
			String sql = "INSERT INTO event_person(person_auto_id_fkey, event_title_fkey) values(?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setInt(1, person_auto_id);
			prepedStmt.setString(2, title);

			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean createPostEventSupply(Supply supply) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO supply(\n"
					+ "	title, quantity, category, sub_category, expiration, status, event_title_fkey, responsible_person_auto_id_fkey)\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, supply.getTitle());
			prepedStmt.setInt(2, supply.getQuantity());
			prepedStmt.setString(3, supply.getCategory());
			prepedStmt.setString(4, supply.getSub_category());
			prepedStmt.setTimestamp(5, supply.getExpiration());
			prepedStmt.setString(6, supply.getStatus());
			prepedStmt.setString(7, supply.getEvent_title_fkey());
			prepedStmt.setInt(8, supply.getResponsible_person_auto_id_fkey());
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
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean createPostEventSchedule(Schedule schedule) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO schedule(from_hour, from_minute, from_month, from_day, from_year, to_hour, to_minute, to_month, to_day, to_year, event_title_fkey) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setInt(1, schedule.getFrom_hour());
			prepedStmt.setInt(2, schedule.getFrom_minute());
			prepedStmt.setInt(3, schedule.getFrom_month());
			prepedStmt.setInt(4, schedule.getFrom_day());
			prepedStmt.setInt(5, schedule.getFrom_year());
			prepedStmt.setInt(6, schedule.getTo_hour());
			prepedStmt.setInt(7, schedule.getTo_minute());
			prepedStmt.setInt(8, schedule.getTo_month());
			prepedStmt.setInt(9, schedule.getTo_day());
			prepedStmt.setInt(10, schedule.getTo_year());
			prepedStmt.setString(11, schedule.getEvent_title_fkey());

			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean createPostEventMessageThread(MessageThread message_thread) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO message_thread(date_time_started, " + "title, event_title_fkey) VALUES (?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setTimestamp(1, message_thread.getDate_time_started());
			prepedStmt.setString(2, message_thread.getTitle());
			prepedStmt.setString(3, message_thread.getEvent_title_fkey());
			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean createPostMessageThreadMessage(Message message) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO message(date_time, text, " + "message_thread_date_time_started_fkey, "
					+ "person_auto_id_fkey) VALUES (?, ?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setTimestamp(1, message.getDate_time());
			prepedStmt.setString(2, message.getText());
			prepedStmt.setTimestamp(3, message.getMessage_thread_date_time_started_fkey());
			prepedStmt.setInt(3, message.getPerson_auto_id_fkey());
			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public HashMap<String, Event> readGetEventAll() {
		HashMap<String, Event> events = new HashMap<String, Event>();

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM Event";
			prepedStmt = connection.prepareStatement(sql);

			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				Event event = new Event(rs.getString("title"), rs.getString("info_url"), rs.getInt("address_number"),
						rs.getString("street_name"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"),
						rs.getInt("number_helpers_needed"), rs.getString("event_image_url"));
				events.put(rs.getString("title"), event);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			ConnectionService.closeResources(prepedStmt, connection);
		}
		// return the list of Event objects populated by the DB.
		return events;
	}

	@Override
	public Event readGetEvent(String title) {
		Event event = null;
		try {
			connection = ConnectionService.getConnection();
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
			ConnectionService.closeResources(prepedStmt, connection);
		}
		return event;
	}

	@Override
	public Schedule readGetEventSchedule(String title) {
		Schedule schedule = null;
		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM schedule WHERE event_title_fkey = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, title);
			ResultSet rs = prepedStmt.executeQuery();

			if (rs.next()) {
				schedule = new Schedule(rs.getInt("schedule_auto_id"), rs.getInt("from_hour"), rs.getInt("from_minute"),
						rs.getInt("from_month"), rs.getInt("from_day"), rs.getInt("from_year"), rs.getInt("to_hour"),
						rs.getInt("to_minute"), rs.getInt("to_month"), rs.getInt("to_day"), rs.getInt("to_year"),
						rs.getString("event_title_fkey"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
		return schedule;
	}

	@Override
	public ArrayList<Message> readGetEventMessageThreadMessageAll(String title) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM message " + "WHERE message_thread_date_time_started_fkey = "
					+ "(SELECT date_time_started FROM message_thread " + "WHERE event_title_fkey = ?)";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, title);
			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				Message message = new Message(rs.getTimestamp("date_time"), rs.getString("text"),
						rs.getTimestamp("message_thread_date_time_started_fkey"), rs.getInt("person_auto_id_fkey"));
				messages.add(message);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			ConnectionService.closeResources(prepedStmt, connection);
		}
		// return the list of Event objects populated by the DB.
		return messages;
	}

	@Override
	public HashMap<Integer, Supply> readGetEventSupplyAll(String title) {
		HashMap<Integer, Supply> supplies = new HashMap<Integer, Supply>();

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM supply WHERE event_title_fkey = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, title);
			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				supplies.put(rs.getInt("supply_auto_id"),
						new Supply(rs.getInt("supply_auto_id"), rs.getString("title"), rs.getInt("quantity"),
								rs.getString("category"), rs.getString("sub_category"), rs.getTimestamp("expiration"),
								rs.getString("status"), rs.getString("event_title_fkey"),
								rs.getInt("responsible_person_auto_id_fkey")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			ConnectionService.closeResources(prepedStmt, connection);
		}
		// return the list of Event objects populated by the DB.
		return supplies;
	}

	@Override
	public HashMap<Integer, Person> readGetEventPersonAll(String title) {
		HashMap<Integer, Person> people = new HashMap<Integer, Person>();

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * \n" + "FROM event_person\n" + "INNER JOIN person\n"
					+ "ON event_person.person_auto_id_fkey = person.person_auto_id\n" + "WHERE event_title_fkey = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, title);
			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				people.put(rs.getInt("person_auto_id"),
						new Person(rs.getInt("person_auto_id"), rs.getString("first_name"), rs.getString("last_name"),
								rs.getString("role"), rs.getInt("seats_available"), rs.getString("phone"),
								rs.getString("email"), rs.getString("facebook"), rs.getInt("address_number"),
								rs.getString("street_name"), rs.getString("unit"), rs.getString("city"),
								rs.getString("state"), rs.getInt("zip"), rs.getString("profile_image_url")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			ConnectionService.closeResources(prepedStmt, connection);
		}
		// return the list of Event objects populated by the DB.
		return people;
	}

	@Override
	public boolean updatePutEvent(Event event, String original_title) {
		try {
			// in controller validator check the values comply before opening connection..
			// more abstraction.
			connection = ConnectionService.getConnection();
			String sql = "UPDATE Event SET title=?, info_url=?, " + "address_number=?, street_name=?, city=?, "
					+ "state=?, zip=?, number_helpers_needed=?, " + "event_image_url=? WHERE title=?";
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
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override

	public boolean updatePutEventSupply(Supply supply) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "UPDATE supply\n"
					+ "	SET title=?, quantity=?, category=?, sub_category=?, expiration=?, status=?, event_title_fkey=?, responsible_person_auto_id_fkey=?\n"
					+ "	WHERE supply_auto_id = ?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, supply.getTitle());
			prepedStmt.setInt(2, supply.getQuantity());
			prepedStmt.setString(3, supply.getCategory());
			prepedStmt.setString(4, supply.getSub_category());
			prepedStmt.setTimestamp(5, supply.getExpiration());
			prepedStmt.setString(6, supply.getStatus());
			prepedStmt.setString(7, supply.getEvent_title_fkey());
			prepedStmt.setInt(8, supply.getResponsible_person_auto_id_fkey());
			prepedStmt.setInt(9, supply.getSupply_auto_id());

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
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean updatePutEventSchedule(Schedule schedule) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "UPDATE public.schedule " + "SET from_hour=?, from_minute=?, from_month=?, "
					+ "from_day=?, from_year=?, to_hour=?, to_minute=?, to_month=?, to_day=?, to_year=?"
					+ "WHERE schedule_auto_id = ?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setInt(1, schedule.getFrom_hour());
			prepedStmt.setInt(2, schedule.getFrom_minute());
			prepedStmt.setInt(3, schedule.getFrom_month());
			prepedStmt.setInt(4, schedule.getFrom_day());
			prepedStmt.setInt(5, schedule.getFrom_year());
			prepedStmt.setInt(6, schedule.getTo_hour());
			prepedStmt.setInt(7, schedule.getTo_minute());
			prepedStmt.setInt(8, schedule.getTo_month());
			prepedStmt.setInt(9, schedule.getTo_day());
			prepedStmt.setInt(10, schedule.getTo_year());
			prepedStmt.setInt(11, schedule.getSchedule_auto_id());
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
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean deleteDeleteEvent(String title) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "DELETE from Event WHERE title=?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, title);
			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override

	public boolean deleteDeleteEventSupply(int supply_auto_id) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "DELETE from supply WHERE supply_auto_id=?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setInt(1, supply_auto_id);
			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}

	@Override
	public boolean deleteDeletePersonFromEvent(String event_title_fkey, int person_auto_id_fkey) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "DELETE from event_person WHERE event_title_fkey=? AND person_auto_id_fkey=?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, event_title_fkey);
			prepedStmt.setInt(1, person_auto_id_fkey);

			if (prepedStmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
	}
}
