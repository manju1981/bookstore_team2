import React from "react";
import logo from "../../assets/book_logo.png";
import { Header as HeaderComponent } from 'semantic-ui-react';
import Search from "../Search/Search";  
import { headerStyle, logoStyle, textStyle, searchContainerStyle } from './Header.style';  

const Header = () => {
  return (
    <HeaderComponent as='h2' style={headerStyle}>
      <img src={logo} alt="book-logo" style={logoStyle} />
      <span style={textStyle}>THE BOOKSTORE</span>
      <div style={searchContainerStyle}>
        <Search />
      </div>
    </HeaderComponent>
  );
};

export default Header;
