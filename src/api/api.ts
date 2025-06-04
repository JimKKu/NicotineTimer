export async function getData() {
    const response = await fetch('/api/get', {
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