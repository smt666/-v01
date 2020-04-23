package cn.smt666.jt.service;

import java.util.List;

import cn.smt666.common.vo.SysResult;
import cn.smt666.jt.pojo.Cart;

public interface CartService {

	/**
	 * 根据用户ID查询购物车的数据。
	 * @param userId
	 * @return
	 */
	public List<Cart> myCart(Integer userId);
	
	/**
	 * <font color="red">添加商品到购物车。</font><br/><br/>
	 * <b>实现步骤：</b><br/>&nbsp;&nbsp;
	 * （<b>1</b>）、<font color="blue">先判断指定的这个用户的（将要添加到购物车中的这件）商品是否（存在）曾经有添加过：</font></br>&nbsp;&nbsp;
	 * （<b>2</b>）、如果<font color="blue">存在</font>：那么此时添加这件商品的操作实际上是一个&nbsp;<font color="red" size="4">修改 update</font>（购物车此商品数量）的操作。</br>&nbsp;&nbsp;
	 * （<b>3</b>）、如果<font color="blue">不存在</font>：那么此时添加这件商品的操作就是一个&nbsp;<font color="red" size="4">新增 insert</font>（这件商品到购物车）的操作。
	 * @param cart
	 * @return 执行save操作后的处理结果。
	 */
	public SysResult save(Cart cart);
	
	/**
	 * 更新指定用户的指定的商品的数量。
	 * @param cart
	 */
	public void updateNum(Cart cart);
	
	/**
	 * 删除指定用户的指定的商品信息。
	 * @param cart
	 */
	public void delete(Cart cart);
}
