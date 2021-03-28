export function validateInput(customerInfo, billingAddress, amount) {
    let validationError = customerInfoValidation(customerInfo);
    if (!validationError) {
        validationError = billingAddressValidation(billingAddress);
    }
    if (!validationError) {
	validationError = amountValidation(amount);
    }
    return validationError;
}

function billingAddressValidation(billingAddress) {
    if (!billingAddress.street) {
        return "Address can not be empty";
    } else if (!billingAddress.city) {
        return "City can not be empty";
    } else if (!billingAddress.zip) {
        return "Zip Code can not be empty";
    } else if (Object.is(Number(billingAddress.zip), NaN)) {
        return "Invalid Zip Code";
    } 
    return "";
}

function customerInfoValidation(customerInfo) {
     if (!customerInfo.email) {
        return "Email can not be empty";
    } else if (!isValidEmail(customerInfo.email)) {
        return "Invalid email entered";
    }	else if (!customerInfo.firstName) {
        return "First Name can not be empty";
    } else if (!customerInfo.lastName) {
        return "Last Name can not be empty";
    } else if (!customerInfo.phone) {
        return "Phone Number can not be empty";
    } 
	
    return "";
}

function amountValidation(amount) {
	if (!amount) {
		return "Amount can not be empty";
	} else if (Object.is(Number(amount), NaN)) {
		return "Amount should be in number";
	}
}
function isValidEmail(email) {
    const regularExpression = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regularExpression.test(String(email).toLowerCase());
}
