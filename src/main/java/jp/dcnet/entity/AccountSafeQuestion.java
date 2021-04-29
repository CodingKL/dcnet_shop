package jp.dcnet.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="account_safety_question")
public class AccountSafeQuestion {

	@Id
	private int userId;
	private String userName;
	private String userEmail;
	private String questionOne;
	private String questionTow;
	private String questionThree;
	private Timestamp dataTime;



}
