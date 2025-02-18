import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

const API_BASE_URL = process.env.REACT_APP_BASE_URL;

const Cart = () => {
  const { cartId } = useParams();
  const navigate = useNavigate(); 
  const [cart, setCart] = useState(null);
  const [discountCode, setDiscountCode] = useState("");

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/cart/${cartId}`)
      .then((response) => {
        setCart(response.data);
      })
      .catch((error) => console.error("Error fetching cart:", error));
  }, [cartId]);

  const handleCheckout = () => {
    const checkoutUrl = discountCode
      ? `${API_BASE_URL}/orders/checkout/${cartId}?discountCode=${discountCode}`
      : `${API_BASE_URL}/orders/checkout/${cartId}`;

    axios
      .post(checkoutUrl)
      .then((response) => {
        navigate(`/order-confirmation`, { state: response.data });
      })
      .catch((error) => console.error("Checkout failed:", error));
  };

  if (!cart) return <h2 className="text-center mt-5">Loading Cart...</h2>;

  return (
    <div className="container mt-5">
      <h2 className="text-center text-primary fw-bold">Your Cart</h2>

      <div className="row justify-content-center">
        <div className="col-md-8">
          <ul className="list-group shadow-sm">
            {cart.cartItems.map((cartItem) => (
              <li key={cartItem.id} className="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <h5 className="fw-bold">{cartItem.item.name}</h5>
                  <p className="text-muted">Rs. {cartItem.item.price.toFixed(2)} x {cartItem.quantity}</p>
                </div>
                <h6 className="fw-bold text-success">Rs. {(cartItem.item.price * cartItem.quantity).toFixed(2)}</h6>
              </li>
            ))}
          </ul>

          <div className="mt-4 p-3 border rounded shadow-sm">
            <h4 className="fw-bold">Order Summary</h4>
            <div className="d-flex justify-content-between">
              <span className="fw-bold">Total:</span>
              <span className="fw-bold">Rs. {cart.totalPrice.toFixed(2)}</span>
            </div>
          </div>

          <div className="mt-3 d-flex">
            <input
              type="text"
              className="form-control me-2"
              placeholder="Enter discount code"
              value={discountCode}
              onChange={(e) => setDiscountCode(e.target.value)}
            />
          </div>

          <button className="btn btn-success fw-bold w-100 mt-4" onClick={handleCheckout}>
            Checkout
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
