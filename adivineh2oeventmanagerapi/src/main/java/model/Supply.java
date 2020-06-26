package main.java.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Supply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int supply_auto_id;
	private String title;
	private int quantity;
	private String category;
	private String sub_category;
	private Timestamp expiration;
	private String status;
	private String event_title_fkey;
	private int responsible_person_auto_id_fkey;

	// no args for jersey post
	public Supply() {
	}

	// create
	public Supply(String title, int quantity, String category, String sub_category, Timestamp expiration, String status,
			String event_title_fkey, int responsible_person_auto_id_fkey) {
		this.title = title;
		this.quantity = quantity;
		this.category = category;
		this.sub_category = sub_category;
		this.expiration = expiration;
		this.status = status;
		this.event_title_fkey = event_title_fkey;
		this.responsible_person_auto_id_fkey = responsible_person_auto_id_fkey;
	}

	// get from db
	public Supply(int supply_auto_id, String title, int quantity, String category, String sub_category, Timestamp expiration,
			String status, String event_title_fkey, int responsible_person_auto_id_fkey) {
		this.supply_auto_id = supply_auto_id;
		this.title = title;
		this.quantity = quantity;
		this.category = category;
		this.sub_category = sub_category;
		this.expiration = expiration;
		this.status = status;
		this.event_title_fkey = event_title_fkey;
		this.responsible_person_auto_id_fkey = responsible_person_auto_id_fkey;
	}

	public int getSupply_auto_id() {
		return supply_auto_id;
	}

	public String getTitle() {
		return title;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getCategory() {
		return category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public Timestamp getExpiration() {
		return expiration;
	}

	public String getStatus() {
		return status;
	}

	public String getEvent_title_fkey() {
		return event_title_fkey;
	}

	public int getResponsible_person_auto_id_fkey() {
		return responsible_person_auto_id_fkey;
	}
}
