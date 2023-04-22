
// firebase
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-app.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-auth.js";
import { getDatabase, ref, set, update, Database, runTransaction } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";
import { onChildAdded, onChildChanged, onChildRemoved } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";

// Initialize Firebase
// let: 변수 업데이트 가능 재선언 불가, const: 업데이트 불가, 재선언 불가
let firebaseConfig = {
    apiKey: "AIzaSyCk93TmaAMTjEACTNAB0CFSXgbHfxUtHLM",
    authDomain: "cau-custom-cake.firebaseapp.com",
    projectId: "cau-custom-cake",
    storageBucket: "cau-custom-cake.appspot.com",
    databaseURL: "https://cau-custom-cake-default-rtdb.asia-southeast1.firebasedatabase.app/",
    messagingSenderId: "387687894137",
    appId: "1:387687894137:web:4aef3e4aff4b401dfcd8a8",
    measurementId: "G-JL51ZWPK6N"
};

// Initialize App and Realtime Database
const app = initializeApp(firebaseConfig);
export const database = getDatabase(app);
// const auth = getAuth();
// const memberId = auth.currentUser.uid

const fcmIdRef = ref(database,"FcmId/");
const messagesRef = ref(database,"Messages/");
// chatRef.on("child_added", function(data) {
//     // display incoming chat messages in the chat-messages list
//     const message = data.val();
//     const li = document.createElement("li");
//     li.innerText = message.sender + ": " + message.text;
//     document.getElementById("chat-messages").appendChild(li);
// });

let FirebaseChat = function (obj) {
    this.database = database;
    this.messagesRef = ref(database,"Messages");
    this.usersRef = ref(database,"Users");
    this.operatorsRef = ref(database,"Operators");
    this.memberRoomsRef = ref(database,"MemberRooms");

    const button = document.getElementById('loadOperatorListBtn');
    button.addEventListener('click', function() {
        console.log("OPERATOR loadOperatorList");
        this.operatorTemplate = document.getElementById('templateOperatorList').innerHTML;
        // Database ref 에 대해 off() method를 호출하면 callback이 삭제된다.
        const operatorsRef = ref(database,'Operators');
        operatorsRef.off();
        operatorsRef.orderByChild("storeName").once('value', this.getOperatorList.bind(this));
    });
};


FirebaseChat.prototype.loadOperatorList = function() {
    console.log("OPERATOR loadOperatorList");
    this.operatorTemplate = document.getElementById('templateOperatorList').innerHTML;
    // Database ref 에 대해 off() method를 호출하면 callback이 삭제된다.
    const operatorsRef = this.database.ref('Operators');
    operatorsRef.off();
    operatorsRef.orderByChild("storeName").once('value', this.getOperatorList.bind(this));
}

FirebaseChat.prototype.getOperatorList = function(snapshot) {;
    let operatorListHtml = '';
    let cbDisplayOperatorList = function(data){
        const val = data.val();
        console.log("OPERATOR UID",data.key);
        operatorListHtml += _.template(this.operatorTemplate)({
            targetOperatorUid: data.key.split("-"),
            storeName: val.storeName,
            email: val.email,
            phone : val.phone,
        });
    }
    snapshot.forEach(cbDisplayOperatorList.bind(this));
    document.getElementById('ulOperatorList').innerHTML = operatorListHtml
    // this.ulChatRoomList.innerHTML = operatorListHtml;
}

export { FirebaseChat };