### 对XML文件进行pull解析

```
public class XMLpullActivity extends AppCompatActivity {
     List<City> cityList;
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.xml);
     }
     public void click(View view) throws Exception {
         /**
          * 获取xml文件的xml pull解析器
          * */
         //XmlPullParser xmlPullParser = Xml.newPullParser();
         XmlPullParser xmlPullParser = getResources().getXml(R.xml.weacher);
         /**
          * 得到当前的解析事件，根据事件类型做相应的处理
          * */
         int type = xmlPullParser.getEventType();
         City city = null;
         /**
          * 如果当前的解析事件不是 XmlPullParser.END_DOCUMENT 说明解析还没有完成
          * */
         while (type != xmlPullParser.END_DOCUMENT) {
             switch (type) {
                 case XmlPullParser.START_TAG:
                     /**
                      * 获取当前结点的名字
                      * */
                     if ("weather".equals(xmlPullParser.getName())) {
                         cityList = new ArrayList<>();
                     } else if ("city".equals(xmlPullParser.getName())) {
                         /**
                          * 创建City的JavaBean对象
                          * */
                         city = new City();
                     } else if ("name".equals(xmlPullParser.getName())) {
                         /**
                          * 获取结点内的内容
                          * */
                         String name = xmlPullParser.nextText();
                         city.setName(name);
                     } else if ("temp".equals(xmlPullParser.getName())) {
                         String temp = xmlPullParser.nextText();
                         city.setTemp(temp);
                     } else if ("pm".equals(xmlPullParser.getName())) {
                         String pm = xmlPullParser.nextText();
                         city.setPm(pm);
                     }
                     break;
                case XmlPullParser.END_TAG:
                     if ("city".equals(xmlPullParser.getName())) {
                         cityList.add(city);
                     }
                     break;
             }
             /**
              * 获取下一个解析事件
             * */
             type = xmlPullParser.next();
         }
         for (City c : cityList) {
             Log.d("nbq", c.toString());
         }
     }
     class City {
         String name;
         String temp;
         String pm;
         public String getName() {
             return name;
         }
         public void setName(String name) {
             this.name = name;
         }
         public String getTemp() {
             return temp;
         }
         public void setTemp(String temp) {
             this.temp = temp;
         }
         public String getPm() {
             return pm;
         }
         public void setPm(String pm) {
            this.pm = pm;
         }
         @Override
         public String toString() {
             return "City{" +"name='" + name + '\'' +", temp='" + temp + '\'' +", pm='" + pm + '\'' +'}';
         }
     }
 }
```
#### res/xml/weather.xml
```
<?xml version="1.0" encoding="utf-8"?>
 <weather>
     <city>
         <name>北京</name>
         <temp>5。</temp>
         <pm>80</pm>
     </city>
     <city>
         <name>上海</name>
         <temp>-5。</temp>
         <pm>800</pm>
     </city>
     <city>
         <name>深圳</name>
         <temp>12。</temp>
         <pm>60</pm>
     </city>
 </weather>
```
