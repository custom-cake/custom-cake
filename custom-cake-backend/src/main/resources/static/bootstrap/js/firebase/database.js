
import {
    getDatabase,
    ref,
    set,
    get,
    child,
    onValue,
} from "https://www.gstatic.com/firebasejs/9.1.3/firebase-database.js";

// function writeUserData(user) {
//     const db = ref(database,"Users/")
//         .child("USER-"+user.userId)
//         .set({
//             name: user.name,
//             nickname: user.nickname,
//             phone: user.phone
//         })
// }
const db = getDatabase();

window.getOperatorData = function() {
    // const operatorsRef = child(dbRef, '/Operators/OPERATOR-1');
    // onValue() : 경로의 데이터를 읽고 변경사항을 수신 대기할 수 있음.
    onValue(ref(db,'Operators'), (snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val()["OPERATOR-1"]);
            console.log(snapshot.val()["OPERATOR-2"]);
        } else {
            console.log("No data available");
        }
    }, {
        // 없으면 계속 listening 하고 있음
        onlyOnce: true
    });

    // get(operatorsRef).then((snapshot) => {
    //     if (snapshot.exists()) {
    //         console.log(snapshot.val());
    //     } else {
    //         console.log("No data available");
    //     }
    // }).catch((error) => {
    //     console.error(error);
    // });
}


window.getUserData = function() {
    get(ref(db), 'Users').then((snapshot) => {
        if (snapshot.exists()) {
            console.log(snapshot.val());
        } else {
            console.log("No data available");
        }
    }).catch((error) => {
        console.error(error);
    });
}