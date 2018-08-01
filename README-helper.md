### 使用SQLiteOpenHelper工具类和事务处理
#### DBHelper.java
```
public class DBHelper extends SQLiteOpenHelper {
     public DBHelper(Context context, String name) {
         super(context, name, null, 1);
     }
     public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
         super(context, name, factory, version);
     }
     /**
      * 首次创建数据库的时候调用 一般把创建数据库，创建数据表的操作放在这里
      */
     @Override
     public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table if not exists stub(_id integer primary key autoincrement, name text not null , age integer not null)");
         db.execSQL("insert into stub(name , age) values('zhangsan' , 20)");
     }
     /**
      * 当数据库版本更新的时候会自动执行
      */
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     }
 }
```
#### Activity
```
public class MyActivity extends AppCompatActivity {
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         DBHelper dbHelper = new DBHelper(this, "my.db");
         /**
          * getReadableDatabase
          * getWritableDatabase
          * 创建或打开数据库，如果数据库不存在则创建数据库，如果数据库存在直接打开数据库，默认情况下两个函数都表示打开或者创建可读可写的数据库对象
          * 如果磁盘已满或者数据库本身权限等情况下getReadableDatabase打开的是只读数据库
          * */
         SQLiteDatabase database = dbHelper.getWritableDatabase();
         try {
             /**
              * 开启事务
              * */
             database.beginTransaction();
             ContentValues values = new ContentValues();
             values.put("name" , "sb");
             database.update("stub", values, "_id = ?", new String[]{"1"});
             values.clear();
             values.put("name", "caonima");
             database.update("stub", values, "_id = ?", new String[]{"1"});
             /**
              * 设置事务执行成功
              * */
             database.setTransactionSuccessful();
         }finally {
             /**
              * 关闭事务，同时提交，如果已经设置的事务执行成功，那么生效，否则，将会回滚不执行
              * */
             database.endTransaction();
         }
     }
 }
```
