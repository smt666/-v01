package cn.smt666.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.smt666.common.vo.SysResult;
import cn.smt666.jt.pojo.Cart;
import cn.smt666.jt.service.CartService;

/**
 * 提供者的CRUD增删改查操作。
 * 
 * @author smt666
 * @Date 2020-04-21
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	/**
	 * 根据用户ID查询购物车信息。<br/><br/>
	 * 当前方法处理"/user/myCart"请求，把查询的结果按照JSON格式字符串类型，响应给网页。<br/>
	 * <font color="red">原本try-catch方式，但是现在已经被我们的Hystrix断路器替代了。所以，我们可以直接返回一个正确的返回形式进行返回即可。</font>
	 * @param userId 需要查询的指定用户的ID（我们会根据这个用户ID来查询这个用户名下的购物车中保存的信息）。
	 * @return JSON格式的字符串数据：指定用户的购物车中的信息。
	 */
	@RequestMapping("/query/{userId}")
	public SysResult myCart(@PathVariable Integer userId) {
		// 原本try-catch方式，但是现在已经被我们的Hystrix断路器替代了。所以，我们可以直接返回一个正确的结果即可。
		return SysResult.oK(cartService.myCart(userId));
	}
	
	/**
	 * 添加到购物车。<br/><br/>
	 * 为了使得请求的参数，可以<font color="red">被Feign支持</font>，所我们把原先RestFul风格的url请求，按照<font color="red">JSON格式</font>的方式来接收请求。<br/>
	 * （即<font color="blue" size="3">方法的参数</font>也<font color="red" size="2">必须添加这个<font color="blue" size="3">@RequestBody</font>注解</font>）
	 * @param cart
	 * @return
	 */
	@RequestMapping("/save")
	public SysResult save(@RequestBody Cart cart) {
		return cartService.save(cart);
	}
	
	/**
	 * 更新购物车数据表中商品的数量。<br/><br/>
	 * 为了使得请求的参数，可以<font color="red">被Feign支持</font>，所我们把原先RestFul风格的url请求，按照<font color="red">JSON格式</font>的方式来接收请求。<br/>
	 * （即<font color="blue" size="3">方法的参数</font>也<font color="red" size="2">必须添加这个<font color="blue" size="3">@RequestBody</font>注解</font>）
	 * @param cart
	 * @return
	 */
	@RequestMapping("/update/num")
	public SysResult updateNum(@RequestBody Cart cart) {
		cartService.updateNum(cart);
		return SysResult.oK();
	}
	
	/**
	 * 删除指定用户的购物车中的指定的商品信息。<br/><br/>
	 * 为了使得请求的参数，可以<font color="red">被Feign支持</font>，所我们把原先RestFul风格的url请求，按照<font color="red">JSON格式</font>的方式来接收请求。<br/>
	 * （即<font color="blue" size="3">方法的参数</font>也<font color="red" size="2">必须添加这个<font color="blue" size="3">@RequestBody</font>注解</font>）
	 * @param cart
	 * @return
	 */
	@RequestMapping("/delete")
	public SysResult delete(@RequestBody Cart cart) {
		cartService.delete(cart);
		return SysResult.oK();
	}

}
