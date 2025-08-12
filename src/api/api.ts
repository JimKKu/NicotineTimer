import axios from "axios";

export async function getData() {
    const response = await fetch('/smoke/count', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    return data;
}


export async function fetchLatestDate() {
    const response = await fetch('/timer/latest', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    return data;
}


// 设置时间功能
export async function setDate(date:string,time:string) {
    let datetime = `${date}${time}`
    const response = await fetch(`/timer/set/${datetime}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    return data;
}

// 设置时间功能
// export async function getStartDayForEChart(date:string,time:string) {
//     let datetime = `${date}${time}`
//     const response = await fetch(`/timer/set/${datetime}`, {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//     });
//
//     if (!response.ok) {
//         throw new Error(`HTTP error! status: ${response.status}`);
//     }
//     const data = await response.json();
//     return data;
// }