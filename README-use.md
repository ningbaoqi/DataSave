### 使用SQLite实例简介
#### Activity
```
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     Button create, table, insert_sql, insert_api, delete_sql, delete_api, update_sql, update_api, query_sql, query_api;
     private SQLiteDatabase database;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         create = (Button) findViewById(R.id.create);
         table = (Button) findViewById(R.id.table);
         insert_sql = (Button) findViewById(R.id.insert_sql);
         insert_api = (Button) findViewById(R.id.insert_api);
         delete_sql = (Button) findViewById(R.id.delete_sql);
         delete_api = (Button) findViewById(R.id.delete_api);
         update_sql = (Button) findViewById(R.id.update_sql);
         update_api = (Button) findViewById(R.id.update_api);
         query_sql = (Button) findViewById(R.id.query_sql);
         query_api = (Button) findViewById(R.id.query_api);
         create.setOnClickListener(this);
         table.setOnClickListener(this);
         insert_sql.setOnClickListener(this);
         insert_api.setOnClickListener(this);
         delete_sql.setOnClickListener(this);
         delete_api.setOnClickListener(this);
         update_sql.setOnClickListener(this);
         update_api.setOnClickListener(this);
         query_sql.setOnClickListener(this);
         query_api.setOnClickListener(this);
     }
 
     @Override
     public void onClick(View v) {
         switch (v.getId()) {
             case R.id.create:
                 database = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
                 break;
             case R.id.table:
                 database.execSQL("create table if not exists usertb(_id integer primary key autoincrement,name text not null,age integer not null, sex text nut null)");
                 break;
             case R.id.insert_sql:
                 database.execSQL("insert into usertb(name,sex,age) values('小名','女',18)");
                 database.execSQL("insert into usertb(name,sex,age) values(?,?,?)", new Object[]{"小名", "女", 13824744});
                 break;
             case R.id.insert_api:
                 ContentValues values = new ContentValues();
                 values.put("name", "sada");
                 values.put("sex", "nan");
                 values.put("age", 17);
                 database.insert("usertb", null, values);
                 break;
             case R.id.delete_sql:
                 database.execSQL("delete from usertb where sex = ?", new String[]{"nan"});
                 break;
             case R.id.delete_api:
                 database.delete("usertb", "_id = ? and name = ?", new String[]{"1", "小名"});
                 break;
             case R.id.update_sql:
                 database.execSQL("update usertb set age=? where name =?", new Object[]{15, "小名"});
                 break;
             case R.id.update_api:
                 ContentValues values1 = new ContentValues();
                 values1.put("name", "小黑");
                 database.update("usertb", values1, "name = ?", new String[]{"小名"});
                 break;
             case R.id.query_sql:
                 Cursor cursor = database.rawQuery("select * from usertb", null);
                 if (cursor != null) {
                     while (cursor.moveToNext()) {
                         String name = cursor.getString(cursor.getColumnIndex("name"));
                         Log.d("nbq", name);
                     }
                 }
                 cursor.close();
                 break;
             case R.id.query_api:
                 Cursor cursor1 = database.query("usertb", null, null, null, null, null, null);
                 if (cursor1 != null) {
                     while (cursor1.moveToNext()) {
                         String name = cursor1.getString(cursor1.getColumnIndex("name"));
                         Log.d("nbq", name);
                     }
                 }
                 cursor1.close();
                 break;
         }
     }
 }
```
