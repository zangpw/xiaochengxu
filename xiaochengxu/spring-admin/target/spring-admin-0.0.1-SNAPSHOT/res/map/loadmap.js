// 百度地图API功能
	var map = new BMap.Map("allmap");
	// 定位参数 经纬度 坐标
	//var point = new BMap.Point(116.404, 39.915);
	
	//缩放比例
	map.centerAndZoom("北京", 15);
	
	map.enableScrollWheelZoom(true);   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom(true);    //启用地图惯性拖拽，默认禁用
//	
//	// 添加带有定位的导航控件
//	var navigationControl = new BMap.NavigationControl({
//	    // 靠左上角位置
//	    anchor: BMAP_ANCHOR_TOP_LEFT,
//	    // LARGE类型
//	    type: BMAP_NAVIGATION_CONTROL_LARGE,
//	    // 启用显示定位
//	    enableGeolocation: true
//	});
//	  map.addControl(navigationControl);
//	  // 添加定位控件
//	var geolocationControl = new BMap.GeolocationControl();
//	  geolocationControl.addEventListener("locationSuccess", function(e){
//	    // 定位成功事件
//	    var address = '';
//	    address += e.addressComponent.province;
//	    address += e.addressComponent.city;
//	    address += e.addressComponent.district;
//	    address += e.addressComponent.street;
//	    address += e.addressComponent.streetNumber;
//	    //alert("当前定位地址为：" + address);
//	  });
//	geolocationControl.addEventListener("locationError",function(e){
//	    // 定位失败事件
//	    alert(e.message);
//	});
//	map.addControl(geolocationControl);
  
	
	// 编写自定义函数,创建标注
	function addMarker(point,infoWindow){
	  var marker = new BMap.Marker(point);
 
	  map.addOverlay(marker);	 
	  
	  //添加信息窗口时间
	  marker.addEventListener("click", function(){          
		   this.openInfoWindow(infoWindow);
		   //图片加载完毕重绘infowindow
		   /*document.getElementById('imgDemo').onload = function () {
			   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
		   }*/
	  });
	}
	
	
	/**
	 * url : http://api.map.baidu.com/geosearch/v3/nearby?
	 * ak=jbU0vnj19jGfXyWOTDliRuhCtGgfIYsn&
	 * geotable_id=144373&
	 * location=116.388741,39.913296&
	 * radius=1000
	 * lbs 加载附近商家
	 * 
	 * @param {String} ak  用户ak
	 * @param {String} location 坐标
	 * @param {String} radius 范围 1000 表示1公里
	 * 
	 */
	
	var loadStore = function(ak, geotable_id, location, radius){
		
		$.ajax({
			type:"get",
			dataType:"jsonp",  
			url:"http://api.map.baidu.com/geosearch/v3/nearby",
			data:"ak="+ak+"&geotable_id="+geotable_id+"&location="+location+"&radius="+radius,
			success:function(data) {
				
				/**
				 * 	{
				    "status": 0,
				    "total": 1,
				    "size": 1,
				    "contents": [
				        {
				            "tags": "五星级",
				            "uid": 1729226932,
				            "province": "北京市",
				            "geotable_id": 144373,
				            "district": "西城区",
				            "create_time": 1466578664,
				            "city": "北京市",
				            "location": [
				                116.388741,
				                39.913296
				            ],
				            "address": "北京市西城区西长安街12号",
				            "title": "天安门饭店",
				            "coord_type": 3,
				            "direction": "附近",
				            "type": 0,
				            "distance": 0,
				            "weight": 0
				        }
				    ]
				}
				 * 
				 */
				
				if(data.total > 0){
					
					$.each(data.contents, function(index, content) {
						var point = new BMap.Point(content.location[0], content.location[1]);
						
						//信息窗口内容
						var opts = {
						  width : 200,     // 信息窗口宽度
						  height: 100,     // 信息窗口高度
						  title :  content.title+"<a href='http://localhost:8080/com-zerosoft-boot-assembly-front-local-1.0.0-SNAPSHOT/store/shop?storeId="+content.storeId+"'><font color='blue'>详情</font></a>", // 信息窗口标题
						  enableMessage:true,//设置允许信息窗发送短息
						  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
						}
						addMarker(point,new BMap.InfoWindow("地址："+content.address, opts));
					});
				}
			},
			error: function(){
				alert("数据加载异常，请重新刷新尝试");
			}
		});
	}
		
	/****/
	$(function(){  
		loadStore('5mZIpDbx0TWnb9T3ZlvuTPjNRnM2DGBA', '144577', '108.712981,34.336076',300000000);
	});