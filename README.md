# RedisUtil

#### 当前版本：1.1
    增加更全的方法，对以前的部分方法进行了规范命名，请放心替换成新版本。 
          
          
[Redis知识补充](#知识补充) <br/>
[Redis与Spring集成](#三redis与spring的集成) <br/>
[查看RedisUtil源代码](https://github.com/whvcse/RedisUtil/blob/master/RedisUtil.java)

## 介绍
最全的Java操作Redis的工具类，使用StringRedisTemplate实现，封装了对Redis五种基本类型的各种操作，力求符合Redis的原生操作！
<br/>
分为六个部分：[keys命令操作](#一keys相关命令)、[String数据操作](#二string数据类型操作)、[Hash数据操作](#三hash相关的操作)、[List数据操作](#四list相关的操作)、[Set数据操作](#五set相关的操作)、[zSet数据操作](#六zset数据类型操作)。 
 

## 注意 
如果你比较关心为什么它没有提供存储Object的方法，请看这里介绍[存Object为什么不好？](#二redistemplate和stringredistemplate) 
 
## 用法
### 一、keys相关命令

| 序号 | 方法                                                    | 描述                                          |
|:----:|---------------------------------------------------------|-----------------------------------------------|
|   1  | void delete(String key)                                 | key 存在时删除 key                            |
|   2  | void delete(Collection keys)                            | 批量删除key                                   |
|   3  | byte[] dump(String key)                                 | 序列化给定 key ，并返回被序列化的值           |
|   4  | Boolean hasKey(String key)                              | 检查给定 key 是否存在                         |
|   5  | Boolean expire(String key, long timeout, TimeUnit unit) | 设置过期时间                                  |
|   6  | Boolean expireAt(String key, Date date)                 | 设置过期时间                                  |
|   7  | Set<String> keys(String pattern)                        | 查找所有符合给定模式( pattern)的 key          |
|   8  | Boolean move(String key, int dbIndex)                   | 将当前数据库的 key 移动到给定的数据库 db 当中 |
|   9  | Boolean persist(String key)                             | 移除 key 的过期时间，key 将持久保持           |
|  10  | Long getExpire(String key, TimeUnit unit)               | 返回 key 的剩余的过期时间                     |
|  11  | Long getExpire(String key)                              | 返回 key 的剩余的过期时间                     |
| 12   | String randomKey()                                      | 从当前数据库中随机返回一个 key                |
| 13   | void rename(String oldKey, String newKey)               | 修改 key 的名称                               |
| 14   | Boolean renameIfAbsent(String oldKey, String newKey)    | 仅当 newkey 不存在时，将 oldKey 改名为 newkey |
| 15   | DataType type(String key)                               | 返回 key 所储存的值的类型                     |
   
-----

### 二、String数据类型操作

| 序号 | 方法                                                              | 描述                                     |
|:----:|-------------------------------------------------------------------|------------------------------------------|
|   1  | String get(String key)                                            | 获取指定 key 的值                        |
|   2  | String getRange(String key, long start, long end)                 | 返回 key 中字符串值的子字符              |
|   3  | String getAndSet(String key, String value)                        | 将给定 key 的值设为 value ，并返回key<br/>的旧值(old value)|
|   4  | Boolean getBit(String key, long offset)                           | 对 key 所储存的字符串值，获取指定偏移<br/>量上的位(bit)    |
|   5  | List multiGet(Collection keys)                                    | 批量获取                                 |
|  |  |  |
|   6  | void set(String key, String value)                                | 设置指定 key 的值                        |
|   7  | boolean setBit(String key, long offset, boolean value)            | 设置ASCII码, 字符串'a'的ASCII码是97, 转<br/>为二进制是'01100001', 此方法是将<br/>二进制第offset位值变为value |
|   8  | void setEx(String key, String value, long timeout, TimeUnit unit) | 将值 value 关联到 key ，并将 key 的过期<br/>时间设为 timeout,unit:时间单位, <br/>天:TimeUnit.DAYS 小时:TimeUnit.HOURS <br/>分钟:TimeUnit.MINUTES,<br/>秒:TimeUnit.SECONDS <br/>毫秒:TimeUnit.MILLISECONDS |
|   9  | boolean setIfAbsent(String key, String value)                     | 只有在 key 不存在时设置 key 的值         |
|  10  | void setRange(String key, String value, long offset)              | 用 value 参数覆写给定 key 所储存的字符串<br/>值，从偏移量 offset 开始 |
|  11  | void multiSet(Map<String,String> maps)                            | 批量添加                                 |
|  12  | boolean multiSetIfAbsent(Map<String,String> maps)                 | 同时设置一个或多个 key-value 对，当且仅<br/>当所有给定 key 都不存在   |
|  |  |  |
|  13  | Integer append(String key, String value)                          | 追加到末尾                               |
|  14  | Long incrBy(String key, long increment)                           | 增加(自增长), 负数则为自减               |
|  15  | Double incrByFloat(String key, double increment)                  | 增加(自增长), 负数则为自减               |
|  16  | Long size(String key)                                             | 获取字符串的长度                         |

------

### 三、Hash相关的操作 

| 序号 | 方法                                                           | 描述                                                |
|:----:|----------------------------------------------------------------|-----------------------------------------------------|
|   1  | Object hGet(String key, String field)                          | 获取存储在哈希表中指定字段的值                      |
|   2  | Map hGetAll(String key)                                        | 获取所有给定字段的值                                |
|   3  | List hMultiGet(String key, Collection fields)                  | 获取所有给定字段的值                                |
|  |  |  |
|   4  | void hPut(String key, String hashKey, String value)            | 添加字段                                            |
|   5  | void hPutAll(String key, Map maps)                             | 添加多个字段                                        |
|   6  | Boolean hPutIfAbsent(String key, String hashKey, String value) | 仅当hashKey不存在时才设置                           |
|  |  |  |
|   7  | Long hDelete(String key, Object... fields)                     | 删除一个或多个哈希表字段                            |
|   8  | boolean hExists(String key, String field)                      | 查看哈希表 key 中，指定的字段是<br/>否存在               |
|   9  | Long hIncrBy(String key, Object field, long increment)         | 为哈希表 key 中的指定字段的整数<br/>值加上增量 increment |
|  10  | Double hIncrByFloat(String key, Object field, double delta)    | 为哈希表 key 中的指定字段的整数<br/>值加上增量 increment |
|  11  | Set hKeys(String key)                                          | 获取所有哈希表中的字段                              |
|  12  | Long hSize(String key)                                         | 获取哈希表中字段的数量                              |
|  13  | List hValues(String key)                                       | 获取哈希表中所有值                                  |
|  14  | Cursor hScan(String key, ScanOptions options)                  | 迭代哈希表中的键值对                                |

---

### 四、List相关的操作

| 序号 | 方法                                                     | 描述                                |
|:----:|----------------------------------------------------------|-------------------------------------|
|   1  | String lIndex(String key, long index)                    | 通过索引获取列表中的元素            |
|   2  | List lRange(String key, long start, long end)            | 获取列表指定范围内的元素            |
|      |                                                          |                                     |
|   3  | Long lLeftPush(String key, String value)                 | 存储在list头部                      |
|   4  | Long lLeftPushAll(String key, String... value)           | 存储在list头部                      |
|   5  | Long lLeftPushAll(String key, Collection value)          | 存储在list头部                      |
|   6  | Long lLeftPushIfPresent(String key, String value)        | 当list存在的时候才加入              |
|   7  | lLeftPush(String key, String pivot, String value)        | 如果pivot存在,再pivot前面添加       |
|      |                                                          |                                     |
|   8  | Long lRightPush(String key, String value)                | 存储在list尾部                      |
|   9  | Long lRightPushAll(String key, String... value)          | 存储在list尾部                      |
|  10  | Long lRightPushAll(String key, Collection value)         | 存储在list尾部                      |
|  11  | Long lRightPushIfPresent(String key, String value)       | 当list存在的时候才加入              |
|  12  | lRightPush(String key, String pivot, String value)       | 在pivot元素的右边添加值             |
|      |                                                          |                                     |
|  13  | void lSet(String key, long index, String value)          | 通过索引设置列表元素的值            |
|      |                                                          |                                     |
|  14  | String lLeftPop(String key)                              | 移出并获取列表的第一个元素          |
|  15  | String lBLeftPop(String key, long timeout, TimeUnit unit) | 移出并获取列表的第一个元素， 如果列<br/>表没有元素会阻塞列表直到等待超时或<br/>发现可弹出元素为止 |
|      |                                                          |                                     |
|  16  | String lRightPop(String key)                             | 移除并获取列表最后一个元素          |
| 17   | String lBRightPop(String key, long timeout, TimeUnit unit) | 移出并获取列表的最后一个元素， 如<br/>果列表没有元素会阻塞列表直到等待超时<br/>或发现可弹出元素为止   |
| 18   | String lRightPopAndLeftPush(String sourceKey, String destinationKey) | 移除列表的最后一个元素，<br/>并将该元素添加到另一个列表并返回  |
| 19   | String lBRightPopAndLeftPush(String sourceKey, String destinationKey,,long timeout, TimeUnit unit) | 从列表中弹出一个值，将弹出的元素插入到<br/>另外一个列表中并返回它； 如果列表没<br/>有元素会阻塞列表直到等待超时或发现可弹出<br/>元素为止 |
|      |                                                          |                                     |
| 20   | Long lRemove(String key, long index, String value)       | 删除集合中值等于value得元素         |
| 21   | void lTrim(String key, long start, long end)             | 裁剪list                            |
| 22   | Long lLen(String key)                                    | 获取列表长度                        |

-----

### 五、Set相关的操作
#### 1.添加操作：
```java
//添加
Long sAdd(String key, String... values);

```

#### 2.获取操作：
```java
//获取集合所有元素
Set<String> sMembers(String key);

//获取集合大小
Long sSize(String key);

//判断集合是否包含value
Boolean sIsMember(String key, Object value);

//随机获取集合中的一个元素
String sRandomMember(String key);

//随机获取集合count个元素
List<String> sRandomMembers(String key, long count);

//随机获取集合中count个元素并且去除重复的
Set<String> sDistinctRandomMembers(String key, long count);

//使用迭代器获取元素
Cursor<String> sScan(String key, ScanOptions options);

//-------------------------------------------------------------------------------------

//获取两个集合的交集
Set<String> sIntersect(String key, String otherKey);

//获取key集合与多个集合的交集
Set<String> sIntersect(String key, Collection<String> otherKeys);

//key集合与destKey集合的交集存储到destKey集合中
Long sIntersectAndStore(String key, String otherKey, String destKey);

//key集合与多个集合的交集存储到destKey集合中
Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey)

//--------------------------------------------------------------------------------------

//获取两个集合的并集
Set<String> sUnion(String key, String otherKeys);

//获取key集合与多个集合的并集
Set<String> sUnion(String key, Collection<String> otherKeys);

//key集合与otherKey集合的并集存储到destKey中
Long sUnionAndStore(String key, String otherKey, String destKey);

//key集合与多个集合的并集存储到destKey中
Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey);

//-------------------------------------------------------------------------------------

//获取两个集合的差集
Set<String> sDifference(String key, String otherKey);

//获取key集合与多个集合的差集
Set<String> sDifference(String key, Collection<String> otherKeys);

//key集合与otherKey集合的差集存储到destKey中
Long sDifference(String key, String otherKey, String destKey);

//key集合与多个集合的差集存储到destKey中
Long sDifference(String key, Collection<String> otherKeys, String destKey);


```

#### 3.修改操作：
```java
//移除
Long sRemove(String key, Object... values);

//随机移除一个元素
String sPop(String key);

//将key集合中value元素移到destKey集合中
Boolean sMove(String key, String value, String destKey);

```

### 六、zset数据类型操作
#### 1.添加操作：
```java
//添加元素,有序集合是按照元素的score值由小到大排列
Boolean zAdd(String key, String value, double score);

//批量添加
Long zAdd(String key, Set<TypedTuple<String>> values);
//TypedTuple使用
TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>(value, score);

```

#### 2.获取操作：
```java
//获取集合的元素, 从小到大排序, start开始位置, end结束位置
Set<String> zRange(String key, long start, long end);

//获取集合元素, 并且把score值也获取
Set<TypedTuple<String>> zRangeWithScores(String key, long start, long end);

//根据Score值查询集合元素的值, 从小到大排序
Set<String> zRangeByScore(String key, double min, double max);

//根据Score值查询集合元素, 从小到大排序
Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max);

//根据Score值查询集合元素, 从小到大排序
Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end);

//----------------------------------------------------------------------------------

//获取集合的元素, 从大到小排序
Set<String> zReverseRange(String key, long start, long end);

//获取集合的元素, 从大到小排序, 并返回score值
Set<TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end);

//根据Score值查询集合元素, 从大到小排序
Set<String> zReverseRangeByScore(String key, double min, double max);

//根据Score值查询集合元素, 从大到小排序
Set<TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max);

//
Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end);

//-----------------------------------------------------------------------------------

//返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
Long zRank(String key, Object value);

//返回元素在集合的排名,按元素的score值由大到小排列
Long zReverseRank(String key, Object value);

//根据score值获取集合元素数量
Long zCount(String key, double min, double max);

//获取集合大小
Long zSize(String key);

//获取集合大小
Long zZCard(String key);

//获取集合中value元素的score值
Double zScore(String key, Object value);

//------------------------------------------------------------------------------------

//获取key和otherKey的并集并存储在destKey中
Long zUnionAndStore(String key, String otherKey, String destKey);

//获取key和多个集合的并集并存储在destKey中
Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey)

//-----------------------------------------------------------------------------------

//获取key和otherKey的交集并存储在destKey中
Long zIntersectAndStore(String key, String otherKey, String destKey);

//获取key和多个集合的交集并存储在destKey中
Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

//-----------------------------------------------------------------------------------

//使用迭代器获取
Cursor<TypedTuple<String>> zScan(String key, ScanOptions options);

```

#### 3.修改操作：
```java
//移除
Long zRemove(String key, Object... values);

//增加元素的score值，并返回增加后的值
Double zIncrementScore(String key, String value, double delta);

//移除指定索引位置的成员
Long zRemoveRange(String key, long start, long end);

//根据指定的score值的范围来移除成员
Long zRemoveRangeByScore(String key, double min, double max);

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
二者主要区别是他们使用的序列化类不一样，RedisTemplate使用的是JdkSerializationRedisSerializer， StringRedisTemplate使用的是StringRedisSerializer，两者的数据是不共通的。

#### 1.RedisTemplate：
   RedisTemplate使用的是JDK的序列化策略，向Redis存入数据会将数据先序列化成字节数组然后在存入Redis数据库，这个时候打开Redis查看的时候，你会看到你的数据不是以可读的形式展现的，而是以字节数组显示，类似下面：\xAC\xED\x00\x05t\x05sr\x00。 
   所以使用RedisTemplate可以直接把一个java对象直接存储在redis里面，但是存进去的数据是不易直观的读的，不通用的，建议最好不要直接存一个Object对象，可以变成Hash来存储，也可以转成json格式的数据来存储，在实际应用中也是很多都采用json格式来存储的。
   
#### 2.StringRedisTemplate:
   StringRedisTemplate默认采用的是String的序列化策略，保存的key和value都是采用此策略序列化保存的。StringRedisTemplate是继承RedisTemplate的，这种对redis的操方式更优雅，因为RedisTemplate以字节数组的形式存储不利于管理，也不通用。
 
                 
		           
			                
### 三、Redis与Spring的集成
1.集成配置
```java
<bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
	<property name="maxIdle" value="300" />
	<property name="maxTotal" value="600" />
	<property name="maxWaitMillis" value="1000" />
	<property name="testOnBorrow" value="true" />
</bean>

<bean id="jedisConnectionFactory"
	class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
	<property name="hostName" value="127.0.0.1" />
	<property name="password" value="WangFan01!" />
	<property name="port" value="6379" />
	<property name="poolConfig" ref="poolConfig" />
</bean>

<bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
	<property name="connectionFactory" ref="jedisConnectionFactory" />
</bean>

<!-- 这里可以配置多个redis -->
<bean id="redisUtil" class="com.wf.ew.core.utils.RedisUtil">
	<property name="redisTemplate" ref="redisTemplate" />
</bean>
```
2.使用RedisUtil工具类方法如下：
```java
@Autowired
private RedisUtil redisUtil;
```
