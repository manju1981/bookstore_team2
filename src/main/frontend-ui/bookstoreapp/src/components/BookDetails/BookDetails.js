import React, { useEffect, useState } from "react";

import {
  Image,
  HeaderSubheader,
  Header,
  Dropdown,
  ButtonContent,
  Button,
  Icon,
} from "semantic-ui-react";
import { Link, useParams } from "react-router-dom";
import {
  Container,
  DetailsContatiner,
  ToastMessage,
  ImageContainer,
  ButtonContainer,
} from "./BookDetails.style";
import { getItem, isNewInCart, setItem } from "../../utils";

const BookDetails = () => {
  const { id } = useParams();
  const [bookDetails, setBookDetails] = useState({
    img: "",
    title: "",
    author: "",
    description: "",
    quantity: 0, // Add quantity property
  });

  const [isAddedToCart, setIsAddedToCart] = useState(false);
  const [isAddedSuccessfully, setIsAddedSuccessfully] = useState(false);
  const [selectedCountry, setSelectedCountry] = useState(null); // Track selected country
  const [countries, setCountries] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8090/api/v1/books/${id}`)
      .then((response) => response.json())
      .then((data) => {
        if (!isNewInCart(data)) {
          setIsAddedToCart(true);
        }
        setBookDetails({ ...data, quantity: 0 });
      })
      .catch((error) => console.error(error));

    fetch(`http://localhost:8090/api/v1/countries/fetch-all`)
      .then((response) => response.json())
      .then((data) => {
        setCountries(data);
      })
      .catch((error) => console.error(error));
  }, [id]);

  const performAddToCart = () => {
    const cartData = getItem();
    if (cartData?.length) {
      const existingCartItem = cartData.find(
        (item) => item.id === bookDetails.id
      );
      if (existingCartItem) {
        existingCartItem.quantity += 1;
      } else {
        cartData.push({ ...bookDetails, quantity: 1 });
      }
      setItem(cartData);
      setIsAddedSuccessfully(true);
      setTimeout(() => {
        setIsAddedSuccessfully(false);
      }, 2000);
    } else {
      setItem([{ ...bookDetails, quantity: 1 }]);
    }
    setIsAddedToCart(true);
  };

  const handleCountryChange = (event, data) => {
    setSelectedCountry(data.value);
  };

  return (
    <div style={Container}>
      <Image
        style={ImageContainer}
        src={bookDetails.img}
        size="large"
        floated="left"
      />
      <div style={DetailsContatiner}>
        <Header style={{ fontSize: "40px" }} textAlign="left" as="h1">
          {bookDetails.title}
          <Header style={{ fontSize: "20px", marginTop: "0.5rem" }} as="h4">
            {bookDetails.author}
            <HeaderSubheader
              style={{ fontSize: "18px", marginTop: "2rem" }}
              as="h4"
            >
              {bookDetails.description}
            </HeaderSubheader>
          </Header>
        </Header>

        <Dropdown
          placeholder="Select your country"
          options={countries.map((item) => ({
            value: item.id,
            text: item.name,
          }))}
          search
          selection
          onChange={handleCountryChange}
          value={selectedCountry}
          style={{ marginTop: "1rem" }}
        />
        <Button
          circular={true}
          size="large"
          style={ButtonContainer}
          animated="horizontal"
          onClick={performAddToCart}
        >
          <ButtonContent hidden>BUY</ButtonContent>
          <ButtonContent visible>
            <Icon name="shop" />
          </ButtonContent>
        </Button>
      </div>
      <div style={ToastMessage}>
        {isAddedSuccessfully && (
          <div className="ui success message">
            <i className="close icon"></i>
            <div className="header">Item added to cart successfully!</div>
          </div>
        )}
      </div>
    </div>
  );
};

export default BookDetails;
