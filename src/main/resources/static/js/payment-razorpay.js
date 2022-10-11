var $paymentStatusWindow = $("#paymentStatusWindow");

// checkout payment using ajax
function startPayment(formData) {
	console.log("payment started... ", formData);
	
	if(window.hasOwnProperty("RazorPay")) {
		alert("Unable to initilise online payment, Please check network connection.")
		return;
	}
	
	if(!formData && !formData.amount && formData.amount=="") {
		return;
	} else {
//		formData.amount = formData.amount*100;
	}

	// request server to create order -- jquery
//	var reqData = {
//		address_id: formData["address_id"],
//		"payment-method": formData['payment-method'],
//		"message": formData['message'],
//		amount: amount,
//		_csrf: formData[name='_csrf']
//	};

	var reqData = JSON.stringify(formData);

	$.ajax({
		url: "/cart/order",
		data: reqData,
		contentType: "application/json; charset=utf-8",
		type: 'POST',
		dataType: "json",
		beforeSend: function(){
	        $('#loading').show();
	    }, success: function(res) {
			console.log(res);
			createRazorPaymentWindow(res);
		}, error: function(error) {
			console.error(error);
			alert("Something went wrong");
		}, complete: function(){
	        $('#loading').hide();
	    }
	});
};

/** 
 *	res = {amount, razor_order_id, username, email, phone}
 */
function createRazorPaymentWindow(res) {
	// Open payment form after order is created on razorpay server
	var options = {
		key: "rzp_test_nLIXAPQblFuAPV",
		amount: res.amount,
		currency: "INR",
		name: "UIGO Shopping Cart Payment",
		description: "Cart Purchase",
		image: "/images/logo-sm.png",
		order_id: res.id,
		handler: function(response) {
			console.log("Payment Success:",response);
			updatePaymentStatus({
				razorOrderId:response.razorpay_order_id,
				razorPaymentId:response.razorpay_payment_id,
				status:"PAID"
			});
		},
		// auto filling razorpay form
		prefill: {
			name: res.username,
			email: res.email,
			"contact": res.phone
		},
		notes: {
			address: "UIGO Shopping Site"
		},
		theme: {
			color: "#003380"
		}
	};
	// Initiate Payment
	var rzp = new Razorpay(options);

	rzp.on("payment.fail", function(r) {
		console.log("Payment Failed:", r.error);
		updatePaymentStatus({
			razorOrderId:response.razorpay_order_id,
			razorPaymentId:response.razorpay_payment_id,
			status:"FAILED"
		});
	});

	rzp.open();
}

function updatePaymentStatus(payment) {
	$.post({
		url:"/cart/payment/update",
		method:"POST",
		data:payment,
		dataType:"json", 
		beforeSend: function(){
	        $('#loading').show();
	    }, success:function(data) {
			console.log("payment-status", data);
			$paymentStatusWindow.data("status", data.status);
			var $modalBody = $paymentStatusWindow.find(".modal-body");
			$modalBody.find(".icon").hide();
			if(data.status=="FAILED") {				
				$modalBody.find(".icon.failure").show();
				$modalBody.find(".message").html("YOUR PAYMENT UNSUCCSESSFUL!!!");
			} else if(data.status=="PAID") {
				$modalBody.find(".icon.success").show();
				$modalBody.find(".message").html("YOUR PAYMENT IS SUCCESSFUL!!!, ");
			}
			$paymentStatusWindow.data("data", data);
			$paymentStatusWindow.modal();
		},
		error: function(err) {
			console.log("payment-error", status);
			var $modalBody = $paymentStatusWindow.find(".modal-body");
			$modalBody.find(".icon").hide();
			$modalBody.find(".message").html("Your payment is successful, but unable to save to our server. We will update information manually if something didn't matched.'");
			$paymentStatusWindow.modal();
			
		}, complete: function(){
	        $('#loading').hide();
	    }
	});
};

$paymentStatusWindow.find(".ok-btn").on("click", function(){
	var data = $paymentStatusWindow.data("data");
	var url = "/orderstatus/"+data.orderId+"/"+data.paymentId;
	window.location.href = url;
});
