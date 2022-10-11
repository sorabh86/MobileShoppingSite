;(function(so, $) {
	so.URL = 'http://localhost:8080/ws';
	so.MURL = "/uigo/message";
	so.PMURL = "/uigo/private";
	so.AURL = "/uigo/message.add";
	so.$memberList = $(".member-list");
	so.$chatRooms = $(".chat-rooms");
	
	var p = so.Chat = function(username) {
		this.stompClient = null;
		this.username = username;
		this.publicChats = [];
		this.privateChats = [];
		
		console.log("Try to connect with server ", username);
		this.connect();
	};
	
	var p = so.Chat.prototype = {};
	
	p.connect = function() {
		this.socket = new SockJS(so.URL);
		this.stompClient = Stomp.over(this.socket);
		this.stompClient.connect({}, this.onConnected.bind(this), this.onError.bind(this));
	};
	
	p.onConnected = function() {
		console.log("onConnected", this.stompClient);
		
		// Subscribe to the Public Topic
	    this.stompClient.subscribe('/chatroom/public', this.onMessageReceived.bind(this));
		// Subscribe to the Private Topic
	    this.stompClient.subscribe('/user/'+this.username+'/private', 
	    	this.onPrivateMessageReceived.bind(this));
	    
	    var users = $(".member-list ul .member");
	    for(var i = 0; i<users.length; i++) {
			var username = $(users[i]).data("href");
	    	this.stompClient.subscribe('/user/'+username+'/private', 
	    		this.onPrivateMessageReceived.bind(this));
		}
	    
	    // User Join
	    this.stompClient.send(so.AURL, {}, JSON.stringify({
            username: this.username,
            status:"JOIN"
        }));
	    
	    if(this.connectedHandler) this.connectedHandler();
	};
	p.onError = function (error) {
		console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
	};
	p.onMessageReceived = function(payload){
		console.log("onMesssageReceived:", payload);
		var payloadData = JSON.parse(payload.body);

		if(payloadData.status === "JOIN") {
			if(this.username!=payloadData.username) {
				var $userStatus = so.$memberList.find("ul ."+payloadData.username+" .user-status");
				$userStatus.removeClass("bg-danger");
				$userStatus.addClass("bg-success");
//				if(so.$memberList.find("ul ."+payloadData.username).length<=0){
//					so.$memberList.find("ul").append(this.getUserTab(payloadData.username));
//					so.$chatRooms.append(this.getUserContent(payloadData.username));
//				}
			}
		} else if(payloadData.status === "LEAVE") {
			var $userStatus = so.$memberList.find("ul ."+payloadData.username+" .user-status");
			$userStatus.removeClass("bg-success");
			$userStatus.addClass("bg-danger");
//			so.$memberList.find("ul li[data-href='."+payloadData.username+"']").remove();
//			so.$chatRooms.find("."+payloadData.username).remove();
		} else {
			so.$chatRooms.find(".chatroom .chat-messages").append(this.getChatMessage(payloadData));
		}
		
		if(this.updateHandler) this.updateHandler(payloadData);
	};
	p.onPrivateMessageReceived = function(payload) {
		console.log("onPrivateMessageReceived:", payload);
		var payloadData = JSON.parse(payload.body);
		
		if(payloadData.status === "MESSAGE") {
			if(this.username==payloadData.username) {
				so.$chatRooms.find("."+payloadData.receiverUsername+" .chat-messages").append(this.getChatMessage(payloadData));				
			} else {
				so.$chatRooms.find("."+payloadData.username+" .chat-messages").append(this.getChatMessage(payloadData));
			}
		}
        
        if(this.updateHandler) this.updateHandler(payloadData);
	};
	p.sendMessage = function(message){
        if (this.stompClient) {
          console.log("sendValue:", message);
          this.stompClient.send(so.MURL, {}, JSON.stringify({
            username: this.username,
            message: message,
            status:"MESSAGE"
          }));
        }
    };
	p.sendPrivateMessage = function(message, receiverUsername){
		if (this.stompClient) {
          console.log("sendValue:", message);
          this.stompClient.send(so.PMURL, {}, JSON.stringify({
            username: this.username,
           	receiverUsername: receiverUsername,
            message: message,
            status:"MESSAGE"
          }));
        }
    };
    
	p.getChatMessage = function(chat) {
		var dateParts = chat.date.split("-");
		var dateStr = dateParts[2].substr(0,2)+"/"+dateParts[1]+"/"+dateParts[0].substr(2,2);
		console.log(dateStr); 
		var str = '';
		if(chat.username == this.username) {
			str = '<li class="message self">\
				<div class="message-data">'+chat.message+'</div>\
				<div class="avatar self">'+chat.username+'<br />\
    				<small class="badge">'+dateStr+'</small>\
				</div>\
			</li>';
		} else {
			str = '<li class="message">\
				<div class="avatar">'+chat.username+'<br />\
    				<small class="badge">'+dateStr+'</small>\
				</div>\
				<div class="message-data">'+chat.message+'</div>\
			</li>';
		}
		return str;
	}
})(window.so = window.so || {}, jQuery);

/**** DOM SCRIPTURES **/
var $body = $(document.body);

var $startBtn = $("#start-btn");
$startBtn.click(loginHandler);
function loginHandler(){
	if(window.chat) {
		console.log("already connected");
	} else {
		var username = $startBtn.data("name");
		
		window.chat = new so.Chat(username);
		window.chat.connectedHandler=function(){
			$(".chat-status").html('<span class="fa fa-circle text-success" ></span> Online');
		};
		window.chat.updateHandler = function(){
			scrollBottom();
		};
	}
	return false;
}

function scrollBottom(){
	// scroll active chat box to bottom
	$chatMessages = $(".chat-content.active .chat-messages")
	$chatMessages.scrollTop($chatMessages[0].scrollHeight);
}

function getGroupChat(username, $element) {
	$.ajax({
		url:"/admin/chats/messages/"+username+"/"+window.chat.username, 
		dataType:"json",
		success:function(s){
			var htmlStr = '';
			for(var i in s) {
				var chat = s[i], str='', date = (new Date(chat.date)).toJSON();
				chat.date = date;
				htmlStr += window.chat.getChatMessage(chat);
			}
			$element.html(htmlStr);
			console.log('get-private-chat'+s);
		}, error:function(e){
			console.log(e)
		}
	});
}
loginHandler();

// Switch between different rooms
$body.on("click", ".member-list .member", function(){
	var $this = $(this),
		href = $this.data("href");
	
	$(".member-list .member").removeClass("active");
	$this.addClass("active");
	
	var $chatContent = $('.chat-content');
	$chatContent.removeClass("active");
	var $element = $chatContent.filter('.'+href).addClass("active");

	if(href!='chatroom')
		getGroupChat(href, $element.find('.chat-messages'));
	
	setTimeout(scrollBottom, 100);
	return false;
});

// Sending messages to different rooms
$body.on("keyup", ".input-message", function(e){
	if(e.keyCode == 13) { // ENTER KEY
		var $this = $(this),
			$parent = $this.parent().parent(),
			message = $(this).val(),
			receiverUsername = $(".member.active").data("href");
			
		if($parent.hasClass('chatroom')) {
			window.chat.sendMessage(message);			
		} else {
			window.chat.sendPrivateMessage(message, receiverUsername);
		}
		$this.val("");
	}
	return false;
})
$body.on("click", ".send-button", function() {
	var $this = $(this),
		$parent = $this.parent(),
		$parent2 = $parent.parent(),
		$input = $parent.find(".input-message"),
		message = $input.val(),
		receiverUsername = $(".member.active").data("href");
	
	if($parent.hasClass('chatroom')) {
		window.chat.sendMessage(message);			
	} else {
		window.chat.sendPrivateMessage(message, receiverUsername);
	}
	$input.val('');
	return false;
});