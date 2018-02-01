# RedisUtil
Java操作Redis的工具类，使用StringRedisTemplate实现，封装了对Redis五种基本类型的各种操作！

## 用法
### 一、String数据类型操作
#### 1.添加操作：
```java
//
boolean set(String key, String value);

//timeout过期时间,unit时间单位(例:TimeUnit.SECONDS 秒)
boolean set(String key, String value, long timeout, TimeUnit unit);

//从指定位置开始覆写
boolean set(String key, String value, long offset);

//之前已经存在返回false,不存在返回true
boolean setIfAbsent(String key, String value);

//批量添加
boolean multiSet(Map<String, String> maps);

//之前已经存在返回false,不存在返回true
boolean multiSetIfAbsent(Map<String, String> maps);

//添加并且返回旧值
String getAndSet(String key, String value);

//设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value,true为1,false为0
boolean setBit(String key, long offset, boolean value);

```
   
#### 2.查询操作：
```java
//
String get(String key);

//截取字符串, start开始位置, end结束位置
String get(String key, long start, long end);

//批量获取
List<String> multiGet(Collection<String> keys);

//获取字符串长度
Long size(String key);

//获取第offset位ASCII码, 返回true表示1,返回false表示0
Boolean getBit(String key, long offset);

```
   
#### 3.修改操作：
```java
//增加(自增)
Long increment(String key, long value);

//
Double increment(String key, double value);

//拼接到末尾
Integer append(String key, String value);

```
   
### 二、list数据类型操作
#### 1.添加操作：
```java
//在list头部添加
Long listLeftPush(String key, String value);

//在list头部批量添加
Long listLeftPushAll(String key, String... value);

//在list头部批量添加
Long listLeftPushAll(String key, Collection<String> value);

//当list存在的时候才添加
Long listLeftPushIfPresent(String key, String value);

//在pivot前面添加(如果pivo存在)
Long listLeftPush(String key, String pivot, String value);


//在list尾部添加
Long listRightPush(String key, String value);

//在list尾部批量添加
Long listRightPushAll(String key, String... value);

//在list尾部批量添加
Long listRightPushAll(String key, Collection<String> value);

//当list存在的时候才添加
Long listRightPushIfPresent(String key, String value);

//在pivot后面添加(如果pivot存在)
Long listRightPush(String key, String pivot, String value);

//在index位置设置
boolean listSet(String key, long index, String value);


 
```

#### 2.获取操作：
```java
//查询list, start开始位置, end结束位置(-1可查询所有)
List<String> listRange(String key, long start, long end);

//获取index位置的元素
String listIndex(String key, long index);

//查询list大小
Long listSize(String key);


```

#### 3.修改操作：
```java
//裁剪list, start开始位置, end结束位置
boolean listTrim(String key, long start, long end);

/** 
 * 删除值等于value的元素, index=0, 删除所有值等于value的元素; 
 * index>0, 从头部开始删除第一个值等于value的元素;
 * index<0, 从尾部开始删除第一个值等于value的元素;
 */
Long listRemove(String key, long index, String value);

//删除最左边的元素
String listLeftPop(String key);

//删除最左边的元素, 如果集合没有元素就一直等待到有元素, timeout等待超时时间
String listLeftPop(String key, long timeout, TimeUnit unit);

//删除最右边元素
String listRightPop(String key);

//删除最右边元素，如果集合没有元素一直等待
String listRightPop(String key, long timeout, TimeUnit unit);

//删除最右边元素并且添加到另一个集合中, destinationKey另一个集合的key
String listRightPopAndLeftPush(String sourceKey, String destinationKey);

//同上, 如果不存在该元素,则一直等待,直到超时
String listRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit);


```

### 三、set数据类型操作
#### 1.添加操作：
```java

```

#### 2.获取操作：
```java

```

#### 3.修改操作：
```java

```

### 四、zset数据类型操作
#### 1.添加操作：
```java

```

#### 2.获取操作：
```java

```

#### 3.修改操作：
```java

```

### 五、hash数据类型操作
#### 1.添加操作：
```java
//添加
boolean hashPut(String key, String hashKey, String value);

//批量添加
boolean hashPutAll(String key, Map<String, String> maps);

//当hashKey不存在时才添加
boolean hashPutIfAbsent(String key, String hashKey, String value);

```

#### 2.获取操作：
```java
//获取
Object hashGet(String key, String hashKey);

//批量获取
List<Object> hashMultiGet(String key, Collection<Object> hashKeys);

//获取key对应的所有hashKey
Set<Object> hashKeys(String key);

//获取key的所有hash值
List<Object> hashValues(String key);

//根据key获取所有hasyKey和值
Map<Object, Object> hashEntries(String key);

//是否存在hashKey
boolean hashHasKey(String key, String hashKey);

//获取hashKey的数量
Long hashSize(String key);

//使用迭代器获取
Cursor<Entry<Object, Object>> hashScan(String key, ScanOptions options);
//示例:
Cursor<Map.Entry<Object, Object>> curosr = Cursor<Entry<Object, Object>> hashScan("key", ScanOptions.NONE);
while ( curosr.hasNext() ) {
  Map.Entry<Object, Object> entry = curosr.next();
  System.out.println(entry.getKey()+":"+entry.getValue());
}

```

#### 3.修改操作：
```java
//删除
boolean hashDelete(String key, Object... hashKeys);

//增加(自增)
Long hashIncrement(String key, Object hashKey, long delta);

//增加(自增)
Double hashIncrement(String key, Object hashKey, double delta);

```

## 知识补充
### 一、Redis知识补充
 

### 二、RedisTemplate和StringRedisTemplate
#### 1.RedisTemplate：

#### 2.StringRedisTemplate:

## 最后再说两句
