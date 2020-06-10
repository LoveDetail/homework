package com.elitel.license;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LicenseUtil {
	private static String __ComputerSN = "";
	  private static String __LicCode = "";
	  private static String __LicSN = "";
	  public static Date licTm0 = new Date(new Date().getTime() - 3600000L);
	  public static Date licTm1 = new Date(new Date().getTime() + 3600000L);
	  public static boolean isLiced = false;
	  public static boolean isEveryTimeCheck = false;
	  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	  public static String licenseFilePath;
	  static int __checkstate = 0;

	  public static String computerSN()
	  {
	    return __ComputerSN;
	  }

	  public static Date DateFromString(String s, Date tm)
	  {
	    int len = s.length();
	    if (len < 8)
	      return tm;
	    int year = 1989;
	    int month = 1;
	    int day = 1;
	    try {
	      year = Integer.parseInt(s.substring(0, 4));
	      month = Integer.parseInt(s.substring(4, 6));
	      day = Integer.parseInt(s.substring(6, 8));
	    } catch (Exception e) {
	      return tm;
	    }
	    try
	    {
	      return new Date(year - 1900, month - 1, day); } catch (Exception e) {
	    }
	    return tm;
	  }

	  public static List<String> getSysBase(File file)
	  {
	    List<String> list = new ArrayList<>();

	    BufferedReader br = null;
	    try
	    {
	      br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
	      String readLine = br.readLine();
	      while (readLine != null) {
	        list.add(readLine);
	        readLine = br.readLine();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (br != null) {
	        try {
	          br.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    return list;
	  }

	  public static String GetComputerSN()
	  {
	    boolean bol = (__ComputerSN != null) && (!__ComputerSN.equals(""));
	    if ((bol) && (!isEveryTimeCheck))
	      return __ComputerSN;
	    if (!bol) {
	      __ComputerSN = getLocalSN();
	    }

	    String path = GetLicFilePath();
	    List<String> text = null;
	    File textfile = new File(path);
	    if (textfile.exists())
	      text = getSysBase(textfile);
	    if ((text == null) || (text.size() == 0))
	    {
	      WriteEmptyLic(path);
	      return __ComputerSN;
	    }
	    String computerSn = "";
	    String tm0 = "";
	    String tm1 = "";
	    String code = "";

	    for (String s1 : text) {
	      if ((s1 == null) && ("".equals(s1)))
	        continue;
	      String[] ss2 = s1.split(":");
	      if (ss2.length < 2)
	        continue;
	      String key = ss2[0];
	      String value = ss2[1];
	      if (key == "")
	        continue;
	      key = key.trim();
	      if (value != "")
	        value = value.trim();
	      if ((key == "licTm0") || (key.equals("licTm0")))
	        tm0 = value;
	      else if ((key == "licTm1") || (key.equals("licTm1")))
	        tm1 = value;
	      else if ((key == "SN") || (key.equals("SN")))
	        computerSn = value;
	      else if ((key == "LicSN") || (key.equals("LicSN")))
	        __LicSN = value;
	      else if ((key == "LicCode") || (key.equals("LicCode"))) {
	        code = value;
	      }
	    }
	    String lic = GenLicCode(computerSn, tm0, tm1, __LicSN);
	    if ((lic == code) || (lic.equals(code)))
	      isLiced = true;
	    __LicCode = code;
	    licTm0 = DateFromString(tm0, licTm0);
	    licTm1 = DateFromString(tm1, licTm1);
	    __ComputerSN = computerSn;

	    if (!isLiced)
	    {
	      WriteEmptyLic(path);
	    } else {
	      Thread t2 = new Thread() {
	        public void run() {
	        	LicenseUtil.WriteEmptyLic();
	        }
	      };
	      t2.start();
	    }

	    return __ComputerSN;
	  }

	  static String GetLicFilePath()
	  {
	    String licenserealFilePath = licenseFilePath + "\\syf.base.dll";
	    return licenserealFilePath;
	  }

	  static void WriteEmptyLic()
	  {
	    WriteEmptyLic(GetLicFilePath());
	  }

	  public static void WriteEmptyLic(String path)
	  {
	    if (path == "")
	      path = GetLicFilePath();
	    String sn = GenSN("");

	    sn = new StringBuffer(sn).insert(4, "-").toString();

	    String code0 = GenLicCode(sn, sdf.format(licTm0), sdf.format(licTm1), __LicSN);
	    if ((!sn.equals(__ComputerSN)) || (!code0.equals(__LicCode)))
	    {
	      __ComputerSN = sn;
	      __LicCode = code0;
	      String text = String.format("SN:%s\r\nlicTm0:%s\r\nlicTm1:%s\r\nLicSN:%s\r\nLicCode:%s", new Object[] { sn, "20140101", "20140105", __LicSN, "XXXX-XXXXXX-XXXXXX" });

	      writeAllText(path, text);
	      licTm1 = new Date(new Date(licTm0.getTime()).getTime() - 3600000L);
	    }
	  }

	  public static void writeAllText(String path, String content)
	  {
	    OutputStream os = null;
	    try {
	      os = new FileOutputStream(path);

	      byte[] bytes = content.getBytes();
	      os.write(bytes);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    finally {
	      if (os != null)
	        try {
	          os.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	    }
	  }

	  static String GenLicCode(String computer, String tm0, String tm1, String licsn)
	  {
	    __LicSN = licsn;
	    String lic = GenSN(tm0 + tm1 + computer);
	    lic = new StringBuffer(lic).insert(4, "-").toString();
	    return lic;
	  }

	  static String GenSN(String sn)
	  {
	    if (sn == "")
	    {
	    	LicenseUtil m = new LicenseUtil();
	      sn = m.GetCpuInfo();
	      sn = sn + m.GetHDid();
	      sn = sn.replace(" ", "");
	      sn = sn.replace(":", "");
	      sn = sn.replace("-", "");
	      sn = sn.toUpperCase();
	    }
	    int len = sn.length();
	    if (len < 11)
	      return sn;
	    char c0 = sn.charAt(0);
	    char c1 = sn.charAt(1);
	    String result = sn.substring(2, len);

	    c0 = (char)((c0 + c1) / 2);
	    while (c0 < 'A')
	    {
	      int c = c0;
	      c += 5;
	      c0 = (char)c;
	    }
	    while (c0 > 'Z')
	    {
	      int c = c0;
	      c -= 6;
	      c0 = (char)c;
	    }
	    result = result + c0;
	    return GenSN(result);
	  }

	  private String GetCpuInfo()
	  {
	    long start = System.currentTimeMillis();

	    String serial = "";
	    try {
	      Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });

	      process.getOutputStream().close();
	      Scanner sc = new Scanner(process.getInputStream());
	      String property = sc.next();
	      serial = sc.next();
	    }
	    catch (IOException localIOException) {
	    }
	    return serial;
	  }

	  private String GetHDid()
	  {
	    String line = "";
	    String HdSerial = "";
	    try
	    {
	      Process proces = Runtime.getRuntime().exec("cmd /c dir c:");

	      BufferedReader buffreader = new BufferedReader(new InputStreamReader(proces
	        .getInputStream(), "GB2312"));

	      while ((line = buffreader.readLine()) != null)
	      {
	        if (line.indexOf("卷的序列号是 ") == -1)
	          continue;
	        HdSerial = line.substring(line.indexOf("卷的序列号是 ") + "卷的序列号是 "
	          .length(), line.length());
	      }

	    }
	    catch (IOException localIOException)
	    {
	    }

	    return HdSerial.replace("-", "");
	  }

	  private static void CheckThread()
	  {
	    __checkstate = 1;
	    String code = GetComputerSN();
	    long a = new Date().getTime();
	    long b = licTm0.getTime();
	    long c = licTm1.getTime();
	    if (!isLiced) __checkstate = 4;
	    else if ((a >= licTm0.getTime()) && (a <= licTm1.getTime()))
	      __checkstate = 2;
	    else
	      __checkstate = 3;
	  }

	  public static LicenseState CheckLicense()
	  {
	    if ((isEveryTimeCheck) || (__checkstate == 0))
	    {
	      CheckThread();
	    }
	    if (__checkstate == 1) return LicenseState.Working;
	    if (__checkstate == 2) return LicenseState.Ok;
	    if (__checkstate == 3) return LicenseState.Expired;
	    return LicenseState.LicError;
	  }

	  public static boolean IsLicensed()
	  {
		  LicenseState s = CheckLicense();
	    if (s == LicenseState.Ok) return true;
	    if (s == LicenseState.NoInit) return true;
	    return s == LicenseState.Working;
	  }

	  static String getLocalSN()
	  {
	    String sn = GenSN("");
	    sn = new StringBuffer(sn).insert(4, "-").toString();
	    return sn;
	  }
}
