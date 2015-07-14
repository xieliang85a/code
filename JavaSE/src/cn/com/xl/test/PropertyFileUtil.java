package cn.com.xl.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.Resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;


public class PropertyFileUtil {
	private final static String DEFAULT_ENCODING = "UTF-8";
    private static Properties properties;
    public void loadProperties(String location) throws IOException{
       ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        InputStream is = resource.getInputStream();
        PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
        propertiesPersister.load(properties, new InputStreamReader(is, "UTF-8"));
    }
 
    // 获取属性值
    public static String get(String key) {
        String propertyValue = properties.getProperty(key);
        return propertyValue;
    }
    
    
    /**
     * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入.
     * 文件路径使用Spring Resource格式, 文件编码使用UTF-8.
     *
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    public static Properties loadProperties(String... resourcesPaths) throws IOException {
    	ResourceLoader resourceLoader = new DefaultResourceLoader();
        Properties props = new Properties();
        PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
        for (String location : resourcesPaths) {
            Resource resource = resourceLoader.getResource(location);
            InputStream is = resource.getInputStream();
            propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
        }
        return props;
    }
}