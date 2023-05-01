import "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.13.1/underscore-min.js";
import {
    ref,
    get, set,
    child,
    onValue, query,
    limitToLast, orderByChild,
} from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";
import {database} from "./init.js";


// 전역변수
let isOpenRoom = true;
const MAKE_CHAR = '@make@';
const DATETIME_CHAR = '@time@';
const SPLIT_CHAR = '@spl@';

function clearTextarea() {
    $('div.input-div textarea').val('');
}

window.init = function() {

    // enter 키 이벤트
    $('#input-message').on('keydown', function(e){
        if(e.keyCode === 13 && !e.shiftKey) {
            e.preventDefault();
            // 메시지 전송
            sendMessage($(this).val());
            // 입력창 clear
            clearTextarea();
        }
    });
}
window.init();


window.getOperatorData = function () {
    // onValue() : 경로의 데이터를 읽고 변경사항을 수신 대기할 수 있음.
    onValue(ref(database, 'Operators'), (snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val()["OPERATOR-1"]);
            // document.getElementById("operatorObject").innerHTML = snapshot.val()["OPERATOR-1"];
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
            console.log(snapshot.val());
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
    let chatRoomList = '';
    let cbDisplayChatRoomList = function (data) {
        const chatRoomId = data.key;
        const chatRoom = data.val();
        if (chatRoomId !== null && chatRoom.chatStatus === chatStatus) {
            chatRoomList += _.template(chatroomTemplate)({
                targetChatRoomUid: chatRoomId,
                lastMessage: chatRoom.lastMessage,
                roomUserName: chatRoom.roomUserName,
                roomUserID: chatRoom.roomUserID,
                timestamp: timestampToTime(chatRoom.timestamp)
            });
        }
    }
    snapshot.forEach((data) => {
        cbDisplayChatRoomList(data)
    });
    const ulChatRoomList = document.getElementById('ulChatRoomList');
    ulChatRoomList.innerHTML = chatRoomList;

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

    openChatRoom(roomId, roomTitle)
}

/**
 * 채팅방 오픈 + message load
 */
window.openChatRoom = function (roomId, roomTitle) {
    const spTitle = document.getElementById('spTitle');
    // const tabMessageList = document.getElementById('tabMessageList');  // 채팅방 화면 보이기
    isOpenRoom = true;
    if (roomTitle) {
        spTitle.innerHTML = roomTitle;
    }
    loadMessageList(roomId);  // message load
    // tabMessageList.click();
}

/**
 * 채팅방 message load
 */
window.loadMessageList = function (roomId) {
    const loadMessage = function (roomId, snapshot) {
        // message 화면 리셋
        ulMessageList.innerHTML = '';
        let messageTemplate = document.getElementById('templateMessageList').innerHTML;
        let cbDisplayMessages = function (data) {
            let messageHtml = '';
            const val = data.val();
            //
            if (val.senderType === 'OPERATOR') {
                appendMessageTag("right", val.sender, val.message);
            } else {
                appendMessageTag("left", val.sender, val.message);
            }

            // messageHtml = _.template(messageTemplate)({
            //     key: data.key,  // messageId
            //     // profileImg : val.profileImg
            //     memberUid: val.memberUid,
            //     sender: val.sender,  // == userName
            //     senderType: val.senderType,
            //     message: val.message,
            //     timestamp: timestampToTime(val.timestamp)
            // });

            ulMessageList.innerHTML += messageHtml;
            ulMessageList.scrollTop = ulMessageList.scrollHeight;
        }
        snapshot.forEach((data) => {
            cbDisplayMessages(data)
        });
    };

    const appendMessageTag = function (LR_className, senderName, message) {
        const chatLi = createMessageTag(LR_className, senderName, message);

        $('div.chat:not(.format) ul').append(chatLi);

        // 스크롤바 아래 고정
        $('div.chat').scrollTop($('div.chat').prop('scrollHeight'));
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
        limitToLast(50)
    );
    onValue(messageQuery, (snapshot) => {
        if (snapshot.exists()) {
            console.log("Message=", snapshot.val());
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

function yyyyMMddHHmmsss() {
    let vDate = new Date(),
        yyyy = vDate.getFullYear().toString(),
        MM = (vDate.getMonth() + 1).toString(),
        dd = vDate.getDate().toString(),
        HH = vDate.getHours().toString(),
        mm = vDate.getMinutes().toString(),
        ss = vDate.getSeconds().toString(),
        sss = vDate.getMilliseconds().toString();

    return yyyy + (MM[1] ? MM : '0' + MM[0]) + (dd[1] ? dd : '0' + dd[0]) + (HH[1] ? HH : '0' + HH[0])
        + (mm[1] ? mm : '0' + mm[0]) + (ss[1] ? ss : '0' + ss[0]) + sss;
}

window.getUserData = function () {
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

/**
 * 채팅 전송 버튼 클릭 시, firebase Messages 저장, MemberRooms 마지막 메시지 업데이트
 *
 * @param operatorId
 * @param storeName
 * @param userId
 * @param roomId
 * @param message
 */
window.saveMessageData = function (operatorId, storeName, userId, roomId, message) {

    /**
     *  메세지에 태그 입력시 변경하기
     */
    const convertMessage = function (html) {
        let tmp = document.createElement("DIV");
        tmp.innerHTML = html;
        return tmp.textContent || tmp.innerText || "";
    }
    const messagesRef = ref(database, 'Messages');
    const convertMsg = convertMessage(message);
    let messageRefKey = messagesRef.push().key; // 메세지 키값 구하기
    let multiUpdates = {};

    const ulMessageList = document.getElementById('ulMessageList');

    // 이미 사용자가 메시지를 전송했으므로 메시지를 처음 입력하는 경우 없음 !
    // if (ulMessageList.getElementsByTagName('li').length === 0){ // 메세지 처음 입력 하는 경우
    // RoomMembers 업데이트 할 필요 X
    // multiUpdates[`RoomMembers/${roomId}/${userId}`] = true;
    // }

    // Messages 저장
    multiUpdates[`Messages/${roomId}/${messageRefKey}`] = {
        memberUid: `OPERATOR-${operatorId}`,
        sender: storeName,
        senderType: "OPERATOR",
        message: convertMsg, // 태그 입력 방지
        // profileImg: user.photoURL ? user.photoURL : '',
        timestamp: database.ServerValue.TIMESTAMP  //서버시간 등록하기
    }

    messagesRef
        .child(`${roomId}`)
        .set({})


    // MemberRooms 업데이트
    /*
    var roomUserListLength = this.roomUserlist.length;
    if(this.roomUserlist && roomUserListLength > 0){
        for(var i = 0; i < roomUserListLength ; i++){
            multiUpdates['UserRooms/'+ this.roomUserlist[i] +'/'+ this.roomId] = {
                roomId : this.roomId,
                roomUserName : this.roomUserName.join(this.SPLIT_CHAR),
                roomUserlist : this.roomUserlist.join(this.SPLIT_CHAR),
                roomType : roomUserListLength > 2 ? this.MULTI : this.ONE_VS_ONE,
                roomOneVSOneTarget : roomUserListLength == 2 && i == 0 ? this.roomUserlist[1] :  // 1대 1 대화이고 i 값이 0 이면
                    roomUserListLength == 2 && i == 1 ? this.roomUserlist[0]   // 1대 1 대화 이고 i값이 1이면
                        : '', // 나머지
                lastMessage : convertMsg,
                profileImg : user.photoURL ? user.photoURL : '',
                timestamp: firebase.database.ServerValue.TIMESTAMP

            };
        }
    }
    this.database.ref().update(multiUpdates);*/

}

