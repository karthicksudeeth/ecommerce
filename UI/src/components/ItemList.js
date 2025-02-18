import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import img1 from "../images/img1.jpg";
import img2 from "../images/img2.jpg";
import img3 from "../images/img3.jpg";
import img4 from "../images/img4.jpg";
import img5 from "../images/img5.jpg";
import img6 from "../images/img6.jpg";

const API_BASE_URL = process.env.REACT_APP_BASE_URL;
const images = [img1, img2, img3, img4, img5, img6];

const ItemList = () => {
  const [items, setItems] = useState([]);
  const [quantities, setQuantities] = useState({});
  const navigate = useNavigate(); 

  useEffect(() => {
    axios.get(`${API_BASE_URL}/items`)
      .then(response => setItems(response.data))
      .catch(error => console.error("Error fetching items:", error));
  }, []);

  const handleCreateCart = () => {
    axios.post(`${API_BASE_URL}/cart/create`)
      .then(response => {
        const cartId = response.data.id;
  
        const cartItems = Object.entries(quantities)
          .filter(([_, quantity]) => quantity > 0)
          .map(([itemId, quantity]) => ({
            itemId: parseInt(itemId), 
            quantity
          }));
  
        if (cartItems.length === 0) {
          navigate(`/cart/${cartId}`);
          return;
        }
  
        axios.post(`${API_BASE_URL}/cart/${cartId}/add`, cartItems)
          .then(() => {
            navigate(`/cart/${cartId}`);
          })
          .catch(error => {
            console.error("Error adding items to cart:", error);
          });
      })
      .catch(error => {
        console.error("Error creating cart:", error);
      });
  };
  

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-end mb-4">
        <button
          className="btn btn-primary fw-bold"
          onClick={handleCreateCart}
        >
          Go to Cart
        </button>
      </div>

      <h2 className="mb-4 text-center text-primary fw-bold">Available Items</h2>
      <div className="row gap-4 justify-content-center">
        {items.map((item, index) => (
          <div className="col-md-4 col-sm-6 col-12" key={item.id}>
            <div 
              className="card shadow border-0 rounded-4 p-3" 
              style={{ background: "linear-gradient(135deg, #f8f9fa, #e9ecef)" }}
            >
              <div className="text-center">
                <img 
                  src={images[index % images.length]} 
                  className="rounded-3" 
                  alt={item.name} 
                  style={{ height: "200px", width: "100%", objectFit: "contain", padding: "10px", background: "#fff" }} 
                />
              </div>

              <div className="card-body text-center">
                <h5 className="card-title fw-bold text-dark">{item.name}</h5>
                <p className="card-text text-muted">{item.description}</p>
                <h6 className="text-success fs-5 fw-bold">Rs. {item.price.toFixed(2)}</h6>

                <div className="d-flex align-items-center justify-content-center mt-3">
                  <button 
                    className="btn btn-light border rounded-circle px-3 py-1 fw-bold shadow-sm" 
                    onClick={() => setQuantities({ ...quantities, [item.id]: Math.max((quantities[item.id] || 0) - 1, 0) })}
                    style={{ transition: "0.2s", color: "#ff6b6b", borderColor: "#ff6b6b" }}
                  >-</button>
                  <span className="mx-3 fs-5 fw-bold">{quantities[item.id] || 0}</span>
                  <button 
                    className="btn btn-light border rounded-circle px-3 py-1 fw-bold shadow-sm" 
                    onClick={() => setQuantities({ ...quantities, [item.id]: (quantities[item.id] || 0) + 1 })}
                    style={{ transition: "0.2s", color: "#4caf50", borderColor: "#4caf50" }}
                  >+</button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ItemList;
