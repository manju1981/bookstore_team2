import React from "react";
import { footerStyle, footerTextStyle, actionIconsStyle } from "./Footer.style";

const Footer = () => {
  return (
    <div style={footerStyle} data-testid="Footer-test">
      <div style={footerTextStyle}>
        "Books are the plane, and the train, and the road. They are the
        destination, and the journey. They are home."
      </div>
    </div>
  );
};

export default Footer;
