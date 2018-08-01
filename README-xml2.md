### 使用xml序列化器来生成xml文件

```
public class XMLSerializerActivity extends AppCompatActivity {
     List<Message> smsList = new ArrayList<>();
 
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.xml);
         for (int i = 0; i < 10; i++) {
             Message message = new Message("ab" + i, "cd" + i, "ef" + i, "gh" + i);
             smsList.add(message);
         }
     }
 
     /**
      * 使用xml序列化器生成xml文件
      */
     public void click(View view) throws Exception {
         XmlSerializer xmlSerializer = Xml.newSerializer();
         File file = new File("data/data/ningbaoqi.com.androidfilesystem/msm3.xml");
         FileOutputStream output = new FileOutputStream(file);
         xmlSerializer.setOutput(output, "UTF-8");//指定用什么编码生成xml文件
         xmlSerializer.startDocument("UTF-8", true);
         xmlSerializer.startTag(null, "message");
         for (Message sms : smsList) {
             xmlSerializer.startTag(null, "sms");
             xmlSerializer.startTag(null, "body");
             xmlSerializer.text(sms.getBody());
             xmlSerializer.endTag(null, "body");
             xmlSerializer.startTag(null, "date");
             xmlSerializer.text(sms.getData());
             xmlSerializer.endTag(null, "date");
             xmlSerializer.startTag(null, "address");
             xmlSerializer.text(sms.getAddress());
             xmlSerializer.endTag(null, "address");
             xmlSerializer.startTag(null, "type");
             xmlSerializer.text(sms.getType());
             xmlSerializer.endTag(null, "type");
             xmlSerializer.endTag(null, "sms");
         }
         xmlSerializer.endTag(null, "message");
         xmlSerializer.endDocument();
     }
 
     class Message {
         private String body;
         private String data;
         private String address;
         private String type;
 
         public Message(String body, String data, String address, String type) {
             this.body = body;
             this.data = data;
             this.address = address;
             this.type = type;
         }
 
         public String getBody() {
             return body;
         }
 
         public void setBody(String body) {
             this.body = body;
         }
 
         public String getData() {
             return data;
         }
 
         public void setData(String data) {
             this.data = data;
         }
 
         public String getAddress() {
             return address;
         }
 
        public void setAddress(String address) {
             this.address = address;
         }
 
         public String getType() {
             return type;
         }
 
         public void setType(String type) {
             this.type = type;
         }
     }
 }
```
