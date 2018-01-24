package com.fbee.modules.service.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fbee.modules.core.config.Global;
import com.fbee.modules.core.utils.StringUtils;
import com.fbee.modules.redis.exception.RedisExcepiton;
import com.fbee.modules.redis.utils.Encode;
import com.fbee.modules.service.RedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

/** 
* @ClassName: RedisServiceImpl 
* @Description: 缓存接口实现
* @author 贺章鹏
* @date 2016年12月20日 下午6:25:43 
*  
*/
@Service
public class RedisServiceImpl implements RedisService {

	private String host = "192.168.1.116";

	private int port = 6379;

	private String password = "";

	int dbNum = 0;

	private int timeout = 20000;

	private Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

	private Config poolConfig = new JedisPoolConfig();

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	// 消息推送
	public void publish(String channel, String message) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			jedis.publish(channel, message);
			if (logger.isDebugEnabled())
				logger.debug(" publish for channel :" + channel + " message " + message);
		} catch (JedisConnectionException e) {
			logger.error(" publish JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" publish JedisException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
	}

	// 消息订阅
	public void subScribe(JedisPubSub jedisPubSub, String... channels) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			jedis.subscribe(jedisPubSub, channels);
			if (logger.isDebugEnabled())
				logger.debug(" subScribe for channel :" + channels);
		} catch (JedisConnectionException e) {
			logger.error(" subScribe JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" subScribe JedisException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
	}

	// 初始化客户端
	public boolean init(String setHost, String setPort/*, String setPassword*/) {
		this.host = setHost;
		this.port = Integer.parseInt(setPort);
		/*this.password = setPassword;*/
		try {
			String maxActive = Global.getConfig("jedis.maxActive", "");
			String maxIdle = Global.getConfig("jedis.maxIdle");
			String minIdle = Global.getConfig("jedis.minIdle");
			String maxWait = Global.getConfig("jedis.maxWait");
			String minEvictableIdleTimeMillis = Global.getConfig("jedis.minEvictableIdleTimeMillis");
			String timeBetweenEvictionRunsMillis = Global.getConfig("jedis.timeBetweenEvictionRunsMillis");
			poolConfig.maxActive = Integer.parseInt(StringUtils.isBlank(maxActive) ? "1000" : maxActive.trim());
			poolConfig.maxIdle = Integer.parseInt(StringUtils.isBlank(maxIdle) ? "1000" : maxIdle.trim());
			poolConfig.minIdle = Integer.parseInt(StringUtils.isBlank(minIdle) ? "10" : minIdle.trim());
			poolConfig.maxWait = Integer.parseInt(StringUtils.isBlank(maxWait) ? "20000" : maxWait.trim());
			if (StringUtils.isNotBlank(minEvictableIdleTimeMillis))// 设定多长时间视为失效链接
				poolConfig.minEvictableIdleTimeMillis = Integer.parseInt(minEvictableIdleTimeMillis.trim());
			if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis))// 设定每隔多长时间进行有效检查与上面参数同时使用
				poolConfig.timeBetweenEvictionRunsMillis = Integer.parseInt(timeBetweenEvictionRunsMillis.trim());
			jedisPool = new JedisPool(poolConfig, host, port, timeout/*, password*/);
			return Boolean.TRUE;
		} catch (JedisConnectionException e) {
			logger.error(" JedisConnectionException :", e);
		} catch (NumberFormatException e) {
			logger.error(" NumberFormatException :", e);
		} catch (Exception e) {
			logger.error(" Exception :", e);
		}
		return Boolean.FALSE;
	}

	/**
	 * 选择哪个Redis库，默认选择序号为0的库，从0开始
	 * 
	 * @param int
	 * @return boolean
	 */
	public boolean selectRedisDB(int dbNum) {
		Jedis jedis = null;
		start();
		try {
			if (dbNum < 0)
				return Boolean.FALSE;
			jedis = jedisPool.getResource();
			this.dbNum = dbNum;
			String statusCode = jedis.select(dbNum);
			if (statusCode.equalsIgnoreCase("ok"))
				return Boolean.TRUE;
			if (logger.isDebugEnabled())
				logger.debug(" selectRedisDB " + dbNum + " ");
		} catch (JedisConnectionException e) {
			logger.error(" selectRedisDB JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" selectRedisDB JedisException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

	public boolean setStrValue(String key, String value) {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return Boolean.FALSE;
			}
			start();
			jedis = jedisPool.getResource();
			String flag = jedis.set(key, value);
			if (flag.equalsIgnoreCase("OK")) {
				if (logger.isDebugEnabled())
					logger.debug(" setValue successful,key is : " + key);
				return Boolean.TRUE;
			}
		} catch (JedisConnectionException e) {
			logger.error(" setValue JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" setValue JedisException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

	public String getStrValue(String key) throws RedisExcepiton {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return null;
			}
			start();
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (JedisConnectionException e) {
			throw new RedisExcepiton(" getStrValue  JedisException", e);
		} catch (JedisException e) {
			throw new RedisExcepiton("  getStrValue JedisException", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
	}

	/**
	 * 通过key得到对象
	 * 
	 * @return <T extends Serializable> T
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T getValue(String key) throws RedisExcepiton {
		Serializable decode = null;
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return null;
			}
			start();
			jedis = jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			if (jedis.exists(byteKey))
				decode = Encode.decode(jedis.get(byteKey));
			else
				return null;
		} catch (JedisConnectionException e) {
			throw new RedisExcepiton(" redis getValue JedisConnectionException", e);
		} catch (JedisException e) {
			throw new RedisExcepiton(" redis getValue JedisException", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return (T) decode;
	}

	public <T extends Serializable> boolean setValue(String key, T value) {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return Boolean.FALSE;
			}
			start();
			jedis = jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			byte[] byteValue = Encode.encode(value);
			String flag = jedis.set(byteKey, byteValue);
			if (flag.equalsIgnoreCase("OK")) {
				if (logger.isDebugEnabled())
					logger.debug(" setValue successful,key is : " + key);
				return Boolean.TRUE;
			}
		} catch (JedisConnectionException e) {
			logger.error(" setValue JedisConnectionException for key:" + key, e);
		} catch (JedisException e) {
			logger.error(" setValue JedisException for key:" + key, e);
		} catch (RedisExcepiton e) {
			logger.error(" setValue encode exception for key:" + key, e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * 将对象存入redis，有一个过期时间seconds,成功返回true，失败返回false
	 * 
	 * @param <T>
	 * @param int
	 * @return boolean
	 */
	public <T extends Serializable> boolean setValue(String key, T value, int seconds) {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return Boolean.FALSE;
			}
			start();
			jedis = jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			byte[] byteValue = Encode.encode(value);
			String flag = jedis.set(byteKey, byteValue);
			jedis.expire(byteKey, seconds);
			if (flag.equalsIgnoreCase("OK")) {
				if (logger.isDebugEnabled())
					logger.debug(" setValue successful,key is : " + key + " and timeout is " + seconds);
				return Boolean.TRUE;
			}
		} catch (JedisConnectionException e) {
			logger.error(" setValue JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" setValue JedisException:", e);
		} catch (RedisExcepiton e) {
			logger.error(" setValue encode exception for key:" + key, e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

	public boolean setKeyExpireTime(String key, int seconds) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			if (StringUtils.isNotBlank(key) && seconds > 0) {
				jedis.expire(key, seconds);
				return Boolean.TRUE;
			}
		} catch (JedisConnectionException e) {
			logger.error(" setKeyExpireTime JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" setKeyExpireTime JedisException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

	public boolean setKeyExpireAtTime(String key, long unixTime) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			if (StringUtils.isNotBlank(key) && unixTime > 0) {
				jedis.expireAt(key, unixTime);
				return Boolean.TRUE;
			}
		} catch (JedisConnectionException e) {
			logger.error(" setKeyExpireTime JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" setKeyExpireTime JedisException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

	public long remove(String key) {
		long number = 0;
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return number;
			}
			start();
			jedis = jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			number = jedis.del(byteKey);
		} catch (JedisConnectionException e) {
			logger.error(" remove JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" remove JedisException:", e);
		} catch (RedisExcepiton e) {
			logger.error(" remove encode exception for key:" + key, e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return number;
	}

	public long getRemainingTimeByKey(String key) {
		long seconds = 0;
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			if (StringUtils.isNotBlank(key) && jedis.exists(byteKey)) {
				seconds = jedis.ttl(key);
			}
		} catch (JedisConnectionException e) {
			logger.error(" getRemainingTimeByKey JedisConnectionException:", e);
		} catch (JedisException e) {
			logger.error(" getRemainingTimeByKey JedisException:", e);
		} catch (RedisExcepiton e) {
			logger.error(" getRemainingTimeByKey encode exception for key:" + key, e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return seconds;
	}

	public boolean exists(String key) throws RedisExcepiton {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			if (jedis.exists(byteKey)) {
				return Boolean.TRUE;
			}
		} catch (JedisConnectionException e) {
			throw new RedisExcepiton(" redis exists JedisConnectionException", e);
		} catch (JedisException e) {
			throw new RedisExcepiton(" redis exists JedisException", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE;
	}

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
	public void increaseByKey(String key, String field) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			// 原子递增
			Long count = jedis.hincrBy(key, field, 1);
			jedis.hset(key, field, String.valueOf(count));
		} catch (JedisConnectionException e) {
			logger.error(" increaseByKey JedisConnectionException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
	}

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
	public void delIncreaseByKey(String key, String field, Long value) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			Long count = jedis.hincrBy(key, field, value > 0 ? 0L - value : value);
			jedis.hset(key, field, String.valueOf(count));
		} catch (JedisConnectionException e) {
			logger.error(" delIncreaseByKey JedisConnectionException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
	}

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
	public Long getIncreaseByKey(String key, String field) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			if (jedis.hexists(key, field)) {
				try {
					return Long.valueOf(jedis.hget(key, field));
				} catch (NumberFormatException e) {
					logger.error(" getIncreaseByKey NumberFormatException:", e);
					return 0L;
				}
			}
		} catch (JedisConnectionException e) {
			logger.error(" getIncreaseByKey JedisConnectionException:", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return 0L;
	}

	// 以下接口在返回null时表示接口调用异常
	public Long sAdd(String key, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long sMove(String srckey, String dstkey, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(srckey) || StringUtils.isBlank(dstkey)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.smove(srckey, dstkey, member);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}

		return null;
	}

	public Long sCard(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.scard(key);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long sRem(String key, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.srem(key, member);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Set<String> sMembers(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long hSet(String key, String field, String value) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long hDel(String key, String field) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(key, field);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}

		return null;
	}

	public Long zAdd(String key, String score, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.zadd(key, Double.valueOf(score), member);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long zRem(String key, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.zrem(key, member);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long zCard(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Set<String> zrange(String key, int start, int end) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.zrange(key, start, end);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	@Override
	public Set<String> zrevrange(String key, int start, int end) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long zRemrangeByRank(String key, int start, int end) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	@Override
	public Long del(String... keys) {
		Jedis jedis = null;
		start();
		try {
			jedis = jedisPool.getResource();
			return jedis.del(keys);
		} catch (Exception e) {
			logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	private volatile boolean isRun = Boolean.FALSE;

	private void start() {
		if (!isRun) {
			/*this.init(Global.getConfig("jedis_host", "192.168.1.107"),
					Global.getConfig("jedis_port", "6379"),
					Global.getConfig("jedis_password", ""));*/
			this.init(Global.getConfig("jedis_host", "192.168.1.107"),
					Global.getConfig("jedis_port", "6379"));
			isRun = !isRun;
		}
	}


}
