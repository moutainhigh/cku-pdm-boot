package com.ckunion.modules.utils.freemarker.handler;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DocumentHandler {
	private static DocumentHandler instance;
	// public Configuration configuration = null;
	public SimpleDateFormat sd = new SimpleDateFormat("yyMMdd_HHmm");
	private static String filePath = "D://MESFiles";

	private DocumentHandler() {
		// configuration = new Configuration();
		// configuration.setDefaultEncoding("UTF-8");
	}

	public static DocumentHandler getInstance() {
		// if(instance==null){
		// synchronized (DocumentHandler.class) {
		// if(instance==null){
		// instance=new DocumentHandler();
		// }
		// }
		//
		// }
		if (instance == null) {
			instance = new DocumentHandler();
		}
		return instance;
	}

	/**
	 * 
	 * @param FileName
	 *            加載的模板名
	 * @param dataMap
	 *            要填入模本的数据文件,
	 *            注意dataMap里存放的数据Key值要与模板中的参数相对应，也就是说模板中调用的动态参数名必须在此方法中声明
	 * @param filePath
	 *            输出文档路径及名称
	 */
	public static void createDoc(String FileName, Map<String, Object> dataMap,
			String filePath) {
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		// 这里我们的模板是放在com.havenliu.document.template包下面
		configuration.setClassForTemplateLoading(DocumentHandler.class,
				"/com/ckunion/modules/utils/freemarker/template");
		Template t = null;
		try {
			if (FileName.indexOf("/") > 0) {
				FileName = FileName.replace("/", "");
			}
			// test.ftl为要装载的模板
			t = configuration.getTemplate(FileName + ".xml");
			t.setEncoding("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		File outFile = new File(filePath);
		OutputStreamWriter outwriter = null;
		Writer out = null;
		try {
			outwriter = new OutputStreamWriter(new FileOutputStream(outFile),
					"UTF-8");
			out = new BufferedWriter(outwriter);
			t.process(dataMap, out);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				outwriter.flush();
				out.close();
				outwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 
	 * @param folder
	 *            目录名
	 * @param FileName
	 *            加載的模板名
	 * @param dataMap
	 *            要填入模本的数据文件,
	 *            注意dataMap里存放的数据Key值要与模板中的参数相对应，也就是说模板中调用的动态参数名必须在此方法中声明
	 * @param filePath
	 *            输出文档路径及名称
	 */
	public static void createDoc(String folder, String FileName,
			Map<String, Object> dataMap, String filePath) {
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		// 这里我们的模板是放在com.havenliu.document.template包下面
		configuration.setClassForTemplateLoading(DocumentHandler.class,
				"/com/ckunion/modules/utils/freemarker/template" + folder);
		Template t = null;
		try {
			/*
			 * if(FileName.indexOf("/")>0){ FileName = FileName.replace("/",
			 * ""); }
			 */
			// test.ftl为要装载的模板
			t = configuration.getTemplate(FileName + ".xml");
			t.setEncoding("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		File outFile = new File(filePath);
		OutputStreamWriter outwriter = null;
		Writer out = null;
		try {
			outwriter = new OutputStreamWriter(new FileOutputStream(outFile),
					"UTF-8");
			out = new BufferedWriter(outwriter);
			t.process(dataMap, out);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				outwriter.flush();
				out.close();
				outwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Object formatValue(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof String) {
			if ("null".equals((String) obj)) {
				return "";
			}
			String str = obj + "";
			str = str.replace("<", "&lt;");
			str = str.replace(">", "&gt;");
			return str;
		} else if (obj instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(obj);
		}
		return obj;
	}

	public static Object formatDate(Object obj, String pattern) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(obj);
		}
		return obj;
	}
	
	//获得图片的base64码
    @SuppressWarnings("deprecation")
    public static String getImageBase(String src) {
        if(src==null||src==""){
            return "";
        }
        File file = new File(src);
        if(!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;  
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (IOException e) {  
          e.printStackTrace();  
        } 
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

	/**
	 * 产品履历书生成目录是 /订单号/产品代号/产品编号
	 * 
	 * @param poCode
	 * @param poProductNo
	 * @param productCode
	 */
	public static String buildFile(String poCode, String poProductNo,
			String productCode) {
		String file_path = getFilePath() + File.separatorChar + "buildWord"
				+ File.separatorChar + poCode + File.separatorChar
				+ getPath(poProductNo);
		if (StringUtils.isNotBlank(productCode)) {
			file_path = file_path + File.separatorChar + getPath(productCode);
		}
		File file = new File(file_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}

	private static String getPath(String path) {
		if (path == null)
			return null;
		path = StringUtils.replace(path, "/", "@");
		path = StringUtils.replace(path, "<", "@");
		path = StringUtils.replace(path, ">", "@");

		return path;
	}

	/**
	 * 根据用户名，到数据库查找电子章，将图片转换成 BASE64,便于插入word
	 * 
	 * @param userName
	 * @return
	 */
	public String getImageStr(String userName) {
		String sql = "select w.attach as \"ATTACH\",t.actual_path  from Res_Worker w "
				+ "left join hr_user u on w.staffid=u.user_code "
				+ "left join Pro_Document_Attachment t on w.attach=t.actual_name "
				+ "where w.is_checker='Y' and w.worker_name='" + userName + "'";
		/*
		 * try { List<Map<String,Object>>
		 * list=getDataManager().queryForListMap(sql); if(list==null ||
		 * list.size()==0){ return ""; } Object
		 * fileName=list.get(0).get("ATTACH"); if(fileName==null) return "";
		 * //路径 FacesContext fc = FacesContext.getCurrentInstance();
		 * HttpServletRequest request = (HttpServletRequest)
		 * fc.getExternalContext().getRequest(); String path =
		 * request.getRealPath("/") +
		 * "\\ui\\prod\\prodResource\\hrusermanage\\images\\";
		 * path=path+fileName; if(!new File(path).exists()){ return ""; }
		 * InputStream in = null; byte[] data = null; try { in = new
		 * FileInputStream(path); data = new byte[in.available()];
		 * in.read(data); in.close(); } catch (IOException e) {
		 * e.printStackTrace(); } BASE64Encoder encoder = new BASE64Encoder();
		 * return encoder.encode(data); } catch (DataManagerException e1) {
		 * throw new ServiceException(e1); }
		 */
		return "";
	}

	/**
	 * 读取合格电子章，将图片转换成 BASE64,便于插入word
	 * 
	 * @param userName
	 * @return
	 */
	public String getImageHgStr() {
		// 路径
		/*
		 * FacesContext fc = FacesContext.getCurrentInstance();
		 * HttpServletRequest request = (HttpServletRequest)
		 * fc.getExternalContext().getRequest(); String path =
		 * request.getRealPath("/") + "\\resources\\images\\hg.png"; if(!new
		 * File(path).exists()){ return ""; } InputStream in = null; byte[] data
		 * = null; try { in = new FileInputStream(path); data = new
		 * byte[in.available()]; in.read(data); in.close(); } catch (IOException
		 * e) { e.printStackTrace(); } BASE64Encoder encoder = new
		 * BASE64Encoder(); return encoder.encode(data);
		 */
		return "";
	}

	/**
	 * @return the filePath
	 */
	public static String getFilePath() {
		/*
		 * if(filePath==null){ filePath=ParameterConfig.getInstance()
		 * .getParameterByCode("com.ckunion.fileupload.path") .getParamValue();
		 * }
		 */
		return filePath;
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		if(StringUtils.isBlank(src)) return " ";
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 注意dataMap里存放的数据Key值要与模板中的参数相对应，也就是说模板中调用的动态参数名必须在此方法中声明
	 * 
	 * @param dataMap
	 */
	// public abstract void getData(Map<String,Object> dataMap,int type);

	public static void main(String[] args) {
		// DocumentHandler dh=new DocumentHandler();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("test1", "哈哈哈");
		dataMap.put("test2", "wwwww");
		DocumentHandler.createDoc("A组检验报告", dataMap, filePath + "\\A组检验报告.xls");
	}

}
