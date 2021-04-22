package jp.dcnet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	private Integer codeId;
	private String image;
	private String name;
	private Integer price;
	private Integer quantity;
	private Integer total;

}
