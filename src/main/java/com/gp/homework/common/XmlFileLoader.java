package com.gp.homework.common;

import org.springframework.core.io.ClassPathResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStreamReader;

public class XmlFileLoader {

    /**
     *
     * @param filePath 相对路径  eg： String path = "xmlFile/StatusConfig_1280.xml" ;
     * @param clazzT xml文件需要转化的对象实体class
     * @param <T>  略
     * @return clazzT对应的对象，由xml转化而来
     */
    public static <T> T loadXmlFileToEntity(String filePath,Class<T> clazzT){

        try(InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(filePath).getInputStream())){
            return (T) JAXBContext.newInstance(clazzT).createUnmarshaller().unmarshal(inputStreamReader);
        }
        catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return null ;
    }

}
