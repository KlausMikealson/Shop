import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

public class TestFastDFS {
    @Test
    public void testUpload() throws IOException, MyException {
        //1、把FastDFS提供的jar包添加到pom.xml
        //2、初始化全局配置，加载配置文件
        ClientGlobal.init("D:\\IdeaProjects\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        //3、创建TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //4、创建TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、声明StorageServer
        StorageServer storageServer = null;
        //6、获得StorageClient
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //7、直接调用StorageClient的方法上传文件
        String[] strings = storageClient.upload_file("C:\\Users\\张慧\\Desktop\\4934501486627131.jpg", "jpg", null);
        for (String string:strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testFastDFSClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("D:\\IdeaProjects\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        String[] strings = fastDFSClient.uploadFile("C:\\Users\\张慧\\Desktop\\4934501486627131.jpg", "jpg");
        for (String string:strings) {
            System.out.println(string);
        }

    }
}
