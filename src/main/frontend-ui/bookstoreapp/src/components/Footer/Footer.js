import React from "react";
import next_icon from "../../assets/next_icon.png";
import previous_icon from "../../assets/previous_icon.png";

const Footer = () => {
  return (
    <div className="footer-container">
      <div className="footer-text">
        "Books are the plane, and the train, and the road. They are the
        destination, and the journey. They are home."
      </div>
      <div className="action-icons">
        <img src={previous_icon} className="action-icon" alt="previous_icon" />
        <img src={next_icon} className="action-icon" alt="next_icon" />
      </div>
    </div>
  );
};

export default Footer;
