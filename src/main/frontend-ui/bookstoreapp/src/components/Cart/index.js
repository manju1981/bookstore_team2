import React, { useState, useEffect } from "react";
import "./Cart.css";
import logo from "../../assets/delete.png";
import {
  Button,
  ButtonContent,
  Icon,
  Image,
  Dropdown,
} from "semantic-ui-react";
import {
  clearAllData,
  getItem,
  getTotalCartAmountQty,
  removeFromCart,
  updateItem,
} from "../../utils";
import CheckoutModal from "../CheckoutModal/CheckoutModal";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [countries, setCountries] = useState([]);
  const countryOptions = countries.map((item) => ({
    value: item.id,
    text: item.name,
  }));

  //   { key: "in", value: "in", flag: "in", text: "India" },
  //   { key: "af", value: "af", flag: "af", text: "Afghanistan" },
  //   { key: "ax", value: "ax", flag: "ax", text: "Aland Islands" },
  //   { key: "al", value: "al", flag: "al", text: "Albania" },
  //   { key: "dz", value: "dz", flag: "dz", text: "Algeria" },
  //   { key: "as", value: "as", flag: "as", text: "American Samoa" },
  //   { key: "ad", value: "ad", flag: "ad", text: "Andorra" },
  //   { key: "ao", value: "ao", flag: "ao", text: "Angola" },
  //   { key: "ai", value: "ai", flag: "ai", text: "Anguilla" },
  //   { key: "ag", value: "ag", flag: "ag", text: "Antigua" },
  //   { key: "ar", value: "ar", flag: "ar", text: "Argentina" },
  //   { key: "am", value: "am", flag: "am", text: "Armenia" },
  //   { key: "aw", value: "aw", flag: "aw", text: "Aruba" },
  //   { key: "au", value: "au", flag: "au", text: "Australia" },
  //   { key: "at", value: "at", flag: "at", text: "Austria" },
  //   { key: "az", value: "az", flag: "az", text: "Azerbaijan" },
  //   { key: "bs", value: "bs", flag: "bs", text: "Bahamas" },
  //   { key: "bh", value: "bh", flag: "bh", text: "Bahrain" },
  //   { key: "bd", value: "bd", flag: "bd", text: "Bangladesh" },
  //   { key: "bb", value: "bb", flag: "bb", text: "Barbados" },
  //   { key: "by", value: "by", flag: "by", text: "Belarus" },
  //   { key: "be", value: "be", flag: "be", text: "Belgium" },
  //   { key: "bz", value: "bz", flag: "bz", text: "Belize" },
  //   { key: "bj", value: "bj", flag: "bj", text: "Benin" },
  // ];

  useEffect(() => {
    setCartItems(getItem());
    fetch(`http://localhost:8090/api/v1/countries/fetch-all`)
      .then((response) => response.json())
      .then((data) => {
        setCountries(data);
      })
      .catch((error) => console.error(error));
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

  const placeOrder = () => {
    const requestBody = {
      countryId: 1,
      orders: cartItems.map((item) => ({
        bookId: item.bookId,
        quantity: item.quantity,
      })),
      totalOrderValues: getTotalCartAmountQty()?.total,
    };

    fetch(`http://localhost:8090/api/v1/orders`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    })
      .then((response) => response.json())
      .then((data) => {
        if (!data.statusCode) {
          clearAllCart();
          alert("Order placed!");
        } else {
          alert(data.message);
        }
      })
      .catch((error) => {
        alert("Failed to add order.");
        console.error(error);
      });
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
              <th></th>
            </tr>
          </thead>
          <tbody>
            {cartItems.map((item) => (
              <tr key={item.id}>
                <td>
                  <img
                    height={80}
                    width={80}
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
            <th></th>
            <th></th>
            <th>Total: {getTotalCartAmountQty()?.total}</th>
            <th></th>
          </tr>
        </table>

        {/* <h3 id="cart-total">Cart Total</h3> */}
        {/* <div id="totals">
          <span>Cart Totals</span>
          <span>Number of items: {getTotalCartQuantity()}</span>
          <span>Total: {getTotalCartAmount()}</span>
        </div> */}
      </div>
      <div className="check-delivery">
        <div className="check-del">Check Delivery: </div>

        <Dropdown
          placeholder="Select your country"
          options={countryOptions}
          search
          selection
          data-testid="country-dropdown"
        />
      </div>

      <CheckoutModal />

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
        {/* <Button
          circular={true}
          size="large"
          className="checkout-btn"
          animated="horizontal"
          onClick={placeOrder}
        >
          <ButtonContent hidden>
            <Icon name="long arrow alternate right" />
          </ButtonContent>
          <ButtonContent visible> CHECKOUT </ButtonContent>
        </Button> */}
      </div>
    </div>
  );
};

export default Cart;
