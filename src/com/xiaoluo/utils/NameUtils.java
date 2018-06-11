package com.xiaoluo.utils;

public class NameUtils {
	
	/**
	 * 111个形容词语
	 */
	 static String[] desc1 = 
		{ "美丽","漂亮","清泠","残破","翩跹","落寞","尘封","封存", 
		  "怅惘","黯然","悸动","渺茫","恬淡","萦绕","皱褶 ","定格",
		  "凋落","旖旎","缱绻","踯躅","孤独","流连","素洁","晶莹",
		  "夕颜","秋恋","蝉羽","浴兰","沉鱼","落雁","闭月","羞花", 
		  "幽然","静微","优雅","卉馨","轩然","子茹","萦绕","流萤","静谧", 
		  "流凨","羽翼","蔓延","浅唱","轻盈","清芳","黯淡","纯洁",
		  "涤荡","皓月","思琪","绚烂","郁葱","寂寞","尘世", 
		  "旖旎","绚烂","璀璨","圣洁","香醇","透明","晶莹","纯净","典雅",
		  "雄伟","壮丽","活泼","婉约","轻灵","隽丽",
		  "凝炼","流畅","独特","别致","美好","甜美","冷艳","独特","高贵","高雅","俊朗",
		  "真诚","实在","创意","安全","神奇","蔚蓝","浩瀚","传统","阴霾","隐晦","娇憨",
		  "时尚","细致","开朗","舒适","清澈","颓废","温柔","惊艳","端庄","淡定","野蛮",
		  "淡漠","丰盈","圆滑","虚伪","伪装","生硬"};
	 
	 
	 static String[] desc2 = 
		{ "美丽","漂亮","清泠","残破","翩跹","落寞","尘封","封存", 
		  "怅惘","黯然","悸动","渺茫","恬淡","萦绕","皱褶","定格 ",
		  "凋落","旖旎","缱绻","踯躅","孤星","流连","素洁","晶莹",
		  "夕颜","秋恋","蝉羽","浴兰","沉鱼","落雁","闭月","羞花", 
		  "幽然","静微","艺雅","卉馨","轩然","子茹","萦绕","流萤","静谧", 
		  "流凨","羽翼","蔓延","浅唱","轻盈","清芳","黯淡","纯洁 ",
		  "涤荡","皓月","思琪","绚烂","郁葱","寂寞","尘世", 
		  "旖旎","绚烂","璀璨","圣洁","香醇","透明","晶莹","纯净","典雅",
		  "雄伟","壮丽","活泼","婉约","轻灵","隽丽",
		  "凝炼","流畅","独特","别致","美好","甜美","冷艳","独特","高贵","高雅","俊朗",
		  "真诚","实在","创意","安全","神奇","蔚蓝","浩瀚","传统","阴霾","隐晦","娇憨",
		  "时尚","细致","开朗","舒适","清澈","颓废","温柔","惊艳","端庄","淡定","野蛮",
		  "淡漠","丰盈","圆滑","虚伪","伪装","生硬"};
	 
	 /**
	  * 100个名词
	  */
	static  String[] names = {
				"兔子","老虎","犀牛","蟒蛇","海豹","孔雀","鳄鱼","猴子","棕熊",
				"豺狼","石榴","香蕉","苹果","梨子","草莓","芒果","葡萄","蜜桃",
				"荔枝","西瓜","海报","墙壁","围墙","汽车","卡车","单车","单词",
				"鼠标","书包","书本","象棋","香气","围棋","跳棋","军旗","军区",
				"蘑菇","木耳","母鸡","木屐","钱包","铅笔","前辈","相片","橡皮",
				"尺子","彩旗","古筝","小孩","小号","笑话","校花","小花","草原",
				"草鱼","鲈鱼","梭鱼","琵琶","竖琴","扬琴","芹菜","黄瓜","皇冠",
				"皇宫","黄山","皇上","香水","面膜","面貌","面目","唇膏","作业",
				"座椅","数字","树枝","树脂","书桌","椅子","板凳","岛屿","刀鱼",
				"导师","道士","和尚","水流","花朵","树木","荷花","莲花","荷塘",
				"合同","核桃","木鱼","钱包","铅笔","前辈","狮子","时装","市长",
				"师长"
		};
	 
	 
	static String[] picPaths = {
			"1.png","2.png"
	};
	
	public static void main(String[] args) {
		for(int i = 0;i<desc1.length;i++){
			if(desc1[i].equals("孤星"))
				System.out.println(i);
		}
		for(int i = 0;i<20;i++){
			System.out.println(getNickName());
		}
		
	}
	
	public static String getNickName(){
		int desc1Index = (int)(Math.random()*110);
		int desc2Index = desc1Index;
		while(desc2Index==desc1Index)
		   desc2Index = (int)(Math.random()*110);
		
		int nameIndex = (int)(Math.random()*99);
		return desc1[desc1Index]+"又"+desc2[desc2Index]+"的"+names[nameIndex];
	}
	
	public static String toStringArray(String str){
		System.out.println(str.replaceAll("，", "\",\""));
		return str;
	}
	
	
	public static String getNickPic(){
		int picIndex = (int)(Math.random()*2);
		return picPaths[picIndex];
	}
	
}
