### 使用SimpleCursorAdapter
#### Acitivity
```
public class MyAdapterActivity extends AppCompatActivity {
     private ListView listView;
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.listview);
         listView = (ListView) findViewById(R.id.list);
         MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this);
         SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
         for (int i = 1; i < 30; i++) {
             writableDatabase.execSQL("insert into student(name , age) values('张三 " + i + " ', 18)");
         }
         Cursor c = writableDatabase.query("student", null, null, null, null, null, null);
         /**
          * 将cursor 添加到适配器中 SimpleCursorAdapter主键必须是_id
         * */
         SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listview_item, c, new String[]{"_id", "name", "age"}, new int[]{R.id.text1, R.id.text2, R.id.text3}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
         listView.setAdapter(adapter);
     }
 }
```
#### MyDatabaseHelper
```
public class MyDatabaseHelper extends SQLiteOpenHelper {
     public MyDatabaseHelper(Context context) {
         super(context, "my.db", null, 1);
     }
     public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
         super(context, name, factory, version);
     }
     @Override
     public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table if not exists student(_id integer primary key autoincrement , name text not null , age integer not null)");
     }
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     }
 }
```
