var $choseList  = $('.chose-list');
var $detailList = $('.detail-list'); 
var $itemList   = $('.item-list');

var $groupChoseList = $('.group-chose-list');
var $groupDetailList =$('.group-chose-list');

var $chatOverlay= $('.chat-overlay');
var $GroupChatOverlay= $('.group-chat-overlay');

var $showChatBtn = $('#chatBtn');
var $showGroupChatBtn = $('#groupChatBtn');

var chatTab;
var GroupChatTab;
var showGroupMap = new Map();
var openGroupMap = new Map();
var itemMap = new Map();


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
				console.log(res);
				flag = true;
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
//渲染数据
function createChoseItem(item)
{
	var htmlText = [];
	htmlText.push('<div class="chose-item" data-index="'+ item.userItemId +'">'+item.talkerName);
	htmlText.push('<span class="cls-btn float-r">关闭</span></div>')
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
	htmlText.push('<div class="detail-item" data-index="'+item.userItemId+'">');
	htmlText.push('<div class="message-list">>');
	htmlText.push('<ul ');
	if(item.messages){
	item.messages.forEach(function(message,index){
		htmlText.push(chatMessageTpl(message));
	});}
	htmlText.push('</ul></div>');
	htmlText.push('<div><textarea class="chat-btn" type="text" name="text"></textarea><button class="btn send-btn active-btn">发送</button></div>');
	htmlText.push('</div>')
	return htmlText.join('');
}

function ItemItemTpl(item)
{
	var htmltext = [];
	htmltext.push('<div class="item-item" data-index="'+ item.userItemId +'">');
		htmltext.push('<div class="head-img"><img src="');htmltext.push(item.talkPic);
		htmltext.push('"></div>')
		htmltext.push('<div class="ietm-item-detail">');
			htmltext.push('<p><span class="name">'+ item.talkerName +'</span><span class="last-time">'+ (item.lastTime+'') +'</span>' );
			htmltext.push('</p>');
//			console.log('你好',messages[messages.length-1].lastTime);
			htmltext.push('<span class="content">'+(messages[messages.length-1].content||''));
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
function groupDetailItemTpl(group)
{
	var htmltext = [];
	htmltext.push('<div class="group-detail-item ">');
	htmltext.push('<div class="group-message-list scrll-y"><ul class="gruop-message">');
	    group.messages.forEach(function(message,index){
	    	htmltext.push(groupMessageTpl(message));
	    });
	htmltext.push('</ul></div>');
	htmltext.push('<textarea type="text" name="text" ></textarea><button class="send-btn">提交</button>');
	htmltext.push('</div>');
	return htmltext.join('');
}
function groupChoseItemTpl(group)
{
	var htmltext = [];
	htmltext.push('<div class="group-chose-item" data-index='+ group.id +'>');
		htmltext.push(group.groupName);htmltext.push('<span class="cls-btn float-r">关闭</span>');
	htmltext.push('</div>');
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
}

