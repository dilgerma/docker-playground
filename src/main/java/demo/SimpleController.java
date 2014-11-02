package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Martin Dilger
 * @since: 02.11.14
 */
@RestController
public class SimpleController
{

	private static final Logger LOG = LoggerFactory.getLogger(SimpleController.class);

	private RedisTemplate<String, String> template;

	@Autowired
	public SimpleController(StringRedisTemplate template)
	{
		System.out.println("!!!!! Controller created");
		this.template = template;
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Integer readValue() {
		System.out.println("!!!!!! called");
		RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger("cnt", template.getConnectionFactory());
		return redisAtomicInteger.incrementAndGet();
	}

	@ExceptionHandler
	public void handle(Exception e) {
		System.out.println("Error happened");
		LOG.error("blubb", e);
	}
}
