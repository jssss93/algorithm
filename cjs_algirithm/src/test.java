import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class test {
	public static void main(String args[]) throws UnsupportedEncodingException{
		String url = "/front/right/comm/logn_.do?id=unireger%27%3D%3Daalertlert%28146%29%2B%27er";
		
		System.out.println(url);
		System.out.println("30.0".replaceAll("\\.0", ""));
//		System.out.println(keyfilters(url));
	}

	public static String keyfilters(String originalValue) throws UnsupportedEncodingException {
		
		String s = originalValue;
		if (s != null) {
			
			s = URLDecoder.decode(s, "UTF-8");

			s = s.replaceAll("(?i)script", "");
			s = s.replaceAll("(?i)javascript", "");
			s = s.replaceAll("(?i)alert", "");
			s = s.replaceAll("(?i)onclick", "");
			s = s.replaceAll("(?i)onchange", "");
			s = s.replaceAll("(?i)accesskey", "");
			s = s.replaceAll("(?i)iframe", "");
			s = s.replaceAll("(?i)frameset", "");
			s = s.replaceAll("(?i)select", "");
			s = s.replaceAll("(?i)union", "");
			s = s.replaceAll("(?i)drop", "");
			s = s.replaceAll("<", "&lt;");
			s = s.replaceAll(">", "&gt;");
			s = s.replaceAll("\\(", "(");
			s = s.replaceAll("\\)", ")");
			// s = s.replaceAll("\'" , "&#39;");
			// s = s.replaceAll("\"" , "&quot;");
//			s = s.replaceAll("eval\\((.*)\\)", "");
//			s = s.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
//			s = s.replaceAll("[\\\"\\\'][\\s]*vbscript:(.*)[\\\"\\\']", "\"\"");
//			s = s.replaceAll("script", "");
//			s = s.replaceAll("onload", "no_onload");
//			s = s.replaceAll("expression", "no_expression");
//			s = s.replaceAll("onmouseover", "no_onmouseover");
//			s = s.replaceAll("onmouseout", "no_onmouseout");
//			s = s.replaceAll("onclick", "no_onclick");
//			s = s.replaceAll("<iframe", "<iframe");
//			s = s.replaceAll("<object", "<object");
//			s = s.replaceAll("<embed", "<embed");
//			s = s.replaceAll("document.cookie", "document.cookie");
			

			
		}
		return s;
	}

}
