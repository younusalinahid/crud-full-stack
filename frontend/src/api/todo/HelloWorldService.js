import axios from "axios";

class HelloWorldService {

    executeHelloWorldService() {
        return axios.get('http://localhost:8080/hello-world');
    }

    executeHelloWorldBeanService() {
        return axios.get('http://localhost:8080/hello-world-bean');
    }

    executeHelloWorldPathVariableService(name) {
        return axios.get(`http://localhost:8080/hello-world/path-variable/${name}`, {
            headers: {
                'Authorization': 'Basic ' + btoa('username:password')
            }
        });
    }
}

export default new HelloWorldService();
