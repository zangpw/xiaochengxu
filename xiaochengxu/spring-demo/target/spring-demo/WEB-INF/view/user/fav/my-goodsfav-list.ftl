<script type="text/javascript" src="${base}/res/js/member/member.js"></script>
<#assign favoriteGoodsListTag = newTag("favoriteGoodsListTag")/>
<#assign favoriteGoodsList =favoriteGoodsListTag("{'tagDataType':'2','pageSize':'${pageSize}','pageNo':'${pageNo}','flags':'goods'}")>
<table class="ncu-table-style">
   <#if favoriteGoodsList?exists && favoriteGoodsList?size gt 0>
	<#list favoriteGoodsList as favoriteGoods>
	<tbody>
	<tr class="bd-line">
		<td style="vertical-align: middle;">
			<input type="checkbox"  class="checkitem"  value="${favoriteGoods.favId}"/>
		</td>
		<td>
			<div class="goods-pic-small">
				<span class="thumb size60"><i></i>
					<a href="${base}/product/detail?id=${favoriteGoods.favId}" target="_blank">
						<img src="${imgServer}${favoriteGoods.goods.goodsImage}" style="width: 60px;height: 60px;" />
					</a>
				</span>
			</div>
		</td>
		<td>
			<dl class="goods-name">
				<dt><a href="${base}/product/detail?id=${favoriteGoods.favId}" target="_blank">${favoriteGoods.goods.goodsName}</a></dt>
				<dd class="share-sale"><span>售出：<em>${favoriteGoods.goods.salenum}</em>件</span><span>(<em>${favoriteGoods.goods.commentnum}</em>条评论)</span></dd>
			</dl>
		</td>
		<td></td>
		<td class="tl">
			<p> 
				<a href="${base}/store/shop?storeId=${favoriteGoods.goods.storeId}" target="_blank">${favoriteGoods.goods.storeName}</a>
					<span class="goods-favorite" title="${favoriteGoods.goods.storeName}"><i class="have">&nbsp;</i></span>
				</p>
		</td>
		<td class="goods-price"><strong>${favoriteGoods.goods.goodsStorePrice}</strong></td>
		<td class="goods-time">${favoriteGoods.goods.goodsStarttime}</td>
		<td><p><a href="javascript:void(0)" onclick="deletefavotitegoods('${favoriteGoods.favId}','${favoriteGoods.favType}');" class="ncu-btn2 mt5">删&nbsp;除</a></p></td>
	</tr>
	</tbody>
	</#list>
	<#else>
	<tbody>
 	<tr>
 		<td colspan="9">
 			暂无收藏商品
 		</td>
 	</tr>
 	</tbody>
	</#if>
	 <tfoot>
		<tr>
		       <#if favoriteGoodsList?exists && favoriteGoodsList?size gt 0>
					<td>
						<input type="checkbox" id="all2" class="checkall" />
					</td>
				</#if>
				<td colspan="20">
				<#if favoriteGoodsList?exists && favoriteGoodsList?size gt 0>
					<label for="all2">全选</label>
					<a href="javascript:void(0);" class="ncu-btn1" uri="" name="fav_id" confirm="您确定要删除吗?" nc_type="batchbuttons"><span>删除</span></a>
				</#if>
				<div class="pagination">
				<#assign recordCount = favoriteGoodsListTag("{'pageSize':'${pageSize}','tagDataType':'5','flags':'goods'}")>
				<#import "/commons/tagpage.ftl" as q> <#--引入分页-->
				<#if recordCount??>
				    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				</#if>
				</div>
			</td>
		</tr>
	</tfoot>
</table>