import React, { useState, useEffect } from "react";
import "./Cart.css";
import logo from "../../assets/delete.png";
import { Button, ButtonContent, Icon, Image } from "semantic-ui-react";
import {
  clearAllData,
  getItem,
  getTotalCartAmountQty,
  removeFromCart,
  updateItem,
} from "../../utils";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    setCartItems(getItem());
  }, []);

  console.log("Shipra getItem ", getItem());

  const handleQuantityChange = (item, qty) => {
    const updatedCartItems = updateItem(item, qty) || [];
    setCartItems(updatedCartItems);
  };

  const handleDeleteItem = (item) => {
    const updatedCartItems = removeFromCart(item);
    setCartItems(updatedCartItems);
  };

  const clearAllCart = () => {
    clearAllData();
    setCartItems([]);
  };

  if (!cartItems?.length) {
    return (
      <div className="cart-container">
        <div className="cart-header">Cart is empty!</div>
      </div>
    );
  }

  return (
    <div className="cart-container">
      <div className="cart-header">Shopping Cart</div>
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>Product</th>
              <th>Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Total</th>
              <th>Delete Icon</th>
            </tr>
          </thead>
          <tbody>
            {cartItems.map((item) => (
              <tr key={item.id}>
                <td>
                  <img
                    height={100}
                    width={100}
                    src={item?.img}
                    alt="Product img"
                  />
                </td>
                <td>{item.title}</td>
                <td>{item.price}</td>
                <td>
                  <input
                    type="number"
                    min="1"
                    value={item.quantity}
                    onChange={(e) =>
                      handleQuantityChange(item, parseInt(e.target.value, 10))
                    }
                  />
                </td>
                <td>{(item?.quantity || 0) * (item?.price || 0)}</td>
                <td className="delete-icon-row">
                  <Image
                    className="delete-icon"
                    src={logo}
                    alt="delete-icon"
                    onClick={() => handleDeleteItem(item)}
                    data-testid="Logo-test"
                  />
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <table className="table-total">
          <tr>
            <th>Cart Totals</th>
            <th>Number of items: {getTotalCartAmountQty()?.qty}</th>
            <td></td>
            <th>Total: {getTotalCartAmountQty()?.total}</th>
            <td></td>
          </tr>
        </table>

        {/* <h3 id="cart-total">Cart Total</h3> */}
        {/* <div id="totals">
          <span>Cart Totals</span>
          <span>Number of items: {getTotalCartQuantity()}</span>
          <span>Total: {getTotalCartAmount()}</span>
        </div> */}
      </div>
      <div className="button-wrapper">
        <Button
          circular={true}
          size="large"
          className="checkout-btn"
          animated="horizontal"
          onClick={clearAllCart}
        >
          <ButtonContent hidden>
            <Icon name="close" />
          </ButtonContent>
          <ButtonContent visible> Clear Cart </ButtonContent>
        </Button>
        <Button
          circular={true}
          size="large"
          className="checkout-btn"
          animated="horizontal"
        >
          <ButtonContent hidden>
            <Icon name="long arrow alternate right" />
          </ButtonContent>
          <ButtonContent visible> CHECKOUT </ButtonContent>
        </Button>
      </div>
    </div>
  );
};

export default Cart;
