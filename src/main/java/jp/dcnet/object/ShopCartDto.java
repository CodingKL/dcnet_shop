package jp.dcnet.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ShopCartDto {
	private int code_id;
	private String image;
	private String name;
	private int quantity;
	private int price;
	private int total;
}
