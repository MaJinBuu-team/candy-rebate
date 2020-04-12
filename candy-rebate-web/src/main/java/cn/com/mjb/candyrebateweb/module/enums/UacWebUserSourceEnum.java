package cn.com.mjb.candyrebateweb.module.enums;

import java.util.Arrays;
import java.util.List;


/**
 * The enum Uac user source enum.
 *
 * @author buu
 */
public enum UacWebUserSourceEnum {

	/**
	 * 注册
	 */
	REGISTER("REGISTER", "注册"),
	/**
	 * 后台新增
	 */
	INSERT("INSERT", "后台新增"),
	/**
	 * 后台导入
	 */
	IMPORT("IMPORT", "后台导入"),
	/**
	 * 手机
	 */
	APP("APP", "手机"),;

	/**
	 * The Key.
	 */
	String key;
	/**
	 * The Value.
	 */
	String value;

	UacWebUserSourceEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获取key获取value
	 *
	 * @param key key
	 *
	 * @return value value
	 */
	public static String getValue(String key) {
		for (UacWebUserSourceEnum ele : UacWebUserSourceEnum.values()) {
			if (key.equals(ele.getKey())) {
				return ele.getValue();
			}
		}
		return null;
	}

	/**
	 * 根据key获取该对象
	 *
	 * @param key key
	 *
	 * @return this enum
	 */
	public static UacWebUserSourceEnum getEnum(String key) {
		for (UacWebUserSourceEnum ele : UacWebUserSourceEnum.values()) {
			if (key.equals(ele.getKey())) {
				return ele;
			}
		}
		return null;
	}

	/**
	 * 获取List集合
	 *
	 * @return List list
	 */
	public static List<UacWebUserSourceEnum> getList() {
		return Arrays.asList(UacWebUserSourceEnum.values());
	}
}
