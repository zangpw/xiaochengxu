
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>分类 - LmShop B2B2C V3.0 2015版商城系统</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script>
jQuery(document).ready(function(e) {
    jQuery(".category_xx_center_list li").click(function(){
		var id = jQuery(this).attr("id");
		var mark = jQuery(this).attr("mark");	
		if(mark=="true"){
			jQuery(this).find("img").attr("src","${base}/res/html5/images/search_on_bg.png");
			jQuery("#class2_"+id).fadeOut();
			jQuery(this).attr("mark","false");
		}else{
			jQuery(this).find("img").attr("src","${base}/res/html5/images/search_off_bg.png");
			jQuery("#class2_"+id).fadeIn();
			jQuery(this).attr("mark","true");
		}	
	})
});
</script>
</head>
<body class="index_bg">
<div class="phone_hd"><a class="back" href="${base}/m/category/category"><img src="${base}/res/html5/images/back.png" width="25" height="25" /></a>商品分类<a class="menu home" href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" width="25" height="25" /></a></div>
<div class="category_xx">
	<div class="category_xx_center">
   	  
      <div class="category_xx_center_list">
      	<ul>
	      	<#assign goodsClassTag =newTag("goodsClassTag")>
			<#assign goodsList =goodsClassTag("{'parentid':'${categoryId}'}")>
			<#assign href ='m/category/categoryList?'>
			 <#list goodsList as class>
				
	       		<li id="${class.gcId}" mark="false">
		          <div class="category_center_list_left"><a href="${base}/m/goods/goodslist?searchType=gcIdSearch&keyword=${class.gcId}&refereurl=${base}/m/category/categoryList?keyword=${categoryId}">${class.gcName}</a></div>
		          <i><img src="${base}/res/html5/images/search_on_bg.png"></i>
	        	</li>
          	</#list>
  		</ul>
      </div>
        
    </div>
  <!--底部-->
    <@f.foot/>	

<!--按钮-->
 </div>
<!--悬浮层-->
<script>
function click_layer(){
	var ret = jQuery("#show").attr("mark");
	if(ret=="show"){
		jQuery("#show").hide();
		jQuery("#show").attr("mark","hide");
		}else{
		jQuery("#show").show();
		jQuery("#show").attr("mark","show");	
			}
	}
</script>
<div class="layer">
	<a class="layer_menu" href="javascript:void(0);" onclick="click_layer();"><img src="${base}/res/html5/images/layer_05.png" width="35" height="35" /><span class="black_bg"></span></a>
    <div class="layer_show" id="show" mark="hide" style="display:none">
    	<ul>
        	<li><a href="${base}/m/index/index"><img src="${base}/res/html5/images/layer_01.png" width="20" height="20" /><p>首页</p></a></li>
            <li><a href="${base}/m/authc/buyer/orderList?orderState=99"><img src="${base}/res/html5/images/layer_02.png" width="20" height="20" /><p>订单</p></a></li>
            <li><a href="${base}/m/authc/cart/cartGoodsList"><img src="${base}/res/html5/images/layer_03.png" width="20" height="20" /><p>购物车</p></a></li>
            <li><a href="${base}/m/authc/buyer/center"><img src="${base}/res/html5/images/layer_04.png" width="20" height="20" /><p>用户中心</p></a></li>
        </ul>
        <div class="show_bg"></div>
    </div>
</div>

</body>
</html>
