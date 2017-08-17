const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {orders: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/orders'}).done(response => {
            this.setState({orders: response.entity._embedded.orders});
        });
    }

    render() {
        return (
            <OrderList orders={this.state.orders}/>
        )
    }
}

class OrderList extends React.Component {
    render() {
        var orders = this.props.orders.map(order =>
            <Order key={order._links.self.href} order={order}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Number</th>
                    <th>Amount</th>
                </tr>
                {orders}
                </tbody>
            </table>
        )
    }
}

class Order extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.order.number}</td>
                <td>{this.props.order.amount}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
