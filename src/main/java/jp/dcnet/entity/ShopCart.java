package jp.dcnet.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(ShopCartKey.class)
@NoArgsConstructor
@Table(name = "shop_cart")
public class ShopCart {

	@Id
	@Column(name = "code_id")
	private int codeId;
	@Id
	@Column(name = "user_id")
	private int  userId;
	private int quantity;
	private Timestamp date_time;
}
