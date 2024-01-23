import React from "react";
import { Header as HeaderComponent, Image } from "semantic-ui-react";
import logo from "../../assets/book_logo.png";
import Search from "../Search/Search";
import {
  headerStyle,
  logoStyle,
  textStyle,
  searchContainerStyle,
} from "./Header.style";

const Header = () => {
  return (
    <HeaderComponent style={headerStyle} data-testid="Header-test">
      <Image
        src={logo}
        alt="book-logo"
        style={logoStyle}
        data-testid="Logo-test"
      />
      <span style={textStyle} data-testid="Title-test">
        THE BOOKSTORE
      </span>
      <div style={searchContainerStyle}>
        <Search />
      </div>
    </HeaderComponent>
  );
};

export default Header;
