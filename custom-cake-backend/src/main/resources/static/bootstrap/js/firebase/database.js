import "https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.13.1/underscore-min.js";
import {
    ref,
    get, set,
    child,
    onValue, query,
    limitToLast, orderByChild,
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

// 전역변수
let isOpenRoom = true;
const MAKE_CHAR = '@make@';
const DATETIME_CHAR = '@time@';
const SPLIT_CHAR = '@spl@';

window.getOperatorData = function() {
    // onValue() : 경로의 데이터를 읽고 변경사항을 수신 대기할 수 있음.
    onValue(ref(database,'Operators'), (snapshot) => {
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
 // TODO 채팅 페이지 클릭할 때 onclick event 로 method 등록
 */
window.loadOperatorChatRoomList = function(operatorId, chatStatus) {

    // MemberRooms : Member 별 채팅방 리스트
    const chatRoomRef = query(
        ref(database,`MemberRooms/OPERATOR-${operatorId}`),
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
    snapshot.forEach((data) => { cbDisplayChatRoomList(data) });
    const ulChatRoomList = document.getElementById('ulChatRoomList');
    ulChatRoomList.innerHTML = chatRoomList;

    /** 채팅방 click event 적용 */
    let arrLiList = ulChatRoomList.getElementsByTagName('tr');
    for (let i=0; i < arrLiList.length; i++) {
        arrLiList[i].addEventListener('click', onChatRoomClick.bind(this));
    }
}



/**
 * 채팅방 클릭 시 이벤트
 * */
window.onChatRoomClick = function (event) {
    // let aBackBtn = document.getElementById('aBackBtn');
    // aBackBtn.classList.remove('hiddendiv');

    const roomId = event.currentTarget.getAttribute('data-targetChatRoomUid');
    const roomUserName = event.currentTarget.getAttribute('data-roomUserName');
    const roomTitle = roomUserName+'님';

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
    // TODO limitToLast 로 최근 50개 메시지만 가져오기
    const ulMessageList = document.getElementById('ulMessageList');
    if (roomId) {
        // message 화면 리셋
        ulMessageList.innerHTML = '';
        let messageTemplate = document.getElementById('templateMessageList').innerHTML;
        console.log(roomId)
        let cbDisplayMessages = function(data) {
            let messageHtml = '';
            const val = data.val();
            messageHtml = _.template(messageTemplate)({
                key: data.key,
                memberUid: data.memberUid,
                sender: data.sender,  // == userName
                senderType: data.senderType,
                // profileImg : val.profileImg
                message: data.message,
                timestamp: timestampToTime(data.timestamp)
            });
            ulMessageList.innerHTML += messageHtml;
            ulMessageList.scrollTop = ulMessageList.scrollHeight;
        }

        const messageQuery = query(
            ref(database,`Messages/${roomId}`),
            // queryConstraints
            limitToLast(50)

        );
        onValue(messageQuery, (snapshot) => {
            if (snapshot.exists()) {
                console.log(snapshot.val());
                cbDisplayMessages(snapshot);
            } else {
                console.log("No data available.");
            }
        }, {
            onlyOnce: false
        });
    }
}

/**
 * timestamp 형태의 시간을 YY/MM/DD HH:mm:ss 로 변경
 * @param timestamp
 */
const timestampToTime = function (timestamp) {
    const date = new Date(timestamp),
        year = date.getFullYear(),
        month = date.getMonth()+1,
        day = date.getDate(),
        hour = date.getHours(),
        minute = date.getMinutes(),
        week = ['일', '월', '화', '수', '목', '금', '토'];

    let convertDate = year + "년 "+month+"월 "+ day +"일 ("+ week[date.getDay()] +") ";
    let convertHour = "";
    const pad = (n) =>  n > 9 ? "" + n: "0" + n;  /** 10미만 숫자 앞에 0 붙이기 */
    if (hour < 12) {
        convertHour = "오전 " + pad(hour) +":" + pad(minute);
    } else if (hour === 12) {
        convertHour = "오후 " + pad(hour) +":" + pad(minute);
    } else {
        convertHour = "오후 " + pad(hour - 12) +":" + pad(minute);
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
        sss= vDate.getMilliseconds().toString();

    return yyyy + (MM[1] ? MM : '0' + MM[0]) + (dd[1] ? dd : '0' + dd[0]) + (HH[1] ? HH : '0' + HH[0])
        + (mm[1] ? mm : '0'+ mm[0]) + (ss[1] ? ss : '0' + ss[0]) + sss;
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