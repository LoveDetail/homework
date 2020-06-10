package com.elitel.license;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class LicenseThread extends Thread{


	  private long millis = 3600000L;

	  public LicenseThread(long millis) {
	    try { this.millis = millis;
	      init();
	    } catch (Exception localException) {
	    }
	  }

	  public LicenseThread() {
	    try {
	      init();
	    } catch (Exception localException) {
	    }
	  }

	  private void init() throws Exception {
		  LicenseUtil.isEveryTimeCheck = true;

	    String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

	    if (path.endsWith("!/BOOT-INF/classes!/")) {
	      String pathsplit1 = path.substring(0, path.lastIndexOf("!/BOOT-INF/classes!/"));
	      String pathpre = pathsplit1.substring(0, pathsplit1.lastIndexOf("/"));
	      if (pathpre.startsWith("file:/"))
	    	  LicenseUtil.licenseFilePath = pathpre.replace("file:/", "");
	      else
	    	  LicenseUtil.licenseFilePath = pathpre;
	    }
	    else
	    {
	    	LicenseUtil.licenseFilePath = path;
	    }
	    
	    System.out.println(String.format("授权认证文件路径为:%s", LicenseUtil.licenseFilePath));
//	    logger.error("授权认证文件路径为:" + LiUtil.licenseFilePath);
	  }

	  public void run()
	  {
	    try {
	      while (true) {
	        if (!LicenseUtil.IsLicensed()) {
//	          logger.error("机器认证失败，请联系管理员！！！\r\n机器码：" + LiUtil.computerSN());
	          System.out.println(String.format("机器认证失败，请联系管理员！！！\r\n机器码：:%s", LicenseUtil.computerSN()));
	          
	          String name = ManagementFactory.getRuntimeMXBean().getName();
	          String pid = name.split("@")[0];
	          System.out.println(name+"****************"+pid) ;
	          
	          TimeUnit.SECONDS.sleep(15);
	          
	          Runtime.getRuntime().exec("taskkill /F /PID " + pid);
	        }
	        sleep(this.millis);
	      }
	    } catch (Exception e) {
	      e.toString();
	    }
	  }
}
