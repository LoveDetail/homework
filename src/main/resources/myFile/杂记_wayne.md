---
typora-root-url: pic
---



# 1、函数式编程 jdk1.8

`常见的函数式编程接口，基于JDK1.8`



| 序号 | 接口                    | 描述                                                         |
| ---- | ----------------------- | ------------------------------------------------------------ |
| 1    | BiConsumer<T,U>         | 代表了一个接受两个输入参数的操作，并且不返回任何结果         |
| 2    | BiFunction<T,U,R>       | 代表了一个接受两个输入参数的方法，并且返回一个结果           |
| 3    | BinaryOperator<T>       | 代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果 |
| 4    | BiPredicate<T,U>        | 代表了一个两个参数的boolean值方法                            |
| 5    | BooleanSupplier         | 代表了boolean值结果的提供方                                  |
| 6    | Consumer<T>             | 代表了接受一个输入参数并且无返回的操作                       |
| 7    | DoubleBinaryOperator    | 代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。 |
| 8    | DoubleConsumer          | 代表一个接受double值参数的操作，并且不返回结果。             |
| 9    | DoubleFunction<R>       | 代表接受一个double值参数的方法，并且返回结果                 |
| 10   | DoublePredicate         | 代表一个拥有double值参数的boolean值方法                      |
| 11   | DoubleSupplier          | 代表一个double值结构的提供方                                 |
| 12   | DoubleToIntFunction     | 接受一个double类型输入，返回一个int类型结果。                |
| 13   | DoubleToLongFunction    | 接受一个double类型输入，返回一个long类型结果                 |
| 14   | DoubleUnaryOperator     | 接受一个参数同为类型double,返回值类型也为double 。           |
| 15   | Function<T,R>           | 接受一个输入参数，返回一个结果。                             |
| 16   | IntBinaryOperator       | 接受两个参数同为类型int,返回值类型也为int 。                 |
| 17   | IntConsumer             | 接受一个int类型的输入参数，无返回值 。                       |
| 18   | IntFunction<R>          | 接受一个int类型输入参数，返回一个结果 。                     |
| 19   | IntPredicate            | 接受一个int输入参数，返回一个布尔值的结果。                  |
| 20   | IntSupplier             | 无参数，返回一个int类型结果。                                |
| 21   | IntToDoubleFunction     | 接受一个int类型输入，返回一个double类型结果 。               |
| 22   | IntToLongFunction       | 接受一个int类型输入，返回一个long类型结果。                  |
| 23   | IntUnaryOperator        | 接受一个参数同为类型int,返回值类型也为int 。                 |
| 24   | LongBinaryOperator      | 接受两个参数同为类型long,返回值类型也为long。                |
| 25   | LongConsumer            | 接受一个long类型的输入参数，无返回值。                       |
| 26   | LongFunction<R>         | 接受一个long类型输入参数，返回一个结果。                     |
| 27   | LongPredicate           | R接受一个long输入参数，返回一个布尔值类型结果。              |
| 28   | LongSupplier            | 无参数，返回一个结果long类型的值。                           |
| 29   | LongToDoubleFunction    | 接受一个long类型输入，返回一个double类型结果。               |
| 30   | LongToIntFunction       | 接受一个long类型输入，返回一个int类型结果。                  |
| 31   | LongUnaryOperator       | 接受一个参数同为类型long,返回值类型也为long。                |
| 32   | ObjDoubleConsumer<T>    | 接受一个object类型和一个double类型的输入参数，无返回值。     |
| 33   | ObjIntConsumer<T>       | 接受一个object类型和一个int类型的输入参数，无返回值。        |
| 34   | ObjLongConsumer<T>      | 接受一个object类型和一个long类型的输入参数，无返回值。       |
| 35   | Predicate<T>            | 接受一个输入参数，返回一个布尔值结果。                       |
| 36   | Supplier<T>             | 无参数，返回一个结果。                                       |
| 37   | ToDoubleBiFunction<T,U> | 接受两个输入参数，返回一个double类型结果                     |
| 38   | ToDoubleFunction<T>     | 接受一个输入参数，返回一个double类型结果                     |
| 39   | ToIntBiFunction<T,U>    | 接受两个输入参数，返回一个int类型结果。                      |
| 40   | ToIntFunction<T>        | 接受一个输入参数，返回一个int类型结果。                      |
| 41   | ToLongBiFunction<T,U>   | 接受两个输入参数，返回一个long类型结果。                     |
| 42   | ToLongFunction<T>       | 接受一个输入参数，返回一个long类型结果。                     |
| 43   | UnaryOperator<T>        | 接受一个参数为类型T,返回值类型也为T。                        |



# 2、Java 内部类

在Java中，可以将一个类定义在另一个类里面或者一个方法里面，这样的类称为内部类。广泛意义上的内部类一般来说包括这四种：`成员内部类`、`局部内部类`、`匿名内部类`和`静态内部类`。

##2.1 ` 成员内部类`

```java
class Circle {
    private double radius = 0;
    public static int count =1;
    public Circle(double radius) { this.radius = radius; }
  class Draw {     //内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
            System.out.println(count);   //外部类的静态成员
        }
    }
}
```

```php
成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）,当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员。如果要访问外部类的同名成员，需要以下面的形式进行访问：
     外部类.this.成员变量
	 外部类.this.成员方法
```

​	虽然成员内部类可以无条件地访问外部类的成员，而外部类想访问成员内部类的成员却不是这么随心所欲了。在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问

```java
class Circle {
    private double radius = 0;
 
    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawSahpe();   //必须先创建成员内部类的对象，再进行访问
    }
     
    private Draw getDrawInstance() {
        return new Draw();
    }
     
    class Draw {     //内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
        }
    }
}
```

成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。创建成员内部类对象的一般方式如下： 

```java
public class Test {
    public static void main(String[] args)  {
        //第一种方式：
        Outter outter = new Outter();
        Outter.Inner inner = outter.new Inner();  //必须通过Outter对象来创建
         
        //第二种方式：
        Outter.Inner inner1 = outter.getInnerInstance();
    }
}
 
class Outter {
    private Inner inner = null;
    public Outter() {}
     
    public Inner getInnerInstance() {
        if(inner == null)
            inner = new Inner();
        return inner;
    }
      
    class Inner {
        public Inner() {}
    }
}
```

```php
内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限。比如上面的例子，如果成员内部类Inner用private修饰，则只能在外部类的内部访问，如果用public修饰，则任何地方都能访问；如果用protected修饰，则只能在同一个包下或者继承外部类的情况下访问；如果是默认访问权限，则只能在同一个包下访问。这一点和外部类有一点不一样，外部类只能被public和包访问两种权限修饰。我个人是这么理解的，由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰。
```



##2.2 `局部内部类`

```php
	局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
    注意，局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。
```

```.java
class People{
    public People() {}
}
 
class Man{
    public Man(){}
     
    public People getWoman(){
        class Woman extends People{   //局部内部类
            int age =0;
        }
        return new Woman();
    }
}
```

## 2.3 `匿名内部类`

```.php
匿名内部类应该是平时我们编写代码时用得最多的，在编写事件监听的代码时使用匿名内部类不但方便，而且使代码更加容易维护。
```

```java
private void setListener(){
    scan_bt.setOnClickListener(new Listener1());       
    history_bt.setOnClickListener(new Listener2());
}
 
class Listener1 implements View.OnClickListener{
    @Override
    public void onClick(View v) {}
}
 
class Listener2 implements View.OnClickListener{
    @Override
    public void onClick(View v) {}
}
```

```php
	这种写法虽然能达到一样的效果，但是既冗长又难以维护，所以一般使用匿名内部类的方法来编写事件监听代码。同样的，匿名内部类也是不能有访问修饰符和static修饰符的。
　　匿名内部类是唯一一种没有构造器的类。正因为其没有构造器，所以匿名内部类的使用范围非常有限，大部分匿名内部类用于接口回调。匿名内部类在编译的时候由系统自动起名为Outter$1.class。一般来说，匿名内部类用于继承其他类或是实现接口，并不需要增加额外的方法，只是对继承方法的实现或是重写。
```



##2.4 `静态内部类`

​	静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static。静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，并且它不能使用外部类的非static成员变量或者方法，这点很好理解，因为在没有外部类的对象的情况下，可以创建静态内部类的对象，如果允许访问外部类的非static成员就会产生矛盾，因为外部类的非static成员必须依附于具体的对象。

```java
public class Test {
    public static void main(String[] args)  {
        Outter.Inner inner = new Outter.Inner();
    }
}
 
class Outter {
    public Outter() {}
     
    static class Inner {
        public Inner() {}
    }
}
```

---



##2.5 总结:

​	从前面可知，对于成员内部类，必须先产生外部类的实例化对象，才能产生内部类的实例化对象。而静态内部类不用产生外部类的实例化对象即可产生内部类的实例化对象:

```php
    创建静态内部类对象的一般形式为：  外部类类名.内部类类名 xxx = new 外部类类名.内部类类名()
　　创建成员内部类对象的一般形式为：  外部类类名.内部类类名 xxx = 外部类对象名.new 内部类类名()
```

​	最后补充一点知识：关于成员内部类的继承问题。一般来说，内部类是很少用来作为继承用的。但是当用来继承的话，要注意两点：

```php
    1）成员内部类的引用方式必须为 Outter.Inner.
　　2）构造器中必须有指向外部类对象的引用，并通过这个引用调用super()。这段代码摘自《Java编程思想》
```

```java
class WithInner {
    class Inner{}
}
class InheritInner extends WithInner.Inner {
    // InheritInner() 是不能通过编译的，一定要加上形参
    InheritInner(WithInner wi) {
        wi.super(); //必须有这句调用
    }
      public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner obj = new InheritInner(wi);
    }
}
```

---



#3、数据库相关 

##3.1 Sql语句的执行顺序

Mysql的内部模块来讲，Sql语句需要通过如下部分进行处理：

`连接器->解析器 ->优化器->执行器`

从关键字执行角度来讲，顺序如下：

```sql
SELECT omonm,a.addvcd,c.addvnm,unit,d.name,a.address,b.rvnm,b.bsnm,b.hnnm,b.stcd
FROM ioms_omo_b a INNER JOIN ioms_stbprp_b b ON a.omoid =b.omoid
INNER JOIN ioms_addvcd_s c ON a.addvcd = c.addvcd
INNER JOIN ioms_unit_b d ON a.unit = d.unitcd
WHERE a.omoid BETWEEN 0 AND 300
GROUP BY c.addvcd  HAVING a.addvcd > 0 ORDER BY a.omoid DESC
```

![1561867977627](C:\Users\sks\AppData\Local\Temp\1561867977627.png)



##3.2 Mysql 数据库迁移

```java
	线上mysql  用的版本是 5.7  由于 之前 安装 datadir 是默认安装， datadir  /var/lib/mysql，但是随着 mysql 的数据量的增大， /var 目录 磁盘有点比较紧张，已经用到了80%，因此需更改datadir 
```



3.2.1 mysql之前的配置

```java
root 292354 0.0 0.0 106224 1080 ? S Feb15 0:00 /bin/sh /usr/bin/mysqld_safe --datadir=/var/lib/mysql --socket=/var/lib/mysql/mysql.sock --pid-file=/var/run/mysqld/mysqld.pid --basedir=/usr --user=mysql
--------------------- 
mysql 292615 17.8 0.2 1757264 281552 ? Sl Feb15 41493:50 /usr/sbin/mysqld --basedir=/usr --datadir=/var/lib/mysql --plugin-dir=/usr/lib64/mysql/plugin --user=mysql --log-error=/var/log/mysqld.log --pid-file=/var/run/mysqld/mysqld.pid --socket=/var/lib/mysql/mysql.sock --port=3306 

```

3.2.2 把 read_only  设置成1   

```sql
show global variables like "%read_only%";
flush tables with read lock;
set global read_only=1;
show global variables like "%read_only%";
```

3.2.3 移动拷贝 

```shell
 cp  -rf /var/lib/mysql   /mnt/diskc/data/
 chown -R mysql.mysql   /mnt/diskc/data/mysql  
```



3.2.4  修改 启动脚本和  配置文件 的 datadir  

```shell
为保证MySQL能够正常工作，需要指明mysql.sock文件的产生位置。 修改socket=/var/lib/mysql/mysql.sock一行中等号右边的值为：/mnt/diskc/data/mysql/mysql.sock   操作如下： 
[client] 
socket = /mnt/diskc/data/mysql/mysql.sock 
default-character-set=utf8 
[mysqld] 
datadir=/mnt/diskc/data/mysql 
socket=/mnt/diskc/data/mysql/mysql.sock 
character-set-server=utf8
```



3.2.5 重启 mysqld  服务 和查看datadir

```shell
/etc/init.d/mysqld restart
```

```mysql
mysql> show  variables like '%datadir%';
+---------------+------------------------+
| Variable_name | Value                  |
+---------------+------------------------+
| datadir       | /mnt/diskc/data/mysql/ |
+---------------+------------------------+
```

3.2.6 查看 进程 

```shell
ps aux |grep mysql
```

```shell
root 245222 0.0 0.0 106324 1488 ? S Jul27 0:00 /bin/sh /usr/bin/mysqld_safe --datadir=/mnt/diskc/data/mysql --socket=/mnt/diskc/data/mysql/mysql.sock --pid-file=/var/run/mysqld/mysqld.pid --basedir=/usr --user=mysql 
---------------------
mysql 245533 12.4 0.6 13216988 817496 ? Sl Jul27 108:05 /usr/sbin/mysqld --basedir=/usr --datadir=/mnt/diskc/data/mysql --plugin-dir=/usr/lib64/mysql/plugin --user=mysql --log-error=/var/log/mysqld.log --pid-file=/var/run/mysqld/mysqld.pid --socket=/mnt/diskc/data/mysql/mysql.sock --port=3306 
```

3.2.7 mysql 将MySQL从只读设置为读写状态 

```mysql
unlock tables;
set global read_only=0;
```

```shell
总结：
这样就算是 更改了 mysql 的datadir  ，也是一次比较成功小小经验。 当然 可能 迁移的过程中，可能出现一些问题，这个时候就要看 错误日志，具体问题具体分析。
```

## 3.3Centos7 ，忘记密码

1. 停止msyql服务，`systemctl stop mysqld`

2. `whereis my.cnf`查看文件位置，然后 进入目录，然后`vim my.cnf`打开文件

3. 增加如下代码 ，表示跳过用户名验证`skip-grant-tables`

4. `mysql -uroot -p`登录，选择mysql数据库

5. 输入`update mysql.user set authentication_string=password('root_password') where user='root';`

6. 输入`FLUSH PRIVILEGES;`然后输入`exit`

7. 进入刚才的`my.cnf`文件，注释掉刚才`skip-grant-tables`

8. 重启mysql服务，`systemctl restart mysqld`

   

## 3.4Windows mysql路径变更

1 察看mysql数据库`SHOW VARIABLES LIKE '____dir%'`,确定现有的两个路径(basedir,datadir)，然后停止mysql服务；

2 建立一个目标文件夹，用来存放mysql服务器，数据库，和my.ini文件

3 存放数据库文件的文件夹要建立一个`Network Service`的用户(右键->属性->安全)，并授予权限

4 将原basedir复制到心得basedir路径下，同样将datadir复制到心得新的数据库文件夹下(感概建立了Network Service用户的文件夹)

5 修改原my.ini文件，将原basedir注释放开，并修改为新的路径，原datadir也修改为新的路径，同时将my.ini文件也复制到新的目录中(位置随意)

6 修改注册表配置`HKEY_LOCAL_MACHINE-->SYSTEM-->CurrentControlSet-->services`找到MySQL服务键，把 ImagePath 后面的原值

```java
"C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqld.exe" --defaults-file="C:\ProgramData\MySQL\MySQL Server 5.7\my.ini" MySQL57
```

```java
"D:\SJK_ALL\MySql_DB\MySQL Server 5.7\bin" --defaults-file="D:\SJK_ALL\MySql_DB\my.ini" MySQL57
```

7 在操作系统的服务列表中，重启mysql57的服务即可,然后用`SHOW VARIABLES LIKE '____dir%'`命令察看路径是否已经变成最新的路径；

## 3.5 Centos7 安装redis实例

1、下载redis
下载地址在：redis.io         比如把Redis安装到/usr/local/soft/

```.java
cd /usr/local/soft/
wget http://download.redis.io/releases/redis-5.0.5.tar.gz
```

2、解压缩

```.java
tar -zxvf redis-5.0.5.tar.gz
```

3、安装gcc依赖  Redis是C语言编写的，编译需要

```.java
yum install gcc
```

4、编译安装

```.java
cd redis-5.0.5
make MALLOC=libc
```

将/usr/local/redis-5.0.5/src目录下二进制文件安装到/usr/local/bin

```.java
cd src
make install
```

5、修改配置文件   默认的配置文件是/usr/local/redis-5.0.5/redis.conf

```.java
daemonize no   改成   daemonize yes

下面一行必须改成 bind 0.0.0.0 或注释，否则只能在本机访问  
bind 127.0.0.1 

如果需要密码访问，取消requirepass的注释
requirepass yourpassword
```

6、使用指定配置文件启动Redis（这个命令建议配置alias）

```.java
/usr/local/soft/redis-5.0.5/src/redis-server /usr/local/soft/redis-5.0.5/redis.conf
```

7、进入客户端（这个命令建议配置alias）

```.java
/usr/local/soft/redis-5.0.5/src/redis-cli
```

8、停止redis（在客户端中）

```.java
redis> shutdown 或    
ps -aux | grep redis
kill -9 xxxx
```





---

# 4、多线程知识

 ## 4.1 Object的wait和notify

1）使用wait()、notify()和notifyAll()时需要先对调用对象加锁。 

2）调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的 等待队列。 

3）notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或 notifAll()的线程释放锁之后，等待线程才有机会从wait()返回。

4）notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll() 方法则是将等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为 BLOCKED。 

5）从wait()方法返回的前提是获得了调用对象的锁。 



## 4.2 关于Piped的管道通讯机制

```java

```







# 5 、tomcat域名将解析配置





# 6、常见问题处理

## 6.1、APP登录异常

​		1 安卓系统，安装正常，使用一段时之后，偶见登录不上或者运行缓慢，使用者网络条件很好

​		2 Tomcat部署到客户服务器，通过IP访问正常，通过客户域名访问，不能加载js和css样式



# 7 操作系统知识

## 7.1 同步、异步、阻塞、非阻塞

### **7.1.1 IO操作:**

```.java
IO分两阶段（一旦拿到数据后就变成了数据操作，不再是IO）：
    1.数据准备阶段
    2.内核空间复制数据到用户进程缓冲区（用户空间）阶段

在操作系统中，程序运行的空间分为内核空间和用户空间。
    应用程序都是运行在用户空间的，所以它们能操作的数据也都在用户空间。


阻塞IO和非阻塞IO的区别在于第一步发起IO请求是否会被阻塞：
    如果阻塞直到完成那么就是传统的阻塞IO，如果不阻塞，那么就是非阻塞IO。

一般来讲：
    阻塞IO模型、非阻塞IO模型、IO复用模型(select/poll/epoll)、信号驱动IO模型都属于同步IO，因为阶段2是阻塞的(尽管时间很短)。

同步IO和异步IO的区别就在于第二个步骤是否阻塞：
    如果不阻塞，而是操作系统帮你做完IO操作再将结果返回给你，那么就是异步IO
    
```

![7.1.1_IO图](/../杂记_wayne/7.1.1_IO图.png)

### 7.1.2 同步和异步IO 阻塞和非阻塞IO

```.java
同步和异步IO的概念：
	同步是用户线程发起I/O请求后需要等待或者轮询内核I/O操作完成后才能继续执行;
	异步是用户线程发起I/O请求后仍继续执行，当内核I/O操作完成后通知用户线程，或调用用户线程注册的回调函数;

阻塞和非阻塞IO的概念：
	阻塞是指I/O操作需要彻底完成后才能返回用户空间;
	非阻塞是指I/O操作被调用后立即返回一个状态值，无需等I/O操作彻底完成;
```

![7.1.2_异步IO模型](/../杂记_wayne/7.1.2_异步IO模型.png)



![7.1.2_阻塞式IO模型](/7.1.2_阻塞式IO模型.png)

![7.1.2_非阻塞式IO模型](/../杂记_wayne/7.1.2_非阻塞式IO模型.png)

### 7.1.3 同步与异步（线程间调用）

```.java
同步与异步是对应于调用者与被调用者，它们是线程之间的关系，两个线程之间要么是同步的，要么是异步的

	同步操作时，调用者需要等待被调用者返回结果，才会进行下一步操作 ;
	而异步则相反，调用者不需要等待被调用者返回调用，即可进行下一步操作，被调用者通常依靠事件、回调等机制来通知调用者结果 ;

```

### 7.1.4 阻塞与非阻塞（线程内调用）

```.java
阻塞与非阻塞是对同一个线程来说的，在某个时刻，线程要么处于阻塞，要么处于非阻塞

	阻塞和非阻塞关注的是程序在等待调用结果（消息，返回值）时的状态;
    阻塞调用是指调用结果返回之前，当前线程会被挂起。调用线程只有在得到结果之后才会返回;
    非阻塞调用指在不能立刻得到结果之前，该调用不会阻塞当前线程;
```



### 7.1.5 同步与异步调用/线程/通信

```.java
同步就是两种东西通过一种机制实现步调一致，异步是两种东西不必步调一致

一、同步调用与异步调用：
    在用在调用场景中，无非是对调用结果的不同处理。
    同步调用就是调用一但返回，就能知道结果，而异步是返回时不一定知道结果，还得通过其他机制来获知结果，如：
        a. 状态 b. 通知 c. 回调函数

二、同步线程与异步线程：
    同步线程：即两个线程步调要一致，其中一个线程可能要阻塞等待另外一个线程的运行，要相互协商。快的阻塞一下等到慢的步调一致。
    异步线程：步调不用一致，各自按各自的步调运行，不受另一个线程的影响。

三、同步通信与异步通信：
    同步和异步是指：发送方和接收方是否协调步调一致
    同步通信是指：发送方和接收方通过一定机制，实现收发步调协调。
        如：发送方发出数据后，等接收方发回响应以后才发下一个数据包的通讯方式
    异步通信是指：发送方的发送不管接收方的接收状态。
        如：发送方发出数据后，不等接收方发回响应，接着发送下个数据包的通讯方式。

阻塞可以是实现同步的一种手段！例如两个东西需要同步，一旦出现不同步情况，我就阻塞快的一方，使双方达到同步。
同步是两个对象之间的关系，而阻塞是一个对象的状态。
```



### 17.1.6 四种组合方式

```.java
同步阻塞方式(阿呆等水开)：
    发送方发送请求之后一直等待响应。
    接收方处理请求时进行的IO操作如果不能马上等到返回结果，就一直等到返回结果后，才响应发送方，期间不能进行其他工作。

同步非阻塞方式（阿呆时不时看水是否烧开）：
	发送方发送请求之后，一直等待响应。
	接受方处理请求时进行的IO操作如果不能马上的得到结果，就立即返回，取做其他事情。
	但是由于没有得到请求处理结果，不响应发送方，发送方一直等待。
	当IO操作完成以后，将完成状态和结果通知接收方，接收方再响应发送方，发送方才进入下一次请求过程。（实际不应用）

异步阻塞方式（阿呆等着水壶响）：
	发送方向接收方请求后，不等待响应，可以继续其他工作。
	接收方处理请求时进行IO操作如果不能马上得到结果，就一直等到返回结果后，才响应发送方，期间不能进行其他操作。 （实际不应用）

异步非阻塞方式(阿呆等待水壶响)：
	发送方向接收方请求后，不等待响应，可以继续其他工作。
	接收方处理请求时进行IO操作如果不能马上得到结果，也不等待，而是马上返回去做其他事情。
	当IO操作完成以后，将完成状态和结果通知接收方，接收方再响应发送方。（效率最高）
```



# 8 神注释

##8.1 键盘

```java
/***
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
 * │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
 * │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
 */
```



## 8.2 主键盘

```java
 /***
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐
 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │
 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤
 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │
 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤
 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │
 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤
 * │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │
 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤
 * │ Ctrl│ Win│Alt │         Space         │ Alt│ Prt│    │Ctrl│
 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘
 */
```



## 8.3 佛祖保佑

```CQL
/***
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          佛祖保佑             永无BUG
 */
```



##8.4 佛无语

```java
/***
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 *  O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 *          .............................................
 *           佛曰：bug泛滥，我已瘫痪！
 */
```

##8.5 Fuck Bug

```java
/***
 *
 *   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 *  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 *  ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 *  ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 *           ░     ░ ░      ░  ░
 */
```

##8.6 飞龙神兽

```javascript
/***
 *                  ___====-_  _-====___
 *            _--^^^#####//      \\#####^^^--_
 *         _-^##########// (    ) \\##########^-_
 *        -############//  |\^^/|  \\############-
 *      _/############//   (@::@)   \\############\_
 *     /#############((     \\//     ))#############\
 *    -###############\\    (oo)    //###############-
 *   -#################\\  / VV \  //#################-
 *  -###################\\/      \//###################-
 * _#/|##########/\######(   /\   )######/\##########|\#_
 * |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
 * `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
 *    `   `  `      `   / | |  | | \   '      '  '   '
 *                     (  | |  | |  )
 *                    __\ | |  | | /__
 *                   (vvv(VVV)(VVV)vvv)                
 *                        神兽保佑
 *                       代码无BUG!
 */
```



## 8.7 飞龙神兽II

```java
/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 */
```

##8.8 看你妹源码

```c
/***
 *                                         ,s555SB@@&                          
 *                                      :9H####@@@@@Xi                        
 *                                     1@@@@@@@@@@@@@@8                       
 *                                   ,8@@@@@@@@@B@@@@@@8                      
 *                                  :B@@@@X3hi8Bs;B@@@@@Ah,                   
 *             ,8i                  r@@@B:     1S ,M@@@@@@#8;                 
 *            1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S                
 *            1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5               
 *            ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A               
 *             1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1               
 *              sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1                
 *               .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1                  
 *               s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981               
 *               ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;          
 *                .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS        
 *                s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,      
 *              :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,     
 *            .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8     
 *          :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:    
 *      s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r    
 *   ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i    
 *  5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;    
 *  9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:    
 *   SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5    
 *     ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B    
 *           ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S   
 *              ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#:  
 *            .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
 *
 *
 *    又看源码，看你妹妹呀！
 */
```

##8.9 不文明

```java
/***
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 */
```

##8.10 羊驼

```R
/***
 *      ┌─┐       ┌─┐
 *   ┌──┘ ┴───────┘ ┴──┐
 *   │                 │
 *   │       ───       │
 *   │  ─┬┘       └┬─  │
 *   │                 │
 *   │       ─┴─       │
 *   │                 │
 *   └───┐         ┌───┘
 *       │         │
 *       │         │
 *       │         │
 *       │         └──────────────┐
 *       │                        │
 *       │                        ├─┐
 *       │                        ┌─┘
 *       │                        │
 *       └─┐  ┐  ┌───────┬──┐  ┌──┘
 *         │ ─┤ ─┤       │ ─┤ ─┤
 *         └──┴──┘       └──┴──┘
 *                神兽保佑
 *               代码无BUG!
 */
```

##8.11 "海盗"

```R
/***
 **************************************************************
 *                                                            *
 *   .=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.       *
 *    |                     ______                     |      *
 *    |                  .-"      "-.                  |      *
 *    |                 /            \                 |      *
 *    |     _          |              |          _     |      *
 *    |    ( \         |,  .-.  .-.  ,|         / )    |      *
 *    |     > "=._     | )(__/  \__)( |     _.=" <     |      *
 *    |    (_/"=._"=._ |/     /\     \| _.="_.="\_)    |      *
 *    |           "=._"(_     ^^     _)"_.="           |      *
 *    |               "=\__|IIIIII|__/="               |      *
 *    |              _.="| \IIIIII/ |"=._              |      *
 *    |    _     _.="_.="\          /"=._"=._     _    |      *
 *    |   ( \_.="_.="     `--------`     "=._"=._/ )   |      *
 *    |    > _.="                            "=._ <    |      *
 *    |   (_/                                    \_)   |      *
 *    |                                                |      *
 *    '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-='      *
 *                                                            *
 *           LASCIATE OGNI SPERANZA, VOI CH'ENTRATE           *
 **************************************************************
 */
```

## 8.12 好有爱

```java
/***
 *************************************************************** 
 *                 /  \
 *            /'. /   |  Some bunny loves you!
 *           ||'.\|   |
 *           ||  \\   /      /\     __     /^\/^\
 *            \\  \\'```'-._ ; |   /\ \    \    /
 *             \'./`  __    `P | _/ /\_|    `\/`
 *              \         .__|' ` -.|
 *              |           ,'       \         /^\/^\
 *              \          .| -  -   |         \    /
 *               \____,..-`  \ _Y_ __/          `\/`
 *              / /       `---'"""`  `\
 *              \|   .           __.._/
 *               |    '-.__.-""``.-./ |\
 *              |        (  _.'`  |\ ||
 *             .-|         ``      || ||
 *            |  ;                 || //
 *        jgs  '-'\                //`
 *                 `"""""""""`"""""`
**************************************************************
 */
```

## 8.13 小窝

```java
/***
 ***************************************************************
               .-. .-.
              (   `   )
               `.   .'
                 `.'
              .-.   .-.
        (`.__(. .) (. .)__.')
        (      V \ / V      )
  ()     \/   )  / \  (   \/      ()
<)-`\()  / _.'_.'   `._`._ \   ()/'-(>
  <)_>== `'==>>=======<<==`'====<_(>
 <>-'`(>                      <)'`-<>
**************************************************************
 */
```

## 8.14 Windows

```java
                 __
            ,-~¨^  ^¨-,           _,
           /          / ;^-._...,¨/
          /          / /         /
         /          / /         /
        /          / /         /
       /,.-:''-,_ / /         /
       _,.-:--._ ^ ^:-._ __../
     /^         / /¨:.._¨__.;
    /          / /      ^  /
   /          / /         /
  /          / /         /
 /_,.--:^-._/ /         /
^            ^¨¨-.___.:^  (R) - G33K
```

## 8.15 Love you Baby

```java
/***
 ***************************************************************
                    Love you Baby              .,,,.
                      _======================.', ',''===_
                    _=  /%%%%%%%%%%%%%%%%%%%%;',(' ^,%\  =_
                  _=  /%%%%%%%%%%%%____.,__.,''-  __;%%%\  =_
                _=  /%%%%%_%%%%%,/    `::    _   :%%%%%%%%\  =_
              _=  /%%%%%_/ `--.'|     `::    '  ,;__%%%%%%%%\  =_
            _=  /%%%%%%(,-'-..__`,===.,::___' .'--.,-'%%%%%%%%\  =_
          _=  /%%%%%%%%%%%%_/ `--:.  ;-'`%%%`. `._%%%%%%%%%%%%%%\  =_
        _=  /%%%%%%%%%%%%%(,-'-...__,'%%%%%%%%`, ,'%%%%%%%%%%%%%%%\  =_
      _=  /%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%`-'%%%%%%%%%%%%%%%%%%\  =_
    _=  /%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Catalyst%\  =_
  _=    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    =_
 =============================================================================
**********************************************************************************
 */

```

# 9 咕泡学院资源汇总

1. 视频下载(VIP课&公开课):  https://gper.gupaoedu.com/gper-doc/java/vipResource.html

2. 分布式环境准备http://git.gupaoedu.com/java-vip/nice/wikis/%E5%88%86%E5%B8%83%E5%BC%8F%E4%B8%93%E9%A2%98%E4%B8%8A%E8%AF%BE%E7%8E%AF%E5%A2%83%E5%87%86%E5%A4%87

3. 沽泡论坛:   http://bbs.gupaoedu.com

4. 2019年VIP视频 https://dwz.cn/ejsxDG7T  9kxw

5. 2019年公开课视频 https://pan.baidu.com/s/1sjHN3uFB2A674XXK4HQ4vw pcdr

6. 分布式环境搭建 https://gper.club/articles/828

7. 复杂密码生成 https://suijimimashengcheng.51240.com/

8. 80端口被占用 HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\services\HTTP ==> start 0,3,4

9. https://www.jianshu.com/p/3fbb45ab62b2 jar变成系统服务

10. 全国政区数量统计http://www.xzqh.org/html/show/cn/37714.html

11. K8s安装和部署

    ```java
    https://github.com/itcrazy2016/K8s/blob/master/kubeadm%E5%AE%89%E8%A3%85k8s.md
    ```

12. 

    

    



#10 IntelliJ IDEA配置与使用

##10.1 @Autowired Mapper异常 

```php
@Autowired Mapper提示异常,但是系统可以访问
settings->Editor->Inspections->右侧Spring->Spring Core->Code ->Autowiring for bean class 去掉对钩
```



## 10.2 serialVersionUID 不生成

```java
settings->Editor->Inspections->右侧java->Serialization issues->Serializable class Without a serialVersionUID  打上对钩
```



## 10.3 自动补全快捷键设置

```java
settings->keymap->main menu->completion->Basic->"shift+space"   先remove，再add
需修改原“Shift＋空格”的快捷键
Plug+ins -> Database Tools and SQL ->Select Row 去掉“Shift＋空格”，再增加“Alt+/”
```



##10.4 新版本IntelliJ Idea 变量有下划线的问题

```sql
Filt -- Setting -- Editor -- Color Scheme -- Java ，点击下方需要修改代码，看到最右侧有显示效果Effects ，如下图。这里可以看到，参数的显示效果是绿色下划线underscored，（同样的点击下面代码中变量名，可以看到他的显示效果，你也可以按照这个方法取消一些提示显示的效果）。我们可以把underscored换成blod underscored，或者直接取消Effects前的√，Apply，退出。
```



## 10.5 将jar包发布到本地仓库 

```.java
mvn install:install-file -Dfile=F:/aspose-words-jdk16-15.11.0.jar -DgroupId=com.aspose -DartifactId=aspose-words -Dversion=15.11.0 -Dpackaging=jar
```





#11 VUE安装和配置

11.1 VUE~~环境安装~~

 1. 安装node.js，官网下载node.js ,自动安装后，cmd界面输入node -v确定版本

 2. nodejs安装目录,新建`node_global`和`node_cache`两个目录

 3. cmd界面输入

     `npm config set cache "E:\Program Files\nodejs\node_cache"`

     `npm config set prefix "E:\Program Files\nodejs\node_global"`

 4. 改cnpm到淘宝 `npm install -g cnpm --registry=https://registry.npm.taobao.org`

 5. 修改环境变量path为`E:\Program Files\nodejs\node_global`

 6. 增加环境变量NODE_PATH为`E:\Program Files\nodejs\node_modules`

 7. 安装VUE , cmd 窗口执行`cnpm install vue -g`

 8. 安装脚手架vue-cli,cmd窗口执行`cnpm install vue-cli -g`

 9. 新建项目 D:然后执行`vue init webpack vueProject` 一路回车到是否route

 10. 其他都no,一直到项目新建完成

 11. 输入 cd vueProject进入到项目目录，输入`cnpm install`安装模板之间的依赖

 12. 输入`cnpm run dev`启动项目 ，根据输入的地址和端口进行浏览器访问







# 12 吉林水资源II期


√1、运维系统logo 没有切换

√4、三大率 查询时间默认昨天8点 到今天8点
√5、上传率改为：上报率。

√11、在PC端察看测站的单站的运维历史。

√12、维修单故障原因、与维修单模块中备注的内容一样（导出pdf察看即可）。

√13、维修内容中有重复的描述，赘述去掉。



16、备品备件管理 中需要同步建管的数据(联系张黎)

3、上传率、完整率、及时率 算法与部里保持统一，但是如果是个别运维单位自己登陆，查询测站的基数是运维公司的站数(需要国家的 算法)。

6、运维首页，柱状图改为曲线图 查询 三大率的曲线图 上升趋势。

7、监测点统计中测站的行政区划 不要出现吉林省的行政区划。

8、测站基本信息与遥测同步(标准库获取测站数据 )。
9、自动工单产生后、又自动关闭。工单产生的机制，自动关闭条件要查询一下中断期间数据来没来。有下一条指令的时候， （召测）在关闭
工单管理=排序规则 把关闭状态排到最后。工单管理+召测。 (补的数据是否推送到了运维库)





# 13 Linux系统

## 13.1 基于虚拟机的网络配置

​		https://www.cnblogs.com/summer-fate/p/7704070.html



# 14 Maven配置



## 14.1 阿里云镜像配置

```.xml
第一步：修改 maven 根目录下的 conf 文件夹中的 setting.xml 文件，在 mirrors 节点上，添加内容如下：
<mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
</mirrors>

```



```.xml
第二步：pom.xml文件里添加
<repositories>  
        <repository>  
            <id>alimaven</id>  
            <name>aliyun maven</name>  
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
            <releases>  
                <enabled>true</enabled>  
            </releases>  
            <snapshots>  
                <enabled>false</enabled>  
            </snapshots>  
        </repository>  
</repositories>

```





# 15 Centos 



##15.0 超级麻烦安装yum[Centos7]

首先卸载python和yum的一切

```.java
rpm -qa|grep python|xargs rpm -ev --allmatches --nodeps
whereis python |xargs rm -frv
rpm -qa|grep yum|xargs rpm -ev --allmatches --nodeps
whereis yum |xargs rm -frv
```

然后下载所有的一切

```.sql
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/libxml2-python-2.9.1-6.el7.4.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-ipaddress-1.0.16-2.el7.noarch.rpm 
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/lvm2-python-libs-2.02.186-7.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-kitchen-1.1.1-5.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-3.4.3-167.el7.centos.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-2.7.5-88.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-libs-2.7.5-88.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-metadata-parser-1.1.4-10.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-backports-ssl_match_hostname-3.5.0.1-1.el7.noarch.rpm 
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-pycurl-7.19.0-19.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-plugin-aliases-1.1.31-53.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-chardet-2.2.1-3.el7.noarch.rpm                    
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-setuptools-0.9.8-7.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-plugin-fastestmirror-1.1.31-53.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-devel-2.7.5-88.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-urlgrabber-3.10-10.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-plugin-protectbase-1.1.31-53.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/python-iniparse-0.4-9.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/rpm-python-4.11.3-43.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/yum-utils-1.1.31-53.el7.noarch.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/rpm-build-4.11.3-43.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/rpm-build-libs-4.11.3-43.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/rpm-libs-4.11.3-43.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/rpm-python-4.11.3-43.el7.x86_64.rpm
wget http://mirrors.163.com/centos/7/os/x86_64/Packages/rpm-sign-4.11.3-43.el7.x86_64.rpm
```

最后安装一切

```.php
rpm -Uvh --replacepkgs *.rpm --nodeps --force
```



## 15.1 常用命令

1、察看软件是否安装  `rpm -qa | grep vim`

2、察看可可用软件列表 `yum list java* | grep 1.8`

3、察看端口占用情况 `sudo netstat -anp | grep 2181`

4、卸载软件 `rpm -e --nodeps mariadb-libs-5.5.64-1.el7.x86_64`

5、安装locate命令包 ： yum  -y install mlocate  





## 15.2 国内软件安装源

源位置 `/etc/yum.repos.d`

备份源：`mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup `

下载源：`wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo `

生成缓存：`yum makecache`



## 15.3 安装常用环境

安装jdk 

```java
1、 查找可用的jdk源 yum list java* | grep 1.8.0
2、 安装   yum -y  install java-1.8.0-openjdk.x86_64
3、 察看安装结果   java -version 显示正常的版本信息
4、 安装jdk指令   yum -y install java-1.8.0-openjdk-devel.x86_64
5、 执行jps指令测试，显示jps的进程号信息
    
6、 配置环境变量[一般来讲可省略，安装后自动配置]
    vi   /etc/profile
    
	在最后面加上：
    export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-3.b13.el7_5.x86_64
    export JRE_HOME=$JAVA_HOME/jre
    export CLASSPATH=$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
    export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
        
    保存退出   source /etc/profile
    验证： echo $JAVA_HOME
```

安装netstat

```.java
 安装netstat yum -y install net-tools
```



安装mysql 5.7

```java
下载并安装MySQL官方的Yum Repository
    wget -i -c http://dev.mysql.com/get/mysql57-community-release-el7-10.noarch.rpm

直接安装rpm
     yum -y install mysql57-community-release-el7-10.noarch.rpm

安装MySQL服务器
    yum -y install mysql-community-server
    
启动MySQL
    systemctl start mysqld.service

查看MySQL运行状态
    systemctl status mysqld.service
    
此时MySQL已经开始正常运行，需要找出root的密码 ()
    grep "password" /var/log/mysqld.log

如下命令登录mysql    
    mysql -uroot -p
    
输入初始密码，此时不能做任何事情，因为MYSQL默认必须修改密码才能正常使用，这里会遇到一个问题，新密码设置过于简单会报错
    
修改默认密码，否则不允许进行后续操作
   5.7.6版本 ALTER USER USER() IDENTIFIED BY 'Elitel!@3$_Elitel!@3$'';
   5.7.6以前的版本 SET PASSWORD = PASSWORD('Wayne)&11'); 
    
可通过如下命令查看完整的初始密码规则
    mysql> show variables like 'validate_password';

可通过如下命令修改
    mysql> set global validate_password_policy=0;
	mysql> set global validate_password_length=1;

还有一个问题就是Yum Repository,以后每次 yum 操作都会自动更新，需要把这个卸载掉
    yum -y remove mysql57-community-release-el7-10.noarch
    
远程登录数据库出现下面出错信息，原因是没有授予相应的权限
#任何主机
mysql>GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'Wayne)&11' WITH GRANT OPTION;

#创建用户并指定任何主机主机
mysql>GRANT ALL PRIVILEGES ON *.* TO 'wayne'@'%' IDENTIFIED BY 'Wayne)&11' WITH GRANT OPTION;

# 然后刷新权限
mysql>flush privileges;

修改mysql数据库总的user表使相的用户能从某一主机登录
    mysql>use mysql;
	mysql>update user set host = '%' where user = 'root';
	mysql>select host, user from user;

客户端提供MYSQL的环境，但是不支持中文,通过以下命令可以查看mysql的字符集
    mysql>show variables like 'character_set%';

为了让 MySQL支持中文，需要把字符集改成UTF-8，方法如下
    vim /etc/my.cnf
    
    修改
    [mysql]
	no-auto-rehash
	default-character-set=utf8
    
重启mysql服务
    service mysqld restart
    
    重新查看数据库编码
    show variables like 'character_set%';
=========================================================================================
    卸载mysql 
    1、rpm -qa |grep -i mysql
    2、 yum remove mysql-community-common-5.7.20-1.el7.x86_64
		yum remove mysql-community-client-5.7.20-1.el7.x86_64
        yum remove mysql57-community-release-el7-11.noarch
        yum remove mysql-community-libs-5.7.20-1.el7.x86_64
        yum removemysql-community-server-5.7.20-1.el7.x86_64
    3、察看卸载是否完成 rpm -qa | grep -i mysql
    4、查找mysql相关目录 find / -name mysql，删除相关目录
    5、删除/etc/my.cnf      rm -rf /etc/my.cnf
    6、删除/var/log/mysqld.log（如果不删除这个文件，会导致新安装的mysql无法生存新密码，导致无法登陆）
```



## 15.4 防火墙

```java
启动/关闭/重启防火墙： 
    systemctl start/stop/restart firewalld
    
察看防火墙状态： 
    systemctl status firewalld
    
开放端口放行：
    firewall-cmd --zone=public --add-port=3308/tcp --permanent
	(加上--permanet参数永久生效)
    
重新读取防火墙规则: firewall-cmd --reload
```



## 15.5 用户权限指令

```sql
用户组创建：groupadd elitel
用户创建  useradd -g elitel -G root yiWangC

创建/修改密码  passwd yiWangC
从root组中删除night2lx用户  gpasswd root -d night2lx
```

|wheel组的作用:

只有wheel组的用户才可以使用su root 命令，启用root用户执行权限，其他用户组输入密码后提示密码无效，具体配置如下：

```.shell
1）修改 /etc/pam.d/su 文件:
	找到“#auth required /lib/security/$ISA/pam_wheel.so use_uid ”这一行，将行首的“#”去掉。
	
2）修改 /etc/login.defs 文件，在最后一行增加“SU_WHEEL_ONLY yes”语句。
```







## 15.6  启动命令与配置 

jar 文件启动

```.java
java -jar rclient.jar 
java -jar rclient.jar &
nohup java -jar rclient.jar &
nohup java -jar rclient.jar > logs.log &
墙裂推荐 nohup java -jar rclient.jar 1 > logs.log 2>&1 &
systemctl start rclient.service (需配置rclient.service文件)
```



##15.7 各种系统配置

```.
1、配置ssh默认端口 vim /etc/ssh/sshd_config            systemctl restart sshd
2、永久配置主机名称 vim /etc/hostname                   reboot
```





# 16 Docker

## 16.1 Docker安装

```.java
01 卸载之前的docker
	sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
                  
02 安装必要的依赖
	sudo yum install -y yum-utils \
    device-mapper-persistent-data \
    lvm2
    
03 设置docker仓库  [设置阿里云镜像仓库可以先自行百度，学会建立自己的docker hub]	
	sudo yum-config-manager \
      --add-repo \
      https://download.docker.com/linux/centos/docker-ce.repo
      
    [访问这个地址，使用自己的阿里云账号登录，查看菜单栏左下角，发现有一个镜像加速器:https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors]

04 安装docker
	sudo yum install -y docker-ce docker-ce-cli containerd.io
	
05 启动docker
	sudo systemctl start docker
	
06 测试docker安装是否成功
	sudo docker run hello-world
```



## 16.2 Docker基本体验

```.java
01 创建tomcat容器
	docker pull tomcat
	docker run -d --name my-tomcat -p 9090:8080 tomcat

02 创建mysql容器
	docker run -d --name my-mysql -p 3301:3306 -e MYSQL_ROOT_PASSWORD=jack123 --privileged mysql
	
03 进入到容器里面
        docker exec -it containerid /bin/bash
```



## 16.3 mysql容器准备

> (1)创建volume

```
docker volume create v1
```

> (2)创建mysql容器

```
docker run -d --name my-mysql -v v1:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=jack123 --net=pro-net --ip 172.18.0.6 mysql
```



##16.4 其他应用安装

|一、安装vsftp

```.java
docker pull fauria/vsftpd
docker run -d -v /home/vsftpd:/home/vsftpd -p 20:20 -p 21:21 -p 21100-21110:21100-21110 -e FTP_USER=test -e FTP_PASS=test --name vsftpd fauria/vsftpd

会以登录用户名 (test) 创建一个目录 (/home/vsftpd/test) 作为 ftp 根目录
测试时发现不加 -p 20:20 依然可以正常操作
```





##16.n 清理无用的卷，容器，镜像

```.java
docker volume rm $(docker volume ls -qf dangling=true)

docker rmi $(docker images | grep '^<none>' | awk '{print $3}')

docker images --no-trunc | grep '<none>' | awk '{ print $3 }' \
| xargs docker rmi

docker system prune

docker volume prune

docker rm $(docker ps -q)
docker rmi $(docker images -q)

```

| 执行以下命令会彻底清除所有容器。
  docker system prune -a -f







# 17 vagrant操作

##17.1 常用命令

| 命令               | 解释                  |
| ------------------ | --------------------- |
| vagrant init       | 初始化配置vagrantfile |
| vagrant box add    | 新增加一个box         |
| vagrant box remove | 删除指定box           |
| vagrant box list   | 查看目前已有的box     |
| vagrant up         | 启动虚拟机            |
| vagrant ssh        | ssh登录虚拟机         |
| vagrant suspend    | 挂起虚拟机            |
| vagrant reload     | 重启虚拟机            |
| vagrant halt       | 关闭虚拟机            |
| vagrant status     | 查看虚拟机状态        |
| vagrant destroy    | 删除虚拟机            |



# 18 Spring系列 

## 18.1 Spring事务

### 18.1.1 spring事务隔离级别

```.
隔离级别是指若干个并发的事务之间的隔离程度。TransactionDefinition 接口中定义了五个表示隔离级别的常量：

    TransactionDefinition.ISOLATION_DEFAULT：
    这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是
    TransactionDefinition.ISOLATION_READ_COMMITTED。

    TransactionDefinition.ISOLATION_READ_UNCOMMITTED：
    该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读，不可重复读和幻读，因此很少使用该隔离级别。比如PostgreSQL实际上并没有此级别。

    TransactionDefinition.ISOLATION_READ_COMMITTED：
    该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。

    TransactionDefinition.ISOLATION_REPEATABLE_READ：
    该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。该级别可以防止脏读和不可重复读。

    TransactionDefinition.ISOLATION_SERIALIZABLE：
    所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。
```



### 18.1.2 spring事务传播行为

```.java
所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。在TransactionDefinition定义中包括了如下几个表示传播行为的常量：

    TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
    TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
    TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
    TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
    TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
    TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
    TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED
```





### 18.1.3 spring事务回滚规则&常用配置

```.java
	指示spring事务管理器回滚一个事务的推荐方法是在当前事务的上下文内抛出异常。spring事务管理器会捕捉任何未处理的异常，然后依据规则决定是否回滚抛出异常的事务。默认配置下，spring只有在抛出的异常为运行时unchecked异常时才回滚该事务，也就是抛出的异常为RuntimeException的子类(Errors也会导致事务回滚)，而抛出checked异常则不会导致事务回滚。可以明确的配置在抛出那些异常时回滚事务，包括checked异常。也可以明确定义那些异常抛出时不回滚事务。
	
```

```.
事务常用配置

    readOnly：该属性用于设置当前事务是否为只读事务，设置为true表示只读，false则表示可读写，默认值为false。例如：@Transactional(readOnly=true)；
    
    rollbackFor：该属性用于设置需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，则进行事务回滚。例如：指定单一异常类：@Transactional(rollbackFor=RuntimeException.class)指定多个异常类：@Transactional(rollbackFor={RuntimeException.class, Exception.class})；
    
    rollbackForClassName：该属性用于设置需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，则进行事务回滚。例如：指定单一异常类名称@Transactional(rollbackForClassName=”RuntimeException”)指定多个异常类名称：@Transactional(rollbackForClassName={“RuntimeException”,”Exception”})。
    
    noRollbackFor：该属性用于设置不需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，不进行事务回滚。例如：指定单一异常类：@Transactional(noRollbackFor=RuntimeException.class)指定多个异常类：@Transactional(noRollbackFor={RuntimeException.class, Exception.class})。
    
    noRollbackForClassName：该属性用于设置不需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，不进行事务回滚。例如：指定单一异常类名称：@Transactional(noRollbackForClassName=”RuntimeException”)指定多个异常类名称：@Transactional(noRollbackForClassName={“RuntimeException”,”Exception”})。
   
   propagation ：该属性用于设置事务的传播行为。例如：@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)。
    
    isolation：该属性用于设置底层数据库的事务隔离级别，事务隔离级别用于处理多事务并发的情况，通常使用数据库的默认隔离级别即可，基本不需要进行设置。
   
   timeout：该属性用于设置事务的超时秒数，默认值为-1表示永不超时。
```



### 18.1.4 spring事务注意事项

1. 要根据实际的需求来决定是否要使用事务，最好是在编码之前就考虑好，不然到以后就难以维护；
2. 如果使用了事务，请务必进行事务测试，因为很多情况下以为事务是生效的，但是实际上可能未生效！
3. 事物@Transactional的使用要放再类的**公共(public)方法**中，需要注意的是在 protected、private 方法上使用 @Transactional 注解，它也不会报错(IDEA会有提示)，但事务无效。
4. 事物@Transactional是不会对该方法里面的子方法生效！也就是你在公共方法A声明的事物@Transactional，但是在A方法中有个子方法B和C，其中方法B进行了数据操作，但是该异常被B自己处理了，这样的话事物是不会生效的！反之B方法声明的事物@Transactional，但是公共方法A却未声明事物的话，也是不会生效的！如果想事物生效，需要将子方法的事务控制交给调用的方法，在子方法中使用`rollbackFor`注解指定需要回滚的异常或者将异常抛出交给调用的方法处理。一句话就是在使用事物的异常由调用者进行处理！
5. 事物@Transactional由spring控制的时候，它会在抛出异常的时候进行回滚。如果自己使用catch捕获了处理了，是不生效的，如果想生效可以进行手动回滚或者在catch里面将异常抛出，比如`throw new RuntimeException();`。



### 18.1.5 spring事务原理

​		Spring的事务机制是用统一的机制来处理不同数据访问技术的事务处理。Spring的事务机制提供了一个PlatformTransactionManager接口，不同的数据访问技术的事务使用不同的接口实现，如表所示:



| 实现       | 数据访问技术                 |
| :--------- | :--------------------------- |
| JDBC       | DataSourceTransactionManager |
| JPA        | JapTransactionManager        |
| Hibernate  | HibernateTransactionManager  |
| JDO        | JdoTransactionManager        |
| 分布式事务 | JtaTransactionManager        |

在程序中定义事务管理器的代码如下：

```.java
@Bean
public PlatformTransactionManager transactionManager() {

	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setDataSource(dataSource());
	return transactionManager;
}
```



|声明式事务

​		Spring支持声名式事务，即使用注解来选择需要使用事务的方法，它使用@Transactional注解在方法上表明该方法需要事务支持。这是一个基于AOP的实现操作。

```.java
@Transactional
public void saveSomething(Long  id, String name) {
    //数据库操作
}
```

在此处需要特别注意的是，此@Transactional注解来自org.springframework.transaction.annotation包，而不是javax.transaction。



|AOP代理的两种实现

- jdk是代理接口，私有方法必然不会存在在接口里，所以就不会被拦截到；

- cglib是子类，private的方法照样不会出现在子类里，也不能被拦截 ;

  

**Java 动态代理如下四个步骤：**

1. 通过实现 InvocationHandler 接口创建自己的调用处理器；
2. 通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
3. 通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
4. 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。



**CGLIB代理：**

​		cglib（Code Generation Library）是一个强大的,高性能,高质量的Code生成类库。它可以在运行期扩展Java类与实现Java接口。

- cglib封装了asm，可以在运行期动态生成新的class（**子类**）。
- cglib用于AOP，jdk中的proxy必须基于接口，cglib却没有这个限制。

**原理区别：**

```.java
java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。

    如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
    如果目标对象实现了接口，可以强制使用CGLIB实现AOP
    如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
```



**如果是类内部方法直接不是走代理，这个时候可以通过维护一个自身实例的代理。**

```.java
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    // 注入自身代理对象，在本类内部方法调用事务的传递性才会生效
    @Autowired
    PersonService selfProxyPersonService;

    /**
     * 测试事务的传递性
     *
     * @param person
     * @return
     */
    @Transactional
    public Person save(Person person) {
        Person p = personRepository.save(person);
        try {
            // 新开事务 独立回滚
            selfProxyPersonService.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 使用当前事务 全部回滚
            selfProxyPersonService.save2(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        personRepository.save(person);

        return p;
    }

    @Transactional
    public void save2(Person person) {
        personRepository.save(person);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete() {
        personRepository.delete(1L);
        throw new RuntimeException();
    }
}
```

### 18.1.6 Spring事务失效的原因

```.java
1、数据库或者存储引擎不支持事务(mysql的myisam)
2、调用方法为非public方法，只有public的方法才可以进行事务管理
√3、内部调用，同一个类方法A调用方法B，因为是内部调用所以不起作用[可以通过注入自己来实现，但是很不优雅]
4、Transactional注解参数的传播级别是@Transactional(propagation = Propagation.NOT_SUPPORTED)
5、数据源没有启用事务管理
6、所在类没有被加载成Bean,比如，没有@Service注解
√7、抛出的异常被吃了
√8、抛出的异常为错误的异常类型，比如需要捕获RunTimeExcption,结果抛出了Exception
```





# 19 JavaEE系列

## 19.1 过滤器和拦截器

参照：https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650131098&idx=2&sn=4ca5766e9bca1d22126726b4ecdce52e&chksm=f36bd7bbc41c5eadc1c73f87e38d76f7aa732e588c7f7e59a22c501d178401e60f3796df99c3&mpshare=1&scene=1&srcid=0804slU5LWwSAFxOhvRCKG8P&sharer_sharetime=1596523596112&sharer_shareid=27f3e34a1b5a7cb3fda6f20cad5dd0d4&exportkey=AUL%2FVdy9Sw0XBQeHV1fWPGU%3D&pass_ticket=uIl7QeOQrAzDVRHayhgHNUjscSv3d1HMZh1zWPUDGujXt2lWBESXGPNOqS4HGHFq&wx_header=0#rd

|过滤器 (Filter):

```.java
过滤器的配置比较简单，直接实现Filter 接口即可，也可以通过@WebFilter注解实现对特定URL拦截，看到Filter 接口中定义了三个方法。

    init() ：该方法在容器启动初始化过滤器时被调用，它在 Filter 的整个生命周期只会被调用一次。「注意」：这个方法必须执行成功，否则过滤器会不起作用。
    doFilter() ：容器中的每一次请求都会调用该方法， FilterChain 用来调用下一个过滤器 Filter。
    destroy()：当容器销毁 过滤器实例时调用该方法，一般在方法中销毁或关闭资源，在过滤器 Filter 的整个生命周期也只会被调用一次
    
    @Component
public class MyFilter implements Filter {
     @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter 前置");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 处理中");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter 后置");
    }
}
```

|拦截器 (Interceptor)

```.java
	拦截器它是链式调用，一个应用中可以同时存在多个拦截器Interceptor， 一个请求也可以触发多个拦截器 ，而每个拦截器的调用会依据它的声明顺序依次执行。

	首先编写一个简单的拦截器处理类，请求的拦截是通过HandlerInterceptor 来实现，看到HandlerInterceptor 接口中也定义了三个方法。

    preHandle() ：这个方法将在请求处理之前进行调用。「注意」：如果该方法的返回值为false ，将视为当前请求结束，不仅自身的拦截器会失效，还会导致其他的拦截器也不再执行。
    
    postHandle()：只有在 preHandle() 方法返回值为true 时才会执行。会在Controller 中的方法调用之后，DispatcherServlet 返回渲染视图之前被调用。「有意思的是」：postHandle() 方法被调用的顺序跟 
    
    preHandle() 是相反的，先声明的拦截器  preHandle() 方法先执行，而postHandle()方法反而会后执行。
    
    afterCompletion()：只有在 preHandle() 方法返回值为true 时才会执行。在整个请求结束之后，  DispatcherServlet 渲染了对应的视图之后执行。
    
    
    @Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor 前置");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor 处理中");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor 后置");
    }
}
```

​		将自定义好的拦截器处理类进行注册，并通过`addPathPatterns`、`excludePathPatterns`等属性设置需要拦截或需要排除的 `URL`

```.java
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
    }
}
```



|不同点:

​	1、实现原理不同

​			`过滤器` 是基于函数回调的，`拦截器` 则是基于Java的反射机制（动态代理）实现的

​	2、使用范围不同

​			过滤器`Filter` 的使用要依赖于`Tomcat`等容器，导致它只能在`web`程序中使用

​	3、触发时机不同

​			过滤器`Filter`是在请求进入容器后，但在进入`servlet`之前进行预处理，请求结束是在`servlet`处理完			以后。

​			拦截器 `Interceptor` 是在请求进入`servlet`后，在进入`Controller`之前进行预处理的，`Controller` 			中渲染了对应的视图之后请求结束。

​	4、拦截的请求范围不同

​			过滤器`Filter`执行了两次，拦截器`Interceptor`只执行了一次。这是因为过滤器几乎可以对所有进入容			器的请求起作用，而拦截器只会对`Controller`中请求或访问`static`目录下的资源请求起作用

​	5、注入Bean情况不同

​			因为加载顺序导致的问题，`拦截器`加载的时间点在`springcontext`之前，而`Bean`又是由`spring`进行管			理。所以springBean无法注入到拦截器

​			解决方案也很简单，我们在注册拦截器之前，先将`Interceptor` 手动进行注入。**「注意」**：在`registry.addInterceptor()`注册的是`getMyInterceptor()` 实例。

```.java
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public MyInterceptor getMyInterceptor(){
        System.out.println("注入了MyInterceptor");
        return new MyInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
    }
}
```



6、控制执行顺序不同

​		实际开发过程中，会出现多个过滤器或拦截器同时存在的情况，不过，有时我们希望某个过滤器或拦截器能优先执行，就涉及到它们的执行顺序。

​		过滤器用`@Order`注解控制执行顺序，通过`@Order`控制过滤器的级别，值越小级别越高越先执行。

```.java
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class MyFilter2 implements Filter {}
```

拦截器默认的执行顺序，就是它的注册顺序，也可以通过`Order`手动设置控制，值越小越先执行。

```.java
@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**").order(2);
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**").order(1);
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").order(3);
    }
```



## 19.2 Nginx的配置和使用

参考：https://mp.weixin.qq.com/s?__biz=MzAxMTkwODIyNA==&mid=2247492572&amp;idx=1&amp;sn=6cf48b841c92c3e24cf4216db52e3fd2&source=41#wechat_redirect

### 19.2.1  常用命令:

```.shell
nginx -s stop       快速关闭Nginx，可能不保存相关信息，并迅速终止web服务。
nginx -s quit       平稳关闭Nginx，保存相关信息，有安排的结束web服务。
nginx -s reload     因改变了Nginx相关配置，需要重新加载配置而重载。
nginx -s reopen     重新打开日志文件。
nginx -c filename   为 Nginx 指定一个配置文件，来代替缺省的。
nginx -t            不运行，而仅仅测试配置文件。nginx 将检查配置文件的语法的正确性，并尝试打开配置文件中所引用到的文件。
nginx -v            显示 nginx 的版本。
nginx -V            显示 nginx 的版本，编译器版本和配置参数。
```

|如果不想每次都敲命令，可以在 nginx 安装目录下新添一个启动批处理文件startup.bat，双击即可运行。内容如下：

```.shell
@echo off
rem 如果启动前已经启动nginx并记录下pid文件，会kill指定进程
nginx.exe -s stop

rem 测试配置文件语法正确性
nginx.exe -t -c conf/nginx.conf

rem 显示版本信息
nginx.exe -v

rem 按照指定配置去启动nginx
nginx.exe -c conf/nginx.conf
```



### 19.2.2 location 路径匹配

**| location 路径正则匹配：**  

| 说明 | 符号                                                         |
| ---- | ------------------------------------------------------------ |
| `~`  | 正则匹配，区分大小写                                         |
| `~*` | 正则匹配，不区分大小写                                       |
| `^~` | 普通字符匹配，如果该选项匹配，则，只匹配改选项，不再向下匹配其他选项 |
| `=`  | 普通字符匹配，精确匹配                                       |
| `@`  | 定义一个命名的 location，用于内部定向，例如 error_page，try_files |



**|匹配优先级(跟 location 的书写顺序关系不大)：**

1. **精确匹配**：

   `=`前缀的指令严格匹配这个查询。

   如果找到，停止搜索。

2. **普通字符匹配**：

   所有剩下的常规字符串，最长的匹配。

   如果这个匹配使用`^〜`前缀，搜索停止。

3. **正则匹配**：

   正则表达式，在配置文件中定义的顺序，匹配到一个结果，搜索停止；

4. **默认匹配**：

   如果第3条规则产生匹配的话，结果被使用。

   否则，如同从第2条规则被使用。

通过一个实例，简单说明一下匹配优先级：

```.java
location  = / {
  # 精确匹配 / ，主机名后面不能带任何字符串
  [ configuration A ]
}

location  / {
  # 因为所有的地址都以 / 开头，所以这条规则将匹配到所有请求
  # 但是正则和最长字符串会优先匹配
  [ configuration B ]
}

location /documents/ {
  # 匹配任何以 /documents/ 开头的地址，匹配符合以后，还要继续往下搜索
  # 只有后面的正则表达式没有匹配到时，这一条才会采用这一条
  [ configuration C ]
}

location ~ /documents/Abc {
  # 匹配任何以 /documents/ 开头的地址，匹配符合以后，还要继续往下搜索
  # 只有后面的正则表达式没有匹配到时，这一条才会采用这一条
  [ configuration CC ]
}

location ^~ /images/ {
  # 匹配任何以 /images/ 开头的地址，匹配符合以后，停止往下搜索正则，采用这一条。
  [ configuration D ]
}

location ~* \.(gif|jpg|jpeg)$ {
  # 匹配所有以 gif,jpg或jpeg 结尾的请求
  # 然而，所有请求 /images/ 下的图片会被 config D 处理，因为 ^~ 到达不了这一条正则
  [ configuration E ]
}

location /images/ {
  # 字符匹配到 /images/，继续往下，会发现 ^~ 存在
  [ configuration F ]
}

location /images/abc {
  # 最长字符匹配到 /images/abc，继续往下，会发现 ^~ 存在
  # F与G的放置顺序是没有关系的
  [ configuration G ]
}

location ~ /images/abc/ {
  # 只有去掉 config D 才有效：先最长匹配 config G 开头的地址，继续往下搜索，匹配到这一条正则，采用
    [ configuration H ]
}
```

按照上面的location写法，以下的匹配示例成立：

```.java
    / -> config A：

    精确完全匹配，即使/index.html也匹配不了

    /downloads/download.html -> config B：

    匹配B以后，往下没有任何匹配，采用B

    /images/1.gif -> configuration D：

    匹配到F，往下匹配到D，停止往下

    /images/abc/def -> config D：

    最长匹配到G，往下匹配D，停止往下你可以看到 任何以/images/开头的都会匹配到D并停止，FG写在这里是没有任何意义的，H是永远轮不到的，这里只是为了说明匹配顺序

    /documents/document.html -> config C：

    匹配到C，往下没有任何匹配，采用C

    /documents/1.jpg -> configuration E：

    匹配到C，往下正则匹配到E

    /documents/Abc.jpg -> config CC：

    最长匹配到C，往下正则顺序匹配到CC，不会往下到E
```





### 19.2.3 Nginx实战配置

`注：conf / nginx.conf 是 nginx 的默认配置文件。你也可以使用 nginx -c 指定你的配置文件`

`webapp，注意启动绑定的端口要和 nginx 中的 upstream 设置的端口保持一致`

**实战一：反向代理**

```.shell
#运行用户
#user somebody;

#启动进程,通常设置成和cpu的数量相等
worker_processes  1;

#全局错误日志
error_log  D:/Tools/nginx-1.10.1/logs/error.log;
error_log  D:/Tools/nginx-1.10.1/logs/notice.log  notice;
error_log  D:/Tools/nginx-1.10.1/logs/info.log  info;

#PID文件，记录当前启动的nginx的进程ID
pid        D:/Tools/nginx-1.10.1/logs/nginx.pid;

#工作模式及连接数上限
events {
    worker_connections 1024;    #单个后台worker process进程的最大并发链接数
}

#设定http服务器，利用它的反向代理功能提供负载均衡支持
http {
    #设定mime类型(邮件支持类型),类型由mime.types文件定义
    include       D:/Tools/nginx-1.10.1/conf/mime.types;
    default_type  application/octet-stream;

    #设定日志
    log_format  main  '[$remote_addr] - [$remote_user] [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log    D:/Tools/nginx-1.10.1/logs/access.log main;
    rewrite_log     on;

    #sendfile 指令指定 nginx 是否调用 sendfile 函数（zero copy 方式）来输出文件，对于普通应用，
    #必须设为 on,如果用来进行下载等应用磁盘IO重负载应用，可设置为 off，以平衡磁盘与网络I/O处理速度，降低系统的uptime.
    sendfile        on;
    #tcp_nopush     on;

    #连接超时时间
    keepalive_timeout  120;
    tcp_nodelay        on;

    #gzip压缩开关
    #gzip  on;

    #设定实际的服务器列表
    upstream zp_server1{
        server 127.0.0.1:8089;
    }

    #HTTP服务器
    server {
        #监听80端口，80端口是知名端口号，用于HTTP协议
        listen       80;

        #定义使用www.xx.com访问
        server_name  www.helloworld.com;

        #首页
        index index.html

        #指向webapp的目录
        root D:\01_Workspace\Project\github\zp\SpringNotes\spring-security\spring-shiro\src\main\webapp;

        #编码格式
        charset utf-8;

        #代理配置参数
        proxy_connect_timeout 180;
        proxy_send_timeout 180;
        proxy_read_timeout 180;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarder-For $remote_addr;

        #反向代理的路径（和upstream绑定），location 后面设置映射的路径
        location / {
            proxy_pass http://zp_server1;
        }

        #静态文件，nginx自己处理
        location ~ ^/(images|javascript|js|css|flash|media|static)/ {
            root D:\01_Workspace\Project\github\zp\SpringNotes\spring-security\spring-shiro\src\main\webapp\views;
            #过期30天，静态文件不怎么更新，过期可以设大一点，如果频繁更新，则可以设置得小一点。
            expires 30d;
        }

        #设定查看Nginx状态的地址
        location /NginxStatus {
            stub_status           on;
            access_log            on;
            auth_basic            "NginxStatus";
            auth_basic_user_file  conf/htpasswd;
        }

        #禁止访问 .htxxx 文件
        location ~ /\.ht {
            deny all;
        }

        #错误处理页面（可选择性配置）
        #error_page   404              /404.html;
        #error_page   500 502 503 504  /50x.html;
        #location = /50x.html {
        #    root   html;
        #}
    }
}
```



**实战二：负载均衡**

```.java
http {
     #设定mime类型,类型由mime.type文件定义
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;
    #设定日志格式
    access_log    /var/log/nginx/access.log;

    #设定负载均衡的服务器列表
    upstream load_balance_server {
        #weigth参数表示权值，权值越高被分配到的几率越大
        server 192.168.1.11:80   weight=5;
        server 192.168.1.12:80   weight=1;
        server 192.168.1.13:80   weight=6;
    }

   #HTTP服务器
   server {
        #侦听80端口
        listen       80;

        #定义使用www.xx.com访问
        server_name  www.helloworld.com;

        #对所有请求进行负载均衡请求
        location / {
            root        /root;                 #定义服务器的默认网站根目录位置
            index       index.html index.htm;  #定义首页索引文件的名称
            proxy_pass  http://load_balance_server ;#请求转向load_balance_server 定义的服务器列表

            #以下是一些反向代理的配置(可选择性配置)
            #proxy_redirect off;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            #后端的Web服务器可以通过X-Forwarded-For获取用户真实IP
            proxy_set_header X-Forwarded-For $remote_addr;
            proxy_connect_timeout 90;          #nginx跟后端服务器连接超时时间(代理连接超时)
            proxy_send_timeout 90;             #后端服务器数据回传时间(代理发送超时)
            proxy_read_timeout 90;             #连接成功后，后端服务器响应时间(代理接收超时)
            proxy_buffer_size 4k;              #设置代理服务器（nginx）保存用户头信息的缓冲区大小
            proxy_buffers 4 32k;               #proxy_buffers缓冲区，网页平均在32k以下的话，这样设置
            proxy_busy_buffers_size 64k;       #高负荷下缓冲大小（proxy_buffers*2）
            proxy_temp_file_write_size 64k;    #设定缓存文件夹大小，大于这个值，将从upstream服务器传

            client_max_body_size 10m;          #允许客户端请求的最大单文件字节数
            client_body_buffer_size 128k;      #缓冲区代理缓冲用户端请求的最大字节数
        }
    }
}
```



**实战三  网站有多个 webapp 的配置**

```.java
http {
    #此处省略一些基本配置

    upstream product_server{
        server www.helloworld.com:8081;
    }

    upstream admin_server{
        server www.helloworld.com:8082;
    }

    upstream finance_server{
        server www.helloworld.com:8083;
    }

    server {
        #此处省略一些基本配置
        #默认指向product的server
        location / {
            proxy_pass http://product_server;
        }

        location /product/{
            proxy_pass http://product_server;
        }

        location /admin/ {
            proxy_pass http://admin_server;
        }

        location /finance/ {
            proxy_pass http://finance_server;
        }
    }
}
```



**实战四：https 反向代理配置**

使用 nginx 配置 https 需要知道几点：

- HTTPS 的固定端口号是 443，不同于 HTTP 的 80 端口
- SSL 标准需要引入安全证书，所以在 nginx.conf 中你需要指定证书和它对应的 key

其他和 http 反向代理基本一样，只是在 Server 部分配置有些不同

```.java
#HTTP服务器
  server {
      #监听443端口。443为知名端口号，主要用于HTTPS协议
      listen       443 ssl;

      #定义使用www.xx.com访问
      server_name  www.helloworld.com;

      #ssl证书文件位置(常见证书文件格式为：crt/pem)
      ssl_certificate      cert.pem;
      #ssl证书key位置
      ssl_certificate_key  cert.key;

      #ssl配置参数（选择性配置）
      ssl_session_cache    shared:SSL:1m;
      ssl_session_timeout  5m;
      #数字签名，此处使用MD5
      ssl_ciphers  HIGH:!aNULL:!MD5;
      ssl_prefer_server_ciphers  on;

      location / {
          root   /root;
          index  index.html index.htm;
      }
  }
```



**实战五：静态站点配置**

有时候，我们需要配置静态站点(即 html 文件和一堆静态资源)。

举例来说：如果所有的静态资源都放在了 /app/dist 目录下，我们只需要在 nginx.conf 中指定首页以及这个站点的 host 即可。

配置如下：

```.java
worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    gzip on;
    gzip_types text/plain application/x-javascript text/css application/xml text/javascript application/javascript image/jpeg image/gif image/png;
    gzip_vary on;

    server {
        listen       80;
        server_name  static.zp.cn;

        location / {
            root /app/dist;
            index index.html;
            #转发任何请求到 index.html
        }
    }
}
```

然后，添加 HOST：127.0.0.1 static.zp.cn

此时，在本地浏览器访问 static.zp.cn ，就可以访问静态站点了。



**实战六：搭建文件服务器:**

有时候，团队需要归档一些数据或资料，那么文件服务器必不可少。使用 Nginx 可以非常快速便捷的搭建一个简易的文件服务。

Nginx 中的配置要点：

- 将 autoindex 开启可以显示目录，默认不开启。
- 将 autoindex_exact_size 开启可以显示文件的大小。
- 将 autoindex_localtime 开启可以显示文件的修改时间。
- root 用来设置开放为文件服务的根路径。
- charset 设置为 charset utf-8,gbk;，可以避免中文乱码问题（windows 服务器下设置后，依然乱码，本人暂时没有找到解决方法）。

一个最简化的配置如下：

```.java
autoindex on;# 显示目录
autoindex_exact_size on;# 显示文件大小
autoindex_localtime on;# 显示文件时间

server {
    charset      utf-8,gbk; # windows 服务器下设置后，依然乱码，暂时无解
    listen       9050 default_server;
    listen       [::]:9050 default_server;
    server_name  _;
    root         /share/fs;
}
```



**实战七：跨域解决方案**

各自独立的 web app 在互相访问时，势必存在跨域问题。解决跨域问题一般有两种思路：

**1、CORS**

在后端服务器设置 HTTP 响应头，把你需要运行访问的域名加入加入 Access-Control-Allow-Origin中。

**2、jsonp**

把后端根据请求，构造 json 数据，并返回，前端用 jsonp 跨域。

这两种思路，本文不展开讨论。

需要说明的是，nginx 根据第一种思路，也提供了一种解决跨域的解决方案。

举例：www.helloworld.com 网站是由一个前端 app ，一个后端 app 组成的。前端端口号为 9000， 后端端口号为 8080。

前端和后端如果使用 http 进行交互时，请求会被拒绝，因为存在跨域问题。来看看，nginx 是怎么解决的吧：

首先，在 enable-cors.conf 文件中设置 cors ：

```.java
# allow origin list
set $ACAO '*';

# set single origin
if ($http_origin ~* (www.helloworld.com)$) {
  set $ACAO $http_origin;
}

if ($cors = "trueget") {
    add_header 'Access-Control-Allow-Origin' "$http_origin";
    add_header 'Access-Control-Allow-Credentials' 'true';
    add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
    add_header 'Access-Control-Allow-Headers' 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type';
}

if ($request_method = 'OPTIONS') {
  set $cors "${cors}options";
}

if ($request_method = 'GET') {
  set $cors "${cors}get";
}

if ($request_method = 'POST') {
  set $cors "${cors}post";
}
```

接下来，在你的服务器中 include enable-cors.conf 来引入跨域配置：

```.java
# ----------------------------------------------------
# 此文件为项目 nginx 配置片段
# 可以直接在 nginx config 中 include（推荐）
# 或者 copy 到现有 nginx 中，自行配置
# www.helloworld.com 域名需配合 dns hosts 进行配置
# 其中，api 开启了 cors，需配合本目录下另一份配置文件
# ----------------------------------------------------
upstream front_server{
  server www.helloworld.com:9000;
}
upstream api_server{
  server www.helloworld.com:8080;
}

server {
  listen       80;
  server_name  www.helloworld.com;

  location ~ ^/api/ {
    include enable-cors.conf;
    proxy_pass http://api_server;
    rewrite "^/api/(.*)$" /$1 break;
  }

  location ~ ^/ {
    proxy_pass http://front_server;
  }
}
```































