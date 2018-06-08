
function TabByClass(id,panleClass,showAreaClass,num)
{
	var container = document.getElementById(id);
	var panles = Array.prototype.slice.call(container.querySelectorAll("."+panleClass));
	var showAreas = Array.prototype.slice.call(container.querySelectorAll("."+showAreaClass));
	return new TAb(panles,showAreas,num||0);
}
function TAb(panles,showAreas,num)
{
	var length = panles.length>showAreas.length?showAreas.length:panles.length;
	num = num>=length?length:num;	
	
	this.init(panles,showAreas,num,length);
}
TAb.prototype.init = function(panles,showAreas,num,length){
	this.removeTime=0;
	this.panles = panles.splice(0,length);
	this.showAreas = showAreas.splice(0,length);
	this.panles.size = this.panles.length;
	for(let i=this.panles.length-1;i>=0;i--)
	{
		this.panles[i].target = this.showAreas[i]
	}
	this.current = num||0;
//	this.allHide();
    if(this.panles.size>0)
    {
    	this.active(this.current);
		this.addClick();
    }

};

TAb.prototype.addClick = function(){
	var self = this;
	for(let i=this.panles.length-1;i>=0;i--)
	{
		this.panles[i].addEventListener('click',function(event){if(self.clickFlag.call(self,event)){return;};self.active.call(self,i)});
	}
};

TAb.prototype.allHide = function(){
   this.panles.forEach(function(a,b){a.classList.add("hidden");});
   this.showAreas.forEach(function(a,b){a.classList.add("hidden");});
};
TAb.prototype.activeByKey = function(key){
	if(this.attrMap)
	{
		this.active(this.attrMap.get(key));
	}
	else
	{
		this.active(key)
	}
}
TAb.prototype.active = function(Num){
//	console.log(44,Num,this)
	var num = Num||0;
	num = Number(num);
	num = num>this.panles.length-1?this.panles.length-1:num;
//	if(num==this.current){return ;}
	this.panles[this.current].classList.remove("active");
	this.showAreas[this.current].classList.remove("active");
	this.panles[num].classList.add("active");
	this.showAreas[num].classList.add("active");
	this.current = num;
};
/**
 * 
 * @param {object} panle
 * @param {objrct} showArea
 * @param {boolean} isActive
 */
TAb.prototype.add = function(panle,showArea,isActive){
	var self = this;
	this.panles.push(panle);
	this.showAreas.push(showArea);
	this.panles.size++;
	if(isActive!=true){isActive=false;}
	let num = self.panles.length-1
	panle.target = showArea;
	panle.addEventListener('click',function(event){if(self.clickFlag.call(self,event)){return;};self.active.call(self,num)});
//	panle.onclick =(function(event){console.log(event);self.active.call(self,num)});
	if(this.panles.size==0||isActive)
	{
		console.log('为什么不显示呢','isActive',isActive,this.panles.length);
		this.active(this.panles.length);
	}
	else
	{
		panle.classList.remove("active");
		showArea.classList.remove("active");
	}
	if(this.attrMap)
	{
		var data = panle.getAttribute(this.data);
		if(data)
		{
			this.attrMap.set(data+'',num);
		}
	}
	return this.panles.length;
};
TAb.prototype.remove = function(num,isClear){
	
	if(this.attrMap)
	{ console.log('存在map',this.attrMap,num);
		var a = this.attrMap.get(num);
		this.attrMap.delete(num);
		num = a;
		console.log('当前的num',a);
	}
	var start = num;
	var panle = this.panles[num];
	var showArea = this.showAreas[num];
	if(num<0||num>this.panles.length-1){return null;}
	
	if(num==this.current)
	{
		do
		{
			num = (num+1)%this.panles.length;
//			console.log(num,'循环',start);
			if(this.panles[num])
			{
//				console.log(num,'跳出');
				break;
			}
//			console.log(num,'循环为',start,num==start);
		}
		while(num!=start)
//		console.log(num,start,1111,this.panles);
		//表示还有剩余的元素
		if(num!=start)
		{
			this.active(num);
		}
		
	}
	if(isClear)
	{
		panle.parentNode.removeChild(panle);
		showArea.parentNode.removeChild(showArea);
	}
	this.panles[start] = null;
	this.showAreas[start] = null;console.log(this.panles.size,'size变化前');
	this.panles.size--;
//	console.log(this.panles.size,'size变化后');
	//启用更新方法
	
	if(this.panles.size==0)
	{
//		console.log('更新');
		this.updataArray();
	}
	else
	{
		this.removeTime>5?this.updataArray():this.removeTime++;
	}

	return [panle,showArea];
};
TAb.prototype.changePanle = function(Num){
	var num = Num||1;
	active(num%this.panles.length);
};
TAb.prototype.nextPanle = function(){
	changePanle(1);
};
TAb.prototype.lastPanle = function(){
	changePanle(-1);
};
TAb.prototype.updataArray = function(){
	var self = this;
	var size =0;
	var newPanles = [];var newShowAreas = [];
	this.panles.forEach(function(a,b){
		if(a)
		{
			newPanles.push[a];
			newShowAreas.push(this.showAreas[b]);
			size++;
		}
	});
	this.panles = newPanles;
	this.showAreas = newShowAreas;
	this.panles.size = size;
	var flag = this.attrMap?true:false;
	var data = this.data;
	if(true)
	{
		var New = new TAb(newPanles,newShowAreas);
		if(flag){
			New.madeMap(data);
		}
	}
	
	self = New;
}
TAb.prototype.madeMap = function(dataa){
	var self =this;
	var data = dataa||'id';
	this.data = data;
	this.attrMap = new Map();
	this.panles.forEach(function(a,b){
		var attr = a.getAttribute(data);
		if(attr)
		{
			self.attrMap.set(attr+'',b);
		}
	});
};
TAb.prototype.addSelect = function(selecter){
	this.seleter = this.selseter||[];
	this.seleter.push(selecter);
};
TAb.prototype.clickFlag = function(event)
{
	
	if(this.seleter)
	{
		var $dom = $(event.target);
//		console.log($dom);
		var seleter;
		for(let i=0;i<this.seleter.length;i++)
		{
			seleter = this.seleter[i];
			if($dom.is(seleter)||$($dom.parent).is(seleter))
			{
				return true;
			}
		}
	}
	return false;
}
