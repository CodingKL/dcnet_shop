package jp.dcnet.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShopCartKey  implements Serializable {


	private int codeId;

	private int  userId;

}
