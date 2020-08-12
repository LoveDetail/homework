package com.gp.homework.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.Reader;

/**
 * @program: uni_commu
 * @description:
 * @author:
 * @create: 2020-08-06
 **/
public class XmlUtil {

    /**
     * 将file类型的xml转换成对象
     */
    public static Object convertXmlFileToObject(Class clazz, Reader reader) throws JAXBException {
           return JAXBContext.newInstance(clazz).createUnmarshaller().unmarshal(reader);
    }
}