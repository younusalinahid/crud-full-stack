import React, {Component} from "react";
import {Link, useParams} from "react-router-dom"; // Added useParams
import HelloWorldService from "../../api/todo/HelloWorldService";

class WelcomeComponent extends Component {

    constructor(props) {
        super(props);
        this.retrieveWelcomeMessage = this.retrieveWelcomeMessage.bind(this)
        this.state = {
            welcomeMessage: ''
        };
        this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this)
        this.handleError = this.handleError.bind(this)
    }

    render() {
        const { name } = this.props.params; // Adjusted to extract name from params
        return (
            <>
                <h1>Welcome!</h1>
                <div className="container">
                    Welcome {name}. {/* Adjusted to use name */}
                    You can manage your todos <Link to="/todos">here</Link>.
                </div>
                <div className="container">
                    Click here to get a customized welcome message.
                    <button onClick={this.retrieveWelcomeMessage}
                            className="btn btn-success">Get Welcome message</button>
                </div>
                <div className="container">
                    {this.state.welcomeMessage}
                </div>
            </>
        );
    }

    retrieveWelcomeMessage() {
        HelloWorldService.executeHelloWorldPathVariableService(this.props.params.name) // Adjusted to use this.props.params.name
            .then(response => this.handleSuccessfulResponse(response))
            .catch(error => this.handleError(error))
    }

    handleSuccessfulResponse(response) {
        console.log(response)
        this.setState({welcomeMessage: response.data.message})
    }

    handleError(error) {
        let errorMessage = '';
        if (error.message) {
            errorMessage += error.message
        }

        if (error.response && error.response.data) {
            errorMessage += ' ' + error.response.data.message // Added space for better readability
        }
        this.setState({welcomeMessage: errorMessage})
    }

}

// Added a higher-order component to pass params
const WelcomeComponentWithParams = (props) => {
    const params = useParams();
    return <WelcomeComponent {...props} params={params} />;
};

export default WelcomeComponentWithParams;
