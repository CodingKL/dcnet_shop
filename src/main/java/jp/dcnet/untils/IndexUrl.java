package jp.dcnet.untils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndexUrl {
	//主頁
	public static final String INDEX_VIEW = "/index";
	//登錄頁面
	public static final String LOGIN_VIEW = "/index/login";
	//注冊頁面
	public static final String REGISTERED_VIEW = "/index/registered";
	//用戶中心
	public static final String INDEX_USER_CENTER = "/index/center";
	//用戶登出
	public static final String INDEX_LOGOUT = "/logout";
	//密码更改页面
	public static final String INDEX_USER_CENTER_CAHNGEPWD = "/index/changePwd";
	//密码更改提交地址
	public static final String INDEX_USER_CENTER_PWD = "/index/updatepwd";
	//用户信息页面地址
	public static final String INDEX_USER_CENTER_USERINFO = "/index/userInfo";
	//用户信息画面提交地址
	public static final String INDEX_USER_CENTER_USERINFOUP = "/index/userInfoup";

	public static final String INDEX_USER_CENTER_USERINFEDIT = "/index/userInfoEdit{id}";

	public static final String INDEX_USER_CENTER_USERINFUPEDIT = "/index/userInfoUpEdit{id}";

	public static final String INDEX_USER_CENTER_USERSAFE = "/index/safe";

	public static final String INDEX_USER_CENTER_USERCANCEL = "/index/cancel";

	public static final String INDEX_USER_CENTER_USERCANCELUP = "index/usercancel";

	public static final String INDEX_USER_CENTER_PRODUCTUP = "/index/productup";

	public static final String INDEX_USER_CENTER_PRODUCTUPLOAD = "/index/productupload";

	public static final String INDEX_USER_CENTER_PRODUCTDETAILED = "/index/productdetialed{id}";

	public static final String INDEX_USER_CENTER_PRODUCTDETAILEDD = "/index/productdetialedd{id}";

	public static final String INDEX_USER_CENTER_SHOPCART ="/index/shopcart";

	public static final String INDEX_USER_CENTER_SHOPCARTADD ="/index/shopcartadd{id}";

	public static final String INDEX_USER_CENTER_USERCONTORL="/index/usercontorl";

	public static final String INDEX_USER_CENTER_USERCONTORLUP="/index/usercontrolup{id}";

	public static final String INDEX_USER_CENTER_USERCONTORLDELETE="/index/usercontroldelete{id}";

	public static final String INDEX_USER_CENTER_PRODUCTCONTORL="/index/productcontorl";

	public static final String INDEX_USER_CENTER_ORDERCONTORL="/index/checkorder";

	public static final String INDEX_USER_CENTER_ORDERCHECK="/index/ordercheck";

	public static final String INDEX_USER_ORDER_PAY="/index/orderpay";

	public static final String INDEX_USER_ORDER_SETTLEMENTORDER="/index/settlementorder";

	public static final String INDEX_USER_CENTER_WAREHOUSECONTORL="/index/warehousecontorl";

	public static final String INDEX_USER_CENTER_UPCOMPANY="/index/upcompany";

	public static final String INDEX_USER_CENTER_PRODUCTDOWN="/index/productdown{id}";

	public static final String INDEX_USER_CENTER_COMPANYPRODUCTDOWN="/index/companyproductdown{id}";

	public static final String INDEX_USER_CENTER_PRODUCTUPED="/index/productup{id}";

	public static final String INDEX_USER_CENTER_COMPANYPRODUCTUPED="/index/companyproductup{id}";

	public static final String INDEX_USER_CENTER_USERPULLBACK="/index/userPullBlack{id}";

	public static final String INDEX_USER_CENTER_USERREMOVEBACKLIST="/index/userRemoveBlacklist{id}";

	public static final String INDEX_USER_CENTER_PRODUCTDELETE="/index/productDelete{id}";

	public static final String INDEX_USER_CENTER_PRODUCTCONTORLER="/index/productcontorler";
	//用户地址页面地址
	public static final String INDEX_USER_CENTER_USERADDRESS = "/index/address";
	//用户地址更改提交页面
	public static final String INDEX_USER_CENTER_USERADDRESSUP = "/index/addressup";
	//用戶收貨地址修改對應ID
	public static final String INDEX_USER_CENTER_ADDRESSMODIFY = "/index/addressmodify{id}";
	//用戶收貨地址更改提交地址
	public static final String INDEX_USER_CENTER_ADDRESSUPDATE = "/index/addressupdate{id}";
	//用戶收貨地址刪除對應ID
	public static final String INDEX_USER_CENTER_ADDRESSDELETE = "/index/addressdelete{id}";

	public static final String INDEX_USER_CENTER_ICONUPLOAD = "/imageUpload";

}
