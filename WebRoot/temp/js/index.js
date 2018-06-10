var $choseList  = $('.chose-list');
var $detailList = $('.detail-list'); 
var $itemList   = $('.item-list');

var $groupChoseList = $('.group-chose-list');
var $groupDetailList =$('.group-detail-list');

var $chatOverlay= $('.chat-overlay');
var $GroupChatOverlay= $('.group-chat-overlay');

var $showChatBtn = $('#chatBtn');
var $showGroupChatBtn = $('#groupChatBtn');

var chatTab;
var GroupChatTab;
var openItemId = [];
var showGroupMap = new Map();
var openGroupMap = new Map();
var itemMap = new Map();

var deleteIds = [];

var meaasgeNUll = {sendTime:'',content:''};



//请求初始数据
function askforData()
{
	var flag;
	//用户已经登录，则加载数据
	if(sessionId&&sessionId.trim()!='')
	{
		//唯一一次请求服务器，使用ajax
		var message = {sessionId:sessionId};
		$.ajax({
			url :'indexgetAllMsg.action',
			data :message,
			success :function(Res){
//				console.log('元数据',Res);
				var res = JSON.parse(Res);
//				console.log('success',res);
				items = res.items;
				deleteIds = res.deleteIds||[];
				deleteLocalMessage();
				flag = true;
				getLocalMessage(items);
				init();
			},
			error :function(Res)
			{
				console.log('元数据',Res);
				var res = eval(Res);
				console.log('failed',res);
				alert(res.message)
			},
			setTimeOut:8000,
		});
		//请求初始数据或者到本地加载
	}
}
/*
 *加载本地消息的方法 
 */
function deleteLocalMessage()
{
	deleteIds.forEach(function(deleteId,index){
		var itemId = UserId+'|'+deleteId;
		//删除本地
		localStorage.remove(itemId);
	});
}
function getLocalMessage(items)
{
	if(items)
	{
		
		items.forEach(function(item,index){
			var messages = readLocal(item.userItemId);
			messages = messages?messages:[];
			
			//两个messages相互拼接
			messages.push.apply(messages,item.messages);
			item.messages = messages;
			
			openItemIds = readLocal('openItemIds')||[];
		});
		
	}
}
//本地数据的操作
function setLocal(key,value,isStr)
{
	if(isStr)
	{
		//保存为字符串
		localStorage.setItem(key+'',value);
	}
	else
	{
		//保存为json字符串
		var json = JSON.stringify(value);
		localStorage.setItem(key+'',json);
	}
	var json = JSON.stringify(value);
	localStorage.setItem(key+'',json);
}
function readLocal(key,isStr)
{
	return  isStr?localStorage.getItem(key+''):JSON.parse(localStorage.getItem(key+''));
}
function deleteLocal(key){
	localStorage.deleteItem(key+'');
}
//渲染数据
function createChoseItem(item)
{
	var htmlText = [];
	htmlText.push('<div class="chose-item" data-index="'+ item.userItemId +'">');
	htmlText.push('<img class="head-img" src="'+item.talkerPic+'">');
	htmlText.push('<span class="talker-name">'+item.talkerName+'</span>');
	htmlText.push('<i class="cls-btn hidden"></i><span class="show-newnum "> new </span></div>');
	
	return htmlText.join('');
	
}
function chatMessageTpl(message)
{
	htmlText =[];
	htmlText.push('<li class="message-item" data-sendindex="'+ message.fromId +'">'+ message.content +'</li>');
	return htmlText.join('');
}

function detailItemTpl(item)
{
	var htmlText = [];
	htmlText.push('<div class="detail-item" data-index="'+item.userItemId+'" data-name="'+ item.talkerName +'" data-pic="'+ item.talkerPic +'">');
		htmlText.push('<div class="detail-title">');
			htmlText.push('<img class="head-img" src="'+ item.talkerPic +'"><span class="talker-name">'+ item.talkerName +'</span>');
		htmlText.push('</div>');
		htmlText.push('<div class="message-list">');
		htmlText.push('<ul>');
		if(item.messages){
			item.messages.forEach(function(message,index){
				htmlText.push(chatMessageTpl(message));
			});
		}
		htmlText.push('</ul></div>');
	htmlText.push('<div class="tool">工具栏</div>')
	htmlText.push('<div><textarea class="message-input" type="text" name="text"></textarea></div>');
	htmlText.push('<div class="button-area"><button class="btn send-btn"><span>发 送</span></button></div>');
	htmlText.push('</div>')
	return htmlText.join('');
}

function ItemItemTpl(item)
{
	var htmltext = [];
	var messages = item.messsages;
	if(messages){}else{messages=[]};
	htmltext.push('<div class="item-item" data-index="'+ item.userItemId +'">');
		htmltext.push('<div class="head-img"><img src="');htmltext.push(item.talkerPic);
		htmltext.push('"></div>')
		htmltext.push('<div class="ietm-item-detail">');
			htmltext.push('<p><span class="name">'+ item.talkerName +'</span><span class="last-time">'+ (item.lastTime+'') +'</span>' );
			htmltext.push('</p>');
//			console.log('你好',messages[messages.length-1].lastTime);
			htmltext.push('<span class="content">'+(messages.length>0?([messages.length-1].content||''):''));
			htmltext.push('</span>');
		htmltext.push('</div>');
	htmltext.push('</div>')
	
	return htmltext.join('');
}
function groupMessageTpl(message)
{
	var htmltext = [];
	htmltext.push('<li>');
	htmltext.push(message.content);
	htmltext.push('</li>');
	return htmltext.join('');
}
function groupUserTpl(user)
{
	if(UserId==user.Id){return ;}
	var htmltext = [];
	htmltext.push('<li class="user-item item-btn" data-index="'+ user.id +'">');
	htmltext.push('<div class="user-item-scal"><div class="head-img"><img src="'+ user.pic +'"></div>');
	htmltext.push('<p class="name">'+ (user.name||"").replace(/\s+/g,"") +'</p>');
	htmltext.push('</div></li>');
	return htmltext.join('');
}
function groupDetailItemTpl(group)
{
	var htmltext = [];
	htmltext.push('<div class="group-detail-item " data-index='+ group.id +'>');
	htmltext.push('<div class="group-detail-message ">');
	htmltext.push('<div class="group-detail-title">');
	htmltext.push('<img class="head-img" src="'+ group.groupPic +'"><span class="group-name">'+ group.groupName +'</span></div>');
	htmltext.push('<div class="group-message-list "><ul class="group-messages">');
	    group.messages.forEach(function(message,index){
	    	htmltext.push(groupMessageTpl(message));
	    });
	htmltext.push('</ul></div>');
	htmltext.push('<div class="tool">工具栏</div>')
	htmltext.push('<div><textarea class="message-input" type="text" name="text"></textarea></div>');
	htmltext.push('<div class="button-area"><button class="btn send-btn"><span>发 送</span></button></div>');
	htmltext.push('</div>');
	
	/*
	 *另一个栏目开始
	 * */
	htmltext.push('<div class="group-users">');
		htmltext.push('<div class="group-user-top">');
		htmltext.push('<p>在线人数<span class="num">'+ group.groupNum +'</span></p>');
		htmltext.push('</div>');
		htmltext.push('<div class="group-user">');
			htmltext.push('<ul class="user-list"><!--消息列表-->');
			group.users.forEach(function(user,index){
				htmltext.push(groupUserTpl(user));
			});
			htmltext.push('</ul>');
		htmltext.push('</div>');
	htmltext.push('</div>');
	
	htmltext.push('</div>');
	return htmltext.join('');
}
function groupChoseItemTpl(group)
{
	var htmltext = [];
	htmltext.push('<div class="group-chose-item" data-index='+ group.id +'>');
	htmltext.push('<img class="head-img" src="'+group.groupPic+'">');
	htmltext.push('<span class="group-name">'+group.groupName+'</span>');
	htmltext.push('<i class="cls-btn hidden"></i><span class="show-newnum "> new </span></div>');
	return htmltext.join('');
}

function deteleMessageTpl(item)
{
	var htmltext = [];
	htmltext.push('<p class="message">用户 '+ item.talkerName +' 关闭了和你的对话</P>');
	return htmltext.join('');
}


function render()
{
	var htmlText =[];
	if(items){
	items.forEach(function(item,index){
		item.messages = item.messages||[];
		itemMap.set(item.userItemId+'',item);
		htmlText.push(ItemItemTpl(item));
	});}
	var html = htmlText.join('');
	$itemList.append(html) ;
	//从本地完善已经添加的消息
	openItemIds.forEach(function(itemId,index){
		var item = itemMap.get(itemId);
		if(item)
		{
			var $newChoseItem = $(createChoseItem(item));
			var $newDetailItem = $(detailItemTpl(item));
			$choseList.append($newChoseItem);
			$detailList.append($newDetailItem);
			console.log('本地已打开对话添加成功',$newChoseItem[0],$newDetailItem[0]);
		}
		//Tab创建时会自动加载
		//Tab.add($newChoseItem[0],$newDetailItem[0],true);
	});
}

