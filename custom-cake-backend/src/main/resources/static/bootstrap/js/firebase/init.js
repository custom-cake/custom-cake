
// <!-- firebase -->
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-app.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-auth.js";
import { getDatabase, ref, set, update, Database, runTransaction } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";
import { onChildAdded, onChildChanged, onChildRemoved } from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";

// Initialize Firebase
let firebaseConfig = {
    apiKey: "YOUR_API_KEY",
    authDomain: "YOUR_AUTH_DOMAIN",
    databaseURL: "YOUR_DATABASE_URL",
    projectId: "YOUR_PROJECT_ID",
    storageBucket: "YOUR_STORAGE_BUCKET",
    messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
    appId: "YOUR_APP_ID"
};

// Initialize App and Realtime Database
const app = initializeApp(firebaseConfig);
const database = getDatabase(app);
const auth = getAuth();
const memberId = auth.currentUser.uid

const chatRef = ref(database,"chats");
// chatRef.on("child_added", function(data) {
//     // display incoming chat messages in the chat-messages list
//     const message = data.val();
//     const li = document.createElement("li");
//     li.innerText = message.sender + ": " + message.text;
//     document.getElementById("chat-messages").appendChild(li);
// });


export function sendChat(data) {
    data.storeName = String;
    return chatRef.push({
        message: data.message,
        timestamp: data.timestamp,
        memberUid: data.uid,
        senderType: "OPERATOR",
        senderName: data.storeName  // "레이네 케이크"
    })
}

function writeUserData(userId, name, nickname) {
    const db = getDatabase()
    set(ref(db, 'users/' + userId),
        {
            username: name,
            nickname: nickname,
        })
}