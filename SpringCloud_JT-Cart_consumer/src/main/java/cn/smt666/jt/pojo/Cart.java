package cn.smt666.jt.pojo;

/**===============================<br/>
 * <font color="red">关于为什么把【商品标题、图片、价格】属性赋值在当前Cart购物车实体类的说明。</font><br/>
 * 数据尽量做到唯一出处，对于itemTitle商品名称、商品图片、商品价格这样的字段信息。<br/>
 * 按照唯一出处原则，本身是应该存放在商品信息表`tb_item`，并且使用item主外键表关联查询方式才合理。<br/>
 * 但是，由于我们项目是电商类的项目。<br/>
 * 电商项目对于用户的体验来说，项目的性能就需要极致。<br/>&nbsp;&nbsp;&nbsp;&nbsp;
 *    如果采用主外键关联表方式来查询商品标题、图片价格这样的信息的话，<br/>&nbsp;&nbsp;&nbsp;&nbsp;
 *    那么很显然，相对于单表的查询，效率上一定是单表的查询性能更加高效。<br/>
 * 所以，为了提高对[商品标题、图片、价格]这三个字段信息的查询效率，我们直接在`Cart`购物车表中额外又添加了这三个字段。<br/>
 * 也就是冗余的字段。性能虽然提高了。但是由于这三个字段的信息，存在于2张不同的数据表`tb_cart`和`tb_item`中。<br/>
 * 那么就会造成一个问题：<br/>&nbsp;&nbsp;&nbsp;&nbsp;
 * 	      如果`tb_item`商品表中这三个字段的信息发生了更新，那么`tb_cart`表的这三个字段的信息的更新势必会存在一定的时间差。<br/>
 * 只要存在时间差，相同字段存在于2张不同数据表中，一旦某张表这些自动数据发生更新，<br/>
 * 在处于高并发状况下，是有可能存在查询到的这些自动的数据发生数据不一致问题的。<br/>&nbsp;&nbsp;&nbsp;&nbsp;
 *    因为我们是电商项目，这种极短暂的时间不一致是允许的。<br/>
 * 为了能够最终更新完毕，通常会吧更新操作交给消息队列MQ去处理。（一般在秒级的短暂不一致）。<br/>
 * 所以，我们把这种冗余字段最终更新完毕的特性，就成为冗余的最终一致性。<br/>
 * <br/>
 * ===============================<br/>
 * <font color="red">关于把商品价格设计成Long长整型的说明。</font><br/>
 * 在Java语言中的double数据类型它是有精度问题的。<br/>
 * 只要去计算精度的浮点数，性能就慢。<br/>
 * 而电商环境中商品价格来说，一般就就只有2为小数（例如，25.60元），不存在2位以上的小数部分的情况的。<br/>&nbsp;&nbsp;&nbsp;&nbsp;
 *     对于只有2位小数这样的需要，我们只要乘100再除100即可实现2位小数的结果。<br/>&nbsp;&nbsp;&nbsp;&nbsp;
 * 	        并且这样乘100又除100的操作，我们是可以放在JS（浏览器客户端就可以）处理的，并不会影响我们服务端的性能。<br/>
 * 所以，相比使用Double浮点数的数据类型而言，使用长整型的整体性能更加高效。<br/>
 * 
 * @author smt666
 * @Date 2020-04-21
 *
 */
public class Cart extends BasePojo {
	
	private static final long serialVersionUID = 421261987258310035L;
	
	/** 记录ID（本条数据的编号） */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 商品编号 */
	private Long itemId;
	/** [冗余字段]商品标题 */
	private String itemTitle;
	/** [冗余字段]商品图片 */
	private String itemImage;
	/** [冗余字段]商品价格 */
	private Long itemPrice;
	/** 商品库存 */
	private Integer num;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public Long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
