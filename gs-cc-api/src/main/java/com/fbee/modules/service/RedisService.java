package com.fbee.modules.service;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fbee.modules.redis.exception.RedisExcepiton;

import redis.clients.jedis.JedisPubSub;


/** 
* @ClassName: RedisService 
* @Description: 缓存服务接口
* @author 贺章鹏
* @date 2016年12月20日 下午6:24:45 
*  
*/
public interface RedisService {
	public static final String NEWS = "JC_NEWS";

	/**
	 * 推送消息
	 * 
	 * @param channel
	 * @param message
	 */
	public void publish(String channel, String message);

	/**
	 * 订阅消息
	 * 
	 * @param jedisPubSub
	 * @param channels
	 */
	public void subScribe(JedisPubSub jedisPubSub, String... channels);

	/**
	 * 初始化redis客户端
	 * 
	 * @param host
	 *            服务端IP
	 * @param port
	 *            端口
	 * @param password
	 *            密码
	 */
	public boolean init(String host, String port/*, String password*/);

	/**
	 * 将字符串永久性的存入redis,成功返回true，失败返回false 注意:key和value是string类型
	 * 
	 * @param <T>
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setStrValue(String key, String value);

	/**
	 * 取字符串
	 * 
	 * @param <T>
	 * @param key
	 * @param value
	 * @return
	 */
	public String getStrValue(String key) throws RedisExcepiton;

	/**
	 * 将对象永久性的存入redis,成功返回true，失败返回false 注意：必须是序列化的对象
	 * 
	 * @param <T>
	 * @param key
	 * @param value
	 * @return
	 */
	public <T extends Serializable> boolean setValue(String key, T value);

	/**
	 * 将对象存入redis，设置一个过期时间seconds,成功返回true，失败返回false 注意：必须是序列化的key和value
	 * 
	 * @param <T>
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	public <T extends Serializable> boolean setValue(String key, T value, int seconds);

	/**
	 * 通过key得到序列化后的对象 注意:key是String，和序列化的对象区分开来
	 * 
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T extends Serializable> T getValue(String key) throws RedisExcepiton;

	/**
	 * 给一个key设置过期时间,这个过期时间是以秒来计算，比如10秒后key过期
	 * 
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean setKeyExpireTime(String key, int seconds);

	/**
	 * 给一个key设置过期时间，这个过期时间是以1970年以来的秒数来计算
	 * 
	 * @param value
	 * @param seconds
	 * @return
	 */
	public boolean setKeyExpireAtTime(String key, long unixTime);

	/**
	 * 将一个key删除掉
	 * 
	 * @param key
	 * @return
	 */
	public long remove(String key);

	/**
	 * 如果key设置了过期时间，则可以获取key的存活时间
	 * 
	 * @param key
	 * @return
	 */
	public long getRemainingTimeByKey(String key);

	/**
	 * 判断key是否存在 存储的类型是对象字符串时适用
	 * 
	 * @param key
	 * @return boolean
	 * @throws com.rongzhong.cache.exception.CacheExcepiton
	 */
	public boolean exists(String key) throws RedisExcepiton;

	/**
	 * <p>
	 * 原子递增,用于统计
	 * </p>
	 * <p>
	 * 方法调用一次加一
	 * </p>
	 * 
	 * @param key
	 *            递增的值的模块代号
	 * @param field
	 *            递增的值
	 */
	public void increaseByKey(String key, String field);

	/**
	 * <p>
	 * 统计减少,用于清零
	 * </p>
	 * 
	 * @param key
	 *            递增的值的模块代号
	 * @param field
	 *            递增的值
	 * @param value
	 *            要减少的值
	 */
	public void delIncreaseByKey(String key, String field, Long value);

	/**
	 * <p>
	 * 得到统计数据
	 * </p>
	 * 
	 * @param key
	 *            递增的值的模块代号
	 * @param field
	 *            递增的值
	 */
	public Long getIncreaseByKey(String key, String field);

	/*
	 * set 操作接口
	 */

	/**
	 * Add the specified member to the set value stored at key. If member is
	 * already a member of the set no operation is performed. If key does not
	 * exist a new set with the specified member as sole member is created. If
	 * the key exists but does not hold a set value an error is returned.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @param member
	 * @return Integer reply, specifically: 1 if the new element was added 0 if
	 *         the element was already a member of the set
	 */
	public Long sAdd(final String key, final String value);

	/**
	 * Move the specifided member from the set at srckey to the set at dstkey.
	 * This operation is atomic, in every given moment the element will appear
	 * to be in the source or destination set for accessing clients.
	 * <p>
	 * If the source set does not exist or does not contain the specified
	 * element no operation is performed and zero is returned, otherwise the
	 * element is removed from the source set and added to the destination set.
	 * On success one is returned, even if the element was already present in
	 * the destination set.
	 * <p>
	 * An error is raised if the source or destination keys contain a non Set
	 * value.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param srckey
	 * @param dstkey
	 * @param member
	 * @return Integer reply, specifically: 1 if the element was moved 0 if the
	 *         element was not found on the first set and no operation was
	 *         performed
	 */
	public Long sMove(final String srckey, final String dstkey, final String member);

	/**
	 * Return the set cardinality (number of elements). If the key does not
	 * exist 0 is returned, like for empty sets.
	 * 
	 * @param key
	 * @return Integer reply, specifically: the cardinality (number of elements)
	 *         of the set as an integer.
	 */
	public Long sCard(final String key);

	/**
	 * Remove the specified member from the set value stored at key. If member
	 * was not a member of the set no operation is performed. If key does not
	 * hold a set value an error is returned.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @param member
	 * @return Integer reply, specifically: 1 if the new element was removed 0
	 *         if the new element was not a member of the set
	 */
	public Long sRem(final String key, final String member);

	/**
	 * Return all the members (elements) of the set value stored at key. This is
	 * just syntax glue for {@link #sinter(String...) SINTER}.
	 * <p>
	 * Time complexity O(N)
	 * 
	 * @param key
	 * @return Multi bulk reply
	 */
	public Set<String> sMembers(final String key);

	/*
	 * hash 操作接口
	 */

	/**
	 * 
	 * Set the specified hash field to the specified value.
	 * <p>
	 * If key does not exist, a new key holding a hash is created.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return If the field already exists, and the HSET just produced an update
	 *         of the value, 0 is returned, otherwise if a new field is created
	 *         1 is returned.
	 */
	public Long hSet(final String key, final String field, final String value);

	/**
	 * Return all the fields and associated values in a hash.
	 * <p>
	 * <b>Time complexity:</b> O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields and values contained into a hash.
	 */
	public Map<String, String> hgetAll(final String key);

	/**
	 * Remove the specified field from an hash stored at key.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @return If the field was present in the hash it is deleted and 1 is
	 *         returned, otherwise 0 is returned and no operation is performed.
	 */
	public Long hDel(final String key, final String field);

	/*
	 * Sorted set 操作接口
	 */
	/**
	 * Add the specified member having the specifeid score to the sorted set
	 * stored at key. If member is already a member of the sorted set the score
	 * is updated, and the element reinserted in the right position to ensure
	 * sorting. If key does not exist a new sorted set with the specified member
	 * as sole member is crated. If the key exists but does not hold a sorted
	 * set value an error is returned.
	 * <p>
	 * The score value can be the string representation of a double precision
	 * floating point number.
	 * <p>
	 * Time complexity O(log(N)) with N being the number of elements in the
	 * sorted set
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return Integer reply, specifically: 1 if the new element was added 0 if
	 *         the element was already a member of the sorted set and the score
	 *         was updated
	 */
	public Long zAdd(final String key, final String score, final String member);

	/**
	 * Remove the specified member from the sorted set value stored at key. If
	 * member was not a member of the set no operation is performed. If key does
	 * not not hold a set value an error is returned.
	 * <p>
	 * Time complexity O(log(N)) with N being the number of elements in the
	 * sorted set
	 * 
	 * 
	 * 
	 * @param key
	 * @param member
	 * @return Integer reply, specifically: 1 if the new element was removed 0
	 *         if the new element was not a member of the set
	 */
	public Long zRem(final String key, final String member);

	/**
	 * Return the sorted set cardinality (number of elements). If the key does
	 * not exist 0 is returned, like for empty sorted sets.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @return the cardinality (number of elements) of the set as an integer.
	 */
	public Long zCard(final String key);

	public Set<String> zrange(final String key, final int start, final int end);

	public Set<String> zrevrange(final String key, final int start, final int end);

	/**
	 * Remove all elements in the sorted set at key with rank between start and
	 * end. Start and end are 0-based with rank 0 being the element with the
	 * lowest score. Both start and end can be negative numbers, where they
	 * indicate offsets starting at the element with the highest rank. For
	 * example: -1 is the element with the highest score, -2 the element with
	 * the second highest score and so forth.
	 * <p>
	 * <b>Time complexity:</b> O(log(N))+O(M) with N being the number of
	 * elements in the sorted set and M the number of elements removed by the
	 * operation
	 * 
	 */
	public Long zRemrangeByRank(final String key, final int start, final int end);

	public Long del(final String... keys);
}
