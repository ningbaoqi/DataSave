### 使用CursorAdapter
#### MyCursorAdapterActivity 
```
public class MyCursorAdapterActivity extends AppCompatActivity {
     /**
      * @param savedInstanceState
      */
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.listview);
         ListView listView = (ListView) findViewById(R.id.list);
         MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this);
         SQLiteDatabase database = databaseHelper.getWritableDatabase(); 
         Cursor cursor = database.query("student", null, null, null, null, null, null);
         MyAdapter adapter = new MyAdapter(this, cursor);
         listView.setAdapter(adapter);
     }
     class MyAdapter extends CursorAdapter {
         public MyAdapter(Context context, Cursor c) {
             super(context, c);
         }
         @Override
         public View newView(Context context, Cursor cursor, ViewGroup parent) {
             View view = View.inflate(context, R.layout.listview_item, null);
             return view;
         }
        @Override
         public void bindView(View view, Context context, Cursor cursor) {
             TextView text1 = view.findViewById(R.id.text1);
             TextView text2 = view.findViewById(R.id.text2);
             TextView text3 = view.findViewById(R.id.text3);
             text1.setText(cursor.getInt(cursor.getColumnIndex("_id")) + "");
             text2.setText(cursor.getString(cursor.getColumnIndex("name")));
             text3.setText(cursor.getInt(cursor.getColumnIndex("age")) + "");
         }
     }
 }
```
![image](https://github.com/ningbaoqi/DataSave/blob/master/gif/pic-2.gif)
