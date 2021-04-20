package jp.dcnet.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
	private int id;
	private String image;
	private String name;
	private String description;
	private int quantity;
	private int price;
	private int status;
}
