<@p.header title="LmShop B2B2C V3.0 2015版商城系统"/>
<#assign siteSettingTag = newTag("siteSettingTag") />
<#assign siteSet = siteSettingTag("") />
<script src="${base}/res/js/jquery.js"></script>
<div class="clear"></div>
<!--主页内容，调用index-->
<div class="nch-breadcrumb-layout"></div>
<link href="${base}/res/css/index.css" rel="stylesheet" type="text/css">
<!--[if IE 6]>
<script type="text/javascript" src="res/js/ie6.js" charset="utf-8"></script>
<![endif]-->
<!--控制左侧商品的js-->
<style type="text/css">
    .category {
        display: block !important;
    }
</style>

<!-- HomeFocusLayout Begin-->
<div class="home-focus-layout">
	<!-- 首页大广告 -->
 	<#assign advPositionTag = newTag("advTag") />
 	<#assign topPosition = advPositionTag("{'apId':'6'}") />
 	<#if topPosition?? && topPosition!''>
 	<#assign apList = topPosition.advList />
 	
  	
  	
  	</#if>
  	<!--栏目商品-->
  	<#assign recommendGoodsTag = newTag("recommendGoodsTag") />
   <#assign recommendGoodslist = recommendGoodsTag("{'goodsflagsname':'recommend'}") />
  
    <div class="right-sidebar">
        <div class="policy">
            <ul>
                <li class="b1">七天包退</li>
                <li class="b2">正品保障</li>
                <li class="b3">闪电发货</li>
            </ul>
        </div>
        <!---团购模块-->
        <div class="groupbuy">
            <div class="title"><i>抢</i>限时抢购</div>
            <ul>
            <#assign rigthPosition = advPositionTag("{'apId':'7'}") />
            <#if rigthPosition?? && rigthPosition!''>
	            <#assign apList = rigthPosition.advList />
	            <#if apList??>
	              <#list apList as advs >
		              <li> 
						  <a href="${advs.advUrl}" target="_blank" style="opacity: 1;"><img style="width: 210px; height: 180px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${advs.resUrl}" 
						  data-url="${imgServer}${advs.resUrl}" alt="${advs.advTitle}"></a>
					  </li>
				  </#list>
				</#if>
			</#if>
            </ul>
        </div>

        <div class="proclamation">
            <ul class="tabs-nav">
                <li class="tabs-selected"><h3>商城公告</h3></li>
                <li class=""><h3>招商入驻</h3></li>
            </ul>
            <div class="tabs-panel">
                <ul class="mall-news">
                <#assign articleTag = newTag("articleTag") />
                <#assign noticeList = articleTag("{'tagDataType':'2','acId':'8','pageSize':'5'}") />
                <#if noticeList??>
                	<#list noticeList as notices >
                    <li><i class="icon-dot"></i>
                        <a href="${base}/help/content?acId=${notices.acId}&articleId=${notices.articleId}" title="${notices.articleTitle}">${notices.articleTitle}</a>
                        <time>(${notices.createTimeStr?string("yyyy-MM-dd")})</time>
                    </li>
                    </#list>
                 </#if>
                </ul>
            </div>
            <div class="tabs-panel tabs-hide">
                <a href="${base}/signUp" title="商家开店" class="store-join-btn" target="_blank">&nbsp;</a>
                <a href="${base}/document/forward?id=4" target="_blank" class="store-join-help"><i class="icon-question-sign"></i>查看开店协议</a>
            </div>
        </div>
    </div>
</div>
<div class="home-sale-layout wrapper">
    <div class="left-layout">
        <ul class="tabs-nav">
            <li class="tabs-selected">
            	<i class="arrow"></i>
                <h3>新品上市</h3>
            </li>
            
            <li>
            	<i class="arrow"></i>
                <h3>推荐商品</h3>
            </li>
        </ul>
        <!--新品上市 -->
        <#assign recommendGoodslist = recommendGoodsTag("{'goodsflagsname':'newgoods'}") />
        <div class="tabs-panel sale-goods-list ">
            <ul class="specially">
               <#if recommendGoodslist??>
               	<#list recommendGoodslist as recommendGoods >
                      <li>
	                    <dl>
	                        <!--商品名称-->
	                        <dt class="goods-name">
	                        	<a target="_blank" href="${base}/product/detail?id=${recommendGoods.goods.goodsId}" title="${recommendGoods.goods.goodsName}">
	                        		${recommendGoods.goods.goodsName}
	                        	</a>
	                        </dt>
	                        </dt>
	                        <!--商品图片-->
	                        <dd class="goods-thumb">
	                        	<a target="_blank" href="${base}/product/detail?id=${recommendGoods.goods.goodsId}">
	                        		<img class="scrollLoading" src="" data-url="${imgServer}${recommendGoods.goods.goodsImage}"
	                                alt="${recommendGoods.goods.goodsName}"/></a></dd>
	                        <!--商品价格-->
	                        <dd class="goods-price"> 商城价：<em>&yen;${recommendGoods.goods.goodsStorePrice}</em></dd>
		                   </dl>
	               		</li>
              		   <#if (recommendGoods_index>5)>
              			  <#break/>
		             </#if>
                   </#list>
                </#if>
            </ul>
        </div>
       
        <!--推荐商品-->
        <#assign newGoodslist = recommendGoodsTag("{'goodsflagsname':'recommend'}") />
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul class="specially">
               <#if newGoodslist??>
               	<#list newGoodslist as newGoods >
                     <li>
	                    <dl>
	                        <!--商品名称-->
	                        <dt class="goods-name">
	                        	<a target="_blank" href="${base}/product/detail?id=${newGoods.goods.goodsId}" title="${newGoods.goods.goodsName}">
	                        		${newGoods.goods.goodsName}
	                        	</a>
	                        </dt>
	                        </dt>
	                        <!--商品图片-->
	                        <dd class="goods-thumb">
	                        	<a target="_blank" href="${base}/product/detail?id=${newGoods.goods.goodsId}">
	                        		<img class="scrollLoading" src="" data-url="${imgServer}${newGoods.goods.goodsImage}"
	                                alt="${newGoods.goods.goodsName}"/></a></dd>
	                        <!--商品价格-->
	                        <dd class="goods-price"> 商城价：<em>&yen;${newGoods.goods.goodsStorePrice}</em></dd>
	                    </dl>
               		  </li>
           		     <#if (newGoods_index>5)>
           			      <#break/>
		             </#if>
                   </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>
<!--HomeFocusLayout End-->
<div class="clear"></div>





<script type="text/javascript" src="res/js/home_index.js" charset="utf-8"></script>

<div style="clear: both;"></div>
<div id="web_chat_dialog" style="display: none;float:right;"></div>
<a id="chat_login" href="javascript:void(0)" style="display: none;"></a>

<script type="text/javascript" src="res/js/jquery.charCount.js" charset="utf-8"></script>
<script type="text/javascript" src="res/js/jquery.smilies.js" charset="utf-8"></script>
<div class="clear"></div>
<!-----footer------>
<@p.footer/>
