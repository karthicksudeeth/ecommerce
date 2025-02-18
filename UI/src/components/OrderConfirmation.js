import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const OrderConfirmation = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const order = location.state;

  if (!order) {
    return <h2 className="text-center mt-5">No order details found.</h2>;
  }

  return (
    <div className="container mt-5 text-center">
      <h2 className="text-success fw-bold">🎉 Yay! Your Order is Placed 🎉</h2>

      <div className="mt-4 p-4 border rounded shadow-sm">
        <h4 className="fw-bold">Order Details</h4>
        <p><strong>Order ID:</strong> {order.id}</p>
        <p><strong>Total Amount:</strong> Rs. {order.totalAmount.toFixed(2)}</p>
        <p><strong>Discount Applied:</strong> Rs. {order.discountAmount.toFixed(2)}</p>
        <p><strong>Final Amount Paid:</strong> Rs. {order.finalAmount.toFixed(2)}</p>
      </div>

      <button className="btn btn-primary mt-3" onClick={() => navigate("/")}>
        Go to Home
      </button>
    </div>
  );
};

export default OrderConfirmation;
