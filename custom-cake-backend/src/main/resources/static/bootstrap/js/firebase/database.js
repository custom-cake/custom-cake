import "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.13.1/underscore-min.js";
import {
    ref,
    get,
    push, update,
    onValue, query,
    limitToLast, orderByChild, child,
    serverTimestamp
} from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";
import {database} from "./init.js";


// 전역변수
let currRoomId = '';
let currOperatorId = 0;

function clearTextarea() {
    $('div.input-div textarea').val('');
}

// TODO storeId global variable ?
window.init = function(operatorId) {
    const $divChat = $('div.chat');
    $divChat.animate({
        scrollTop : $divChat.scrollHeight
    }, 100);

    // 채팅 페이지 onload
    // enter 키 이벤트
    $('#input-message').on('keydown', function(e){
        if(e.keyCode === 13 && !e.shiftKey) {
            e.preventDefault();
            const message = $(this).val();
            currOperatorId = operatorId;
            // 메시지 전송
            sendMessage(message);
            // 입력창 clear
            clearTextarea();
        }
    });

}

window.getOperatorData = function () {
    // onValue() : 경로의 데이터를 읽고 변경사항을 수신 대기할 수 있음.
    onValue(ref(database, 'Operators'), (snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val()["OPERATOR-1"]);
            return snapshot.val()["OPERATOR-1"];
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
 */
window.loadOperatorChatRoomList = function (operatorId, chatStatus) {

    // MemberRooms : Member 별 채팅방 리스트
    const chatRoomRef = query(
        ref(database, `MemberRooms/OPERATOR-${operatorId}`),
        // queryConstraints
        orderByChild('timestamp', 'desc')
    );

    onValue(chatRoomRef, (snapshot) => {
        if (snapshot.exists()) {
            console.log("채팅방 리스트",snapshot.val());
            getChatRoomList(snapshot, chatStatus);
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
window.getChatRoomList = function (snapshot, chatStatus) {
    const chatroomTemplate = document.getElementById('templateChatRoomList').innerHTML;
    let arrChatRoomList = [];
    let cbDisplayChatRoomList = function (data) {
        const chatRoomId = data.key;
        const chatRoom = data.val();
        if (chatRoomId !== null && chatRoom.chatStatus === chatStatus) {
            arrChatRoomList.push(_.template(chatroomTemplate)({
                targetChatRoomUid: chatRoomId,
                lastMessage: chatRoom.lastMessage,
                roomUserName: chatRoom.roomUserName,
                roomUserID: chatRoom.roomUserID,
                timestamp: timestampToTime(chatRoom.timestamp)
            }));
        }
    }
    snapshot.forEach((data) => {
        cbDisplayChatRoomList(data)
    });
    const ulChatRoomList = document.getElementById('ulChatRoomList');
    ulChatRoomList.innerHTML = arrChatRoomList.reverse().join('');

    /** 채팅방 click event 적용 */
    let arrLiList = ulChatRoomList.getElementsByTagName('tr');
    for (let i = 0; i < arrLiList.length; i++) {
        arrLiList[i].addEventListener('click', onChatRoomClick.bind(this));
    }
}


/**
 * 채팅방 클릭 시 이벤트
 * */
window.onChatRoomClick = function (event) {
    const roomId = event.currentTarget.getAttribute('data-targetChatRoomUid');
    const roomUserName = event.currentTarget.getAttribute('data-roomUserName');
    const roomTitle = roomUserName + '님';

    openChatRoom(roomId, roomTitle);
}

/**
 * 채팅방 오픈 + message load
 */
window.openChatRoom = function (roomId, roomTitle) {
    currRoomId = roomId;
    const spTitle = document.getElementById('spTitle');
    if (roomTitle) {
        spTitle.innerHTML = roomTitle;
    }
    loadMessageList(roomId);  // message load
}

/**
 * 채팅방 message load
 */
window.loadMessageList = function (roomId) {
    const loadMessage = function (roomId, snapshot) {
        // message 화면 리셋
        ulMessageList.innerHTML = ''
        let cbDisplayMessages = function (data) {
            const val = data.val();
            // message rendering
            if (val.senderType === 'OPERATOR') {
                appendMessageTag("right", val.sender, val.message);
            } else {
                appendMessageTag("left", val.sender, val.message);
            }
        }
        snapshot.forEach((data) => {
            cbDisplayMessages(data)
        });
    };

    const appendMessageTag = function (LR_className, senderName, message) {
        const chatLi = createMessageTag(LR_className, senderName, message);

        $('div.chat:not(.format) ul').append(chatLi);

        // 스크롤바 아래 고정
        $('#chat').clientHeight = 0;
    };

    const createMessageTag = function (LR_className, senderName, message) {
        // 형식 가져오기
        let chatLi = $('div.chat.format ul li').clone();

        // 값 채우기
        chatLi.addClass(LR_className);
        chatLi.find('.sender span').text(senderName);
        chatLi.find('.message span').text(message);

        return chatLi;
    };

    // limitToLast 로 최근 50개 메시지만 가져오기
    const ulMessageList = document.getElementById('ulMessageList');
    const messageQuery = query(
        ref(database, `Messages/${roomId}`),
        // queryConstraints
        orderByChild('timestamp', 'desc'),
        limitToLast(50)
    );
    onValue(messageQuery, (snapshot) => {
        if (snapshot.exists()) {
            if (roomId) {
                loadMessage(roomId, snapshot);
            }
        } else {
            console.log("No data available.");
        }
    }, {
        onlyOnce: false
    });
}


/**
 * timestamp 형태의 시간을 YY/MM/DD HH:mm:ss 로 변경
 * @param timestamp
 */
const timestampToTime = function (timestamp) {
    const date = new Date(timestamp),
        year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        hour = date.getHours(),
        minute = date.getMinutes(),
        week = ['일', '월', '화', '수', '목', '금', '토'];

    let convertDate = year + "년 " + month + "월 " + day + "일 (" + week[date.getDay()] + ") ";
    let convertHour = "";
    const pad = (n) => n > 9 ? "" + n : "0" + n;  /** 10미만 숫자 앞에 0 붙이기 */
    if (hour < 12) {
        convertHour = "오전 " + pad(hour) + ":" + pad(minute);
    } else if (hour === 12) {
        convertHour = "오후 " + pad(hour) + ":" + pad(minute);
    } else {
        convertHour = "오후 " + pad(hour - 12) + ":" + pad(minute);
    }

    return convertDate + convertHour;
}

/**
 * 채팅 전송 버튼 클릭 시, firebase Messages 저장, MemberRooms 마지막 메시지 업데이트
 *
 * @param message
 */
window.sendMessage = function (message) {

    /**
     *  메세지에 태그 입력시 변경하기 (태그 입력 방지)
     */
    const convertMessage = function (html) {
        let tmp = document.createElement("DIV");
        tmp.innerHTML = html;
        return tmp.textContent || tmp.innerText || "";
    }

    // 이미 사용자가 메시지를 전송했으므로 메시지를 처음 입력하는 경우 없음 !
    // RoomMembers 업데이트 할 필요 X
    if (currOperatorId !== 0 && currOperatorId !== undefined && currRoomId !== '') {
        let multiUpdates = {};
        const convertMsg = convertMessage(message);
        // Get a key for a new Post.
        // const newPostKey = push(child(ref(db), 'posts')).key;
        const messageRefKey = push(child(ref(database), `Messages/${currRoomId}`)).key; // 메세지 키값 구하기
        const timestamp = serverTimestamp();  // 서버시간 등록하기

        // 네 안녕하세요~ 주문서 작성 부탁드릴게요!
        console.log("운영자 id=",currOperatorId);  // 없음
        console.log("채팅방 id=",currRoomId);
        get(ref(database, `MemberRooms/OPERATOR-${currOperatorId}/${currRoomId}`)).then((snapshot) => {
            if (snapshot.exists()) {
                const val = snapshot.val();
                console.log("채팅방 데이터", val);

                // Messages 저장
                multiUpdates[`Messages/${currRoomId}/${messageRefKey}`] = {
                    memberUid: `OPERATOR-${currOperatorId}`,
                    sender: val.roomOperatorName,
                    senderType: "OPERATOR",
                    message: convertMsg,
                    timestamp: timestamp
                    // profileImg: user.photoURL ? user.photoURL : '',
                }

                // MemberRooms 저장
                multiUpdates[`MemberRooms/OPERATOR-${currOperatorId}/${currRoomId}`] = {
                    chatStatus: "IN_PROGRESS",  // 진행중
                    lastMessage: convertMsg, // 태그 입력 방지
                    roomOperatorId: val.roomOperatorId,
                    roomOperatorName: val.roomOperatorName,
                    roomUserId: val.roomUserId,
                    roomUserName: val.roomUserName,
                    timestamp: timestamp
                    // profileImg: user.photoURL ? user.photoURL : '',
                }

                // Messages, MemberRooms 업데이트
                update(ref(database), multiUpdates);
            } else {
                console.log("채팅방 데이터 없음");
            }
        }).catch((error) => {
            console.error(error);
        });
    }

}

