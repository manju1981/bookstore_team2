import React from "react";
import {
  ButtonContent,
  Header as HeaderComponent,
  Icon,
  Image,
} from "semantic-ui-react";
import { useNavigate } from "react-router-dom";
import logo from "../../assets/new_Logo.png";
import Search from "../Search/Search";
import {
  headerStyle,
  logoStyle,
  textStyle,
  CartIcon,
  searchContainerStyle,
  CartBadgeBody,
  CartBadgeText,
} from "./Header.style";
import { getItem } from "../../utils";

const Header = () => {
  const navigate = useNavigate();

  const navigateToContacts = (e, { result }) => {
    console.log("shipppp");
    navigate(`/cart/`);
  };

  const handleLogoClick = () => {
    navigate("/");
  };

  return (
    <HeaderComponent style={headerStyle} data-testid="Header-test">
      <Image
        src={logo}
        alt="book-logo"
        style={logoStyle}
        data-testid="Logo-test"
        onClick={handleLogoClick}
      />
      <span style={textStyle} data-testid="Title-test">
        THE BOOKSTORE
      </span>
      <div style={searchContainerStyle}>
        <Search />
      </div>
      <div>
        <ButtonContent visible>
          <Icon style={CartIcon} name="shop" onClick={navigateToContacts} />
        </ButtonContent>
        {getItem()?.length && (
          <div style={CartBadgeBody}>
            <span style={CartBadgeText}>{getItem()?.length}</span>
          </div>
        )}
      </div>
    </HeaderComponent>
  );
};

export default Header;
