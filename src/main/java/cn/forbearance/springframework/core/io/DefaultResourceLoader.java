package cn.forbearance.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取资源的实现
 *
 * @author cristina
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            // Try to parse the location as a URL...
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            // No URL -> resolve as resource path.
            return new FileSystemResource(location);
        }
    }
}
