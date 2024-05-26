import React, {Component} from "react"
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import withNavigation from './WithNavigation.jsx'
import withParams from './WithParams.jsx'
import AuthenticatedRoute from "./AuthenticatedRoute";
import LoginComponent from "./LoginComponent";
import HeaderComponent from "./HeaderComponent";
import ListTodosComponent from "./ListTodosComponent";
import WelcomeComponent from "./WelcomeComponent";
import FooterComponent from "./FooterComponent";
import ErrorComponent from "./ErrorComponent";
import LogoutComponent from "./LogoutComponent";
import TodoComponent from "./TodoComponent";

class TodoApp extends Component {
    render() {
        const LoginComponentWithNavigation = withNavigation(LoginComponent);
        const WelcomeComponentWithParams = withParams(WelcomeComponent);
        const ListTodosComponentWithNavigation = withNavigation(ListTodosComponent)
        const TodoComponentWithParamsAndNavigation = withParams(withNavigation(TodoComponent));
        const HeaderComponentWithNavigation = withNavigation(HeaderComponent);
        return (
            <div className="TodoApp">
                <Router>
                    <HeaderComponentWithNavigation/>
                    <Routes>
                        <Route path="/" element={<LoginComponentWithNavigation />} />

                        <Route path="/login" element={<LoginComponentWithNavigation />} />

                        <Route path="/welcome/:name" element={<AuthenticatedRoute><WelcomeComponentWithParams /></AuthenticatedRoute>} />

                        <Route path="/todos/:id" element={<AuthenticatedRoute><TodoComponentWithParamsAndNavigation /></AuthenticatedRoute>} />

                        <Route path="/todos" element={<AuthenticatedRoute><ListTodosComponentWithNavigation /></AuthenticatedRoute>} />

                        <Route path="/logout" element={<AuthenticatedRoute><LogoutComponent /></AuthenticatedRoute>} />

                        <Route path="*" element={<ErrorComponent />} />
                    </Routes>
                    <FooterComponent/>
                </Router>
            </div>
        )
    }
}

export default TodoApp;