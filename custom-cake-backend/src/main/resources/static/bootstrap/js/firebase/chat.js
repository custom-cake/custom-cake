
// <!-- firebase -->
import { getMessaging, onMessage } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-messaging.js";
import { database, FirebaseChat } from "./init.js";
import { ref } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";

FirebaseChat.prototype.init = function() {
    // this.ulChatRoomList = document.getElementById('ulChatRoomList');
}

FirebaseChat.prototype.setLogin = function(user){
    //...생략
    this.loadChatRoomList();
    this.loadOperatorList();
    this.tabMessageList = document.getElementById('tabMessageList');
    this.aBackBtn = document.getElementById('aBackBtn');
    this.aInvite = document.getElementById('aInvite');
}

/**
 * 채팅방 리스트 로딩
 * @param operatorId
 * @param chatStatus
 */
FirebaseChat.prototype.loadChatRoomList = function(operatorId, chatStatus) {
    this.chatroomTemplate = document.getElementById('templateChatRoomList').innerHTML;
    // Database ref 에 대해 off() method를 호출하면 callback이 삭제된다.
    let memberRoomsRef = ref(database,`MemberRooms/OPERATOR-${operatorId}`);
    memberRoomsRef.off();
    this.memberRoomsRef.orderByChild("timestamp").once('value', this.getChatRoomList.bind(this))
}

/**
 * loadChatRoomList 에서 데이터를 받아 왔을 때
 */
FirebaseChat.prototype.getChatRoomList  =  function(snapshot) {
    let chatRoomList = '';
    let cbDisplayChatRoomList = function(data){
        const val = data.val();
        if(data.key !== this.auth.currentUser.uid){
            chatRoomList += _.template(this.chatroomTemplate)({
                targetChatRoomUid: data.key,
                lastMessage: val.lastMessage,
                roomOperatorName: val.roomOperatorName,
                roomUserName : val.roomUserName,
                timestamp: val.timestamp
            });
        }
    }
    snapshot.forEach(cbDisplayChatRoomList.bind(this));
    // this.ulChatRoomList.innerHTML = chatRoomList;
}

// <script type="text/template" id="templateUserList">
//     <li id="li<%=targetUserUid %>" data-targetUserUid="<%=targetUserUid %>" data-username="<%=userName %>" class="collection-item avatar list">
//         <img src="<%=profileImg ? profileImg : 'img/noprofile.png'  %>" alt="" class="circle">
//             <span class="title"><%=userName %></span>
//             <span class="small material-icons right hiddendiv done">done</span>
//             <span class="small material-icons right hiddendiv mood yellow-text">mood</span>
//     </li>
// </script>

/**
 * 채팅방 멤버 데이터 로딩
 * @param chatStatus
 * @param chatRoomId
 */
FirebaseChat.prototype.loadChatMembers = function(chatStatus, chatRoomId) {

}

/**
 * 채팅 메시지 전송
 * @param chatRoomId
 * @param data
 */
export function sendChat(chatRoomId, data) {
    data.storeName = String;
    let msg = "";

    let multiUpdates = {};
    const messageRefKey = this.messagesRef.push().key;

    // first message

    // Message 저장
    multiUpdates[`Messages/${chatRoomId}/${messageRefKey}`] = {
        message: data.message,
        timestamp: data.timestamp,
        memberUid: data.uid,
        senderType: "OPERATOR",
        senderName: data.storeName  // "레이네 케이크"
    }

    // MemberRoom
}

/**
 * 메시지에 tag 입력 시, 변경
 * @param html
 * @returns {string|string}
 */
function convertMsg(html) {
    const tmp = document.createElement("DIV");
    tmp.innerHTML = html;
    return tmp.textContent || tmp.innerHTML || "";
}