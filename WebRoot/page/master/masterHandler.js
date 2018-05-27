var DemoHandler = function () {
	 var name = document.getElementById("name").innerHTML;
  this.onopen = function (event, ws) {
    ws.send(name+'连上了哦')
    document.getElementById('contentId').innerHTML += '你连上了哦<br>';
  }

  /**
   * 收到服务器发来的消息
   * @param {*} event 
   * @param {*} ws 
   */
  this.onmessage = function (event, ws) {
    var data = event.data
    document.getElementById('contentId').innerHTML += data + '<br>'
  }

  this.onclose = function (e, ws) {
    // error(e, ws)
	  
  }

  this.onerror = function (e, ws) {
    // error(e, ws)
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
