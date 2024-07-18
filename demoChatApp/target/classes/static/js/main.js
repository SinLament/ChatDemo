const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/ws' // Thay thế localhost và cổng nếu cần
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);

    // Đăng ký nhận tin nhắn từ /topic/public
    stompClient.subscribe('/topic/public', (message) => {
        showMessage(JSON.parse(message.body));
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.activate();

function sendMessage(messageContent) {
    stompClient.publish({
        destination: '/app/chat.sendMessage', // Gửi đến endpoint này
        body: JSON.stringify({
            sender: 'your-username', // Thay thế bằng tên người dùng thực tế
            type: 'CHAT',  // Loại tin nhắn (có thể tùy chỉnh)
            content: messageContent
        })
    });
}

function createGroupChat(chatName) {
    stompClient.publish({
        destination: '/app/chat/createGroupChat',
        body: JSON.stringify({
            content: chatName
        })
    });
}

function createPrivateChat() {
    stompClient.publish({
        destination: '/app/chat/createPrivateChat',
        body: JSON.stringify({})
    });
}

function showMessage(message) {
    // Cập nhật giao diện người dùng để hiển thị tin nhắn mới
    const messageElement = document.createElement('div');
    messageElement.textContent = `${message.sender}: ${message.content}`;
    document.getElementById('messageArea').appendChild(messageElement);
}
