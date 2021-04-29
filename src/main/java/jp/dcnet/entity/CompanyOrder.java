package jp.dcnet.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CompanyOrder {

	@Id
	private int orderId;
	private String username;
	private String name;
	private int price;
	private int quantity;
	private int total;
	private int status;
	private String orderNumber;
	private Timestamp payTime;



}
