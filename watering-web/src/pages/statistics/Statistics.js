import {Line, Bar, Doughnut, HorizontalBar, Scatter} from 'react-chartjs-2';
import React from 'react';
import PropTypes from 'prop-types';

class HumidityLine extends React.Component {

    componentDidMount() {
        this.props.loadGraphData(); // Вызываем загрузку
    }

    render() {
        const data = this.props.statData;
        return (
            <div>
                <h2>Статистика по влажности</h2>
                <Line data={data} />
                {/*<Bar data={data}  />*/}
            </div>
        );
    };
}

HumidityLine.propTypes = {
    data: PropTypes.object.isRequired
};

export default HumidityLine;