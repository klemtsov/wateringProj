import * as types from '../constants/StatTypes'

const initialState = {
    //statData: {
        labels: [],
        datasets: [
            {
                label: 'Влажность',
                //fill: false,
                lineTension: 0.1,
                backgroundColor: 'rgba(75,192,192,0.4)',
                borderColor: 'rgba(75,192,192,1)',
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                pointBorderColor: 'rgba(75,192,192,1)',
                pointBackgroundColor: '#fff',
                pointBorderWidth: 1,
                pointHoverRadius: 10,
                pointHoverBackgroundColor: 'rgba(75,192,192,1)',
                pointHoverBorderColor: 'rgba(220,220,220,1)',
                pointHoverBorderWidth: 1,
                pointRadius: 1,
                pointHitRadius: 10,
                data: []

            }
        ]
    //}

};


export default function statReducer(state = initialState, action) {
    const {payload} = action;
    switch (action.type) {
        case types.STAT_GET_SUCCESS: {
            return {statData: payload};
        }

        case types.STAT_GET_ERROR: {
            console.error("Ошибка при получении данных графика", action.payload);
            return state;
        }
        default:
            return state;
    }
}