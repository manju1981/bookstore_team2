import React, { useEffect, useState } from "react";
import {
    Image,
    HeaderSubheader,
    Header,
    Dropdown,
    ButtonContent,
    Button,
    Icon
} from "semantic-ui-react";
import { useParams } from "react-router-dom";
import {
    Container,
    DetailsContatiner,
    ImageContainer,
    ButtonContainer
} from "./BookDetails.style";

const countryOptions = [
    { key: "in", value: "in", flag: "in", text: "India" },
    { key: "af", value: "af", flag: "af", text: "Afghanistan" },
    // ... (rest of the options)
];

const BookDetails = () => {
    const { id } = useParams();
    const [bookDetails, setBookDetails] = useState({
        img: "",
        title: "",
        author: "",
        desc: ""
    });

    useEffect(() => {
        // Fetch book details based on the 'id' parameter
        // You need to implement your own logic to fetch data from the API
        // Replace the URL and API call with your actual endpoint
        fetch(`http://localhost:8090/api/v1/books/${id}`)
            .then((response) => response.json())
            .then((data) => {
                setBookDetails(data); // Update the bookDetails state with fetched data
            })
            .catch((error) => console.error(error));
    }, [id]);

    return (
        <div style={Container}>
            <Image style={ImageContainer} src={bookDetails.img} size="large" floated="left" />
            <div style={DetailsContatiner}>
            <div>
                <Header style={{ fontSize: "40px" }} textAlign="left" as="h1">
                    {bookDetails.title}
                    <Header style={{ fontSize: "20px", marginTop: "0.5rem" }} as="h4">
                        {bookDetails.author}
                        <HeaderSubheader style={{ fontSize: "18px", marginTop: "2rem" }} as="h4">
                            {bookDetails.description}
                        </HeaderSubheader>
                    </Header>
                    <div>
                              <label>Rs. {bookDetails?.price}</label>
                              </div>
                </Header>
            </div>
            <div style={{display: 'flex', justifyContent: 'space-between'}}>
             <Dropdown
                placeholder="Select your country"
                options={countryOptions}
                search
                selection
                data-testid="country-dropdown"
                style={{ marginTop: "1rem" }}
             />
             <Button circular={true} size="large" style={ButtonContainer} animated="horizontal">
                <ButtonContent hidden>BUY</ButtonContent>
                <ButtonContent visible>
                  <Icon name="shop" />
                </ButtonContent>
             </Button>
            </div>
            </div>
        </div>
    );
};

export default BookDetails;
