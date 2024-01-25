import React from "react";
import {
  ModalDescription,
  ModalContent,
  ModalActions,
  Button,
  Header,
  Image,
  Modal,
} from "semantic-ui-react";
import logo from "../../assets/new_Logo.png";

function CheckoutModal() {
  const [open, setOpen] = React.useState(false);

  return (
    <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={
        <Button
          style={{
            float: "right",
            background: "#6ba02b",
            borderRadius: "2em",
            color: "white",
            width: "250px",
            fontSize: "16px",
          }}
        >
          Check Out
        </Button>
      }
    >
      {/* <ModalHeader>Select a Photo</ModalHeader> */}
      <ModalContent
        style={{ flexDirection: "column", alignItems: "center" }}
        image
      >
        <Image size="small" src={logo} wrapped />
        <ModalDescription>
          <Header style={{ fontSize: "26px" }}>
            Order Placed Successfully!
          </Header>
          {/* <p>
            We've found the following gravatar image associated with your e-mail
            address.
          </p>
          <p>Is it okay to use this photo?</p> */}
        </ModalDescription>
      </ModalContent>
      <ModalActions>
        <Button
          style={{ width: "250px", fontSize: "16px", background: "#262626" }}
          color="black"
          onClick={() => setOpen(false)}
        >
          Go back to Shopping
        </Button>
        {/* <Button
          content="Yep, that's me"
          labelPosition="right"
          icon="checkmark"
          onClick={() => setOpen(false)}
          positive
        /> */}
      </ModalActions>
    </Modal>
  );
}

export default CheckoutModal;
