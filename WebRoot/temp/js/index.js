var $choseList  = $('.chose-list');
var $detailList = $('.detail-list'); 
var $itemList   = $('.item-list');

var $chatOverlay= $('.chat-overlay');
var $GroupChatOverlay= $('.group-chat-overlay');

var $showChatBtn = $('#chatBtn');
var $showGroupChatBtn = $('#groupChatBtn');

var chatTab;
var GroupChatTab;
var showGroupMap = new Map();
var openGroupMap = new Map();
var itemMap = new Map();

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
				console.log('元数据',Res);
				var res = JSON.parse(Res);
				console.log('success',res);
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
function createDetailItem(item)
{
	var htmlText = [];
	htmlText.push('<div class="detail-item" data-index="'+item.userItemId+'">');
	htmlText.push('<ul class="message-list">');
	item.messages.forEach(function(message,index){
		htmlText.push('<li class="message-item" data-sendindex="'+ message.fromId +'">'+ message.content +'</li>');
	});
	htmlText.push('</ul>');
	htmlText.push('<div><textarea type="text" name="text"></textarea><button>发送</button></div>');
	htmlText.push('</div>')
	return htmlText.join('');
}
function createItemItem(item)
{
	var message = item.message
	var htmltext = [];
	htmltext.push('<div class="item-item" data-index="'+ item.userItemId +'">');
		htmltext.push('<div class="head-img"><img src="');htmltext.push(item.talkPic);
		htmltext.push('"></div>')
		htmltext.push('<div class="ietm-item-detail">');
			htmltext.push('<p><span class="name">'+ item.talkerName +'</span><span class="last-time">'+ (item.lastTime+'') +'</span>' );
			htmltext.push('</p>');
			console.log('你好',item.messages[item.messages.length-1].content);
			htmltext.push('<span class="content">'+(item.messages[item.messages.length-1].content||''));
			htmltext.push('</span>');
		htmltext.push('</div>');
	htmltext.push('</div>')
	itemMap.set(item.userItemId+'',item);
	return htmltext.join('');
}
function createMessage()
{
	
}

function render()
{
	var htmlText =[];
	items.forEach(function(item,index){
		htmlText.push(createItemItem(item));
	})
	var html = htmlText.join('');
	$itemList.append(html) ;
}

