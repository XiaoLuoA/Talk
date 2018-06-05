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

function tioReady(flag)
{
	//tio初始化
	tioInit();
	//添加监听事件
	BieginListener();
	//添加ajax发送事件
	BeginSend();
	
}
function tioReady()
{
	console.log(initWs,queryString);
	initWs(queryString.trim());
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
	//点击创建新群聊
	//点击关闭当前群聊
	
	//准备响应tio
	tioReady();
}

function init()
{
	
	askforData();
	// 渲染页面
	render();
	//事件绑定
	bindEvent();
}

askforData();
