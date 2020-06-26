package main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.dao.PersonDAO;
import main.java.model.Message;
import main.java.model.Person;
import main.java.model.Schedule;
import main.java.model.Supply;
import main.java.validator.PassHashValidator;
import main.java.web.ConnectionService;

public class PersonDAOImpl implements PersonDAO {

	Connection connection = null;
	PreparedStatement prepedStmt = null;

	@Override
	public boolean createPostPerson(Person person) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO person(first_name, last_name, role, seats_available, phone, email, facebook, address_number, street_name, unit, city, state, zip, profile_image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, person.getFirst_name());
			prepedStmt.setString(2, person.getLast_name());
			prepedStmt.setString(3, person.getRole());
			prepedStmt.setInt(4, person.getSeats_available());
			prepedStmt.setString(5, person.getPhone());
			prepedStmt.setString(6, person.getEmail());
			prepedStmt.setString(7, person.getFacebook());
			prepedStmt.setInt(8, person.getAddress_number());
			prepedStmt.setString(9, person.getStreet_name());
			prepedStmt.setString(10, person.getUnit());
			prepedStmt.setString(11, person.getCity());
			prepedStmt.setString(12, person.getState());
			prepedStmt.setInt(13, person.getZip());
			prepedStmt.setString(14, person.getProfile_image_url());

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
	public boolean createPostPersonRegister(Person person, String pass) {

		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO person(first_name, last_name, role, seats_available, phone, email, facebook, address_number, street_name, unit, city, state, zip, profile_image_url, pass) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, person.getFirst_name());
			prepedStmt.setString(2, person.getLast_name());
			prepedStmt.setString(3, person.getRole());
			prepedStmt.setInt(4, person.getSeats_available());
			prepedStmt.setString(5, person.getPhone());
			prepedStmt.setString(6, person.getEmail());
			prepedStmt.setString(7, person.getFacebook());
			prepedStmt.setInt(8, person.getAddress_number());
			prepedStmt.setString(9, person.getStreet_name());
			prepedStmt.setString(10, person.getUnit());
			prepedStmt.setString(11, person.getCity());
			prepedStmt.setString(12, person.getState());
			prepedStmt.setInt(13, person.getZip());
			prepedStmt.setString(14, person.getProfile_image_url());
			prepedStmt.setBytes(15, PassHashValidator.hashAPass(pass));

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
	public boolean createPostPersonSchedule(Schedule schedule) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "INSERT INTO schedule(from_hour, from_minute, from_month, from_day, from_year, to_hour, to_minute, to_month, to_day, to_year, available, person_auto_id_fkey) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			prepedStmt.setBoolean(11, schedule.isAvailable());
			prepedStmt.setInt(12, schedule.getPerson_auto_id_fkey());

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
	public Person readGetPersonLogin(String phone, String input_pass) {
		byte[] hashedPass = null;
		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM Person WHERE (phone = ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, phone);

			ResultSet rs = prepedStmt.executeQuery();

			if (rs.next()) {
				hashedPass = rs.getBytes("pass");
				if (hashedPass == null || hashedPass.length < 1) {
					System.out.println("user in db has no password \n\n\n\n");
					return null;
				} else if (PassHashValidator.isHashPassMatch(hashedPass, input_pass)) {
					return new Person(rs.getInt("person_auto_id"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("role"), rs.getInt("seats_available"),
							rs.getString("phone"), rs.getString("email"), rs.getString("facebook"),
							rs.getInt("address_number"), rs.getString("street_name"), rs.getString("unit"),
							rs.getString("city"), rs.getString("state"), rs.getInt("zip"),
							rs.getString("profile_image_url"));
				} else {

					// change all these return null to error handling that adds message to ui and
					// reloads a blank ui
					System.out.println("user phone and pass do not match");
					return null;
				}
			} else {
				System.out.println("user phone and pass not found in db");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
		return null;
	}

	@Override
	public Person readGetPerson(String phone) {
		Person person = null;

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM Person WHERE phone = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, phone);
			ResultSet rs = prepedStmt.executeQuery();

			if (rs.next()) {
				person = new Person(rs.getInt("person_auto_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("role"), rs.getInt("seats_available"), rs.getString("phone"),
						rs.getString("email"), rs.getString("facebook"), rs.getInt("address_number"),
						rs.getString("street_name"), rs.getString("unit"), rs.getString("city"), rs.getString("state"),
						rs.getInt("zip"), rs.getString("profile_image_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
		return person;
	}

	@Override
	public HashMap<Integer, Person> readGetPersonAll() {
		HashMap<Integer, Person> people = new HashMap<Integer, Person>();

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM Person";
			prepedStmt = connection.prepareStatement(sql);

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
	public ArrayList<Schedule> readGetPersonScheduleAll(int person_auto_id) {
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM schedule " + "WHERE person_auto_id_fkey = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setInt(1, person_auto_id);
			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				schedules.add(new Schedule(rs.getInt("schedule_auto_id"), rs.getInt("from_hour"),
						rs.getInt("from_minute"), rs.getInt("from_month"), rs.getInt("from_day"),
						rs.getInt("from_year"), rs.getInt("to_hour"), rs.getInt("to_minute"), rs.getInt("to_month"),
						rs.getInt("to_day"), rs.getInt("to_year"), rs.getBoolean("available"),
						rs.getInt("person_auto_id_fkey")));
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
		return schedules;
	}

	@Override
	public HashMap<Integer, Supply> readGetPersonSupplyAll(int responsible_person_auto_id_fkey) {
		HashMap<Integer, Supply> supplies = new HashMap<Integer, Supply>();

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM supply WHERE responsible_person_auto_id_fkey = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setInt(1, responsible_person_auto_id_fkey);
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
	public HashMap<Timestamp, ArrayList<Message>> readGetPersonMessageAllMessageThreadMatch(int person_auto_id_fkey) {
		HashMap<Timestamp, ArrayList<Message>> message_thread = new HashMap<>();

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM message WHERE person_auto_id_fkey = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setInt(1, person_auto_id_fkey);
			ResultSet rs = prepedStmt.executeQuery();

			while (rs.next()) {
				Message message = new Message(rs.getTimestamp("date_time"), rs.getString("text"),
						rs.getTimestamp("message_thread_date_time_started_fkey"), rs.getInt("person_auto_id_fkey"));

				Timestamp key = message.getMessage_thread_date_time_started_fkey();
				//creates a new array list if none exists at key and then it appends
				message_thread.computeIfAbsent(key, k -> new ArrayList<>()).add(message);
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
		return message_thread;
	}

	@Override
	public boolean updatePutPerson(Person person) {
		try {
			// in controller validator check the values comply before connecting..
			connection = ConnectionService.getConnection();
			String sql = "UPDATE Person SET first_name=?, last_name=?, role=?, seats_available=?, phone=?, email=?, facebook=?, address_number=?, street_name=?, unit=?, city=?, state=?, zip=?, profile_image_url=? WHERE person_auto_id=?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, person.getFirst_name());
			prepedStmt.setString(1, person.getLast_name());
			prepedStmt.setString(1, person.getRole());
			prepedStmt.setInt(1, person.getSeats_available());
			prepedStmt.setString(1, person.getPhone());
			prepedStmt.setString(1, person.getEmail());
			prepedStmt.setString(1, person.getFacebook());
			prepedStmt.setInt(1, person.getAddress_number());
			prepedStmt.setString(1, person.getStreet_name());
			prepedStmt.setString(1, person.getUnit());
			prepedStmt.setString(1, person.getCity());
			prepedStmt.setString(1, person.getState());
			prepedStmt.setInt(1, person.getZip());
			prepedStmt.setString(1, person.getProfile_image_url());
			// check if setInt works for postgresql type serial
			prepedStmt.setInt(1, person.getPerson_auto_id());

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
	public boolean updatePutPersonPass(String phone, String old_pass, String new_pass) {
		try {
			if (readGetPersonLogin(phone, old_pass) != null) {
				connection = ConnectionService.getConnection();
				String sql = "UPDATE Person SET pass= ? WHERE phone = ?";
				prepedStmt = connection.prepareStatement(sql);
				prepedStmt.setBytes(1, PassHashValidator.hashAPass(new_pass));
				prepedStmt.setString(2, phone);

				if (prepedStmt.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}

			} else {
				System.out.println("your old password does not match the db password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);

		}
		System.out.println("something else unexpected happened");
		return false;
	}

	@Override
	public boolean deleteDeletePerson(int person_auto_id) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "DELETE from Person WHERE person_auto_id=?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setInt(1, person_auto_id);
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
	public boolean deleteDeletePersonSchedule(int schedule_auto_id) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "DELETE from Schedule WHERE schedule_auto_id=?";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setInt(1, schedule_auto_id);
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
