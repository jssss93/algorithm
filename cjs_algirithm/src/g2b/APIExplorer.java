package g2b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class APIExplorer {
	public static void main(String[] args) throws IOException {

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1230000/BidPublicInfoService/getBidPblancListInfoCnstwk"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=KZ%2FJez3NnaG5GczsRyhPw4JQj7evCIL5IXL%2FQKZVC5mnmrNUNW0%2BXjboeqbPS%2BkeFO0MEmRnbH4jqNsRrP%2BGfg%3D%3D"); /*
																																 * Service
																																 * Key
																																 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
				+ URLEncoder.encode(
						"KZ%2FJez3NnaG5GczsRyhPw4JQj7evCIL5IXL%2FQKZVC5mnmrNUNW0%2BXjboeqbPS%2BkeFO0MEmRnbH4jqNsRrP%2BGfg%3D%3D",
						"UTF-8")); /* 공공데이터포탈에서 받은 인증키 */
		urlBuilder.append("&" + URLEncoder.encode("inqryDiv", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /*
							 * 검색하고자하는 조회구분 1.등록일시, 2.입찰공고번호 3.변경일시 (등록일시로 검색시
							 * 방위사업청 입찰공고데이터의 등록일시는 방위사업청에서 제공하는 오픈API로 연계된
							 * 입찰공고정보가 조
							 */
		urlBuilder.append("&" + URLEncoder.encode("inqryBgnDt", "UTF-8") + "=" + URLEncoder.encode("201605010000",
				"UTF-8")); /* 검색하고자하는 등록일시 또는 변경일시 조회시작일시 */
		urlBuilder.append("&" + URLEncoder.encode("inqryEndDt", "UTF-8") + "=" + URLEncoder.encode("201605052359",
				"UTF-8")); /* 검색하고자하는 등록일시 또는 변경일시 조회종료일시 */
		urlBuilder.append("&" + URLEncoder.encode("bidNtceNo", "UTF-8") + "=" + URLEncoder.encode("20160430911",
				"UTF-8")); /* 검색하고자 하는 입찰공고번호 (조회구분 '2' 선택시 필수) */
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json",
				"UTF-8")); /* 오픈API 리턴 타입을 JSON으로 받고 싶을 경우 'json' 으로 지정 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		// System.out.println(sb.toString());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(sb.toString());
		String prettyJsonString = gson.toJson(je);
		System.out.println(prettyJsonString);
		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String jsonOutput = gson.toJson(sb.toString());

//		System.out.println(jsonOutput);

	}
}