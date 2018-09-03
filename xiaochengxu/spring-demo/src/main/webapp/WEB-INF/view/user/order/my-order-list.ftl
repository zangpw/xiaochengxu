<@p.userHeader title="我的订单"/>
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left/>
    	<div class="right-content"> 
      		<div class="path">
        		<div>
        			<a href="${base}">我的商城</a><span>&raquo;</span>
                    <a href="#"/>我的订单</a><span>&raquo;</span>
                   	订单列表
                </div>
      		</div>
      		<div class="main">
       			<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
				<style type="text/css">
				.store-name {
					width: 130px;
					display: inline-block;
					overflow: hidden;
					white-space: nowrap;
					text-overflow: ellipsis;
				}
				</style>
				<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
  							<li class="active"><a  href="#">订单列表</a></li>
  							<li class="normal"><a  href="${base}/user/refundList">退款记录</a></li>
  							<li class="normal"><a  href="${base}/user/returnList">退货记录</a></li>
  							<li class="normal"><a href="${base}/user/barterList">换货记录</a></li>
  						</ul>
  					</div>
  					<form method="post" action="${base}/user/list" target="_self" id="queryForm" >
    					<table class="search-form">
      						<input type="hidden" name="act" value="member" />
      						<input type="hidden" name="op" value="order" />
      						<input type="hidden" name="evaluationStatus" value="${evaluationStatus}"/>
      						<tr>
        							<th>订单号：</th>
        							<td class="w100"><input type="text" class="text" style="width: 130px" name="orderSn" value="${orderSn}"></td>
        							<th>订单状态：</th>
        							<td class="w100">
        								<select name="orderState">
							         	   	<option value="99" <#if orderState==99>selected</#if>>所有订单</option>
					                        <option value="10" <#if orderState==10>selected</#if>>待付款</option>
					                        <option value="20" <#if orderState==20>selected</#if>>待发货</option>
					                        <option value="30" <#if orderState==30>selected</#if>>已发货</option>
					                        <option value="40" <#if orderState==40>selected</#if>>已完成</option>
					                        <option value="0" <#if orderState==0>selected</#if>>已取消</option>
					                        <option value="50" <#if orderState==50>selected</#if>>已提交</option>
					                        <option value="60" <#if orderState==60>selected</#if>>已确认</option>
          								</select>
          							</td>
          							<th>下单时间：</th>
          							<td class="w260">
	        							<input name="startTime" id="add_time_from" type="text" style="width: 110px" class="txt Wdate" value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'add_time_to\')}'});" />&#8211;
	          							<input name="endTime" id="add_time_to" type="text" style="width: 110px" class="txt Wdate" value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'add_time_from\')}'});" />
	          						</td>
        							<td class="w90 tc">
        								<input type="submit" class="submit" value="搜索" />
        							</td>
      							</tr>
    						</table>
						</form>
  						<table class="order ncu-table-style">
    						<thead>
      							<tr>
									<th class="w10"></th>
        							<th class="w70"></th>
        							<th>商品信息</th>
        							<th class="w60">单价</th>
        							<th class="w60">数量</th>
        							<th class="w150">店铺</th>
        							<th class="w100">订单总价</th>
        							<th class="w110">状态与操作</th>
      							</tr>
    						</thead>
        					<tbody>
        					<#assign orderTag = newTag("orderTag")/>
							<#assign orderLists = orderTag("{'orderSn':'${orderSn}','startTime':'${startTime}','endTime':'${endTime}','orderState':'${orderState}','evaluationStatus':'${evaluationStatus}','returnDataType':'2','lockState':'${lockState}','pageNo':'${pageNo}','pageSize':'${pageSize}'}") />
       						<#if orderLists??>
        						<#list orderLists as order>
	            					<tr>
										<td colspan="19" class="sep-row"></td>
	      							</tr>
	      							<tr>
	        							<th colspan="19"> 
	        								<span class="fl ml10">订单号：<span class="goods-num"><em>${order.orderSn}</em></span></span> 
	        								<span class="fl ml20">下单时间：<em class="goods-time">${order.createTimeStr?string("yyyy-MM-dd")}</em></span> 
	        								<span class="fl ml20"><a href="${base}/user/detail?orderId=${order.orderId}" target="_blank" class="nc-show-order">
	        									<i></i>查看订单</a>
	                    					</span> 
	                    					<#-- <span class="fr">
	                    						<a href="javascript:void(0)" class="snsshare-btn" nc_type="sharegoods" data-param='{"gid":"88"}'>
		                    						<i></i>
		          									<h5>分享商品</h5>
	          									</a>
	          								</span> -->
	          							</th>
	      							</tr>
	      							<#if order.orderGoodsList?size gt 0>
	      							<#assign rowsize = order.orderGoodsList?size>
	  								<#list order.orderGoodsList as orderGoods>
	   								<tr>
		       							<td class="bdl"></td>
		       							<td>
		       								<div class="goods-pic-small">
		       									<span class="thumb size60">
		       										<i></i>
		       										<a href="${base}/product/detail?id=${orderGoods.goodsId}" target="_blank">
		       											<img src="${imgServer}${orderGoods.goodsImage}" style="width: 60px;height: 60px;" />
		       										</a>
		       									</span>
		       								</div>
		       							</td>
		       							<td>
		       								<dl class="goods-name">
		           								<dt>
		           									<a href="${base}/product/detail?id=${orderGoods.goodsId}" target="_blank">${orderGoods.goodsName}</a>
		           								</dt>
		           								<dd>${orderGoods.specInfo}</dd>
		           								<dd>
	                                   				<#if order.orderState==40&& orderGoods.evaluationStatus == 0>
							                			<a href="${base}/myReviews/reviews?orderSn=${order.orderSn}&recId=${orderGoods.recId}">我要评价</a>
							                		<#elseif orderGoods.evaluationStatus == 1>
							                			已评价
							                		</#if>
												</dd>
	         								</dl>
	         							</td>
		       							<td class="goods-price">
		       								<i>
		       									<script type="text/javascript">
					              					var price = number_format(${orderGoods.goodsPrice},2);
					              					document.write(price);
					              				</script>
		       								</i>
		       							</td>
	      								<td>${orderGoods.goodsNum}
											<#--退货/换货-->
											<#if (order.orderState==30||order.orderState==40)&&(orderGoods.goodsBarterNum==0||orderGoods.goodsBarterNum==null)&&(orderGoods.refundAmount==0||orderGoods.refundAmount==null)>
                                                <p><a href="${base}/user/refundOrderIndex?orderGoodsId=${orderGoods.recId}" >退款/退货</a></p>
                                                <p><a href="${base}/user/shopBarterIndex?orderGoodsId=${orderGoods.recId}" >换货</a></p>
											</#if>
	      								</td>
	      								<#if orderGoods_index == 0>
	      								<td class="bdl" rowspan="${rowsize}">
	      									<p class="store-name">
											    <a title="${order.storeName}" target="_blank" href="${base}/store/shop?storeId=${order.storeId}">${order.storeName}</a>
											</p>
											<!-- <p>
											    <a title="店主" href="${base}/store/shop?storeId=${order.storeId}">${order.storeName}</a>
											    <a class="message" title="站内消息" href="#?act=home&op=sendmsg&member_id=5" target="_blank"></a>
											</p> -->
	      								</td>
	      								<td class="bdl" rowspan="${rowsize}">
	      									<p id="order6_order_amount" class="goods-price">
											    <strong>
											    	<script type="text/javascript">
						              					var amount = number_format(${order.orderAmount},2);
						              					document.write(amount);
						              				</script>
											    </strong>
											</p>
											<p class="goods-freight">
												<#if order.shippingFee??>
									          		<#if order.shippingFee!=0>
									          			运费:
									          			<script type="text/javascript">
							              					var shipFee = number_format(${order.shippingFee},2);
							              					document.write(shipFee);
							              				</script>
							              			<#else>
							              				（免运费）
									          		</#if>
									          	</#if>
											</p>
	      								</td>
	      								<td class="bdl bdr" rowspan="${rowsize}">
	      									<#if order.lockState!=0>
												<#if order.returnNum!=null&&order.returnNum!=0>
                                                    <p><span style="color:#999">订单退货中</span><br/></p>
												<#elseif order.barterNum!=null&&order.barterNum!=0>
                                                    <p><span style="color:#999">订单换货中</span><br/></p>
												<#else>
                                                    <p><span style="color:#999">订单退款中</span><br/></p>
												</#if>
											<#elseif order.orderState==0>
							                    <p><span style="color:#999">订单已取消</span><br/></p>
							                <#elseif order.orderState==10>
						                    	<p><span style="color:#36C">待买家付款</span><br/></p>
						                    	<p><a href="javascript:void(0)" onclick="cancelOrder('${order.orderSn}')" style="color:#F30; text-decoration:underline;">取消订单</a></p>
						                    	<p>
						                    		<a href="${base}/cart/goToPay?orderId=${order.orderId}" target="_blank" class="ncu-btn6 mt5" id="order1_action_confirm">付款</a>
						                    	</p>
							                <#elseif order.orderState==20>
							                    <p><span style="color:#36C">买家已付款</span><br/></p>
							                    <p><a href="${base}/user/refundIndex?orderId=${order.orderId}" style="color:#F30; text-decoration:underline;">订单退款</a></p>
							                <#elseif order.orderState==30>
							                	<p><span style="color:#36C">卖家已发货</span><br/></p>
	                              				<p><a href="javascript:void(0)" class="ncu-btn7 mt5" onclick="finishOrder('${order.orderSn}')" id="order5_action_cancel">确认收货</a></p>
							                <#elseif order.orderState==40>
						                		<p>
						                			<span style="color:#060">交易完成</span><br/>
						                		</p>
							                <#elseif order.orderState==50>
							                	<p><span style="color:#36C">已提交，待确认</span><br/></p>
							                	<p><a href="javascript:void(0)" onclick="cancelOrder('${order.orderSn}')" style="color:#F30; text-decoration:underline;">取消订单</a></p>
							                <#elseif order.orderState==60>
							                	<p><span style="color:#36C">已确认，待发货</span><br/></p>
							                	<p><a href="javascript:void(0)" onclick="cancelOrder('${order.orderSn}')" style="color:#F30; text-decoration:underline;">取消订单</a></p>
							                </#if>
	      								</td>
	      								</#if>
	      							</tr>
	      							</#list>
	      							</#if>
              					</#list>
              				</#if>
              				
              				
                			</tbody>
        					<tfoot>
      							<tr>
        							<td colspan="19">
        								<#--获取总条数-->
        								<#assign recordCount = orderTag("{'orderSn':'${orderSn}','startTime':'${startTime}','endTime':'${endTime}','orderState':'${orderState}','evaluationStatus':'${evaluationStatus}','returnDataType':'1'}") />
        								<#import "/commons/usertagpage.ftl" as q> <#--引入分页-->
										<#if recordCount??>
										    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
										</#if>
        							</td>
								</tr>
    						</tfoot>
      					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">

var APP_BASE = '${base}';
//订单支付方法
function goPay(ordersn, paymentId) {
    location.href = APP_BASE + "/cart/orderpay?paysn="+ordersn+"&paymentId="+paymentId;
}

/**取消订单**/
function cancelOrder(id) {
   	layer.open({
	    type: 2,
	    area: ['500px', '310px'],
	    skin: 'layui-layer-rim',
	    title: '取消订单',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/user/cancelOrderIndex?orderSn=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderSn=layer.getChildFrame("#orderSn",index).val();
				var state_info=layer.getChildFrame("input[name='state_info']:checked",index);
				var cancelCause="";
				if(state_info.attr("flag")=="other_reason"){
					cancelCause = layer.getChildFrame("#other_reason_input",index).val();
				}else{
					cancelCause = $(state_info).val();
				}
				var fmUrl = '${base}/user/cancelOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderSn:orderSn,cancelCause:cancelCause},
		             dataType: "json",
					 success:function(data) {
						if(data.success){
							parent.layer.alert("订单取消成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.alert("订单取消失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         });
			});	
	    }
	});
}

/**确认收货**/
function finishOrder(id) {
   	layer.open({
	    type: 2,
	    area: ['500px', '240px'],
	    skin: 'layui-layer-rim',
	    title: '确认收货',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/user/finishOrderIndex?orderSn=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderSn=layer.getChildFrame("#orderSn",index).val();
				var fmUrl = '${base}/user/finishOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderSn:orderSn},
		             dataType: "json",
					 success:function(data) {
						if(data.success){
							parent.layer.alert("确认收货成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.alert("确认收货失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         }); 
			});	 
	    }
	});
}

</script>
<@p.userfooter/>