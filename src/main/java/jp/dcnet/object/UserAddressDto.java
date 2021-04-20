package jp.dcnet.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserAddressDto {
	
	private String name;
	private String telephone;
	private String postcode;
	private String address;
	private String detaddress;

}
