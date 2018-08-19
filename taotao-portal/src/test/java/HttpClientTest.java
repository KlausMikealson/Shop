import org.apache.commons.codec.StringEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HttpClientTest {
    @Test
    public void testHttpGet() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet对象,需要制定一个请求的url
        HttpGet get = new HttpGet("http://www.baidu.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //接受返回结果，HttpEntity对象
        HttpEntity entity = response.getEntity();
        //取响应结果
        String html = EntityUtils.toString(entity);
        System.out.println("-------------------------------------------");
        System.out.println(html);
        //关闭response，HttpClient
        response.close();
        httpClient.close();
    }

    @Test
    public void testHttpPost() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpPost对象,需要制定一个请求的url
        HttpPost post = new HttpPost("http://localhost:8082/posttest.html");
        //创建一个list模拟表单，list中每个元素是一个NameValuePair对象
        List<NameValuePair> formList = new ArrayList<>();
        formList.add(new BasicNameValuePair("name", "张慧"));
        formList.add(new BasicNameValuePair("pass", "value2"));
        //把表单包装到Entity对象中，StringEntity
        StringEntity entity = new UrlEncodedFormEntity(formList,"utf-8");
        post.setEntity(entity);
        //执行请求
        CloseableHttpResponse response = httpClient.execute(post);
        //接受返回结果，HttpEntity对象
        HttpEntity httpEntity = response.getEntity();
        //取响应结果
        String result = EntityUtils.toString(httpEntity);
        System.out.println("-------------------------------------------");
        System.out.println(result);
        //关闭response，HttpClient
        response.close();
        httpClient.close();
    }
}
