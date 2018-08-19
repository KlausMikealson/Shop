package utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient storageClient = null;

    public FastDFSClient(String conf) throws IOException, MyException {
        if(conf.contains("classpath:"))
        {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient(trackerServer, storageServer);
    }

    public String[] uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String[] result = storageClient.upload_file(fileName, extName, metas);
        return result;
    }
    public String[] uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }
    public String[] uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    public String[] uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
        String[] result = storageClient.upload_file(fileContent, extName, metas);
        return result;
    }
    public String[] uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }
    public String[] uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

}
