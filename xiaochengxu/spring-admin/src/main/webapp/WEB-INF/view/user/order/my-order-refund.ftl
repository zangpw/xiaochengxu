<@p.userHeader title="申请退款"/>
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<style>
	/* 上传图片标签 */
	.addImg {
		display: block;
		width: 80px;
		margin-top: 5px;
		height: 80px;
		line-height: 80px;
		border: 1px solid #ccc;
		color: #999;
		text-align: center;
	}
	 a:visited {color: #999} 
	 .img_li{
	 	display: inline; 
	 	padding: 10px;
	 }
	 .img_ul{
	 	padding-top: 10px;
	 }
	dl div.required em {
	    line-height: 22px;
	    background: url(../images/member/ncus_public.png) no-repeat scroll -276px -231px;
	    display: inline-block;
	    width: 16px;
	    height: 16px;
	    margin-right: 5px;
	}
</style>
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left/>
    	<div class="right-content"> 
      		<div class="path">
        		<div>
        			<a href="${base}">我的商城</a><span>&raquo;</span>
                    <a href="${base}/user/list"/>我的订单</a><span>&raquo;</span>
                   	申请退款
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
  							<li class="active"><a  href="#">仅退款</a></li>
  							<li class="normal"><a  href="${base}/user/refundReturnIndex?orderGoodsId=${orderGoods.recId}">退货退款</a></li>
  						</ul>
  					</div>
  					<#if orderGoods??>
	  					<table class="ncu-table-style">
	  						<input name="orderId" value="${orderGoods.orderId}" type="hidden"/>
							<thead>
								<tr>
									<th class="w10"></th>
									<th class="w70"></th>
									<th>商品名称</th>
									<th>单价</th>
									<th>商品数量</th>
								</tr>
							</thead>
							<tbody>
								<input name="orderGoodsId" value="${orderGoods.recId}" type="hidden"/>
								<tr class="bd-line">
									<td></td>
									<td>
										<div class="goods-pic-small">
											<span class="thumb size60">
												<i></i>
												<a target="_blank" href="${base}/product/detail?id=${orderGoods.goodsId}">
													<img style="display: inline;" src="${imgServer}${orderGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);">
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
										</dl>
									</td>
									<td>
										<script type="text/javascript">
			              					var price = number_format(${orderGoods.goodsPrice},2);
			              					document.write(price);
			              				</script>
									</td>
									<td class="w150 bdl bdr" id="goodsNum">${orderGoods.goodsNum}</td>
								</tr>
							</tbody>
						</table>
						<div class="wrap-shadow">
							<div class="wrap-all ncu-order-view"> 
								<dl>
							      <dt class="required"><em class="pngFix"></em>退款金额：</dt>
							      <dd>
							        <input name="refundAmount" disabled="disabled" value="${orderGoods.goodsPayPrice}" style="width: 70px;"/>
							        <em><b>元</b>&nbsp;&nbsp;(最多 ${orderGoods.goodsPayPrice} 元)</em>
							      </dd>
							    </dl>								
								<dl>
								<div class="required" style="position: relative;top: 21px;left: -9px;"><em class = "pngFix"></div>
							      <dt class="required">退款原因：</dt>
							      <dd style="width:320px">
							        <textarea name="buyer_message" class="textarea w300" rows="5"></textarea>
							      </dd>
							    </dl>
							    <dl>
							      <dt>&nbsp;</dt>
							      <dd style="width:320px">
							        <p class="hint">退货信息只能填写提交一次，建议与卖家沟通后认真填写。</p>
							      </dd>
							    </dl>
							    <dl>
							    	<dt>图片信息：</dt>
				                	<table>
				                		<tr>
				                			<td><ul class="img_ul" id="return_img"></ul></td>
				                			<td>
				                				<a class="addImg" href="#none" style="position: relative; z-index: 1;text-decoration:none;">添加图片
							                		<input type="file" onChange="ajaxImageUploads('myBlogImage1')" style="opacity:0; top:0; left:0; width:100%; height:100%; margin:0; position:absolute;" id="myBlogImage1" name="myfiles" class="file" multiple="multiple"/>
							                	</a>
				                			</td>
				                		</tr>
				                	</table>
				                </dl>
							    <dl class="bottom">
							      <dt>&nbsp;</dt>
							      <dd>
							        <input type="submit" class="submit" id="confirm_button" name="confirm_button" value="确定">
							      </dd>
							    </dl>
							</div>
						</div>
					<#else>
						<div class="wrap-shadow">
							<div class="wrap-all ncu-order-view"> 
								<p style="font-size: 30px;">您的订单已申请退款</p>
							</div>
						</div>
					</#if>
				</div>
  			</div>
  		</div>
  	</div>
</div>
<script type="text/javascript">
	$(function(){
		//删除上传的图片
		$("[name=deletePhoto]").live("click", function(){
			$(this).parent().remove();
		});
		
		$("#confirm_button").click(function(){
			var orderId = $("input[name='orderId']").val(); //订单id
    		var orderGoodsId = $("input[name='orderGoodsId']").val(); //订单项id
    		var buyerMessage = $("textarea[name='buyer_message']").val(); //退款原因
    		var refundAmount = $("input[name='refundAmount']").val(); //退款金额
			//判断填写退货数量为0
			if(refundAmount==0){
				layer.msg("请您填写退款金额!",{icon:2});
				return false;
			}
			if(buyerMessage==''){
				layer.msg("请填写退款原因",{icon:2});
				return false;
			} 
			//商品多图
			var goodsImageMore = "";
			$("[name=deletePhoto]").each(function(){
				goodsImageMore += $(this).attr("imageSrc") + ",";
			});
			var fmUrl = '${base}/user/refundOrder';
			$.ajax({
	             type: "post",
	             url: fmUrl,
	             data: {orderId:orderId,buyerMessage:buyerMessage,orderGoodsId:orderGoodsId,goodsImageMore:goodsImageMore,refundAmount:refundAmount},
	             dataType: "json",
				 success:function(data) {
					if(data.success){
						layer.alert("退款提交成功",{icon:1},function(){
							location.href="${base}/user/refundList";
						});	
					}else{
						layer.alert("退款提交失败",{icon:2},function(){
							layer.closeAll();
						});		
					}
				}
	         }); 
		});
		
		$("input[name='refundAmount']").val(toDecimal2(${orderGoods.goodsPayPrice}));
		$("input[name='refundAmount']").change(function(){
			var reg = /^[-\+]?\d+(\.\d+)?$/; //检验是否为数字的正则表达式
			var refundPrice = $(this).val();
			if(refundPrice==''){
				$(this).val(toDecimal2(${orderGoods.goodsPayPrice}));
			}else if(!reg.test(refundPrice)){
				layer.msg("请您输入正确的金额!",{icon:2});
				$(this).val(toDecimal2(${orderGoods.goodsPayPrice}));
			}else if(refundPrice>${orderGoods.goodsPayPrice}){
				layer.msg("请您输入正确的金额!",{icon:2});
				$(this).val(toDecimal2(${orderGoods.goodsPayPrice}));
			}else{
				$(this).val(toDecimal2(refundPrice));
			}
		});
	});
	/*上传评论图片*/
	function ajaxImageUploads(myBlogImage){
		var imgNo = $("#return_img").find("li").length;
		$.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url:"${base}/user/returnImageFileUpload",
	        secureuri:false,                       //是否启用安全提交,默认为false
	        fileElementId:myBlogImage,           //文件选择框的id属性
	        dataType:'json',                       //服务器返回的格式,可以是json或xml等
	        data:{imgNo:imgNo},						   //已上传的图片数量
	        success:function(data, status){        //服务器响应成功时的处理函数
	            if(data.success){
	            	var count = parseInt(data.listimgSize);
	            	var strs01 = data.photoNewName.split(",");
	            	var imgBasePath = '${imgServer}'
	            	var imgPath = data.imgPath;
	            	for(var i = 0; i < count; i++){	         
	            		var photoSrc01 = imgPath + "/" + strs01[i];
	            		var photoServcerSrc = imgBasePath + imgPath + "/" + strs01[i];
	            		$("#return_img").append("<li class='img_li'><img style='width:82px;height:82px' src='"+photoServcerSrc+"'/><a href='javascript:void(0)' imageSrc='"+photoSrc01+"' name='deletePhoto'> 删除</a></li>");
	            	}
	            }else{
	            	layer.alert("最多上传三张图片",{icon:2});
	            }
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
	            layer.alert('图片上传失败，请重试！！',{icon:2});
	        }
	    });
	}	
	
	//准换价格格式
	function toDecimal2(x) {   
	    var f = parseFloat(x);   
	    if (isNaN(f)) {   
	        return false;   
	    }   
	    var f = Math.round(x*100)/100;   
	    var s = f.toString();   
	    var rs = s.indexOf('.');   
	    if (rs < 0) {   
	        rs = s.length;   
	        s += '.';   
	    }   
	    while (s.length <= rs + 2) {   
	        s += '0';   
	    }   
	    return s;   
	}  
	
</script>
<@p.userfooter/>