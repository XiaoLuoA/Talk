package test;

import org.tio.utils.json.Json;

import com.xiaoluo.model.User;

public class FastJsonTest {
	public static void main(String[] args) {
		System.out.println(Json.toBean("{userMessId :1,itemId :1,fromId :2,toId:1,sendTime :'2017-05-45|12:00',isRead:true,content:'这是一条信息',}", Msg.class));
	}
	
	public static void test(){
		
	}
}
class Msg{
	Integer userMessId;
	public Integer getUserMessId() {
		return userMessId;
	}
	public void setUserMessId(Integer userMessId) {
		this.userMessId = userMessId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	Integer itemId;
	Integer fromId;
	Integer toId;
	String sendTime;
	boolean isRead;
	String content;
}
