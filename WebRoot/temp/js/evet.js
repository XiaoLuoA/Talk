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
			var $newDetailItem = $(createDetailItem(item));
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
	console.log(event.target,'子元素');

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
	console.log(event.target.classList.contains('overlay'));
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
		var name = "123";
		this.onopen = function (event, ws) {
			console.log('open详情',event,ws);
    		ws.send(name+'连上了哦')
		}

  		/**
   		* 收到服务器发来的消息
   		* @param {*} event 
   		* @param {*} ws 
   		*/
		this.onmessage = function (event, ws) {
			console.log('onmessage启动',event);
			var data = event.data
		}
		this.onclose = function (e, ws) {
			console.log('onclose启动',e);
		}
		this.onerror = function (e, ws) {
			console.log('onerror',e);
		}

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
	var port = 8080
	var heartbeatTimeout = 5000; // 心跳超时时间，单位：毫秒
	var reconnInterval = 1000; // 重连间隔时间，单位：毫秒
	var binaryType = 'blob'; // 'blob' or 'arraybuffer';//arraybuffer是字节
	var handler = new DemoHandler();
	var param = null;
	window.tiows = new tio.ws(protocol, ip, port, queryString, param, handler, heartbeatTimeout, reconnInterval, binaryType)
  	tiows.connect()
  	console.log('tiows建立连接');

	function send () {
  	var msg = document.getElementById('textId')
  	tiows.send(msg.value)
}

}

function tioReady()
{
	//tio初始化
	tioInit(queryString.trim());
	//	tioInit();

	//添加监听事件
	BieginListener();
	//添加ajax发送事件
	BeginSend();
	
}


function BieginListener()
{
	function newGroupMessageListener(data)
	{
		//变量操作
		//dom操作
	}
	function newMessageListener(data)
	{
		//变量操作
		//dom操作
	}
	function newItemListener(data)
	{
		//变量操作
		//dom操作
	} 
}
function BeginSend()
{
	window.sendFunctons = 
	{
		openNewChat :function(){},
		closeChat :function(){},
		sendChat :function(){},
		moreChatMessage :function(){},
		
		openNewGroupChat :function(){},
		closeGroupChat :function(){},
		sendGroupChat :function(){},
		moreGroupChatMessage :function(){},
	};
}
function sendChatBtn(event)
{
	var $DetailItem = $(event.target.parents('detail-item'));
	var userItemId = $DetailItem.attr('data-index');
	var item = itemMap.get(userItemId);
	var messge = {
		itemId :userItemId,
		fromId :sessionId,
		toId :item.talkerId,
		sendTime :(new Date())+'',
		isRead :false,
		content:'$DetailItem.find("textarea").value',
	};
	item.messages.push(message)
}
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
	//点击创建新会话
	//点击创建新对话
	$itemList.on('click','.item-item',newTalk);
	//点击关闭对话
	$choseList.on('click','.cls-btn',closeTalkBtn);
	//点击发送私聊消息
	$detailList.on('click','button',sendChatMessage)
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
