//возвращаем конфиг запроса
export function getRequestConfig() {
    const ENV = process !== undefined && process.env !== undefined ? process.env.NODE_ENV : "none";
    var config;
    if (ENV === "development") {
        config = {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                'Access-Control-Allow-Origin': "*",
                'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE',
                //'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Authorization'
            }
        };
    } else {
        config =
            {
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }
    }

    return config;

}

export function getApiHost(){
    const ENV = process !== undefined && process.env !== undefined ? process.env.NODE_ENV : "none";
    var config;
    if (ENV === "development") {
        return "http://localhost:8082/";
    } else {
        return "";
    }
}