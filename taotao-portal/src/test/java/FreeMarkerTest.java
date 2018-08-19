import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FreeMarkerTest {

    /*
    简单数据类型
     */
    @Test
    public void testFreeMarker() throws IOException, TemplateException {
        //创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //告诉Configuration对象模板文件存放的路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\IdeaProjects\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        //设置Configuration的默认字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //从Configuration对象中获得模板对象，需要制定一个模板文件的名字
        Template template = configuration.getTemplate("first.ftl");
        //创建模板需要的数据集，可以是map也可以是pojo，把模板需要的数据都放入数据集
        Map root = new HashMap<>();
        root.put("hello", "hello freemarker");
        //创建一个Writer对象，制定生成的文件保存的路径及文件名
        Writer out = new FileWriter(new File("D:\\temp\\html\\hello.html"));
        //调用模板对象的process方法生成静态文件
        template.process(root, out);
        //关闭writer对象
        out.flush();
        out.close();
    }

    public class Student
    {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /*
    包装数据类型
     */
    @Test
    public void testFreeMarker1() throws IOException, TemplateException {
        //创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //告诉Configuration对象模板文件存放的路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\IdeaProjects\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        //设置Configuration的默认字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //从Configuration对象中获得模板对象，需要制定一个模板文件的名字
        Template template = configuration.getTemplate("second.ftl");
        //创建模板需要的数据集，可以是map也可以是pojo，把模板需要的数据都放入数据集
        Map root = new HashMap<>();
        root.put("title", "hello freemarker");
        root.put("student", new Student(1,"张慧"));
        //创建一个Writer对象，制定生成的文件保存的路径及文件名
        Writer out = new FileWriter(new File("D:\\temp\\html\\hello.html"));
        //调用模板对象的process方法生成静态文件
        template.process(root, out);
        //关闭writer对象
        out.flush();
        out.close();
    }

    /*
    遍历
     */
    @Test
    public void testFreeMarker2() throws IOException, TemplateException {
        //创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //告诉Configuration对象模板文件存放的路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\IdeaProjects\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        //设置Configuration的默认字符集，一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //从Configuration对象中获得模板对象，需要制定一个模板文件的名字
        Template template = configuration.getTemplate("third.ftl");
        //创建模板需要的数据集，可以是map也可以是pojo，把模板需要的数据都放入数据集
        Map root = new HashMap<>();
        //root.put("title", "hello freemarker");
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "杭州"));
        list.add(new Student(2, "北京"));
        root.put("students", list);
        root.put("curDate", new Date());
        //创建一个Writer对象，制定生成的文件保存的路径及文件名
        Writer out = new FileWriter(new File("D:\\temp\\html\\hello.html"));
        //调用模板对象的process方法生成静态文件
        template.process(root, out);
        //关闭writer对象
        out.flush();
        out.close();
    }

}
