import React from "react";
import logo from "../../assets/book_logo.png";

const Header = () => {
  return (
    <div className="header-container">
      <img src={logo} className="icon-container" alt="book-logo" />
      <div className="header-text">THE BOOK STORE</div>
    </div>
  );
};

export default Header;
