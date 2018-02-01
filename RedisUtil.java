package com.wf.ew.core.utils;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * 
 * @author WangFan
 * @date 2018-01-28 上午11:03:50
 * @version 1.0 (GitHub: https://github.com/whvcse/RedisUtil )
 */
public class RedisUtil {
	private StringRedisTemplate redisTemplate;

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            过期时间
	 * @param unit
	 *            时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
	 *            秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
	 * @return
	 */
	public boolean set(String key, String value, long timeout, TimeUnit unit) {
		try {
			redisTemplate.opsForValue().set(key, value, timeout, unit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param offset
	 *            从指定位置开始覆写
	 * @return
	 */
	public boolean set(String key, String value, long offset) {
		try {
			redisTemplate.opsForValue().set(key, value, offset);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return 之前已经存在返回false,不存在返回true
	 */
	public boolean setIfAbsent(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	/**
	 * 批量添加
	 * 
	 * @param maps
	 * @return
	 */
	public boolean multiSet(Map<String, String> maps) {
		try {
			redisTemplate.opsForValue().multiSet(maps);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param maps
	 * @return 之前已经存在返回false,不存在返回true
	 */
	public boolean multiSetIfAbsent(Map<String, String> maps) {
		return redisTemplate.opsForValue().multiSetIfAbsent(maps);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 截取字符串
	 * 
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public String get(String key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	/**
	 * 批量获取
	 * 
	 * @param keys
	 * @return
	 */
	public List<String> multiGet(Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * 设置并返回旧值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String getAndSet(String key, String value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	/**
	 * 增加
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long increment(String key, long value) {
		return redisTemplate.opsForValue().increment(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Double increment(String key, double value) {
		return redisTemplate.opsForValue().increment(key, value);
	}

	/**
	 * 追加到末尾
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	/**
	 * 获取字符串的长度
	 * 
	 * @param key
	 * @return
	 */
	public Long size(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	/**
	 * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
	 * 
	 * @param key
	 * @param postion
	 *            位置
	 * @param value
	 *            值,true为1, false为0
	 * @return
	 */
	public boolean setBit(String key, long offset, boolean value) {
		return redisTemplate.opsForValue().setBit(key, offset, value);
	}

	/**
	 * 
	 * @param key
	 * @param offset
	 * @return true为1, false为0
	 */
	public Boolean getBit(String key, long offset) {
		return redisTemplate.opsForValue().getBit(key, offset);
	}

	/**
	 * 获取list
	 * 
	 * @param key
	 * @param start
	 *            开始位置, 0是开始位置
	 * @param end
	 *            结束位置, -1返回所有
	 * @return
	 */
	public List<String> listRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 裁剪list
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean listTrim(String key, long start, long end) {
		try {
			redisTemplate.opsForList().trim(key, start, end);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取list的大小
	 * 
	 * @param key
	 * @return
	 */
	public Long listSize(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 存储在list头部
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listLeftPush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listLeftPushAll(String key, String... value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listLeftPushAll(String key, Collection<String> value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 当list存在的时候才加入
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listLeftPushIfPresent(String key, String value) {
		return redisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	/**
	 * 如果pivot存在,再pivot前面添加
	 * 
	 * @param key
	 * @param pivot
	 * @param value
	 * @return
	 */
	public Long listLeftPush(String key, String pivot, String value) {
		return redisTemplate.opsForList().leftPush(key, pivot, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listRightPush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listRightPushAll(String key, String... value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listRightPushAll(String key, Collection<String> value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listRightPushIfPresent(String key, String value) {
		return redisTemplate.opsForList().rightPushIfPresent(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param pivot
	 * @param value
	 * @return
	 */
	public Long listRightPush(String key, String pivot, String value) {
		return redisTemplate.opsForList().rightPush(key, pivot, value);
	}

	/**
	 * 在index位置设置value
	 * 
	 * @param key
	 * @param index
	 *            位置
	 * @param value
	 * @return
	 */
	public boolean listSet(String key, long index, String value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除集合中值等于value得元素
	 * 
	 * @param key
	 * @param index
	 *            index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
	 *            index<0, 从尾部开始删除第一个值等于value的元素;
	 * @param value
	 * @return
	 */
	public Long listRemove(String key, long index, String value) {
		return redisTemplate.opsForList().remove(key, index, value);
	}

	/**
	 * 获取list第index位置的元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String listIndex(String key, long index) {
		return redisTemplate.opsForList().index(key, index);
	}

	/**
	 * 删除最左边元素
	 * 
	 * @param key
	 * @return 删除的元素
	 */
	public String listLeftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 删除最左边元素,如果集合没有元素则一直等待,直到超时
	 * 
	 * @param key
	 * @param timeout
	 *            等待时间
	 * @param unit
	 *            时间单位
	 * @return
	 */
	public String listLeftPop(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().leftPop(key, timeout, unit);
	}

	/**
	 * 删除最右边元素
	 * 
	 * @param key
	 * @return 删除的元素
	 */
	public String listRightPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 删除最右边元素,如果集合没有元素则一直等待,直到超时
	 * 
	 * @param key
	 * @param timeout
	 *            等待时间
	 * @param unit
	 *            时间单位
	 * @return
	 */
	public String listRightPop(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().rightPop(key, timeout, unit);
	}

	/**
	 * 删除最右边元素并且添加到另一个集合中
	 * 
	 * @param sourceKey
	 * @param destinationKey
	 * @return
	 */
	public String listRightPopAndLeftPush(String sourceKey,
			String destinationKey) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
				destinationKey);
	}

	/**
	 * 
	 * @param sourceKey
	 * @param destinationKey
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public String listRightPopAndLeftPush(String sourceKey,
			String destinationKey, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
				destinationKey, timeout, unit);
	}

	/**
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public boolean hashDelete(String key, Object... hashKeys) {
		try {
			redisTemplate.opsForHash().delete(key, hashKeys);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 是否有hashKey字段
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public boolean hashHasKey(String key, String hashKey) {
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}

	/**
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hashGet(String key, String hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 * 
	 * @param key
	 * @param hashKeys
	 * @return
	 */
	public List<Object> hashMultiGet(String key, Collection<Object> hashKeys) {
		return redisTemplate.opsForHash().multiGet(key, hashKeys);
	}

	/**
	 * 
	 * @param key
	 * @param hashKey
	 * @param delta
	 * @return
	 */
	public Long hashIncrement(String key, Object hashKey, long delta) {
		return redisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	/**
	 * 
	 * @param key
	 * @param hashKey
	 * @param delta
	 * @return
	 */
	public Double hashIncrement(String key, Object hashKey, double delta) {
		return redisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	/**
	 * 获取key对应的所有hashKey
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> hashKeys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Long hashSize(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 添加hash值
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean hashPut(String key, String hashKey, String value) {
		try {
			redisTemplate.opsForHash().put(key, hashKey, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @param maps
	 * @return
	 */
	public boolean hashPutAll(String key, Map<String, String> maps) {
		try {
			redisTemplate.opsForHash().putAll(key, maps);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 仅当hashKey不存在时才设置
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return
	 */
	public boolean hashPutIfAbsent(String key, String hashKey, String value) {
		try {
			redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据key获取所有值
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> hashValues(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 根据key获取所有hasyKey和值
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hashEntries(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Cursor<Entry<Object, Object>> hashScan(String key,
			ScanOptions options) {
		return redisTemplate.opsForHash().scan(key, options);
	}

	/**
	 * set添加元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long setAdd(String key, String... values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	/**
	 * set移除元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long setRemove(String key, Object... values) {
		return redisTemplate.opsForSet().remove(key, values);
	}

	/**
	 * 移除并返回集合的一个随机元素
	 * 
	 * @param key
	 * @return
	 */
	public String setPop(String key) {
		return redisTemplate.opsForSet().pop(key);
	}

	/**
	 * 将元素value从一个集合移到另一个集合
	 * 
	 * @param key
	 * @param value
	 * @param destKey
	 * @return
	 */
	public Boolean setMove(String key, String value, String destKey) {
		return redisTemplate.opsForSet().move(key, value, destKey);
	}

	/**
	 * 获取集合的大小
	 * 
	 * @param key
	 * @return
	 */
	public Long setSize(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 判断集合是否包含value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setIsMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 获取两个集合的交集
	 * 
	 * @param key
	 * @param otherKey
	 * @return
	 */
	public Set<String> setIntersect(String key, String otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的交集
	 * 
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> setIntersect(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().intersect(key, otherKeys);
	}

	/**
	 * key集合与多个集合的交集存储到destKey集合中
	 * 
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long setIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey,
				destKey);
	}

	/**
	 * key集合与多个集合的交集存储到destKey集合中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long setIntersectAndStore(String key, Collection<String> otherKeys,
			String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKeys,
				destKey);
	}

	/**
	 * 获取两个集合的并集
	 * 
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> setUnion(String key, String otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * 获取key集合与多个集合的并集
	 * 
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> setUnion(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的并集存储到destKey中
	 * 
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long setUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的并集存储到destKey中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long setUnionAndStore(String key, Collection<String> otherKeys,
			String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取两个集合的差集
	 * 
	 * @param key
	 * @param otherKey
	 * @return
	 */
	public Set<String> setDifference(String key, String otherKey) {
		return redisTemplate.opsForSet().difference(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的差集
	 * 
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> setDifference(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().difference(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的差集存储到destKey中
	 * 
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long setDifference(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKey,
				destKey);
	}

	/**
	 * key集合与多个集合的差集存储到destKey中
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long setDifference(String key, Collection<String> otherKeys,
			String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKeys,
				destKey);
	}

	/**
	 * 获取集合所有元素
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Set<String> setMembers(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 随机获取集合中的一个元素
	 * 
	 * @param key
	 * @return
	 */
	public String setRandomMember(String key) {
		return redisTemplate.opsForSet().randomMember(key);
	}

	/**
	 * 随机获取集合中count个元素
	 * 
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> setRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().randomMembers(key, count);
	}

	/**
	 * 随机获取集合中count个元素并且去除重复的
	 * 
	 * @param key
	 * @param count
	 * @return
	 */
	public Set<String> setDistinctRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(key, count);
	}

	/**
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Cursor<String> setScan(String key, ScanOptions options) {
		return redisTemplate.opsForSet().scan(key, options);
	}

	/**
	 * 添加元素,有序集合是按照元素的score值由小到大排列
	 * 
	 * @param key
	 * @param value
	 * @param score
	 * @return
	 */
	public Boolean zSetAdd(String key, String value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long zSetAdd(String key, Set<TypedTuple<String>> values) {
		return redisTemplate.opsForZSet().add(key, values);
	}

	/**
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long zSetRemove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	/**
	 * 增加元素的score值，并返回增加后的值
	 * 
	 * @param key
	 * @param value
	 * @param delta
	 * @return
	 */
	public Double zSetIncrementScore(String key, String value, double delta) {
		return redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	/**
	 * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
	 * 
	 * @param key
	 * @param value
	 * @return 0表示第一位
	 */
	public Long zSetRank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(key, value);
	}

	/**
	 * 返回元素在集合的排名,按元素的score值由大到小排列
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long zSetReverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	/**
	 * 获取集合的元素, 从小到大排序
	 * 
	 * @param key
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置, -1查询所有
	 * @return
	 */
	public Set<String> zSetRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * 获取集合元素, 并且把score值也获取
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<TypedTuple<String>> zSetRangeWithScores(String key, long start,
			long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素
	 * 
	 * @param key
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public Set<String> zSetRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从小到大排序
	 * 
	 * @param key
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public Set<TypedTuple<String>> zSetRangeByScoreWithScores(String key,
			double min, double max) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
	}

	/**
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<TypedTuple<String>> zSetRangeByScoreWithScores(String key,
			double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max,
				start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zSetReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序, 并返回score值
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<TypedTuple<String>> zSetReverseRangeWithScores(String key,
			long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start,
				end);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zSetReverseRangeByScore(String key, double min,
			double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<TypedTuple<String>> zSetReverseRangeByScoreWithScores(
			String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key,
				min, max);
	}

	/**
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zSetReverseRangeByScore(String key, double min,
			double max, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max,
				start, end);
	}

	/**
	 * 根据score值获取集合元素数量
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zSetCount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * 获取集合大小
	 * 
	 * @param key
	 * @return
	 */
	public Long zSetSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	/**
	 * 获取集合大小
	 * 
	 * @param key
	 * @return
	 */
	public Long zSetZCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	/**
	 * 获取集合中value元素的score值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Double zSetScore(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	/**
	 * 移除指定索引位置的成员
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zSetRemoveRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * 根据指定的score值的范围来移除成员
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zSetRemoveRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/**
	 * 获取key和otherKey的并集并存储在destKey中
	 * 
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long zSetUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long zSetUnionAndStore(String key, Collection<String> otherKeys,
			String destKey) {
		return redisTemplate.opsForZSet()
				.unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 交集
	 * 
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long zSetIntersectAndStore(String key, String otherKey,
			String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKey,
				destKey);
	}

	/**
	 * 交集
	 * 
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long zSetIntersectAndStore(String key, Collection<String> otherKeys,
			String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys,
				destKey);
	}

	/**
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Cursor<TypedTuple<String>> zSetScan(String key, ScanOptions options) {
		return redisTemplate.opsForZSet().scan(key, options);
	}
}
