package com.gp.homework.pattern.proxy.cglib;

import com.gp.homework.common.XmlFileLoader;
import com.gp.homework.config.xmlconfig.StatusConfig;

import java.util.Comparator;

public class MyTestJava {

	public static void main(String[] args) {
//		byte[] bytes = new byte[]{0,0,0x19,0x2C} ;
//		System.out.println(ByteUtil.bytesToFloat(bytes, false));
//
//		System.out.println(ByteUtil.bytesToInt(bytes, false));
//		System.out.println(Float.intBitsToFloat(6444));


		String path = "xmlFile/StatusConfig_1280.xml" ;

		StatusConfig config_= XmlFileLoader.loadXmlFileToEntity(path, StatusConfig.class) ;

		System.out.println(config_);

	}

}

class CompareObject implements Comparator<Integer>,Comparable{

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1-o2 > 0 ? 1 : -1;
	}

	@Override
	public int compareTo(Object o) {
		if(o == null) return 0 ;
		if (this.equals(o)) return 0 ;

		return this.hashCode() - o.hashCode();
	}
}
