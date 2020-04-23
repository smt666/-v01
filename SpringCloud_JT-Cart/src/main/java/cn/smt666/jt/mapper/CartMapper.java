package cn.smt666.jt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.smt666.jt.pojo.Cart;

/**
 * 继承BaseMapper接口,使用MyBatisPlus提供的通用Mapper技术，自动生成CRUD方法。<br/>
 * 对于单表CRUD方法交给MyBatisPlus提供的BaseMapper接口来完成，所以此处不用写接口方法代码了。
 * 
 * @author 27140
 *
 */
public interface CartMapper extends BaseMapper<Cart> {
	// 对于单表CRUD方法交给MyBatisPlus提供的BaseMapper接口来完成，所以此处不用写接口方法代码了。
	
	// 演示。这样的查询，完全可以使用MP替代，此处仅仅演示可以在xml中手写SQL而已。
	public List<Cart> myCart(Integer userId);
}
