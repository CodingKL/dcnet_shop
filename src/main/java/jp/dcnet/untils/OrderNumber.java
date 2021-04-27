package jp.dcnet.untils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumber {
	/**
	* 生成主键id
	* 时间+随机数
	* @return
	*/
	public static synchronized String generateUniqueKey() {
		Random random = new Random();
		// 随机数的量 自由定制，这是3位随机数
		Integer r = random.nextInt(9000) + 1000;

		// 返回  10位时间
		Long timeMillis = System.currentTimeMillis();

		// 返回  10位时间
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStr = sdf.format(new Date());

		// 13位毫秒+3位随机数
		///return  timeMillis + String.valueOf(r);
		// 17位时间+3位随机数
		return timeStr + r;
	}

}
