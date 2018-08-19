import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class SolrJTest {
    @Test
    public void testSolrJ() throws IOException, SolrServerException {
        //创建链接
        SolrServer solrServer = new HttpSolrServer("http://192.168.223.128:8080/solr");
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //添加域
        document.addField("id", "solrtest01");
        document.addField("item_title", "测试商品");
        document.addField("item_sell_point", "卖点");
        document.addField("item_image", "图片");
        //添加到索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    @Test
    public void testQuery() throws SolrServerException {
        //创建链接
        SolrServer solrServer = new HttpSolrServer("http://192.168.223.128:8080/solr");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocuments = response.getResults();
        for (SolrDocument solrDocument:solrDocuments)
        {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_image"));
        }
    }
}























