package com.tencent.jstorm.daemon.logviewer.api;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencent.jstorm.ClojureClass;
import com.tencent.jstorm.daemon.logviewer.LogViewerUtils;
import com.tencent.jstorm.utils.CoreUtil;

/**
 * 
 * @author <a href="mailto:caofangkun@gmail.com">caokun</a>
 * @author <a href="mailto:xunzhang555@gmail.com">zhangxun</a>
 * @author <a href="mailto:gulele2003@qq.com">gulele</a>
 * @author <a href="mailto:darezhong@qq.com">liuyuzhong</a>
 * @author <a href="mailto:fuangguang@126.com">wangfangguang</a>
 * @ModifiedBy yuzhongliu
 * @ModifiedTime 3:20:46 PM Jul 12, 2016
 */
@ClojureClass(className = "backtype.storm.daemon.logviewer#search")
public class SearchServlet extends LogviewerServlet {

  private static final long serialVersionUID = 1L;
  private static Logger LOG = LoggerFactory.getLogger(SearchServlet.class);

  /**
   * We do not use servlet-response here, but do not remove it from the :keys
   * list, or this rule could stop working when an authentication filter is
   * configured.
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String file = req.getParameter("file");
      boolean isDaemon = "yes".equals(req.getParameter("is-daemon"));
      String search = req.getParameter("search-string");
      String numMatches = req.getParameter("num-matches");
      String startByteOffset = req.getParameter("start-byte-offset");
      String callback = req.getParameter("callback");
      String origin = req.getHeader("Origin");
      String user = LogViewerUtils.httpcredshandler.getUserName(req);
      String logRoot = getLogRoot();
      String daemonlogRoot = getDaemonlogRoot();
      String rootDir = isDaemon ? daemonlogRoot : logRoot;
      LogViewerUtils.searchLogFile(req, resp, URLDecoder.decode(file), user,
          rootDir, isDaemon, search, numMatches, startByteOffset, callback, origin);
    } catch (Exception e) {
      String errMsg = CoreUtil.stringifyError(e);
      LOG.error(errMsg);
      LogViewerUtils.ringResponseFromException(resp, errMsg);
    }
  }
}
