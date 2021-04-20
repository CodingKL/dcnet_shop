package jp.dcnet.object;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {

	private String usericon;
	private String username;
	private String name;
	private int sex;
	private Date birthday;
	private String address;
	private String hometown;
	private String email;


}
