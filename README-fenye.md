### 数据库分页加载
#### Activity
```
/* 数据库分页
 * select * from student limit 0 , 20; 0表示当前页码第一条数据的下标，20表示每页显示的数据条目
 */
public class PageActivity extends AppCompatActivity {
    List<Student> students;
    private int totalNum;//数据库中的总条目数
    private static final int PAGESIZE = 30;//每页展示的条目数量
    private int pageNum;//有多少页
    private SQLiteDatabase database;
    private int currentPage = 1;//当前页码
    private ListView listView;
    private boolean isPage;
    private MyBaseAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        MyDatabaseHelper helper = new MyDatabaseHelper(this);
        database = helper.getWritableDatabase();
        totalNum = getDataCount();
        pageNum = (int) Math.ceil(totalNum / (float) PAGESIZE);
        listView = (ListView) findViewById(R.id.list);
        students = new ArrayList<>();
        if (currentPage == 1) {
            students.addAll(getData(currentPage));
        }
        adapter = new MyBaseAdapter();
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
                    currentPage++;
                    if (currentPage <= pageNum) {
                        students.addAll(getData(currentPage));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isPage = firstVisibleItem + visibleItemCount == totalItemCount;
            }
        });
    }
     public int getDataCount() {
        Cursor c = database.rawQuery("select * from student", null);
        int dataCount = c.getCount();
        c.close();
        return dataCount;
    }
    private List<Student> getData(int currentPage) {
        List<Student> lists = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from student limit " + (currentPage - 1) * PAGESIZE + " , " + PAGESIZE, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                Student student = new Student(_id, name, age);
                lists.add(student);
            }
        }
        return lists;
    }
    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return students.size();
        }
        @Override
        public Object getItem(int position) {
            return students.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(PageActivity.this, R.layout.listview_item, null);
                viewHolder._id = convertView.findViewById(R.id.text1);
                viewHolder.name = convertView.findViewById(R.id.text2);
                viewHolder.age = convertView.findViewById(R.id.text3);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder._id.setText(students.get(position).get_id() + "");
            viewHolder.name.setText(students.get(position).getName());
            viewHolder.age.setText(students.get(position).getAge() + "");
            return convertView;
        }
    }
    class ViewHolder {
        TextView _id;
        TextView name;
        TextView age;
    }
}
```
#### Student 
```
public class Student {
    int _id;
    String name;
    int age;
    public Student(int _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   public int getAge() {
         return age;
   }
   public void setAge(int age) {
       this.age = age;
   }
}
```
![image](https://github.com/ningbaoqi/DataSave/blob/master/gif/pic-3.gif)
