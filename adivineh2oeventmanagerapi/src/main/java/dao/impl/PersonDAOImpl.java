package main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.dao.PersonDAO;
import main.java.model.Person;
import main.java.validator.PassHashValidator;
import main.java.web.ConnectionService;

public class PersonDAOImpl implements PersonDAO {

	Connection connection = null;
	PreparedStatement prepedStmt = null;

	public boolean isPhoneAndPassMatch(String phone, String input_pass) {
		byte[] hashedPass = null;
		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM Person WHERE (phone = ?)";
			prepedStmt = connection.prepareStatement(sql);

			prepedStmt.setString(1, phone);

			ResultSet rs = prepedStmt.executeQuery();

			if (rs.next()) {
				// just return the password for decrypting
				// only ever call this from bcrypt
				hashedPass = rs.getBytes("pass");
				if (hashedPass == null || hashedPass.length < 1){
					System.out.println("user in db has no password \n\n\n\n");
					return false;
				}
			} else {
				System.out.println("user phone and pass not found in db");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionService.closeResources(prepedStmt, connection);
		}
		return PassHashValidator.isHashPassMatch(hashedPass, input_pass);
	}

	public Person getPerson(String phone) {
		Person person = null;

		try {
			connection = ConnectionService.getConnection();
			String sql = "SELECT * FROM Person WHERE phone = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setString(1, phone);
			ResultSet rs = prepedStmt.executeQuery();

			if (rs.next()) {
				person = new Person(rs.getInt("auto_id"), rs.getString("first_name"), rs.getString("last_name"),
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

	public boolean updatePerson(Person person) {
		try {
			// in controller validator check the values comply before connecting..
			connection = ConnectionService.getConnection();
			String sql = "UPDATE Person SET first_name=?, last_name=?, role=?, seats_available=?, phone=?, email=?, facebook=?, address_number=?, street_name=?, unit=?, city=?, state=?, zip=?, profile_image_url=? WHERE auto_id=?";
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
			prepedStmt.setInt(1, person.getAuto_id());

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

	public boolean updatePersonPass(String phone, String new_pass) {
		try {
			connection = ConnectionService.getConnection();
			String sql = "UPDATE Person SET pass=? WHERE phone = ?";
			prepedStmt = connection.prepareStatement(sql);
			prepedStmt.setBytes(1, PassHashValidator.hashAPass(new_pass));
			prepedStmt.setString(2, phone);

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

}
