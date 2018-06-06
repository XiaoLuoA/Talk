function newTalk(event)
{
	var itemDom = $(event.target);
	
	if(true)
	{
		itemDom = itemDom.hasClass('item-item')?itemDom:$(itemDom.parents('.item-item'));
		var id = itemDom.attr('data-index');//得到id
		
		var index = Tab.attrMap.get(id);
		//查询是否已经添加
		if(index)
		{
			//已经添加,找到对应索引并激活
			Tab.active(index);
		}
		else
		{
			var item = itemMap.get(id+'');
			//未添加，添加并激活
			var $newChoseItem = $(createChoseItem(item));
			var $newDetailItem = $(detailItemTpl(item));
			$choseList.append($newChoseItem);
			$detailList.append($newDetailItem);
			Tab.add($newChoseItem[0],$newDetailItem[0]);
		}
	}
	event.stopPropagation()
}
function newItem(item){}
function newMessage(){}
function closeTalkBtn(event)
{
	//得到id
	var $a = $(event.target);
	var id = $a.parents('.chose-item').attr('data-index');
	Tab.remove(id,true);
	//remove
	return false;
}
function closeItem(){}
function sendMessage(){}

function showChat(event)
{
	changeShowHidden($chatOverlay);
	event.isPropagationStopped();
	return false;
}
function clkOverLayhide(event)
{
	if(event.target.classList.contains('overlay'))
	{
		hiddenArea($(event.target));
	}
	event.isPropagationStopped();return false;
}
function showGruopChat(event)
{
	changeShowHidden($GroupChatOverlay);
	event.isPropagationStopped();
	return false;
}


function tioInitWs(queryString)
{
	var DemoHandler = function () {
		this.onopen = function (event, ws) {
			console.log('open详情',event,ws);
		}

  		/**
   		* 收到服务器发来的消息
   		* @param {*} event 
   		* @param {*} ws 
   		*/
		this.onmessage = BieginListener;
		this.onclose = function (e, ws) {
			console.log('onclose启动',e);
		};
		this.onerror = function (e, ws) {
			console.log('onerror',e);
		};

	 	/**
  	 	* 发送心跳，本框架会自动定时调用该方法，请在该方法中发送心跳
   		* @param {*} ws 
   		*/
  		this.ping = function (ws) {
 			// log("发心跳了")
 			ws.send('心跳内容')
 		}
	}
	
	
	var protocol = 'ws'; // ws 或 wss
	var ip = '127.0.0.1'
	var port = 8888
	var heartbeatTimeout = 5000; // 心跳超时时间，单位：毫秒
	var reconnInterval = 1000; // 重连间隔时间，单位：毫秒
	var binaryType = 'blob'; // 'blob' or 'arraybuffer';//arraybuffer是字节
	var handler = new DemoHandler();
	var param = null;
	window.tiows = new tio.ws(protocol, ip, port, queryString, param, handler, heartbeatTimeout, reconnInterval, binaryType)
  	tiows.connect()
  	console.log('tiows建立连接');

	function send () {
  	tiows.send(msg.value)
}

}

function tioReady()
{
	//tio初始化
	tioInitWs(queryString.trim());
	//tioInit();

	//添加监听事件
//	BieginListener();
	//添加ajax发送事件
	BeginSend();
	
}


function BieginListener(event, ws)
{
//	console.log('onmessage启动',event);
	var data = event.data
	var res = JSON.parse(data);
	//json数据格式了
	switch(res.type)
	{
		case 0:
			newChatListener(res.message);
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
	}
	function newGroupMessageListener(data)
	{
		//变量操作
		//dom操作
	}
	/*
	 * 收到新的个人聊天信息
	 */
	function newChatListener(data)
	{
		//变量操作
		var message = data;
		var itemid = message.itemId;
		
		var $DetailItem = $(Tab.showAreas[Tab.attrMap.get(itemid)]);
	
		itemMap.get(itemid+'').messages = itemMap.get(itemid+'').messages||[];
		itemMap.get(itemid+'').messages.push(message);
		//dom操作


		$($DetailItem.find('.message-list')).append($(chatMessageTpl(message)));
	}
	function newItemListener(data)
	{
		//变量操作
		var item = data;
		itemMap.set(item.userItemId,item);
		//dom操作
		$itemList.append(ItemItemTpl(item));
	} 
}
function BeginSend()
{
	window.sendFunctons = 
	{
		openNewChat :function(){},
		closeChat :function(){},
		sendChat :function(Data){
			var data = {type:0,message:Data};
			tiows.send(JSON.stringify(data));
			console.log('tio发送',JSON.stringify(data));
		},
		moreChatMessage :function(){},
		
		openNewGroupChat :function(Data){
			var data = {type:1,message:Data};
			tiows.send(JSON.stringify(data));
			console.log('tio发送',JSON.stringify(data));
		},
		closeGroupChat :function(){},
		sendGroupChat :function(){},
		moreGroupChatMessage :function(){},
	};
}
function sendChatBtn(event)
{
	var $DetailItem = $($(event.target).parents('.detail-item'));
	var $textArea = $DetailItem.find("textarea");
	var userItemId = $DetailItem.attr('data-index');
	var item = itemMap.get(userItemId);
	var message = {
		itemId :userItemId,
		fromId :sessionId,
		toId :item.userItemId.split('|')[1],
		sendTime :(new Date().getTime())+'',
		isRead :false,
		content:$textArea.val(),
	};
	console.log('将要发送的消息',message);
	
	//添加本消息并渲染
	item.messages.push(message);
	$($DetailItem.find('.message-list')).append($(chatMessageTpl(message)));
	//消除本地消息
	$textArea.val('');
	//修改itemID方便对方接受
	message.itemId = message.itemId.split('|')[1]+'|'+ message.itemId.split('|')[0]
	//使用tio发送消息;
	sendFunctons.sendChat(message);
}

function newGroupChat(event)
{
	var $DOM = $(event.target);
	$DOM.hasClass('group')?true:$DOM=$($DOM.parents('.group'));
	//从前台获取属性
	console.log($DOM);
	var groupId  = $DOM.attr('data-inDex');
	var groupName= $DOM.find('.group-name').html();
	//先添加MAP
	var group =
	{
		id :groupId,
		groupName :groupName,
		
		messages:[],
		users:[],
	}
	groups.push(group);
	openGroupMap.set( groupId,group);
	//然后渲染
	//生成panels 和showAreas
	var $newGroupChoseItem = $(groupChoseItemTpl(group));
	var $newGroupDetailItem = $(groupDetailItemTpl(group));
	
	
	$groupChoseList.append($newGroupChoseItem);
	$groupDetailList.append($newGroupDetailItem);
	GroupChatTab.add($newGroupChoseItem[0],$newGroupDetailItem[0]);
	//渲染完成后 ，开始初始华群消息
	var data = {groupId:groupId,};
	$.ajax({
		url:'chatgetGroupInfo.action',
		data:data,
		success:function(Res){
			console.log('群聊加入请求成功',Res);
			var res = JSON.parse(Res);
			console.log(res);
			//调用渲染操作
			group.messages = res.items;
			group.users = res.users;
			
			var newMessageHtml = [];
			var newUserHtml = [];
			group.messages.forEach(function(message,index){
				newMessageHtml.push(message);
			});
			group.users.forEach(function(user,index){
				newUserHtml.push(newUserHtml);
			});
			$($groupDetailList.find('.group-message-list')).append($(newMessageHtml));
			$($groupDetailList.find('.user-list')).append($(newUserHtml));
			
		},
		error:function(Res){
			console.log('群聊加入请求成功',Res);
			console.log(JSON.parse(Res));
		},
		setTimeOut: 2000,
		timeOut: 2000,
	});
	//发送tio请求
	var tioData = 
	{
		groupId:groupId,
	};
	console.log(tioData);
	sendFunctons.openNewGroupChat(tioData);
	
	
}
/**
 * 几乎所有的事件注册都写到这里面了
 */
function bindEvent()
{
	//启动弹窗操作
	$showChatBtn.on('click',showChat);
	$showGroupChatBtn.on('click',showGruopChat);
	//弹窗关闭操作
	$chatOverlay.on('click',clkOverLayhide);
	$GroupChatOverlay.on('click',clkOverLayhide);
	//创建选项卡
	Tab = TabByClass('MessageArea','chose-item','detail-item');
	Tab.madeMap('data-index');
	Tab.addSelect('.cls-btn');
	
	GroupChatTab = TabByClass('GroupMessageArea','group-chose-item','group-detail-item');
	GroupChatTab.madeMap('data-groupid');
	GroupChatTab.addSelect('.cls-btn')
	
	//添加点击事件
	
	//点击打开群聊
	$('.gruop-area').on('click','.group',newGroupChat);
	
	
	//点击创建新会话
	//点击创建新对话
	$itemList.on('click','.item-item',newTalk);
	//点击关闭对话
	$choseList.on('click','.cls-btn',closeTalkBtn);
	//点击发送私聊消息
	$detailList.on('click','button',sendChatBtn);
	//点击创建新群聊
	//点击关闭当前群聊
	


}

function init()
{
	// 渲染页面
	render();
	//事件绑定
	bindEvent();
}

tioReady();
askforData();
