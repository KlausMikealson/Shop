/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-04-07 12:32:22 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>淘淘商城后台管理系统</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"js/jquery-easyui-1.4.1/themes/default/easyui.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"js/jquery-easyui-1.4.1/themes/icon.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/taotao.css\" />\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-easyui-1.4.1/jquery.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-easyui-1.4.1/jquery.easyui.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/common.js\"></script>\n");
      out.write("<style type=\"text/css\">\n");
      out.write("\t.content {\n");
      out.write("\t\tpadding: 10px 10px 10px 10px;\n");
      out.write("\t}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body class=\"easyui-layout\">\n");
      out.write("    <div data-options=\"region:'west',title:'菜单',split:true\" style=\"width:180px;\">\n");
      out.write("    \t<ul id=\"menu\" class=\"easyui-tree\" style=\"margin-top: 10px;margin-left: 5px;\">\n");
      out.write("         \t<li>\n");
      out.write("         \t\t<span>商品管理</span>\n");
      out.write("         \t\t<ul>\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'item-add'}\">新增商品</li>\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'item-list'}\">查询商品</li>\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'item-param-list'}\">规格参数</li>\n");
      out.write("\t         \t</ul>\n");
      out.write("         \t</li>\n");
      out.write("         \t<li>\n");
      out.write("         \t\t<span>网站内容管理</span>\n");
      out.write("         \t\t<ul>\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'content-category'}\">内容分类管理</li>\n");
      out.write("\t         \t\t<li data-options=\"attributes:{'url':'content'}\">内容管理</li>\n");
      out.write("\t         \t</ul>\n");
      out.write("         \t</li>\n");
      out.write("         </ul>\n");
      out.write("    </div>\n");
      out.write("    <div data-options=\"region:'center',title:''\">\n");
      out.write("    \t<div id=\"tabs\" class=\"easyui-tabs\">\n");
      out.write("\t\t    <div title=\"首页\" style=\"padding:20px;\">\n");
      out.write("\t\t        \t\n");
      out.write("\t\t    </div>\n");
      out.write("\t\t</div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("$(function(){\n");
      out.write("\t$('#menu').tree({\n");
      out.write("\t\tonClick: function(node){\n");
      out.write("\t\t\tif($('#menu').tree(\"isLeaf\",node.target)){\n");
      out.write("\t\t\t\tvar tabs = $(\"#tabs\");\n");
      out.write("\t\t\t\tvar tab = tabs.tabs(\"getTab\",node.text);\n");
      out.write("\t\t\t\tif(tab){\n");
      out.write("\t\t\t\t\ttabs.tabs(\"select\",node.text);\n");
      out.write("\t\t\t\t}else{\n");
      out.write("\t\t\t\t\ttabs.tabs('add',{\n");
      out.write("\t\t\t\t\t    title:node.text,\n");
      out.write("\t\t\t\t\t    href: node.attributes.url,\n");
      out.write("\t\t\t\t\t    closable:true,\n");
      out.write("\t\t\t\t\t    bodyCls:\"content\"\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("});\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
