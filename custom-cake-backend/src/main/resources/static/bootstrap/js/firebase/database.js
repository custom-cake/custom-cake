import "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.13.1/underscore-min.js";
import {
    ref,
    get,
    set,
    onValue,
    child,
    onChildChanged, orderByChild, query
} from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";
import { database } from "./init.js";

// function writeUserData(user) {
//     const db = ref(database,"Users/")
//         .child("USER-"+user.userId)
//         .set({
//             name: user.name,
//             nickname: user.nickname,
//             phone: user.phone
//         })
// }

window.getOperatorData = function() {
    // onValue() : 경로의 데이터를 읽고 변경사항을 수신 대기할 수 있음.
    onValue(ref(database,'Operators'), (snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val()["OPERATOR-1"]);
            // document.getElementById("operatorObject").innerHTML = snapshot.val()["OPERATOR-1"];
            return snapshot.val()["OPERATOR-1"]
        } else {
            console.log("운영자 데이터 없음");
        }
    }, {
        // onlyOnce: false => data 계속 listening
        onlyOnce: true
    });
}

/**
 * 채팅방 리스트 로딩
 * @param operatorId
 * @param chatStatus
 // TODO 채팅 페이지 클릭할 때 onclick event 로 method 등록
 */
window.loadOperatorChatRoomList = function(operatorId, chatStatus) {
    const chatroomTemplate = document.getElementById('templateChatRoomList').innerHTML;

    // MemberRooms : Member 별 채팅방 리스트
    const chatRoomRef = query(
        ref(database,`MemberRooms/OPERATOR-${operatorId}`),
        // queryConstraints
        orderByChild('timestamp')
    );

    onValue(chatRoomRef, (snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val());
            getChatRoomList(chatroomTemplate, snapshot);
        } else {
            console.log("No data available.");
        }
    }, {
        onlyOnce: false
    });
}

/**
 * loadChatRoomList 에서 데이터를 받아 왔을 때
 */
window.getChatRoomList = function (chatroomTemplate, snapshot) {
    let chatRoomList = '';
    let cbDisplayChatRoomList = function (data) {
        const chatRoomId = data.key;
        const chatRoom = data.val();
        if (chatRoomId !== null) {
            chatRoomList += _.template(chatroomTemplate)({
                targetChatRoomUid: chatRoomId,
                lastMessage: chatRoom.lastMessage,
                roomUserName: chatRoom.roomUserName,
                roomUserID: chatRoom.roomUserID,
                timestamp: chatRoom.timestamp
            });
        }
    }
    snapshot.forEach((data) => { cbDisplayChatRoomList(data) });
    document.getElementById('ulChatRoomList').innerHTML = chatRoomList;
}


window.getUserData = function() {
    get(ref(database), 'Users').then((snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val());
        } else {
            console.log("유저 데이터 없음");
        }
    }).catch((error) => {
        console.error(error);
    });
}