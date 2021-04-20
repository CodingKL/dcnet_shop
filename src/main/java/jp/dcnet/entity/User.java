package jp.dcnet.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_tbl")
public class User {
	@Id
	private Integer id;
	private String username;
	private String email;
	private String password;
	private Timestamp dateTime;
	private String role;
	private Integer status;

}
