const headerStyle = {
  backgroundColor: "#262626",
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  padding: "10px",
  margin: "0",
  position: "fixed",
  zIndex: 4,
  top: 0,
  left: 0,
  right: 0,
};

const logoStyle = {
  marginRight: "10px",
  height: "auto",
  width: "50px",
};

const textStyle = {
  color: "#fff",
  fontFamily: "Montserrat, sans-serif",
  fontWeight: "300",
  fontSize: "1.7em",
};

const searchContainerStyle = {
  marginLeft: "auto",
  padding: "5px 15px",
};
const CartIcon = {
  width: "55px",
  color: "white",
  fontSize: "1.5em",
};

export const CartBadgeBody = {
  position: "absolute",
  width: 20,
  height: 20,
  backgroundColor: "pink",
  top: 15,
  right: 23,
  borderRadius: 20,
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  border: "1px solid #fff",
};

export const CartBadgeText = { fontSize: 9 };

export { headerStyle, logoStyle, textStyle, searchContainerStyle, CartIcon };
