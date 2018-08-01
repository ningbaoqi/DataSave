### 手动拼接XML格式并保存到内存

```
public class XMLActivity extends AppCompatActivity {
     List<Message> smsList = new ArrayList<>();
 
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.xml);
         for (int i = 0; i < 10; i++) {
             Message msg = new Message("ab" + i, "cd" + i, "ef" + i, "gh" + i);
             smsList.add(msg);
         }
     }
 
     public void click(View v) throws Exception {
         StringBuffer buffer = lineSms();
         File file = new File("data/data/ningbaoqi.com.androidfilesystem/sms.xml");
         FileOutputStream output = new FileOutputStream(file);
         output.write(buffer.toString().getBytes());
         output.close();
     }
 
     private StringBuffer lineSms() {
         StringBuffer buffer = new StringBuffer();
         buffer.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
         buffer.append("<message>");
         for (Message msg : smsList) {
             buffer.append("<sms>");
             buffer.append("<body>");
             buffer.append(msg.getBody());
             buffer.append("</body>");
             buffer.append("<date>");
             buffer.append(msg.getData());
             buffer.append("</date>");
             buffer.append("<type>");
             buffer.append(msg.getType());
             buffer.append("</type>");
             buffer.append("</type>");
             buffer.append("<address>");
             buffer.append(msg.getAddress());
             buffer.append("</address>");
             buffer.append("</sms>");
         }
         buffer.append("</message>");
         return buffer;
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
