import React from "react";
import logo from "../../assets/book_logo.png";
import { Header as HeaderComponent } from 'semantic-ui-react';
import Search from "../Search/Search";  
import { headerStyle, logoStyle, textStyle, searchContainerStyle } from './Header.style';  

const Header = () => {
  return (
    <HeaderComponent style={headerStyle} data-testid="Header-test">
      <img src={logo} alt="book-logo" style={logoStyle} data-testid="Logo-test"/>
      <span style={textStyle} data-testid="Title-test">THE BOOKSTORE</span>
      <div style={searchContainerStyle}>
        <Search />
      </div>
    </HeaderComponent>
  );
};

export default Header;
