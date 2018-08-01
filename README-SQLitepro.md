### SQLite数据库简介
#### SQLite数据库的主要特点

|SQLite数据库的主要特点|
|------|
|轻量级，一个动态库，单文件|
|独立性，没有依赖，无需安装|
|隔离性，全部在一个文件夹中|
|跨平台，支持众多操作系统|
|多语言接口，支持众多编程语言|
|安全性，支持事务；通过数据库上的独占性和共享锁来实现独立事务处理，多个进程可以在同一时间从同一个数据库读取数据，但只有一个进程可以写入数据|

#### SQLite数据库的数据类型

|SQLite数据库的数据类型|
|------|
|SQLite支持`NULL`、`INTEGER`、`REAL`、`TEXT`、`BLOB`数据类型，依次代表：空值、整形值、浮点值、字符串值、二进制对象|
|当某个值插入到数据库时，SQLite将会检查它的类型，如果该类型与关联的列不匹配，SQLite则会尝试将该值转换成该列的类型，如果不能转换，则该值将作为本身的类型存储|

#### 使用SQLite数据库的关键类

|关键类|说明|
|------|------|
|SQLiteDatabase|提供了一些管理SQLite数据库的类，提供创建、删除、执行SQL命令，并执行其他常见的数据库管理任务的方法，每个程序的数据库名字是唯一的|
|SQLiteOpenHelper|SQLiteDatabase的帮助类，用于管理数据库的创建和版本更新，一般是建立一个类继承它，并重写onCreate和onUpgrade方法|

#### SQL常用语法

|常用语法|说明|
|------|------|
|drop table 表名|删除表|
|insert into 表名[字段，字段] values(值1,值2....)|向表中插入数据|
|insert into person(_id , ags) values(1 , 20)|向表中插入数据，带条件|
|insert into person values(2 , "sa" , 20)|向表中插入数据|
|update 表名 set 字段=新值 where 修改的条件|更新数据|
|update person set name="ls",age=20 where _id=1|更新数据|
|delete from tablename where 删除条件|删除数据|
|delete from persion where _id=2|删除数据|
|select 字段 from 表名 where 查询条件 group by 分组的字段 having 筛选条件 order by 排序字段|查询数据|
|select * from person|查询数据|
|select _id , name , from person|查询数据|
|select * from person where _id=1|查询数据|
|select * from person where _id<>1|查询_id不等与1的字段|
|select * from person _id=1 and ags >18|查询数据|
|select * from person where name like "%小%"|查询数据|
|select * from person where name like "_小%"|查询数据|
|select * from person where name is null|查询数据|
|select * from person where age between 18 and 30|查询18到30的数据|
