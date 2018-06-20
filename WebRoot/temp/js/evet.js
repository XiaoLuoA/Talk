/*
 * 打开itemid对应的item
 */
function newTalk(itemId)
{
	console.log('激活会话',itemId);
	var index = Tab.attrMap.get(itemId);
	//查询是否已经添加
	if(index==0||index)
	{
		console.log('已经激活');
		//已经添加,找到对应索引并激活
		Tab.active(index);
	}
	else
	{
		console.log('未激活');
		var item = itemMap.get(itemId+'');
		//未添加，添加并激活
		var $newChoseItem = $(createChoseItem(item));
		var $newDetailItem = $(detailItemTpl(item));
		$choseList.append($newChoseItem);
		$detailList.append($newDetailItem);
		console.log('添加成功',$newChoseItem[0],$newDetailItem[0]);
		Tab.add($newChoseItem[0],$newDetailItem[0],true);
		//修改本地存储
		openItemIds.push(itemId);
		setLocal('openItemIds',openItemIds);
	}
}
function newTalkBtn(event)
{
	var itemDom = $(event.target);
	if(itemDom.is('i'))
	{
		DeleteItemBtn(event);
		event.stopPropagation();
		return false;
	}
	if(true)
	{
		itemDom = itemDom.hasClass('item-item')?itemDom:$(itemDom.parents('.item-item'));
		var itemId = itemDom.attr('data-index');//得到id
		newTalk(itemId);
	}
	event.stopPropagation();
	return false;

}

function newGroupChat($DOM)
{
	var groupId  = $DOM.attr('data-inDex');
	//首先判断是否已经添加
	console.log('群组查找',groupId,openGroupMap);
	var group = openGroupMap.get(groupId)
	if(group)
	{
		//已经打开 ，激活就好
		var index = GroupChatTab.attrMap.get(groupId);
		GroupChatTab.active(index);
	}
	else
	{
		//未打开 ，先打开再激活
		var groupName= $DOM.find('.group-name').html();
		//先添加MAP
		var group =
		{
			id :groupId,
			groupName :groupName,
			groupNum: 1,
			groupPic: $DOM.find('.group-img').attr('src'),
			messages:[],
			users:[],
		}
		groups.push(group);
		openGroupMap.set( groupId,group);
		//创建dom
		var $newGroupChoseItem = $(groupChoseItemTpl(group));
		var $newGroupDetailItem = $(groupDetailItemTpl(group));
		$groupChoseList.append($newGroupChoseItem);
		$groupDetailList.append($newGroupDetailItem);
//		console.log('群聊渲染完成',$newGroupChoseItem,$newGroupDetailItem);
		GroupChatTab.add($newGroupChoseItem[0],$newGroupDetailItem[0],true);
		//渲染成功后开始发建立链接
		var data = {groupId:groupId,};
		//先请求数据
		$.ajax({
			url:'chatgetGroupInfo.action',
			data:data,
			success:function(Res){
				console.log('群聊加入请求成功',Res);
				var res = JSON.parse(Res);
				console.log('接收到的群聊数据AJAX',res,res.items);
				group.messages = res.items||[];
				group.users = res.users;
				group.groupNum = group.users.length;

				var newMessageHtml = [];
				var newUserHtml = [];
				group.messages.forEach(function(message,index){
					newMessageHtml.push(groupMessageTpl(message));
				});
				group.users.forEach(function(user,index){
					newUserHtml.push(groupUserTpl(user));
				});
				$newGroupDetailItem.find('.group-user-top .num').html(group.groupNum);
//				console.log(newMessageHtml.join(''));
//				console.log(newUserHtml.join(''));
				$($groupDetailList.find('.group-message-list ul')).append($(newMessageHtml.join('')));
				$($groupDetailList.find('.user-list')).append($(newUserHtml.join('')));
			
			},
			error:function(Res){
				console.log('群聊加入请求失败',Res,JSON.parse(Res));
			},
			setTimeOut: 2000,
			timeOut: 2000,
		});
		//发送tio请求
		var tioData = 
		{
			groupId:groupId,
		};
		sendFunctons.openNewGroupChat(tioData);
	}
}

function newGroupChatBtn(event)
{
	var $DOM = $(event.target);
	$DOM.hasClass('group')?true:$DOM=$($DOM.parents('.group'));
	//从前台获取属性
	newGroupChat($DOM);
	
	$chatOverlay.addClass('hidden');
	$GroupChatOverlay.removeClass('hidden');
	
//	var groupId  = $DOM.attr('data-inDex');
//	if(openGroupMap.get(groupId+'')){	event.stopPropagation();;return false;}
//	var groupName= $DOM.find('.group-name').html();
//	
//	//先添加MAP
//	var group =
//	{
//		id :groupId,
//		groupName :groupName,
//		groupNum:1,
//		messages:[],
//		users:[],
//	}
//	groups.push(group);
//	openGroupMap.set( groupId,group);
////	console.log('插入的值',groupId,group);
//	//然后渲染
//	//生成panels 和showAreas
//	var $newGroupChoseItem = $(groupChoseItemTpl(group));
//
//	var $newGroupDetailItem = $(groupDetailItemTpl(group));
//
//	
//	$groupChoseList.append($newGroupChoseItem);
//	$groupDetailList.append($newGroupDetailItem);
//	GroupChatTab.add($newGroupChoseItem[0],$newGroupDetailItem[0]);
//	//渲染完成后 ，开始初始华群消息
//	var data = {groupId:groupId,};
//	$.ajax({
//		url:'chatgetGroupInfo.action',
//		data:data,
//		success:function(Res){
//			console.log('群聊加入请求成功',Res);
//			var res = JSON.parse(Res);
//			//调用渲染操作
//			console.log('接收到的数据',res,res.items);
//			group.messages = res.items||[];
//			group.users = res.users;
//			group.groupNum = group.users.length+1;
//
//			var newMessageHtml = [];
//			var newUserHtml = [];
//			group.messages.forEach(function(message,index){
//				newMessageHtml.push(groupMessageTpl(message));
//			});
//			group.users.forEach(function(user,index){
//				newUserHtml.push(groupUserTpl(user));
//			});
//			$newGroupDetailItem.find('.group-user-top .num').html(group.groupNum);
//			console.log(newMessageHtml.join(''));
//			console.log(newUserHtml.join(''));
//			$($groupDetailList.find('.group-message-list')).append($(newMessageHtml.join('')));
//			$($groupDetailList.find('.user-list')).append($(newUserHtml.join('')));
//			
//		},
//		error:function(Res){
//			console.log('群聊加入请求失败',Res,JSON.parse(Res));
//		},
//		setTimeOut: 2000,
//		timeOut: 2000,
//	});
//	//发送tio请求
//	var tioData = 
//	{
//		groupId:groupId,
//	};
//	sendFunctons.openNewGroupChat(tioData);
}

function newItemBtn(event){
	var $dom = $(event.target);
	console.log('点击用户方法出发',$dom);
	$dom.hasClass('item-btn')?true:$dom = $dom.parents('.item-btn');
	console.log('确认的$dom',$dom);
	//首先得到用户的ID
	var userId = $dom.attr('data-index');
	var itemId = UserId+'|'+userId
	console.log('拿到了用户的id',userId);
	//判断是不是已有的，没有就创建
	var items = ($itemList[0]).querySelectorAll('.item-item');
	var item; var flag=true;
	for(let index=items.length-1;index>=0;index--)
	{
		item = items[index];
		if(item.getAttribute('data-index')==itemId)
		{
			console.log('右匹配',item);
			flag=false;
			break;
		}
	}
	//需要新建立item
	if(flag)
	{
		window.$dom = $dom;
		console.log('还没有点开');
		//根据前台显示
		//封装item对象
		var newItem =
			{
				userItemId :UserId+'|'+userId,
				talkerId :userId,
				talkerPic :$($dom.find('img')).attr('src'),
				talkerName :$($dom.find('.name')).html(),
				messages :[],
			}
		//加入map
		itemMap.set(newItem.userItemId,newItem);
		console.log('产生的对象',newItem);
		//渲染吧
		$itemList.append(ItemItemTpl(newItem));
		//硬生生创造出 palen showArea
//		var $newChoseItem = $(createChoseItem(newItem));
//		var $newDetailItem = $(detailItemTpl(newItem));
//		$choseList.append($newChoseItem);
//		$detailList.append($newDetailItem);
//		console.log('产生的dom',$newChoseItem,$newDetailItem);
//		Tab.add($newChoseItem[0],$newDetailItem[0]);
		newTalk(newItem.userItemId);
		$GroupChatOverlay.addClass('hidden');
		$chatOverlay.removeClass('hidden');
	}
	else{
		console.log('已经点开了','激活',item.getAttribute('data-index'),item);
//		Tab.activeByKey(item.getAttribute('data-index'));
		newTalk(item.getAttribute('data-index'));
		$GroupChatOverlay.addClass('hidden');
		$chatOverlay.removeClass('hidden');
	}
	
}
function closeTalkBtn(event)
{
	console.log('删除');
	//得到id
	var $a = $(event.target);
	var itemId = $a.parents('.chose-item').attr('data-index');
	Tab.remove(itemId,true);
	//remove
	//修改本地储存
	for(let index = openItemIds.length-1;index>=0;index--)
	{
		if(itemId==openItemIds[index])
		{
			openItemIds.splice(index,1);
			break;
		}
	}
	setLocal('openItemIds',openItemIds);
	
	event.stopPropagation()
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
	console.log('收到数据',res.type,res);
	//json数据格式了
	switch(res.type)
	{
		//普通消息
		case 0:
			newChatListener(res.message);
			break;
		//有人加入群聊
		case 1:
			newGroupMemberListener(res.message);
			break;
		//群聊新消息
		case 2:
			newGroupMessageListener(res.message);
			break;
		//有人退出群聊
		case 3:
//			memberOutListener(res.message);
			memberOutListener(res);
			break;
		//有人新建了群聊
		case 4:
//			newItemListener(res.message);
			newItemListener(res);
			break;
		//有人删除了好友
		case 5:
			itemDelete(res.message);
			break;
	}

	/*
	 * 收到新的个人聊天信息
	 */
	function newChatListener(data)
	{
		//变量操作
		var message = data;
		var itemId = message.itemId;
		
		var $DetailItem = $(Tab.showAreas[Tab.attrMap.get(itemId)]);
		var item = itemMap.get(itemId+'');
	
		item.messages = item.messages||[];
		item.messages.push(message);
		//本地化储存
		setLocal(itemId,item.messages);
		//dom操作
		$($DetailItem.find('.message-list ul')).append($(chatMessageTpl(message)));
	}
	
	function newGroupMemberListener(data)
	{
		console.log('新用户进入',UserId,data);
		var groupNum = data.num;
		var groupId = data.groupId;
		var user = data.user;
		
		var $groupDetailItem = $(GroupChatTab.showAreas[GroupChatTab.attrMap.get(groupId)]);
		var group = openGroupMap.get(groupId);
		group.groupNum = groupNum;
		$groupDetailItem.find('.group-user-top .num').html(group.groupNum);
		//如果是本人不执行操作
		if(user.id==UserId)
		{
			
			return false;
		}

		group.groupNum++;
		console.log('我要渲染了',$groupDetailItem);
		$($groupDetailItem.find('.user-list')).append($(groupUserTpl(user)));
	}
	
	function newGroupMessageListener(data)
	{
		//变量操作
		var message = data;
		//如果是本人就不操作
		if(message.fromId==UserId){return false;}
		var groupId = data.groupId; 
		var group = openGroupMap.get(groupId +'');
		group.messages.push(message);
		var $groupDetailItem = $(GroupChatTab.showAreas[GroupChatTab.attrMap.get(groupId)]);
		//dom操作
		$($groupDetailItem.find('.group-messages')).append($(groupMessageTpl(message)));
	}
	
	function memberOutListener(data)
	{
		var message = data;
		var groupId = message.groupId;
		var groupNum = message.num;
		var userId = message.userId;
		//如果是本人不执行操作
		if(userId==UserId){ return false;}
		var group = openGroupMap.get(groupId +'');
		var $groupDetailItem = $(GroupChatTab.showAreas[GroupChatTab.attrMap.get(groupId)]);
		var userItems = $groupDetailItem[0].querySelectorAll('li.user-item');
		//在前台清除dom
		var userItem;
		for(let index=userItems.length-1;index>=0;index--)
		{
			userItem = userItems[index];
			if(userItem.getAttribute('data-index')==userId)
			{
				userItem.parentNode.removeChild(userItem);
				break;
			}
		}
		//在对象中清除储存
		var GroupUserId;
		for(let index=group.users.length-1;index>=0;index--)
		{
			GroupUserId = group.users[index].id;
			if(GroupUserId==userId)
			{
				group.users.splice(index,1);
				break;
			}
			
		}
		//修改在线人数
		group.groupNum = groupNum;
		$($groupDetailItem.find('.group-user-top .num')).html(group.groupNum);
		
		
	}
	
	function newItemListener(data)
	{
		//变量操作
		var item = data.items;
		//添加一个item
		items.push(item);
		itemMap.set(item.userItemId,item);
		//dom操作
		$itemList.append(ItemItemTpl(item));
		//硬生生创造出 palen showArea
//		var $newChoseItem = $(createChoseItem(item));
//		var $newDetailItem = $(detailItemTpl(item));
//		$choseList.append($newChoseItem);
//		$detailList.append($newDetailItem);
//		Tab.add($newChoseItem[0],$newDetailItem[0]);
		//已经创建并激活
	} 
	
	function itemDelete(data)
	{
		var deleteIds = data.deleteIds||[];
		deleteIds.forEach(function(deleteId,index){
			var itemId = UserId+'|'+deleteId;
			//删除本地
			deleteLocal(itemId);
			//清除dom
			Tab.remove(itemId,true);
			$itemList.find('[data-index="'+ itemId +'"]').remove();
			//添加提醒
		});
	}
}
function BeginSend()
{
	window.sendFunctons = 
	{
		openNewChat :function(){},
		deleteItem :function(Data){
			var data = {type:5,message:Data};
			tiows.send(JSON.stringify(data));
			console.log('tio发送',JSON.stringify(data));
		},
		sendChat :function(tempName,tempPic,message){
			var data = {type:0,tempName,tempPic,message:message};
			tiows.send(JSON.stringify(data));
			console.log('tio发送',JSON.stringify(data));
	
		},
		moreChatMessage :function(){},
		
		openNewGroupChat :function(Data){
			var data = {type:1,message:Data};
			tiows.send(JSON.stringify(data));
			console.log('tio发送',JSON.stringify(data));
		},
		closeGroupChat :function(Data){
			var data ={type:3,message:Data};
			tiows.send(JSON.stringify(data));
			console.log('tio发送',JSON.stringify(data));
		},
		sendGroupChat :function(Data){
			var data = {type:2,message:Data};
			console.log('tio发送GroupChat',JSON.stringify(data));
			tiows.send(JSON.stringify(data));
		},
		moreGroupChatMessage :function(){},
	};
}


function sendChatBtn(event)
{
	var sendBtnDom = event.target.localName=='button'?event.target:event.target.parentNode;
	var $DetailItem = $($(sendBtnDom).parents('.detail-item'));
	var $textArea = $DetailItem.find("textarea");
	var userItemId = $DetailItem.attr('data-index');
	var item = itemMap.get(userItemId);
	
	//得到用户临时的信息并封装
	var tempName = $DetailItem.attr('data-name');
	var tempPic = $DetailItem.attr('data-pic');
	
	var temp = 
	{
		tempName :tempName,
		tempPic :tempPic,
	}
	var message = {
		itemId :userItemId,
		fromId :sessionId,
		toId :userItemId.split('|')[1],
		sendTime :(new Date().getTime())+'',
		isRead :false,
		content:$textArea.val(),
	};
//	console.log('将要发送的消息',message);
	console.log('发送的message和temp',message,temp);
	//添加本消息并渲染
	
	$($DetailItem.find('.message-list ul')).append($(chatMessageTpl(message,true)));
	//消除本地消息
	$textArea.val('');
	//隐藏按钮
	hideSendBtn(sendBtnDom);
	var ItemId = message.itemId;
	//修改itemID方便对方接受
	message.itemId = ItemId.split('|')[1]+'|'+ ItemId.split('|')[0]
	//使用tio发送消息;
	var data = {temp :temp,message :message,};
	sendFunctons.sendChat(tempName,tempPic,message);
	//保存到本地
	message.fromId = UserId ;
	item.messages.push(message);
	setLocal(ItemId,itemMap.get(ItemId).messages);
}
function deleteItem($itemItem)
{
	var itemId = $itemItem.attr('data-index');
	//发送数据
	var tioData = 
	{
		deleteItemId :itemId,
	}
	sendFunctons.deleteItem(tioDat);
	//清除打开的对话
	Tab.remove(itemId,true);
	//清除本地记录
	deleteLocal(itemId);
	//清除dom
	$itemItem.remove();
}
function deleteItemBtn(event)
{
	var $itemItem = $($(event.target).parents('item-item'));
	deleteItem($itemItem);
	event.stopPropagation();
	return false;
}

function sendGroupChatBtn(event)
{
	var sendBtnDom = event.target.localName=='button'?event.target:event.target.parentNode;
	var $GroupDetailItem = $($(sendBtnDom).parents('.group-detail-item'));
	var $textArea = $GroupDetailItem.find("textarea");
	var groupId = $GroupDetailItem.attr('data-index');
	var group = openGroupMap.get(groupId+'');
	console.log('发信息的group',groupId,group);
	var groupMessage = {
		groupId :groupId,
		content :$textArea.val(),
		talkerId :sessionId,
		talkerPic :$GroupDetailItem.attr('data-talkerPic'),
		talkerName :$GroupDetailItem.attr('data-name'),
	};
	group.messages.push(groupMessage);
	//渲染
	$($GroupDetailItem.find('.group-messages')).append($(groupMessageTpl(groupMessage,true)));
	//恢复
	$textArea.val(''),
	//隐藏按钮
	hideSendBtn(sendBtnDom);
	//发送
	sendFunctons.sendGroupChat(groupMessage);
}


function closeGroupBtn(event)
{
		//得到id
	var $Dom = $(event.target);
	var groupId = $Dom.parents('.group-chose-item').attr('data-index');
	//remove
	GroupChatTab.remove(groupId,true);
	//从map中移除
	openGroupMap.delete(groupId+'');
	
	//前台组件移除完毕，开始向后台发送
	var tioData = {groupId:groupId};
	sendFunctons.closeGroupChat(tioData);
	event.stopPropagation()
	return false;
}

function DeleteItemBtn(event)
{
	var flag =confirm('你确定吗，你会彻底失去与此人的联系');
	var $dom =  $(event.target);
	var deleteItemId = $dom.parent().attr('data-index');
	
	if(flag)
	{
		var tioData = 
		{
			deleteItemId:deleteItemId,
		}
		sendFunctons.deleteItem(tioData);
	}
	console.log('开始删除一个人',$dom);
	//清除dom
	$dom.parents('.item-item').remove();
	//清除map
	itemMap.delete(deleteItemId);
	//清除local
	deleteLocal(deleteItemId);
	return false;
}


function inuputKeyUp(event)
{
	
	var $pageInputArea = $(event.target);
	window.$pageInputArea = $pageInputArea;
	var sendBtnDom = $pageInputArea.parent().parent().find('.send-btn')[0];
	if($pageInputArea.val().trim().length>0)
	{
		showSendBtn(sendBtnDom);
	}
	else   //防止输入空格回车或者删除输入内容
	{
		sendBtnDom.classList.remove('active-btn');
	}
}
function showSendBtn(sendBtnDom)
{
	sendBtnDom.classList.add('active-btn');
}
function hideSendBtn(sendBtnDom)
{
	console.log('隐藏按钮',sendBtnDom);
	sendBtnDom.classList.remove('active-btn');
}

function showDeletebtnBtn(event)
{
	var $dom = $(event.target);
	if($dom.html()=='管理')
	{
		$dom.html('完成');
		$($dom.parents('.list-item').find('.delete-btn')).removeClass('hidden');
	}
	else
	{
		$dom.html('管理');
		$($dom.parents('.list-item').find('.delete-btn')).addClass('hidden');
	}
	return false;
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
	//群聊选项卡
	GroupChatTab = TabByClass('GroupMessageArea','group-chose-item','group-detail-item');
	GroupChatTab.madeMap('data-index');
	GroupChatTab.addSelect('.cls-btn');
	//会话列表选项卡
	itemTab = TabByClass('MessageArea','top-panel','list-item');
	
	
	//添加点击事件
	
	//点击打开群聊


	//点击创建新会话
	$groupDetailList.on('click','.item-btn',newItemBtn);
	//点击创建新对话
	$itemList.on('click','.item-item',newTalkBtn);
	//点击关闭对话
	$choseList.on('click','.cls-btn',closeTalkBtn);
	//点击发送私聊消息
	$detailList.on('click','.send-btn.active-btn',sendChatBtn);
	//点击删除会话
	//点击创建新群聊
	$('.group-area').on('click','.join-btn',newGroupChatBtn);
	//点击发送群聊消息
	$groupDetailList.on('click','.send-btn.active-btn',sendGroupChatBtn);
	//点击关闭当前群聊
	$('.group-chose-list').on('click','.cls-btn',closeGroupBtn);
	
	
	//点击管理
	$('.item').on('click','.showDelete-btn',showDeletebtnBtn);
	//删除对话
	$('.item').on('click','i.delete-btn',DeleteItemBtn);
	
	//按钮状态事件
	$detailList.on('keyup','textarea',inuputKeyUp);
	$groupDetailList.on('keyup','textarea',inuputKeyUp);

}

function init()
{
	//渲染数据
//	renderData()
	// 渲染页面
	render();
	//事件绑定
	bindEvent();
}

tioReady();
askforData();
