package jp.dcnet.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RoleDto {

	private int id ;
	private String email;
	private String userName;
	private  String role;
	private int status;

}
