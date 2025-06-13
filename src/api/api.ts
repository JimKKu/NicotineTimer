export async function getData() {
    const response = await fetch('/api/iTimer/smoke/count', {
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
