### 对XML文件进行SAX解析

```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String xmlData = null;
        parseDataWithSax(xmlData);
    }

    private void parseDataWithSax(String s) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        XMLReader reader = factory.newSAXParser().getXMLReader();
        MyHandler handler = new MyHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(new StringReader(s)));
    }
}

class MyHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    /**
     * 会获取节点中内容的时候调用，该方法可能会被调用多次，一些换行符也被当做内容解析出来，需要针对这种情况在代码中做好控制
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("id".equals(nodeName)) {//根据当前节点名判断将内容添加到哪一个StringBuidler对象中
            id.append(ch, start, length);
        } else if ("name".equals(nodeName)) {
            name.append(ch, start, length);
        } else if ("version".equals(nodeName)) {
            version.append(ch, start, length);
        }
    }

    /**
     * 会在完成整个xml解析的时候调用
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * 会在完成解析某个节点的时候调用，判断如果app节点已经解析完成，需要注意：目前id、version、name中都可能时包含回车符或换行符，因此在打印之前还需要调用trim方法，并且在打印完成后清空，以便不影响下一次内容的提取
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)) {
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    /**
     * 会在开始xml解析的时候调用，在该函数中进行初始化
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    /**
     * 会在开始解析某个节点的时候调用，其中localName参数记录了当前节点的名字，这里把他记录下来，接着在解析节点中具体内容的时候就会调用characters方法
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        nodeName = localName;//记录当前节点名
    }
}
```
