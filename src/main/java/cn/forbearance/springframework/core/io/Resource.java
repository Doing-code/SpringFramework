package cn.forbearance.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cristina
 */
public interface Resource {

    /**
     * 提供获取 InputStream 流的入口，操作不同的流文件 classpath、FileSystem、URL
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
