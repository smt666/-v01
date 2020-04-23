package cn.smt666.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.smt666.common.vo.SysResult;
import cn.smt666.jt.feign.CartFeign;
import cn.smt666.jt.pojo.Cart;

/**
 * 对外暴露的控制层类。
 * 
 * @author 27140
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartFeign cartFeign;
	
	/**
	 * 查看指定用户的购物车中的所有的商品信息。
	 * @param userId
	 * @return
	 */
	@RequestMapping("/query/{userId}")
	public SysResult myCart(@PathVariable Integer userId) {
		return cartFeign.myCart(userId);
	}
	
	/**
	 * 加入购物车。（如果曾经添加过，那么就现在修改购物车中商品的数量）。<br/>
	 * @param cart
	 * @return
	 */
	@RequestMapping("/save")
	public SysResult save(Cart cart) {
		return cartFeign.save(cart);
	}
	
	/**
	 * 修改指定用户购物车中商品的数量。<br/>
	 * @param cart
	 * @return
	 */
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(Cart cart) {
		return cartFeign.updateNum(cart);
	}
	
	/**
	 * 删除指定用户的购物车中的指定的商品。<br/>
	 * @param cart
	 * @return
	 */
	@RequestMapping("/delete/{userId}")
	public SysResult delete(Cart cart) {
		return cartFeign.delete(cart);
	}
}
