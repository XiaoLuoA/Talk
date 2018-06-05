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
	//初始条件不存在，或者初始条件不足
	if(false)
	{
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
	htmlText.push('<div class="detail-item" data-index="'+ item.userItemId +'">'+item.lastContent);
	htmlText.push('<div><textarea type="text" name="text"><button>发送</button></div>');
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
			htmltext.push('<p><span class="name">'+ item.talkerName +'</span><span class="last-time">'+ item.lastTime.split('|')[1] +'</span>' );
			htmltext.push('</p>');
			htmltext.push('<span class="content">'+(item.lastContent||''));
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

