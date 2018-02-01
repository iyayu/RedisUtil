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
//添加
Long setAdd(String key, String... values);

```

#### 2.获取操作：
```java
//获取集合所有元素
Set<String> setMembers(String key);

//获取集合大小
Long setSize(String key);

//判断集合是否包含value
Boolean setIsMember(String key, Object value);

//随机获取集合中的一个元素
String setRandomMember(String key);

//随机获取集合count个元素
List<String> setRandomMembers(String key, long count);

//随机获取集合中count个元素并且去除重复的
Set<String> setDistinctRandomMembers(String key, long count);

//使用迭代器获取元素
Cursor<String> setScan(String key, ScanOptions options);

//-------------------------------------------------------------------------------------

//获取两个集合的交集
Set<String> setIntersect(String key, String otherKey);

//获取key集合与多个集合的交集
Set<String> setIntersect(String key, Collection<String> otherKeys);

//key集合与destKey集合的交集存储到destKey集合中
Long setIntersectAndStore(String key, String otherKey, String destKey);

//key集合与多个集合的交集存储到destKey集合中
Long setIntersectAndStore(String key, Collection<String> otherKeys, String destKey)

//--------------------------------------------------------------------------------------

//获取两个集合的并集
Set<String> setUnion(String key, String otherKeys);

//获取key集合与多个集合的并集
Set<String> setUnion(String key, Collection<String> otherKeys);

//key集合与otherKey集合的并集存储到destKey中
Long setUnionAndStore(String key, String otherKey, String destKey);

//key集合与多个集合的并集存储到destKey中
Long setUnionAndStore(String key, Collection<String> otherKeys, String destKey);

//-------------------------------------------------------------------------------------

//获取两个集合的差集
Set<String> setDifference(String key, String otherKey);

//获取key集合与多个集合的差集
Set<String> setDifference(String key, Collection<String> otherKeys);

//key集合与otherKey集合的差集存储到destKey中
Long setDifference(String key, String otherKey, String destKey);

//key集合与多个集合的差集存储到destKey中
Long setDifference(String key, Collection<String> otherKeys, String destKey);


```

#### 3.修改操作：
```java
//移除
Long setRemove(String key, Object... values);

//随机移除一个元素
String setPop(String key);

//将key集合中value元素移到destKey集合中
Boolean setMove(String key, String value, String destKey);

```

### 四、zset数据类型操作
#### 1.添加操作：
```java
//添加元素,有序集合是按照元素的score值由小到大排列
Boolean zSetAdd(String key, String value, double score);

//批量添加
Long zSetAdd(String key, Set<TypedTuple<String>> values);
//TypedTuple使用
TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>(value, score);

```

#### 2.获取操作：
```java
//获取集合的元素, 从小到大排序, start开始位置, end结束位置
Set<String> zSetRange(String key, long start, long end);

//获取集合元素, 并且把score值也获取
Set<TypedTuple<String>> zSetRangeWithScores(String key, long start, long end);

//根据Score值查询集合元素的值, 从小到大排序
Set<String> zSetRangeByScore(String key, double min, double max);

//根据Score值查询集合元素, 从小到大排序
Set<TypedTuple<String>> zSetRangeByScoreWithScores(String key, double min, double max);

//根据Score值查询集合元素, 从小到大排序
Set<TypedTuple<String>> zSetRangeByScoreWithScores(String key, double min, double max, long start, long end);

//----------------------------------------------------------------------------------

//获取集合的元素, 从大到小排序
Set<String> zSetReverseRange(String key, long start, long end);

//获取集合的元素, 从大到小排序, 并返回score值
Set<TypedTuple<String>> zSetReverseRangeWithScores(String key, long start, long end);

//根据Score值查询集合元素, 从大到小排序
Set<String> zSetReverseRangeByScore(String key, double min, double max);

//根据Score值查询集合元素, 从大到小排序
Set<TypedTuple<String>> zSetReverseRangeByScoreWithScores(String key, double min, double max);

//
Set<String> zSetReverseRangeByScore(String key, double min, double max, long start, long end);

//-----------------------------------------------------------------------------------

//返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
Long zSetRank(String key, Object value);

//返回元素在集合的排名,按元素的score值由大到小排列
Long zSetReverseRank(String key, Object value);

//根据score值获取集合元素数量
Long zSetCount(String key, double min, double max);

//获取集合大小
Long zSetSize(String key);

//获取集合大小
Long zSetZCard(String key);

//获取集合中value元素的score值
Double zSetScore(String key, Object value);

//------------------------------------------------------------------------------------

//获取key和otherKey的并集并存储在destKey中
Long zSetUnionAndStore(String key, String otherKey, String destKey);

//获取key和多个集合的并集并存储在destKey中
Long zSetUnionAndStore(String key, Collection<String> otherKeys, String destKey)

//-----------------------------------------------------------------------------------

//获取key和otherKey的交集并存储在destKey中
Long zSetIntersectAndStore(String key, String otherKey, String destKey);

//获取key和多个集合的交集并存储在destKey中
Long zSetIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

//-----------------------------------------------------------------------------------

//使用迭代器获取
Cursor<TypedTuple<String>> zSetScan(String key, ScanOptions options);

```

#### 3.修改操作：
```java
//移除
Long zSetRemove(String key, Object... values);

//增加元素的score值，并返回增加后的值
Double zSetIncrementScore(String key, String value, double delta);

//移除指定索引位置的成员
Long zSetRemoveRange(String key, long start, long end);

//根据指定的score值的范围来移除成员
Long zSetRemoveRangeByScore(String key, double min, double max);

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
Redis 可以存储键与5种不同数据结构类型之间的映射，这5种数据结构类型分别为：String（字符串）、List（列表）、Set（集合）、Hash（散列）和 Zset（有序集合）。
    

结构类型 | 结构存储的值 | 结构的读写能力
---|---|---
String | 可以是字符串、整数或者浮点数 | 对整个字符串或者字符串的其中一部分执行操作；对象和浮点数执行自增(increment)或者自减(decrement)
List | 一个链表，链表上的每个节点都包含了一个字符串 | 从链表的两端推入或者弹出元素；根据偏移量对链表进行修剪(trim)；读取单个或者多个元素；根据值来查找或者移除元素
Set | 包含字符串的无序收集器(unorderedcollection)，并且被包含的每个字符串都是独一无二的、各不相同 | 添加、获取、移除单个元素；检查一个元素是否存在于某个集合中；计算交集、并集、差集；从集合里卖弄随机获取元素
Hash | 包含键值对的无序散列表 | 添加、获取、移除单个键值对；获取所有键值对
ZSet | 字符串成员(member)与浮点数分值(score)之间的有序映射，元素的排列顺序由分值的大小决定 | 添加、获取、删除单个元素；根据分值范围(range)或者成员来获取元素


   
   
### 二、RedisTemplate和StringRedisTemplate
#### 1.RedisTemplate：

#### 2.StringRedisTemplate:

## 最后再说两句
