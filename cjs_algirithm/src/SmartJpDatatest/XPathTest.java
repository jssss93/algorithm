package SmartJpDatatest;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XPathTest {
	public static void main(String[] args) throws Exception {
		
//		<root>
//		    <row>
//		        <col1 id="c1">값1</col1>
//		        <col2 id="c2" val="val2">값2</col2>
//		    </row>
//		    <row>
//		        <col1 id="c3">값3</col1>
//		        <col2 id="c4">값4</col2>
//		    </row>
//		</root>


		
		
		String xml = "<root><row><col1 id='c1'>값1</col1><col2 id='c2' val='val2'>값2</col2></row>"
				+ "<row><col1 id='c3'>값3</col1><col2 id='c4'>값4</col2></row></root>";

		// XML Document 객체 생성
		InputSource is = new InputSource(new StringReader(xml));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

		// 인터넷 상의 XML 문서는 요렇게 생성하면 편리함.
		// Document document =
		// DocumentBuilderFactory.newInstance().newDocumentBuilder()
		// .parse("http://www.example.com/bracketCheck.xml");

		// xpath 생성
		XPath xpath = XPathFactory.newInstance().newXPath();

		// NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
		NodeList cols = (NodeList) xpath.evaluate("//row/col1", document, XPathConstants.NODESET);
		for (int idx = 0; idx < cols.getLength(); idx++) {
			System.out.println(cols.item(idx).getTextContent());
		}
		// 값1 값3 이 출력됨

		// id 가 c2 인 Node의 val attribute 값 가져오기
		Node col2 = (Node) xpath.evaluate("//*[@id='c2']", document, XPathConstants.NODE);
		System.out.println(col2.getAttributes().getNamedItem("val").getTextContent());
		// val2 출력

		// id 가 c3 인 Node 의 value 값 가져오기
		System.out.println(xpath.evaluate("//*[@id='c3']", document, XPathConstants.STRING));
		// 값3 출력
	}
}
