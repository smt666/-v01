package cn.smt666.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.smt666.common.vo.SysResult;
import cn.smt666.jt.mapper.CartMapper;
import cn.smt666.jt.pojo.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> myCart(Integer userId) {
		return cartMapper.myCart(userId);
	}
	
	/** 添加商品到购物车。（如果曾经有添加过的话：则修改商品的数量） */
	@Override
	public SysResult save(Cart cart) {
		// 1、判断。判断此用户的此次将要添加到购物车中的这件商品，是否已经存在（如果曾经有添加过那么就存在了）。
		/**
		 * 此处我们为什么要new一个Cart对象？
		 * 答：我们使用的这个MyBatisPlus它是以POJO作为where条件进行查询数据表的。
		 * 查询规则是，【参数!=null】，如此一来，如果我们直接将cart对象作为参数，那么cart中有诸多参数，都是不为null的，那么查询结果就会不符合业务需求了。
		 * 而我们仅仅只希望将userId与itemId作为查询的where条件，所以，我们在此处new一个新的cart对象，并且只封装了userId与itemId然后进行查询。
		 */
		Cart param = new Cart();
		param.setUserId(cart.getUserId());
		param.setItemId(cart.getItemId());
		//》封装了userId后进行查询，此处只会把这个封装到新对象cart中的userId作为查询条件进行查询了。
		Cart oldCartDate = cartMapper.selectOne(param);
		// 判断。
		if (oldCartDate != null) {
			// 2、如果此商品在购物车中是存在的话，那么当前这个save就是一个修改update（次商品在购物车中数量）的操作。
			// 修改 = 数据库的库存数量 + 页面数量
			oldCartDate.setNum(oldCartDate.getNum() + cart.getNum());
			// 设置修改的时间。
			oldCartDate.setUpdated(new Date());
			/**
			 * EntityWrapper对象的职责，就是圣旨where条件。
			 * ew.where("id={0}", oldCartDate.getId());
			 * ==================
			 * 其中： where语句部分为
			 *    WHERE id=xxx
			 * {0} 它就相当于？,一个占位符，数字0代表第1个？的位置，即第一个参数。
			 * 因为我们是更新库存充足，所以只需要这一个参数，所以where条件就变成了：
			 *    id={0}
			 * oldCartDate.getId() 它就表示我们将要修改的目标，即：我们需要对哪一个用户的记录进行修改。
			 * 所以，此处我们提供一个id （即，修改指定id的购物车记录的库存的数量）。
			 */
//			EntityWrapper<Cart> ew = new EntityWrapper<Cart>();
//			ew.where("id={0}", oldCartDate.getId());
//			cartMapper.update(oldCartDate, ew);
//    链式编程：
			cartMapper.update(oldCartDate, new EntityWrapper<Cart>().where("id={0}", oldCartDate.getId()));
			return SysResult.build(202, "当前用户的商品已经存在");
		} else {
			// 3、如果此商品在购物车中找不到，即不存在，那么当前save就是新增insert（将此商品添加到购物车）的操作。
			// 设置添加商品到购物车中的时间。
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
			return SysResult.oK();
		}
	}

	/** 更新商品的数量。 */
	@Override
	public void updateNum(Cart cart) {
		EntityWrapper<Cart> ew = new EntityWrapper<Cart>();
		/**
		 * where()方法：
		 * 第1个参数：负责传递业务数据。
		 * 第2个参数：负责封装where条件。
		 * 注意，表达式中中，左边部分，必须是SQL中的字段！：
		 *   user_id、item_id 它们必须是字段。
		 */
		ew.where("user_id={0}", cart.getUserId());
		ew.and("item_id={0}", cart.getItemId());
		// 相当于： 【where (user_id=? and item_id=?)】。
		cartMapper.update(cart, ew);
	}

	/** 删除指定用户的指定的商品信息。 */
	@Override
	public void delete(Cart cart) {
		EntityWrapper<Cart> ew = new EntityWrapper<Cart>();
		ew.where("user_id={0}", cart.getUserId());
		ew.andNew("item_id={0}", cart.getItemId());
		// 相当于： 【where user_id=? and (item_id=?)】。此处执行的效果一样所以可以使用and()方法即可。
		cartMapper.delete(ew);
	}
	
	
	

}
