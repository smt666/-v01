package cn.smt666.jt.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.smt666.common.vo.SysResult;
import cn.smt666.jt.pojo.Cart;

/**
 * 消费者。
 * @author 27140
 *
 */
@FeignClient(value = "jt-cart-provider")
public interface CartFeign {

	/**
	 * 查看指定用户的购物车中的所有的商品信息。
	 * @param userId
	 * @return
	 */
	@RequestMapping("/cart/query/{userId}")
	public SysResult myCart(@PathVariable("userId") Integer userId);
	
	/**
	 * 加入购物车。（如果曾经添加过，那么就现在修改购物车中商品的数量）。<br/>
	 * Feign获取对象中的参数时，可以使用【@RequestBody】注解：把json转换成Java对象。
	 * @param cart
	 * @return
	 */
	@RequestMapping("/cart/save")
	public SysResult save(@RequestBody Cart cart);
	
	/**
	 * 为了使得请求的参数，可以被Feign支持，所我们把原先RestFul风格的url请求，按照JSON格式的方式来接收请求。<br/>
	 * 即：url请求的参数，不再采用RestFul风格，处理请求的方法的参数添加了@RequestBody注解，它表明了接收方是以json的格式来接收参数的。<br/>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
	 *    注意，这样一来，相对于的【提供者】，也必须使用JSON这样的方式（即也必须添加这个@RequestBody注解）。</font><br/>
	 * 修改指定用户购物车中商品的数量。<br/>
	 * Feign获取对象中的参数时，可以使用【@RequestBody】注解：把json转换成Java对象。
	 * @param cart
	 * @return
	 */
	@RequestMapping("/cart/update/num")
	public SysResult updateNum(@RequestBody Cart cart);
	
	/**
	 * 删除指定用户的购物车中的指定的商品。<br/>
	 * Feign获取对象中的参数时，可以使用【@RequestBody】注解：把json转换成Java对象。
	 * @param cart
	 * @return
	 */
	@RequestMapping("/cart/delete")
	public SysResult delete(@RequestBody Cart cart);
}
