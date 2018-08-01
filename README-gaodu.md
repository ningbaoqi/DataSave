### SQLite数据库的高级用法：附着数据库与取消附着

|高级用法|说明|
|------|------|
|attach database 'second.db' as 'second'|将原来的数据库和second.db数据库附着在一起，这样查询的时候就会在两个数据库文件中操作|
|detach database 'second'|将附着的数据库second取消附着|

### SQLite数据库的高级用法：约束

|约束|说明|
|------|------|
|NOT NULL|非空|
|UNIQUE|唯一,这个字段的数据不能跟前面的字段数据冲突|
|PRIMARY KEY|主键|
|CHECK|检查|
|DEFAULT|默认值|

```
create table student(_id integer primary key autoincrement , age integer NOT NULL , fee DEFAULT 10.0 CHECK(fee < 10000))
```
### SQLite数据库的高级用法：方法

|方法|说明|
|------|------|
|group by|分组查询|
|having|约束条件|
|distinct|不允许重复|
|order by|排序条件|
|limit|限制输出条数|
|join，on|连接，将多个表连接起来|
|like , GLOB|模糊匹配，like是大小写不敏感，GLOB是大小写敏感|
|通配符|%表示前面有很多或一个或0个，_表示一个字符，多个或者0个或者一个，?表示一个；请注意like 和GLOB分别使用不同的通配符|
|in|在什么范围内，精确匹配，必须在括号中列出才能匹配|
|between|在什么范围内，区间匹配，只要在区间之内包含区间就匹配|

```
select distinct age from student group by _id having _id>20 order by _id asc/desc limit 0 ，5 
select * from student inner join xiaolei on student._id=xiaolei._id
select * from student where name like "%操你妈_"
select * from student where name GLOB "?操你妈*"
select * from student where age in (18 , 30)
select * from student where age between 18 and 30
```
### SQLite数据库的高级用法：聚合函数

|聚合函数|说明|
|------|------|
|avg(x)|求平均值，如果传递的x是字符串，字符串将会被当作0来处理|
|count(x`\|`*)|计数，如果是*表示有多少行，x表示非空的字段有多少行|
|group_concat(x[,y])|将字段x所有满足条件的内容组合成字符串,y表示分隔符|
|max(x)|最大的字段|
|min(x)|最小的字段|
|total(x)|对某个字段进行求和，当作浮点数进行计算，不会溢出|
|sum(x)|对某个字段进行求和，如果x全是整数，结果就是整数，需要关注是否溢出|

```
select avg(age) from student
```
### SQLite数据库的高级用法：核心函数

|核心函数|说明|
|------|------|
|last_insert_rowid()|返回最后插入的rowid|
|abs()|绝对值|
|length(x)|字的长度，而不是字节数的长度|
|lower(x)|输出字符串小写|
|nullif(x , y)|如果x ， y不一样返回x的值，如果完全一样返回null|
|like(x , y)|看y的字段的值是否符合x的模式匹配，如果符合返回1，如果不符合返回0|
|random()|返回随机整数|
|replace(X , Y , Z)|X这个字段，使用Z替换字段值Y|
|round(x[,y])|四舍五入，y表示保留小数位数|
|substr(x,y[,z])|输出子字符串，x字符串，y开始的索引，索引从1开始，z表示长度|
|typeof(x)|表示字段的类型|
|instr(x,y)|y在x字段中出现的第一个索引位置|

```
select typeof(age) from student
```
### SQLite数据库的高级用法：时间

```
select date('now' , '+2 days')//当前日期加两天
select date('now')//当前日期

select time('now')//当前时间
```
### SQLite数据库的高级用法：json

```
select json('["first":"test" , "second":"haha"]')//生成json
select json_object('a',2,'c',4)//生成json
select json_array(1,2,3,4)//生成json数据
select json_array_length('[1,2,3,4]')//显示json的长度
```
### SQLite数据库的高级用法：性能优化

|SQLite问题|说明|优化|
|------|-----|------|
|insert很慢|因为操作insert的时候，每次insert都会开启一个事务处理|将所有的insert语句通过显示事务方式，一次insert|

|参数调整|
|------|
|PRAGMA auto_vacuum=0`\|`1；对数据库文件大小进行压缩，删除数据时，将会把空间自动释放，0表示不开启，1表示开启，创建数据库时没有数据的情况下进行设置才能有效|
|PRAGMA cache_size = 2000;default_cache_size|缓存尺寸|
|PRAGMA page_size=bytes|设置每个页面有多少字节|
|PRAGMA synchronous=FULL`\|`NORMAL`\|`OFF|设置同步，防止数据丢失，一般很少用到，默认为OFF|
|PRAGMA temp_store=MEMORY`\|`FILE`\|`DEFAULT|默认存储|
|Transaction|优化大批量数据插入|

|索引|说明|
|------|------|
|索引的创建|需要大数据存储，并为经常存储的字段进行创建索引|
|索引的使用|需要触发索引|

```
create index i1 on student (age)//在表student的age字段上创建索引i1
EXPLAIN QUERY PLAN select * from student where age='20'//测试是否触发索引
```
