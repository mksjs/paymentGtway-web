import React, {Component} from "react";
import FormControl from "@material-ui/core/FormControl";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import config from "../../paysafe.json";
import {validateInput} from "./FormValidation";
import HelpCheckout from "./HelpCheckout";


class Checkout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            customerInfo: {
		email: "",
                firstName: "",
                lastName: "",
		phone: "",
            },
	    billingAddress: {
                street: "",
                street2: "jkfeihiuhfi",
                city: "",
                zip: "",
                country: "IN",
                state: "BH",
            },
            paysafeCustomerId: "1234567810",
            isPaymentProcessing: false,
	    amountObj: {
	   	amount:10,
	    },
            error: "",
        };

    }

   
    //init the paysafe sdk
    componentDidMount() {
        //includ Paysafe SDK
        const script = document.createElement("script");
        script.src = config.paysafeCheckoutSDKSource;
        script.async = true;
        document.body.appendChild(script);
    }

    paysafeCheckOut = async (amount) => {
        this.setState({
            isPaymentProcessing: true,
        });
        const helpCheckout = new HelpCheckout();
        //set up payment
        const setupInput = await helpCheckout.prepareSetupInput(
	    this.state.billingAddress,
            this.state.customerInfo,
	    amount,
            this.state.paysafeCustomerId
        );
        //paysafe checkout
        window.paysafe.checkout.setup(
            "cHVibGljLTc3NTE6Qi1xYTItMC01ZjAzMWNiZS0wLTMwMmQwMjE1MDA4OTBlZjI2MjI5NjU2M2FjY2QxY2I0YWFiNzkwMzIzZDJmZDU3MGQzMDIxNDUxMGJjZGFjZGFhNGYwM2Y1OTQ3N2VlZjEzZjJhZjVhZDEzZTMwNDQ=",
            setupInput,
            helpCheckout.callBackFunction,
            helpCheckout.closeCallBack
        );
        try {
            const status = await helpCheckout.paymentStatus;
            if (status.status === "success") {
                console.log(status);
            }
        } catch (err) {
            this.setState({
                isPaymentProcessing: false,
            });
        }
    };

    //checkout using with paysafe
    handleCheckout = async (event) => {
        event.preventDefault();
        let validationError = validateInput(
            this.state.customerInfo,
	    this.state.billingAddress,
	    this.state.amountObj.amount
        );
        if (!validationError) {
            this.setState({
                error: "",
            });
            await this.paysafeCheckOut(parseInt(this.state.amountObj.amount)*100);
        } else {
            this.setState({
                error: validationError,
            });
        }
    };


    onCustomerDetailsInputChange = (event) => {
        const changedInput = {...this.state.customerInfo};
        changedInput[event.target.id] = event.target.value;
        this.setState({
            customerInfo: changedInput,
        });
    };

    onBillingAddressInputChange = (event) => {
        const changedInput = {...this.state.billingAddress};
        changedInput[event.target.id] = event.target.value;
        this.setState({
            billingAddress: changedInput,
        });
    };

    onAmountInputChange = (event) => {
	const changedInput = {...this.state.amountObj};
	changedInput[event.target.id] = event.target.value;
	this.setState({
	    amountObj: changedInput,
	});
    }

    render() {
        return (
            <center>
                <h1 style={{color: "#4a54f1"}}>ARIES GLOBAL ASSIGNMET</h1>
                <form style={{
                    fontSize: "14px",
                    color: "#4a54f1",
                    textAlign: "center",
                    paddingTop: "50px",
                }} onSubmit={(event) => this.handleCheckout(event)}>
		    <FormControl style={{width: "50%"}}>
                        <InputLabel htmlFor="email">Email</InputLabel>
                        <Input
                            value={this.state.customerInfo.email}
                            onChange={this.onCustomerDetailsInputChange}
                            id="email"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="firstName">First Name</InputLabel>
                        <Input
                            value={this.state.customerInfo.firstName}
                            onChange={this.onCustomerDetailsInputChange}
                            id="firstName"
                        />
                    </FormControl>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="lastName">Last Name</InputLabel>
                        <Input
                            value={this.state.customerInfo.lastName}
                            onChange={this.onCustomerDetailsInputChange}
                            id="lastName"
                        />
                    </FormControl>
 		    <br/>    
		    <FormControl style={{width: "50%"}}>
                        <InputLabel htmlFor="street">Address </InputLabel>
                        <Input
                            value={this.state.billingAddress.street}
                            onChange={this.onBillingAddressInputChange}
                            id="street"
                        />
                    </FormControl>
                    <br/>
                    
                    <FormControl style={{width: "50%"}}>
                        <InputLabel htmlFor="phone">Phone Number</InputLabel>
                        <Input
                            value={this.state.customerInfo.phone}
                            onChange={this.onCustomerDetailsInputChange}
                            id="phone"
                        />
                    </FormControl>
                    <br/>
		    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="city">City</InputLabel>
                        <Input
                            value={this.state.billingAddress.city}
                            onChange={this.onBillingAddressInputChange}
                            id="city"
                        />
                    </FormControl>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="zip">Zip Code</InputLabel>
                        <Input
                            value={this.state.billingAddress.zip}
                            onChange={this.onBillingAddressInputChange}
                            id="zip"
                        />
                    </FormControl>
		    <br/>
                    <FormControl style={{width: "50%"}}>
                        <InputLabel htmlFor="Amount">Amount</InputLabel>
                        <Input
                            value={this.state.amountObj.amount}
                            onChange={this.onAmountInputChange}
                            id="amount"
                        />
                    </FormControl>
                    <br/>
                    <br/>
                    {this.state.error && (
                        <div className="has-text-danger">{this.state.error}</div>
                    )}
                    <br/>
                    <button
                        type="submit"
                        className="button is-success is-medium is-outlined"
                        disabled={this.state.isPaymentProcessing}
                    >
                        Proceed To Payment
                    </button>
                </form>
            </center>
        );
    }
}

export default Checkout
