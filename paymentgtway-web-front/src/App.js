import React from 'react';
import './App.css';
import Checkout from "./components/payment-checkout/Checkout";
import {Route, BrowserRouter as Router} from "react-router-dom";

function App() {

    return (
        <div className="App">
            <Router>
                <Route exact path="/" component={Checkout}/>
            </Router>
        </div>
    );
}

export default App;
